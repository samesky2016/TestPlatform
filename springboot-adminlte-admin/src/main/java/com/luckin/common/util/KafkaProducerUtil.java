package com.luckin.common.util;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaProducerUtil {
	public KafkaProducer producer;
	public static StringBuffer result ;
	public KafkaProducerUtil(String bootstrapSeverStr){
		result= new StringBuffer("");
		Properties props = new Properties();
		props.put("bootstrap.servers", bootstrapSeverStr);
		//props.put("retries", Integer.valueOf(0));
		props.put("linger.ms", Integer.valueOf(0));
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("partitioner.class", "com.luckin.common.util.MyPartition");
		props.put("acks", "1");
		props.put("retries", "3");
		props.put("timeout.ms", "10000");
		props.put("retry.backoff.ms", "100");
		producer = new KafkaProducer<String, String>(props);
		
	}
	
	public void sendData(String topic, String content, int index) throws InterruptedException, ExecutionException {
		
		ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, content);
		long startTime = System.currentTimeMillis();
		producer.send(record,new DemoCallBack(startTime,content));
		//tips.append("[" + DateUtils.getCurrDateTimeStr() + "]------->>send:" + index + ",topic:" + topic);
		System.out.println("[" + DateUtils.getCurrDateTimeStr() + "]-----send:" + index + ",topic:" + topic);
	}
	
	
	class DemoCallBack implements Callback{
	    private final long startTime;
	    private final String content;

	    public DemoCallBack(long startTime,String content) {
	        this.startTime = startTime;
	        this.content = content;

	    }
		@Override
		public void onCompletion(RecordMetadata recordMetadata, Exception exception) {
			// TODO Auto-generated method stub
			  if (recordMetadata != null) {

		            long elapsedTime = System.currentTimeMillis() - startTime;
//		            System.out.println("--------------->>消息发送完成内容："+ content + " ---->>send to partition("
//		                    + recordMetadata.partition() + ")," + "offset(" + recordMetadata.offset() + ") 耗时：" + elapsedTime);
		            result.append("\n").append("[" + DateUtils.getCurrDateTimeStr() + "]").append("------->>消息发送完成内容："+ content + "  send to partition("
		                    + recordMetadata.partition() + ")," + "offset(" + recordMetadata.offset() + ") 耗时：" + elapsedTime+"\n");
		            if(result.length()>5000){
		            	result=new StringBuffer("");
		            }
			  } else {
				  result.append("[" + DateUtils.getCurrDateTimeStr() + "]").append("------->>发送失败"+exception.getMessage()+exception.getStackTrace()).append("\n");
		            exception.printStackTrace();
		        }
		}
		
	}
}
