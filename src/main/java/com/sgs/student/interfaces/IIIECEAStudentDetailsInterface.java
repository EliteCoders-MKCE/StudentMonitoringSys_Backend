package com.sgs.student.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgs.student.models.IIIECEAStudentDetails;

@Repository
public interface IIIECEAStudentDetailsInterface extends JpaRepository<IIIECEAStudentDetails,String>{

}
