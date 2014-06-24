package com.easou.game.sghhr;

import org.cocos2dx.lib.Cocos2dxActivity;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;
import org.json.JSONException;
import org.json.JSONObject;

import com.easou.game.sghhr.common.Native;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;

public abstract class GameEntryBaseActivity extends Cocos2dxActivity implements
		IPay {
	private static GameEntryBaseActivity _instance = null;
	public static byte[] PAY_LOCK = new byte[0];
	public static String url = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		_instance = this;
		super.onCreate(savedInstanceState);
		
		
		Native.init(this);
		Native.setGLSurfaceView(getSurfaceView());
	}

	@Override
	protected void onResume() {
		if (!Utils.isNetworkUseable(GameEntryBaseActivity.this)) {
			showAlertDialogForNetworkInvalid();
		}
		super.onResume();
	}

	private void showAlertDialogForNetworkInvalid() {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				GameEntryBaseActivity.this);
		builder.setTitle("提示");
		builder.setMessage("主公，您的手机网络不给力，请检查手机网络设置");
		builder.setCancelable(false);
		builder.setNegativeButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				finish();
			}
		});
		builder.create();
		builder.show();
	}
	

	
	public static final void startShare(final String json){
		try {
			JSONObject jsonObject = new JSONObject(json);
			String context = jsonObject.getString("jsonObject");
			//1新浪微博 2腾讯微博
			if("".equals(context)){
				return;
			}
			if(jsonObject.getInt("type") == 1){
				url = "http://service.weibo.com/share/share.php?title="+context+"&appkey=3138506347&pic=&ralateUid=";   
			}else if(jsonObject.getInt("type") == 2){
				url = "http://share.v.t.qq.com/index.php?c=share&a=index&title="+context+"&appkey=5eoif9GRfSN2MlgW";
			}else if(jsonObject.getInt("type") == 3){
				url = "http://118.244.198.75:8080/gameadmin/getdata?data="+context;
			}
			Intent mIntent = new Intent(_instance,WeiboShareActivity.class);  
	        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
	        _instance.startActivity(mIntent); 
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static boolean catchBackPressed() {
		return false;
	}

	public static final void startPay(final String payParaJsonStr) {
		if (_instance != null) {
			synchronized (PAY_LOCK) {
				
				_instance.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						_instance.doPay(payParaJsonStr);
					}
				});
			}
		}
	}

}
