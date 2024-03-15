package com.mtit.EmployeeSalarySubscriber;

import com.mtit.EmployeeSalaryPublisher.EmployeeSalaryPublisher;

public interface EmployeeSalarySubscriber {
	public void employeeSalaryCalculation(EmployeeSalaryPublisher employeeSalaryPublisher);

}
