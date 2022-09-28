package com.EmpDept.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.EmpDept.model.Employee;

@Repository
public interface EmployeeReopsitory extends JpaRepository<Employee, Long> {

	List<Employee> findByDepartmentId(Long departmentId);

	@Query("select e from Employee e where e.name=:name")
	List<Employee> getEmployeesSortedInfo(String name);

}
