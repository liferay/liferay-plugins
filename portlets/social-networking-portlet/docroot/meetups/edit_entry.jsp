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

<portlet:actionURL name="updateMeetupsEntry" var="updateMeetupsEntryURL" />

<aui:form action="<%= updateMeetupsEntryURL %>" enctype="multipart/form-data" method="post" name="fm" onSubmit='<%= renderResponse.getNamespace() + "updateMeetupsEntry(); return false;" %>'>
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="meetupsEntryId" type="hidden" value="<%= meetupsEntryId %>" />

	<aui:model-context bean="<%= meetupsEntry %>" model="<%= MeetupsEntry.class %>" />

	<aui:input name="title" />

	<aui:input name="description" />

	<aui:input name="startDate" />

	<aui:input name="endDate" />

	<aui:input name="maxAttendees" />

	<aui:input name="price" />

	<aui:input label="thumbnail" name="fileName" type="file" />

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= HtmlUtil.escape(PortalUtil.escapeRedirect(redirect)) %>" value="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />updateMeetupsEntry() {
		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>