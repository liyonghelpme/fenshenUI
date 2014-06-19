package com.easou.game.sghhr.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LogDataRunnable implements Runnable{
	
	private LogData logData;
	
	private int act;
	
	public LogDataRunnable(LogData logData,int act){
		this.logData = logData;
		this.act = act;
	}

	@Override
	public void run() {
		URL url = null;
		HttpURLConnection connection = null;
		InputStreamReader in = null;
		try {
			String str = "http://stat.ldsg.lodogame.com/dot.e?act="+act+"&pid="+logData.getPid()
					+"&qn="+logData.getQn()+"&gver="+logData.getGver()+"&imei="+logData.getImei()+"&mac="+
					logData.getMac()+"&idfa="+logData.getIdfa()+"&gt="+logData.getGt()+"&ua="+logData.getUa()
					+"&sid="+logData.getSid();
			url = new URL(str);
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(10000);
			connection.setReadTimeout(10000);
			in = new InputStreamReader(connection.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(in);
			StringBuffer strBuffer = new StringBuffer();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				strBuffer.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
