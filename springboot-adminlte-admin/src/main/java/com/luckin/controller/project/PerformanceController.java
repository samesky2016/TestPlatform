package com.luckin.controller.project;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.luckin.common.anno.Log;
import com.luckin.common.bean.Rest;
import com.luckin.common.controller.SuperController;
import com.luckin.entity.Mock;
import com.luckin.entity.Performance;
import com.luckin.service.PerformanceService;
import com.luckin.service.ProjectService;

/**
 * 性能测试
 */
@Controller
@RequestMapping("/performance")
public class PerformanceController extends SuperController{


	@Autowired private ProjectService projectService;
	@Autowired private PerformanceService performanceService;
	/**
	 * 分页查询测试用例
	 */
    @RequestMapping("/list/{pageNumber}")
    public  String list(@PathVariable Integer pageNumber,@RequestParam(defaultValue="15") Integer pageSize,String search,Model model){
		if(StringUtils.isNotBlank(search)){
			model.addAttribute("search", search);
		}
    	Page<Map<Object, Object>> page = getPage(pageNumber,pageSize);
    	model.addAttribute("pageSize", pageSize);
    	Page<Map<Object, Object>> pageData = performanceService.selectTestcasePage(page, search);
    	model.addAttribute("pageData", pageData);
    	return "performance/list";
    } 
    /**
     * 新增性能指标
     */
    @RequestMapping("/add")
    public  String add(Model model){
    	model.addAttribute("projectList", projectService.selectList(null));
		return "performance/add";
    } 
    
    /**
     * 执行新增
     */
    @Log("新增用例")
    @RequestMapping("/doAdd")
    @ResponseBody
    public  Rest doAdd(Performance performance){
    	performanceService.insertPerformance(performance);
    	return Rest.ok();
    }
    /**
     * 删除用例
     */
    @Log("删除用例")
    @RequestMapping("/delete")
    @ResponseBody
    public  Rest delete(String id){
    	performanceService.delete(id);
    	return Rest.ok();
    }
    /**
     * 编辑用户
     */
    @RequestMapping("/caseresult/{id}")
    public  String caseresult(@PathVariable String id,Model model){
    	Performance performance = performanceService.selectById(id);
        model.addAttribute("performance",performance);
        model.addAttribute("projectList", projectService.selectList(null));
        return "performance/caseresult";
    }
    /**
	 * 编辑用户
	 */
    @RequestMapping("/edit/{id}")  
    public  String edit(@PathVariable String id,Model model){
    	Performance performance = performanceService.selectById(id);
    	model.addAttribute("performance",performance);

    	model.addAttribute("projectList", projectService.selectList(null));
    	return "performance/edit";
    } 
    /**
     * 执行编辑
     */
    @Log("编辑用户")
    @RequestMapping("/doEdit")
    @ResponseBody
    public  Rest doEdit(Performance performance){
    	performanceService.updatePerformance(performance);
    	return Rest.ok();
    } 
    
    /**
     * 验证用例名称是否已存在
     */
    @RequestMapping("/checkName")  
    @ResponseBody
    public Rest checkName(String Label){
    	List<Performance> list = performanceService.selectList(new EntityWrapper<Performance>().eq("Label", Label));
    	if(list.size() > 0){
    		return Rest.failure("用例已存在");
    	}
    	return Rest.ok();
    }
    
}
