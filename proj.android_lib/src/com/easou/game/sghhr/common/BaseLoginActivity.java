package com.easou.game.sghhr.common;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.easou.game.sghhr.BackgroundMusicService;
import com.easou.game.sghhr.R;
import com.easou.game.sghhr.ResUpdateUtil;
import com.easou.game.sghhr.ServerInfo;
import com.easou.game.sghhr.Utils;
import com.easou.game.sghhr.secretarydialog.SecretaryDialog;
import com.easou.game.sghhr.secretarydialog.SecretaryDialog.DialogCallBack;
import com.umeng.analytics.MobclickAgent;

public abstract class BaseLoginActivity extends Activity {
	/** 跳转界面--到游戏界面 */
	protected static Activity instance;
	/** 消息处理对象 */
	protected Handler mHandler;
	/** 当前服务器名称 */
	protected TextView curGameServerNameText;
	/** 当前服务器状态 */
	protected ImageView curGameServerState;
	
	/** 登录游戏助手类 */
	protected LoginHelper loginHelper = null;
	/** 是否有账号管理功能 */
	private boolean isHaveAccountManager = false;
	/** 服务器对象 */
	private static ServerInfo currentServerInfo = null;

	/** 登录接口 */
	protected abstract void doLogin();

	/** 账号管理接口 */
	protected abstract void accountManager();

	/** 获取配置信息对象 */
	protected abstract IConfig getConfig();

	/** 获取游戏启动对象 */
	protected abstract IStartGameEntryActivity getStarter();

	/** 初始化第三方SDK */
	protected abstract void initSDK();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_mainpage);
		MobclickAgent.onEvent(instance, "loading_finish");// 加载完成
		IConfig config = getConfig();
		init(this, config);
		// 1.初始化第三方SDK(根据SDK文档来确定是否在此处初始化)
		initSDK();
		// 2.进行登录操作
		doLogin();
	}

	/**
	 * 初始化
	 * 
	 * @param activity
	 * @param config
	 */
	protected void init(Activity activity, IConfig config) {
		// 初始化数据量
		instance = activity;
		mHandler = new MHandler();
		this.isHaveAccountManager = config.isHaveAccountManager();
		loginHelper = new LoginHelper(mHandler, config, getStarter());
		// 初始化控件
		((TextView) findViewById(R.id.textview_version)).setText("v"
				+ Utils.getApkVersionName(instance) + "."
				+ ResUpdateUtil.getGameApkVersion(instance) + "."
				+ ResUpdateUtil.getGameResVersion(instance));
		curGameServerNameText = (TextView) findViewById(R.id.cur_gamecenter);
		curGameServerState =  (ImageView) findViewById(R.id.cur_tip_id);
		// 默认不隐藏账号管理按钮
		if (isHaveAccountManager) {
			findViewById(R.id.usercenter_btn).setVisibility(View.VISIBLE);
		} else {
			findViewById(R.id.usercenter_btn).setVisibility(View.INVISIBLE);
		}
		addListener();
	}

	/** 添加监听器 */
	private void addListener() {
		// 账号管理
		findViewById(R.id.usercenter_btn).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// 账号管理按钮点击
						if (isHaveAccountManager) {
							MobclickAgent.onEvent(instance, "usercenter_btn");
							accountManager();
						}
					}
				});

		// 选服列表
		findViewById(R.id.select_server_btn).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						MobclickAgent
								.onEvent(instance, "select_gamecenter_btn");
						loginHelper.selectServer(instance);
					}
				});

		// 开始游戏
		findViewById(R.id.begin_game).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MobclickAgent.onEvent(instance, "begin_game_btn");
				loginHelper.startGame(instance);
			}
		});
	}

	/**
	 * 异步更新UI
	 * 
	 * @author ted
	 */
	private class MHandler extends Handler {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UIHelper.UIHELPER_HANDLER_SHOW_NONETWORK_DIALOG:
				ProgressLoading.endProgress();
				showSetNetworkDialog("网络错误", "网络连接错误，请检查网络设置。");
				break;
			case UIHelper.UIHELPER_HANDLER_SHOW_RELOGIN_DIALOG:
				// msg.obj的结构是 title:msg
				String[] details = msg.obj.toString().split(":");
				showReloginDialog(details[0], details[1]);
				break;
			case UIHelper.UIHELPER_HANDLER_UPDATE_SERVERNAME_TEXT:
				ServerInfo serverInfo = (ServerInfo) msg.obj;
				curGameServerNameText.setText(serverInfo.getServer_name());
				
				if(serverInfo.getServer_status() == 1){
					curGameServerState.setBackgroundResource(R.drawable.smooth_txt);
				}else if(serverInfo.getServer_status() == 2){
					curGameServerState.setBackgroundResource(R.drawable.full_txt);
				}else if(serverInfo.getServer_status() == 3){
					curGameServerState.setBackgroundResource(R.drawable.latest_txt);
				}
				
				break;
			case UIHelper.UIHELPER_HANDLER_DOLOGIN:
				doLogin();
				break;
			default:
				break;
			}
		}
	}

	/**
	 * 处理网络异常
	 */
	public void showSetNetworkDialog(final String title, final String msg) {
		ProgressLoading.endProgress();
		final AlertDialog.Builder builder = new AlertDialog.Builder(instance);
		instance.runOnUiThread(new Runnable() {
			public void run() {
				// builder.setTitle(title);
				// builder.setMessage(msg);
				// builder.setCancelable(false);
				// builder.setPositiveButton("退出程序",
				// new DialogInterface.OnClickListener() {
				// @Override
				// public void onClick(DialogInterface dialog,
				// int which) {
				// instance.finish();
				// }
				// });
				//
				// builder.setNegativeButton("设置网络",
				// new DialogInterface.OnClickListener() {
				// @Override
				// public void onClick(DialogInterface dialog,
				// int which) {
				// instance.startActivity(new Intent(
				// Settings.ACTION_WIRELESS_SETTINGS));
				// }
				// });
				// builder.show();

				SecretaryDialog.startSecretaryDialog(instance, "小秘书：", msg,
						SecretaryDialog.pointView, new DialogCallBack() {

							@Override
							public void restPressed() {
								// TODO Auto-generated method stub

							}

							@Override
							public void confirmPressed() {
								// TODO Auto-generated method stub
								instance.startActivity(new Intent(
										Settings.ACTION_WIRELESS_SETTINGS));
							}

							@Override
							public void cancalPressed() {
								// TODO Auto-generated method stub
								instance.finish();
							}
						});

			}
		});
	}

	/**
	 * 异常处理对话框
	 */
	private static AlertDialog alertDialog = null;

	public void showReloginDialog(final String title, final String msg) {
		ProgressLoading.endProgress();
		dismissReloginDialog();
		alertDialog = new AlertDialog.Builder(instance).create();
		instance.runOnUiThread(new Runnable() {
			public void run() {
				alertDialog.setTitle(title);
				alertDialog.setMessage(msg);
				alertDialog.setCancelable(false);

				alertDialog.setButton(AlertDialog.BUTTON1, "退出程序",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dismissReloginDialog();
								instance.finish();
								android.os.Process
										.killProcess(android.os.Process.myPid());
							}
						});

				alertDialog.setButton(AlertDialog.BUTTON3, "重试",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dismissReloginDialog();
								ProgressLoading.endProgress();
								doLogin();
							}
						});
				alertDialog.show();
				// new AlertDialog.Builder(instance).create();

				// SecretaryDialog.startSecretaryDialog(instance, "小秘书：", msg,
				// SecretaryDialog.pointView, new DialogCallBack() {
				//
				// @Override
				// public void restPressed() {
				// // TODO Auto-generated method stub
				//
				// }
				//
				// @Override
				// public void confirmPressed() {
				// // TODO Auto-generated method stub
				// ProgressLoading.endProgress();
				// doLogin();
				// }
				//
				// @Override
				// public void cancalPressed() {
				// // TODO Auto-generated method stub
				// instance.finish();
				// }
				// });
			}
		});
	}

	/**
	 * 关闭异常处理对话框
	 */
	public static void dismissReloginDialog() {
		if (alertDialog != null) {
			alertDialog.dismiss();
			alertDialog = null;
		}
	}

	@Override
	protected void onDestroy() {
		instance = null;
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
		if (BaseApp.isOrderToExit()) {
			this.finish();
		}
		ProgressLoading.endProgress();
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (!loginHelper.isLogin() && loginHelper.isShowFailDialog()) {
					runOnUiThread(new Runnable() {
						public void run() {
							showReloginDialog("登录提示", "登录失败，请重试！");
						}
					});
				}
				cancel();
			}
		}, 500);

		// 开启音乐
		Intent intent = new Intent(this, BackgroundMusicService.class);
		intent.setType("BACKGROUND");// 设置Intent标识
		startService(intent);
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
		loginHelper.setShowFailDialog(true);
		BackgroundMusicService.stopMusic();
	}

	@Override
	public void onBackPressed() {
		Intent i = new Intent(Intent.ACTION_MAIN);
		i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		i.addCategory(Intent.CATEGORY_HOME);
		startActivity(i);
	}

	// 获取SharedPreferences
	public static SharedPreferences getServerPerferences() {
		SharedPreferences preferences = instance.getSharedPreferences(
				"ServerInfo", MODE_PRIVATE);
		return preferences;
	}

	/**
	 * 保存服务器列表Id
	 * 
	 * @param serverList
	 * @param serverIndex
	 */
	public static void saveServerId(String serverId) {
		// 保存服务器列表信息
		SharedPreferences perference = getServerPerferences();
		perference.edit().putString("ServerId", serverId).commit();
	}

	/**
	 * 获取保存的服务器对象
	 * 
	 * @param serverList
	 * @return
	 */
	public static ServerInfo getServerInfo(List<ServerInfo> serverList) {
		String recordServerId = getServerPerferences().getString("ServerId",
				"请选择服务器");
		ServerInfo serverInfo = null;
		for (ServerInfo info : serverList) {
			if (info.getServer_id().equals(recordServerId)) {
				serverInfo = info;
				break;
			}
		}
		if (serverInfo == null)
			serverInfo = serverList.get(0);// 默认选择第一个
		return serverInfo;
	}

	/**
	 * 保存当前服务器对象
	 * 
	 * @param serverInfo
	 */
	public static void saveCurrentServerInfo(ServerInfo serverInfo) {
		currentServerInfo = serverInfo;
		// 将该接口开放到CommonUtil中
		CommonUtil.saveCurrentServerInfo(serverInfo);
	}

	/**
	 * 获取当前服务器对象
	 * 
	 * @return
	 */
	public static ServerInfo getCurrentServerInfo() {
		return currentServerInfo;
	}

}
