<%
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
%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ page import="com.liferay.portal.kernel.json.JSONArray" %>
<%@ page import="com.liferay.portal.kernel.json.JSONObject" %>
<%@ page import="com.liferay.testworkflow.test.WorkflowTestSuite" %>

<%@ page import="java.util.List" %>

<portlet:defineObjects />

<%
JSONArray testSuiteResult = WorkflowTestSuite.runTestSuite(application);

for (int i = 0; i < testSuiteResult.length(); i++) {
	JSONObject testCaseResult = testSuiteResult.getJSONObject(i);

	String testCaseName = testCaseResult.getString("name");
%>

	<h3><%= testCaseName %></h3>

<%
	JSONArray testResults = testCaseResult.getJSONArray("testResults");

	for (int j = 0; j < testResults.length(); j++) {
		JSONObject testResult = testResults.getJSONObject(j);

		String name = testResult.getString("name");
		String status = testResult.getString("status");
		String exceptionMessage = testResult.getString("exceptionMessage");
		String exceptionStackTrace = testResult.getString("exceptionStackTrace");
%>

		<p>
			<%= name %> <%= status %> <%= exceptionMessage %>
		</p>
		<p>
			<%= exceptionStackTrace %>
		</p>

<%
	}
}
%>