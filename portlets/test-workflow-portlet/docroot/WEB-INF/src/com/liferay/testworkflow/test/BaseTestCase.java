/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.testworkflow.test;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.MethodComparator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.lang.reflect.Method;

import java.util.Arrays;

import javax.servlet.ServletContext;

import org.apache.commons.lang.Validate;

/**
 * <a href="BaseTestCase.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 */
public class BaseTestCase {

	public static void assertEquals(Object expected, Object actual) {
		Validate.isTrue(expected.equals(actual));
	}

	public static void assertFalse(boolean value) {
		Validate.isTrue(!value);
	}

	public static void assertNotNull(Object object) {
		Validate.notNull(object);
	}

	public static void assertTrue(boolean value) {
		Validate.isTrue(value);
	}

	public static void fail() {
		throw new IllegalArgumentException();
	}

	public static void fail(String message) {
		throw new IllegalArgumentException(message);
	}

	public static byte[] readBytes(InputStream inputStream) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		int i = -1;

		while ((i = inputStream.read()) != -1) {
			byteArrayOutputStream.write(i);
		}

		byteArrayOutputStream.close();

		return byteArrayOutputStream.toByteArray();
	}

	public BaseTestCase(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public JSONObject callTestMethods() {
		JSONObject testCaseResult = JSONFactoryUtil.createJSONObject();

		Class<? extends BaseTestCase> clazz = getClass();

		String testCaseName = clazz.getName();

		testCaseResult.put("name", testCaseName);

		Method setUpMethod = null;

		try {
			setUpMethod = clazz.getMethod("setUp");
		}
		catch (Exception e) {
		}

		Method tearDownMethod = null;

		try {
			tearDownMethod = clazz.getMethod("tearDown");
		}
		catch (Exception e) {
		}

		JSONArray testResults = JSONFactoryUtil.createJSONArray();

		testCaseResult.put("testResults", testResults);

		Method[] methods = clazz.getMethods();

		Arrays.sort(methods, new MethodComparator());

		for (Method method : methods) {
			if (method.getName().startsWith("test")) {
				JSONObject testResult = JSONFactoryUtil.createJSONObject();

				testResult.put("name", method.getName());

				try {
					if (setUpMethod != null) {
						setUpMethod.invoke(this);
					}

					method.invoke(this);

					if (tearDownMethod != null) {
						tearDownMethod.invoke(this);
					}

					testResult.put("status", _STATUS_PASSED);
				}
				catch (Exception e) {
					Throwable cause = e.getCause();

					testResult.put("status", _STATUS_FAILED);
					testResult.put("exceptionMessage", cause.getMessage());

					StringWriter stringWriter = new StringWriter();
					PrintWriter printWriter = new PrintWriter(stringWriter);

					cause.printStackTrace(printWriter);

					testResult.put(
						"exceptionStackTrace", stringWriter.toString());

					printWriter.close();
				}

				testResults.put(testResult);
			}
		}

		return testCaseResult;
	}

	protected ServletContext servletContext;

	private static final String _STATUS_FAILED = "FAILED";

	private static final String _STATUS_PASSED = "PASSED";

}