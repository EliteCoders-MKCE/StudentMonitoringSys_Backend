package com.sgs.student.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgs.student.models.AttendanceFileLog;

@Repository
public interface AttendanceFileLogInterface extends JpaRepository <AttendanceFileLog,String>{

}
