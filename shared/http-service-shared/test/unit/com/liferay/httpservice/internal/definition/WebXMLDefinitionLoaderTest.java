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

package com.liferay.httpservice.internal.definition;

import com.liferay.httpservice.test.MockBundle;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.xml.SAXReaderImpl;

import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletContextListener;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;

import org.osgi.framework.Bundle;

import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Miguel Pastor
 */
@RunWith(PowerMockRunner.class)
public class WebXMLDefinitionLoaderTest {

	@BeforeClass
	public static void setUp() {
		SAXReaderUtil saxReaderUtil = new SAXReaderUtil();

		saxReaderUtil.setSAXReader(SAXReaderImpl.getInstance());
	}

	public WebXMLDefinitionLoaderTest() throws DocumentException {
		_webXMLDefinitionLoader = new WebXMLDefinitionLoader();
	}

	@Test
	public void testLoadDefaultDependencies() throws Exception {
		WebXMLDefinition webXMLDefinition = _webXMLDefinitionLoader.loadWebXML(
			_bundle);

		List<ListenerDefinition> listenerDefinitions =
			webXMLDefinition.getListenerDefinitions();

		Assert.assertEquals(
			_LISTENER_DEFAULT_CLASS_NAMES.length, listenerDefinitions.size());

		for (ListenerDefinition listenerDefinition : listenerDefinitions) {
			Object listener = listenerDefinition.getListener();

			Assert.assertTrue(listener instanceof ServletContextListener);
		}
	}

	private static final String[] _LISTENER_DEFAULT_CLASS_NAMES = {
		"org.eclipse.jetty.servlet.listener.ELContextCleaner",
		"org.eclipse.jetty.servlet.listener.IntrospectorCleaner"
	};

	private static final String[] _SERVLET_DEFAULT_CLASSE_NAMES = {
		"com.liferay.httpservice.servlet.ResourceServlet",
		"org.apache.jasper.servlet.JspServlet"
	};

	private Bundle _bundle = new CustomClassLoaderMockBundle();

	@Mock
	private Servlet _servlet;

	@Mock
	private ServletContextListener _servletContextListener;

	private WebXMLDefinitionLoader _webXMLDefinitionLoader;

	private class CustomClassLoaderMockBundle extends MockBundle {

		@Override
		public Class<?> loadClass(String className)
			throws ClassNotFoundException {

			if (ArrayUtil.contains(_LISTENER_DEFAULT_CLASS_NAMES, className)) {
				return _servletContextListener.getClass();
			}
			else if (ArrayUtil.contains(
						_SERVLET_DEFAULT_CLASSE_NAMES, className)) {

				return _servlet.getClass();
			}
			else {
				return super.loadClass(className);
			}
		}

	}

}