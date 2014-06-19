package com.easou.game.sghhr.common;

import org.cocos2dx.plugin.PluginWrapper;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

import com.easou.game.sghhr.BackgroundMusicService;
import com.easou.game.sghhr.GameEntryBaseActivity;
import com.easou.game.sghhr.ServerInfo;

public abstract class BaseGameEntryActivity extends GameEntryBaseActivity {

	private static final String TAG = "GameEntryActivity";
	static {
		System.loadLibrary("ldsggame");
	}

	/** 跳转界面--退出游戏界面 */
	private WakeLock mWakeLock;
	public Handler mHandler = null;

	/**
	 * 请求锁：防屏幕关闭
	 */
	private void acquireWakeLock() {
		PowerManager pm = (PowerManager) this
				.getSystemService(Context.POWER_SERVICE);
		if (mWakeLock != null) {
			mWakeLock.release();
			mWakeLock = null;
		}
		mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, TAG);
		mWakeLock.acquire();
	}

	/**
	 * 释放锁：防屏幕关闭
	 */
	private void releaseWakeLock() {
		if (mWakeLock != null) {
			mWakeLock.release();
			mWakeLock = null;
		}

	}

	@Override
	protected void onResume() {
		BackgroundMusicService.stopMusic();
		acquireWakeLock();
		super.onResume();
	}

	@Override
	protected void onPause() {
		releaseWakeLock();
		super.onPause();
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mHandler = new Handler();
		PluginWrapper.init(this);
		// TODO： 6.更具第三方SDK确定是否需要初始化SDK
		initSDKCallBack();
	}

	@Override
	protected void onDestroy() {
		BaseApp.exit();
		super.onDestroy();
	}

	@Override
	public void doPay(String payParaJsonStr) {
		LdsgPayBean bean = LdsgPayBean.parseJson(payParaJsonStr);
		ServerInfo info = BaseLoginActivity.getCurrentServerInfo();
		if (bean == null) {
			bean = new LdsgPayBean();
			bean.setExt1(payParaJsonStr);
		}
		pay(bean, info);
	};

	/** 支付 */
	public abstract void pay(LdsgPayBean bean, ServerInfo info);

	/** 初始化SDK */
	public abstract void initSDKCallBack();

}
