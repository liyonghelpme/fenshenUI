package com.easou.game.sghhr.splash;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;

import android.text.TextUtils;

import com.easou.game.sghhr.Md5;
import com.easou.game.sghhr.NetRequest;
import com.easou.game.sghhr.NetRequest.DownloadCallback;
import com.easou.game.sghhr.ResUpdateUtil;
import com.easou.game.sghhr.UpdateInfoBean;
import com.easou.game.sghhr.log.LogActEnum;
import com.easou.game.sghhr.log.LogUtil;

public class UpdateRes extends BaseStartTask {
	boolean temp = true;

	public UpdateRes(SplashBaseActivity slpashActivity) {
		super(slpashActivity);
	}

	@Override
	public void runTask(HashMap<String, Object> para) {
		// Log.d("StartWorkFlow", this.getClass().getName());
		LogUtil.sendLogInfo(
				LogActEnum.CHECKSTATICDATA
						.getAct(),
				mSplashActivity);
		if (para.containsKey("IS_NEED_UPDATE_RES")) {
			UpdateInfoBean updateInfo = (UpdateInfoBean) para
					.get("UPDATE_INFO");
			String updateUrl = updateInfo.getResUrl();
			if (!TextUtils.isEmpty(updateUrl)) {
				checkGameResToStartGame(updateUrl, para);
			} else {
				onFinish(null);
			}
		} else {
			onFinish(null);
		}

	}

	// 检测游戏资源更新，并启动游戏
	private void checkGameResToStartGame(final String updateUrl,
			final HashMap<String, Object> para) {
		if (temp) {
			temp = false;
			onProgressMsg("正在更新游戏资源...");
		}
		// 开始下载
		String fileSavePath = new File(mSplashActivity.getCacheDir(),
				Md5.encode(updateUrl)).getAbsolutePath();// 下载后的文件保存路径
		NetRequest.download(mSplashActivity, updateUrl, fileSavePath,
				new DownloadCallback() {

					@Override
					public void onProgress(final long current,
							final long fileTotalSize) {
						DecimalFormat df = new DecimalFormat("#.00");
						UpdateRes.super
								.onProgress((int) (current * 100 / fileTotalSize));
						if (fileTotalSize > 1024 * 1024 * 10) {
							UpdateRes.super.onProgressMsg("正在更新资源... "
									+ "("
									+ (df.format((double) current / 1024 / 1024))
									+ "M/"
									+ (df.format((double) fileTotalSize / 1024 / 1024))
									+ "M)");
						} else {
							UpdateRes.super.onProgressMsg("正在更新资源... " + "("
									+ (current / 1024) + "K/"
									+ (fileTotalSize / 1024) + "K)");
						}
					}

					@Override
					public void onFinish(String filePath) {
						// 安装更新资源包
						File updateFile = new File(filePath);
						ResUpdateUtil.installUpdate(mSplashActivity,
								updateFile,
								new ResUpdateUtil.OnResInstallProgress() {
									public void onProgressMessage(String msg) {
										UpdateRes.super.onProgressMsg(msg);
									}

									@Override
									public void onError(int code, String msg) {
										mSplashActivity
												.showAlertDialogForError(
														"亲，更新失败，请重进游戏再试！", 0,
														getTag(), para);
									}

									@Override
									public void onFinish(Object o) {
										UpdateRes.super.onFinish(null);
									}

									@Override
									public void onProgress(int n) {
										UpdateRes.super.onProgress(n);

									}

								});

					}

					@Override
					public void onError(int code, String msg, Exception e) {
						mSplashActivity.showAlertDialogForError(
								"亲，更新资源下载失败，请重试！", 0, getTag(), para);

					}
				});

	}

}