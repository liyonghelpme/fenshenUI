package com.easou.game.sghhr.common;

import java.util.List;

import com.easou.game.sghhr.ServerInfo;

/**
 * 配置信息
 * @author ted
 */
public interface IConfig {
	/** 获取游戏服务器地址*/
	public String getApiDomain();
	/** 获取合作商PartenerId*/
	public String getPartenerId();
	/** 是否有闪屏*/
	public boolean isHaveFlashScreen();
	/** 获取闪屏图片Id*/
	public int getFlashScreenId();
	/** 是否有账号管理功能*/
	public boolean isHaveAccountManager();
	/** 篡改选服列表，开发测试时使用*/
	public List<ServerInfo> reWriteServerList();
}
