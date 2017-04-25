
# rabbitmq 生产者

重写spring rabbitmqtemple 模板方法

# 自动配置
   - 在Spring Boot中实现了RabbitMQ的自动配置，在配置文件中添加如下配置信息
    ```
    spring.rabbitmq.host=localhost  
    spring.rabbitmq.port=5672  
    spring.rabbitmq.username=test  
    spring.rabbitmq.password=test  
    spring.rabbitmq.virtualHost=test 
    ```
    后会自动创建ConnectionFactory以及RabbitTemplate对应Bean，为什么上面我们还需要手动什么呢?
    自动创建的ConnectionFactory无法完成事件的回调，即没有设置下面的代码
   
    ```
     connectionFactory.setPublisherConfirms(true);  

    ```

 #  参考
 http://dev.macero.es/2016/10/23/produce-and-consume-json-messages-with-spring-boot-amqp/
    