package com.bqjr.rocketmq.demo;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * 
 * @author jiangjb
 *
 */
@Component
public class msgProducerDemo {
	
	@Autowired
    private DefaultMQProducer defaultProducer;
    
    @Autowired
    private TransactionMQProducer transactionProducer;
    
    @Value("${spring.extend.rocketmq.producer.topic}")
	private String producerTopic;
    
    public void sendMsg() {
    	 Message msg = new Message(producerTopic,// topic
                 "TagA",// tag
                 "OrderID001",// key
                 ("Hello jyqlove333").getBytes());// body
         try {
			defaultProducer.send(msg,new SendCallback(){
				
				@Override
				public void onSuccess(SendResult sendResult) {
					 System.out.println(sendResult);
					 //TODO 发送成功处理
				}
				
				@Override
				public void onException(Throwable e) {
					 System.out.println(e);
					//TODO 发送失败处理
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public String sendTransactionMsg() {
    	SendResult sendResult = null;
    	try {
    		//构造消息
            Message msg = new Message(producerTopic,// topic
                    "TagA",// tag
                    "OrderID001",// key
                    ("Hello jyqlove333").getBytes());// body
            
            //发送事务消息，LocalTransactionExecute的executeLocalTransactionBranch方法中执行本地逻辑
            sendResult = transactionProducer.sendMessageInTransaction(msg, (Message msg1,Object arg) -> {
                int value = 1;
                
                //TODO 执行本地事务，改变value的值
                //===================================================
                System.out.println("执行本地事务。。。完成");
                if(arg instanceof Integer){
                	value = (Integer)arg;
                }
                //===================================================
                
                if (value == 0) {
                    throw new RuntimeException("Could not find db");
                } else if ((value % 5) == 0) {
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                } else if ((value % 4) == 0) {
                    return LocalTransactionState.COMMIT_MESSAGE;
                }
                return LocalTransactionState.ROLLBACK_MESSAGE;
            }, 4);
            System.out.println(sendResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult.toString();
    }
}
