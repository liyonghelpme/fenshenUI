package com.easou.game.sghhr.splash;

import java.util.HashMap;

import android.util.Log;

public class GoToLogin extends BaseStartTask{

	public GoToLogin(SplashBaseActivity slpashActivity) {
		super(slpashActivity);
	}

	@Override
	public void runTask(HashMap<String, Object> para) {
//		Log.d("StartWorkFlow",this.getClass().getName());
		onProgressMsg("开始游戏祝您愉快！");
		super.mSplashActivity.jumpToLoginCenterActivity();
		onFinish(null);
	}
	
}
