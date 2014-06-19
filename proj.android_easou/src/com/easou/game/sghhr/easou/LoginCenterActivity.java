package com.easou.game.sghhr.easou;

import android.app.Activity;
import android.content.Intent;

import com.easou.androidsdk.StartESAccountCenter;
import com.easou.androidsdk.StartESAccountCenter.LoginCallBack;
import com.easou.androidsdk.StartESAccountCenter.PasswordChangeCallBack;
import com.easou.bbs.SuspensionService;
import com.easou.game.ldsg.GameEntryActivity;
import com.easou.game.sghhr.common.BaseLoginActivity;
import com.easou.game.sghhr.common.IConfig;
import com.easou.game.sghhr.common.IStartGameEntryActivity;
import com.easou.game.sghhr.common.ProgressLoading;

/**
 * 登录注册页面
 */
public class LoginCenterActivity extends BaseLoginActivity {

	private boolean isLoginCenter = false;
	private boolean isClickUserCenter = false;
	private String lastToken;

	/**
	 * 1.初始化第三方SDK
	 */
	@Override
	protected void initSDK() {

	}

	/**
	 * 2.进行登录
	 */
	@Override
	protected void doLogin() {
		ProgressLoading.startProgress(instance, "正在登陆...");
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				 StartESAccountCenter.login(instance, mHandler,new LoginCallBack() {
					
					@Override
					public void loginSuccess(String token) {
						// TODO Auto-generated method stub
						loginHelper.loginSuccCallBack(instance, token);
					}
					
					@Override
					public void loginFail(String msg) {
						// TODO Auto-generated method stub
						loginHelper.loginFailedCallBack(msg);
					}
				});// 登录用户中心
			}
		}).start();

		// TODO: 3.调用第三方登录 eg: SDK.login(){...}
		// TODO: 4.登录成功后调用loginHelper.loginSuccCallBack(instance,token + ":" +
		// memberId);
		// TODO: 4.登录失败后调用loginHelper.loginFailedCallBack("登陆失败的原因");
	}

	/** 账号管理 */
	protected void accountManager() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (!isClickUserCenter) {
					isClickUserCenter = true;
					StartESAccountCenter.startPortraitActivity(instance,new PasswordChangeCallBack() {
						
						@Override
						public void passwordChange() {
							// TODO Auto-generated method stub
							
						}
					});
				}
			}
		}).start();

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (Config.isOpenBBS) {
			Intent intent = new Intent(this, SuspensionService.class);
			this.stopService(intent);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		isClickUserCenter = false;
		if (Config.isOpenBBS) {
			Intent intent_su = new Intent(this, SuspensionService.class);
			this.startService(intent_su);
		}
	}

	/************************************************************************
	 * 回调接口，如果没有明确要求则不需要修改
	 ************************************************************************/
	@Override
	protected IConfig getConfig() {
		return new Config();
	}

	@Override
	protected IStartGameEntryActivity getStarter() {
		return new StartGameEntryActivity();
	}

	/**
	 * 启动游戏Activity
	 * 
	 * @author ted
	 */
	private class StartGameEntryActivity implements IStartGameEntryActivity {
		@Override
		public void goGameEntryActivity(Activity mActivity) {
			Intent intent = new Intent();
			intent.setClass(mActivity, GameEntryActivity.class);
			mActivity.startActivity(intent);
			mActivity.finish();
//			overridePendingTransition(R.anim.hyperspace_in,
//					R.anim.hyperspace_out);
			//overridePendingTransition(R.anim., exitAnim)
			//overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
		}
	}

}