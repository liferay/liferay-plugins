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
<%@ page import="com.liferay.portlet.workflow.test.WorkflowTestSuite" %>

<%@ page import="java.util.List" %>

<portlet:defineObjects />

<%
JSONArray testSuiteResults = WorkflowTestSuite.runTestSuite(application);

for (int i = 0; i < testSuiteResults.length(); i++) {
	JSONObject testSuiteResult = testSuiteResults.getJSONObject(i);

	String testCaseName = testSuiteResult.getString("name");
%>

	<h3><%= testCaseName %></h3>

<%
	JSONArray testCaseResults = testSuiteResult.getJSONArray("testCaseResults");

	for (int j = 0; j < testCaseResults.length(); j++) {
		JSONObject testCaseResult = testCaseResults.getJSONObject(j);

		String name = testCaseResult.getString("name");
		String status = testCaseResult.getString("status");
		String exceptionMessage = testCaseResult.getString("exceptionMessage");
		String exceptionStackTrace = testCaseResult.getString("exceptionStackTrace");
%>

		<p><%= name %> <%= status %> <%= exceptionMessage %></p>
		<p><%= exceptionStackTrace %></p>

<%
	}
%>
<%
}
%>