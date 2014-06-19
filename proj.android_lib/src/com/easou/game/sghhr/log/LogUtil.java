package com.easou.game.sghhr.log;

import java.util.UUID;

import android.content.Context;
import android.os.Build;

import com.easou.game.sghhr.CustomConfig;
import com.easou.game.sghhr.ResUpdateUtil;
import com.easou.game.sghhr.Utils;

public class LogUtil {
	
	public static final LogData logData  = new LogData();
	
	private static final String logUrl = "http://stat.ldsg.lodogame.com/dot.e";

	public static void sendLogInfo(int act,Context _context){
		try {
			if(act == LogActEnum.START.getAct()){
				logData.setGt(getGt());
				logData.setGver(getGver(_context));
				logData.setImei(Utils.getImei(_context));
				logData.setMac(Utils.getMacAddress(_context));
				logData.setPid(CustomConfig.getPartnerId());
				logData.setQn(CustomConfig.getQn());
				UUID uuid = UUID.randomUUID();
				logData.setSid(uuid.toString().replaceAll("-",""));
				logData.setUa(Build.MODEL);
			}
			logData.setAct(act);
			new Thread(new LogDataRunnable(logData, act)).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String getGt(){
		return "android|"+Build.VERSION.RELEASE;
	}
	
	private static String getGver(Context _context){
		String small_version = ResUpdateUtil.getGameResVersion(_context);
		String big_version = ResUpdateUtil.getGameApkVersion(_context);
		return big_version+"."+small_version;
	}
}
