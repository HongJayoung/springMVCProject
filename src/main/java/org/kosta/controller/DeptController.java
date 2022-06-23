package org.kosta.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kosta.dto.DeptDTO;
import com.kosta.myapp.model.DeptService;
import com.kosta.myapp.model.TestDAO;
import com.kosta.myapp.vo.Car;

@Controller
public class DeptController {
	
	Logger logger = LoggerFactory.getLogger(DeptController.class);
	
	@Autowired
	DeptService dservice;
	
//	@ExceptionHandler(Exception.class)
//	public String processException(Exception ex) {
//		ex.printStackTrace();
//		logger.info("[오류]" + ex.getMessage());
//		
//		return "error/error500";
//	}
	
	@RequestMapping(value = "/dept/deptList.do", method = RequestMethod.GET)
	public void deptList(Model model, HttpServletRequest request) {
		List<DeptDTO> dlist = dservice.selectAll();
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		
		String resultMsg = null;
		if(flashMap !=null) {
			resultMsg = (String)flashMap.get("resultMsg");
			model.addAttribute("resultMsg", resultMsg);
		}
		
		model.addAttribute("deptList", dlist);
	}
	
	@RequestMapping(value = "/dept/deptDelete.do", method = RequestMethod.GET)
	public String deptDeleteGet(int dept_id, RedirectAttributes redirectAttr) {
		//logger.info(dept_id+"번 부서 삭제");
		
		int result = dservice.deptDelete(dept_id);
		redirectAttr.addFlashAttribute("resultMsg", result+"건 삭제");
		
		return "redirect:/dept/deptList.do";
	}
	
	@RequestMapping(value = "/dept/deptInsert.do", method = RequestMethod.GET)
	public String deptInsertGet() {
		
		return "/dept/deptInsert";
	}
	
	@RequestMapping(value = "/dept/deptInsert.do", method = RequestMethod.POST)
	public String deptInsertPost(DeptDTO dept, RedirectAttributes redirectAttr) {
		//logger.info(dept.toString());
		
		int result = dservice.deptInsert(dept);
		redirectAttr.addFlashAttribute("resultMsg", result+"건 입력");
		
		return "redirect:/dept/deptList.do";
	}
	
	@RequestMapping(value = "/dept/deptUpdate.do", method = RequestMethod.GET)
	public String deptUpdateGet(int dept_id, Model model) {
		DeptDTO dept = dservice.selectById(dept_id);
		model.addAttribute("dept", dept);
		
		return "/dept/deptDetail";
	}
	
	@RequestMapping(value = "/dept/deptUpdate.do", method = RequestMethod.POST)
	public String deptUpdatePost(DeptDTO dept, RedirectAttributes redirectAttr) { //new DeptDTO(); dept.setDepartment_id(request.getParameter("dept_id"));
		//logger.info(dept.toString());
		
		int result = dservice.deptUpdate(dept);
		redirectAttr.addFlashAttribute("resultMsg", result+"건 수정");
		
		return "redirect:/dept/deptList.do";
	}
}
