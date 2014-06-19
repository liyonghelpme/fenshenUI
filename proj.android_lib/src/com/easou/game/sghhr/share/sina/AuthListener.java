package com.easou.game.sghhr.share.sina;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.easou.game.sghhr.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuth;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;

public class AuthListener implements WeiboAuthListener {
	
	/** 微博 Web 授权类，提供登陆等功能  */
	private WeiboAuth mWeiboAuth;
	/** 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能  */
	private Oauth2AccessToken mAccessToken;
	
	private Context instance;
	
	public AuthListener(Context instance){
		this.instance = instance;
	}

	@Override
	public void onComplete(Bundle values) {
		// 从 Bundle 中解析 Token
		mAccessToken = Oauth2AccessToken.parseAccessToken(values);
		if (mAccessToken.isSessionValid()) {
			//授权成功，启动线程
			AccessTokenKeeper.writeAccessToken(instance,mAccessToken);
			new Thread(new ShareThread(mAccessToken.getToken(), "三国合伙人测试......")).start();
		} else {
			// 当您注册的应用程序签名不正确时，就会收到 Code，请确保签名正确
			String code = values.getString("code");
			String message = "授权失败";
			if (!TextUtils.isEmpty(code)) {
				message = message + "\nObtained the code: " + code;
				System.out.println(message);
			}
			Toast.makeText(instance, message,
					Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onCancel() {
		Toast.makeText(instance, "取消授权", Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public void onWeiboException(WeiboException e) {
		Toast.makeText(instance,
				"Auth exception : " + e.getMessage(), Toast.LENGTH_LONG)
				.show();
	}
}
