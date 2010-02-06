/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
 * <a href="TestCase.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 */
public class TestCase {

	public void assertEquals(Object expected, Object actual) {
		Validate.isTrue(expected.equals(actual));
	}

	public void assertFalse(boolean value) {
		Validate.isTrue(!value);
	}

	public void assertNotNull(Object object) {
		Validate.notNull(object);
	}

	public void assertNull(Object object) {
		Validate.isTrue(object == null);
	}

	public void assertTrue(boolean value) {
		Validate.isTrue(value);
	}

	public void fail() {
		throw new IllegalArgumentException();
	}

	public void fail(String message) {
		throw new IllegalArgumentException(message);
	}

	public byte[] readBytes(InputStream inputStream) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		int i = -1;

		while ((i = inputStream.read()) != -1) {
			byteArrayOutputStream.write(i);
		}

		byteArrayOutputStream.close();

		return byteArrayOutputStream.toByteArray();
	}

	public JSONObject runTests() {
		JSONObject testCaseResult = JSONFactoryUtil.createJSONObject();

		Class<? extends TestCase> testCaseClass = getClass();

		String testCaseName = testCaseClass.getName();

		testCaseResult.put("name", testCaseName);

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

		JSONArray testResults = JSONFactoryUtil.createJSONArray();

		testCaseResult.put("testResults", testResults);

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

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public void setUp() throws Exception {
	}

	public void tearDown() throws Exception {
	}

	protected ServletContext servletContext;

	private static final String _STATUS_FAILED = "FAILED";

	private static final String _STATUS_PASSED = "PASSED";

}