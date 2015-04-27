package com.webapp.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webapp.model.User;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	static Log log = LogFactory.getLog(LoginController.class);
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		
		log.info("######################");
		log.info("login() GET...");
		log.info("######################");
		
		return "login/loginform";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(User user, HttpSession session) {
		
		log.info("######################");
		log.info("login() POST...");
		log.info("id=" + user.getId());
		log.info("password=" + user.getPassword());
		log.info("######################");
		
		if("webapp".equals(user.getId()) && "1234".equals(user.getPassword())) {
			session.setAttribute("user", user);
			return "login/success";
			// Login Success
		} else {
			return "redirect:login";
			// Login Fail
		}
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public String logout(HttpSession session) {
		
		//session 무효화
		session.invalidate();
		
		return "login/logoutsuccess";
	}
}
