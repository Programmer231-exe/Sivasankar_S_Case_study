package org.hospitalmanagementsystem.entities;

import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@NamedQueries({
		@NamedQuery(name = "resetPasswordForPatient",
					query = "UPDATE PatientLoginDetails as L SET password = :newpassword WHERE username LIKE :user"),
		@NamedQuery(name = "getLoginDetails",
					query = "SELECT ld FROM PatientLoginDetails as ld WHERE username LIKE :username"
					)}
)
@Entity
public class PatientLoginDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(unique = true,nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;
	@OneToOne(cascade = CascadeType.ALL)
	private Patient patient;
	
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient p) {
		this.patient = p;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String setPassword() {
		char[] password = new char[10];
		Random random = new Random();
		char a = (char)random.nextInt(26);
		for(int i = 0; i < 9; i++) {
			password[i] = (char)(a + random.nextInt(255));
		}
		System.out.println(password.toString());
		return password.toString();	
	}
	

}
