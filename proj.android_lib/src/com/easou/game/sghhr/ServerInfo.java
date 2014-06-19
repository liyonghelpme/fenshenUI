package com.easou.game.sghhr;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/** 服务器对象 */
public class ServerInfo{
	/** 服务器ID */
	public String server_id;
	/** 服务器名称 */
	private String server_name;
	/** 服务器端口号*/
	private int server_port;
	/** 服务器状态 服务器状态（0：未开启，1：流畅，2：火爆，3：最新）*/
	private int server_status;
	
	public String getServer_id() {
		return server_id;
	}
	public void setServer_id(String server_id) {
		this.server_id = server_id;
	}
	public String getServer_name() {
		return server_name;
	}
	public void setServer_name(String server_name) {
		this.server_name = server_name;
	}
	public int getServer_status() {
		return server_status;
	}
	public void setServer_status(int server_status) {
		this.server_status = server_status;
	}
	public int getServer_port() {
		return server_port;
	}
	public void setServer_port(int server_port) {
		this.server_port = server_port;
	}
	
	
	/**
	 * 将Json字符串转化为ServerInfo列表对象
	 * @param json
	 * @return
	 */
	public static List<ServerInfo> parseServerList(String json){
		ArrayList<ServerInfo> serverList = new ArrayList<ServerInfo>();
		try {
			JSONObject js = new JSONObject(
					json);
			
			JSONArray so = js.getJSONArray("sl");
			for (int i = 0; i < so.length(); i++) {
				JSONObject serveritem = so
						.getJSONObject(i);
				ServerInfo item = new ServerInfo();
				item.setServer_id(serveritem
						.getString("serverId"));
				item.setServer_name(serveritem
						.getString("serverName"));
				item.setServer_status(serveritem
						.getInt("status"));
				item.setServer_port(serveritem
						.getInt("serverPort"));
				serverList.add(item);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return serverList;
	}
	
	
	
}