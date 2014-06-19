package com.easou.game.sghhr;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;


public class ApkUpdateUtil {

	/**
	 * 引导安装
	 * 
	 */
	public static void install(Activity activity, UpdateInfoBean bean,String path) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setDataAndType(
				Uri.parse("file://" + path
						+ "/Ldsg_" + bean.getVersion() + ".apk"),
				"application/vnd.android.package-archive");
		activity.startActivity(intent);
		activity.finish();
	}

}
