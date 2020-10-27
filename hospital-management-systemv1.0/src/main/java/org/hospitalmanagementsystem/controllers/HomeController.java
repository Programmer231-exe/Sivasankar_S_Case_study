
package org.hospitalmanagementsystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String welcome() {
		return "index";
	}
	
	@GetMapping(value = "/patientlogin.ppl")
	public String patientLogin() {
		return "welcomePeople";
	}
	
	@GetMapping(value = "/physicianlogin.ppl")
	public String physicianLogin() {
		return "welcomeDoctor";
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String allExceptionHandler(RuntimeException e,Model model) {
		model.addAttribute("message",e.getMessage());
		return "error";
	}
	
	
}
