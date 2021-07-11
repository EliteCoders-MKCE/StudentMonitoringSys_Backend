package com.sgs.student.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgs.student.models.AttendanceManager;

@Repository
public interface AttendenceManagerInterface extends JpaRepository <AttendanceManager,String>{

}
