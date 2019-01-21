package com.luckin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.luckin.entity.Performance;
import com.luckin.mapper.PerformanceMapper;
import com.luckin.service.PerformanceService;


/**
 *
 * Testcase 表数据服务层接口实现类
 *
 */
@Service
public class PerformanceServiceImpl extends ServiceImpl<PerformanceMapper, Performance> implements PerformanceService {

	@Autowired private PerformanceMapper performanceMapper;

	@Override
	public void insertPerformance(Performance performance) {
		// TODO Auto-generated method stub
		performance.setCreateTime(new Date());
		//保存用户
		performanceMapper.insert(performance);
	}

	@Override
	public void updatePerformance(Performance performance) {
		// TODO Auto-generated method stub
		//更新用户
		performanceMapper.updateById(performance);
	}

	@Override
	public Page<Map<Object, Object>> selectTestcasePage(Page<Map<Object, Object>> page, String search) {
		// TODO Auto-generated method stub
		page.setRecords(baseMapper.selectTestcaseList(page, search));
		return page;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		this.deleteById(id);
	}

	@Override
	public List<Map<Object,Object>> query(String path) {
		// TODO Auto-generated method stub
		List<Map<Object,Object>> result=performanceMapper.selectOne(path);
		return result;
	}






}