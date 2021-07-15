package com.sgs.student.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="iii_ece_a_studentdetails")

public class IIIECEAStudentDetails {
	@Id
	@Column(name="registerNo")
	private String registerNo;
	@Column(name="name")
	private String name;
	@Column(name="gender")
	private String gender;
	@Column(name="eMail")
	private String mail;
	@Column(name="deptClass")
	private String deptClassGroup;
	
	
	public IIIECEAStudentDetails() {
		super();
		
	}


	public IIIECEAStudentDetails(String registerNo, String name, String mail, String deptClassGroup) {
		super();
		this.registerNo = registerNo;
		this.name = name;
		this.mail = mail;
		this.deptClassGroup = deptClassGroup;
	}


	public String getRegisterNo() {
		return registerNo;
	}


	public void setRegisterNo(String registerNo) {
		this.registerNo = registerNo;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getDeptClassGroup() {
		return deptClassGroup;
	}


	public void setDeptClassGroup(String deptClassGroup) {
		this.deptClassGroup = deptClassGroup;
	}

	
}
