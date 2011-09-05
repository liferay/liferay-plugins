package com.liferay.osgi.http;

import org.eclipse.equinox.http.servlet.HttpServiceServlet;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	public void start(BundleContext bundleContext) throws Exception {
		_serviceRegistration = bundleContext.registerService(
			HttpServiceServletWrapper.class.getName(),
			new HttpServiceServletWrapper(new HttpServiceServlet()), null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		_serviceRegistration.unregister();

		_serviceRegistration = null;
	}

	private static ServiceRegistration _serviceRegistration;

}