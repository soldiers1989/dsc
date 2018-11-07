package com.yixin.web.utils;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * Package : com.yixin.xxm.utils
 *
 * @author YixinCapital -- zhuq 2016年9月7日 下午3:33:29
 *
 */
public class HttpClientHelper {

	private final static Logger logger = LoggerFactory.getLogger(HttpClientHelper.class);
	private static HttpClient httpClient = new DefaultHttpClient(); //创建默认的httpClient实例
	static {
		X509TrustManager xtm = new X509TrustManager(){   //创建TrustManager
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
			public X509Certificate[] getAcceptedIssuers() { return null; }
		};
		try {
			//TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext
			SSLContext ctx = SSLContext.getInstance("TLS");
			//使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
			ctx.init(null, new TrustManager[]{xtm}, null);
			//创建SSLSocketFactory
			SSLSocketFactory socketFactory = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//			socketFactory = new SSLSocketFactory(ctx);
//			socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));
		}catch (Exception e){
			logger.error("https链接初始化异常,{}",e.getMessage(), e);
		}
	}

	public static String sendPost(String url,Map<String, Object> paramMap){
		String returnmsg="";
		try {
			String param = JSONObject.toJSONString(paramMap);
			if(url.startsWith("https")){
				returnmsg=sendPostHttps(url,param);
			}else{
				returnmsg=sendPostHttp(url,param);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnmsg;
	}

	//发送http请求
	public static String sendPostHttp(String url, String json){
		// 发送请求
		HttpClient client = new DefaultHttpClient();
		String returnmsg=""; //失败
		try {
			//实例化HTTP POST方法
			HttpPost postmethod = new HttpPost(url);
			postmethod.addHeader("Content-Type", "application/json");
			StringEntity se = new StringEntity(json,"UTF-8");
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader("Content-Type","application/json"));
			postmethod.setEntity(se);
			//执行请求
			HttpResponse reponse = client.execute(postmethod);
			//回去返回实体
			HttpEntity entity = reponse.getEntity();
			if (null != entity) {
				returnmsg = EntityUtils.toString(entity, "UTF-8");
				EntityUtils.consume(entity); //销毁你打开的entity  Consume response content
			}
		}catch(Exception e) {
			logger.error("http访问异常,url:{},json:{},{}", url, json, e.getMessage());
		}
//		finally{
//			//关闭连接,释放资源
//			client.getConnectionManager().shutdown();
//		}
		return returnmsg;
	}

	//发送https请求
	public static String sendPostHttps(String url, String json){
		//long responseLength = 0;                         //响应长度
		String returnmsg = null;                   //响应内容
		try {
			HttpPost httpPost = new HttpPost(url);                        //创建HttpPost
			httpPost.addHeader("Content-Type", "application/json");
			StringEntity se = new StringEntity(json,"UTF-8");
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader("Content-Type","application/json"));
			httpPost.setEntity(se);
			HttpResponse response = httpClient.execute(httpPost); //执行POST请求
			HttpEntity entity = response.getEntity();             //获取响应实体
			if (null != entity) {
				//responseLength = entity.getContentLength();
				returnmsg = EntityUtils.toString(entity, "UTF-8");
				EntityUtils.consume(entity); //销毁你打开的entity  Consume response content
			}

		}catch (Exception e) {
			logger.error("https访问异常,url:{},json:{},{}", url, json, e.getMessage());
		}
//		finally {
//			httpClient.getConnectionManager().shutdown(); //关闭连接,释放资源
//		}
		return returnmsg;

	}

	public static void main(String[] args) throws IOException {
//		String url="http://192.168.145.99:8080/fileserver/api/fileDownload/download";
//		String fileId = "group2/M00/01/09/wKibNVp3vJ3lqEJkAAA30z6Vucc53.docx";
////		Map<String,Object> paramMap = new HashMap<>();
////		paramMap.put("","group2/M00/01/09/wKibNVp3vJ3lqEJkAAA30z6Vucc53.docx");
//		String result = sendPostHttp(url,fileId);
//		System.out.println(result);

		String url = "https://fileserver.uat.bd.dk/fileserver/api/fileDownload/download";
		String result = sendPostHttps(url,"group1/M01/06/4D/CgECL1uD1MKAC8qZAADD96Envz0585.pdf");
		System.out.println(result);

//		ChallengeSignForMultiDTO multiSignDTO = new ChallengeSignForMultiDTO();
//		List<String> sourceFileNames = new ArrayList<>();
//		sourceFileNames.add("test1.pdf");
//		sourceFileNames.add("test2.pdf");
//		sourceFileNames.add("test3.pdf");
//		multiSignDTO.setPhone("15210206302");
//		multiSignDTO.setSourceFileNames(sourceFileNames);
//		multiSignDTO.setChallengeCode("130911");
//		multiSignDTO.setTransId("16211f68-8a68-464f-8154-ceb98bbabd8c");
//		url="http://192.168.145.99:8080/fileserver/api/electronicSign/challengeMultiFileSign";
//		String jsonSin = JSONObject.toJSONString(multiSignDTO);
//		String signStr = sendPostHttp(url,jsonSin);
//		System.out.println("签名信息："+signStr);
	}
}
