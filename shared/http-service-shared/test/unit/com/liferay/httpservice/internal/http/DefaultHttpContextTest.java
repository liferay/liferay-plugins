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

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.Mockito;

import org.osgi.framework.Bundle;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * @author Miguel Pastor
 */
@RunWith(PowerMockRunner.class)
public class DefaultHttpContextTest extends PowerMockito {

	@Before
	public void setUp() {
		_defaultHttpContext = new DefaultHttpContext(_bundle);
	}

	@Test
	public void testGetExistingResourceWithNoSlash() throws Exception {
		mockBundleFindEntries();

		getResourceAndVerify("existingResource");

		verifyBundleFindEntries();
	}

	@Test
	public void testGetExistingResourceWithSlash() throws Exception {
		mockBundleFindEntries();

		getResourceAndVerify("/existingResource");

		verifyBundleFindEntries();
	}

	@Test
	public void testGetNonExistingResourceNoSlash() {
		Assert.assertNull(
			_defaultHttpContext.getResource("nonExistingResource"));
	}

	@Test
	public void testGetNonExistingResourceWithSlash() {
		Assert.assertNull(
			_defaultHttpContext.getResource("nonExistingResource"));
	}

	@Test
	public void testHandleSecurity() {
		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();
		MockHttpServletResponse mockHttpServletResponse =
			new MockHttpServletResponse();

		Assert.assertTrue(
			_defaultHttpContext.handleSecurity(
				mockHttpServletRequest, mockHttpServletResponse));
	}

	protected void getResourceAndVerify(String path) {
		URL url = _defaultHttpContext.getResource(path);

		Assert.assertNotNull(url);
		Assert.assertEquals(_FILE, url.getFile());
		Assert.assertEquals(_HOST, url.getHost());
		Assert.assertEquals(_PROTOCOL, url.getProtocol());
	}

	protected void mockBundleFindEntries() throws MalformedURLException {
		when(
			_bundle.getResource(Mockito.anyString())
		).thenReturn(
			new URL(_PROTOCOL, _HOST, _FILE)
		);
	}

	protected void verifyBundleFindEntries() {
		Bundle bundle = Mockito.verify(_bundle);

		bundle.getResource(Mockito.anyString());
	}

	private static final String _FILE = "/tmp/foo";

	private static final String _HOST = "localhost";

	private static final String _PROTOCOL = "file";

	@Mock
	private Bundle _bundle;

	private DefaultHttpContext _defaultHttpContext;

}