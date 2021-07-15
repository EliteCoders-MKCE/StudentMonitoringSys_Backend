package com.sgs.student.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgs.student.models.IIIECEAAttendanceLog2;

@Repository
public interface IIIECEAAttendanceLog2Interface extends JpaRepository<IIIECEAAttendanceLog2,String> {

}
