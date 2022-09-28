package com.EmpDept.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EmpDept.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	

	//this method is use to fetch data based on name 
	
	//Department findByLocation(String location);
	
	//this method is use to fetch data based on name and location
	Department findByNameAndLocation(String name, String location);

	Department findAllById(Long departmentId);

}
