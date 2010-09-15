<%--
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
--%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ page import="com.liferay.portal.kernel.json.JSONArray" %>
<%@ page import="com.liferay.portal.kernel.json.JSONObject" %>
<%@ page import="com.liferay.testworkflow.test.WorkflowTestSuite" %>

<%@ page import="java.util.List" %>

<portlet:defineObjects />

<%
WorkflowTestSuite workflowTestSuite = new WorkflowTestSuite();

JSONArray testSuiteResult = workflowTestSuite.runTestSuite(application);

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