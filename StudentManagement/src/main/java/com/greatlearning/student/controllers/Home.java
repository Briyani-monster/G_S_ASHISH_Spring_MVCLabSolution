package com.greatlearning.student.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home {
 
	@RequestMapping("/")
	public String ShowMainPage() {
		System.out.println("Working");
		return "home";
	}
}