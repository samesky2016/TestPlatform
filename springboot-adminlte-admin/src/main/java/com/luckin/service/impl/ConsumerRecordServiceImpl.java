package com.luckin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.luckin.entity.ConsumerRecord;
import com.luckin.mapper.ConsumerRecordMapper;
import com.luckin.service.ConsumerRecordService;


/**
 *
 * Testcase 表数据服务层接口实现类
 *
 */
@Service
public class ConsumerRecordServiceImpl extends ServiceImpl<ConsumerRecordMapper, ConsumerRecord> implements ConsumerRecordService {

	@Autowired private ConsumerRecordMapper crm;

	@Override
	public void insertConsumerRecord(ConsumerRecord cr) {
		// TODO Auto-generated method stub
		cr.setCreateTime(new Date());
		//保存用户
		crm.insert(cr);
	}

	@Override
	public void updateConsumerRecord(ConsumerRecord cr) {
		// TODO Auto-generated method stub
		//更新用户
		crm.updateById(cr);
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
		List<Map<Object,Object>> result=crm.selectOne(path);
		return result;
	}




}