package com.easou.game.sghhr.splash;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.easou.game.sghhr.CustomConfig;
import com.easou.game.sghhr.Md5;
import com.easou.game.sghhr.NetRequest;
import com.easou.game.sghhr.NetRequest.DownloadCallback;
import com.easou.game.sghhr.NetResult;
import com.easou.game.sghhr.ResUpdateUtil;

public class UpdateStaticFiles extends BaseStartTask {

	public UpdateStaticFiles(SplashBaseActivity slpashActivity) {
		super(slpashActivity);
	}

	@Override
	public void runTask(HashMap<String, Object> para) {
		onProgressMsg("正在检测数值配置更新");
		// 获取更新信息
		NetResult netRequest = NetRequest.checkStaticFilesRequest(
				super.mSplashActivity, CustomConfig.getApiDomain());

		if (netRequest != null && netRequest.getRc() == 1000) {// 获取升级信息成功，则进一步解析，是否要进行升级
			String data = netRequest.getDt();
			if (null != data) {
				JSONObject js = null;
				try {
					js = new JSONObject(data);
					if (null != js && js.has("dt")) {
						String dt = js.getString("dt");
						JSONObject jsUrl = new JSONObject(dt);
						if (jsUrl != null && jsUrl.has("url")) {
							String url = jsUrl.getString("url");
							if (url != null && url.length() > 0) {
								downloadStaticFilesAndInstall(url, para);// 下载静态数值资源
							} else {
								onFinish(null);
							}
						} else {
							onFinish(null);
						}
					} else {
						onFinish(null);
					}
				} catch (JSONException e) {
					e.printStackTrace();
					super.mSplashActivity.showAlertDialogForError("静态数值表更新失败",
							0, getTag(), para);
					onError(1, "errorMessagefinal", null);
				}
			} else {
				super.mSplashActivity.showAlertDialogForError("静态数值表更新失败", 0,
						getTag(), para);
				onError(1, "errorMessagefinal", null);
			}

		} else {
			String errorMsg = "网络连接错误";
			if (netRequest != null) {
				errorMsg = netRequest.getMsg();
			}
			final String errorMessagefinal = errorMsg;
			super.mSplashActivity.showAlertDialogForError(errorMessagefinal, 0,
					getTag(), para);
			onError(1, "errorMessagefinal", null);
		}
	}

	/**
	 * 下载静态数值配置，并解压覆盖
	 * 
	 * @param url
	 */
	private void downloadStaticFilesAndInstall(String url,
			final HashMap<String, Object> para) {
		onProgressMsg("正在更新数值配置...");
		// 开始下载
		String fileSavePath = new File(mSplashActivity.getCacheDir(),
				Md5.encode(url)).getAbsolutePath();// 下载后的文件保存路径

		NetRequest.download(mSplashActivity, url, fileSavePath,
				new DownloadCallback() {

					@Override
					public void onProgress(final long current,
							final long fileTotalSize) {
						DecimalFormat df = new DecimalFormat("#.00");
						UpdateStaticFiles.super
								.onProgress((int) (current * 100 / fileTotalSize));
						if (fileTotalSize > 1024 * 1024 * 10) {
							UpdateStaticFiles.super.onProgressMsg("正在更新数值配置... "
									+ "("
									+ (df.format((double) current / 1024 / 1024))
									+ "M/"
									+ (df.format((double) fileTotalSize / 1024 / 1024))
									+ "M)");
						} else {
							UpdateStaticFiles.super
									.onProgressMsg("正在更新数值配置... " + "("
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
										UpdateStaticFiles.super
												.onProgressMsg(msg);
									}

									@Override
									public void onError(int code, String msg) {
										mSplashActivity
												.showAlertDialogForError(
														"更新失败，请重进游戏再试！", 0,
														getTag(), para);
									}

									@Override
									public void onFinish(Object o) {
										UpdateStaticFiles.super.onFinish(null);
									}

									@Override
									public void onProgress(int n) {
										UpdateStaticFiles.super.onProgress(n);

									}

								});

					}

					@Override
					public void onError(int code, String msg, Exception e) {
						mSplashActivity.showAlertDialogForError(
								"更新数值配置下载失败，请重试！", 0, getTag(), para);

					}
				});

	}
}