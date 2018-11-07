package com.yixin.kepler.v1.utils;


import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.IOUtils;

public class AESUtils {

	public static byte[] AESEncrypt(byte[] data,byte[] key,String Mode) throws Exception
	{
		SecretKey aeskey=new SecretKeySpec(key, "AES");   
		Cipher cipher=Cipher.getInstance(Mode); //
	    cipher.init(Cipher.ENCRYPT_MODE, aeskey);
	    byte [] byte_AES=cipher.doFinal(data);
	    return byte_AES;
	}
	public static byte[] AESDecrypt(byte[] data,byte[] bKey, String Mode) throws Exception
	{
		byte[] AESDecryptRet;
		
		SecretKey key = new SecretKeySpec(bKey,"AES");
		Cipher cipher=Cipher.getInstance(Mode); 
		cipher.init(Cipher.DECRYPT_MODE, key);
		AESDecryptRet=cipher.doFinal(data);
		
		return AESDecryptRet;
	}

	public static void main(String[] args) throws Exception{
		//解密
		String key = "eBvuOBoXuoJvcQw7";
		//加密前的附件
		String file = "D:\\chen.lin/桌面/compress/MEDIA_MEDIAFILE_TG_20181015_001.zip";
		byte[] content = IOUtils.toByteArray(new FileInputStream(file));
		byte[] keyBytes = key.getBytes();

		byte[] afterContent = AESUtils.AESDecrypt(content, keyBytes,"AES/ECB/PKCS5Padding");
		String encryptFile = "D:\\chen.lin/桌面/compress/MEDIA_MEDIAFILE_TG_20181015_001_1.zip";
		IOUtils.write(afterContent, new FileOutputStream(encryptFile));
	}
	

}
