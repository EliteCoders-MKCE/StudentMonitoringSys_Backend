package com.sgs.student.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgs.student.models.AttendanceLog2;

@Repository
public interface AttendanceLog2Interface extends JpaRepository<AttendanceLog2,String> {

}
