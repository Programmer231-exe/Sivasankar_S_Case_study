package org.hospitalmanagementsystem.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hospitalmanagementsystem.entities.Patient;
import org.hospitalmanagementsystem.entities.PatientDiagnosisDetails;
import org.hospitalmanagementsystem.services.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/patient")
@Scope(value = "session")
public class PatientController {
	
	private static Logger logger = LogManager.getLogger(PatientController.class);
	@Autowired
	private PatientServices patientServices;
	@GetMapping("/login")
	public String getIntoLogin() {
		return "patientLogin";
	}

	
	@PostMapping("/validate")
	public String validate(@RequestParam("username")String username, @RequestParam("password")String password,HttpServletRequest request,Model model) {
		logger.debug(username);
		logger.debug(password);
		if(patientServices.isValidUser(username,password)) {
			request.getSession().setAttribute("user", patientServices.getPatient(username));
			return "welcomepeople";
		}
		else {
			model.addAttribute("message","Check your username and password");
			return "patientLogin";
		}
		
	}
	
	@GetMapping("/resetpassword.ppl")
	public String resetPassword() {
		return "resetpassword";
	}
	
	@PostMapping("/resetpassword.ppl")
	public String updatePassword(@RequestParam("username")String username,@RequestParam("newpassword")String newpassword,HttpServletRequest request,Model model) {
			request.getSession().getAttribute("user");
			
		if(patientServices.resetPassword(username, newpassword)) {
			model.addAttribute("message","Password Successfully Changed");
			return "welcomepeople";
		}else {
			model.addAttribute("message","Password Change Request Failed");
			return "welcomepeople";
		}
	}
	@GetMapping("/bookanappointment.ppl")
	public String bookAnAppointment() {
		return "bookanappointment";
	}
	
	@PostMapping("/bookanappointment.ppl")
	public String toBookanAppointment(@RequestParam("department")String department,Model model) {
		model.addAttribute("physicianLists",patientServices.getPhysician(department));
		return "registerappointment";
	}
	
	@PostMapping("/registerappointment.ppl")
	public String registerappointment(@RequestParam("physician")String physician,HttpServletRequest request,Model model) {
		Patient pdDetails  = (Patient)request.getSession().getAttribute("user");
		logger.debug(pdDetails.getEmail());
		model.addAttribute("message","Your appointment with " + physician + "is succesfully registered" );
		patientServices.registerAppointment(pdDetails.getEmail(),physician);
		return "welcomepeople";
	}
	
	@GetMapping("/history.ppl")
	public String getHistory(HttpServletRequest request,Model model) {
		String username = ((Patient)request.getSession().getAttribute("user")).getEmail();
		List<PatientDiagnosisDetails> patientDiagnosisDetails = patientServices.getDiagnosisDetails(username);
		for(PatientDiagnosisDetails p : patientDiagnosisDetails) {
			System.out.println(p.getDiagnosisId());
		}
		model.addAttribute("diagnosisList",patientDiagnosisDetails);
		return "patientdiagnosisrepost";
	}
	
	@GetMapping("/logout.ppl")
	public String logout(HttpServletRequest request,Model model) {
		request.getSession(false).invalidate();
		model.addAttribute("message","You are logged out successfully");
		return "index";
	}
	
	@GetMapping("/welcome.ppl")
	public String welcomePage() {
		return "welcomepeople";
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String allExceptionHandler(RuntimeException e,Model model) {
		model.addAttribute("message",e.getMessage());
		return "error";
	}
	
	
}
