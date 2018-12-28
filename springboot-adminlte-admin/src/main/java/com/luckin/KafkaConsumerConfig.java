package com.luckin;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.luckin.controller.KafkaWebSocket;

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
