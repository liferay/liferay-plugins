/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.httpservice.internal.http;

import com.liferay.httpservice.internal.servlet.BundleServletContext;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpContext;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Miguel Pastor
 * @author Raymond Augé
 */
public abstract class BaseServiceTrackerCustomizer<S, T>
	implements ServiceTrackerCustomizer<S, T> {

	public BaseServiceTrackerCustomizer(HttpSupport httpSupport) {
		this.httpSupport = httpSupport;
	}

	@Override
	public T addingService(ServiceReference<S> serviceReference) {
		BundleContext bundleContext = httpSupport.getBundleContext();

		T service = (T)bundleContext.getService(serviceReference);

		return doAction(serviceReference, service, ACTION_ADDING);
	}

	@Override
	public void modifiedService(
		ServiceReference<S> serviceReference, T service) {

		doAction(serviceReference, service, ACTION_MODIFIED);
	}

	@Override
	public void removedService(
		ServiceReference<S> serviceReference, T service) {

		doAction(serviceReference, service, ACTION_REMOVED);
	}

	protected T doAction(
		ServiceReference<S> serviceReference, T service, int action) {

		Map<String, String> initParameters = new HashMap<String, String>();

		if (action != ACTION_REMOVED) {
			for (String key : serviceReference.getPropertyKeys()) {
				if (key.startsWith("init.")) {
					String value = GetterUtil.getString(
						serviceReference.getProperty(key));

					initParameters.put(key.substring(5), value);
				}
			}

			int serviceRanking = GetterUtil.getInteger(
				serviceReference.getProperty(Constants.SERVICE_RANKING));

			initParameters.put(
				Constants.SERVICE_RANKING, String.valueOf(serviceRanking));
		}

		Bundle bundle = serviceReference.getBundle();

		try {
			BundleServletContext bundleServletContext =
				httpSupport.getBundleServletContext(bundle);

			if (action != ACTION_ADDING) {
				unregisterService(bundleServletContext, serviceReference);
			}

			if (action != ACTION_REMOVED) {
				String contextId = GetterUtil.getString(
					serviceReference.getProperty("contextId"));

				HttpContext httpContext = httpSupport.getHttpContext(contextId);

				if (httpContext == null) {
					httpContext = bundleServletContext.getHttpContext();
				}

				registerService(
					bundleServletContext, serviceReference, service,
					initParameters, httpContext);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return service;
	}

	protected abstract void registerService(
			BundleServletContext bundleServletContext,
			ServiceReference<S> serviceReference, T service,
			Map<String, String> initParameters, HttpContext httpContext)
		throws Exception;

	protected abstract void unregisterService(
		BundleServletContext bundleServletContext,
		ServiceReference<S> serviceReference);

	protected static final int ACTION_ADDING = 0;

	protected static final int ACTION_MODIFIED = 1;

	protected static final int ACTION_REMOVED = 2;

	protected HttpSupport httpSupport;

	private static Log _log = LogFactoryUtil.getLog(
		BaseServiceTrackerCustomizer.class);

}