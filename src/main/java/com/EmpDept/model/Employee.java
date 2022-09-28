package com.EmpDept.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*this is an entity/pojo/bean class 
 *in side this class we write properties(variables) like id,name etc
 *mark this class as @entity and @table annotation
 *@Entity specifies that the class is an entity.
 *@Table annotation specifies the name of the database table to be used for mapping.
 */

@Entity
@Table(name="employee")
public class Employee implements Serializable   {
	    //@Id assign primary key for  id 
		/*@GeneratedValue using with @Id annotation which means it enable an id generation. 
		Which means that it will generate an Id value for us and handle the auto incrementing. */
		
	
	    @Id
	    @GeneratedValue(generator = "employee_generator")
	    @SequenceGenerator(
	            name = "employee_generator",
	            sequenceName = "employee_sequence",
	            initialValue = 1000
	    )
	
		private long id;
		//@column is use to specifies detail of column to which field is get mapped
	    //we can specifies column annotation with different attributes(like name,length) 
		@Column
		private String name;
		
		@Column
		private String email;
		@Column
		private long phone_no;
		@Column
		private int salary;
		
		//A many-to-one mapping means that many instances(columns) of this entity are mapped
		//to one instance of another entity 
		
		@ManyToOne(fetch = FetchType.LAZY, optional = false)
		@JoinColumn(name = "department_id", nullable = false)
		@OnDelete(action = OnDeleteAction.CASCADE)
		@JsonIgnore
		private Department department;
		
		
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
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public long getPhone_no() {
			return phone_no;
		}
		public void setPhone_no(long phone_no) {
			this.phone_no = phone_no;
		}
		public int getSalary() {
			return salary;
		}
		public void setSalary(int salary) {
			this.salary = salary;
		}
		public Department getDepartment() {
			return department;
		}
		public void setDepartment(Department department) {
			this.department = department;
		}
		
		
	
	}
