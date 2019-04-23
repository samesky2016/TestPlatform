package com.luckin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.luckin.common.bean.WeixinTokenBean;
import com.luckin.common.bean.WeixinUserListBean;
import com.luckin.common.util.JsonUtil;
import com.luckin.common.util.OkHttpUtil;

/**
 * 微信通用access_token
 * @author YangJie [2016年5月3日 下午5:40:57]
 */
@Service
public class TokenServiceImpl {
	
	/** 缓存Token信息 */
	private WeixinTokenBean tokenBean;
	
	/** 缓存user List信息 */
	private WeixinUserListBean userListBean;
	
	private Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);
	
	/** 获取access_token地址 */
	public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
	/** 获取所有的关注用户*/
	public static final String OPENID_URL = "https://api.weixin.qq.com/cgi-bin/user/get";
	
	@Value("${wechat.appid}")
	private String appid;
	@Value("${wechat.secret}")
	private String secret;
	
	
	/**
	 * 获取accessToken
	 * @author YangJie [2016年5月4日 下午3:37:50]
	 * @return
	 */
	public String getAccessToken() {
		// 判断当前token是否在有效期内

		if (tokenBean!=null && tokenBean.getAccessToken()!=null) { 
			if((System.currentTimeMillis()-tokenBean.getUpdateTime().getTime())/1000 < (tokenBean.getExpiresIn()-600)){
				logger.debug("返回有效期内的access_token: {}", tokenBean.getAccessToken());
				return tokenBean.getAccessToken();
			}
		}
		// 如果没有token信息或者已经过期, 重新从api获取
		StringBuilder urlBuilder = new StringBuilder(ACCESS_TOKEN_URL);
		urlBuilder.append("?appid=").append(appid);
		urlBuilder.append("&secret=").append(secret);
		urlBuilder.append("&grant_type=").append("client_credential");
		logger.info("获取access_token请求地址: {}", urlBuilder);
		String result = OkHttpUtil.get(urlBuilder.toString());
		logger.info("获取access_token返回数据: {}", result);
		tokenBean = JsonUtil.toObject(result, WeixinTokenBean.class);
		if (tokenBean!=null && tokenBean.getAccessToken()!=null) {
			tokenBean.setUpdateTime(new Date());
			logger.debug("返回新获取的access_token: {}", tokenBean.getAccessToken());
			return tokenBean.getAccessToken();
		}
		logger.error("获取access_token信息失败!, 返回null");
		return null;
	}
	
	public List<String> getAllOpenId(String token) {
		
		// 判断当前userList是否在有效期内
		if (userListBean!=null && userListBean.getData()!=null) { 
			if((System.currentTimeMillis()-userListBean.getUpdateTime().getTime())/1000 < (tokenBean.getExpiresIn()-600)){
				logger.debug("返回用户列表: {}", userListBean.getData().get("openid"));
				return  userListBean.getData().get("openid");
			}
		}
		
		StringBuilder urlBuilder = new StringBuilder(OPENID_URL);
		urlBuilder.append("?access_token=").append(token);
		urlBuilder.append("&next_openid=");
		logger.debug("获取getAllOpenId请求地址: {}", urlBuilder);
		String result = OkHttpUtil.get(urlBuilder.toString());
		logger.debug("获取getAllOpenId返回数据: {}", result);
	
		userListBean  = JsonUtil.toObject(result, WeixinUserListBean.class);
		
		if (userListBean!=null && userListBean.getData()!=null) {
			userListBean.setUpdateTime(new Date());
			logger.debug("返回新获取的openid: {}", userListBean.getData().get("openid"));
			return userListBean.getData().get("openid");
		}
		
		logger.error("获取openid信息失败!, 返回null");
		return null;
		
		
	}
}
