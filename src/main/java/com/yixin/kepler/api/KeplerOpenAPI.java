package com.yixin.kepler.api;

import static com.yixin.kepler.core.constant.CommonConstant.FAILURE;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yixin.common.exception.BzException;
import com.yixin.common.utils.InvokeResult;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.core.domain.LoanDomain;
import com.yixin.kepler.core.service.FinancialService;
import com.yixin.kepler.core.service.PaymentService;
import com.yixin.kepler.core.service.RulerService;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.dto.BaseReqDTO;
import com.yixin.kepler.dto.ControlReqDTO;
import com.yixin.kepler.dto.PretrialDTO;
import com.yixin.kepler.dto.RespFinancialDTO;
import com.yixin.kepler.dto.RespMessageDTO;
import com.yixin.kepler.dto.StatusDTO;
import com.yixin.kepler.dto.webank.WBComputerRespDTO;

@Service
public class KeplerOpenAPI {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private FinancialService financialServiceImpl;
	
	@Resource
	private RulerService rulerServiceImpl;
	
	@Resource
	private PaymentService paymentServiceImpl;
	
	@Resource
	private LoanDomain loanDomain;
	
	
	/**
	 * 请款
	 * @param applyNo
	 * @return
	 */
	public RespMessageDTO<BaseMsgDTO> paymentRequest(String applyNo){
		
		
		
		if (StringUtils.isBlank(applyNo)) {
			return RespMessageDTO.getInstance(BaseMsgDTO.class)
					.hashErrors(true).errorMessage("请款单号为空");
		}
		
		boolean isExit = paymentServiceImpl.isExitByApplyNo(applyNo);
		if (!isExit) {
			return RespMessageDTO.getInstance(BaseMsgDTO.class)
					.hashErrors(true).errorMessage("请款订单号不存在");
		}
		
		try {
			BaseMsgDTO baseMsgDTO = paymentServiceImpl.paymentRequest(applyNo);
			
			if (CommonConstant.SUCCESS.equals(baseMsgDTO.getCode())) {
				return RespMessageDTO.getInstance(BaseMsgDTO.class).success()
						.data(baseMsgDTO);
			}else {
				return  RespMessageDTO.getInstance(BaseMsgDTO.class)
						.fail(baseMsgDTO.getMessage());
			}
		} catch (Exception e) {
			logger.error("订单号{}创建请款请求异常，异常信息为:",applyNo,e);
		}
		
		return RespMessageDTO.getInstance(BaseMsgDTO.class).fail("创建请款任务异常");
	}
	
	
	/**
	 * 获取全量准入规则
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public RespMessageDTO<List> getAccessRules(){
		
		
		try {
			List<Map<String, Object>> accessRules = rulerServiceImpl
					.getAccessRules();
			return RespMessageDTO.getInstance(List.class)
					.success().data(accessRules); 
			
		} catch (Exception e) {
			
			logger.error("获取全量准入规则接口异常",e);
		}
		
		return RespMessageDTO.getInstance(List.class)
				.fail("程序异常");
		
	}
	
	
	
	
	
	
	/**
	 * 根据资金方id获取准入条件、补充资料信息
	 * @param id
	 * @return
	 */
	public RespMessageDTO<RespFinancialDTO> getFinancialInfo(String id){
		
		if (StringUtils.isBlank(id)) {
			return RespMessageDTO.getInstance(RespFinancialDTO.class).fail("资方id为空");
		}
	
		//是否存在
		boolean isExit =  financialServiceImpl.isExist(id);
		if (!isExit) {
			return RespMessageDTO.getInstance(RespFinancialDTO.class).fail("资方id不存在");
		}
		//获取规则和信息
		try {
			RespFinancialDTO resultData = financialServiceImpl.getRuleAndInfo(id);
			return RespMessageDTO.getInstance(RespFinancialDTO.class)
					.success().data(resultData);
		} catch (Exception e) {
			logger.error("根据资方id获取准入条件和资料异常，异常信息为",e);
		}
		return RespMessageDTO.getInstance(RespFinancialDTO.class).fail("程序异常");
	}
	
	
	
	 /**
     * <B>功能简述</B><br>
     * 信审接口
     *
     * @param baseReqDTO 申请编号
     * @return
     * @author liuhongshen
     * @date 2018/6/7 上午9:51
     */
    public InvokeResult<BaseMsgDTO> loanApply(String applyNo) {

        if (StringUtils.isBlank(applyNo)) {
            return new InvokeResult<>(new BaseMsgDTO(FAILURE, "参数异常"))
                    .failure("参数异常");
        }
        try {
            return  loanDomain.loanApply(applyNo);
        }catch (BzException e) {
			logger.error("ApplyNo{} 发起信审异常BzException， ，错误信息:{}", applyNo, e.getMessage());
			return new InvokeResult<BaseMsgDTO>().failure(e.getMessage());
		} catch (Exception e) {
            logger.error("ApplyNo_{} 发起信审异常Exception， ", applyNo, e);
            return new InvokeResult<BaseMsgDTO>().failure("发起信审异常");
        }

    }

    /**
     * <B>功能简述</B><br>
     * 取消、提回、退回订单接口
     *
     * @param controlReqDTO 申请编号
     * @return
     * @author liuhongshen
     * @date 2018/6/7 上午10:22
     */
    public InvokeResult<BaseMsgDTO> loanControl(ControlReqDTO controlReqDTO) {
        if (controlReqDTO == null || StringUtils.isEmpty(controlReqDTO.getApplyNo())) {
            return new InvokeResult<>(new BaseMsgDTO(FAILURE, "参数异常"))
                    .failure("参数异常");
        }
        return loanDomain.loanControl(controlReqDTO.getApplyNo(), controlReqDTO.getCtype());
    }

    /**
     * <B>功能简述</B><br>
     * 同步贷后资料接口
     *
     * @param baseReqDTO 申请编号
     * @return
     * @date 2018/6/7 上午10:31
     * @author liuhongshen
     */
    public InvokeResult<BaseMsgDTO> syncLoanInfo(BaseReqDTO baseReqDTO) {
        return loanDomain.syncLoanInfo(baseReqDTO.getApplyNo());
    }


    /**
     * 根据申请编号和类型查询不通的状态，只返回最新的状态数据
     *
     * @param applyNo
     * @param type
     * @return
     */
    public InvokeResult<StatusDTO> loanStatus(String applyNo,String type) {

        if (StringUtils.isEmpty(applyNo) || StringUtils.isEmpty(type)) {
            return new InvokeResult<StatusDTO>().failure("参数错误");
        }
        try {
            return loanDomain.getStatus(applyNo, type);
        } catch (Exception e) {
            logger.error("ApplyNo_{} 查询订单状态异常： ", applyNo, e);
            return new InvokeResult<StatusDTO>().failure("查询订单状态异常");
        }

    }

    /**
     * 借款预审
     *
     * @param pretrialDTO
     * @return
     */
    public InvokeResult<BaseMsgDTO> pretrialRequest(PretrialDTO pretrialDTO) {
        return loanDomain.pretrialRequest(pretrialDTO);
    }


	/**
	 * 借据试算
	 * @param applyNo 申请编号
	 * @return 试算结果
	 * @author YixinCapital -- chenjiacheng
	 *             2018/7/6 10:26
	 */
	public InvokeResult<WBComputerRespDTO> computer(String applyNo, Map<String,Object> paramMap) {
		return loanDomain.computer(applyNo, paramMap);
	}

	
	/**
     * 免预审处理
     *
     * @param pretrialDTO
     * @return
     */
    public InvokeResult<BaseMsgDTO> noPretrialHandle(PretrialDTO pretrialDTO) {
        return loanDomain.noPretrialHandle(pretrialDTO);
    }



}
