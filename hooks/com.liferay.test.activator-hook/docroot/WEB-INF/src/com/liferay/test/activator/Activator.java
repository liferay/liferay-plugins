package com.liferay.test.activator;

import com.liferay.portal.service.UserLocalService;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	public final void start(BundleContext bundleContext) throws Exception {
		Hashtable<String,Object> properties = new Hashtable<String,Object>();

		properties.put("portal.service", Boolean.TRUE);
		properties.put("portal.service.wrapper", Boolean.TRUE);
		properties.put(
			"portal.service.type", UserLocalService.class.getName());

		_serviceRegistration = bundleContext.registerService(
			UserLocalService.class.getName(),
			new CustomUserLocalService(), properties);
	}

	public final void stop(BundleContext bundleContext) throws Exception {
		if (_serviceRegistration != null) {
			_serviceRegistration.unregister();

			_serviceRegistration = null;
		}
	}

	private ServiceRegistration _serviceRegistration;

}