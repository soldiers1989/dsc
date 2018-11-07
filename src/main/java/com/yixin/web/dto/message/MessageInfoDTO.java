package com.yixin.web.dto.message;

/**
 * @description:
 * @date: 2018-09-03 16:33
 */
public class MessageInfoDTO {

    /**
     * 消息内容
     */
    private MessageBodyDTO data;

    /**
     * 消息类型，2表示邮件
     */
    private Integer type;


    public MessageBodyDTO getData() {
        return data;
    }

    public void setData(MessageBodyDTO data) {
        this.data = data;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
