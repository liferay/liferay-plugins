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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class TestSuite {

	public void addTestCase(Class<? extends TestCase> testCaseClass) {
		_testCaseClasses.add(testCaseClass);
	}

	public JSONArray runTestSuite(ServletContext servletContext)
		throws Exception {

		JSONArray testSuiteJSONArray = JSONFactoryUtil.createJSONArray();

		for (Class<?> testCaseClass : _testCaseClasses) {
			TestCase testCase = (TestCase)testCaseClass.newInstance();

			testCase.setServletContext(servletContext);

			JSONObject testCaseJSONObject = testCase.runTests();

			testSuiteJSONArray.put(testCaseJSONObject);
		}

		return testSuiteJSONArray;
	}

	private List<Class<? extends TestCase>> _testCaseClasses =
		new ArrayList<Class<? extends TestCase>>();

}