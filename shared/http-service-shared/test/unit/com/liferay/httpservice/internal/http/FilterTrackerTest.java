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
import com.liferay.httpservice.mock.MockFilter;

import javax.servlet.Filter;

import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;

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
	protected void verifyRegisterServiceAction(
			VerificationMode verificationMode)
		throws Exception {

		BundleServletContext bundleServletContext = Mockito.verify(
			this.bundleServletContext, verificationMode);

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

}