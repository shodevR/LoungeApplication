package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.exceptions.EmployeeException;
import com.app.model.Employee;
import com.app.services.EmployeeService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/addEmployee")
	public ResponseEntity<Employee>addEmployeeEntity(@RequestBody Employee employee )throws EmployeeException{
		/*
		 * String fileName = file.getOriginalFilename(); String filepath =
		 * "D:\\STSWorkspace\\LoungeTool\\EmployeeDocuments\\"+employee.getEmployeeId()+"
		 * /"; try { file.transferTo( new File(filepath + fileName)); } catch (Exception
		 * e) { return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		 * }
		 * 
		 * employee.setDocument(filepath);
		 */
		Employee addEmployee = employeeService.addEmployee(employee);
		
		
		return new ResponseEntity<Employee>(addEmployee,HttpStatus.CREATED);
		
	}
	@PostMapping("/file/{id}")
	public ResponseEntity<String>uploadFilEntity(@PathVariable("id") Integer id, MultipartFile file)throws EmployeeException{
		String string = employeeService.uploadFile(id, file);
		return new ResponseEntity<String>(string,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Employee>>getAllEmployee()throws EmployeeException {
	List<Employee>employees = employeeService.viewAllEmployees();
	return new ResponseEntity<List<Employee>>(employees,HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Employee> employeeUpdatEntity(@RequestBody Employee employee)throws EmployeeException {
		//TODO: process PUT request
		Employee existingEmployee = employeeService.updateEmployee(employee);
		return new ResponseEntity<Employee>(existingEmployee,HttpStatus.OK);
		
		
	}
	@DeleteMapping("/delete")
	public ResponseEntity<Employee>deleteEmployee(@RequestParam Integer employeeID) throws EmployeeException{
		Employee deleteEmployee = employeeService.removeEmployee(employeeID);
		return new ResponseEntity<Employee>(deleteEmployee, HttpStatus.OK);
	}
}
