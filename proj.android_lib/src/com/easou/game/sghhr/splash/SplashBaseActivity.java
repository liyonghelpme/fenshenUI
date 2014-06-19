package com.easou.game.sghhr.splash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;

import com.easou.game.sghhr.log.LogActEnum;
import com.easou.game.sghhr.log.LogUtil;
import com.easou.game.sghhr.secretarydialog.SecretaryDialog;
import com.easou.game.sghhr.secretarydialog.SecretaryDialog.DialogCallBack;

public abstract class SplashBaseActivity extends Activity implements
		ISplashActivity {
	List<IStartTask> workflow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Stat.onEntry(this);// 游戏启动时异步向服务器发送统计信息
		LogUtil.sendLogInfo(LogActEnum.START.getAct(), SplashBaseActivity.this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void runWorkflow() {
		workflow = new ArrayList<IStartTask>();
		// 0.从assert中拷贝游戏资源zip到手机内存
		workflow.add(new CopyGameResZip(SplashBaseActivity.this).setPower(20));
		// 1.解压脚本，初始游戏
		workflow.add(new UnZipGameRes(SplashBaseActivity.this).setPower(30));
		// 2.检查网络是否正常
		workflow.add(new CheckNetwork(SplashBaseActivity.this).setPower(5));
		// 3.检测激活码
		workflow.add(new CheckActivation(SplashBaseActivity.this).setPower(5));
		// 4.检查是否在模拟器中
		workflow.add(new CheckDevice(SplashBaseActivity.this).setPower(0));
		// 5.检测更新
		workflow.add(new CheckUpdate(SplashBaseActivity.this).setPower(5));
		// 6.下载并安装更新apk
		workflow.add(new UpdateApk(SplashBaseActivity.this).setPower(10));
		// 7.下载并安装资源包
		workflow.add(new UpdateRes(SplashBaseActivity.this).setPower(10));
		// 8.检测下载并覆盖静态数值配置
		// workflow.add(new
		// UpdateStaticFiles(SplashBaseActivity.this).setPower(10));
		// 9.跳转到登录页
		workflow.add(new GoToLogin(SplashBaseActivity.this).setPower(5));

		// 将启动流程串连
		int progress = 0;
		for (int i = 1; i < workflow.size(); i++) {
			IStartTask perTask = workflow.get(i - 1);
			IStartTask task = workflow.get(i);
			progress += perTask.getPower();
			task.setStartProgress(progress);
			perTask.setNextTask(task);
			perTask.setTag(i - 1);
		}

		// 启动工作流程
		if (workflow.size() > 0) {
			workflow.get(0).runTask(null);
		}

	}

	public void runNextTask(final IStartTask startTask,
			final HashMap<String, Object> para) {
		startTask.getNextTask().runTask(para);
	}

	public void showToast(final String msg) {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// Toast.makeText(SplashBaseActivity.this, msg,
				// Toast.LENGTH_SHORT)
				// .show();
				SecretaryDialog.startSecretaryDialog(SplashBaseActivity.this,
						"小秘书：", msg, SecretaryDialog.pointView,
						new DialogCallBack() {

							@Override
							public void restPressed() {
								// TODO Auto-generated method stub

							}

							@Override
							public void confirmPressed() {
								// TODO Auto-generated method stub
								finish();
							}

							@Override
							public void cancalPressed() {
								// TODO Auto-generated method stub
								finish();
							}
						});
			}
		});

	}

	public void showAlertDialogForError(final String errorMsgFinal,
			final int errorType, final int tag,
			final HashMap<String, Object> para) {
		this.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// String errorMsg = errorMsgFinal;
				// AlertDialog.Builder builder = new AlertDialog.Builder(
				// SplashBaseActivity.this);
				// builder.setTitle("提示");
				// if (errorMsg == null || errorMsg.length() == 0) {
				// errorMsg = "发生未知错误";
				// }
				// builder.setMessage(errorMsg);
				// builder.setCancelable(false);
				// builder.setNegativeButton("确定", new OnClickListener() {
				//
				// @Override
				// public void onClick(DialogInterface dialog, int which) {
				// dialog.dismiss();
				// finish();
				// }
				// });
				// builder.create();
				// builder.show();
				int witchView = -1;
				if (errorType == 0) {
					witchView = SecretaryDialog.pointView;
				} else {
					witchView = SecretaryDialog.downView;
				}
				SecretaryDialog.startSecretaryDialog(SplashBaseActivity.this,
						"小秘书 :", errorMsgFinal, witchView,
						new DialogCallBack() {

							@Override
							public void restPressed() {
								// TODO Auto-generated method stub
								finish();
							}

							@Override
							public void confirmPressed() {
								// TODO Auto-generated method stub
								new Thread() {
									public void run() {
										// 初始化启动流程
										workflow.get(tag).runTask(para);
									};
								}.start();

							}

							@Override
							public void cancalPressed() {
								// TODO Auto-generated method stub
								finish();
							}
						});
			}

		});

	}
}