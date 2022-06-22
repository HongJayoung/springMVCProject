package net.kosta.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kosta.myapp.vo.Car;
import com.kosta.myapp.vo.UserVO;

@Controller
public class MyController2 {
	
	Logger logger = LoggerFactory.getLogger(MyController2.class);
	
	@RequestMapping("/spring/test1.do")
	public String method1() {
		
		return "0622/test1";
	}
	
	//@ModelAttribute("user") 이름이 생략되면 view에서 사용시 UserVO userVO와 같이 사용해야함
	@RequestMapping(value = "/spring/test2.do", method = RequestMethod.POST)
	public String method2(String userid, 
							String userpw, 
							@ModelAttribute("user") UserVO user, 
							Model model, 
							HttpServletRequest request,
							HttpSession session) {
		String path = request.getRealPath(".");
		String path2 = session.getServletContext().getRealPath(".");
		String url = request.getRequestURI();
		
		session.setAttribute("user", user);

		logger.info(user.toString());
		logger.info(userid);
		logger.info(userpw);
		
		logger.info(path);
		logger.info(path2);
		logger.info(url);
		
		model.addAttribute("userid", userid);
		model.addAttribute("userpw", userpw);
		
		return "0622/test2";
	}
	
	//return => ModelAndView, Model, String, void
	//String : view 이름, @ResponseBody와 같이 사용하면 document에 출력할 문자
	//ModelAndView : data + view 이름
	//Model : 메서드 파라메터로 정의하고 값은 addAttribute()로 setting, view이름 String으로 return
	//void : 요청 URI로부터 맨압의 실래시와 확장자를 제외한 나머지부분을 뷰 이름으로 사용
	
	@RequestMapping("/0622/test3.do") //view => 0622/test3.jsp
	public void method3(Model model, HttpServletRequest request) {
		
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		if(flashMap !=null ) {
			String subject = (String)flashMap.get("subject");
			Car car = (Car)flashMap.get("car");

			model.addAttribute("key", subject);
			model.addAttribute("car", car);
		}


	}
	
	@RequestMapping("/test4.do")
	public String method4(Model model, RedirectAttributes redirectAttr) {
		
		redirectAttr.addFlashAttribute("subject", "SpringFrameWork");
		redirectAttr.addFlashAttribute("car", new Car("A", 1000, "white"));
		
		//return "/0622/test4"; //default로 forward됨 => 주소창이 그대로
		return "redirect:/0622/test3.do"; //redirect => 주소창이 바뀜
	}
}
