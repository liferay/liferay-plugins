<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

<portlet:renderURL var="redirectURL" windowState="<%= WindowState.NORMAL.toString() %>" />

<form action="<portlet:actionURL name="updateSummary" />" method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace />redirect" type="hidden" value="<%= redirectURL %>" />

<div class="portlet-msg-info">
	<liferay-ui:message arguments="<%= new Object[] {themeDisplay.getURLMyAccount()} %>" key="use-my-account-to-change-regular-account-settings" />
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

<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= redirectURL %>';" />

</form>