package com.easou.game.sghhr;

/** 
 * 存储不同渠道的静态数据
 *  */
public class CustomConfig {
	
	/** 密钥 */
	public static final String key = "gCvKaE0tTcWtHsPkbRdE";
	
	/** 合作运营商 Id */
	private static String partnerId;
	private static boolean isInit = false;
	private static String apiDomain;
	private static boolean isNeedActivation =false;
	/** 渠道 QN 参数 */
	private static String qn;

	/** 外部获取运营商ID */
	public static String getPartnerId() {
		return partnerId;
	}

	public synchronized static void init(String _partnerId,String _apiDomain,boolean _isNeedActivation,String _qn) {
		if(!isInit){
			isInit = true;
			partnerId = _partnerId;
			apiDomain = _apiDomain;
			isNeedActivation = _isNeedActivation;
			qn = _qn;
		}
	}
	
	public static String getApiDomain(){
		return apiDomain;
	}
	
	public static boolean isNeedActivation(){
		return isNeedActivation;
	}
	
	public static String getQn(){
		return qn;
	}
}