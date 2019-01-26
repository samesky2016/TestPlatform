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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.luckin.service.MockService;

/**
 * Api调用控制器
 * 
 */
@Controller
@RequestMapping("/mockTest")
public class ApiController {

	@Autowired
	private MockService mockService;

	protected static Logger logger = LoggerFactory.getLogger(ApiController.class);

	// api调用进入到指定的过滤器
	@RequestMapping("/**")
	public void doMock(HttpServletRequest req, HttpServletResponse res) {

		try {
			req.setCharacterEncoding("utf-8");
			res.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String uri = req.getRequestURI();
		
		logger.info("------------------->>uri:" + uri);
		logger.info("------------------->>request:"+ReadAsChars(req) );

		String result = "";
		Map<Object, Object> resultMap = queryReponsParam(uri);
		if (resultMap != null) {
			int code = Integer.valueOf(resultMap.get("responseCode").toString());

			res.setStatus(code);
			if (code == 200)
				result = resultMap.get("responseParameter").toString();

		} else {

			result = "response is not set,please set response values!";
		}

		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.write(result);
			out.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("------------------->>" + e.getMessage());
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

	public Map<Object, Object> queryReponsParam(String uri) {
		List<Map<Object, Object>> result = mockService.query(uri);
		if (result != null && result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}
	
	// 字符串读取
    // 方法一
    public static String ReadAsChars(HttpServletRequest request)
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
