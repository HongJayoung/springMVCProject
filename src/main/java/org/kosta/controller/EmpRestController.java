package org.kosta.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.dto.EmpVO;
import com.kosta.dto.JobVO;
import com.kosta.myapp.model.EmpService;

@RestController //Controller + ResponseBody
@RequestMapping("/emp/*") //@RequestMapping => @GetMapping, @PostMapping, @PutMapping, @DeleteMapping
public class EmpRestController {

	@Autowired
	EmpService eservice;
	
	Logger logger = LoggerFactory.getLogger(EmpRestController.class);
	
	@GetMapping(value = "/22empList.do", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EmpVO> empList() {
		return eservice.selectAll();
	}
	
	@GetMapping(value = "/22empDetail.do/{empid}", produces = "application/json")
	public EmpVO empById(@PathVariable int empid) {
		return eservice.selectById(empid);
	}
	
	@GetMapping(value = "/22empByMgr.do/{mgrid}", produces = "application/json")
	public List<EmpVO> empByMgr(@PathVariable int mgrid) {
		return eservice.selectByManager(mgrid);
	}
	
	@GetMapping(value = "/22empByEmail.do/{email}", produces = "text/plain;charset=utf-8")
	public String empByEmail(@PathVariable String email) {
		return eservice.emailDup(email)+"건";
	}
	
	@GetMapping(value = "/22allMgr.do", produces = "application/json")
	public List<EmpVO> allMgr() {
		return eservice.selectAllMgr();
	}
	
	@GetMapping(value = "/22allJob.do", produces = "application/json")
	public List<JobVO> allJob() {
		return eservice.selectAllJob();
	}
	
	@GetMapping(value = "/22empByJob.do/{jobid}", produces = "application/json")
	public List<EmpVO> empByJob(@PathVariable String jobid) {
		return eservice.selectByJob(jobid);
	}
	
	@GetMapping(value = "/22empByDept.do/{deptid}", produces = "application/json")
	public List<EmpVO> empByDept(@PathVariable int deptid) {
		return eservice.selectByDept(deptid);
	}
	
	@GetMapping(value = "/22empByCondition.do/{deptid}/{jobid}/{sal}/{sdate}", produces = "application/json")
	public List<EmpVO> empByCondition(@PathVariable int deptid, @PathVariable String jobid, @PathVariable int sal, @PathVariable String sdate) {
		return eservice.selectByCondition(deptid, jobid, sal, sdate);
	}
	
	//입력 @PostMapping = @RequestMapping + method = RequestMethod.POST
	@PostMapping(value = "/22empInsert.do", consumes = "application/json")
	public int empInsert(@RequestBody EmpVO emp) {
		return eservice.empInsert(emp);
	}
	
	//수정
	@PutMapping(value = "/22empUpdate.do", consumes = "application/json")
	public int empUpdate(@RequestBody EmpVO emp) {
		return eservice.empUpdate(emp);
	}
	
	//삭제
	@DeleteMapping(value = "/22empDelete.do/{empid}")
	public int empDelete(@PathVariable int empid) {
		return eservice.empDelete(empid);
	}
}
