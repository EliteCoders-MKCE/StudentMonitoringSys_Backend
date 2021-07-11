package com.sgs.student.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgs.student.models.StudentLogin;

@Repository

public interface StudentLoginInterface extends JpaRepository<StudentLogin,String>{

}
