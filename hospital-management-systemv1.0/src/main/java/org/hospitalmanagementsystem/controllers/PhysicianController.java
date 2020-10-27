package org.hospitalmanagementsystem.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hospitalmanagementsystem.entities.PatientDiagnosisDetails;
import org.hospitalmanagementsystem.entities.Physician;
import org.hospitalmanagementsystem.services.PhysicianServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/physician")
@Scope(value = "session")
public class PhysicianController {
	
	private static Logger logger = LogManager.getLogger(PhysicianController.class);
	@Autowired
	PhysicianServices physicianServices;
	
	@GetMapping("/login")
	public String getIntoLogin() {
		return "physicianLogin";
	}
	
	/*
	 * @ModelAttribute("patient") public Patient getPatient() { return new
	 * Patient(); }
	 */
	
	@PostMapping("/validate")
	public String validate(@RequestParam("username")String username, @RequestParam("password")String password,HttpServletRequest request,Model model) {
		logger.debug(username);
		logger.debug(password);
		Physician physician = physicianServices.isValidUser(username, password);
		if(physician != null) {
			request.getSession().setAttribute("user", physician);
			return "redirect:welcome.ppl";
		}
		else {
			model.addAttribute("message","Check your username and password");
			return "physicianLogin";
		}
		
	}
	
	@GetMapping("/welcome.ppl")
	public String welcomePage(HttpServletRequest request,Model model) {
		String physicianId = ((Physician)request.getSession().getAttribute("user")).getId();
		model.addAttribute("diagnosislist",physicianServices.getDiagnosisReport(physicianId));
		return "welcomedoctor";
		
	}
	
	@PostMapping("/treat.ppl")
	public String treat(@RequestParam("diagnosisId")String id,Model model,HttpServletRequest request) {
		model.addAttribute("diagnosis",physicianServices.getDiagnosis(id));
		request.getSession().setAttribute("diagnosisreport",physicianServices.getDiagnosis(id));
		return "treatment";
	}
	
	@PostMapping("/updateDiagnosisReport.ppl")
	public String updateDiagnosisReport(@ModelAttribute("diagnosis")PatientDiagnosisDetails diagnosis,Model model,HttpServletRequest request)
	{
		PatientDiagnosisDetails pdDetails = (PatientDiagnosisDetails)request.getSession().getAttribute("diagnosisreport");
		pdDetails.setDateOfDiagnosis(diagnosis.getDateOfDiagnosis());
		pdDetails.setIsFollowUpRequired(diagnosis.getIsFollowUpRequired());
		if(diagnosis.getIsFollowUpRequired() == 'y') {
			pdDetails.setDateOfFollowUp(diagnosis.getDateOfFollowUp());
		}else {
			pdDetails.setDateOfFollowUp(null);	
		}
		pdDetails.setBillAmount(diagnosis.getBillAmount());
		pdDetails.setModeOfPayment(diagnosis.getModeOfPayment());
		pdDetails.setStatus("close");
		pdDetails.setSymptoms(diagnosis.getSymptoms());
		if(physicianServices.updateDiagnosisReport(pdDetails)) {
			model.addAttribute("message","Report successfully saved");
			return "welcomedoctor";
		}else {
			model.addAttribute("message","Report failsed to save");
			return "treatment";
		}
	}
	
	@GetMapping("/logout.ppl")
	public String logout(HttpServletRequest request,Model model) {
		request.getSession(false).invalidate();
		model.addAttribute("message","Successfully Logged out");
		return "physicianLogin";
		
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public String allExceptionHandler(MissingServletRequestParameterException e,Model model) {
		model.addAttribute("message",e.getMessage());
		return "error";
	}
	
	
		
}
