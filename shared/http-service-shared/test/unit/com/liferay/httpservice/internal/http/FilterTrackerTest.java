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

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.osgi.util.tracker.ServiceTrackerCustomizer;

import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Miguel Pastor
 */
@RunWith(PowerMockRunner.class)
public class FilterTrackerTest extends BaseTrackerTestCase<Filter> {

	@Override
	protected Filter buildService() {
		return new MockFilter();
	}

	@Override
	protected ServiceTrackerCustomizer<Filter, Filter> buildTracker() {
		return new FilterTracker(httpSupport);
	}

	@Override
	protected void verifyRegisterServiceAction() throws Exception {
		BundleServletContext bundleServletContext = Mockito.verify(
			this.bundleServletContext);

		bundleServletContext.registerFilter(
			Mockito.anyString(), Mockito.anyString(), Mockito.eq(service),
			Mockito.anyMap(), Mockito.eq(httpContext));
	}

	@Override
	protected void verifyUnRegisterServiceAction() throws Exception {
		BundleServletContext bundleServletContext = Mockito.verify(
			this.bundleServletContext);

		bundleServletContext.unregisterFilter(Mockito.anyString());
	}

	private class MockFilter implements Filter {

		@Override
		public void destroy() {
		}

		@Override
		public void doFilter(
			ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) {
		}

		@Override
		public void init(FilterConfig filterConfig) {
		}

	}

}