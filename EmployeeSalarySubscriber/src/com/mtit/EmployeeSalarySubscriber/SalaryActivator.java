package com.mtit.EmployeeSalarySubscriber;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.mtit.EmployeeSalaryPublisher.EmployeeSalaryPublisher;

public class SalaryActivator implements BundleActivator {
	ServiceReference serviceReference;

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		System.out.println("Employee service Subscriber Started!!");
		serviceReference = context.getServiceReference(EmployeeSalaryPublisher.class.getName());
		
		EmployeeSalaryPublisher employeeSalaryPublisher = (EmployeeSalaryPublisher)context.getService(serviceReference);
		EmployeeSalarySubscriber employeeSalarySubscriber = new EmployeeSalarySubscriberImpl();
		employeeSalarySubscriber.employeeSalaryCalculation(employeeSalaryPublisher); 
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		System.out.println("Employee service Subscriber Stopped!!");
		context.ungetService(serviceReference);
	}

}
