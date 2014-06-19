package com.easou.game.sghhr.common;

import java.util.List;

import android.app.Application;
import android.content.SharedPreferences;

import com.easou.game.sghhr.ServerInfo;

public class BaseApp extends Application {
	protected static BaseApp instance;
	private static ServerInfo currentServerInfo = null;

	public static BaseApp getContext() {
		return instance;
	}
	
	private boolean isOrderToExit = false;

	public static void exit() {
		instance.isOrderToExit = true;
	}

	public static boolean isOrderToExit() {
		return instance.isOrderToExit;
	}
	
	public static void setOrderToExit(boolean isOrderToExit){
		instance.isOrderToExit = isOrderToExit;
	}
	

	//获取SharedPreferences
	public static SharedPreferences getServerPerferences(){
		SharedPreferences preferences = instance.getSharedPreferences("ServerInfo", MODE_PRIVATE);
		return preferences;
	}
	
	/**
	 * 保存服务器列表Id
	 * @param serverList
	 * @param serverIndex
	 */
	public static void saveServerId(String serverId){
	//保存服务器列表信息
		SharedPreferences perference = getServerPerferences();
		perference.edit().putString("ServerId", serverId).commit();
	}
	
	/**
	 * 获取保存的服务器对象
	 * @param serverList
	 * @return
	 */
	public static ServerInfo getServerInfo(List<ServerInfo> serverList){
		String recordServerId = getServerPerferences().getString("ServerId", "请选择服务器");
		ServerInfo serverInfo = null;
		for(ServerInfo info : serverList){
			if(info.getServer_id().equals(recordServerId)){
				serverInfo = info;
				break;
			}
		}
		if(serverInfo == null)
			serverInfo = serverList.get(0);//默认选择第一个
		return serverInfo;
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
	 * @return
	 */
	public static ServerInfo getCurrentServerInfo(){
		return currentServerInfo;
	}
}
