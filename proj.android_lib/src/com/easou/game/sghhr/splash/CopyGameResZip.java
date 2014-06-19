package com.easou.game.sghhr.splash;

import java.io.File;
import java.util.HashMap;

import com.easou.game.sghhr.ResUpdateUtil;
import com.easou.game.sghhr.log.LogActEnum;
import com.easou.game.sghhr.log.LogUtil;

public class CopyGameResZip extends BaseStartTask {

	public CopyGameResZip(SplashBaseActivity slpashActivity) {
		super(slpashActivity);

	}

	@Override
	public void runTask(final HashMap<String, Object> para) {
		// Log.d("StartWorkFlow",this.getClass().getName());
		ResUpdateUtil resUpdateUtil = new ResUpdateUtil(mSplashActivity);
		LogUtil.sendLogInfo(LogActEnum.COPYASSERT.getAct(),mSplashActivity);
		// 复制脚本
		resUpdateUtil.checkAndCopyAssertToScrpitDir(mSplashActivity,
				new ResUpdateUtil.OnResInstallProgress() {
					public void onProgressMessage(String msg) {
						CopyGameResZip.super.onProgressMsg(msg);
					}

					@Override
					public void onError(int code, String msg) {
						mSplashActivity.showAlertDialogForError(
								"亲，初始化游戏失败哦,再试试！", 0, getTag(), para);
					}

					@Override
					public void onFinish(Object o) {

						if (o != null) {
							File gameResZipFile = (File) o;
							HashMap<String, Object> result = new HashMap<String, Object>();
							result.put("GAME_RES_ZIP_FILE", gameResZipFile);
							CopyGameResZip.super.onFinish(result);
						} else {
							CopyGameResZip.super.onFinish(null);
						}
					}

					@Override
					public void onProgress(int n) {
						CopyGameResZip.super.onProgress(n);

					}
				});
	}

}
