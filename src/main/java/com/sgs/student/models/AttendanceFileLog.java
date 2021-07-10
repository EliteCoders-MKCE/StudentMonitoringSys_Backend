package com.sgs.student.models;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="attendance_file_log")

public class AttendanceFileLog {
	@Id
	@Column(name="attendance_id")
	private String attendanceId;
	@Column(name="attendance_file")
	@Lob
	private Blob attendanceFile;
	@Column(name="date_time")
	private String dateTime;
	
	
	public AttendanceFileLog() {
		super();
		
	}
	public AttendanceFileLog(String attendanceId, Blob attendanceFile, String dateTime) {
		super();
		this.attendanceId = attendanceId;
		this.attendanceFile = attendanceFile;
		this.dateTime = dateTime;
	}
	public String getAttendanceId() {
		return attendanceId;
	}
	public void setAttendanceId(String attendanceId) {
		this.attendanceId = attendanceId;
	}
	public Blob getAttendanceFile() {
		return attendanceFile;
	}
	public void setAttendanceFile(Blob attendanceFile) {
		this.attendanceFile = attendanceFile;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	

}
