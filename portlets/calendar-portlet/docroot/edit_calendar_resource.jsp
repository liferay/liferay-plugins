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

CalendarResource calendarResource = (CalendarResource)request.getAttribute(WebKeys.CALENDAR_RESOURCE);

long calendarResourceId = 0;

List<Calendar> calendars = null;

if (calendarResource != null) {
	calendarResourceId = calendarResource.getCalendarResourceId();

	calendars = CalendarLocalServiceUtil.getCalendars(themeDisplay.getScopeGroupId(), calendarResourceId);
}

String code = BeanParamUtil.getString(calendarResource, request, "code");
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= (calendarResource != null) ? calendarResource.getName(locale) : "new-calendar-resource" %>'
/>

<liferay-portlet:actionURL name="updateCalendarResource" var="updateCalendarResourceURL">
	<liferay-portlet:param name="mvcPath" value="/edit_calendar_resource.jsp" />
	<liferay-portlet:param name="redirect" value="<%= redirect %>" />
	<liferay-portlet:param name="calendarResourceId" value="<%= String.valueOf(calendarResourceId) %>" />
</liferay-portlet:actionURL>

<aui:form action="<%= updateCalendarResourceURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "updateCalendarResource();" %>'>
	<aui:model-context bean="<%= calendarResource %>" model="<%= CalendarResource.class %>" />

	<liferay-ui:error exception="<%= CalendarResourceCodeException.class %>" message="please-enter-a-valid-code" />
	<liferay-ui:error exception="<%= DuplicateCalendarResourceException.class %>" message="please-enter-a-unique-resource-code" />

	<aui:fieldset>
		<c:choose>
			<c:when test="<%= calendarResource == null %>">
				<c:if test="<%= !PortletPropsValues.CALENDAR_RESOURCE_FORCE_AUTOGENERATE_CODE %>">
					<aui:input name="code" />
				</c:if>
			</c:when>
			<c:otherwise>
				<aui:field-wrapper label="code">
					<%= code %>
				</aui:field-wrapper>
			</c:otherwise>
		</c:choose>

		<aui:input name="name" />

		<aui:input name="description" />

		<aui:select name="type" value="<%= (calendarResource == null) ? StringPool.BLANK : calendarResource.getType() %>">
			<aui:option label="" value="" />

			<%
			for (String type : PortletPropsValues.CALENDAR_RESOURCE_TYPES) {
			%>

				<aui:option label="<%= type %>" value="<%= type %>" />

			<%
			}
			%>

		</aui:select>

		<c:if test="<%= calendars != null %>">
			<aui:select label="default-calendar" name="defaultCalendarId" value="<%= calendarResource.getDefaultCalendarId() %>">

				<%
				for (Calendar calendar : calendars) {
				%>

					<aui:option label="<%= calendar.getName(locale) %>" value="<%= calendar.getCalendarId() %>" />

				<%
				}
				%>

			</aui:select>
		</c:if>

		<aui:input inlineLabel="left" name="active" type="checkbox" value="<%= (calendarResource == null) ? true : calendarResource.isActive() %>" />

		<c:if test="<%= calendarResource == null %>">
			<aui:field-wrapper label="permissions">
				<liferay-ui:input-permissions modelName="<%= CalendarResource.class.getName() %>" />
			</aui:field-wrapper>
		</c:if>

		<aui:button-row>
			<aui:button type="submit" />

			<aui:button href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:fieldset>

</aui:form>

<aui:script>
	function <portlet:namespace />updateCalendarResource() {
		submitForm(document.<portlet:namespace />fm);
	}

	<c:choose>
		<c:when test="<%= PortletPropsValues.CALENDAR_RESOURCE_FORCE_AUTOGENERATE_CODE %>">
			Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
		</c:when>
		<c:otherwise>
			Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace /><%= (calendarResource == null) ? "code" : "name" %>);
		</c:otherwise>
	</c:choose>
</aui:script>