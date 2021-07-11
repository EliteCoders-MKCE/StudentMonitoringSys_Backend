package com.sgs.student.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgs.student.models.AttendanceLog1;

@Repository
public interface AttendanceLog1Interface extends JpaRepository <AttendanceLog1,String>{

}
