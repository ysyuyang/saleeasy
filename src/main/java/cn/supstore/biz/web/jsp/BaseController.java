package cn.supstore.biz.web.jsp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {

	@RequestMapping("login")
	public String login(){
		return "backend/login"; 
	}
	
	@RequestMapping("hello2")
    public ModelAndView example1() {
	    Map<String, String> map = new HashMap<String, String>();
	    map.put("a", "1");
	    map.put("b", "2");
	    return new ModelAndView("backend/login", map);
    }
	@RequestMapping("main")
    public String example2() {
		return "backend/main"; 
    }
}
