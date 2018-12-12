package com.luckin.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.luckin.entity.Mock;
import com.luckin.entity.Testcase;
import com.luckin.mapper.MockMapper;
import com.luckin.mapper.TestcaseMapper;
import com.luckin.service.MockService;
import com.luckin.service.TestcaseService;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * Testcase 表数据服务层接口实现类
 *
 */
@Service
public class MockServiceImpl extends ServiceImpl<MockMapper, Mock> implements MockService {

	@Autowired private MockMapper mockMapper;

	@Override
	public void insertMock(Mock mock) {
		// TODO Auto-generated method stub
		mock.setCreateTime(new Date());
		//保存用户
		mockMapper.insert(mock);
	}

	@Override
	public void updateMock(Mock mock) {
		// TODO Auto-generated method stub
		//更新用户
		mockMapper.updateById(mock);
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
		List<Map<Object,Object>> result=mockMapper.selectOne(path);
		return result;
	}




}