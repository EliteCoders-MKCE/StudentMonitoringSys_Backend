package com.sgs.student.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgs.student.models.StudentDetails;

@Repository
public interface StudentDetailsInterface extends JpaRepository<StudentDetails,String>{

}
