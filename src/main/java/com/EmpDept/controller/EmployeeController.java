package com.EmpDept.controller;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.EmpDept.exception.ResourceNotFoundException;
import com.EmpDept.model.Department;
import com.EmpDept.model.Employee;
import com.EmpDept.repository.DepartmentRepository;
import com.EmpDept.repository.EmployeeReopsitory;
@Controller
//@RequestMapping
@ResponseBody
//@RestController
public class EmployeeController {
	
	@Autowired
    private EmployeeReopsitory employeeReopsitory;

    @Autowired
    private DepartmentRepository departmentRepository;

    
    
    @GetMapping("/departments/{departmentId}/employee")
    public List<Employee> getEmployeeByDepartmentId(@PathVariable Long departmentId) {
        return employeeReopsitory.findByDepartmentId(departmentId);
    }
    
    @GetMapping("/departments/{name}")
	public List<Employee> getEmployee(@PathVariable String name) {
    	System.out.println("******");
    	
		return employeeReopsitory.getEmployeesSortedInfo(name);
	}
    
    @ResponseBody
    @RequestMapping(value="/test/departments")
    public List<Employee> getEmployeesOfDepartment1() {
        List<Employee> employeeresponse = (List<Employee>) employeeReopsitory.findAll();
        return employeeresponse;
    }
    
    
    @PostMapping("/departments/{departmentId}/employee")
    public Employee addEmployee(@PathVariable Long departmentId,
                            @Valid @RequestBody Employee employee) {
        return departmentRepository.findById(departmentId)
                .map(dept -> {
                    employee.setDepartment(dept);
                    return employeeReopsitory.save(employee);
                }).orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + departmentId));
    }

    @PutMapping("/departments/{departmentId}/employee/{employeeId}")
    public Employee updateEmployee(@PathVariable Long departmentId,
                               @PathVariable Long employeeId,
                               @Valid @RequestBody Employee employeeRequest) {
        if(!departmentRepository.existsById(departmentId)) {
            throw new ResourceNotFoundException("Department not found with id " + departmentId);
        }

        return employeeReopsitory.findById(employeeId)
                .map(emp -> {
                	//emp.setName(employeeRequest.getName());
                	emp.setEmail(employeeRequest.getEmail());
                    return employeeReopsitory.save(emp);
                }).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + employeeId));
    }

    @DeleteMapping("/departments/{departmentId}/employee/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long departmentId,
                                          @PathVariable Long employeeId) {
        if(!departmentRepository.existsById(departmentId)) {
            throw new ResourceNotFoundException("Department not found with id " + departmentId);
        }

        return employeeReopsitory.findById(employeeId)
                .map(emp1 -> {
                	employeeReopsitory.delete(emp1);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + employeeId));

    }

}
