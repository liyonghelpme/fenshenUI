package com.easou.bbs;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;

import com.easou.game.sghhr.easou.R;

public class EASOUBBSActivity extends Activity {
	
	//进入这个界面的图标坐标
	static int Location_x  =0;
	static int Location_y  =100;

    //主页
	private final String bbs_Homepage = "http://bbs.sghhr.com/forum.php?mobile=2";

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
		setContentView(R.layout.bbslayout);
		this.loading = (ProgressBar) this.findViewById(R.id.progressBar);
		this.loading.setMax(100);
		webView = (WebView) this.findViewById(R.id.web_view);
		BBSWebViewClient webViewClient = new BBSWebViewClient(
				EASOUBBSActivity.this, this.loading);
		webView.setWebViewClient(webViewClient);
		BBSWEBWebChromeClient chromeClient = new BBSWEBWebChromeClient(this,
				loading);
		webView.setWebChromeClient(chromeClient);
		this.loading.setVisibility(View.VISIBLE);
		hanlder.sendEmptyMessage(CONNECTED);
		
		button  = (Button) findViewById(R.id.goback);
	    button.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EASOUBBSActivity.this.finish();
//				Intent  intent = new Intent(EASOUBBSActivity.this,SuspensionService.class);
//		        EASOUBBSActivity.this.startService(intent);
			}
		});
		
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
	    	webView.goBack();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.easoubb, menu);
		return true;
	}

	private void webHtml() {
		try {
//			 CookieSyncManager.createInstance(this);
//			 CookieSyncManager.getInstance().startSync();
//			 CookieManager.getInstance().removeAllCookie();
			//webView.getSettings().setPluginsEnabled(true);
			
			
			webView.getSettings().setJavaScriptEnabled(true);
			webView.getSettings().setSupportMultipleWindows(false);
			webView.loadUrl(bbs_Homepage);
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

			cookieManager.setCookie(bbs_Homepage, cookis);
			CookieSyncManager.getInstance().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.loading.setVisibility(View.INVISIBLE);
			hanlder.sendEmptyMessage(CONNECTED);
		}


	}
	
	

	// private boolean isPad() {
	// WindowManager wm = (WindowManager)
	// getSystemService(Context.WINDOW_SERVICE);
	// Display display = wm.getDefaultDisplay();
	// // ��Ļ���
	// float screenWidth = display.getWidth();
	// // ��Ļ�߶�
	// float screenHeight = display.getHeight();
	// DisplayMetrics dm = new DisplayMetrics();
	// display.getMetrics(dm);
	// double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
	// double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
	// // ��Ļ�ߴ�
	// double screenInches = Math.sqrt(x + y);
	// // ����6�ߴ���ΪPad
	// if (screenInches >= 5.0) {
	// return true;
	// }
	// return false;
	// }

}
