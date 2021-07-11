package com.sgs.student.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="staff_login")

public class StaffLogin {
	@Id
	@Column(name="staff_id")
	private String staffId;
	@Column(name="password")
	private String password;
	@Column(name="class_group")
	private String classGroup;
	
	public StaffLogin() {
		super();
	}
	
	
	public StaffLogin(String staffId, String password, String classGroup) {
		super();
		this.staffId = staffId;
		this.password = password;
		this.classGroup = classGroup;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getClassGroup() {
		return classGroup;
	}
	public void setClassGroup(String classGroup) {
		this.classGroup = classGroup;
	}
	
	

}
