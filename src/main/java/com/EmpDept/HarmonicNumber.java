package com.EmpDept;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HarmonicNumber {
	private static final Logger logger = LogManager.getLogger(HarmonicNumber.class);
	float harmonic = 1;
	int num;

	// Method to find Nth harmonic value
	public void harmonicNum() {
		// User Scanner to take input from User
		Scanner s = new Scanner(System.in);
		logger.info("Enter Number :");
		num = s.nextInt();
		s.close();
		// code to find Nth Harmonic
		for (int i = 2; i <= num; i++) {
			harmonic += (float) 1 / i;
		}
		logger.info("Nth Harmonic Value : " + harmonic);
	}

}
