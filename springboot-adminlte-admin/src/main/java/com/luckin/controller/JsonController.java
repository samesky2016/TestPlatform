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
@RequestMapping("/md5")
public class JsonController {



	protected static Logger logger = LoggerFactory.getLogger(JsonController.class);

	
	@RequestMapping
	public String JsonFormat(Model model){
		return "md5/md5";
	}

}
