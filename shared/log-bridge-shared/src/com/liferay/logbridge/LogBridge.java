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

package com.liferay.logbridge;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogEntry;
import org.osgi.service.log.LogListener;
import org.osgi.service.log.LogReaderService;
import org.osgi.service.log.LogService;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Raymond Augé
 */
public class LogBridge
	implements BundleActivator, LogListener,
			   ServiceTrackerCustomizer<LogReaderService, LogReaderService> {

	public LogReaderService addingService(
		ServiceReference<LogReaderService> serviceReference) {

		LogReaderService logReaderService = _bundleContext.getService(
			serviceReference);

		logReaderService.addLogListener(this);

		return logReaderService;
	}

	public void logged(LogEntry logEntry) {
		int level = logEntry.getLevel();

		Bundle bundle = logEntry.getBundle();

		String symbolicName = StringUtil.replace(
			bundle.getSymbolicName(), StringPool.PERIOD, StringPool.UNDERLINE);

		Log log = LogFactoryUtil.getLog("osgi.logging." + symbolicName);

		String message = logEntry.getMessage();

		ServiceReference<?> serviceReference = logEntry.getServiceReference();

		if (serviceReference != null) {
			message += " " + serviceReference.toString();
		}

		if ((level == LogService.LOG_DEBUG) && log.isDebugEnabled()) {
			log.debug(message, logEntry.getException());
		}
		else if ((level == LogService.LOG_ERROR) && log.isErrorEnabled()) {
			log.error(message, logEntry.getException());
		}
		else if ((level == LogService.LOG_INFO) && log.isInfoEnabled()) {
			log.info(message, logEntry.getException());
		}
		else if ((level == LogService.LOG_WARNING) && log.isWarnEnabled()) {
			log.warn(message, logEntry.getException());
		}
	}

	public void modifiedService(
		ServiceReference<LogReaderService> serviceReference,
		LogReaderService logReaderService) {
	}

	public void removedService(
		ServiceReference<LogReaderService> serviceReference,
		LogReaderService logReaderService) {

		logReaderService.removeLogListener(this);
	}

	public void start(BundleContext bundleContext) throws Exception {
		_bundleContext = bundleContext;

		_serviceTracker =
			new ServiceTracker<LogReaderService, LogReaderService>(
				bundleContext, LogReaderService.class, this);

		_serviceTracker.open();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		_bundleContext = null;

		_serviceTracker.close();

		_serviceTracker = null;
	}

	private BundleContext _bundleContext;
	private ServiceTracker<LogReaderService, LogReaderService> _serviceTracker;

}