package com.easou.game.sghhr;

import java.io.File;
import java.util.UUID;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

public class Utils {

	public static boolean isEmulator(Context _context) {
		TelephonyManager tm = (TelephonyManager) _context
				.getSystemService(Context.TELEPHONY_SERVICE);
		if (tm.getNetworkOperatorName().equals("T-Mobile")
				|| tm.getNetworkOperatorName().equals("Android")) {
			return true;
		}
		String android_id = Settings.Secure.getString(_context
				.getContentResolver(), Settings.Secure.ANDROID_ID);
		boolean emulator = TextUtils.isEmpty(android_id)
				|| "google_sdk".equals(Build.PRODUCT)
				|| "sdk".equals(Build.PRODUCT);
		return emulator;
	}

	/** 
	 * 网络请求URL拼装
	 * @note 拼装必须字段 imei version partnerId esqn
	 *  */
	public static String appendUrlWithCustomProperty(String url,Context _context) {
		if (url == null) {
			return null;
		}

		String small_version = ResUpdateUtil.getGameResVersion(_context);
		String big_version = ResUpdateUtil.getGameApkVersion(_context);
		String version = big_version + "." + small_version;
		if (url.indexOf("?") != -1) {// TODO 此处有潜在bug，暂时这样实现，能满足眼下需要
			return url + "&fr=" + Utils.getImei(_context) + "&mac="+Utils.getMacAddress(_context)+"&version=" + version+"&partnerId="+CustomConfig.getPartnerId()+"&qn="+CustomConfig.getQn();
		} else {
			return url + "?fr=" + Utils.getImei(_context) + "&mac="+Utils.getMacAddress(_context)+"&version=" + version+"&partnerId="+CustomConfig.getPartnerId()+"&qn="+CustomConfig.getQn();
		}
	}
	
	/**
	 * 获取设备mac地址
	 * @param _context
	 * @return
	 */
	public static String getMacAddress(Context _context){
		try {
			WifiManager wifi = (WifiManager)_context.getSystemService(Context.WIFI_SERVICE);
			WifiInfo info = wifi.getConnectionInfo();
			return info.getMacAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/** 获取GAMEAPK VERSIONCODE */
	public static int getGameApkVersionCode(Context context) {
		return CommonConfig.versionCode;
	}

	/** Manifest中versioncode */
	public static int getApkVersionCode(Context context) {
		try {
			// 获取packagemanager的实例
			PackageManager packageManager = context.getPackageManager();
			// getPackageName()是你当前类的包名，0代表是获取版本信息
			PackageInfo packInfo;

			packInfo = packageManager.getPackageInfo(context.getPackageName(),
					0);

			int versionCode = packInfo.versionCode;
			return versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * 获取VersionName
	 * */
	public static String getApkVersionName(Context _context) {
		try {
			// 获取packagemanager的实例
			PackageManager packageManager = _context
					.getPackageManager();
			// getPackageName()是你当前类的包名，0代表是获取版本信息
			PackageInfo packInfo;

			packInfo = packageManager.getPackageInfo(_context
					.getPackageName(), 0);

			String versionName = packInfo.versionName;
			return versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String getImei(Context _context) {
		TelephonyManager tm = (TelephonyManager)_context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String imei = tm.getDeviceId();
		if (imei != null) {
			Log.d("IMEI", imei);
		} else {
			Log.d("IMEI", "has no imei");
		}
		return imei;
	}

	/**
	 * 获取未安装的APK信息
	 * 
	 * @param context
	 * @param archiveFilePath
	 *            APK文件的路径
	 */
	public static PackageInfo getApkInfo(Context context, File apkFile) {
		if (apkFile == null || !apkFile.exists())
			return null;
		String archiveFilePath = apkFile.getAbsolutePath();
		PackageManager pm = context.getPackageManager();
		PackageInfo apkInfo = pm.getPackageArchiveInfo(archiveFilePath,
				PackageManager.GET_ACTIVITIES);
		return apkInfo;
	}

	/** 检测apk包是否完整 */
	public static boolean isApkFileOk(Context context, File apkFile) {

		if (getApkInfo(context, apkFile) != null) {
			return true;
		} else {
			apkFile.delete();
			return false;
		}
	}

	/**
	 * 获取APP唯一ID
	 * @return
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}
	
	/**
	 * 判断当前是否有网络
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkUseable(Context context) {

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
}
