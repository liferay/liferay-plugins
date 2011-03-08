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

<%@ include file="/init.jsp" %>

<%
String aboutMe = HtmlUtil.escape(ExpandoValueLocalServiceUtil.getData(User.class.getName(), "SN", "aboutMe", user2.getUserId(), StringPool.BLANK));
%>

<form action="<portlet:actionURL name="updateSummary" />" method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace />redirect" type="hidden" value="<portlet:renderURL windowState="<%= WindowState.NORMAL.toString() %>" />" />

<div class="portlet-msg-info">
	Use <a href="<%= themeDisplay.getURLMyAccount() %>">My Account</a> to change regular account settings like profile picture or password.
</div>

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="job-title" />
	</td>
	<td>
		<liferay-ui:input-field model="<%= Contact.class %>" bean="<%= user2.getContact() %>" field="jobTitle" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="about-me" />
	</td>
	<td>
		<liferay-ui:input-textarea param="aboutMe" defaultValue="<%= aboutMe %>" />
	</td>
</tr>
</table>

<br />

<input type="submit" value="<liferay-ui:message key="save" />" />
<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<portlet:renderURL windowState="<%= WindowState.NORMAL.toString() %>" />';" />

</form>