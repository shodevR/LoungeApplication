package com.app.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.exceptions.EmployeeException;
import com.app.model.Employee;

public interface EmployeeService {
	public List<Employee>viewAllEmployees() throws EmployeeException;
	public Employee addEmployee(Employee employee) throws EmployeeException;
	public Employee updateEmployee(Employee employee) throws EmployeeException;
	public Employee removeEmployee(Integer employeeId) throws EmployeeException;
	public String uploadFile(Integer id, MultipartFile file) throws EmployeeException;

}
