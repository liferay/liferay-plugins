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

package com.liferay.portal.workflow.kaleo.test;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.MethodComparator;
import com.liferay.portal.kernel.util.StackTraceUtil;

import java.lang.reflect.Method;

import java.util.Arrays;

import javax.servlet.ServletContext;

/**
 * @author Shuyang Zhou
 */
public class TestCase {

	public void assertEquals(Object expected, Object actual) {
		isTrue(expected.equals(actual));
	}

	public void assertFalse(boolean value) {
		isTrue(!value);
	}

	public void assertNotNull(Object object) {
		isTrue(object != null);
	}

	public void assertNull(Object object) {
		isTrue(object == null);
	}

	public void assertTrue(boolean value) {
		isTrue(value);
	}

	public void fail() {
		throw new IllegalArgumentException();
	}

	public void fail(String message) {
		throw new IllegalArgumentException(message);
	}

	public JSONObject runTests() {
		JSONObject testCaseJSONObject = JSONFactoryUtil.createJSONObject();

		Class<? extends TestCase> testCaseClass = getClass();

		testCaseJSONObject.put("name", testCaseClass.getName());

		Method setUpMethod = null;

		try {
			setUpMethod = testCaseClass.getMethod("setUp");
		}
		catch (Exception e) {
		}

		Method tearDownMethod = null;

		try {
			tearDownMethod = testCaseClass.getMethod("tearDown");
		}
		catch (Exception e) {
		}

		JSONArray testResultsJSONArray = JSONFactoryUtil.createJSONArray();

		testCaseJSONObject.put("testResults", testResultsJSONArray);

		Method[] methods = testCaseClass.getMethods();

		Arrays.sort(methods, new MethodComparator());

		for (Method method : methods) {
			String methodName = method.getName();

			if (methodName.startsWith("test")) {
				JSONObject testResult = JSONFactoryUtil.createJSONObject();

				testResult.put("name", methodName);

				try {
					if (setUpMethod != null) {
						setUpMethod.invoke(this);
					}

					method.invoke(this);

					if (tearDownMethod != null) {
						tearDownMethod.invoke(this);
					}

					testResult.put("status", "PASSED");
				}
				catch (Exception e) {
					Throwable throwable = e.getCause();

					testResult.put("exceptionMessage", throwable.getMessage());
					testResult.put(
						"exceptionStackTrace",
						StackTraceUtil.getStackTrace(throwable));
					testResult.put("status", "FAILED");
				}

				testResultsJSONArray.put(testResult);
			}
		}

		return testCaseJSONObject;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public void setUp() throws Exception {
	}

	public void tearDown() throws Exception {
	}

	protected void isTrue(boolean expression) {
		if (!expression) {
			throw new IllegalArgumentException(
				"The validated expression is false");
		}
	}

	protected ServletContext servletContext;

}