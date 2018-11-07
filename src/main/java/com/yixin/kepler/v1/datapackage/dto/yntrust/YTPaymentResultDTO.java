package com.yixin.kepler.v1.datapackage.dto.yntrust;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Lists;
import com.yixin.dsc.util.DateUtil;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.v1.datapackage.dto.other.LoanInfoDTO;

/**
 * 请款回调结果报文
 * Package : com.yixin.kepler.v1.datapackage.dto.yntrust
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年9月19日 下午3:53:52
 *
 */
public class YTPaymentResultDTO extends YTCommonResponseDTO {

	private static final long serialVersionUID = -5306576733521620107L;

	private String uniqueId;
	
	private List<PaymentDetail> paymentDetails;
	
	/**
	 * 获取银行交易流水号
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年10月15日 下午6:50:51
	 */
	public LoanInfoDTO getLoanInfo(){
		LoanInfoDTO loanInfo = null;
		if(CollectionUtils.isNotEmpty(paymentDetails)){
			/**
			 * 按照放款明细编号排序，最大的放在第一位，取放款明细编号最大的明细
			 */
			Collections.sort(paymentDetails, (detail1,detail2)-> {
					return Integer.valueOf(detail2.DetailNo) -  Integer.valueOf(detail1.DetailNo);
				}
			);
			PaymentDetail detail = paymentDetails.get(0);
			loanInfo = new LoanInfoDTO();
			loanInfo.setBankSeq(detail.getBankSerialNo()); //银行流水
			loanInfo.setLoanAmount(detail.getAmount()); //放款金额
			loanInfo.setLoanDime(detail.getActExcutedTime()); //实际放款成功时间
			//放款中=0,成功=1,失败=2,业务不执行=3,异常 =4
			loanInfo.setLoanSuccess(CommonConstant.ONE.equals(detail.getProcessStatus())); //是否放款成功
			loanInfo.setProcessStatus(detail.getProcessStatus());
		}
		return loanInfo;
	}
	
	public static void main(String[] args) {
		List<PaymentDetail> paymentDetails = Lists.newArrayList();
		
		
		System.out.println(JSON.toJSONString(paymentDetails));
	}
	
	
	static class PaymentDetail {
		/**
		 * 放款金额
		 */
		private	BigDecimal Amount;
		/**
		 * 放款账户号
		 */
		private	String AccountNo;
		/**
		 * 放款开户名
		 */
		private	String Name;
		/**
		 * 放款银行流水
		 */
		private	String BankSerialNo;
		/**
		 * 处理状态：成功=1,失败=2
		 */
		private	String ProcessStatus;
		/**
		 * 处理状态：成功=1,失败=2
		 */
		private	String Result;
		
		/**
		 * 实际放款成功时间 yyyy-MM-dd hh:mm:ss
		 */
		@JSONField(format=DateUtil.DEFAULT_DATE_TIME_FORMAT)
		private	Date ActExcutedTime;
		
		/**
		 * 放款明细编号
		 */
		private	String DetailNo;

		public BigDecimal getAmount() {
			return Amount;
		}

		public void setAmount(BigDecimal amount) {
			Amount = amount;
		}

		public String getAccountNo() {
			return AccountNo;
		}

		public void setAccountNo(String accountNo) {
			AccountNo = accountNo;
		}

		public String getName() {
			return Name;
		}

		public void setName(String name) {
			Name = name;
		}

		public String getBankSerialNo() {
			return BankSerialNo;
		}

		public void setBankSerialNo(String bankSerialNo) {
			BankSerialNo = bankSerialNo;
		}

		public String getProcessStatus() {
			return ProcessStatus;
		}

		public void setProcessStatus(String processStatus) {
			ProcessStatus = processStatus;
		}

		public String getResult() {
			return Result;
		}

		public void setResult(String result) {
			Result = result;
		}

		public Date getActExcutedTime() {
			return ActExcutedTime;
		}

		public void setActExcutedTime(Date actExcutedTime) {
			ActExcutedTime = actExcutedTime;
		}

		public String getDetailNo() {
			return DetailNo;
		}

		public void setDetailNo(String detailNo) {
			DetailNo = detailNo;
		}
	}


	public String getUniqueId() {
		return uniqueId;
	}


	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}


	public List<PaymentDetail> getPaymentDetails() {
		return paymentDetails;
	}


	public void setPaymentDetails(List<PaymentDetail> paymentDetails) {
		this.paymentDetails = paymentDetails;
	}
}
