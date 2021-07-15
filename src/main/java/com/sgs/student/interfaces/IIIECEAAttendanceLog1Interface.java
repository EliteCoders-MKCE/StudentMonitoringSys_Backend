package com.sgs.student.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgs.student.models.IIIECEAAttendanceLog1;

@Repository
public interface IIIECEAAttendanceLog1Interface extends JpaRepository <IIIECEAAttendanceLog1,String>{

}
