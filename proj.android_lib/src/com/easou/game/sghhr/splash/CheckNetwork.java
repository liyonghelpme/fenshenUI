package com.easou.game.sghhr.splash;

import java.util.HashMap;

import android.util.Log;

import com.easou.game.sghhr.Utils;

public class CheckNetwork extends BaseStartTask{

	public CheckNetwork(SplashBaseActivity slpashActivity) {
		super(slpashActivity);
	}

	@Override
	public void runTask(HashMap<String, Object> para) {
//		Log.d("StartWorkFlow",this.getClass().getName());
		onProgressMsg("正在检测网络");
		if (!Utils.isNetworkUseable(super.mSplashActivity)) {
			super.mSplashActivity.showAlertDialogForError("亲，网络不给力哦，再试试！",0,getTag(),para);
			onError(1, "网络连接失败，请检测网络设置！", null);
		}else{
			onFinish(null);
		}
	}
	
}
