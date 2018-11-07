package com.yixin.kepler.v1.service.capital.icbc;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yixin.common.exception.BzException;
import com.yixin.dsc.entity.order.DscSalesApplyContact;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.kepler.v1.datapackage.dto.icbc.ICBCFirstTrialDataDTO;

/**
 * 校验工行初审必输项
 * Package : com.yixin.kepler.v1.service.capital.icbc;
 * 
 * @author YixinCapital -- changyy
 *		   2017年8月9日 下午2:25:08
 *
 */
@Service
public class IcbcForCheck {
	private  final Logger logger = LoggerFactory.getLogger(IcbcForCheck.class);

    /**
	 * 校验发送工行初审必输项
	 * @param cfFirstTrialDTO 
	 * @author YixinCapital -- changyy
	 *	       2017年8月9日 下午2:26:58
	 */
	public  void check(ICBCFirstTrialDataDTO cfFirstTrialDTO,DscSalesApplyMain mainInfo){
		if (!StringUtils.hasText(cfFirstTrialDTO.getOrderno())) {
			logger.error("<<<<<业务订单号为空>>>>>");
			throw new BzException("<<<<<业务订单号为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getUrgentflag())) {
			logger.error("<<<<<紧急标示为空>>>>>");
			throw new BzException("<<<<<紧急标示为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getProducer())) {
			logger.error("<<<<<生产厂商为空>>>>>");
			throw new BzException("<<<<<生产厂商为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getCarbrand())) {
			logger.error("<<<<<汽车品牌为空>>>>>");
			throw new BzException("<<<<<汽车品牌为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getModel())) {
			logger.error("<<<<<款式规格为空>>>>>");
			throw new BzException("<<<<<款式规格为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getCarprice())) {
			logger.error("<<<<<车辆价格为空>>>>>");
			throw new BzException("<<<<<车辆价格为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getLoanamt())) {
			logger.error("<<<<<贷款金额（总分期金额）为空>>>>>");
			throw new BzException("<<<<<贷款金额（总分期金额）为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getOwnpayamt())) {
			logger.error("<<<<<首付金额为空>>>>>");
			throw new BzException("<<<<<首付金额为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getLoancen())) {
			logger.error("<<<<<贷款成数为空>>>>>");
			throw new BzException("<<<<<贷款成数为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getPlanterm())) {
			logger.error("<<<<<分期期数为空>>>>>");
			throw new BzException("<<<<<分期期数为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getCustsort())) {
			logger.error("<<<<<证件类型为空>>>>>");
			throw new BzException("<<<<<证件类型为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getCustcode())) {
			logger.error("<<<<<证件号码为空>>>>>");
			throw new BzException("<<<<<证件号码为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getSex())) {
			logger.error("<<<<<性别为空>>>>>");
			throw new BzException("<<<<<性别为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getChnsname())) {
			logger.error("<<<<<姓名为空>>>>>");
			throw new BzException("<<<<<姓名为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getMrtlstat())) {
			logger.error("<<<<<婚姻状况为空>>>>>");
			throw new BzException("<<<<<婚姻状况为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getEdulvl())) {
			logger.error("<<<<<受教育程度为空>>>>>");
			throw new BzException("<<<<<受教育程度为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getHomestat())) {
			logger.error("<<<<<住宅状况为空>>>>>");
			throw new BzException("<<<<<住宅状况为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getHprovince())) {
			logger.error("<<<<<住宅地址省份为空>>>>>");
			throw new BzException("<<<<<住宅地址省份为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getHcity())) {
			logger.error("<<<<<住宅地址市为空>>>>>");
			throw new BzException("<<<<<住宅地址市为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getHaddress())) {
			logger.error("<<<<<住宅地址为空>>>>>");
			throw new BzException("<<<<<住宅地址为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getMvblno())) {
			logger.error("<<<<<手机号码为空>>>>>");
			throw new BzException("<<<<<手机号码为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getUnitname())) {
			logger.error("<<<<<工作单位名称为空>>>>>");
			throw new BzException("<<<<<工作单位名称为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getDuty())) {
			logger.error("<<<<<职务为空>>>>>");
			throw new BzException("<<<<<职务为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getCprovince())) {
			logger.error("<<<<<单位地址省份为空>>>>>");
			throw new BzException("<<<<<单位地址省份为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getCcity())) {
			logger.error("<<<<<单位地址市为空>>>>>");
			throw new BzException("<<<<<单位地址市为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getCaddress())) {
			logger.error("<<<<<工作单位地址为空>>>>>");
			throw new BzException("<<<<<工作单位地址为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getYearincome())) {
			logger.error("<<<<<本人年收入为空>>>>>");
			throw new BzException("<<<<<本人年收入为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getModelcode())) {
			logger.error("<<<<<单位性质为空>>>>>");
			throw new BzException("<<<<<单位性质为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getOccptn())) {
			logger.error("<<<<<职业及职级为空>>>>>");
			throw new BzException("<<<<<职业及职级为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getReltname1())) {
			logger.error("<<<<<联系人一姓名为空>>>>>");
			throw new BzException("<<<<<联系人一姓名为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getReltship1())) {
			logger.error("<<<<<联系人一与主卡联系人关系为空>>>>>");
			throw new BzException("<<<<<联系人一与主卡联系人关系为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getReltmobl1())) {
			logger.error("<<<<<联系人一手机为空>>>>>");
			throw new BzException("<<<<<联系人一手机为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getReltname2())) {
			logger.error("<<<<<联系人二姓名>>>>>");
			throw new BzException("<<<<<联系人二姓名为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getReltship2())) {
			logger.error("<<<<<联系人二与主卡联系人关系为空>>>>>");
			throw new BzException("<<<<<联系人二与主卡联系人关系为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getReltmobl2())) {
			logger.error("<<<<<联系人二手机为空>>>>>");
			throw new BzException("<<<<<联系人二手机为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getPrimnat())) {
			logger.error("<<<<<国籍为空>>>>>");
			throw new BzException("<<<<<国籍为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getMonthincome())) {
			logger.error("<<<<<还款人月均总收入为空>>>>>");
			throw new BzException("<<<<<还款人月均总收入为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getServamt())) {
			logger.error("<<<<<服务费为空>>>>>");
			throw new BzException("<<<<<服务费为空>>>>>");
		}
		if (!StringUtils.hasText(cfFirstTrialDTO.getAllamt())) {
			logger.error("<<<<<总金额为空>>>>>");
			throw new BzException("<<<<<总金额为空>>>>>");
		}
		// 二手车新车校验
		String businessType = cfFirstTrialDTO.getBusinesstype();
		logger.info("[工行初审]业务类型 businessType={}", businessType);

		String applyNo = cfFirstTrialDTO.getOrderno();
		 BigDecimal amount= new BigDecimal(200000.00);

		List<DscSalesApplyContact> contactList = DscSalesApplyContact.getListByParms(mainInfo.getId());
		 if(new BigDecimal(cfFirstTrialDTO.getAllamt()).compareTo(amount)==1){
			 if("2".equals(cfFirstTrialDTO.getMrtlstat())){ //已婚,传配偶
				if (!StringUtils.hasText(cfFirstTrialDTO.getChnsname1())) {
					logger.error("<<<<<姓名为空>>>>>(配偶)");
					throw new BzException("<<<<<姓名为空>>>>>(配偶)");
				}
				if (!StringUtils.hasText(cfFirstTrialDTO.getRelation1())) {
					logger.error("<<<<<与申请人关系为空>>>>>(配偶)");
					throw new BzException("<<<<<与申请人关系为空>>>>>(配偶)");
				}
				if (!StringUtils.hasText(cfFirstTrialDTO.getCustsort1())) {
					logger.error("<<<<<证件类型为空>>>>>(配偶)");
					throw new BzException("<<<<< 证件类型为空>>>>>(配偶)");
				}
				if (!StringUtils.hasText(cfFirstTrialDTO.getCustcode1())) {
					logger.error("<<<<<证件号码为空>>>>>(配偶)");
					throw new BzException("<<<<< 证件号码为空>>>>>(配偶)");
				}
				if (!StringUtils.hasText(cfFirstTrialDTO.getMobile1())) {
					logger.error("<<<<<联系电话为空>>>>>(配偶)");
					throw new BzException("<<<<< 联系电话为空>>>>>(配偶)");
				}
				if (!StringUtils.hasText(cfFirstTrialDTO.getBirthdate1())) {
					logger.error("<<<<<出生日期为空>>>>>(配偶)");
					throw new BzException("<<<<< 出生日期为空>>>>>(配偶)");
				}
				if (!StringUtils.hasText(cfFirstTrialDTO.getEthnic1())) {
					logger.error("<<<<<民族为空>>>>>(配偶)");
					throw new BzException("<<<<< 民族为空>>>>>(配偶)");
				}
				if (!StringUtils.hasText(cfFirstTrialDTO.getEducation1())) {
					logger.error("<<<<<教育程度为空>>>>>(配偶)");
					throw new BzException("<<<<< 教育程度为空>>>>>(配偶)");
				}
				if (!StringUtils.hasText(cfFirstTrialDTO.getAddress1())) {
					logger.error("<<<<<居住地址为空>>>>>(配偶)");
					throw new BzException("<<<<< 居住地址为空>>>>>(配偶)");
				}
				if (!StringUtils.hasText(cfFirstTrialDTO.getLivcondition1())) {
					logger.error("<<<<<居住状况为空>>>>>(配偶)");
					throw new BzException("<<<<< 居住状况为空>>>>>(配偶)");
				}
				if (!StringUtils.hasText(cfFirstTrialDTO.getRegaddr1())) {
					logger.error("<<<<<户籍地址为空>>>>>(配偶)");
					throw new BzException("<<<<< 户籍地址为空>>>>>(配偶)");
				}
				if (!StringUtils.hasText(cfFirstTrialDTO.getCompany1())) {
					logger.error("<<<<<工作单位名称为空>>>>>(配偶)");
					throw new BzException("<<<<< 工作单位名称为空>>>>>(配偶)");
				}
				if (!StringUtils.hasText(cfFirstTrialDTO.getComptype1())) {
					logger.error("<<<<<工作单位类型为空>>>>>(配偶)");
					throw new BzException("<<<<< 工作单位类型为空>>>>>(配偶)");
				}
				if (!StringUtils.hasText(cfFirstTrialDTO.getWorkyears1())) {
					logger.error("<<<<<现单位工作年限为空>>>>>(配偶)");
					throw new BzException("<<<<< 现单位工作年限为空>>>>>(配偶)");
				}
			}
		}
		 if (!StringUtils.hasText(cfFirstTrialDTO.getCitycode())) {
				logger.error("<<<<<进件城市为空>>>>>");
				throw new BzException("<<<<<进件城市为空>>>>>");
			}
		 if (!StringUtils.hasText(cfFirstTrialDTO.getYxstatus())) {
				logger.error("<<<<< 易鑫筛查结果为空>>>>>");
				throw new BzException("<<<<< 易鑫筛查结果为空>>>>>");
			}
		 if (!StringUtils.hasText(cfFirstTrialDTO.getShopid())) {
				logger.error("<<<<<4S车商代码为空>>>>>");
				throw new BzException("<<<<<4S车商代码为空>>>>>");
			}
		 if (!StringUtils.hasText(cfFirstTrialDTO.getShopname())) {
				logger.error("<<<<<4S车商名称为空>>>>>");
				throw new BzException("<<<<<4S车商名称为空>>>>>");
			}
		 if (!StringUtils.hasText(cfFirstTrialDTO.getFiletrans())) {
				logger.error("<<<<<文件传输标志为空>>>>>");
				throw new BzException("<<<<<文件传输标志为空>>>>>");
			}
		 if (!StringUtils.hasText(cfFirstTrialDTO.getFilename())) {
				logger.error("<<<<<打包文件名为空>>>>>");
				throw new BzException("<<<<<打包文件名为空>>>>>");
			}
		 if (!StringUtils.hasText(cfFirstTrialDTO.getFilepasswd())) {
				logger.error("<<<<<文件加密密码为空>>>>>");
				throw new BzException("<<<<<文件加密密码为空>>>>>");
			}
		 if (!StringUtils.hasText(cfFirstTrialDTO.getFilesize())) {
				logger.error("<<<<<文件字节数为空>>>>>");
				throw new BzException("<<<<<文件字节数为空>>>>>");
			}
	}

}

