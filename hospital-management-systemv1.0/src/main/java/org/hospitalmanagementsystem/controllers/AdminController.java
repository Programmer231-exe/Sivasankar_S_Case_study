package org.hospitalmanagementsystem.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hospitalmanagementsystem.entities.Patient;
import org.hospitalmanagementsystem.entities.PatientLoginDetails;
import org.hospitalmanagementsystem.entities.Physician;
import org.hospitalmanagementsystem.entities.PhysicianLoginDetails;
import org.hospitalmanagementsystem.services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static Logger logger = LogManager.getLogger(AdminController.class);
	@Autowired
	private AdminServices adminServices;
	@GetMapping("/")
	public String getHome() {
		logger.info("Request for admin login page received");
		return "adminlogin";
	}
	
	@PostMapping(value = "/validate")
	public String validate(@RequestParam("username")String username,@RequestParam("password")String password,Model model,HttpServletRequest request) {
		logger.info("login details are received from user to validate");
		if(username.equals("admin") && password.equals("admin")) {
			logger.info("Admin Login Successful");
			request.getSession().setAttribute("user","Administrator");
			return "redirect:welcome.ppl";
		}else {
			logger.warn("Admin credentials are not matched with the records");
			model.addAttribute("message","Admin credentials are not matched with the records");
			return "adminlogin";
		}
		
	
	}
	@GetMapping("/registerphysician.ppl")	
	public String registerPhysician(Model model) {
		logger.info("Registering physician request generated");
		Physician physician = new Physician();
		model.addAttribute("physician",physician);
		return "registerphysician";
	}
	
	@PostMapping("/updatephysician.ppl")
	public String updatePhysician( @ModelAttribute("physician") @Valid Physician physician,BindingResult result,Model model) 
	{
		
		PhysicianLoginDetails physicianLoginDetails= adminServices.updatePhysician(physician);
		if(result.hasErrors()) {
			return "registerphysician";
		}
		else if(physicianLoginDetails == null) {
			physician = new Physician();
			model.addAttribute("message","Constraint Violation occurs check the firstName and lastName");
			return "registerphysician";
		}else {
			model.addAttribute("details","Physician Successfully Registered");
			model.addAttribute("details","Physician Login Details");
			model.addAttribute("loginDetails",physicianLoginDetails);
			return "welcomeadmin";
		}
		
	}
	
	@GetMapping("/enrollpeople.ppl")
	public String enrollPatient(Model model) {
		logger.info("Enrolling people request generated");
		Patient patient = new Patient();
		model.addAttribute("patient",patient);
		return "enrollpatient";
	}
	
	@PostMapping("/enrollpeople.ppl")
	public String updatePeople(@Valid @ModelAttribute("patient")Patient patient,BindingResult result,Model model) 
	{	logger.info("Login details update requst generated");
	
		if(result.hasErrors()) {
			logger.warn("Validation fails");
			return "enrollpatient";
		}
		PatientLoginDetails patientLoginDetails = adminServices.enrollPatient(patient);
		if(patientLoginDetails != null) {
			model.addAttribute("details","Patient Login Details");
			model.addAttribute("loginDetails",patientLoginDetails);
			return "welcomeadmin";
		}else {
			model.addAttribute("message","Cannot able to create patient one or more constraint fails");
			return "enrollpatient";
		}
		
	}
	
	@GetMapping("/searchphysician.ppl")
	public String toSearchPhysician() {
		return "searchphysician";
	}
	@PostMapping("/searchphysician.ppl")
	public String searchPhysician(@RequestParam("searchby")String searchBy, @RequestParam("searchword")String[] searchwords,Model model) {
		String searchWord = "";
		if(searchBy.equals("state"))
			searchWord = searchwords[0];
		if(searchBy.equals("department"))
			searchWord = searchwords[1];
		if(searchBy.equals("insurancePlan"))
			searchWord = searchwords[2];
		
		System.out.println(searchWord);
		model.addAttribute("physicianLists",adminServices.getPhysician(searchWord, searchBy));
		return "searchphysician";
	}
	
	@GetMapping("/logout.ppl")
	public String logout(HttpServletRequest request,Model model) {
		request.getSession(false).invalidate();
		model.addAttribute("message","Admin Successfully Logged OUt");
		return "index";
	}
	
	
	@GetMapping("/getDiagnosisReportForAdmin.ppl")
	public String getDiagnosisReports(Model model) {
		logger.info("Request to get report generated");
		model.addAttribute("diagnosisLists",adminServices.getDiagnosisReports());
		return "admindiagnosisreport";
	}
	
	@GetMapping("/welcome.ppl")
	public String welcomePage() {
		return "welcomeadmin";
	}
	
	
	@ExceptionHandler(RuntimeException.class)
	public String allExceptionHandler(RuntimeException e,Model model) {
		model.addAttribute("message",e.getMessage());
		return "error";
	}
	

}
