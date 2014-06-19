package com.easou.game.sghhr;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;

import com.easou.game.sghhr.share.sina.WeiboShareWebChromeClient;
import com.easou.game.sghhr.share.sina.WeiboShareWebViewClient;

public class WeiboShareActivity extends Activity {
	
	//进入这个界面的图标坐标
		static int Location_x  =0;
		static int Location_y  =100;


		//登陆 
		private final String bbs_login_phone = "http://bbs.sghhr.com/member.php?mod=logging&action=login&loginsubmit=yes&loginhash=LHxD9&mobile=yes&formhash=?&referer="
				+ "http://bbs.sghhr.com/forum.php?mobile=2&username=esouGameTing&password=abc123";

		
		private WebView webView;;
		private Button button;

		private ProgressBar loading;
		private final int GETCOOKIS = 110; //
		private final int CONNECTED = 111; //

		private Handler hanlder = new Handler() {
			public void handleMessage(android.os.Message msg) {
				switch (msg.what) {
				case CONNECTED:
					webHtml();
					break;

				case GETCOOKIS:
					getCookis();
					break;
				}
			};
		};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weibo_share);
		this.loading = (ProgressBar) this.findViewById(R.id.progressBar);
		this.loading.setMax(100);
		webView = (WebView) this.findViewById(R.id.web_view);
		WeiboShareWebViewClient webViewClient = new WeiboShareWebViewClient(
				WeiboShareActivity.this, this.loading);
		webView.setWebViewClient(webViewClient);
		WeiboShareWebChromeClient chromeClient = new WeiboShareWebChromeClient(this,loading);
		webView.setWebChromeClient(chromeClient);
		this.loading.setVisibility(View.VISIBLE);
		hanlder.sendEmptyMessage(CONNECTED);
		
		button  = (Button) findViewById(R.id.goback);
	    button.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				WeiboShareActivity.this.finish();
//				Intent  intent = new Intent(EASOUBBSActivity.this,SuspensionService.class);
//		        EASOUBBSActivity.this.startService(intent);
			}
		});
	}
	
	private void webHtml() {
		try {
//			 CookieSyncManager.createInstance(this);
//			 CookieSyncManager.getInstance().startSync();
//			 CookieManager.getInstance().removeAllCookie();
			
			//webView.getSettings().setPluginsEnabled(true);
			
			
			webView.getSettings().setJavaScriptEnabled(true);
			webView.getSettings().setSupportMultipleWindows(true);
			webView.loadUrl(GameEntryBaseActivity.url);
		} catch (Exception ex) {
			ex.printStackTrace();
			this.loading.setVisibility(View.INVISIBLE);
		}

	}


	private void getCookis() {
		try {
			CookieSyncManager cookieSyncManager = CookieSyncManager
					.createInstance(this);
			cookieSyncManager.sync();
			CookieManager cookieManager = CookieManager.getInstance();
			String cookis = cookieManager.getCookie(bbs_login_phone);
			if(GameEntryBaseActivity.url.contains("weibo.com")){
				cookis = cookieManager.getCookie("weibo.com");
			}else{
				cookis = cookieManager.getCookie("qq.com");
			}
			cookieManager.setCookie(GameEntryBaseActivity.url, cookis);
			CookieSyncManager.getInstance().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.loading.setVisibility(View.INVISIBLE);
			hanlder.sendEmptyMessage(CONNECTED);
		}


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weibo_share, menu);
		return true;
	}

}
