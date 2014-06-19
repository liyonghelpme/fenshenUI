package com.easou.game.sghhr;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class Activation {

	public static final int ACTIVATION_SUCC = 9999;
	public static Activity a_activity;

	/**
	 * 是否已激活
	 * 
	 * @return
	 */
	public static boolean isActive(Context context,boolean isNeedActivation, String apiDomain) {
		if(!isNeedActivation) return true;
		
		String url = Utils.appendUrlWithCustomProperty(apiDomain
				+ "/webApi/isActive.do?uuid=" + SpHelper.getUserUUID(),context);
		String result = NetRequest.getStringFromNet(url,context);
		if (result != null) {
			JSONObject js;
			try {
				js = new JSONObject(result);
				if (js.has("rc")) {
					return js.getInt("rc") == 1000;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public static void checkAndShowActiveDialog(final Activity activity,final boolean isNeedActivation, final String apiDomain, final Runnable callback){
		new Thread(){
			@Override
			public void run() {
				if(!isActive(activity,isNeedActivation,apiDomain)){
					showDialogToActive(activity);
				}else{
					callback.run();
				}
			}
		}.start();
	}

	/**
	 * 显示激活码输入界面
	 * @param callback 
	 */
	public static void showDialogToActive(Activity activity) {
		activity.startActivityForResult(new Intent(activity, ActivationActivity.class), 0);
		a_activity = activity;
	}
	
	public static void stopAActivity(){
		if(a_activity!=null){
			a_activity.finish();
		}
	}

	/**
	 * 激活
	 * 
	 * @param code
	 * @return
	 */
	public static boolean doActive(String code,Context context,String apiDomain) {
		String url = Utils.appendUrlWithCustomProperty(apiDomain
				+ "/webApi/active.do?uuid=" + SpHelper.getUserUUID()
				+ "&code=" + code,context);
		String result = NetRequest.getStringFromNet(url,context);
		if (result != null) {
			JSONObject js;
			try {
				js = new JSONObject(result);
				if (js.has("rc")) {
					return js.getInt("rc") == 1000;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return false;

	}
}
