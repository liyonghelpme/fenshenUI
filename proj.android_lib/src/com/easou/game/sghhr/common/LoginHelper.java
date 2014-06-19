package com.easou.game.sghhr.common;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.easou.game.sghhr.CustomConfig;
import com.easou.game.sghhr.Md5;
import com.easou.game.sghhr.MobileFunction;
import com.easou.game.sghhr.NetRequest;
import com.easou.game.sghhr.NetRequest.NetRequestCallback;
import com.easou.game.sghhr.SelectServerTip;
import com.easou.game.sghhr.ServerInfo;
import com.easou.game.sghhr.log.LogActEnum;
import com.easou.game.sghhr.log.LogData;
import com.easou.game.sghhr.log.LogUtil;
import com.easou.game.sghhr.login.LoginUtil;
import com.umeng.analytics.MobclickAgent;

public class LoginHelper {

	private boolean isLogin = false; // 是否成功登录第三方服务器
	private boolean isShowFailDialog = false; // 是否显示登录失败对话框
	private boolean isGetServerlist = false; // 是否成功获取服务器列表数据
	private ServerInfo serverInfo = null; // 用户选择的服务器对象
	private List<ServerInfo> serverList = null; // 服务器列表对象
	private String authorizationCode = ""; // 第三方授权token
	private Handler mHandler; // 用来异步更新UI
	private IConfig config = null; // 配置信息
	private IStartGameEntryActivity startGame = null; // 启动游戏接口

	public boolean isShowFailDialog() {
		return isShowFailDialog;
	}

	public void setShowFailDialog(boolean isShowFailDialog) {
		this.isShowFailDialog = isShowFailDialog;
	}

	/**
	 * 创建登录助手
	 * 
	 * @param handler
	 * @param apiDoMainURL
	 * @param partenerId
	 */
	public LoginHelper(Handler handler, IConfig config,
			IStartGameEntryActivity startGame) {
		this.mHandler = handler;
		this.config = config;
		this.startGame = startGame;
	}

	/**
	 * 登录游戏服务器
	 * 
	 * @param authorizationCode
	 */
	public void beginLoginGameServer(final String authorizationCode,
			final String serverId, final Activity mActivity) {
		new Thread() {
			public void run() {
				NetRequest.getTimeDataRequest(BaseApp.getContext(),
						new BeginGameNetCallBack(authorizationCode, serverId,
								mActivity), config.getApiDomain());
			};
		}.start();
	}

	/**
	 * 使用Handler显示对话框
	 * 
	 * @param title
	 * @param msgStr
	 * @param what
	 */
	private void sendMessage(Object obj, int what) {
		Message msg = mHandler.obtainMessage();
		msg.what = what;
		msg.obj = obj;
		msg.sendToTarget();
	}

	/**
	 * 开始游戏
	 * 
	 * @param authorizationCode
	 * @param instance
	 */
	public void startGame(Activity instance) {
		MobclickAgent.onEvent(instance, "begin_game_btn");
		LogUtil.sendLogInfo(LogActEnum.GETSERVERLIST.getAct(), null);
		LogUtil.sendLogInfo(LogActEnum.LOGIN.getAct(), null);
		if (isLogin) {
			ProgressLoading.startProgress(instance, "正在进入游戏...");
			if (isGetServerlist) {
				beginLoginGameServer(authorizationCode,
						serverInfo.getServer_id(), instance);
			} else {
				sendMessage("登陆异常" + ":" + "登录服务器异常，请重试！",
						UIHelper.UIHELPER_HANDLER_SHOW_RELOGIN_DIALOG);
			}
		} else
			sendMessage(null, UIHelper.UIHELPER_HANDLER_DOLOGIN);
	}

	/**
	 * 选择服务器
	 */
	public void selectServer(final Activity instance) {
		SelectServerTip selectServerTip = new SelectServerTip(instance,
				new SelectServerTip.OnServerSelected() {
					@Override
					public void onSelected(ServerInfo serverInfo) {
						MobclickAgent.onEvent(instance,
								"select_server_callback");// 用户选服回调
						if (serverInfo != null) {
							sendMessage(
									serverInfo,
									UIHelper.UIHELPER_HANDLER_UPDATE_SERVERNAME_TEXT);
							BaseLoginActivity.saveServerId(serverInfo
									.getServer_id());
							BaseLoginActivity.saveCurrentServerInfo(serverInfo);
							LoginHelper.this.serverInfo = serverInfo;
						}
					}
				}, serverList);
		selectServerTip.show();
	}

	/**
	 * 开始游戏回调列表
	 * 
	 * @author ted
	 */
	private class BeginGameNetCallBack implements NetRequestCallback {
		private long timeValue = 0L; // 时间戳
		private String signValue = ""; // 签名值
		private String authorizationCode = "";// 从第三方获得的授权token
		private String serverId = ""; // 登录游戏服务器的Id
		private Activity mActivity = null;

		public BeginGameNetCallBack(String authorizationCode, String serverId,
				Activity activity) {
			this.authorizationCode = authorizationCode;
			this.serverId = serverId;
			this.mActivity = activity;
		}

		@Override
		public void onSucc(Map<String, Object> data) {
			timeValue = (Long) data.get("time");
			String token = authorizationCode;
			signValue = Md5.encode(token + config.getPartenerId() + serverId
					+ timeValue + CustomConfig.key);
			// 登录游戏服务器
			LoginCenterRequest(token, serverId, timeValue, signValue, mActivity);
		}

		@Override
		public void onError(int code, String msg, Exception e) {
			isLogin = false;
			isShowFailDialog = true;
			MobclickAgent.onEvent(BaseApp.getContext(),
					"login_game_server_time_error");// 获取时间戳异常
			sendMessage("登陆异常" + ":" + "登录服务器异常，请重试！",
					UIHelper.UIHELPER_HANDLER_SHOW_RELOGIN_DIALOG);
			;
		}
	}

	/**
	 * 登录游戏服务器
	 * 
	 * @param token
	 * @param serverId_value
	 * @param time_value
	 * @param sign_value
	 */
	public void LoginCenterRequest(final String token,
			final String serverId_value, final long time_value,
			final String sign_value, final Activity activity) {
		MobclickAgent.onEvent(BaseApp.getContext(), "login_game_server");
		new Thread() {
			public void run() {
				NetRequest.LoginCenterRequest(BaseApp.getContext(),
						new LoginGameServerNetCallBack(activity),
						config.getApiDomain(), token, config.getPartenerId(),
						serverId_value, time_value, sign_value);
			};
		}.start();
	}

	/**
	 * 登录游戏服务器回调
	 * 
	 * @author ted
	 */
	private class LoginGameServerNetCallBack implements NetRequestCallback {
		private Activity mActivity = null;

		public LoginGameServerNetCallBack(Activity activity) {
			this.mActivity = activity;
		}

		@Override
		public void onSucc(Map<String, Object> data) {
			// 成功登录游戏服务器
			MobclickAgent.onEvent(BaseApp.getContext(),
					"login_game_server_success");
			JSONObject obj = (JSONObject) data.get("data");
			EasouLoginInfo easouLoginInfo = EasouLoginInfo.parseJson(obj
					.toString());
			// 保存用户信息
			CommonUtil.saveEasouLoginInfo(easouLoginInfo);
			// 保存登录信息到文件中（在游戏中读取这些文件）
			savePayInfo(serverInfo, mActivity, LogUtil.logData);
			ProgressLoading.endProgress();
			// 启动游戏
			startGame.goGameEntryActivity(mActivity);
		}

		@Override
		public void onError(int code, String msg, Exception e) {
			MobclickAgent.onEvent(BaseApp.getContext(),
					"login_game_server_error");// 登录游戏服务器失败
			isLogin = false;
			isShowFailDialog = true;
			ProgressLoading.endProgress();
			sendMessage("登录失败" + ":" + "登录游戏失败",
					UIHelper.UIHELPER_HANDLER_SHOW_RELOGIN_DIALOG);
		}
	}

	/**
	 * 获取服务器列表数据
	 */
	private void getLoginData() {
		new Thread() {
			public void run() {
				MobclickAgent.onEvent(BaseApp.getContext(), "try_login_center");// 登录用户中心
				if (!isLogin) {
					MobclickAgent.onEvent(BaseApp.getContext(),
							"try_centerlogin_error");// 登录用户中心异常
				} else {
					MobclickAgent.onEvent(BaseApp.getContext(),
							"login_center_success");// 成功登录用户中心
					NetRequest.getServerListDataRequest(BaseApp.getContext(),
							new GetLoginDataNetCallback(),
							config.getApiDomain());
				}
			}
		}.start();
	}

	/**
	 * 获取服务器列表请求回调
	 * 
	 * @author ted
	 */
	private class GetLoginDataNetCallback implements NetRequestCallback {
		@Override
		public void onSucc(Map<String, Object> data) {
			MobclickAgent.onEvent(BaseApp.getContext(), "get_server_list");// 获取服务器列表
			String json = (String) data.get("serverlist");
			// 获取篡改后的内容（开发测试时使用）
			serverList = config.reWriteServerList();
			if (serverList == null || serverList.size() <= 0) {
				// 用户并未进行选服列表的篡改，则进行正常调用
				serverList = ServerInfo.parseServerList(json);
			}
			serverInfo = BaseLoginActivity.getServerInfo(serverList);
			if (!MobileFunction.playList.contains(serverInfo)) {
				MobileFunction.playList.add(0, serverInfo);
			}
			BaseLoginActivity.saveCurrentServerInfo(serverInfo);
			sendMessage(serverInfo,
					UIHelper.UIHELPER_HANDLER_UPDATE_SERVERNAME_TEXT);
			isGetServerlist = true;
			if (isLogin && isGetServerlist) {
				ProgressLoading.endProgress();
			}
			LogUtil.sendLogInfo(LogActEnum.GETSERVERLIST.getAct(), null);
		}

		@Override
		public void onError(int code, String msg, Exception e) {
			ProgressLoading.endProgress();
			isLogin = false;
			isShowFailDialog = true;
			MobclickAgent
					.onEvent(BaseApp.getContext(), "get_server_list_error");// 获取服务器列表异常
			sendMessage("登录失败" + ":" + "登录游戏失败",
					UIHelper.UIHELPER_HANDLER_SHOW_RELOGIN_DIALOG);
		}
	}

	/**
	 * 登陆成功时调用
	 * 
	 * @param authorizationCode
	 *            第三方返回的token数据
	 */
	public void loginSuccCallBack(final Activity instance, final String token) {
		LogUtil.sendLogInfo(LogActEnum.SDKLOGIN.getAct(), instance);
		instance.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				BaseLoginActivity.dismissReloginDialog();
				ProgressLoading.endProgress();
				authorizationCode = token;
				isLogin = true;// 是否登录标识
				isShowFailDialog = false;
				ProgressLoading.startProgress(instance, "正在加载服务器列表...");
				getLoginData();// 获取服务器列表数据
				Toast.makeText(instance, "恭喜您，登录成功！", Toast.LENGTH_SHORT)
						.show();
				UIHelper.startLoginAnimation(instance, mHandler);
			}
		});

	}

	/**
	 * 登录失败时调用
	 * 
	 * @param msg
	 *            失败原因
	 */
	public void loginFailedCallBack(final String msg) {
		ProgressLoading.endProgress();
		isLogin = false;// 是否登录标识
		isShowFailDialog = true;
		if (msg != null && !"".equals(msg))
			sendMessage("登录提示:" + msg,
					UIHelper.UIHELPER_HANDLER_SHOW_RELOGIN_DIALOG);
		else
			sendMessage("登录提示:登陆失败，请重试！",
					UIHelper.UIHELPER_HANDLER_SHOW_RELOGIN_DIALOG);
	}

	/**
	 * 注销成功调用
	 */
	public void signOut() {
		isLogin = false;
		isShowFailDialog = true;
	}

	/**
	 * 将创建订单所用到的信息保存到文件中
	 */
	private void savePayInfo(ServerInfo serverInfo, Context mContext,
			LogData logData) {
		EasouLoginInfo info = CommonUtil.getEasouLoginInfo();
		LoginUtil.saveLoginInfoToFile(mContext, info.getTk(),
				serverInfo.getServer_id(), info.getUid(),
				config.getPartenerId(), serverInfo.getServer_port() + "",
				info.getPuid(), null, serverInfo.getServer_name(), logData);
	}

	public boolean isLogin() {
		return isLogin;
	}
}
