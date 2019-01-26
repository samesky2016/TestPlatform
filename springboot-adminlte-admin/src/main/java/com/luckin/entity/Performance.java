package com.luckin.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * <p>
 * 测试用例表
 * </p>
 *
 * @author wenbin.huang
 * @since 2018-11-01
 */
@TableName("performance")
public class Performance extends Model<Performance> {

    private static final long serialVersionUID = 1L;
    
    public static final int _0 = 0;
	public static final int _1 = 1;
    
    /**
     * 主键
     */
    @TableId(type=IdType.UUID)
	private String id;

	private String Label;
	private String ThreadNum;
	
	public String getThreadNum() {
		return ThreadNum;
	}
	public void setThreadNum(String threadNum) {
		ThreadNum = threadNum;
	}
	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	private String Samples;

	private String Average;

	private String Median;

	private String Line90;


	private String Line95;

	private String Line99;

	private String projectId;
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	private String Min;
	private String Max;
	
	private String Error;
	private String Throughput;
	private String Received;
	private String Sent;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
	public String getSamples() {
		return Samples;
	}
	public void setSamples(String samples) {
		Samples = samples;
	}
	public String getAverage() {
		return Average;
	}
	public void setAverage(String average) {
		Average = average;
	}
	public String getMedian() {
		return Median;
	}
	public void setMedian(String median) {
		Median = median;
	}
	public String getLine90() {
		return Line90;
	}
	public void setLine90(String line90) {
		Line90 = line90;
	}
	public String getLine95() {
		return Line95;
	}
	public void setLine95(String line95) {
		Line95 = line95;
	}
	public String getLine99() {
		return Line99;
	}
	public void setLine99(String line99) {
		Line99 = line99;
	}
	public String getMin() {
		return Min;
	}
	public void setMin(String min) {
		Min = min;
	}
	public String getMax() {
		return Max;
	}
	public void setMax(String max) {
		Max = max;
	}
	public String getError() {
		return Error;
	}
	public void setError(String error) {
		Error = error;
	}
	public String getThroughput() {
		return Throughput;
	}
	public void setThroughput(String throughput) {
		Throughput = throughput;
	}
	public String getReceived() {
		return Received;
	}
	public void setReceived(String received) {
		Received = received;
	}
	public String getSent() {
		return Sent;
	}
	public void setSent(String sent) {
		Sent = sent;
	}
	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return id;
	}
	


}
