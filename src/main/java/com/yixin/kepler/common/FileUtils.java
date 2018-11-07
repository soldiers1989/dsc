package com.yixin.kepler.common;

import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.dsc.util.PropertiesManager;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;

/**
 * 文件工具类
 * @author YixinCapital -- chenjiacheng
 *         2018年05月04日 15:24
 **/

public class FileUtils {

	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

	private final static double scale = 1;

	/**
	 * 最大值300Kb
	 */
	public final static long MAX_LENGTH = 500;


	/**
	 * 根据fileId获取文件的字节数组
	 * @param fileId fileId
	 * @return 文件的字节数组
	 */
	public static String getBytesFromFileId(String fileId) {

		String downLoadUrl = getDownLoadUrl();

		logger.info("fileId：{}读取文件字节请求路径为{}",fileId,downLoadUrl);
		JSONObject result = JSONObject.fromObject(RestUtil.postJson(downLoadUrl, fileId, JSONObject.class));
		String data = null;
		if (result.containsKey("data")){
			data = result.getString("data");
		}
		Assert.notNull(data,fileId+"获取到的文件数组为空");
		logger.info("fileId:{}读取文件字节获取到的文件数据长度为{}",fileId,data.length());
		return data;
	}



	public static String getFileStoreUrl(String fileId){
		PropertiesManager propertiesManager = SpringContextUtil.getBean(PropertiesManager.class);
		String filestoreUrl = propertiesManager.getFilestoreUrl();

		filestoreUrl = filestoreUrl.endsWith("/") ? filestoreUrl :filestoreUrl.concat("/");
		return filestoreUrl.concat(fileId);
	}


	public static String  getDownLoadUrl(){
		PropertiesManager propertiesManager = SpringContextUtil.getBean(PropertiesManager.class);
		String downloadUrl = propertiesManager.getFileServerUrlUpload();
		downloadUrl = downloadUrl.replace("downloadFileByUrl","download");
		return downloadUrl;
	}

	/**
	 * 读取http文件流
	 */
	public static InputStream getInputStreamByUrl(String url) throws Exception{
		URLConnection urlConnection = new URL(url).openConnection();
		return urlConnection.getInputStream();
	}



	public static byte[] getCompressBytesByUrl(String url) throws Exception{
		InputStream inputStream = getInputStreamByUrl(url);
		byte[] bytes = IOUtils.toByteArray(inputStream);
		return compress(bytes,scale,MAX_LENGTH);
	}

	/**
	 * 压缩到指定大小
	 *
	 */
	public static  byte[] compress(byte[] bytes,double scale,long maxLength) throws Exception{
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		do {

			BufferedImage read = ImageIO.read(byteArrayInputStream);

			int width = (int)(read.getWidth() * scale);
			int height =(int)(read.getHeight() * scale);

			BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(read.getScaledInstance(width, height,
				Image.SCALE_SMOOTH), 0, 0, null);

			ImageIO.write(tag, "JPEG", byteArrayOutputStream);

			byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
		}while (byteArrayInputStream.available() >= maxLength);

		return byteArrayOutputStream.toByteArray();
	}


	/**
	 * 字节数组转文件
	 * @param bfile 字节数组
	 * @param filePath 文件路径
	 * @param fileName 生成的文件名称
	 * @author YixinCapital -- chenjiacheng
	 *             2018/5/4 15:25
	 */
	public static File byte2File(byte[] bfile,String filePath,String fileName){
		BufferedOutputStream bos=null;
		FileOutputStream fos=null;
		File file=null;
		try{
			File dir=new File(filePath);
			//判断文件目录是否存在
			if(!dir.exists() && !dir.isDirectory()){
				dir.mkdirs();
			}
			file=new File(filePath + fileName);
			fos=new FileOutputStream(file);
			bos=new BufferedOutputStream(fos);
			bos.write(bfile);
		}catch(Exception e){
			logger.error("转换文件异常", e);
		} finally{
			try{
				if(bos != null){
					bos.close();
				}
				if(fos != null){
					fos.close();
				}
			}
			catch(Exception e){

			}
		}
		return file;
	}


	/**
	 * 获取一个文件的md5值(可处理大文件)
	 *
	 * @return md5 value
	 */
	public static String getMD5(InputStream inputStream) {
		try {
			MessageDigest MD5 = MessageDigest.getInstance("MD5");
			byte[] buffer = new byte[2048];
			int length;
			while ((length = inputStream.read(buffer)) != -1) {
				MD5.update(buffer, 0, length);
			}
			return new String(Hex.encodeHex(MD5.digest()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
