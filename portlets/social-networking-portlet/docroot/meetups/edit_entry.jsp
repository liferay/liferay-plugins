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
String redirect = ParamUtil.getString(request, "redirect");

long meetupsEntryId = ParamUtil.getLong(request, "meetupsEntryId");

MeetupsEntry meetupsEntry = null;

try {
	meetupsEntry = MeetupsEntryLocalServiceUtil.getMeetupsEntry(meetupsEntryId);
}
catch (NoSuchMeetupsEntryException nsmee) {
}

Calendar startDate = CalendarFactoryUtil.getCalendar(timeZone, locale);

startDate.add(Calendar.MONTH, 1);

if (meetupsEntry != null) {
	if (meetupsEntry.getStartDate() != null) {
		startDate.setTime(meetupsEntry.getStartDate());
	}
}

Calendar endDate = CalendarFactoryUtil.getCalendar(timeZone, locale);

endDate.add(Calendar.MONTH, 1);
endDate.add(Calendar.HOUR, 3);

if (meetupsEntry != null) {
	if (meetupsEntry.getStartDate() != null) {
		endDate.setTime(meetupsEntry.getEndDate());
	}
}
%>

<form action="<portlet:actionURL name="updateMeetupsEntry"><portlet:param name="redirect" value="<%= redirect %>" /></portlet:actionURL>" enctype="multipart/form-data" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />updateMeetupsEntry(); return false;">
<input name="<portlet:namespace />meetupsEntryId" type="hidden" value="<%= meetupsEntryId %>" />

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="title" />
	</td>
	<td>
		<liferay-ui:input-field bean="<%= meetupsEntry %>" field="title" model="<%= MeetupsEntry.class %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="description" />
	</td>
	<td>
		<liferay-ui:input-field bean="<%= meetupsEntry %>" field="description" model="<%= MeetupsEntry.class %>" />
	</td>
</tr>
<tr>
	<td colspan="2">
		<br />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="start-date" />
	</td>
	<td>
		<liferay-ui:input-field bean="<%= meetupsEntry %>" defaultValue="<%= startDate %>" field="startDate" model="<%= MeetupsEntry.class %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="end-date" />
	</td>
	<td>
		<liferay-ui:input-field bean="<%= meetupsEntry %>" defaultValue="<%= endDate %>" field="endDate" model="<%= MeetupsEntry.class %>" />
	</td>
</tr>
<tr>
	<td colspan="2">
		<br />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="max-attendees" />
	</td>
	<td>
		<liferay-ui:input-field bean="<%= meetupsEntry %>" field="maxAttendees" model="<%= MeetupsEntry.class %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="price" />
	</td>
	<td>
		<liferay-ui:input-field bean="<%= meetupsEntry %>" field="price" model="<%= MeetupsEntry.class %>" />
	</td>
</tr>
<tr>
	<td colspan="2">
		<br />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="thumbnail" />
	</td>
	<td>
		<input name="<portlet:namespace />fileName" size="50" type="file" />
	</td>
</tr>
</table>

<br />

<input type="submit" value="<liferay-ui:message key="save" />" />

<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= HtmlUtil.escape(PortalUtil.escapeRedirect(redirect)) %>';" />

</form>

<aui:script>
	function <portlet:namespace />updateMeetupsEntry() {
		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>