package com.luckin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Api调用控制器
 * 
 */
@Controller
@RequestMapping("/json")
public class MD5Controller {



	protected static Logger logger = LoggerFactory.getLogger(MD5Controller.class);

	
	@RequestMapping
	public String JsonFormat(Model model){
		return "json/jsonFormat";
	}

}
