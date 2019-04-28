package com.luckin.common.bean;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * 微信图文消息实体
 * @author sky [2019年4月26日 上午2:08:28]
 */
@Component
public class WeixinTagidListBean {
	
	public Map<String,List<String>> tagidList;

	public Map<String, List<String>> getTagidList() {
		return tagidList;
	}

	public void setTagidList(Map<String, List<String>> tagidList) {
		this.tagidList = tagidList;
	}
	
	
	
}
