package com.EmpDept.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/*this is an entity/pojo/bean class 
 *in side this class we write properties(variables) like id,name etc
 *mark this class as @entity and @table annotation
 *@Entity specifies that the class is an entity and it is mapped with a database table. 
 *@Table annotation specifies the name of the database table to be used for mapping.
 */


@Entity
@Table(name="department")
public class Department implements Serializable {
	//@Id assign primary key for  id 
	/*@GeneratedValue using with @Id annotation which means it enable an id generation. 
	Which means that it will generate an Id value for us and handle the auto incrementing. */
	
	@Id
    @GeneratedValue(generator = "department_generator")
    @SequenceGenerator(
            name = "department_generator",
            sequenceName = "department_sequence",
            initialValue = 1000
    )
	private long id;
	
	@NotBlank
	@Size(min=2,max=100)
	private String name;
	
	@Column
	private String location;
	
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "department")
    private Set<Employee> employee = new HashSet<>();
	
	
	

	
	
	//write getter and setter methods for properties we have mention in this entity class
	
	
	public long getId() {
		return id;
	}
	

	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public Set<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(Set<Employee> employee) {
		this.employee = employee;
	}
	
	

}
