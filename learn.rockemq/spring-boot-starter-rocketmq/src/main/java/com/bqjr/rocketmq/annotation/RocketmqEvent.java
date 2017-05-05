package com.bqjr.rocketmq.annotation;

import java.io.UnsupportedEncodingException;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.context.ApplicationEvent;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author jiangjb
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class RocketmqEvent extends ApplicationEvent{
	private static final long serialVersionUID = -4468405250074063206L;
	
	private DefaultMQPushConsumer consumer;
	private MessageExt messageExt;
	private String topic;
	private String tag;
	private byte[] body;
	
	public RocketmqEvent(MessageExt msg,DefaultMQPushConsumer consumer) throws Exception {
		super(msg);
		this.topic = msg.getTopic();
		this.tag = msg.getTags();
		this.body = msg.getBody();
		this.consumer = consumer;
		this.messageExt = msg;
	}

	public String getMsg() {
		try {
			return new String(this.body,"utf-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	public String getMsg(String code) {
		try {
			return new String(this.body,code);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
}
