package com.EmpDept.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.EmpDept.exception.ResourceNotFoundException;
import com.EmpDept.model.Department;
import com.EmpDept.repository.DepartmentRepository;
/*
 * it is a controller class
 * @RestController which is trying to control request and mark that particular class 
 * as controller class.This annotation work on class level only
 */


@RestController
public class DepartmentController {
	
	

	 
	    /*here in this controller class we have created object of QuestionRepository
	    using @Autowired annotation*/
	
		@Autowired
		private DepartmentRepository departmentRepository;
		
		
		
		@GetMapping( "/departments/test/{departmentId}")
	    public Department getDepartmentDetails(@PathVariable Long departmentId) {
			 
	        return departmentRepository.findAllById(departmentId);
		}
		
		
		/*
		@GetMapping( "/{departmentId}")
	    public Department getBookDetails(@PathVariable Long departmentId) {
			Department departmentResponse = departmentRepository.findAllById(departmentId);
	        return departmentResponse;
	    }*/
	    
		
	
	    //for http get method mapping which fetch data
	    @GetMapping("/departments")
	    public Page<Department> getDepartments(Pageable pageable) {
	    	
	    	//if you want to fetch all data then you will use findAll method of JpaRepository
	        return departmentRepository.findAll(pageable);
	    }
	    
	    /*@PathVariable is use to read dynamic values from path
	     *if we have multiple PathVariable(like follows) then we have to maintain sequence of PathVariable
	     *and also follows standard practice of naming convention as well
	     */
	   
	    
	     
	    @GetMapping("/departments/{name}/{location}")
		public Department getDepartments1(@PathVariable String name, @PathVariable String location) {
	    	
	    	/*to fetch data base on id we have other options also based on name location
	    	 *or base on multiple combination like findByNameAndLocation(or condition also possible)
	    	 *We are not providing implementation of this findByNameAndLocation method but we have to provide entry for this method 
	    	 */
			return departmentRepository.findByNameAndLocation(name, location);
		}
	    
	    
	    
	    /*@RequestBody annotation is used to read a body from http method 
	     *RequestBody is used in case of post and put method only
	     *whenever you are passing an RequestBody it is expected that is accepting thing should be entity 
	     *which will be one to one mapped with whatever value we are passing here
	     *one to one mapped means this title (Department) should get match with department entity
	     */
	     
	    //for http post method mapping which create(insert) data
	    @PostMapping("/departments")
	    public Department createDepartment(@Valid @RequestBody Department department) {
	        return departmentRepository.save(department);
	    }

	    //http://localhost:8080/departments/1051
	    @PutMapping("/departments/{departmentId}")
	    public Department updateDepartment(@PathVariable Long departmentId,
	                                   @Valid @RequestBody Department departmentRequest) {
	    	
	    	/*using  findById method to fetch data base on id to read the id we will use @PathVariable
	    	 *as parameter to read any path variable
	    	 */
	    	
	        return departmentRepository.findById(departmentId)
	        		    .map(dept -> {
	        		    	dept.setName(departmentRequest.getName());
	        		    	dept.setLocation(departmentRequest.getLocation());
	                    return departmentRepository.save(dept);
	                }).orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + departmentId));
	    }

        /*here we fetch departmentId using findById method and then call delete method of departmentRepository and
         * pass that parameter to delete method.
         */
	    @DeleteMapping("/departments/{departmentId}")
	    public ResponseEntity<?> deleteDepartment(@PathVariable Long departmentId) {
	        return departmentRepository.findById(departmentId)
	                .map(dept1 -> {
	                	departmentRepository.delete(dept1);
	                    return ResponseEntity.ok().build();
	                }).orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + departmentId));
	    }
	    
	    
	    
	    
	    
	    

}
