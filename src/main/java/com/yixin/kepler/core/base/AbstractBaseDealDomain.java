/**
 * 2016年12月6日
 * wangdianxiang
 */
package com.yixin.kepler.core.base;

import com.yixin.common.exception.BzException;
import com.yixin.common.utils.InvokeResult;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.dto.BaseMsgDTO;

/**
 * Package : com.yixin.creditfront.application.domain
 * 处理外部接口基础类
 * @author YixinCapital -- wangdianxiang
 *		   2016年12月6日 上午9:34:04
 *
 */
public abstract class AbstractBaseDealDomain<T, B extends BaseMsgDTO> implements IDealDomain<T,B> {

	/**
	 * 子类定义接口所需信息（按实际需求定义）
	 * @author YixinCapital -- wangdianxiang
	 *		   2016年12月5日 下午3:22:46
	 *
	 */
	//protected ? sendDto;
	
	/**
	 * 入参Dto
	 * @author YixinCapital -- wangdianxiang
	 *		   2016年12月20日 上午11:14:34
	 *
	 */
	protected ThreadLocal<T> inputDto = new ThreadLocal<>();
	//protected T inputDto;
	
	/**
	 *  获取BaseDTO信息
	 * @author YixinCapital -- wangdianxiang
	 *	       2016年12月5日 下午3:22:21
	 */
	protected abstract void getData() throws BzException;
	
	/**
	 *  将我方字典信息转换成银行接口对应信息
	 *  eg：男（M）-> (01)
	 * @author YixinCapital -- wangdianxiang
	 *	       2016年12月5日 下午3:23:49
	 */
	protected abstract void assembler() throws BzException;
	
	/**
	 *  发送信息到银行（调用接口）
	 * @author YixinCapital -- wangdianxiang
	 *	       2016年12月5日 下午3:33:17
	 */
	protected abstract InvokeResult<B> message() throws BzException;


	@Override
	public InvokeResult<B> doIt(T inputDTO) throws BzException{
		this.inputDto.set(inputDTO);
		this.getData();
		this.assembler();
		return this.message();
	}
	
	/**
	 * 银行请求前置
	 * @param applyNo 申请编号
	 * @param financeCode 资方编码
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年7月16日 下午7:18:17
	 */
	public BaseMsgDTO requestPrepose(String applyNo,String financeCode){
		return new BaseMsgDTO(CommonConstant.PreposeResultCode.NORMAL, "正常流转");
	};
	/**
	 * 银行请求前置
	 * @param applyNo 申请编号
	 * @param financeCode 资方编码
	 * @return
	 * @author YixinCapital -- wangwenlong
	 *	       2018年7月16日 下午7:18:17
	 */
	public BaseMsgDTO requestFristApply(String applyNo,String financeCode){
		return new BaseMsgDTO(CommonConstant.PreposeResultCode.SKIP, "跳转");
	};
}

