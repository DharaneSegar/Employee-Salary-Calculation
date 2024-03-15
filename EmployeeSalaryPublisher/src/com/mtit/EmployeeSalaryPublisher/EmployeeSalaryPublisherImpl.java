package com.mtit.EmployeeSalaryPublisher;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class EmployeeSalaryPublisherImpl implements EmployeeSalaryPublisher {

	@Override
	public float calcSalaryForBasicEmployee(int year, int month, int noOfLeaves, float rate) {
		// TODO Auto-generated method stub
		
		LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);
        int weekdays = 0;

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                weekdays++;
            }
        }
        
        int workingDays = weekdays - noOfLeaves;
		return workingDays * rate * 8 ;
	}

	@Override
	public float calcSalaryForContractEmployee(int days, float rate) {
		// TODO Auto-generated method stub
		return days * rate * 8;
	}

	@Override
	public void writeSalaryDetails(String empNo, String name, String type, int month, int year, float salary,
			LocalDate date) {
		// TODO Auto-generated method stub
		// Write the salary details to a file
		try (PrintWriter writer = new PrintWriter(new FileWriter("D:/Downloads/Y3S2/SA/Assignment 1/salary_details.txt", true))) {
		    writer.printf("Employee No: %s%n", empNo);
		    writer.printf("Name: %s%n", name);
		    writer.printf("Type: %s%n", (type.equals("B") ? "Basic" : "Contract"));
		    writer.printf("Month: %d%n", month);
		    writer.printf("Year: %d%n", year);
		    writer.printf("Salary: Rs. %.2f%n", salary);
		    writer.printf("Calculated Date: %s%n", date);
		    writer.println("-----------------------------------");
		} catch (IOException e) {
		    System.err.println("Error writing to file: " + e.getMessage());
		    e.printStackTrace();
		}

		
	}

}
