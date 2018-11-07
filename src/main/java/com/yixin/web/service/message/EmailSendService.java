package com.yixin.web.service.message;

import com.yixin.kepler.common.RestTemplateUtil;
import com.yixin.web.dto.message.MessageBodyDTO;
import com.yixin.web.dto.message.MessageInfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @date: 2018-09-03 15:04
 */
@Service
public class EmailSendService {

    private static final Logger logger = LoggerFactory.getLogger(EmailSendService.class);

    @Value("${spring.message.host}")
    private String messageHost;
    @Value("${spring.notice.mail.from}")
    private String mailFrom;
    @Value("${spring.subject_prefix}")
    private String subjectPrefix;


    /**
     * 发送邮件
     *
     * @return
     */
    public void sendEmail(String mailTo, String subject, String emailTemplate, Object[] values) {

        String url = messageHost + "/mc/yxMessage/send";

        logger.info("send email start , mail to:{}, subject:{}", mailTo, subject);

        try {
            MessageInfoDTO messageInfo = buildEmailData(mailTo, subject, emailTemplate, values);

            String msgRespJson = RestTemplateUtil.sendRequestNoBaffle(url, messageInfo);

            logger.info("send email end, mail to:{}, subject:{}, result:{}", mailTo, subject, msgRespJson);
        } catch (Exception e) {
            logger.error("send email error", e);
        }
    }


    /**
     * 组织邮件数据
     *
     * @param mailTo
     * @param subject
     * @param emailTemplate
     * @param values
     * @return
     */
    private MessageInfoDTO buildEmailData(String mailTo, String subject, String emailTemplate, Object... values) {
        MessageInfoDTO emailMsgInfo = new MessageInfoDTO();
        emailMsgInfo.setType(2);

        MessageBodyDTO messageBody = new MessageBodyDTO();
        emailMsgInfo.setData(messageBody);

        messageBody.setMailFrom(mailFrom);
        messageBody.setMailTo(mailTo);

        String mailText = String.format(emailTemplate, values);
        messageBody.setMailText(mailText);
        messageBody.setMailSubject(buildSubject(subject));

        return emailMsgInfo;
    }


    private String buildSubject(String subject) {
        return subjectPrefix + subject;
    }


}
