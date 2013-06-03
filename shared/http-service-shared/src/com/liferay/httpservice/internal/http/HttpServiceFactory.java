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

import org.osgi.framework.Bundle;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.http.HttpService;

/**
 * @author Raymond Augé
 * @author Miguel Pastor
 */
public class HttpServiceFactory implements ServiceFactory<HttpService> {

	public HttpServiceFactory(HttpSupport httpSupport) {
		_httpSupport = httpSupport;
	}

	@Override
	public HttpService getService(
		Bundle bundle, ServiceRegistration<HttpService> serviceRegistration) {

		try {
			BundleServletContext bundleServletContext =
				_httpSupport.getWABBundleServletContext(bundle);

			if (bundleServletContext != null) {
				return new HttpServiceWrapper(bundleServletContext);
			}

			bundleServletContext = _httpSupport.getNonWABBundleServletContext(
				bundle);

			return new NonWABHttpServiceWrapper(bundleServletContext);
		}
		catch (ClassCastException cce) {
			_log.error(cce, cce);

			return null;
		}
		catch (InvalidSyntaxException ise) {
			throw new IllegalStateException(ise);
		}
	}

	@Override
	public void ungetService(
		Bundle bundle, ServiceRegistration<HttpService> serviceRegistration,
		HttpService httpService) {
	}

	private static Log _log = LogFactoryUtil.getLog(HttpServiceFactory.class);

	private HttpSupport _httpSupport;

}