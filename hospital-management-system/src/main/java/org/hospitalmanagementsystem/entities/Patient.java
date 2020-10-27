package org.hospitalmanagementsystem.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

@Entity
@Component
@NamedQueries({
	@NamedQuery(name = "getPatient",query = "SELECT p FROM Patient AS p WHERE email LIKE :username")
})
public class Patient{
	@Id
	@GenericGenerator(name = "patientIdGenerator",
	strategy = "org.hospitalmanagementsystem.entities.HMSIDGenerator",
	parameters =
	{
			@Parameter(name = HMSIDGenerator.INCREMENT_PARAM,value = "1"),
			@Parameter(name = HMSIDGenerator.NUMBER_FORMAT_PARAMETER,value = "%03d"),
			@Parameter(name = HMSIDGenerator.VALUE_PREFIX_PARAMETER,value = "SIVA")
	})
	@GeneratedValue(generator = "patientIdGenerator")
	private String id;
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column
	@Length(min = 5,max = 20)
	private String firstName;
	
	@Column
	@Length(min = 5,max = 20)
	private String lastName;
	
	@Temporal(value = TemporalType.DATE)
	@NotNull
	private Date dateOfBirth;
	
	@Column(unique = true,nullable = false)
	@Email
	private String email;
	
	@Column
	@NotNull
	private String state;
	
	@Column
	@NotNull
	private String contactNumber;
	
	@Column
	@NotNull
	private String insurancePlan;
	

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getInsurancePlan() {
		return insurancePlan;
	}

	public void setInsurancePlan(String insurancePlan) {
		this.insurancePlan = insurancePlan;
	}

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

	public Patient(String id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Patient() {
		super();
	}
	
		
}
