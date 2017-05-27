package com.youmu.maven.utils;

import com.youmu.maven.utils.model.NetResult;
import com.youmu.maven.utils.model.RequestMethod;

import javax.net.ssl.*;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

public abstract class NetUtils {

	public static String makeUrl(String baseUrl, Map<String, String> params) {
		if (null == baseUrl) {
			throw new RuntimeException("illegal baseUrl");
		}
		if (CollectionUtils.isEmpty(params)) {
			return baseUrl;
		}
		StringBuilder sb = new StringBuilder(baseUrl);
		sb.append("?");
		for (Map.Entry<String, String> entry : params.entrySet()) {
			sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		return sb.toString().substring(0, sb.length() - 1);
	}

	public static NetResult get(String url, Map<String, String> params){
		return request(url, RequestMethod.GET,params,null);
	}
	public static NetResult post(String url, Map<String, String> params){
		return request(url,RequestMethod.POST,params,null);
	}
	public static NetResult get(String url, Map<String, String> params, byte[] out){
		return request(url,RequestMethod.GET,params,out);
	}
	public static NetResult post(String url, Map<String, String> params, byte[] out){
		return request(url,RequestMethod.POST,params,out);
	}
	public static NetResult request(String url, RequestMethod method, Map<String, String> params, byte[] out) {
		url = makeUrl(url, params);
		HttpURLConnection connection = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		NetResult result = new NetResult();
		try {
			connection = getConnection(url);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			// 设置请求方式（GET/POST）
			connection.setRequestMethod(method.getMethod());
//            if ("GET".equalsIgnoreCase(requestMethod)){
//                httpUrlConn.connect();
//            }
			// 当有数据需要提交时
			if (null != out) {
				OutputStream outputStream = connection.getOutputStream();
				outputStream.write(out);
				outputStream.close();
			}
			// 将返回的输入流转换成字符串
			inputStream = connection.getInputStream();
			StringBuilder sb = new StringBuilder();
			if (null != inputStream) {
				inputStreamReader = new InputStreamReader(inputStream, "utf-8");
				bufferedReader = new BufferedReader(inputStreamReader);
				String str = null;

				while ((str = bufferedReader.readLine()) != null) {
					sb.append(str);
				}
			}
			result.setCode(connection.getResponseCode());
			result.setMsg(connection.getResponseMessage());
			result.setContent(sb.toString());
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (null != connection) {
				connection.disconnect();
			}
			try {
				if (null != bufferedReader) {
					bufferedReader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (null != inputStreamReader) {
					inputStreamReader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (null != inputStream) {
					inputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			inputStream = null;
		}
		return result;
	}

	private static HttpURLConnection getConnection(String url) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
		if (!StringUtils.isEmpty(url) && url.startsWith("https")) {
			return getSSLConnection(url);
		}
		return getNormalConnection(url);
	}

	private static HttpURLConnection getNormalConnection(String url) throws IOException {
		URL uurl = new URL(url);
		HttpsURLConnection httpUrlConn = (HttpsURLConnection) uurl.openConnection();
		return httpUrlConn;
	}

	//ssl
	private static TrustManager[] tm = {new X509TrustManager() {
		public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

		}

		public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

		}

		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	}};

	private static HttpURLConnection getSSLConnection(String url) throws NoSuchProviderException, NoSuchAlgorithmException, KeyManagementException, IOException {
		// 创建SSLContext对象，并使用我们指定的信任管理器初始化

		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null, tm, new java.security.SecureRandom());
		// 从上述SSLContext对象中得到SSLSocketFactory对象
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		//打开连接
		URL uurl = new URL(url);
		HttpsURLConnection httpUrlConn = (HttpsURLConnection) uurl.openConnection();
		httpUrlConn.setSSLSocketFactory(ssf);
		return httpUrlConn;
	}

	/**
	 * 获取真实Ip
	 * @param req
	 * @return
	 */
	public static String getRealIp(HttpServletRequest req) {
		String ip = req.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getRemoteAddr();
		}
		return ip;
	}
}
