package com.easou.game.sghhr.splash;

import java.text.DecimalFormat;

import com.easou.game.sghhr.R;
import com.easou.game.sghhr.common.BaseSplashActivity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * 下载通知栏提示
 * 
 * @author yuchun
 * 
 */
public class ProgressNotifition {
	private static ProgressNotifition progressNotifition;
	private Notification notification;
	private NotificationManager notificationManager;
	private long millisecond;
	private long lastSize;
	private PendingIntent contentIntent;
	boolean isFinish = false;

	/**
	 * 发送通知栏消息
	 * 
	 * @param context
	 *            activity
	 * @param totalSize
	 *            总大小
	 * @param currentSize
	 *            当前大小
	 * @param length
	 *            读取长度
	 */
	public static synchronized void sendNotifitionMessage(Activity activity,
			long totalSize, long currentSize, String downloadFilePath) {
		if (progressNotifition == null) {
			progressNotifition = new ProgressNotifition();
			progressNotifition.isFinish = false;
			// 初始化
			progressNotifition.init(activity, downloadFilePath);
		}
		progressNotifition.updateNotifition(totalSize, currentSize, activity,
				downloadFilePath);
	}

	private void init(Activity activity, String downloadFilePath) {
		lastSize = 0;
		millisecond = 0;
		// 更换背景图片
		BaseSplashActivity.setResource(new int[] { R.drawable.background1,
				R.drawable.background2, R.drawable.background3,
				R.drawable.background4 });

		notificationManager = (NotificationManager) activity
				.getSystemService(Context.NOTIFICATION_SERVICE);
		// 定义通知栏展现的内容信息
		notification = new Notification();
		notification.icon = com.easou.game.sghhr.R.drawable.icon_notifition;
		notification.when = System.currentTimeMillis();
		notification.tickerText = "三国合伙人下载通知";
		Intent notificationIntent = new Intent();

		notificationIntent.setClass(activity, activity.getClass());

		contentIntent = PendingIntent.getActivity(activity, 0,
				notificationIntent, 0);
		notification.contentIntent = contentIntent;
		notification.contentView = new RemoteViews(activity.getPackageName(),
				R.layout.progress_item);
		notification.contentView.setImageViewResource(R.id.image_icon,
				notification.icon);
		String[] date = downloadFilePath.split("/");
		String name = date[date.length - 1];
		if (!name.endsWith(".apk")) {
			name = "三国合伙人数据更新";
		}
		notification.contentView.setTextViewText(R.id.text_name, name);
		notification.flags = Notification.FLAG_AUTO_CANCEL;
	}

	/**
	 * 更新通知栏
	 * 
	 * @param totalSize
	 *            总大小
	 * @param currentSize
	 *            当前大小
	 * @param length
	 *            读取长度
	 */
	private void updateNotifition(long totalSize, long currentSize,
			Activity activity, String downloadFilePath) {
		long currentMillis = System.currentTimeMillis();
		long temp = currentMillis - millisecond;
		if (temp < 1000 && totalSize != currentSize) {
			return;
		}
		long speed = 0;
		String unit = "b/s";
		millisecond = currentMillis;
		speed = (currentSize - lastSize) * 1000 / temp;
		lastSize = currentSize;
		if (speed > 1024) {
			speed = speed / 1024;
			unit = "kb/s";
		}
		int len = (int) (currentSize * 100 / totalSize);
		if (len == 100) {
			// 更换背景图片
			BaseSplashActivity
					.setResource(new int[] { R.drawable.background0 });
			
			notification.contentView.setTextViewText(R.id.text_speed, "下载完成 ");
		} else {
			notification.contentView.setTextViewText(R.id.text_speed, "正在下载 "
					+ speed + " " + unit);
		}
		notification.contentView.setTextViewText(R.id.text_proportion, len
				+ " %");
		notification.contentView.setTextViewText(
				R.id.text_totalsize,
				new DecimalFormat("###.00")
						.format((double) totalSize / 1024 / 1024) + " MB");
		notification.contentView.setProgressBar(R.id.progressBar1, 100, len,
				false);
		notificationManager.notify(1, notification);
		if (len == 100) {
			Log.d("下载提示", "下载完成");
			notificationManager.cancelAll();
		}

	}
}
