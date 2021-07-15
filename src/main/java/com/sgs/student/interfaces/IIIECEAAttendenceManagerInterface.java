package com.sgs.student.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgs.student.models.IIIECEAAttendanceManager;

@Repository
public interface IIIECEAAttendenceManagerInterface extends JpaRepository <IIIECEAAttendanceManager,String>{

}
