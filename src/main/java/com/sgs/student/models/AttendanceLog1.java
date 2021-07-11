package com.sgs.student.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="attendance_log_1")
public class AttendanceLog1 {
	@Id
	@Column(name="register_no")
	private String registerNo;
	@Column(name="attendance_status")
	private String attendanceStatus;
	
	
	public AttendanceLog1() {
		super();
		
	}
	public AttendanceLog1(String registerNo, String attendanceStatus) {
		super();
		this.registerNo = registerNo;
		this.attendanceStatus = attendanceStatus;
	}
	public String getRegisterNo() {
		return registerNo;
	}
	public void setRegisterNo(String registerNo) {
		this.registerNo = registerNo;
	}
	public String getAttendanceStatus() {
		return attendanceStatus;
	}
	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}
	
	

}