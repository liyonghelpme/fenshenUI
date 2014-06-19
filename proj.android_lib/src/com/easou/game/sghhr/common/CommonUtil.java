package com.easou.game.sghhr.common;

import java.io.File;

import com.easou.game.sghhr.ServerInfo;

import android.content.Context;

public class CommonUtil {
	
	/** 当前服务器对象*/
	private static ServerInfo currentServerInfo = null;	
	
	/**
	 * 保存宜搜登录信息
	 * @param userInfo
	 */
	public static void saveEasouLoginInfo(EasouLoginInfo userInfo){
		String jsonStr = "";
		if(userInfo != null)
			jsonStr = userInfo.toJsonString();
		FileHelper.writeFile(getEasouLoginInfoCacheFile(), jsonStr);
	}
	
	/**
	 * 保存宜搜登录信息
	 * @return
	 */
	public static EasouLoginInfo getEasouLoginInfo(){
		String jsonStr = FileHelper.readFile(getEasouLoginInfoCacheFile());
		EasouLoginInfo info = EasouLoginInfo.parseJson(jsonStr);
		return info;
	}
	
	/**
	 * 获取EasouLoginInfo的缓存文件
	 * @return
	 */
	public static File getEasouLoginInfoCacheFile(){
		Context context = BaseApp.getContext();
		return new File(context.getFilesDir(),"easouLoginInfo");
	}
	
	/**
	 * 保存当前服务器对象
	 * @param serverInfo
	 */
	public static void saveCurrentServerInfo(ServerInfo serverInfo){
		currentServerInfo = serverInfo;
	}
	
	/**
	 * 获取当前服务器对象
	 * （进入LoginCenterActivity后该对象即被初始化）
	 * @return
	 */
	public static ServerInfo getCurrentServerInfo(){
		return currentServerInfo;
	}
}
