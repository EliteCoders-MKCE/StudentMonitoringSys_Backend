package com.sgs.student.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgs.student.models.IIIECEAAttendanceFileLog;

@Repository
public interface IIIECEAAttendanceFileLogInterface extends JpaRepository <IIIECEAAttendanceFileLog,String>{

}
