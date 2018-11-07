package com.yixin.dsc.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yixin.basemessage.dto.mail.YxMailMessage;
import com.yixin.common.system.util.BaseMessageUtil;
import com.yixin.kepler.enity.ConstantConfig;

import static org.hamcrest.CoreMatchers.containsString;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.id.CompositeNestedGeneratedValueGenerator.GenerationPlan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * 邮件发送工具类
 * @author YixinCapital -- chenjiacheng
 *         2018年08月21日 16:15
 **/

public class MailMessageUtils {

	private static final Logger logger = LoggerFactory.getLogger(MailMessageUtils.class);





	public static void sendException(Exception exception,String subject){
		
		String context = "ip为(%s),发生时间为(%s),执行发生异常，异常信息为(%s).";
		try {
			YxMailMessage mailMessage = new YxMailMessage();
			mailMessage.setMailSubject(subject);
			mailMessage.setMailText(
					String.format(context, getIp(),
							DateUtil.dateToString(new Date(), DateUtil.DEFAULT_TIMESTAMP_FORMAT_SAMLL_NEW),
							exception.getMessage() == null ? "空指针" : exception.getMessage()));
			sendMail(mailMessage);
		}catch (Exception e){
			logger.error("发送邮件异常，异常信息为",e);
		}
	}
	
/*	public static void main(String[] args) {
		sendException(new IllegalArgumentException("数据不能为null"), "");
	}*/
	
	
	



	private static String getIp() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			logger.error("获取本机ip异常",e);
		}
		return "";
	}








	/**
	 * 发送邮件
	 * @param mailMessage 邮件主体
	 * @return 发送结果
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/21 16:23
	 */
	public static String sendMail(YxMailMessage mailMessage){
		try {
			ConstantConfig config = ConstantConfig.findFirstByProperty(ConstantConfig.class, "key", "sendMailMessage");
			if (config == null) {
				logger.warn("未找到邮箱发送配置项，无法发送邮件。具体邮件信息：{}", JSON.toJSONString(mailMessage));
				return "fail";
			}
			JSONObject configJson = JSON.parseObject(config.getValueString());
			if ("1".equals(configJson.getString("switch"))) {
				mailMessage.setMailFrom(configJson.getString("sender"));
				mailMessage.setMailTo(configJson.getString("receiver"));
				if (mailMessage.getMailSubject().contains("####")) {
					mailMessage.setMailSubject(mailMessage.getMailSubject().replace("####", configJson.getString("environment")));
				}
				BaseMessageUtil.sendMail(mailMessage);
			}else {
				logger.info("邮件预警配置已关闭。具体邮件信息：{}", JSON.toJSONString(mailMessage));
			}
			return "success";
		} catch (Exception e) {
			logger.error("发送邮件失败", e);
			return "fail";
		}
	}



}
