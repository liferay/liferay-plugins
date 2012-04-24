/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.testpacl.portlet;

import com.liferay.chat.service.EntryLocalService;
import com.liferay.chat.service.EntryLocalServiceClp;
import com.liferay.chat.service.EntryLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.blogs.service.BlogsEntryLocalService;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.testpacl.service.FooLocalService;
import com.liferay.testpacl.service.FooLocalServiceUtil;
import com.liferay.testpacl.util.TestPACLUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.File;
import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class TestPACLPortlet extends MVCPortlet {

	@Override
	public void destroy() {
		testWriteFile();
	}

	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		testGetClassLoaderBlogsEntryLocalService();
		testGetClassLoaderEntryLocalService();
		testGetClassLoaderFooLocalService();
		testGetClassLoaderPortal();
		testGetClassLoaderTestPACLUtil();

		include(viewTemplate, renderRequest, renderResponse);
	}

	protected void assertEquals(Object expected, Object actual) {
		assertTrue(Validator.equals(expected, actual));
	}

	protected void assertFalse(boolean value) {
		assertTrue(!value);
	}

	protected void assertTrue(boolean value) {
		if (!value) {
			throw new RuntimeException("Assertion failed");
		}
	}

	protected void testGetClassLoaderBlogsEntryLocalService() {
		try {
			BlogsEntryLocalService blogsEntryLocalService =
				BlogsEntryLocalServiceUtil.getService();

			Class<?> clazz = blogsEntryLocalService.getClass();

			clazz.getClassLoader();

			throw new RuntimeException("Portal class loader is not protected");
		}
		catch (SecurityException se) {
		}
	}

	protected void testGetClassLoaderEntryLocalService() {
		EntryLocalService entryLocalService =
			EntryLocalServiceUtil.getService();

		Class<?> clazz = entryLocalService.getClass();

		clazz.getClassLoader();

		assertEquals(EntryLocalServiceClp.class.getName(), clazz.getName());
	}

	protected void testGetClassLoaderFooLocalService() {
		FooLocalService fooLocalService = FooLocalServiceUtil.getService();

		Class<?> clazz = fooLocalService.getClass();

		clazz.getClassLoader();
	}

	protected void testGetClassLoaderPortal() {
		try {
			Portal portal = PortalUtil.getPortal();

			Class<?> clazz = portal.getClass();

			clazz.getClassLoader();

			throw new RuntimeException("Portal class loader is not protected");
		}
		catch (SecurityException se) {
		}
	}

	protected void testGetClassLoaderTestPACLUtil() {
		TestPACLUtil.class.getClassLoader();
	}

	protected void testWriteFile() {
		File file = new File("../webapps/chat-portlet/css/main.css");

		try {
			file.exists();

			throw new RuntimeException("File is not protected");
		}
		catch (SecurityException se) {
		}
	}

}