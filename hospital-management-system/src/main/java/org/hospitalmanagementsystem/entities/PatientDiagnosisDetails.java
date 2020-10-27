package org.hospitalmanagementsystem.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@NamedQueries({
	@NamedQuery(name = "getDiagnosisDetails",
				query = "SELECT dd FROM PatientDiagnosisDetails as dd WHERE patientId_id LIKE :patientId"
				),
	@NamedQuery(name = "getDiagnosisDetailsforDoctor",
	query = "SELECT dd FROM PatientDiagnosisDetails as dd WHERE physician_id LIKE :physicianId"
	),

}
)
@Entity
public class PatientDiagnosisDetails {
	
	@Id
	@GenericGenerator(name = "diagnosisIdGenerator",
				strategy = "org.hospitalmanagementsystem.entities.HMSIDGenerator",
				parameters = {
						@Parameter(name = HMSIDGenerator.VALUE_PREFIX_PARAMETER,value = "DNGS"),
						@Parameter(name = HMSIDGenerator.NUMBER_FORMAT_PARAMETER,value = "%07d"),
						@Parameter(name = HMSIDGenerator.INCREMENT_PARAM,value = "1")
				})
	@GeneratedValue(generator = "diagnosisIdGenerator")
	private String diagnosisId;
	
	@OneToOne
	@NotNull
	private Patient patientId;
	
	@ElementCollection
	@Column(name = "Symtoms")
	private Set<String> symptoms = new HashSet<>();
	
	
	@OneToOne
	@NotNull
	private Physician physician;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date dateOfDiagnosis;
	
	@Column
	private char isFollowUpRequired;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date dateOfFollowUp;
	
	@Column	
	private double billAmount;
	
	@Column
	private String modeOfPayment;
	
	@Column
	private String status;
	
	public double getBillAmount() {
		return billAmount;
	}


	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}


	public String getModeOfPayment() {
		return modeOfPayment;
	}


	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getDiagnosisId() {
		return diagnosisId;
	}

	
	public void setDiagnosisId(String diagnosisId) {
		this.diagnosisId = diagnosisId;
	}

	public Patient getPatientId() {
		return patientId;
	}

	public void setPatientId(Patient patientId) {
		this.patientId = patientId;
	}

	public Set<String> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(Set<String> symptoms) {
		this.symptoms = symptoms;
	}

	public Physician getPhysician() {
		return physician;
	}

	public void setPhysician(Physician physician) {
		this.physician = physician;
	}

	public Date getDateOfDiagnosis() {
		return dateOfDiagnosis;
	}

	public void setDateOfDiagnosis(Date dateOfDiagnosis) {
		this.dateOfDiagnosis = dateOfDiagnosis;
	}


	
	public char getIsFollowUpRequired() {
		return isFollowUpRequired;
	}

	public void setIsFollowUpRequired(char isFollowUpRequired) {
		this.isFollowUpRequired = isFollowUpRequired;
	}

	public Date getDateOfFollowUp() {
		return dateOfFollowUp;
	}

	public void setDateOfFollowUp(Date dateOfFollowUp) {
		this.dateOfFollowUp = dateOfFollowUp;
	}

	public PatientDiagnosisDetails(String diagnosisId, Patient patientId, Set<String> symptoms, Physician physician,
			Date dateOfDiagnosis, char isFollowUpRequired, Date dateOfFollowUp) {
		super();
		this.diagnosisId = diagnosisId;
		this.patientId = patientId;
		this.symptoms = symptoms;
		this.physician = physician;
		this.dateOfDiagnosis = dateOfDiagnosis;
		this.isFollowUpRequired = isFollowUpRequired;
		this.dateOfFollowUp = dateOfFollowUp;
		
	}

	public PatientDiagnosisDetails() {
		super();
		
	}
	
	
}
