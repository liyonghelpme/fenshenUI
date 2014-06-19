package com.easou.game.sghhr.splash;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;

import com.easou.game.sghhr.ApkUpdateUtil;
import com.easou.game.sghhr.CommonConfig;
import com.easou.game.sghhr.NetRequest;
import com.easou.game.sghhr.NetRequest.DownloadCallback;
import com.easou.game.sghhr.UpdateInfoBean;
import com.easou.game.sghhr.Utils;
import com.easou.game.sghhr.log.LogActEnum;
import com.easou.game.sghhr.log.LogUtil;
import com.easou.game.sghhr.secretarydialog.SecretaryDialog;
import com.easou.game.sghhr.secretarydialog.SecretaryDialog.DialogCallBack;

public class UpdateApk extends BaseStartTask {

	public UpdateApk(SplashBaseActivity slpashActivity) {
		super(slpashActivity);
	}

	@Override
	public void runTask(HashMap<String, Object> para) {
		// Log.d("StartWorkFlow", this.getClass().getName());
		LogUtil.sendLogInfo(LogActEnum.CHECKVERSION.getAct(), mSplashActivity);
		if (para.containsKey("IS_NEED_UPDATE_APK")) {
			UpdateInfoBean updateInfo = (UpdateInfoBean) para
					.get("UPDATE_INFO");
			showUpdateDialog(updateInfo, para);
		} else {
			onFinish(para);
		}
	}

	private void showUpdateDialog(final UpdateInfoBean updateInfo,
			final HashMap<String, Object> para) {
		super.mSplashActivity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// final Dialog dialog = new
				// Dialog(UpdateApk.super.mSplashActivity,
				// R.style.MyDialog);
				// dialog.setContentView(R.layout.dialog);
				// TextView msg = (TextView) dialog.findViewById(R.id.show_msg);
				// msg.setText(updateInfo.getDescribe());
				// ImageButton mPositiveButton = (ImageButton) dialog
				// .findViewById(R.id.positive_btn);
				// mPositiveButton.setOnClickListener(new View.OnClickListener()
				// {
				// @Override
				// public void onClick(View v) {}
				//
				// });
				// ImageButton mNegativeButton = (ImageButton) dialog
				// .findViewById(R.id.cacel_btn);
				// mNegativeButton.setOnClickListener(new View.OnClickListener()
				// {
				//
				// @Override
				// public void onClick(View v) {
				// mSplashActivity.finish();
				// }
				//
				// });
				// dialog.show();
				SecretaryDialog.startSecretaryDialog(
						UpdateApk.super.mSplashActivity, "小秘书：",
						updateInfo.getDescribe(),
						SecretaryDialog.downSelectView, new DialogCallBack() {

							@Override
							public void restPressed() {
								// TODO Auto-generated method stub

							}

							@Override
							public void confirmPressed() {
								// TODO Auto-generated method stub
								new Thread() {
									public void run() {
										onProgressMsg("正在下载更新包，请稍后...");

										String[] urlArray = updateInfo
												.getApkUrl().split("/");
										String apkName = urlArray[urlArray.length - 1];

										String downloadApkFilePath = new File(
												CommonConfig.SdcardPath.UPDATE_APK_SAVEPATH,
												apkName).getAbsolutePath();
										String url = updateInfo.getApkUrl();
										NetRequest.download(mSplashActivity,
												url, downloadApkFilePath,
												new DownloadCallback() {

													@Override
													public void onProgress(
															final long current,
															final long fileTotalSize) {
														DecimalFormat df = new DecimalFormat(
																"#.00");

														UpdateApk.super
																.onProgress((int) (current * 100 / fileTotalSize));
														if (fileTotalSize > 1024 * 10) {
															UpdateApk.super
																	.onProgressMsg("正在更新游戏... "
																			+ "("
																			+ (df.format((double) current / 1024 / 1024))
																			+ "M/"
																			+ (df.format((double) fileTotalSize / 1024 / 1024))
																			+ "M)");
														} else {
															UpdateApk.super
																	.onProgressMsg("正在更新游戏... "
																			+ "("
																			+ (current / 1024)
																			+ "K/"
																			+ (fileTotalSize / 1024)
																			+ "K)");
														}
													}

													@Override
													public void onFinish(
															String filePath) {
														File apkFile = new File(
																filePath);
														if (apkFile != null
																&& Utils.isApkFileOk(
																		mSplashActivity,
																		apkFile)) {
															ApkUpdateUtil
																	.install(
																			mSplashActivity,
																			updateInfo,
																			CommonConfig.SdcardPath.UPDATE_APK_SAVEPATH);
														}
													}

													@Override
													public void onError(
															int code,
															String msg,
															Exception e) {
														mSplashActivity
																.showAlertDialogForError(
																		"亲，下载更新包失败，请重试哦！",
																		0,
																		getTag(),
																		para);

														// onError(1, msg, e);
													}

												});
									}
								}.start();

							}

							@Override
							public void cancalPressed() {
								// TODO Auto-generated method stub
								UpdateApk.super.mSplashActivity.finish();
							}
						});
			}
		});
	}

}
