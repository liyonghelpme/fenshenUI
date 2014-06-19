package com.easou.game.sghhr.splash;

import java.io.File;
import java.util.HashMap;

import com.easou.game.sghhr.R;
import com.easou.game.sghhr.ResUpdateUtil;
import com.easou.game.sghhr.common.BaseSplashActivity;
import com.easou.game.sghhr.log.LogActEnum;
import com.easou.game.sghhr.log.LogUtil;

public class UnZipGameRes extends BaseStartTask {

	public UnZipGameRes(SplashBaseActivity slpashActivity) {
		super(slpashActivity);
	}

	@Override
	public void runTask(final HashMap<String, Object> para) {
		// 更换背景图片
		BaseSplashActivity.setResource(new int[] { R.drawable.background1,
				R.drawable.background2, R.drawable.background3,
				R.drawable.background4 });
		LogUtil.sendLogInfo(LogActEnum.UNZIPASSERT.getAct(),
				mSplashActivity);
		ResUpdateUtil resUpdateUtil = new ResUpdateUtil(mSplashActivity);
		File gameResZipFile = null;
		if (para != null && para.containsKey("GAME_RES_ZIP_FILE")) {
			gameResZipFile = (File) para.get("GAME_RES_ZIP_FILE");
		}

		if (gameResZipFile == null) {
			UnZipGameRes.super.onFinish(null);
			// 更换背景图片
			BaseSplashActivity
					.setResource(new int[] { R.drawable.background0 });
			return;
		} else {
			// 更换背景图片
			BaseSplashActivity.setResource(new int[] { R.drawable.background1,
					R.drawable.background2, R.drawable.background3,
					R.drawable.background4 });
		}

		// 解压脚本
		ResUpdateUtil.installUpdate(mSplashActivity, gameResZipFile,
				new ResUpdateUtil.OnResInstallProgress() {
					public void onProgressMessage(String msg) {
						UnZipGameRes.super.onProgressMsg(msg);
					}

					@Override
					public void onError(int code, String msg) {
						mSplashActivity.showAlertDialogForError(
								"亲，初始化失败哦，再试试！", 0, getTag(), para);
					}

					@Override
					public void onFinish(Object o) {
						// 更换背景图片
						BaseSplashActivity
								.setResource(new int[] { R.drawable.background0 });

						UnZipGameRes.super.onFinish(null);
					}

					@Override
					public void onProgress(int n) {
						UnZipGameRes.super.onProgress(n);

					}
				});

	}

}
