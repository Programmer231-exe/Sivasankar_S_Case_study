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
	@NamedQuery(name = "resetPasswordforphysician",
			query = "UPDATE PhysicianLoginDetails as L SET password = :newpassword WHERE username LIKE :user"),
	@NamedQuery(name = "getPhysicianDetailsusingUsername",
	query = "SELECT ld FROM PhysicianLoginDetails as ld WHERE username LIKE :username"),
	
	@NamedQuery(name = "getPhysicianLoginDetails",
	query = "SELECT ld FROM PhysicianLoginDetails as ld WHERE physician_id LIKE :physicianId")
})
		
@Entity
public class PhysicianLoginDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(unique = true,nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;
	@OneToOne(cascade = CascadeType.ALL)
	private Physician physician;
	
	
	public Physician getPhysician() {
		return physician;
	}
	public void setPhysician(Physician physician) {
		this.physician = physician;
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
		for(int i = 0; i < 9; i++) {
			password[i] = (char)('a' + random.nextInt(255));
		}

		return password.toString();
		
		}

}
