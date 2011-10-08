package com.liferay.test.simple.servlet;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	static BundleContext getBundleContext() {
		return _bundleContext;
	}

	public void start(BundleContext bundleContext) throws Exception {
		_bundleContext = bundleContext;

		_serviceTracker = new HttpServiceTracker(bundleContext);
		_serviceTracker.open();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		_serviceTracker.close();
		_serviceTracker = null;

		_bundleContext = null;
	}

	private static BundleContext _bundleContext;

	private HttpServiceTracker _serviceTracker;

}