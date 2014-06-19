package com.easou.game.sghhr.splash;

import java.util.HashMap;

import android.util.Log;

import com.easou.game.sghhr.Activation;
import com.easou.game.sghhr.CommonConfig;
import com.easou.game.sghhr.CustomConfig;

public class CheckActivation extends BaseStartTask {

	public CheckActivation(SplashBaseActivity slpashActivity) {
		super(slpashActivity);
	}

	@Override
	public void runTask(HashMap<String, Object> para) {
//		Log.d("StartWorkFlow",this.getClass().getName());
		if (!Activation.isActive(super.mSplashActivity, CustomConfig.isNeedActivation(),
				CustomConfig.getApiDomain())) {
			Activation.showDialogToActive(super.mSplashActivity);
			onError(1, "流程中断，需要激活码激活", null);
		}else{
			onFinish(null);
		}
	}

}
