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

package com.liferay.portal.http.service.internal.definition;

import com.liferay.portal.http.service.test.MockBundle;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.xml.SAXReaderImpl;

import java.net.URL;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextListener;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;

import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Miguel Pastor
 */
@RunWith(PowerMockRunner.class)
public class WebXMLDefinitionLoaderTest {

	@BeforeClass
	public static void setUpClass() {
		SAXReaderUtil saxReaderUtil = new SAXReaderUtil();

		saxReaderUtil.setSAXReader(SAXReaderImpl.getInstance());
	}

	public WebXMLDefinitionLoaderTest() throws DocumentException {
		_webXMLDefinitionLoader = new WebXMLDefinitionLoader();
	}

	@Test
	public void testLoadCustomDependencies() throws Exception {
		Bundle bundle = new EntryLoaderMockBundle();

		testLoadDependencies(bundle, 1, 1, 1);
	}

	protected void testLoadDependencies(
			Bundle bundle, int numfOfListeners, int numOfFilters,
			int numfOfServlets)
		throws Exception {

		WebXMLDefinition webXMLDefinition = _webXMLDefinitionLoader.loadWebXML(
			bundle);

		List<ListenerDefinition> listenerDefinitions =
			webXMLDefinition.getListenerDefinitions();

		Assert.assertEquals(numfOfListeners, listenerDefinitions.size());

		for (ListenerDefinition listenerDefinition : listenerDefinitions) {
			Object listener = listenerDefinition.getListener();

			Assert.assertTrue(listener instanceof ServletContextListener);
		}

		Map<String, ServletDefinition> servletDefinitions =
			webXMLDefinition.getServletDefinitions();

		Assert.assertEquals(numfOfServlets, servletDefinitions.size());

		Map<String, FilterDefinition> filterDefinitions =
			webXMLDefinition.getFilterDefinitions();

		Assert.assertEquals(numOfFilters, filterDefinitions.size());
	}

	private WebXMLDefinitionLoader _webXMLDefinitionLoader;

	private class EntryLoaderMockBundle extends MockBundle {

		@Override
		public URL getEntry(String path) {
			Class<?> clazz = getClass();

			return clazz.getResource("dependencies/custom-web.xml");
		}

	}

}