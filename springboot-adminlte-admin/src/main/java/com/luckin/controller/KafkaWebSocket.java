package com.luckin.controller;




import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.kafka.common.utils.CopyOnWriteMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@ServerEndpoint(value = "/websocket/{param}")
@Component
public class KafkaWebSocket {
	protected static Logger logger = LoggerFactory.getLogger(KafkaWebSocket.class);
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteMap<String ,KafkaWebSocket> webSocketSet = new CopyOnWriteMap<String ,KafkaWebSocket>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(@PathParam(value="param") String param,Session session) {
        this.session = session;
        
        logger.info("----------------topic:"+param);
        webSocketSet.put(param+session.getId(), this);     //加入set中
        addOnlineCount();           //在线数加1
        logger.info("有新连接加入！当前在线人数为" + getOnlineCount());
        try {
            sendMessage("连接已建立~!");
        } catch (IOException e) {
        	logger.error("IO异常"+e.getMessage());
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam(value="param") String param,Session session) {
        webSocketSet.remove(param+session.getId());  //从set中删除
        subOnlineCount();           //在线数减1
        logger.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
    	logger.info("来自客户端的消息:" + message);
        
        //群发消息
        for (KafkaWebSocket item : webSocketSet.values()) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

  
    @OnError
    public void onError(Session session, Throwable error) {
    	logger.error("发生错误");
        error.printStackTrace();
    }


    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }


    public static void sendInfo(String topic,String message) throws IOException {
        for (String item : webSocketSet.keySet()) {
        	
            try {
            	if(item.contains(topic)){
            		webSocketSet.get(item).sendMessage(message);
            	}
                
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        KafkaWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        KafkaWebSocket.onlineCount--;
    }
    
    @KafkaListener(topics = {"mb.park.in"})
    public void receiveIn(String message){
    	try {
    		 sendInfo("mb.park.in",message);
    		 logger.info("test10001--消费消息:" + message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }
    @KafkaListener(topics = {"mb.park.out"})
    public void receiveoUt(String message){
    	try {
    		 sendInfo("mb.park.out",message);
    		 logger.info("test10001--消费消息:" + message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }
}