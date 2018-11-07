package com.yixin.dsc.service.common;

import com.yixin.dsc.common.exception.BzException;
import com.yixin.dsc.dto.DscActionResultDTO;
import com.yixin.dsc.dto.DscComputerParamDto;
import com.yixin.dsc.dto.DscComputerResultDto;
import com.yixin.dsc.dto.DscElecCreditEvidenceDto;
import com.yixin.dsc.dto.DscElecSignDto;
import com.yixin.dsc.dto.DscReverseFlowForAlixDto;
import com.yixin.dsc.dto.DscSupplyDto;
import com.yixin.dsc.dto.http.DscKeplerDto;
import com.yixin.kepler.dto.PretrialDTO;

/**
 * 调用贷前/资管 服务
 * Package : com.yixin.dsc.common.service
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月6日 下午7:04:13
 *
 */
public interface DscKeplerCommonService {

	/**
	 *  获取订单各种类型的状态
	 * @param applyNo 订单编号
	 * @param type 类型
	 * @return
	 * @author YixinCapital -- wangwenlong
	 *	       2018年6月6日 下午7:46:42
	 */
	DscKeplerDto getApplyNoStatus(String applyNo,String type) throws BzException,Exception ;

	/**
	 * 确认订单各种状态是否完成
	 * @param applyNo 订单编号
	 * @param type 类型
	 * @return
	 * @author YixinCapital -- wangwenlong
	 *	       2018年6月6日 下午7:46:42
	 */
	Boolean confirmApplyNoStatus(String applyNo,String type);

    /**
     * 取消、提回、退回、拒绝订单
     */
    DscReverseFlowForAlixDto keplerControl(String applyNo, String ctype) throws BzException,Exception ;

    /**
     * 信审
     */
    DscActionResultDTO keplerApply(String applyNo) throws BzException,Exception ;

    /**
     * 请款
     */
    DscActionResultDTO keplerRequest(String applyNo) throws BzException,Exception ;


    /**
     * 预审
     * @param applyNo 订单编号
     * @param pretrialParam 预审入参
     * @return 
     * @author YixinCapital -- wangwenlong
     *	       2018年6月8日 下午6:08:28
     */
    DscKeplerDto keplerPretrial(String applyNo,PretrialDTO pretrialDTO) throws BzException,Exception ;
    
    
    /**
     * 根据资金方id获取补充信息
     * @param capitalId 资金方id
     * @return 
     * @author YixinCapital -- wangwenlong
     *	       2018年6月11日 下午4:52:18
     */
    DscSupplyDto keplerSupplyByCapitalId(String capitalId);



	void applyAttachmentTar(String applyNo);

	/**
	 * 保存电子征信存档
	 * @param creditDto
	 * @return
	 */
	Boolean saveElecCreditInfo(DscElecCreditEvidenceDto creditDto);


	/**
	 * 借据计算
	 * @param paramDto 申请编号
	 * @return 试算结果
	 * @author YixinCapital -- chenjiacheng
	 *             2018/7/6 9:48
	 */
	DscComputerResultDto keplerComputer(DscComputerParamDto paramDto);



	/**
	 * 保存电子合同信息
	 * @param signDto
	 * @return
	 */
	Boolean saveEleContractInfo(DscElecSignDto signDto);

    /**
     * 免预审处理
     * @param applyNo 订单编号
     * @param pretrialDTO 预审入参
     * @return 
     * @author YixinCapital -- wangwenlong
     *	       2018年7月12日 上午10:36:44
     */
    DscKeplerDto noPretrialHandle(String applyNo,PretrialDTO pretrialDTO);
    
    /**
     * 同步全量数据校验
     * @param applyNo 申请编号
     * @return 
     * @author YixinCapital -- wangwenlong
     *	       2018年7月19日 下午1:04:50
     */
    Boolean syncAllDataVerify(String applyNo);
}
