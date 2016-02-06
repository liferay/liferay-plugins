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

package com.liferay.testpacl.portlet;

import com.liferay.blogs.kernel.service.BlogsEntryLocalService;
import com.liferay.blogs.kernel.service.BlogsEntryLocalServiceUtil;
import com.liferay.chat.service.EntryLocalService;
import com.liferay.chat.service.EntryLocalServiceClp;
import com.liferay.chat.service.EntryLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.testpacl.service.FooLocalService;
import com.liferay.testpacl.service.FooLocalServiceUtil;
import com.liferay.testpacl.util.TestPACLUtil;

import java.io.IOException;

import java.lang.reflect.Field;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class TestPACLPortlet extends MVCPortlet {

	public TestPACLPortlet() {
		TestPACLUtil.testWriteFile();
	}

	@Override
	public void destroy() {
		TestPACLUtil.testWriteFile();
	}

	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			TestPACLUtil.testWriteFile();

			testGetClassLoaderBlogsEntryLocalService();
			testGetClassLoaderEntryLocalService();
			testGetClassLoaderFooLocalService();
			testGetClassLoaderPortal();
			testGetClassLoaderTestPACLUtil();

			testReflectionTestPACLUtil_log();
			testReflectionTestPACLUtil_TEST_FIELD();
			testReflectionTestPACLUtil_TEST_FIELD_setAccessible();
		}
		catch (Exception e) {
			throw new PortletException(e);
		}

		include(viewTemplate, renderRequest, renderResponse);
	}

	@Override
	public void init() throws PortletException {
		super.init();

		TestPACLUtil.testWriteFile();
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

	protected void testReflectionTestPACLUtil_log() throws Exception {
		Class<?> clazz = TestPACLUtil.class;

		clazz.getDeclaredField("_log");
	}

	protected void testReflectionTestPACLUtil_TEST_FIELD() throws Exception {
		Class<?> clazz = TestPACLUtil.class;

		clazz.getField("TEST_FIELD");
	}

	protected void testReflectionTestPACLUtil_TEST_FIELD_setAccessible()
		throws Exception {

		Class<?> clazz = TestPACLUtil.class;

		Field field = clazz.getField("TEST_FIELD");

		field.setAccessible(false);
	}

}