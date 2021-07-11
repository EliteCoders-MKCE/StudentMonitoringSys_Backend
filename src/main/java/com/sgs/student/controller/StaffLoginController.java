package com.sgs.student.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sgs.student.exception.ResourceNotFoundException;
import com.sgs.student.interfaces.StaffLoginInterface;
import org.springframework.web.client.HttpClientErrorException;
import com.sgs.student.models.StaffLogin;

@RestController
@RequestMapping("api/loginstaff")
public class StaffLoginController {
	@Autowired
	private StaffLoginInterface staffLoginInterface;
	
	@GetMapping("/all")
	public List <StaffLogin>getall()
	{
		return staffLoginInterface.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional <StaffLogin> getById(@PathVariable(value="id")String staffId)
	{
		return this.staffLoginInterface.findById(staffId);
	}

	@PostMapping("/login")
	public HashMap login(@RequestParam("staff_id")String staffId, @RequestParam("password")String password)
	{
        StaffLogin staff = this.staffLoginInterface.findById(staffId).orElseThrow(()->new ResourceNotFoundException("User Not found"));
        if(password.equals(staff.getPassword()) && staffId == staff.getStaffId())
        {
        	 HashMap<String,String> response = new HashMap<String,String>();
             response.put("staffId",staff.getStaffId());
             response.put("class_group", staff.getClassGroup());
             response.put("loginStatus","true");
             
             return response;
        }
        else {
            HashMap<String,String> response = new HashMap<String,String>();
            response.put("loginStatus","false");
            return response;
        }
	
	}
	
	
}
