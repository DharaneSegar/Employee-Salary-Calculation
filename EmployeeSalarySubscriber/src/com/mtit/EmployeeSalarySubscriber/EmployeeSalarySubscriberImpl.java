package com.mtit.EmployeeSalarySubscriber;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

import com.mtit.EmployeeSalaryPublisher.EmployeeSalaryPublisher;

public class EmployeeSalarySubscriberImpl implements EmployeeSalarySubscriber {

    @Override
    public void employeeSalaryCalculation(EmployeeSalaryPublisher employeeSalaryPublisher) {
        Scanner scanner = new Scanner(System.in);
        String empNo;
        String name;
        int month;
        int year;
        String type;
        float rate;
        int days;
        int noOfLeaves;
        float salary;
        boolean continueInput = true;

        while (continueInput) {
            System.out.println("Enter employee number:");
            empNo = scanner.nextLine();

            System.out.println("Enter employee name:");
            name = scanner.nextLine();

            // Get current month and year
            LocalDate currentDate = LocalDate.now();
            year = currentDate.getYear();
            int currentMonth = currentDate.getMonthValue();

            System.out.println("Salary is calculated for the year " + year);

            // Read month and validate
            do {
                System.out.println("Enter month (1-" + currentMonth + "):");
                month = scanner.nextInt();

                if (month < 1 || month > currentMonth) {
                    System.out.println("Invalid month. Please enter a month between 1 and " + currentMonth + ".");
                }
            } while (month < 1 || month > currentMonth);

            // Read employee type until it's "B" or "C"
            do {
                System.out.println("Enter employee type:");
                System.out.println("Enter B for Basic type");
                System.out.println("Enter C for Contract type");
                type = scanner.next().toUpperCase(); // Convert input to uppercase for case-insensitive comparison

                // Check if input is not "B" or "C" and print error message
                if (!type.equals("B") && !type.equals("C")) {
                    System.out.println("Invalid employee type. Please enter B or C.");
                }
            } while (!type.equals("B") && !type.equals("C"));

            if (type.equals("C")) {
                System.out.println("Enter no of days worked:");
                days = scanner.nextInt();

                System.out.println("Enter rate per hour:");
                rate = scanner.nextFloat();

                salary = employeeSalaryPublisher.calcSalaryForContractEmployee(days, rate);
            } else {
                System.out.println("Enter no of leaves taken:");
                noOfLeaves = scanner.nextInt();

                System.out.println("Enter rate per hour:");
                rate = scanner.nextFloat();

                salary = employeeSalaryPublisher.calcSalaryForBasicEmployee(year, month, noOfLeaves, rate);
            }
            
            //Method call to file write
            employeeSalaryPublisher.writeSalaryDetails(empNo, name, type, month, year, salary, currentDate);
            
            // Print employee details in tabular format
            System.out.println("Employee Details:");
            System.out.println("----------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-15s | %-15s | %-10s | %-10s | %-10s | %-15s | %-15s\n", "Employee No", "Name", "Type", "Month", "Year", "Salary", "Calculated Date");
            System.out.println("----------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-15s | %-15s | %-10s | %-10s | %-10s | Rs. %-10s | %-20s\n", empNo, name, (type.equals("B") ? "Basic" : "Contract"), month, year, salary, currentDate);
            System.out.println("---------------------------------------------------------------------------------------------------------------");


            // Ask if the user wants to continue
            System.out.println("Do you want to continue? (yes/no)");
            scanner.nextLine(); // consume the newline character left by nextInt() or nextFloat() to prevent skipping nextLine()
            String continueChoice = scanner.nextLine();

            if (!continueChoice.equalsIgnoreCase("yes")) {
                continueInput = false;
            }
        }
    }

	
}
