package com.easou.game.sghhr;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;

/**
 * 检查网强度状态
 * 
 * @author ted
 * 
 */
public class SignalCheckListener  {
	private TelephonyManager manager;
	private WifiManager wifiManager;
	private WifiInfo info;
	private static int SINGAL_LEVEL = 0; // 0~5:0为无信号，1~5为信号的强度，依次增强
	private int type = 0; // 1： wifi ，2:为2G， 3：为3G ，0：无网络
	private static final int NO_NETWORK = 0;
	private static final int WIFI_NETWORK = 1;
	private static final int TOW_G_NETWORK = 2;
	private static final int THREE_G_NETWORK = 3;

	private StrengthCallBack callBack = null;
	
	/**
	 * 监听回调接口
	 * @author ted
	 */
	public interface StrengthCallBack{
		public void callBack(int level);
	}

	
	public SignalCheckListener(Context context,StrengthCallBack callBack){
		manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		info = wifiManager.getConnectionInfo();
		this.callBack = callBack;
	}
	
	/**
	 * 开始监听
	 */
	public void startListen(){
		manager.listen(new PhoneStateMoniter(),
				PhoneStateListener.LISTEN_SIGNAL_STRENGTHS
						| PhoneStateListener.LISTEN_SERVICE_STATE);
	}
	
	/**
	 * 停止监听
	 */
	public void stopListen(){
		manager = null;
		wifiManager = null;
		info = null;
		System.gc();
	}
	
	
	private class PhoneStateMoniter extends PhoneStateListener {
		@Override
		public void onSignalStrengthsChanged(SignalStrength signalStrength) {
			super.onSignalStrengthsChanged(signalStrength);
			// 在此处整合所有信号变化结果，将其分级
			if (info.getBSSID() != null) {
				// WIFI信号
				type = 1;
				SINGAL_LEVEL = calculateLevel(0, type);
			} else if (!signalStrength.isGsm()) {
				// 3G信号
				type = 3;
				int union = signalStrength.getCdmaDbm(); // 联通3G 信号强度
				int tel = signalStrength.getEvdoDbm(); // 电信3G 信号强度
				int strength = Math.max(union, tel);
				SINGAL_LEVEL = calculateLevel(strength, type);
			} else {
				// 2G信号强度
				type = 2;
				int strength = signalStrength.getGsmSignalStrength();
				SINGAL_LEVEL = calculateLevel(strength, type);
			}
			callBack.callBack(SINGAL_LEVEL);
		}

		/**
		 * 计算信号强度的等级
		 * 
		 * @param strength
		 * @param isGsm
		 * @return
		 */
		public int calculateLevel(int strength, int type) {
			int signalLevel = 0;
			switch (type) {
			case NO_NETWORK:
				signalLevel = NO_NETWORK;
				break;
			case WIFI_NETWORK:
				signalLevel = WifiManager.calculateSignalLevel(info.getRssi(),
						6);
				break;
			case TOW_G_NETWORK:
				signalLevel = deal2GStrength(strength);
				break;
			case THREE_G_NETWORK:
				signalLevel = deal3GStrength(strength);
				break;
			default:
				signalLevel = NO_NETWORK;
				break;
			}
			return signalLevel;
		}

		/**
		 * 处理2G信号强度
		 * 
		 * @param strength
		 * @return
		 */
		private int deal2GStrength(int strength) {
			int signalLevel = 0;
			if (strength <= 5)
				signalLevel = 1;
			else if (strength > 5 && strength <= 10)
				signalLevel = 2;
			else if (strength > 10 && strength <= 15)
				signalLevel = 3;
			else if (strength > 15 && strength <= 20)
				signalLevel = 4;
			else if (strength > 20)
				signalLevel = 5;
			return signalLevel;
		}

		/**
		 * 处理3G信号强度
		 * 
		 * @param strength
		 * @return
		 */
		private int deal3GStrength(int strength) {
			int signalLevel = 0;
			if (strength <= -180)
				signalLevel = 1;
			else if (strength > -180 && strength <= -150)
				signalLevel = 2;
			else if (strength > -150 && strength <= -12)
				signalLevel = 3;
			else if (strength > -120 && strength <= -100)
				signalLevel = 4;
			else if (strength > -100)
				signalLevel = 5;
			return signalLevel;
		}

	}
}
