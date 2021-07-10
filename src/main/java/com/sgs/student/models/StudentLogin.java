package com.sgs.student.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student_login")
public class StudentLogin {
	@Id
	@Column(name="register_no")
	private String registerNo;
	
	@Column(name="password")
	private String password;
	
	@Column(name="class_group")
	private String classGroup;
	
	public StudentLogin() {
		super();
	}

	public StudentLogin(String registerNo, String password, String classGroup) {
		super();
		this.registerNo = registerNo;
		this.password = password;
		this.classGroup = classGroup;
	}

	public String getRegisterNo() {
		return registerNo;
	}

	public void setRegisterNo(String registerNo) {
		this.registerNo = registerNo;
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
