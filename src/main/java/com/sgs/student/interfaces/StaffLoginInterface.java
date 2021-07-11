package com.sgs.student.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgs.student.models.StaffLogin;

@Repository

public interface StaffLoginInterface extends JpaRepository<StaffLogin,String>{

}
