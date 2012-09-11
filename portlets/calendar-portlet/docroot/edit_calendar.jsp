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

Calendar calendar = (Calendar)request.getAttribute(WebKeys.CALENDAR);

CalendarResource calendarResource = (CalendarResource)request.getAttribute(WebKeys.CALENDAR_RESOURCE);
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= (calendar != null) ? calendar.getName(locale) : LanguageUtil.format(pageContext, "add-new-calendar-for-x", calendarResource.getName(locale)) %>'
/>

<liferay-portlet:actionURL name="updateCalendar" var="updateCalendarURL">
	<liferay-portlet:param name="mvcPath" value="/calendar/edit_calendar.jsp" />
	<liferay-portlet:param name="calendarId" value="<%= (calendar != null) ? String.valueOf(calendar.getCalendarId()) : StringPool.BLANK %>" />
	<liferay-portlet:param name="calendarResourceId" value="<%= String.valueOf(calendarResource.getCalendarResourceId()) %>" />
	<liferay-portlet:param name="redirect" value="<%= redirect %>" />
</liferay-portlet:actionURL>

<aui:form action="<%= updateCalendarURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "updateCalendar();" %>'>
	<aui:model-context bean="<%= calendar %>" model="<%= Calendar.class %>" />

	<aui:fieldset>
		<aui:input name="name" />

		<aui:input name="description" />

		<aui:input name="color" type="hidden" />

		<aui:field-wrapper inlineLabel="left" label="color">
			<div class="calendar-portlet-colors" id="<portlet:namespace />colorPicker"></div>
		</aui:field-wrapper>

		<aui:input disabled="<%= (calendar != null) ? calendar.isDefaultCalendar() : false %>" name="defaultCalendar" />

		<c:if test="<%= calendar == null %>">
			<aui:field-wrapper label="permissions">
				<liferay-ui:input-permissions modelName="<%= Calendar.class.getName() %>" />
			</aui:field-wrapper>
		</c:if>

		<aui:button-row>
			<aui:button type="submit" />

			<aui:button href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:fieldset>

</aui:form>

<aui:script>
	function <portlet:namespace />updateCalendar() {
		submitForm(document.<portlet:namespace />fm);
	}

	Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
</aui:script>

<aui:script use="liferay-calendar-simple-color-picker">
	window.<portlet:namespace />colorPicker = new Liferay.SimpleColorPicker(
		{
			color: '<%= ColorUtil.toHexString((calendar != null) ? calendar.getColor() : PortletPropsValues.CALENDAR_COLOR_DEFAULT) %>',
			on: {
				colorChange: function(event) {
					A.one('#<portlet:namespace />color').val(parseInt(event.newVal.substring(1), 16));
				}
			},
			render: '#<portlet:namespace />colorPicker'
		}
	);
</aui:script>