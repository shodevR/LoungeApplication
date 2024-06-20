package com.app.services;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.exceptions.EmployeeException;
import com.app.model.Employee;
import com.app.repository.EmployeeRepo;



@Service
public class EmployeeImpl implements EmployeeService{

	@Autowired
	private EmployeeRepo eRepo;
	
	@Override
	public List<Employee> viewAllEmployees() throws EmployeeException {
		List<Employee>employees = eRepo.findAll();
		if (employees.size()>0) {
			return employees;
		}else {
			throw new EmployeeException("No Employees Found");
		}	
	}

	@Override
	public Employee addEmployee(Employee employee) throws EmployeeException {
		// TODO Auto-generated method stub
		String username = employee.getUsername();
		
		   Employee existingEmployee = eRepo.findUniqueByUsername(username); 
		   if (existingEmployee.getUsername() == username)
		   {
			   throw new EmployeeException("Username already exists: " + username); 
		   }else {
			Employee addEmployee = eRepo.save(employee);
			return addEmployee;
		}
		 
		
	}

	@Override
	public Employee updateEmployee(Employee employee) throws EmployeeException {
		Optional<Employee>optional = eRepo.findById(employee.getEmployeeId());
		if (optional.isPresent()) {
			return eRepo.save(employee);
			
		}else {
			throw new EmployeeException("Invalid Details");
		}
	}

	@Override
	public Employee removeEmployee(Integer employeeId) throws EmployeeException {
		Employee existEmployee = eRepo.findById(employeeId).orElseThrow(()-> new EmployeeException("Employee does not exist wit the id : "+ employeeId));	
		eRepo.delete(existEmployee);
		return existEmployee;
	}

	@Override
	public String uploadFile(Integer id, MultipartFile file) throws EmployeeException {
		 String directoryPath = System.getProperty("user.dir") + "/Uploads/" +id;
		    File directory = new File(directoryPath);

		    // Create the directory if it doesn't exist
		    if (!directory.exists()) {
		        directory.mkdirs();
		    }

		    try {
		        // Save the file inside the employee's directory
		        String filePath = directoryPath + "/" + file.getOriginalFilename();
		        FileOutputStream fout = new FileOutputStream(filePath);
		        fout.write(file.getBytes());
		        fout.close();
		         // Set the file path in employee details
		    } catch (Exception e) {
		        e.printStackTrace();
		        // Handle file upload error
		    }
		    return directoryPath;
	}

}
