package org.kosta.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kosta.dto.EmpVO;
import com.kosta.myapp.model.DeptService;
import com.kosta.myapp.model.EmpService;

@Controller
@RequestMapping("/emp")
public class EmpController {

	Logger logger = LoggerFactory.getLogger(EmpController.class);
	
	@Autowired
	EmpService eservice;
	@Autowired
	DeptService dservice;
	
	//직원목록조회
	@RequestMapping("/empList.do")
	public void empList(Model model, HttpServletRequest request) {
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		
		if(flashMap !=null) {
			String resultMsg = (String)flashMap.get("resultMsg");
			model.addAttribute("resultMsg", resultMsg);
		}
		
		model.addAttribute("mgrlist", eservice.selectAllMgr());
		model.addAttribute("jlist", eservice.selectAllJob());
		model.addAttribute("dlist", dservice.selectAll());
		model.addAttribute("emplist", eservice.selectAll());
	}
	
	//신규등록 페이지
	@RequestMapping(value = "/empInsert.do", method = RequestMethod.GET)
	public void empInsertPage(Model model) {
		model.addAttribute("mgrlist", eservice.selectAllMgr());
		model.addAttribute("jlist", eservice.selectAllJob());
		model.addAttribute("dlist", dservice.selectAll());
	}
	
	//신규등록
	@RequestMapping(value = "/empInsert.do", method = RequestMethod.POST)
	public String empInsert(EmpVO emp, RedirectAttributes redirectAttr) {
		logger.info(emp.toString());
		int result = eservice.empInsert(emp);
		redirectAttr.addFlashAttribute("resultMsg", result+"건 입력");
		
		return "redirect:/emp/empList.do";
	}
	
	//상세보기
	@RequestMapping(value = "/empDetail.do", method = RequestMethod.GET)
	public void empDetail(int employee_id, Model model) {
		model.addAttribute("emp", eservice.selectById(employee_id));
	}
	
	//수정
	@RequestMapping(value = "/empDetail.do", method = RequestMethod.POST)
	public String empUpdate(EmpVO emp, RedirectAttributes redirectAttr) {
		int result = eservice.empUpdate(emp);
		redirectAttr.addFlashAttribute("resultMsg", result+"건 수정");
		
		return "redirect:/emp/empList.do";
	}
	
	//삭제
	@RequestMapping(value = "/empDelete.do", method = RequestMethod.POST)
	public String empDelete(int employee_id, RedirectAttributes redirectAttr) {
		int result = eservice.empDelete(employee_id);
		redirectAttr.addFlashAttribute("resultMsg", result+"건 삭제");
		
		return "redirect:/emp/empList.do";
	}
	
	//부서로 조회
	@RequestMapping("/deptSearch.do")
	public String deptSearch(int dept, Model model) {
		model.addAttribute("emplist", eservice.selectByDept(dept));
		return "emp/empListFrame";
	}
	
	//매니저로 조회
	@RequestMapping("/mgrSearch.do")
	public String mgrSearch(int mgr, Model model) {
		model.addAttribute("emplist", eservice.selectByManager(mgr));
		return "emp/empListFrame";
	}
	
	//직책으로 조회
	@RequestMapping("/jobSearch.do")
	public String jobSearch(String job, Model model) {
		model.addAttribute("emplist", eservice.selectByJob(job));
		return "emp/empListFrame";
	}
}
