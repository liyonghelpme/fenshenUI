package com.easou.game.sghhr.common;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;

import com.easou.game.sghhr.CustomProgressDialog;

/**
 * 过渡动画
 * 
 * @author ted
 * 
 */
public class ProgressLoading {

	private ProgressLoading() {
	}

	private static CustomProgressDialog dialog;
	private static Activity mActivity = null;
	private static String msg = "正在加载...";

	/** 计时器，到达最大等待时间后关闭对话框 */
	private static Timer timer;
	private static TimerTask timerTask;
	private static int DEFAULT_TIME = 0;

	/**
	 * 初始化
	 * 
	 * @param context
	 */
	private static void init(Activity activity) {
		if (dialog == null) {
			dialog = CustomProgressDialog.createDialog(activity);
			dialog.setMessage(msg);
		} else {
			dialog.dismiss();
			dialog = null;
		}

		if (timer == null)
			timer = new Timer(false);
		else {
			timer.cancel();
			timer = null;
		}

		if (timerTask == null) {
			timerTask = new TimerTask() {
				@Override
				public void run() {
					if (DEFAULT_TIME <= 0)
						endProgress();
					else
						DEFAULT_TIME--;
				}
			};
		} else {
			timerTask.cancel();
			timerTask = null;
		}
	}

	/**
	 * 加载过渡动画，默认时长为30秒
	 */
	public static void startProgress(Activity activity, String msgStr) {
		endProgress();
		DEFAULT_TIME = 3000;// 默认为30秒
		mActivity = activity;
		msg = msgStr;
		init(activity);
		mActivity.runOnUiThread(startLoadingAction);
		timer.schedule(timerTask, 1000, 1000);
	}

	/**
	 * 加载过渡动画，最多运行milSeconds时长
	 * 
	 * @param milSeconds
	 *            （毫秒）
	 */
	public static void startProgress(Activity activity, String msgStr,
			int milSeconds) {
		endProgress();
		DEFAULT_TIME = milSeconds;
		mActivity = activity;
		msg = msgStr;
		init(activity);
		mActivity.runOnUiThread(startLoadingAction);
		timer.schedule(timerTask, 1000, 1000);
	}

	/**
	 * 停止Loading动画
	 */
	public static void endProgress() {
		if (timer != null) {
			timer.cancel();
			timer = null;
		}

		if (timerTask != null) {
			timerTask.cancel();
			timerTask = null;
		}

		if (dialog != null)
			mActivity.runOnUiThread(stopLoadingAction);
		DEFAULT_TIME = 0;
	}

	/**
	 * 启动Loading动画Runnable
	 */
	private static Runnable startLoadingAction = new Runnable() {
		public void run() {
			if (dialog != null) {
				dialog.show();
			}
		}
	};

	/**
	 * 停止Loading动画Runnable
	 */
	private static Runnable stopLoadingAction = new Runnable() {
		public void run() {
			if (dialog != null) {
				dialog.dismiss();
				dialog = null;
			}
		}
	};

}
