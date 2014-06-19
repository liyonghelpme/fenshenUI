package com.easou.game.sghhr;

import java.util.UUID;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SpHelper {
	private static Context context;
	public static final String SHARED_PREFERENCES_NAME = "EASOU_PREFERENCES";
	public static final String APK_VERSION = "APK_VERSION";
	public static final String USER_UUID = "USER_UUID";

	/** 设置Context
	 *  */
	public static void setContext(Context _context){
		context = _context;
	}
	
	/**
	 * 判断是否是升级安装后的第一次启动
	 * 
	 * @return
	 */
	public static boolean isUpdated() {

		SharedPreferences sp = context.getSharedPreferences(
				SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

		int perVersion = sp.getInt(APK_VERSION, -1);
		//判断是否是覆盖安装的依据修改为：比较apk中的AndroidManifest.xml的中版本号
		int curVersion = Utils.getApkVersionCode(context);
		Editor edit = sp.edit();
		edit.putInt(APK_VERSION, curVersion);
		edit.commit();
		
		if (perVersion == -1) {
			return false;
		}else{
			//只要版本号不一致，都算是覆盖安装
			return curVersion != perVersion;
		}
		
	}

	public static String getUserUUID() {
		SharedPreferences sp = context.getSharedPreferences(
				SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

		String uuidStr = sp.getString(USER_UUID, null);

		if (uuidStr == null) {
			//优先使用imei,如果没有，则使用uuid
			uuidStr = Utils.getImei(context);
			if (uuidStr == null) {
				uuidStr = UUID.randomUUID().toString();
			}
			Editor edit = sp.edit();
			edit.putString(USER_UUID, uuidStr);
			edit.commit();
		}

		return uuidStr;
	}
}
