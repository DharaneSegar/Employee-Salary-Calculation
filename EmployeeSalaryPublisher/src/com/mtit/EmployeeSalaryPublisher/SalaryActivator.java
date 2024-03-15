package com.mtit.EmployeeSalaryPublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class SalaryActivator implements BundleActivator {
	ServiceRegistration publishServiceRegistration;

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		System.out.println("Employee Salary Publisher Started!!");
		EmployeeSalaryPublisherImpl employeeSalaryPublisherService = new EmployeeSalaryPublisherImpl();
		publishServiceRegistration = context.registerService(EmployeeSalaryPublisher.class.getName(), employeeSalaryPublisherService, null);

	}
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		System.out.println("Employee Salary Publisher Stopped!!");
		publishServiceRegistration.unregister();

	}

}
