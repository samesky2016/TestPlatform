package com.luckin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Api调用控制器
 * 
 */
@Controller
@RequestMapping("/kafkaConsumer")
public class KafkaConsumerController {

	protected static Logger logger = LoggerFactory.getLogger(KafkaConsumerController.class);

	@RequestMapping("/view")
	public String view() {

		return "kafka/consumer";
	}

	

}
