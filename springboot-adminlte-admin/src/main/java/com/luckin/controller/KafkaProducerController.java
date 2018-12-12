package com.luckin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luckin.common.util.KafkaProducerUtil;
import com.luckin.service.MockService;

/**
 * Api调用控制器
 * 
 */
@Controller
@RequestMapping("/kafkaProducer")
public class KafkaProducerController {

	protected static Logger logger = LoggerFactory.getLogger(KafkaProducerController.class);

	@RequestMapping("/view")
	public String view() {

		return "kafka/producer";
	}

	@RequestMapping("/send")
	public void sendData(HttpServletRequest req, HttpServletResponse res) {

		try {
			req.setCharacterEncoding("utf-8");
			res.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String kafkaBroker = req.getParameter("kafkaBroker").trim();

		String topic = req.getParameter("topic").trim();

		String content = req.getParameter("content");

		KafkaProducerUtil kafka = new KafkaProducerUtil(kafkaBroker);
		try {
			kafka.sendData(topic, content, 1);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		PrintWriter out = null;
		try {
			Thread.sleep(1000);
			out = res.getWriter();
			if ("".equals(KafkaProducerUtil.result.toString())) {
				out.write("kafka is no response in one sec!");
			}else{
				out.write(KafkaProducerUtil.result.toString());
				
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			out.close();
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
