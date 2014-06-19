package com.easou.game.sghhr.common;

import org.json.JSONException;
import org.json.JSONObject;

import com.easou.game.sghhr.login.LoginUtil;

/**
 * 宜搜用户实体类
 * 
 * @author ted
 * 
 */
public class EasouLoginInfo {
	private String tk = ""; // 宜搜用户中心返回的token
	private String uid = ""; // 宜搜用户中心返回的uid
	private String puid = ""; // 第三方返回的用户ID
	private String extra1 = "";// 备用字段1
	private String extra2 = "";// 备用字段2

	public String getPuid() {
		return puid;
	}

	public void setPuid(String puid) {
		this.puid = puid;
	}

	public String getTk() {
		return tk;
	}

	public void setTk(String tk) {
		this.tk = tk;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getExtra1() {
		return extra1;
	}

	public void setExtra1(String extra1) {
		this.extra1 = extra1;
	}

	public String getExtra2() {
		return extra2;
	}

	public void setExtra2(String extra2) {
		this.extra2 = extra2;
	}


	public static EasouLoginInfo parseJson(String json) {
		EasouLoginInfo bean = new EasouLoginInfo();
		try {
			JSONObject jsonObj = new JSONObject(json);
			if (!jsonObj.has("tk"))
				return bean;
			if (!jsonObj.has("uid"))
				return bean;
			if (!jsonObj.has("puid"))
				return bean;
			String tk = jsonObj.getString("tk");
			String uid = jsonObj.getString("uid");
			String puid = jsonObj.getString("puid");
			bean.setTk(tk);
			bean.setUid(uid);
			bean.setPuid(puid);
			
			//保存备用字段信息
			if(jsonObj.has("extra1")){
				String extra1 = jsonObj.getString("extra1");
				bean.setExtra1(extra1);
			}
			if(jsonObj.has("extra2")){
				String extra2 = jsonObj.getString("extra2");
				bean.setExtra2(extra2);
			}
			
			// 保存最新公告信息（notice 为公告详情， title为公告标题）
			if (jsonObj.has("notice") && jsonObj.has("title")
					&& jsonObj.getString("notice").trim().length() > 0
					&& jsonObj.getString("title").trim().length() > 0) {
				LoginUtil.saveLatestNewsToFile(BaseApp.getContext(),
								jsonObj.getString("title"),
								jsonObj.getString("notice"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return bean;
	}

	public String toJsonString() {
		JSONObject obj = new JSONObject();
		try {
			obj.put("tk", tk);
			obj.put("uid", uid);
			obj.put("puid", puid);
			obj.put("extra1", extra1);
			obj.put("extra2", extra2);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj.toString();
	}
}
