package com.example.demo.controller;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Clan;
import com.example.demo.service.ClanService;
import org.springframework.ui.Model;
//import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;


@Controller
public class WebController {

	@RequestMapping(value ={"/"}, method = RequestMethod.GET)
	public ModelAndView pocetak(HttpSession request) {

		ModelAndView retVal = new ModelAndView();
		retVal.setViewName("login");
		return retVal; 
	}
	
	
	
    
    
    
    
    
    
    
    
	
}
