package org.hospitalmanagementsystem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

@Entity
@Component
public class Physician {
	
	@Id
	@GenericGenerator(name = "physicianIdGenerator",
					strategy = "org.hospitalmanagementsystem.entities.HMSIDGenerator",
					parameters = {
							@Parameter(name= HMSIDGenerator.VALUE_PREFIX_PARAMETER,value = "PR"),
							@Parameter(name = HMSIDGenerator.NUMBER_FORMAT_PARAMETER,value = "%03d"),
							@Parameter(name = HMSIDGenerator.INCREMENT_PARAM,value = "1")
					}
					)
	@GeneratedValue(generator = "physicianIdGenerator")
	private String id;
	@Column(nullable = false)
	@Length(min = 5,max = 20,message = "first name length should be between 5 and 20")
	private String firstName;
	@Column(nullable = false)
	@Length(min = 5,max = 20,message = "Last name length should be between 5 and 20")
	private String lastName;

	@Column(nullable = false)
	private String department;
	@Column(nullable = false)
	private String educationQualification;
	@Column(nullable = false)
	private String state;
	@Column(nullable = false)
	private int yearsOfExperience;
	@Column(nullable = false)
	private String insurancePlan;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEducationQualification() {
		return educationQualification;
	}
	public void setEducationQualification(String educationQualification) {
		this.educationQualification = educationQualification;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getInsurancePlan() {
		return insurancePlan;
	}
	public void setInsurancePlan(String insurancePlan) {
		this.insurancePlan = insurancePlan;
	}
	public int getYearsOfExperience() {
		return yearsOfExperience;
	}
	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
	
	
	
}
