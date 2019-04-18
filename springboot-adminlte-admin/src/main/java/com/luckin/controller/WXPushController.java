package com.luckin.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.luckin.common.util.DateUtils;
import com.luckin.common.util.OkHttpUtil;
import com.luckin.service.impl.TokenServiceImpl;

@Controller
@RequestMapping("/mockTest")
public class WXPushController {
	
	@Autowired
	private TokenServiceImpl tokenServiceImpl;
	/** 获取所有的关注用户*/
	public static final String SENDMSG_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
	protected static Logger logger = LoggerFactory.getLogger(WXPushController.class);
	
	@Value("${wechat.templateId}")
	private String templateId;
	
    @RequestMapping("/wxSendMsg")
    public  void sendMsg(HttpServletRequest req, HttpServletResponse res){
		try {
			req.setCharacterEncoding("utf-8");
			res.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 String reqParams=getReqAsBody(req);
		 
		 logger.info("---------------->>requestParams:"+reqParams);
 
		 
		 Map<String, String> reqMap = JSON.parseObject(reqParams, Map.class);
		 
    	 String token=tokenServiceImpl.getAccessToken();
    	 List<String> openids=tokenServiceImpl.getAllOpenId(token);
    	 
    	 String postUrl = SENDMSG_URL + token;
    	 

         JSONObject data = new JSONObject();
         JSONObject first = new JSONObject();
         first.put("value", reqMap.getOrDefault("title", "自动化测试报告"));
         first.put("color", "#173177");
         JSONObject keyword1 = new JSONObject();
         keyword1.put("value", reqMap.getOrDefault("projectName", "自动化测试"));
         keyword1.put("color", "#173177");
         JSONObject keyword2 = new JSONObject();
         keyword2.put("value", reqMap.getOrDefault("testStage", "回归测试阶段"));
         keyword2.put("color", "#173177");
         JSONObject keyword3 = new JSONObject();
         keyword3.put("value", reqMap.getOrDefault("testResult", "测试完成"));
         keyword3.put("color", "#173177");
         
         JSONObject keyword4 = new JSONObject();
         keyword4.put("value", DateUtils.getCurrentDateStr());
         keyword4.put("color", "#173177");
         JSONObject keyword5 = new JSONObject();
         keyword5.put("value", reqMap.getOrDefault("tester", "胡国彪"));
         keyword5.put("color", "#173177");
         JSONObject remark = new JSONObject();
         remark.put("value", "点击查看报告详情>>");
         remark.put("color", "#EE4000");
         
         data.put("first",first);
         data.put("keyword1",keyword1);
         data.put("keyword2",keyword2);
         data.put("keyword3",keyword3);
         data.put("keyword4",keyword4);
         data.put("keyword5",keyword5);
         data.put("remark",remark);
         
         long start=System.currentTimeMillis();
         
         for (Object openid:openids) {
             JSONObject jsonObject = new JSONObject();
             jsonObject.put("touser", openid);   // openid
             jsonObject.put("template_id", templateId);
             jsonObject.put("url", reqMap.getOrDefault("resultURL", "http://jsytc.jslife.net/jenkins/view/自动化测试/job/dataCenterAutoTest/HTML_20Report/index.html"));
             
             jsonObject.put("data", data);
      
             String resultStr = OkHttpUtil.postJson(postUrl, jsonObject.toJSONString());
             JSONObject result = JSON.parseObject(resultStr);
             int errcode = result.getIntValue("errcode");
             if(errcode == 0){
                 // 发送成功
            	
            	 logger.info("-------------------->>openId"+openid+":消息推送成功");
             } else {
                 // 发送失败
            	 logger.error("-------------------->>openId"+openid+":消息推送失败");
             }
         }
         long end =System.currentTimeMillis();
         StringBuffer resultSb = new StringBuffer("发送完成，共发送："); 
         resultSb.append(openids.size()).append("条").append(end-start).append("ms");
        
		 
		 try {
			 PrintWriter out = res.getWriter();
			 out.write(resultSb.toString());
			 out.flush();
			 out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

    }
    
 // 字符串读取
    // 方法一
    public  String getReqAsBody(HttpServletRequest request)
    {
 
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder("");
        try
        {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null)
            {
                sb.append(str);
            }
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != br)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
