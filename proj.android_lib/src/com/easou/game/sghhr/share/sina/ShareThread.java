package com.easou.game.sghhr.share.sina;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;

public class ShareThread implements Runnable{

	private String token;
	
	private String text;
	
	public ShareThread(String token,String text){
		this.token = token;
		this.text = text;
	}

	@Override
	public void run() {
		//发送微博
		String path = "https://api.weibo.com/2/statuses/update.json?access_token="+token+
				"&status="+URLEncoder.encode(text);
		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url
					.openConnection();
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			int code = conn.getResponseCode();
			if (code == 200) {
				InputStream is = conn.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				StringBuffer sb = new StringBuffer();
				String temp = "";
				while ((temp = br.readLine()) != null) {
					sb.append(temp);
				}
				JSONObject jsonarr = new JSONObject(sb.toString());
				jsonarr.getLong("id");
			} else {
				System.out.println("->code=" + code);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
