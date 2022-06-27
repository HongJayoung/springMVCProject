package org.kosta.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.dto.EmpVO;

@RestController //Controller + ResponseBody
@RequestMapping("/emp/*")
public class EmpRestController {

	Logger logger = LoggerFactory.getLogger(EmpRestController.class);
	
	@RequestMapping(value = "/empList.do", produces = "text/plain;charset=utf-8")
	public String empList() {
		return "직원조회!";
	}
	
	@RequestMapping(value = "/empDetail.do/{empid}/{message}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EmpVO empById(@PathVariable int empid, @PathVariable String message) {
		logger.info(empid+" "+message);
		EmpVO emp = new EmpVO(empid, "홍", "길동");
		//jackson이 자바의 객체를 JSON으로 만들어준다.
		return emp;
	}
}
