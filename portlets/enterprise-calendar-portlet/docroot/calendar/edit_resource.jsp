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

<%@ include file="/calendar/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

CalendarResource resource = null;

long calendarResourceId = ParamUtil.getLong(request, "calendarResourceId");
List<Calendar> calendars = new ArrayList<Calendar>();

try {
	if (calendarResourceId > 0) {
		resource = CalendarResourceServiceUtil.getCalendarResource(calendarResourceId);
		calendars = CalendarLocalServiceUtil.getResourceCalendars(themeDisplay.getScopeGroupId(), resource.getCalendarResourceId());
	}
}
catch (Exception e) {
	if ((e instanceof NoSuchResourceException) || (e instanceof PrincipalException)) {
		SessionErrors.add(renderRequest, e.getClass().getName());
	}
}

%>

<liferay-portlet:actionURL varImpl="updateResourceURL" name="updateResource" />

<aui:form action="<%= updateResourceURL.toString() %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="calendarResourceId" type="hidden" value="<%= calendarResourceId %>" />

	<liferay-ui:header
		backURL="<%= redirect %>"
		title='<%= (resource != null) ? resource.getName(locale) : "new-calendar-resource" %>'
	/>

	<aui:model-context bean="<%= resource %>" model="<%= CalendarResource.class %>" />

	<aui:fieldset>
		<aui:input name="code" />

		<aui:input name="name" />

		<aui:input name="description" />

		<aui:select name="type">
			<aui:option label="" value="" />
			<%
			for (String resourceType : PortletPropsValues.ECALENDAR_RESOURCE_TYPES) {
			%>
				<aui:option label="<%= resourceType %>" value="<%= resourceType %>" selected="<%= resource != null && resource.getType().equals(resourceType) %>" />
			<%
			}
			%>
		</aui:select>

		<aui:select name="defaultCalendarId" label="default-calendar" >
			<%
			for (Calendar calendar : calendars) {
			%>
				<aui:option label="<%= calendar.getName(locale) %>" selected="<%= calendar.getCalendarId() == resource.getDefaultCalendarId() %>" value="<%= calendar.getCalendarId() %>" />
			<%
			}
			%>
		</aui:select>

		<aui:input inlineLabel="left" name="active" type="checkbox" value="<%= resource == null ? true : resource.isActive() %>" />

		<c:if test="<%= resource == null %>">
			<aui:field-wrapper label="permissions">
				<liferay-ui:input-permissions modelName="<%= CalendarResource.class.getName() %>" />
			</aui:field-wrapper>
		</c:if>

		<aui:button name="saveButton" onClick='<%= renderResponse.getNamespace() + "saveResource();" %>' value="save" />

		<aui:button name="cancelButton" onClick="<%= redirect %>" type="cancel" />

	</aui:fieldset>

</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />saveResource',
		function() {
			var A = AUI();
			var fm = A.one('#<portlet:namespace />fm');
			var cmd = A.one('#<portlet:namespace /><%= Constants.CMD %>');
			var action = '<%= (resource == null) ? Constants.ADD : Constants.UPDATE %>';

			cmd.val(action);

			submitForm(fm);
		},
		['aui-base']
	);
</aui:script>

<aui:script use="aui-base">
	Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />code);
</aui:script>