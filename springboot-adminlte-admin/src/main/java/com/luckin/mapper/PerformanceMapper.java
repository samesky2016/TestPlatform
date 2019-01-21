package com.luckin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.luckin.entity.Performance;

@Repository
public interface PerformanceMapper extends BaseMapper<Performance> {

	List<Map<Object, Object>> selectTestcaseList(Page<Map<Object, Object>> page, @Param("search") String search);
	List<Map<Object, Object>> selectOne(@Param("uri") String uri);
}