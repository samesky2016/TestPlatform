package com.luckin.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;


/**
 * <p>
 * 测试用例表
 * </p>
 *
 * @author wenbin.huang
 * @since 2018-11-01
 */
@Component
@TableName("mock")
public class Mock extends Model<Mock> {

    private static final long serialVersionUID = 1L;
    
    public static final int _0 = 0;
	public static final int _1 = 1;
    
    /**
     * 主键
     */
    @TableId(type=IdType.UUID)
	private String id;
    
    /**
     * 状态码
     */
	private String responseCode;
	
    /**
     * 用例名
     */
	private String mockName;
    /**
     * 接口地址
     */
	private String path;
    /**
     * 接口参数
     */
	private String responseParameter;
	/**
	 * 参数类型
	 */
	private String contenttype;


	private String projectId;
    /**
     * 创建时间
     */
	private Date createTime;

	/**
	 * 用例备注
	 */
	private String comment;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}





	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}



	public String getMockName() {
		return mockName;
	}

	public void setMockName(String mockName) {
		this.mockName = mockName;
	}

	public String getResponseParameter() {
		return responseParameter;
	}

	public void setResponseParameter(String responseParameter) {
		this.responseParameter = responseParameter;
	}

	public String getContenttype() {
		return contenttype;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

}
