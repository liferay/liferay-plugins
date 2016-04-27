<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
if (!themeDisplay.isSignedIn()) {
%>

	Please sign in to run the test.

<%
	return;
}

KaleoTestSuite kaleoTestSuite = new KaleoTestSuite();

JSONArray testSuiteJSONArray = kaleoTestSuite.runTestSuite(application);

for (int i = 0; i < testSuiteJSONArray.length(); i++) {
	JSONObject testCaseJSONObject = testSuiteJSONArray.getJSONObject(i);
%>

	<h3><%= testCaseJSONObject.getString("name") %></h3>

	<%
	JSONArray testResultsJSONArray = testCaseJSONObject.getJSONArray("testResults");

	for (int j = 0; j < testResultsJSONArray.length(); j++) {
		JSONObject testResultsJSONObject = testResultsJSONArray.getJSONObject(j);
	%>

		<p>
			<%= testResultsJSONObject.getString("name") %> <%= testResultsJSONObject.getString("status") %> <%= testResultsJSONObject.getString("exceptionMessage") %>
		</p>

		<p>
			<%= testResultsJSONObject.getString("exceptionStackTrace") %>
		</p>

<%
	}
}
%>