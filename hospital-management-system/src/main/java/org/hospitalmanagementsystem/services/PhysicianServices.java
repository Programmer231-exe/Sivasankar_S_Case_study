package org.hospitalmanagementsystem.services;

import java.util.List;

import org.hospitalmanagementsystem.databaseadministrator.HMS_DAO;
import org.hospitalmanagementsystem.entities.PatientDiagnosisDetails;
import org.hospitalmanagementsystem.entities.Physician;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhysicianServices {
	@Autowired
	HMS_DAO daoManager;
	public Physician isValidUser(String username, String password) {
		Physician physician = daoManager.isValidPhysician(username, password);
		if(physician != null) {
			return physician;
		}else {
			return null;
		}
	}
	public List<PatientDiagnosisDetails> getDiagnosisReport(String physicianId) {
		return daoManager.getDiagnosisReportofPatientforDoctor(physicianId);
	}
	public PatientDiagnosisDetails getDiagnosis(String id) {
		return daoManager.getDiagnosisReport(id);
	}
	public boolean updateDiagnosisReport(PatientDiagnosisDetails pdd) {
		return daoManager.updateDiagnosisReport(pdd);
	}
}
