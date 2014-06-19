package com.easou.game.sghhr.easou;

import android.content.Intent;
import android.os.Bundle;
import com.easou.game.sghhr.common.BaseSplashActivity;
import com.easou.game.sghhr.common.UIHelper;
import com.easou.game.sghhr.easou.R;
import com.umeng.analytics.MobclickAgent;

public class SplashActivity extends BaseSplashActivity {

	private SplashActivity mActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_splash);
		mActivity = this;
		// 初始化
		init();
		// 播放开机动画
		UIHelper.start(mActivity, new Config(), new Runnable() {
			public void run() {
				// 开启音乐
//				Intent intent = new Intent(mActivity,
//						BackgroundMusicService.class);
//				intent.setType("BACKGROUND");// 设置Intent标识
//				mActivity.startService(intent);

				new Thread() {
					public void run() {
						// 初始化启动流程
						runWorkflow();
					};
				}.start();
				MobclickAgent.onEvent(mActivity, "loading");
			}
		});
	}

	/** 跳转到用户中心登录界面 */
	@Override
	public void jumpToLoginCenterActivity() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// 启动登录页
				Intent intent = new Intent(SplashActivity.this,
						LoginCenterActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				SplashActivity.this.startActivity(intent);
				SplashActivity.this.finish();
				//overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
//				overridePendingTransition(R.anim.slide_up_in,
//						R.anim.slide_down_out);
				overridePendingTransition(R.anim.fade, R.anim.hold);
			}
		});
	}
	
}
