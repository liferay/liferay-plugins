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

package com.liferay.portlet.workflow.test;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="BaseTestCase.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 */
public class BaseTestCase {

	public static final String PASSED_KEY = "PASSED";

	public static final String FAILED_KEY = "FAILED";

	public List<String> callTestMethods() {
		List<String> testResults = new ArrayList<String>();

		Class<? extends BaseTestCase> clazz = getClass();

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

		Method[] methods = clazz.getMethods();

		for (Method method : methods) {
			if (method.getName().startsWith("test")) {
				StringBuilder sb = new StringBuilder();

				sb.append(method.getDeclaringClass().getName());
				sb.append(".");
				sb.append(method.getName());
				sb.append("()=");

				try {
					if (setUpMethod != null) {
						setUpMethod.invoke(this);
					}

					method.invoke(this);

					if (tearDownMethod != null) {
						tearDownMethod.invoke(this);
					}

					sb.append(PASSED_KEY);
				}
				catch (Exception e) {
					sb.append(FAILED_KEY);
					sb.append("-message-");
					sb.append(e.getMessage());
				}

				testResults.add(sb.toString());
			}
		}

		return testResults;
	}

}