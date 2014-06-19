package com.easou.game.sghhr.common;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 乐斗三国订单信息
 * @author ted
 *
 */
public class LdsgPayBean {
	
	private String tradeId;		//订单ID
	private String tradeName;	//商品名称
	private String tradeDesc;	//描述
	private String playerId;	//合作商用户ID（玩家ID）
	private float reqFee;		//订单金额
	private String notifyUrl;	//支付结果回调接口
	private String exectInfo;	//额外信息
	/**在现有字段不够用时启用备用字段**/
	private String ext1;  		//备用字段1
	private String ext2;  		//备用字段2
	
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public String getTradeDesc() {
		return tradeDesc;
	}
	public void setTradeDesc(String tradeDesc) {
		this.tradeDesc = tradeDesc;
	}
	public String getPlayerId() {
		return playerId;
	}
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	public float getReqFee() {
		return reqFee;
	}
	public void setReqFee(float reqFee) {
		this.reqFee = reqFee;
	}
	public String getExectInfo() {
		return exectInfo;
	}
	public void setExectInfo(String exectInfo) {
		this.exectInfo = exectInfo;
	}
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	
	
	public static LdsgPayBean parseJson(String json){
		if(json==null)return null;
		try {
			LdsgPayBean bean = new LdsgPayBean();
			JSONObject jsonObj = new JSONObject(json);
			if(!jsonObj.has("tradeId"))return null;
			if(!jsonObj.has("payerId"))return null;
			if(!jsonObj.has("tradeDesc"))return null;
			if(!jsonObj.has("tradeName"))return null;
			if(!jsonObj.has("reqFee"))return null;
			if(!jsonObj.has("notifyUrl"))return null;
			if(!jsonObj.has("exectInfo"))return null;
			
			bean.setTradeId(jsonObj.getString("tradeId"));
			bean.setPlayerId(jsonObj.getString("payerId"));
			bean.setTradeDesc(jsonObj.getString("tradeDesc"));
			bean.setTradeName(jsonObj.getString("tradeName"));
			float fee = Float.parseFloat(jsonObj.getString("reqFee"));
			bean.setReqFee(fee);
			bean.setNotifyUrl(jsonObj.getString("notifyUrl"));
			bean.setExectInfo(jsonObj.getString("exectInfo"));
			
			//检查是否有备用字段
			if(jsonObj.has("ext1"))
				bean.setExt1(jsonObj.getString("ext1"));
			if(jsonObj.has("ext2"))
				bean.setExt2(jsonObj.getString("ext2"));
			
			return bean;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}
