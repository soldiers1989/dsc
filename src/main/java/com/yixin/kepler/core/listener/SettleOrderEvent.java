package com.yixin.kepler.core.listener;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yixin.basemessage.dto.mail.YxMailMessage;
import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.dsc.util.MailMessageUtils;
import com.yixin.kepler.core.listener.settle.AbstractSettleOrder;
import com.yixin.kepler.enity.AssetMainInfo;
import com.yixin.kepler.v1.datapackage.dto.other.LoanInfoDTO;


/**
 * 推送结算数据事件
 *
 * @author sukang
 * @date 2018-06-26 16:33:50
 */
public class SettleOrderEvent extends AbstractEvent {


	private static final long serialVersionUID = -5844602526138612281L;

	private static final String SUFFIX = "SettleOrder";


	private Logger logger = LoggerFactory.getLogger(getClass());

	public SettleOrderEvent(Object source) {
		super(source);
		this.beanClass = getClass();
	}

	@Override
	public void execute(Object source) {

		if(source instanceof String){
			String applyNo = String.valueOf(source);
			logger.info("申请订单号为{},开始推送结算数据信息", applyNo);

			try {
				AssetMainInfo mainInfo = AssetMainInfo.getByApplyNo(applyNo);
				SpringContextUtil.getBean(
						mainInfo.getFinancialCode().concat(SUFFIX), AbstractSettleOrder.class)
						.sendSettleOrder(applyNo);
			} catch (Exception e) {
				try {
					logger.error("给结算推送数据出错,applyNo={}", applyNo, e);
					YxMailMessage mailMessage = new YxMailMessage();
					mailMessage.setMailSubject("####:订单【" + applyNo + "】推送结算数据失败，请关注");
					mailMessage.setMailText("订单【" + applyNo + "】于"
							+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
							+ "推送结算数据失败，异常信息："
							+ e.getMessage()
					);
					MailMessageUtils.sendMail(mailMessage);
				} catch (Exception e1) {
					logger.error("发送邮件失败，applyNo={}", applyNo, e1);
				}

			}
			logger.info("申请订单号为{},推送结算数据信息结束", applyNo);
			
			
		} else if (source instanceof LoanInfoDTO){
			LoanInfoDTO loanInfo = (LoanInfoDTO)source;
			logger.info("申请订单号为{},推送结算放款信息开始", loanInfo.getApplyNo());
			SpringContextUtil.getBean(loanInfo.getFinancialCode().concat(SUFFIX), AbstractSettleOrder.class).pushLoanInfo(loanInfo);
			logger.info("申请订单号为{},推送结算放款信息结束", loanInfo.getApplyNo());
		}
		
	}
}
