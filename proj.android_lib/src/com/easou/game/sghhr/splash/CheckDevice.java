package com.easou.game.sghhr.splash;

import java.util.HashMap;

import android.util.Log;

import com.easou.game.sghhr.Utils;

public class CheckDevice extends BaseStartTask{

	public CheckDevice(SplashBaseActivity slpashActivity) {
		super(slpashActivity);
	}

	@Override
	public void runTask(HashMap<String, Object> para) {
//		Log.d("StartWorkFlow",this.getClass().getName());
		onProgressMsg("正在检测运行环境");
		if (Utils.isEmulator(super.mSplashActivity)) {
			super.mSplashActivity.showToast("亲,请不要使用模拟器哦！");
			super.mSplashActivity.finish();
			onError(1, "流程中断，检测到正在模拟器中运行", null);
		}else{
			onFinish(null);
		}
	}
	
}
