package com.sgs.student.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student_details")

public class StudentDetails {
	@Id
	@Column(name="register_no")
	private String registerNo;
	@Column(name="name")
	private String name;
	@Column(name="mail")
	private String mail;
	@Column(name="dept_class_group")
	private String deptClassGroup;
	
	
	public StudentDetails() {
		super();
		
	}
	public StudentDetails(String registerNo, String name, String mail, String deptClassGroup) {
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
