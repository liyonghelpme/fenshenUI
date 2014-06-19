package com.easou.game.sghhr;

import java.io.File;
import java.util.ArrayList;

import org.json.JSONObject;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;

/**
 * 工具类
 * */
public class MobileFunction {
	public static ArrayList<ServerInfo> playList = new ArrayList<ServerInfo>();
	

	/** 获取手机imei */
	public static String getIMEI(Context context) {
		try {
			TelephonyManager tm = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			return tm.getDeviceId();
		} catch (Exception e) {
			return "";
		}
	}

	/** 获取手机机型 */
	public static String getModel() {
		return Build.MODEL;
	}

	/**
	 * 检测SD卡是否可用
	 * 
	 * @return
	 */
	public static boolean isExternalStorageAvailable() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED))
			return true;
		return false;
	}

	/**
	 * 手机内存的可用空间大小
	 * 
	 * @return
	 */
	public static long getAvailableInternalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		return availableBlocks * blockSize;
	}

	/**
	 * 手机内存的总空间大小
	 * 
	 * @return
	 */
	public static long getTotalInternalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long totalBlocks = stat.getBlockCount();
		return totalBlocks * blockSize;
	}

	/**
	 * sdcard的可用空间大小
	 * 
	 * @return
	 */
	public static long getAvailableExternalMemorySize() {
		if (isExternalStorageAvailable()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long availableBlocks = stat.getAvailableBlocks();
			return availableBlocks * blockSize;
		}
		return 0;
	}

	/**
	 * 获取sdcard的总空间大小
	 * 
	 * @return
	 */
	public static long getTotalExternalMemorySize() {
		if (isExternalStorageAvailable()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long totalBlocks = stat.getBlockCount();
			return totalBlocks * blockSize;
		}
		return 0;
	}

	/**
	 * 判断当前是否有网络
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm == null)
			return false;
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		if (networkInfo == null || !networkInfo.isAvailable()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断当前网络环境是否为wifi
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isWifiConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (ni.getState() == NetworkInfo.State.CONNECTED) {
			return true;
		}
		return false;
	}

	/**
	 * 判断应用是否在前台
	 */
	public static boolean isAppInfront(Context context, String packageName) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		String frontPackageName = am.getRunningTasks(1).get(0).topActivity
				.getPackageName();
		if (frontPackageName.equals(packageName)) {
			return true;
		}
		return false;
	}

	/**
	 * 获取文件的总长度，断点续传时，总长度包含在Content-Ranges中，如Content-Ranges: bytes 0-499/1234
	 * 
	 * @return
	 */
	public static long getFileTotalSize(String contentRanges,
			String contentLength) {
		if (contentRanges != null) {
			int pos = contentRanges.lastIndexOf("/");
			if (pos > 0) {
				return Long.parseLong(contentRanges.substring(pos + 1));
			}
		} else if (contentLength != null) {
			return Long.parseLong(contentLength);
		}
		return 0;
	}

	/**
	 * 获取升级信息
	 * 
	 * @param bts
	 * @return
	 */
	public static UpdateInfoBean buildJsonUploadInf(String result) {
		UpdateInfoBean bean = new UpdateInfoBean();
		try {
			JSONObject js = new JSONObject(result);
			JSONObject so = js.getJSONObject("dt");
				String version = so.getString("ver");
				String apkUrl = so.getString("apkUrl");
				String resUrl = so.getString("resUrl");
				String desc = so.getString("desc");
				bean.setApkUrl(apkUrl);
				bean.setVersion(version);
				bean.setResUrl(resUrl);
				bean.setDescribe(desc);
			
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return bean;
	}

}
