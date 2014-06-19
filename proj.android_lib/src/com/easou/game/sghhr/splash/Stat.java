package com.easou.game.sghhr.splash;

import java.net.URLEncoder;

import android.content.Context;

import com.easou.game.sghhr.CommonConfig;
import com.easou.game.sghhr.CustomConfig;
import com.easou.game.sghhr.NetRequest;
import com.easou.game.sghhr.ResUpdateUtil;
import com.easou.game.sghhr.Utils;

public class Stat {
	public static void onEntry(final Context context)
	{
		new Thread(){
			@Override
			public void run() {
				String url = CommonConfig.STAT_URL+"?"+buildStatData(context);
				NetRequest.doGetWithoutResponse(url);
				super.run();
			}
		}.start();
		
	}

	protected static String buildStatData(Context context) {
		StringBuffer sb = new StringBuffer();
		sb.append("model=");
		sb.append(URLEncoder.encode(android.os.Build.MODEL));
		sb.append("&osVersion=");
		sb.append(URLEncoder.encode(android.os.Build.VERSION.RELEASE));
		sb.append("&imei=");
		String imei = Utils.getImei(context);
		if(imei != null){
			sb.append(URLEncoder.encode(Utils.getImei(context)));
		}
		sb.append("&gamePartnerId=");
		sb.append(URLEncoder.encode(CustomConfig.getPartnerId()));
		sb.append("&easouQn=");
		sb.append(URLEncoder.encode(CustomConfig.getQn()));
		sb.append("&timeStamp=");
		sb.append(URLEncoder.encode(String.valueOf(System.currentTimeMillis())));
		
		String small_version = ResUpdateUtil.getGameResVersion(context);
		String big_version = ResUpdateUtil.getGameApkVersion(context);
		String gameVersion = big_version + "." + small_version;
		sb.append("&gameVersion=");
		sb.append(URLEncoder.encode(gameVersion));
		return sb.toString();
	}
}
