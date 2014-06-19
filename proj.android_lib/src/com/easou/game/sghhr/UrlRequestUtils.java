package com.easou.game.sghhr;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class UrlRequestUtils {
	public class Mode {
		public final static String POST = "POST";
		public final static String GET = "GET";
	};

	public static String execute(String url, Map<String,String> paraMap, String mode) {
		// 请求者
		HttpUriRequest request = null;
		// 应答者
		HttpResponse response = null;
		// 配置对象
		// BasicHttpContext context = new BasicHttpContext();
		if (Mode.POST.equals(mode)) {// POST 请求
			request = new HttpPost(url);
			if (!paraMap.isEmpty()) {// 参数不为空
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				Set<String> key = paraMap.keySet();
				// 遍历
				for (Iterator<String> it = key.iterator(); it.hasNext();) {
					String s = (String) it.next();
					nvps.add(new BasicNameValuePair(s, paraMap.get(s)));
				}
				try {
					((HttpPost) request).setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		} else {// GET 请求
			url = buildURL(url, paraMap);
			request = new HttpGet(url);
		}
		// 发送请求
		response = getHttpResponse(request);
		if (response == null || response.getStatusLine().getStatusCode() != 200) {// 通讯失败
			// 终止连接
			request.abort();
			return null;
		}
		try {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return EntityUtils.toString(entity, "utf-8");
			} else {
				return null;
			}
		} catch (UnsupportedEncodingException e) {
			request.abort();
		} catch (IllegalStateException e) {
			request.abort();
		} catch (IOException e) {
			request.abort();
		} finally {
			request.abort();
		}
		return null;
	}

	public static String buildParam(Map<String,String> paramMap) {

		StringBuffer sb = new StringBuffer();
		for (Iterator<String> iterator = paramMap.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			sb.append(key).append("=").append(paramMap.get(key));
			if (iterator.hasNext()) {
				sb.append("&");
			}
		}
		String paraStr = sb.toString();

		return paraStr;
	}

	public static String buildURL(String url, Map<String,String> paramMap) {
		if (paramMap == null || paramMap.isEmpty()) {
			return url;
		}
		if (StringUtils.isBlank(url)) {
			return url;
		}
		StringBuffer sb = new StringBuffer();
		for (Iterator<String> iterator = paramMap.keySet().iterator(); iterator.hasNext();) {
			String key = iterator.next();
			sb.append(key).append("=").append(paramMap.get(key));
			if (iterator.hasNext()) {
				sb.append("&");
			}
		}
		String paraStr = sb.toString();
		StringBuffer buffer = new StringBuffer();

		buffer.append(url);
		if (url.indexOf("?") != -1) {
			if (url.indexOf("?") == url.length() - 1) {
				buffer.append(paraStr);
			} else {
				buffer.append("&").append(paraStr);
			}
		} else {
			buffer.append("?").append(paraStr);
		}
		return buffer.toString();
	}

	/**
	 * 获取响应对象
	 * 
	 * @param request
	 *            请求对象
	 * @param context
	 *            配置参数
	 * @return
	 */
	public static HttpResponse getHttpResponse(HttpUriRequest request) {
		try {
			// return
			// HttpConnectionPoolManager.getHttpClient().execute(request);
			HttpClient httpClient = new DefaultHttpClient();
			return httpClient.execute(request);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			request.abort();
//			LOG.error("网络连接异常或者客户端协议错误:" + request.getURI());
//			LOG.error(e.getMessage(), e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			request.abort();
//			LOG.info("网络连接异常:" + request.getURI());
//			LOG.error(e.getMessage(), e);
		}
		return null;
	}
}