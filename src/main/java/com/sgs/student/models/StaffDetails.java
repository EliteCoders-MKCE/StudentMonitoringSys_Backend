package com.sgs.student.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="staff_details")

public class StaffDetails {
	@Id
	@Column(name="staff_id")
	private String staffId;
	@Column(name="name")
	private String name;
	@Column(name="mail")
	private String mail;
	@Column(name="dept_class_group")
	private String deptClassGroup;
	
	
	public StaffDetails() {
		super();
		
	}
	public StaffDetails(String staffId, String name, String mail, String deptClassGroup) {
		super();
		this.staffId = staffId;
		this.name = name;
		this.mail = mail;
		this.deptClassGroup = deptClassGroup;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
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
