package com.luckin;



import org.springframework.stereotype.Component;

/**
 * 消费者
 * 使用@KafkaListener注解,可以指定:主题,分区,消费组
 */
@Component
public class KafkaConsumerConfig {
//	@Autowired KafkaWebSocket kafkawebSocket;
//
//    @KafkaListener(topics = {"mb.park.in"})
//    public void receive(String message){
//    	try {
//			kafkawebSocket.sendInfo(message);
//			 System.out.println("test10001--消费消息:" + message);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//       
//    }
}
