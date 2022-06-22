package net.kosta.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosta.myapp.vo.UserVO;

@Controller
public class MyController2 {
	
	Logger logger = LoggerFactory.getLogger(MyController2.class);
	
	@RequestMapping("/spring/test1.do")
	public String method1() {
		
		return "day0622/test1";
	}
	
	@RequestMapping("/spring/test2.do")
	public String method2(String userid, String userpw, 
							@ModelAttribute UserVO user, 
							Model model) {
		
		logger.info(user.toString());
		logger.info(userid);
		logger.info(userpw);
		
		model.addAttribute("userid", userid);
		model.addAttribute("userpw", userpw);
		
		return "day0622/test2";
	}
}
