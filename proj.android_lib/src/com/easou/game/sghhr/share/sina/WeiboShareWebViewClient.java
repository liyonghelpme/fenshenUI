package com.easou.game.sghhr.share.sina;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * 各种通知
 * @author Administrator
 *
 */
public class WeiboShareWebViewClient extends WebViewClient {
//	private  String TAG  = "BBSWebViewClient" ;
    private Context  context;
    private ProgressBar loading ;
	public WeiboShareWebViewClient(Context  context  , ProgressBar  loading) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.loading = loading;
	}
	@Override  
	public void doUpdateVisitedHistory(WebView view, String url,  
	        boolean isReload)  
	{  
	    // TODO Auto-generated method stub  
	    super.doUpdateVisitedHistory(view, url, isReload);  
//	    Log.v(TAG, 36 + url);
	}  
	  
	@Override  
	public void onFormResubmission(WebView view, Message dontResend,  
	        Message resend)  
	{  
	    // TODO Auto-generated method stub  
	    super.onFormResubmission(view, dontResend, resend);  
	}  
	  
	@Override  
	public void onLoadResource(WebView view, String url)  
	{  
	    // TODO Auto-generated method stub  
	    super.onLoadResource(view, url);  
	}  
	  
	@Override  
	public void onPageFinished(WebView view, String url)  
	{  
	    // TODO Auto-generated method stub  
	    super.onPageFinished(view, url);  
//	    Log.v(TAG, "59 ������");
	    this.loading.setVisibility(android.view.View.INVISIBLE);
	}  
	  
	@Override  
	public void onPageStarted(WebView view, String url, Bitmap favicon)  
	{  
	    // TODO Auto-generated method stub  
	    super.onPageStarted(view, url, favicon);  
	    this.loading.setVisibility(android.view.View.VISIBLE);
//	    Log.v(TAG, "68  onPageStarted");
	}  
	  
	@Override  
	public void onReceivedError(WebView view, int errorCode,  
	        String description, String failingUrl)  
	{  
	    // TODO Auto-generated method stub  
	    super.onReceivedError(view, errorCode, description, failingUrl);  
	    this.loading.setVisibility(android.view.View.INVISIBLE);
	    Toast.makeText(context,
	    		errorCode + "/" + description, Toast.LENGTH_LONG)
	    		.show();
//	    Log.v(TAG, "82  onReceivedError");
	}  
	  
	@Override  
	public void onReceivedHttpAuthRequest(WebView view,  
	        HttpAuthHandler handler, String host, String realm)  
	{  
	    // TODO Auto-generated method stub  
	    super.onReceivedHttpAuthRequest(view, handler, host, realm);  
//	    Log.v(TAG, "91  onReceivedHttpAuthRequest");
	}  
	  
	@Override  
	public void onReceivedSslError(WebView view, SslErrorHandler handler,  
	        SslError error)  
	{  
	    // TODO Auto-generated method stub  
	    super.onReceivedSslError(view, handler, error);  
	}  
	  
	@Override  
	public void onScaleChanged(WebView view, float oldScale, float newScale)  
	{  
	    // TODO Auto-generated method stub  
	    super.onScaleChanged(view, oldScale, newScale);  
	}  
	  
	@Override  
	public void onTooManyRedirects(WebView view, Message cancelMsg,  
	        Message continueMsg)  
	{  
	    // TODO Auto-generated method stub  
	    super.onTooManyRedirects(view, cancelMsg, continueMsg);  
	}  
	  
	@Override  
	public void onUnhandledKeyEvent(WebView view, KeyEvent event)  
	{  
	    // TODO Auto-generated method stub  
	    super.onUnhandledKeyEvent(view, event);  
	}  
	  
	@Override  
	public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event)  
	{  
	    // TODO Auto-generated method stub  
	    return super.shouldOverrideKeyEvent(view, event);  
	} 
	// �����ڵ�ǰ��Browser����Ӧ��
	// �����¿�android ��ϵͳ��browser��Ӧ
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		// TODO Auto-generated method stub
//		   Log.v(TAG, "91  shouldOverrideUrlLoading");
	    this.loading.setVisibility(android.view.View.INVISIBLE);
		return super.shouldOverrideUrlLoading(view, url);
//		view.loadUrl(url);
//		return true;
	}
	
}
