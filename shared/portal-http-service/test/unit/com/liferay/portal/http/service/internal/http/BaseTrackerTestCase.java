/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.portal.http.service.internal.http;

import com.liferay.portal.http.service.internal.servlet.BundleServletContext;
import com.liferay.portal.kernel.xml.DocumentException;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpContext;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Miguel Pastor
 */
public abstract class BaseTrackerTestCase<S> extends PowerMockito {

	@Before
	public void setUp() {
		service = buildService();
		_tracker = buildTracker();
	}

	@Test
	public void testAddingServiceWithEmptyInitParameters() throws Exception {
		mockAddingService(new String[0]);

		_tracker.addingService(_serviceReference);

		verifyAddingService(Mockito.never());
	}

	@Test
	public void testAddingServiceWithInitParameters() throws Exception {
		mockAddingService(new String[] {"init.a", "init.b", "foo.c"});

		_tracker.addingService(_serviceReference);

		verifyAddingService(Mockito.never());
	}

	@Test
	public void testRemovedService() throws Exception {
		mockRemovedService();

		_tracker.removedService(_serviceReference, service);

		verifyRemovedService();
	}

	protected abstract S buildService();

	protected abstract ServiceTrackerCustomizer<S, S> buildTracker();

	protected void mockAction(String[] initParameters)
		throws DocumentException, InvalidSyntaxException {

		when(
			bundleContext.getService(_serviceReference)
		).thenReturn(
			service
		);

		when(
			httpServiceWrapper.getBundleServletContext()
		).thenReturn(
			bundleServletContext
		);

		when(
			httpSupport.getHttpContext(Mockito.anyString())
		).thenReturn(
			httpContext
		);

		when(
			httpSupport.getHttpService(bundle)
		).thenReturn(
			httpServiceWrapper
		);

		when(
			_serviceReference.getBundle()
		).thenReturn(
			bundle
		);
	}

	protected void mockAddingService(String[] initParameters) throws Exception {
		when(
			httpSupport.getBundleContext()
		).thenReturn(
			bundleContext
		);

		when(
			_serviceReference.getPropertyKeys()
		).thenReturn(
			initParameters
		);

		mockAction(initParameters);
	}

	protected void mockRemovedService() throws Exception {
		mockAction(null);
	}

	protected void verifyAddingService(VerificationMode verificationMode)
		throws Exception {

		BundleContext bundleContext = Mockito.verify(this.bundleContext);

		bundleContext.getService(_serviceReference);

		HttpSupport httpSupport = Mockito.verify(this.httpSupport);

		httpSupport.getBundleContext();
		httpSupport.getBundleServletContext(bundle);
		httpSupport.getHttpContext(Mockito.anyString());

		verifyRegisterServiceAction(verificationMode);
		verifyServiceReference();
	}

	protected abstract void verifyRegisterServiceAction(
			VerificationMode verificationMode)
		throws Exception;

	protected void verifyRemovedService() throws Exception {
		verifyServiceReference();
		verifyUnRegisterServiceAction();
	}

	protected void verifyServiceReference() {
		ServiceReference<S> serviceReference = Mockito.verify(
			_serviceReference);

		serviceReference.getBundle();
		serviceReference.getPropertyKeys();
	}

	protected abstract void verifyUnRegisterServiceAction() throws Exception;

	@Mock
	protected Bundle bundle;

	@Mock
	protected BundleContext bundleContext;

	@Mock
	protected BundleServletContext bundleServletContext;

	@Mock
	protected HttpContext httpContext;

	@Mock
	protected HttpServiceWrapper httpServiceWrapper;

	@Mock
	protected HttpSupport httpSupport;

	protected S service;

	@Mock
	private ServiceReference<S> _serviceReference;

	private ServiceTrackerCustomizer<S, S> _tracker;

}