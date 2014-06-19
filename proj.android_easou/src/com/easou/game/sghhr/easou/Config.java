package com.easou.game.sghhr.easou;

import java.util.List;

import com.easou.game.sghhr.ServerInfo;
import com.easou.game.sghhr.common.IConfig;

public class Config implements IConfig {
	public static final boolean isNeedActivation = false;// 是否开放激活码功能（封测期间使用）

	/**
	 * 闪屏页配置 TODO:如果存在闪屏页flashScreenId必须配置
	 * */
	private final boolean isHaveFlashScreen = false;
	private final int flashScreenId = com.easou.game.sghhr.easou.R.drawable.ic_launcher;// 默认闪屏页面图像

	/** 是否有账号管理功能 */
	private static final boolean isHaveAccountManager = true;

	/**
	 * 是否打开论坛
	 */
	public static final boolean isOpenBBS = true;

	/**
	 * 此处为第三方分配的APPID、APPKey、等参数 TODO:需要开发人员修改为具体的哪个渠道（此数据由后台和商务提供）
	 * */
	private final String partnerId = "4001"; // 第三方渠道合作号
	private final String appId = ""; // 第三方分配的APP_ID
	private final String appKey = ""; // 第三方分配的 APP_KEY

	/**
	 * 获取服务器地址URL
	 * 
	 * @return
	 */
	@Override
	public String getApiDomain() {
		// TODO:此处在打包前必须确认打哪个服务器的包
		 return "http://api.fengshen.lodogame.com:9088";//开发服api3.ldsg.lodogame.com
		// return "http://api2.ldsg.lodogame.com:8088";// 测试服
		//return "http://wapi.ldsg.lodogame.com:8088";// 线上服
	}

	/**
	 * 篡改选服列表（开发测试时使用） 如果不需要篡改请返回null
	 * 
	 * @return
	 * */
	@Override
	public List<ServerInfo> reWriteServerList() {
		return null;
	}

	@Override
	public String getPartenerId() {
		return partnerId;
	}

	@Override
	public boolean isHaveFlashScreen() {
		return isHaveFlashScreen;
	}

	@Override
	public int getFlashScreenId() {
		return flashScreenId;
	}

	public String getAppId() {
		return appId;
	}

	public String getAppKey() {
		return appKey;
	}

	public boolean isHaveAccountManager() {
		return isHaveAccountManager;
	}
}