package com.bqjr.rocketmq.demo;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.bqjr.rocketmq.annotation.RocketmqEvent;

/**
 * 
 * @author jiangjb
 *
 */
@Component
public class msgConsumerDemo {
	
	@EventListener(condition = "#event.topic=='TopicTest1' && #event.tag=='TagA'")
	public void rocketmqMsgListen(RocketmqEvent event) {
		DefaultMQPushConsumer consumer = event.getConsumer();
		try {
			System.out.println("com.bqjr.rocketmq.demo.msgConsumerDemo监听到一个消息达到：" + event.getMsg("gbk"));
			//TODO 进行业务处理
		} catch (Exception e) {
			if(event.getMessageExt().getReconsumeTimes()<=3){//重复消费3次
				try {
					consumer.sendMessageBack(event.getMessageExt(), 2);
				} catch (Exception e1) {
					//TODO 消息消费失败，进行日志记录
				}
			} else {
				//TODO 消息消费失败，进行日志记录
				
			}
		}
	}
}
