package com.yixin.kepler.core.listener;

/**
 * @author sukang
 */
public class EmailSendEvent extends AbstractEvent{

	private static final long serialVersionUID = 1L;

	public EmailSendEvent(Object source) {
        super(source);
        super.beanClass = getClass();

    }

    @Override
    protected void execute(Object source) {
        //发送邮件
    	

    }
}
