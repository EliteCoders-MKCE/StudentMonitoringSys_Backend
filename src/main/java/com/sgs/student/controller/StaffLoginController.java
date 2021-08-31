package com.sgs.student.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgs.student.database.DatabaseConnector;
import com.sgs.student.database.ResultSetSerialiser;
import com.sgs.student.exception.ResourceNotFoundException;
import com.sgs.student.interfaces.StaffLoginInterface;
import org.springframework.web.client.HttpClientErrorException;
import com.sgs.student.models.StaffLogin;

@RestController
@CrossOrigin
@RequestMapping("api/loginstaff")
public class StaffLoginController {
	@Autowired
	private StaffLoginInterface staffLoginInterface;
	
	@GetMapping("/get-name")
	public String staffName(@RequestParam("staff_id")String staffId)
	{
		try
		{
			DatabaseConnector db = new DatabaseConnector();
			db.createConnection();
			ResultSetSerialiser rss = new ResultSetSerialiser();
			ArrayList<HashMap<String,Object>> arr = rss.convert(db.getValue("SELECT * FROM staff_details WHERE staff_id='"+staffId+"'"));
			for(HashMap<String,Object> hash:arr)
			{
				return (String) hash.get("name");
			}
		}
		catch(Exception ex)
		{
			return "Faculty Portal";
		}
		return "Faculty Portal";
	}
	
	@PostMapping("/login")
	public HashMap login(@RequestParam("staff_id")String staffId, @RequestParam("password")String password)
	{
        StaffLogin staff = this.staffLoginInterface.findById(staffId).orElseThrow(()->new ResourceNotFoundException("User Not found"));
        if(password.equals(staff.getPassword()) && staffId == staff.getStaffId())
        {
        	 HashMap<String,String> response = new HashMap<String,String>();
             response.put("staffId",staff.getStaffId());
             response.put("classGroup", staff.getClassGroup());
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
