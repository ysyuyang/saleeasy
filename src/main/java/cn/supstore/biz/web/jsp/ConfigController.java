package cn.supstore.biz.web.jsp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cfg")
public class ConfigController {

	@RequestMapping("/commonattr")
	public String login(){
		return "backend/cfg/commonattr"; 
	}
	
	 
}
