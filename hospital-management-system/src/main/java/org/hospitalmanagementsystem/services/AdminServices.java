package org.hospitalmanagementsystem.services;

import java.util.List;

import org.hospitalmanagementsystem.databaseadministrator.HMS_DAO;
import org.hospitalmanagementsystem.entities.Patient;
import org.hospitalmanagementsystem.entities.PatientDiagnosisDetails;
import org.hospitalmanagementsystem.entities.PatientLoginDetails;
import org.hospitalmanagementsystem.entities.Physician;
import org.hospitalmanagementsystem.entities.PhysicianLoginDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AdminServices {

	@Autowired
	private HMS_DAO daoManager;
	
	public PhysicianLoginDetails updatePhysician(Physician physician) {
			return daoManager.registerPhysician(physician);
	}
	

	public PatientLoginDetails enrollPatient(Patient patient) {
		return daoManager.enrollPatient(patient);
	}
	
	public List<Physician> getPhysician(String cond,String base){
		return daoManager.getPhysicianByState(cond,base);
	}


	public List<PatientDiagnosisDetails> getDiagnosisReports() {
		return daoManager.getAllDiagnosisReports();
	}
	 
	
}
