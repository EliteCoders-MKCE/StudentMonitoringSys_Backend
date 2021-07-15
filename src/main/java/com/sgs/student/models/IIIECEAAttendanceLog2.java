package com.sgs.student.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="iii_ece_a_attendance_log_2")
public class IIIECEAAttendanceLog2 {
	@Id
	@Column(name="registerNo")
	private String registerNo;
	@Column(name="name")
	private String name;
	@Column(name="status")
	private String attendanceStatus;
	
	
	public IIIECEAAttendanceLog2() {
		super();
		
	}

	

	public IIIECEAAttendanceLog2(String registerNo, String name, String attendanceStatus) {
		super();
		this.registerNo = registerNo;
		this.name = name;
		this.attendanceStatus = attendanceStatus;
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


	public String getAttendanceStatus() {
		return attendanceStatus;
	}


	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}
	
	

}
