package org.hospitalmanagementsystem.services;

import java.util.List;

import org.hospitalmanagementsystem.databaseadministrator.HMS_DAO;
import org.hospitalmanagementsystem.entities.Patient;
import org.hospitalmanagementsystem.entities.PatientDiagnosisDetails;
import org.hospitalmanagementsystem.entities.PatientLoginDetails;
import org.hospitalmanagementsystem.entities.Physician;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServices {

	@Autowired
	private HMS_DAO daoManager;
	
	public PatientLoginDetails enrollPatient(Patient patient) {
		return daoManager.enrollPatient(patient);
	}
	
	
	public boolean isValidUser(String username, String password) {
		if(daoManager.isValidUser(username,password))
			return true;
		else
			return false;
	}
	
	public Patient getPatient(String username) {
		return daoManager.getPatientDetail(username);
	}
	
	public boolean resetPassword(String username,String password) {
		return daoManager.resetPassword(username, password);
	}
	
	public List<Physician> getPhysician(String cond){
		return daoManager.getPhysicianByState(cond,"department");
		
	}
	
	public void registerAppointment(String username,String physician) {
		System.err.println(username + "  " + physician);
		daoManager.registerAppointment(username, physician);
	}


	public List<PatientDiagnosisDetails> getDiagnosisDetails(String username) {
		return daoManager.getDiagnosisReportofPatient(username);
	}
}
