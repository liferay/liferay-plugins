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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

/**
 * <a href="TestSuite.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class TestSuite {

	public void addTestCase(Class<? extends TestCase> testCaseClass) {
		_testCaseClasses.add(testCaseClass);
	}

	public JSONArray runTestSuite(ServletContext servletContext)
		throws Exception {

		JSONArray testSuiteResult = JSONFactoryUtil.createJSONArray();

		for (Class<?> testCaseClass : _testCaseClasses) {
			TestCase testCase = (TestCase)testCaseClass.newInstance();

			testCase.setServletContext(servletContext);

			JSONObject testCaseResult = testCase.runTests();

			testSuiteResult.put(testCaseResult);
		}

		return testSuiteResult;
	}

	private List<Class<? extends TestCase>> _testCaseClasses =
		new ArrayList<Class<? extends TestCase>>();

}