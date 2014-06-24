package com.easou.game.sghhr.common;

import java.util.HashMap;

import org.cocos2dx.lib.Cocos2dxHelper;

import com.easou.game.sghhr.Activation;
import com.easou.game.sghhr.CustomConfig;
import com.easou.game.sghhr.MobileFunction;
import com.easou.game.sghhr.NetRequest;
import com.easou.game.sghhr.NetResult;
import com.easou.game.sghhr.UpdateInfoBean;
import com.easou.game.sghhr.Utils;
//import com.easou.game.sghhr.easou.LoginCenterActivity;
//import com.easou.game.sghhr.easou.R;
//import com.easou.game.sghhr.easou.SplashActivity;

import com.easou.game.sghhr.log.LogActEnum;
import com.easou.game.sghhr.log.LogUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.util.Log;

public class Native {
	private static String TAG = "Native";

	public static Context context;
	public static GLSurfaceView surfaceView;
	private static Handler mainThreadHandler;

	public static void init(Context c) {
		context = c;
		if (null == mainThreadHandler) {
			mainThreadHandler = new Handler();
		}
	}

	public static void setGLSurfaceView(GLSurfaceView gl) {
		surfaceView = gl;
	}

	public static void runOnMainThread(Runnable r) {
		if (mainThreadHandler != null) {
			mainThreadHandler.post(r);
		}
	}

	public static void runOnGLThread(Runnable r) {
		if (surfaceView != null) {
			surfaceView.queueEvent(r);
		}
	}

	private static void checkNetwork() {
		Cocos2dxHelper.setBoolForKey("_checkNetYet", true);
		Log.d(TAG, "check Net State init false ");

		runOnMainThread(new Runnable() {
			@Override
			public void run() {
				ConnectivityManager cm = (ConnectivityManager) context
						.getSystemService(Context.CONNECTIVITY_SERVICE);

				Cocos2dxHelper.setBoolForKey("_checkNetYet", true);
				Log.d(TAG, "check Net State result " + cm);
				if(!Utils.isNetworkUseable(context)){
					Cocos2dxHelper.setBoolForKey("_netState", false);
				}else {
					Cocos2dxHelper.setBoolForKey("_netState", true);
				}
				Log.d(TAG, "connecting to network");

			}

		});
	}

	private static void checkActivation() {
		Log.d(TAG, "checkActivation");
		Cocos2dxHelper.setBoolForKey("_checkActYet", false);
		runOnMainThread(new Runnable(){
			@Override
			public void run() {
				Cocos2dxHelper.setBoolForKey("_checkActYet", true);
				if(!Activation.isActive(context, CustomConfig.isNeedActivation(),
						CustomConfig.getApiDomain())) {
					//Activation.showDialogToActive((Activity)context);
					//onError(1, "流程中断，需要激活码激活", null);
					Cocos2dxHelper.setBoolForKey("_activation", false);
				}else {
					Cocos2dxHelper.setBoolForKey("_activation", true);
				}
			}
			
		});
	}
	
	
	private static void checkDevice() {
		Log.d(TAG, "checkActivation");
		Cocos2dxHelper.setBoolForKey("_checkDevYet", false);
		runOnMainThread(new Runnable(){
			@Override
			public void run() {
				Cocos2dxHelper.setBoolForKey("_checkDevYet", true);
				if(Utils.isEmulator(context)){
					Cocos2dxHelper.setBoolForKey("_device", false);
				}else {
					Cocos2dxHelper.setBoolForKey("_device", true);
				}
			}
		});
	}
	
	
	private static void checkUpdate(){
		Cocos2dxHelper.setBoolForKey("_checkUpdateYet", false);
		runOnMainThread(new Runnable(){
			@Override
			public void run() {
				
				NetResult netRequest = NetRequest.getUpdateDataRequest(
						context, CustomConfig.getApiDomain());
				if (netRequest != null && netRequest.getRc() == 1000) {// 获取升级信息成功，则进一步解析，是否要进行升级
					final UpdateInfoBean updateInfo = MobileFunction
							.buildJsonUploadInf(netRequest.getDt());

					HashMap<String, Object> result = new HashMap<String, Object>();

					if (updateInfo.getApkUrl() != null
							&& updateInfo.getApkUrl().length() > 0) {
						// 更新APK
						result.put("UPDATE_INFO", updateInfo);
						result.put("IS_NEED_UPDATE_APK", true);
					} else {
						// 更新资源文件
						result.put("UPDATE_INFO", updateInfo);
						result.put("IS_NEED_UPDATE_RES", true);
					}
					
					Cocos2dxHelper.setBoolForKey("_update", true);
					//onFinish(result);
				} else {
					String errorMsg = "网络连接错误";
					int errorType = 0;
					if (netRequest != null) {
						errorMsg = netRequest.getMsg();
						errorType = 1;
					}
					final String errorMessagefinal = errorMsg;
					Log.d(TAG, "update error msg "+errorMessagefinal);
					
					//context.showAlertDialogForError(errorMessagefinal,
					//		errorType, getTag(), para);
					//onError(1, "errorMessagefinal", null);
					Cocos2dxHelper.setBoolForKey("_update", false);
				}
				
				Cocos2dxHelper.setBoolForKey("_checkUpdateYet", true);
			}
		});	
	}
	
	private static void checkApk(){
		//LogUtil.sendLogInfo(LogActEnum.CHECKVERSION.getAct(), context);
		
	}
	private static void checkRes(){
		
	
	}
	
	//调用某个子类的 跳转登陆界面
	private static void gotoLogin(){
		
	}
	// 保存当前状态 到 Editor 中即可
	// 供c++ 层调用
	
	//一些网络逻辑 可以 直接放到c++ 里面做
	public static void sendCommand(String cmd, String arg) {
		Log.d(TAG, "sendCommand in java " + cmd + " " + arg);
		if (cmd.equals("checkNetWork")) {
			checkNetwork();
		}else if(cmd.equals("checkActivation")) {
			checkActivation();
		}else if(cmd.equals("checkDevice")) {
			checkDevice();
		}else if(cmd.equals("checkUpdate")) {
			checkUpdate();
		//重新调整一下 更新APK的逻辑
		}else if(cmd.equals("checkApk")) {
			checkApk();
		}else if(cmd.equals("checkRes")) {
			checkRes();
		//跳转界面直接放到 c++中来做
		}else if(cmd.equals("gotoLogin")){
			gotoLogin();
		}
	}

}
