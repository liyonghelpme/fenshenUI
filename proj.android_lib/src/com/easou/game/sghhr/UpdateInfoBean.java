package com.easou.game.sghhr;

import java.io.Serializable;

/**
 * 升级
 * @author Erica
 *
 */
public class UpdateInfoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String version;	//版本号
	private String apkUrl;
	private String resUrl;
	private String describe;	//新版本描述

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}


	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getApkUrl() {
		return apkUrl;
	}

	public void setApkUrl(String apkUrl) {
		this.apkUrl = apkUrl;
	}

	public String getResUrl() {
		return resUrl;
	}

	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}
	
	
}
