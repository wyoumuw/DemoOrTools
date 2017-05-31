package com.youmu.maven.utils;

import com.youmu.maven.utils.model.NetResult;
import com.youmu.maven.utils.model.RequestMethod;

import javax.activation.MimetypesFileTypeMap;
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
import java.util.HashMap;
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
		return request(url, RequestMethod.GET,null,params,null);
	}
	public static NetResult post(String url, Map<String, String> params){
		return request(url,RequestMethod.POST,null,params,null);
	}
	public static NetResult get(String url, Map<String, String> params, byte[] out){
		return request(url,RequestMethod.GET,null,params,out);
	}
	public static NetResult post(String url, Map<String, String> params, byte[] out){
		return request(url,RequestMethod.POST,null,params,out);
	}
	public static NetResult request(String url, RequestMethod method,final Map<String,String> header,final Map<String, String> params, byte[] out) {
		url = makeUrl(url, params);
		HttpURLConnection connection = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		NetResult result = new NetResult();
		try {
			connection = getConnection(url);
            setRequestProperties(connection,header);
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


	private static void setRequestProperties(final HttpURLConnection connection,final Map<String,String> header){
	    if(null==header){return ;}
        for (Map.Entry<String, String> entry : header.entrySet()) {
            connection.addRequestProperty(entry.getKey(),entry.getValue());
        }
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

	private static final String KEY_HEADER_CONTENT_TYPE="Content-Type";
	public static NetResult postMultipart(String url,Map<String,String> param,Map<String,String> formStrParam,Map<String,File> formFileParam){
        Map<String,String> header=new HashMap<>();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            //生成---youmu1496221771223youmu---为分割线
            String boundary = new StringBuilder().append("---youmu").append(System.currentTimeMillis()).append("youmu---").toString();
            header.put(KEY_HEADER_CONTENT_TYPE, new StringBuilder("multipart/form-data; boundary=").append(boundary).toString());
            header.put("Connection", "Keep-Alive");
//        header.put("header","");
//        header.put("header","");

            StringBuilder sb = new StringBuilder();
            if (null != formStrParam) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
                        continue;
                    }
                    sb.append("\r\n").append("--").append(boundary).append("\r\n");
                    sb.append("Content-Disposition: form-data; name=\"").append(key).append("\"\r\n\r\n").append(value);
                }
            }
            byteArrayOutputStream.write(sb.toString().getBytes());
            if (null != formFileParam) {
                for (Map.Entry<String, File> entry : formFileParam.entrySet()) {
                    StringBuilder ssb=new StringBuilder();
                    String key = entry.getKey();
                    File value = entry.getValue();
                    if (StringUtils.isEmpty(key) ||null==value) {
                        continue;
                    }
                    String contentType=MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(value);
                    ssb.append("\r\n").append("--").append(boundary).append("\r\n");
                    ssb.append("Content-Disposition: form-data; name=\"").append(key).append("\";filename=\"").append(value.getName()).append("\"\r\n").append("Content-Type:").append(contentType).append("\r\n\r\n");
                    byteArrayOutputStream.write(ssb.toString().getBytes());
                    DataInputStream in = new DataInputStream(new FileInputStream(value));
                    int len = 0;
                    byte[] bufferOut = new byte[1024];
                    while ((len = in.read(bufferOut)) != -1) {
                        byteArrayOutputStream.write(bufferOut, 0, len);
                    }
                    in.close();
                }
            }
            String end=new StringBuilder("\r\n--").append(boundary).append("--\r\n").toString();
            byteArrayOutputStream.write(end.getBytes());
            byteArrayOutputStream.flush();
            return request(url,RequestMethod.POST,header,param,byteArrayOutputStream.toByteArray());
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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
