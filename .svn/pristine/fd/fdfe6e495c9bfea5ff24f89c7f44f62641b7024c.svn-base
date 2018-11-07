package com.yixin.kepler.core.job;/**
 * Created by liushuai2 on 2018/6/14.
 */

import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.common.system.job.JobSchedulerRabbit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Package : com.yixin.kepler.core.job
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月14日 13:44
 */
@Configuration
public class KeplerJobSchedulerRabbit {
    public static final Logger logger = LoggerFactory.getLogger(KeplerJobSchedulerRabbit.class);

    @Bean
    public JobSchedulerRabbit getJobSchedulerRabbit(
            @Value("${spring.rabbitmq.job.host}") String host,
            @Value("${spring.rabbitmq.job.username}") String userName,
            @Value("${spring.rabbitmq.job.password}") String password,
            @Value("${spring.rabbitmq.job.project}") String project,
            @Qualifier("taskExecutor") ThreadPoolExecutor taskExecutor
            ){
        logger.info("调度任务配置信息：{}", host);
        logger.info("开始配置定时调度中心连接客户端");
        JobSchedulerRabbit jobSchedulerRabbit = new JobSchedulerRabbit();
        jobSchedulerRabbit.setAddresses(host);
        jobSchedulerRabbit.setUsername(userName);
        jobSchedulerRabbit.setInitFlag(true);
        jobSchedulerRabbit.setPassword(password);
        jobSchedulerRabbit.setProjectName(project);


        return jobSchedulerRabbit;
    }


    @Bean(value = "jobExecutor")
    public ThreadPoolTaskExecutor threadPoolExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(2);
        threadPoolTaskExecutor.setMaxPoolSize(20);
        threadPoolTaskExecutor.setQueueCapacity(20);
        threadPoolTaskExecutor.setKeepAliveSeconds(2 * 3600);
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        return threadPoolTaskExecutor;
    }

    @Bean
    public SpringContextUtil springContextUtil() {
        return new SpringContextUtil();
    }
}
