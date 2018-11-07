package com.yixin.dsc;

import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.common.system.job.JobExecutor;
import com.yixin.common.utils.InvokeResult;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.dsc.dto.DscSupplyDto;
import com.yixin.dsc.entity.order.DscFileAttachment;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.kepler.common.FileUtils;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.component.RedisDistributedLock;
import com.yixin.kepler.core.domain.AsyncTask;
import com.yixin.kepler.core.domain.CommonDomainUtil;
import com.yixin.kepler.core.domain.cmbc.LastTrialRequestStrategy;
import com.yixin.kepler.core.job.PaymentReqJobScheduler;
import com.yixin.kepler.core.listener.ContractSignEvent;
import com.yixin.kepler.core.listener.EmailSendEvent;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.dto.OsbAttachmentDTO;

import com.yixin.kepler.enity.AssetContractTask;
import com.yixin.kepler.enity.AssetPaymentTask;
import com.yixin.kepler.v1.common.core.compress.CompressHandle;
import com.yixin.kepler.v1.datapackage.dto.CompressHandleDTO;
import com.yixin.kepler.v1.job.YnContractJobScheduler;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import java.io.*;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Package : com.dsc.test.service.rule
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月13日 17:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class FileUploadTest {


    @Autowired
    private ApplicationContext ioc;

    @Autowired
    private AsyncTask asyncTask ;

    /**
     * 
     * @throws InterruptedException
     */


    @Test
	public void main8(){
    	ioc.publishEvent( new ContractSignEvent("9e338ac9f65a4764a7e0d6eb2ebebcbd"));

		try {
			TimeUnit.MINUTES.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

    @Test
	public void main7(){



		JobExecutor bean = ioc.getBean("dealMQMsgJobScheduler", JobExecutor.class);


		bean.execute(null);

		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}


    @Test
	public void main6(){

		CompressHandle handle = ioc.getBean("YNTRUSTCompressHandle", CompressHandle.class);

		CompressHandleDTO compressHandleDTO = new CompressHandleDTO();

		compressHandleDTO.setOsbFileLogId("402813a6668a22a501668a24330f0002");
		compressHandleDTO.setCompressTranNo("20181019102502020105");
		handle.handle(compressHandleDTO);

		System.out.println("sucess");

		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
    
    
    @Test
    public void main5(){

		List<AssetContractTask> currentTask = AssetContractTask.getCurrentTask(
				new Date(),12,10);



		JobExecutor bean = ioc.getBean("ynContractJobScheduler", JobExecutor.class);


		bean.execute(null);


		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


	}
    
    
    @Test
    public void main4(){
		try {
			String fileId = FileUtils.getBytesFromFileId(
                    "group1/M00/E6/35/wKiRIlvEbsaaZLSzAAZyN0LGxAc231.pdf");


			File file = new File("wKiRIlvEbsaaZLSzAAZyN0LGxAc231.pdf");
			FileOutputStream fileOutputStream = new FileOutputStream(file);

			IOUtils.write(Base64.getDecoder().decode(fileId.getBytes()),
                    fileOutputStream);
			System.out.println("成功");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
    







    @Test
    public void main3() throws InterruptedException{
		RedisDistributedLock redisDistributedLock = ioc.getBean(RedisDistributedLock.class);
    	String lockKey = "sukang";
    	for(int i = 0; i < 100 ; i++){
    		Boolean lock = redisDistributedLock.lock(lockKey,TimeUnit.SECONDS,5l);
    		TimeUnit.SECONDS.sleep(1l);
    		if (lock) {
    			System.out.println("--------" + i + "---------");
    			
    			//redisDistributedLock.unLock(lockKey);
			}else {
				System.out.println(lockKey+"获取失败");
			}
		}

    	TimeUnit.MINUTES.sleep(1l);	

    }



    @Test
    public void main2() throws InterruptedException{
    	try {


			OsbAttachmentDTO osbAttachmentDTO = new OsbAttachmentDTO();
			osbAttachmentDTO.setBzType(BankPhaseEnum.LAST_TRIAL.getPhase());
			osbAttachmentDTO.setBzId("411324");
			asyncTask.uploadAttachment(osbAttachmentDTO);

			//System.out.println(JsonObjectUtils.objectToJson(dscSupplyDtoInvokeResult));

			TimeUnit.MINUTES.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }



}
