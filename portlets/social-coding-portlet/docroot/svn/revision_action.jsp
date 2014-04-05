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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Object[] objArray = (Object[])row.getObject();

SVNRevision svnRevision = (SVNRevision)objArray[0];
String rowHREF = (String)objArray[1];
JIRAIssue jiraIssue = (JIRAIssue)objArray[2];
%>

<div class="revision-actions">
	<liferay-ui:icon-list>

		<%
		String taglibSVNURL = "javascript:location.href = '" + rowHREF + "';";
		%>

		<liferay-ui:icon
			label="<%= true %>"
			message="SVN"
			src='<%= PortalUtil.getPathContext(request) + "/icons/svn.png" %>'
			target="_blank"
			url="<%= taglibSVNURL %>"
		/>

		<c:if test="<%= jiraIssue != null %>">

			<%
			String taglibJIRAURL = "javascript:location.href = '" + PortletPropsValues.JIRA_URL + "/browse/" + jiraIssue.getKey() + "';";
			%>

			<liferay-ui:icon
				label="<%= true %>"
				message="JIRA"
				src='<%= PortalUtil.getPathContext(request) + "/icons/jira.png" %>'
				target="_blank"
				url="<%= taglibJIRAURL %>"
			/>
		</c:if>
	</liferay-ui:icon-list>
</div>