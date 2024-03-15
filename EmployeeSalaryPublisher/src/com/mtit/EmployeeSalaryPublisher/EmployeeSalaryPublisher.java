package com.mtit.EmployeeSalaryPublisher;

import java.time.LocalDate;

public interface EmployeeSalaryPublisher {
	public float calcSalaryForBasicEmployee(int year, int month, int noOfLeaves, float rate);
	public float calcSalaryForContractEmployee(int days,float rate);
	public void writeSalaryDetails(String empNo, String name, String type , int month, int year, float salary, LocalDate date);

}
