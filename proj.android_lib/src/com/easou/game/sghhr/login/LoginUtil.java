package com.easou.game.sghhr.login;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Build;

import com.easou.game.sghhr.CommonConfig;
import com.easou.game.sghhr.log.LogData;

public class LoginUtil {

	public static void saveLoginInfoToFile(Context context, String tk,
			String serverId, String uid, String pid, String port, String puid,
			String esqn,String serverName,LogData logData) {
		try {

			File loginInfoTempFile = new File(context.getFilesDir(),
					CommonConfig.LOGIN_INFO_TEMP_FILE);

			if (!loginInfoTempFile.exists()) {
				loginInfoTempFile.createNewFile();
			}

			FileOutputStream outputStream = new FileOutputStream(
					loginInfoTempFile);
			JSONObject object = new JSONObject();
			try {
				object.put("act", logData.getAct());
				object.put("gt", logData.getGt());
				object.put("gver", logData.getGver());
				object.put("idfa", logData.getIdfa());
				object.put("imei", logData.getImei());
				object.put("mac", logData.getMac());
				object.put("pid", logData.getPid());
				object.put("qn", logData.getQn());
				object.put("sessionId", logData.getSid());
				object.put("ua", logData.getUa());
				object.put("tk", tk);
				object.put("sid", serverId);
				object.put("uid", uid);
				object.put("pid", pid);
				object.put("port", port);
				object.put("puid", puid);
				object.put("phone_model", Build.MODEL);
				object.put("serverName",serverName);
				if (!"0".equals(logData.getQn())) {
					object.put("esqn", logData.getQn());
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			outputStream.write(object.toString().getBytes());
			outputStream.flush();
			outputStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void saveLogDataToFile(Context context,LogData logData) {
		try {
			if(context == null
					|| logData == null){
				return;
			}
			File loginInfoTempFile = new File(context.getFilesDir(),
					CommonConfig.LOG_INFO_TEMP_FILE);

			if (!loginInfoTempFile.exists()) {
				loginInfoTempFile.createNewFile();
			}
			FileOutputStream outputStream = new FileOutputStream(
					loginInfoTempFile);
			JSONObject object = new JSONObject();
			try {
				object.put("act", logData.getAct());
				object.put("gt", logData.getGt());
				object.put("gver", logData.getGver());
				object.put("idfa", logData.getIdfa());
				object.put("imei", logData.getImei());
				object.put("mac", logData.getMac());
				object.put("pid", logData.getPid());
				object.put("qn", logData.getQn());
				object.put("sid", logData.getSid());
				object.put("ua", logData.getUa());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			outputStream.write(object.toString().getBytes());
			outputStream.flush();
			outputStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void saveLatestNewsToFile(Context context, String title,
			String detail) {
		try {

			File latestnewsTempFile = new File(context.getFilesDir(),
					CommonConfig.LATEST_NEWS_TEMP_FILE);

			if (!latestnewsTempFile.exists()) {
				latestnewsTempFile.createNewFile();
			}

			String latestNews_json = "[{\"newsid\":1,\"title\":\"" + title
					+ "\",\"detail\":\"" + detail + "\"}]";

			FileOutputStream outputStream = new FileOutputStream(
					latestnewsTempFile);

			outputStream.write(latestNews_json.getBytes());
			outputStream.flush();
			outputStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
