package com.yixin.dsc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yixin.dsc.common.exception.BzException;

/**
 * http请求常用方法
 * Package : com.yixin.ssp.utils.common
 * 
 * @author YixinCapital -- chenyuan1
 *		   2016年10月24日 下午3:03:54
 *
 */
public class HttpUtils {
	protected final static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
	
	/**
     * http发送post请求,JSON格式入参
     */
    public static final String APPLICATION = "application/json";
    
    
	private HttpUtils(){
	}
	
	/**
     * 发送get请求
     * 
     * @param url 请求地址
     * @param param 请求参数
     * @return 
     * @author YixinCapital -- chenyuan1
     *	       2016年10月24日 下午2:52:18
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
            	logger.info("{}--->:{}",key,map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
        	logger.error("发送GET请求出现异常！:{}",e);
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    
    /**
     * 发送post请求
     * 
     * @param url 请求地址
     * @param param 请求参数
     * @return 
     * @author YixinCapital -- chenyuan1
     *	       2016年10月24日 下午2:52:18
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",  "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
        	logger.error("发送 POST 请求出现异常！:{}",e);
        } finally{   //使用finally块来关闭输出流、输入流
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            } catch(IOException ex){
                logger.error("使用finally块来关闭输出流、输入流失败:{}",ex);
            }
        }
        return result;
    }    
    
    /**
     * 发送get请求
     * 
     * @param url 请求地址
     * @return 
     * @author YixinCapital -- huxinyuan02
     *	       2017年8月30日20:51:24
     */
    public static String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
            	logger.info("{}--->:{}",key,map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
        	logger.error("发送GET请求出现异常！:{}",e);
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
            	logger.error("发送GET请求出现异常！:{}",e2);
            }
        }
        return result;
    }
    
	/**
	 * 发送post请求
	 * @param url
	 * @param paramsMap
	 * @param headMap
	 * @return
	 */
	public static String sendPost(String url, Map<String, String> paramsMap,Map<String, String> headMap) {
		String responseContent = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost(url);
			setPostHead(httpPost, headMap);
			setPostParams(httpPost, paramsMap);
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				HttpEntity entity = response.getEntity();
				responseContent = getRespString(entity);
				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		} catch (Exception e) {
			responseContent = MessageFormat.format("http-error:{0}",e.getMessage());
			logger.error("httpPost请求失败", e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				logger.error("httpPost关闭失败", e);
			}
		}
		return responseContent;
	}
	
	/**
	 * 设置POST的参数
	 * 
	 * @param httpPost
	 * @param paramsMap
	 * @throws Exception
	 */
	private static void setPostParams(HttpPost httpPost,Map<String, String> paramsMap)throws BzException {
		if (paramsMap != null && paramsMap.size() > 0) {
			List<NameValuePair> nvps = new ArrayList<>();
			Set<String> keySet = paramsMap.keySet();
			for (String key : keySet) {
				nvps.add(new BasicNameValuePair(key, paramsMap.get(key)));
			}
			try {
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				logger.error("UrlEncoded转码失败", e);
				throw new BzException("UrlEncoded转码失败");
			}
		}
	}

	/**
	 * 设置http的HEAD
	 * @param httpPost
	 * @param headMap
	 */
	private static void setPostHead(HttpPost httpPost,Map<String, String> headMap) {
		if (headMap != null && headMap.size() > 0) {
			Set<String> keySet = headMap.keySet();
			for (String key : keySet) {
				httpPost.addHeader(key, headMap.get(key));
			}
		}
	}

	/**
	 * 将返回结果转化为String
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	private static String getRespString(HttpEntity entity) throws BzException {
		if (entity == null) {
			return null;
		}
		StringBuilder strBuf = new StringBuilder();
		InputStream is;
		try {
			is = entity.getContent();
			byte[] buffer = new byte[4096];
			int r = 0;
			while ((r = is.read(buffer)) > 0) {
				strBuf.append(new String(buffer, 0, r, "UTF-8"));
			}
		} catch (Exception e) {
			logger.error("读取Http返回结果异常",e);
			throw new BzException("读取Http返回结果异常");
		}
		return strBuf.toString();
	}
	
	/**
     * 发送post请求
     * 
     * @param url 请求地址
     * @param param 请求参数
     * @return 
     * @author YixinCapital -- chenyuan1
     *	       2016年10月24日 下午2:52:18
     */
    public static String sendPost(String url, String param,String application) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",  "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", application);

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
        	logger.error("发送 POST 请求出现异常！:{}",e);
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                logger.error("使用finally块来关闭输出流、输入流失败:{}",ex);
            }
        }
        return result;
    }

    /**
     * 发送post请求,带超时时间
     *
     * @param url 请求地址
     * @param param 请求参数
     * @return
     * @author YixinCapital -- chenyuan1
     *	       2016年10月24日 下午2:52:18
     */
    public static String sendPostWithTimeout(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",  "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 超时时间设置
            conn.setConnectTimeout(20000);
            conn.setReadTimeout(20000);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.error("发送 POST 请求出现异常！:{}",e);
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
                logger.error("使用finally块来关闭输出流、输入流失败:{}",ex);
            }
        }
        return result;
    }

    /**
     * https请求设置ssl
     *
     * @return
     * @author YixinCapital -- minliang
     *	       2016年11月29日 上午11:25:18
     */
    private static CloseableHttpClient createSSLClientDefault(){
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
                    // 全部信任
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (Exception e) {
            logger.error("创建httpclient异常",e);
        }
        return  HttpClients.createDefault();
    }

    /**
     * https发送post请求
     *
     * @param url 请求地址
     * @param param 请求参数
     * @return
     * @author YixinCapital -- minliang
     *	       2016年11月29日 上午11:25:18
     */
    public static String sendPostWithSSL(String url, String param) {
        HttpResponse response = null;
        HttpPost httpPost = null;
        HttpEntity resEntity = null;
        String result = "";
        try {
            CloseableHttpClient httpClient = HttpUtils.createSSLClientDefault();
            StringEntity se = new StringEntity(param);
            se.setContentType("text/json");
            se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
            httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.setEntity(se);
            response = httpClient.execute(httpPost);
            if(response != null){
                resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,"utf-8");
                }
            }
        } catch (Exception e) {
            logger.error("发送 POST 请求出现异常！:{}",e);
        }
        return result;
    }
}
