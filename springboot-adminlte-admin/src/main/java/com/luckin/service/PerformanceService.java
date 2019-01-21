package com.luckin.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.luckin.entity.Performance;


/**
 *
 * testcase 表数据服务层接口
 *
 */
public interface PerformanceService extends IService<Performance> {
	
	/**
	 * 分页查询用例
	 */
	Page<Map<Object, Object>> selectTestcasePage(Page<Map<Object, Object>> page, String search);
	
	
	/**
	 * 保存用例
	 */
	void insertPerformance(Performance performance);
	/**
	 * 更新用例
	 */
	void updatePerformance(Performance performance);
	/**
	 * 删除用例
	 */
	void delete(String id);
	//查询用例是否存在
	
	/**
	 * 查询mock
	 */
	List<Map<Object, Object>> query(String path);

}