<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>

<liferay-theme:defineObjects />

<%
if (!themeDisplay.isSignedIn()) {
%>

	Please sign in to run the test.

<%
	return;
}

Map<String, Object> expectedValues = new HashMap<String, Object>();

expectedValues.put("liferay.company.id", user.getCompanyId());
expectedValues.put("liferay.user.id", user.getUserId());
expectedValues.put("user.name.full", user.getFullName());
%>

<table class="lfr-table">
<tr>
	<th>
		Key
	</th>
	<th>
		Expected Value
	</th>
	<th>
		Actual Value
	</th>
	<th></th>
</tr>

<%
Map userInfo = (Map)renderRequest.getAttribute(PortletRequest.USER_INFO);

if (userInfo != null) {
	for (Map.Entry entry : expectedValues.entrySet()) {
		String key = entry.getKey();
		String expectedValue = String.valueOf(entry.getValue());

		String actualValue = String.valueOf(userInfo.get(key));
%>

		<tr>
			<td>
				<%= key %>
			</td>
			<td>
				<%= expectedValue %>
			</td>
			<td>
				<%= actualValue %>
			</td>
			<td>

				<%
				if (expectedValue.equals(actualValue)) {
				%>

					PASSED

				<%
				}
				else {
				%>

					FAILED

				<%
				}
				%>

			</td>
		</tr>

<%
	}
}
else {
%>

	<tr>
		<td colspan="3"></td>
		</td>
			FAILED
		</td>
	</tr>

<%
}
%>

</table>