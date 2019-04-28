package com.luckin.common.bean;

import java.util.List;



/**
 * 微信图文消息实体
 * @author sky [2019年4月26日 上午2:08:28]
 */
public class WeixinUserInfoBean {
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public List<String> getTagid_list() {
		return tagid_list;
	}
	public void setTagid_list(List<String> tagid_list) {
		this.tagid_list = tagid_list;
	}
	public String openid;
	
	public String nickname;
	
	public String groupid;
	public List<String> tagid_list;
}