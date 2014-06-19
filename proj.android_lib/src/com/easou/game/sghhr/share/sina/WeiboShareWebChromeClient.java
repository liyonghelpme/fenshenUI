package com.easou.game.sghhr.share.sina;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Message;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage.QuotaUpdater;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class WeiboShareWebChromeClient extends WebChromeClient {
//	private String TAG = "BBSWEBWebChromeClient";
    private Activity context;
    private ProgressBar loading ;
	public WeiboShareWebChromeClient (Activity contex ,ProgressBar loading) {
		// TODO Auto-generated constructor stub
		this.context = contex;
		this.loading = loading;
	}
	 @Override  
	    public void onProgressChanged(WebView view, int newProgress)  
	    {  
//		    Log.v(TAG, "30 : "+newProgress);
		   context.setProgress(newProgress*100);
		 
//	        if(newProgress==100)  
//	        {  
//	            view.setVisibility(View.VISIBLE);  
//	        }  
	    }  
	  
	@Override  
	public Bitmap getDefaultVideoPoster()  
	{  
	    // TODO Auto-generated method stub  
	    return super.getDefaultVideoPoster();  
	}  
	  
	@Override  
	public View getVideoLoadingProgressView()  
	{  
	    // TODO Auto-generated method stub  
	    return super.getVideoLoadingProgressView();  
	}  
	  
	@Override  
	public void getVisitedHistory(ValueCallback<String[]> callback)  
	{  
	    // TODO Auto-generated method stub  
	    super.getVisitedHistory(callback);  
	}  
	  
	@Override  
	public void onCloseWindow(WebView window)  
	{  
	    // TODO Auto-generated method stub  
	    super.onCloseWindow(window);  
	}  
	  
	@Override  
	public boolean onConsoleMessage(ConsoleMessage consoleMessage)  
	{  
	    // TODO Auto-generated method stub  
	    return super.onConsoleMessage(consoleMessage);  
	}  
	  
	@Override  
	public void onConsoleMessage(String message, int lineNumber,  
	        String sourceID)  
	{  
	    // TODO Auto-generated method stub  
	    super.onConsoleMessage(message, lineNumber, sourceID);  
	}  
	  
	@Override  
	public boolean onCreateWindow(WebView view, boolean dialog,  
	        boolean userGesture, Message resultMsg)  
	{  
	    // TODO Auto-generated method stub  
	    return super.onCreateWindow(view, dialog, userGesture, resultMsg);  
	}  
	  
	@Override  
	public void onExceededDatabaseQuota(String url,  
	        String databaseIdentifier, long currentQuota,  
	        long estimatedSize, long totalUsedQuota,  
	        QuotaUpdater quotaUpdater)  
	{  
	    // TODO Auto-generated method stub  
	    super.onExceededDatabaseQuota(url, databaseIdentifier, currentQuota,  
	            estimatedSize, totalUsedQuota, quotaUpdater);  
	}  
	  
	@Override  
	public void onGeolocationPermissionsHidePrompt()  
	{  
	    // TODO Auto-generated method stub  
	    super.onGeolocationPermissionsHidePrompt();  
	}  

	@Override  
	public void onHideCustomView()  
	{  
	    // TODO Auto-generated method stub  
	    super.onHideCustomView();  
	}  
	  
	@Override  
	public boolean onJsAlert(WebView view, String url, String message,  
	        JsResult result)  
	{  
	    // TODO Auto-generated method stub  
	    return super.onJsAlert(view, url, message, result);  
	}  
	  
	@Override  
	public boolean onJsBeforeUnload(WebView view, String url,  
	        String message, JsResult result)  
	{  
	    // TODO Auto-generated method stub  
	    return super.onJsBeforeUnload(view, url, message, result);  
	}  
	  
	@Override  
	public boolean onJsConfirm(WebView view, String url, String message,  
	        JsResult result)  
	{  
	    // TODO Auto-generated method stub  
	    return super.onJsConfirm(view, url, message, result);  
	}  
	  
	@Override  
	public boolean onJsPrompt(WebView view, String url, String message,  
	        String defaultValue, JsPromptResult result)  
	{  
	    // TODO Auto-generated method stub  
	    return super.onJsPrompt(view, url, message, defaultValue, result);  
	}  
	  
	@Override  
	public boolean onJsTimeout()  
	{  
	    // TODO Auto-generated method stub  
	    return super.onJsTimeout();  
	}  
	  
	@Override  
	public void onReachedMaxAppCacheSize(long spaceNeeded,  
	        long totalUsedQuota, QuotaUpdater quotaUpdater)  
	{  
	    // TODO Auto-generated method stub  
	    super.onReachedMaxAppCacheSize(spaceNeeded, totalUsedQuota, quotaUpdater);  
	}  
	  
	@Override  
	public void onReceivedIcon(WebView view, Bitmap icon)  
	{  
	    // TODO Auto-generated method stub  
	    super.onReceivedIcon(view, icon);  
	}  
	  
	@Override  
	public void onReceivedTitle(WebView view, String title)  
	{  
	    // TODO Auto-generated method stub  
	    super.onReceivedTitle(view, title);  
	}  
	  
	@Override  
	public void onReceivedTouchIconUrl(WebView view, String url,  
	        boolean precomposed)  
	{  
	    // TODO Auto-generated method stub  
	    super.onReceivedTouchIconUrl(view, url, precomposed);  
	}  
	  
	@Override  
	public void onRequestFocus(WebView view)  
	{  
	    // TODO Auto-generated method stub  
	    super.onRequestFocus(view);  
	}  
	  
	@Override  
	public void onShowCustomView(View view, CustomViewCallback callback)  
	{  
	    // TODO Auto-generated method stub  
	    super.onShowCustomView(view, callback);  
	}  
	      
}
