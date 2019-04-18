package com.luckin.common.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class WeixinUserListBean {
	

	private int total;
	private int count;
	private Map<String,List<String>> data; 
	public Map<String, List<String>> getData() {
		return data;
	}
	public void setData(Map<String, List<String>> data) {
		this.data = data;
	}
	private Date updateTime; // 更新时间;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	


}