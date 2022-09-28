package com.EmpDept;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*This is a stating or main class of our application becoz it contain main method 
 * so application start executing from this class
 */

@SpringBootApplication
@EnableJpaAuditing
//@ComponentScan
//@ComponentScans
public class EmpDeptDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpDeptDemoApplication.class, args);
	}

}

/*@Configuration annotation indicates that a class declares one or more @Bean methods and may be processed 
 * by the Spring container to generate bean definitions and service requests for those beans at runtime.
 */
