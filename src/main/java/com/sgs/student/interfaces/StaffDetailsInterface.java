package com.sgs.student.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgs.student.models.StaffDetails;

@Repository
public interface StaffDetailsInterface extends JpaRepository <StaffDetails,String> {

}
