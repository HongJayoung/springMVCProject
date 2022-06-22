package net.kosta.controller;

import java.sql.Date;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.myapp.vo.Car;

@Controller
@RequestMapping("/hello") //class level 작성
public class MyController {
	
	@RequestMapping("/my1") //function level 작성
	public String test1(Model model) {
		model.addAttribute("major", "컴퓨터공학");
		model.addAttribute("phone", "010-1234-5678");
		model.addAttribute("car", new Car("ABC",1000,"white"));
		
		return "myjsp1";
	}
	
	@RequestMapping(value = {"/my2","/my3"}, method = RequestMethod.GET)
	public ModelAndView test2(Model model) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("major", "컴퓨터공학");
		mv.addObject("phone", "010-1234-5678");
		mv.addObject("car", new Car("ABC",1000,"white"));
		
		mv.setViewName("myjsp1");
		return mv;
	}
	
	@RequestMapping(value = {"/my2","/my3"}, method = RequestMethod.POST)
	public ModelAndView test3(Model model) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("major", "산업공학");
		mv.addObject("phone", "010-1234-5678");
		mv.addObject("car", new Car("CBA",3000,"white"));
		
		mv.setViewName("myjsp1");
		return mv;
	}

	@ResponseBody //response.getWriter().append("aaa")
	@RequestMapping(value = "/param.do", params = {"userid=hi", "userpw", "!email"}, method = RequestMethod.GET)
	public String test4(Model model) {
		System.out.println("요청받음");
		return "paramResult";
	}
	
	@ResponseBody
	@RequestMapping(value = "/param2.do")
	public String test5(String userid, int userpw, String email, Date birthday) {
		System.out.println("요청받음");
		System.out.println(userid);
		System.out.println(userpw);
		System.out.println(email);
		System.out.println(birthday);
		return "paramResult22";
	}
	
	@ResponseBody
	@RequestMapping(value = "/param3.do")
	public String test6(@RequestParam("userid") String userid,
						@RequestParam("userpw") int pw, //보내는 이름과 다르게 사용하고 싶을 때(생략시 type이 달라서 error)
						String email22, //파라미터 이름이 다르므로 값이 전달안됨(error는 안남)
						Date birthday) {
		System.out.println("param3 요청받음");
		System.out.println(userid);
		System.out.println(pw);
		System.out.println(email22);
		System.out.println(birthday);
		return "paramResult22";
	}
	
	@ResponseBody
	@RequestMapping(value = "/param4.do")
	public String test7(Car car, String email, Date birthday) {
		System.out.println("param4 요청받음");
		System.out.println(car);
		System.out.println(email);
		System.out.println(birthday);

		return "car 받음";
	}
	
	@ResponseBody
	@RequestMapping(value = "/param5.do")
	//파라미터 이름과 매개변수이름이 다른 경우 생략불가
	public String test8(@RequestParam Map<String, String> cart) {
		for(String key:cart.keySet()) {
			System.out.println(key+" => "+cart.get(key));
		}
		
		return "map test";
	}
	
	//@ModelAttribute => JavaBeans객체로 view에 전달 가능
	@RequestMapping(value = "/param6.do")
	public String test9(@ModelAttribute Car car, 
						String email, Date birthday, Model model) {
		System.out.println("param6 요청받음");
		System.out.println(car);
		System.out.println(email);
		System.out.println(birthday);
		
		model.addAttribute("title", "@ModelAttribute 연습");
		model.addAttribute("email", email);
		model.addAttribute("birthday", birthday);
		return "carInfo";
	}
}
