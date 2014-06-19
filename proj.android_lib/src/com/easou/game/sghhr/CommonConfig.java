package com.easou.game.sghhr;

import android.os.Environment;


public class CommonConfig {
	public static class SdcardPath{
		/** Apk根目录 */
		public static final String SAVE_ROOTPATH = Environment
				.getExternalStorageDirectory() + "/Ldsg";

		/** 应用更新目录 */
		public static final String UPDATE_APK_SAVEPATH = SAVE_ROOTPATH + "/update";
		
		
	}
	/**系统数据接收接口地址*/
	public static final String STAT_URL = "http://log.ldsg.lodogame.com/LogAccess/";
	
	/** 外部定义versionCode用于更新，区别于Manifest中versioncode */
	public static int versionCode = 6;
	
	/** 要保存的用户登录信息的临时文件名 */
	public static final String LOGIN_INFO_TEMP_FILE = "loginInfoTempFile";
	
	/** 要保存的埋点日志信息的临时文件名 */
	public static final String LOG_INFO_TEMP_FILE = "logInfoTempFile";
	/** 要保存的最新公告的临时文件名 */
	public static final String LATEST_NEWS_TEMP_FILE = "latestnews.txt";
	
}
