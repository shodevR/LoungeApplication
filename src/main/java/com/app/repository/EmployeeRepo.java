package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

	@Query("SELECT e FROM Employee e WHERE e.username = :username")
	Employee findUniqueByUsername(@Param("username") String username);

}
