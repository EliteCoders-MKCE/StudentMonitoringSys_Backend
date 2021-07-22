package com.sgs.student.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="iii_ece_a_attendance_manager")
public class IIIECEAAttendanceManager {
	@Id
	@Column(name="attendance_id")
	private String attendanceId;
	@Column(name="attendance_title")
	private String attendanceTitle;
	@Column(name="attendance_desc")
	private String attendanceDesc;
	@Column(name="date")
	private String date;
	@Column(name="type")
	private String type;
	@Column(name="start_time")
	private String startTime;
	@Column(name="end_time")
	private String endTime;
	@Column(name="result_log")
	private String resultLog;
	@Column(name="status")
	private String status;
	@Column(name="staff_id")
	private String staffId;
	
	
	public IIIECEAAttendanceManager() {
		super();
		
	}


	public IIIECEAAttendanceManager(String attendanceId, String attendanceTitle, String attendanceDesc, String date,
			String type, String startTime, String endTime, String resultLog, String status, String staffId) {
		super();
		this.attendanceId = attendanceId;
		this.attendanceTitle = attendanceTitle;
		this.attendanceDesc = attendanceDesc;
		this.date = date;
		this.type = type;
		this.startTime = startTime;
		this.endTime = endTime;
		this.resultLog = resultLog;
		this.status = status;
		this.staffId = staffId;
	}


	public String getAttendanceId() {
		return attendanceId;
	}


	public void setAttendanceId(String attendanceId) {
		this.attendanceId = attendanceId;
	}


	public String getAttendanceTitle() {
		return attendanceTitle;
	}


	public void setAttendanceTitle(String attendanceTitle) {
		this.attendanceTitle = attendanceTitle;
	}


	public String getAttendanceDesc() {
		return attendanceDesc;
	}


	public void setAttendanceDesc(String attendanceDesc) {
		this.attendanceDesc = attendanceDesc;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public String getResultLog() {
		return resultLog;
	}


	public void setResultLog(String resultLog) {
		this.resultLog = resultLog;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getStaffId() {
		return staffId;
	}


	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	
}
