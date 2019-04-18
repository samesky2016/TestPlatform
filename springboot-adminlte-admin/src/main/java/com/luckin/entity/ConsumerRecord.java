package com.luckin.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * <p>
 * 项目表
 * </p>
 *
 * @author GaoJun.Zhou
 * @since 2017-03-06
 */

@TableName("consumer_records")
@Component
public class ConsumerRecord extends Model<ConsumerRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type=IdType.UUID)
	private String id;
    /**
     * itemId
     */
	private String itemId;

	/**
	 * topic
	 */
	private String topic;

    /**
     * 记录
     */
	private String content;

    /**
     * 创建时间
     */
	private Date createTime;
	

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getItemId() {
		return itemId;
	}


	public void setItemId(String itemId) {
		this.itemId = itemId;
	}


	public String getTopic() {
		return topic;
	}


	public void setTopic(String topic) {
		this.topic = topic;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
