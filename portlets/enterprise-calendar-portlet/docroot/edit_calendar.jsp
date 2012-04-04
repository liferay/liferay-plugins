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

long calendarId = 0;

String title = LanguageUtil.format(pageContext, "add-new-calendar-for-x", calendarResource.getName(locale));

if (calendar != null) {
	calendarId = calendar.getCalendarId();
	title = calendar.getName(locale);
}
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= title %>'
/>

<liferay-portlet:actionURL name="updateCalendar" var="updateCalendarURL">
	<liferay-portlet:param name="mvcPath" value="/calendar/edit_calendar.jsp" />
	<liferay-portlet:param name="calendarId" value="<%= String.valueOf(calendarId) %>" />
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
			<span class="color-picker-element" id="<portlet:namespace />colorPicker">
			</span>
			<span
				class="color-box color-picker-element"
				id="<portlet:namespace />colorBox"
				style='background-color:<%= calendar != null ? ColorUtil.toHexString(calendar.getColor()) : "#0000FF" %>'
			>
			</span>
		</aui:field-wrapper>

		<aui:input inlineLabel="left" name="defaultCalendar" type="checkbox" value="<%= (calendar == null) ? true : calendar.isDefaultCalendar() %>" />

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

	AUI().ready('aui-color-picker-grid-plugin', function(A) {

		var colorPicker = new A.ColorPicker().plug(A.Plugin.ColorPickerGrid,
			{
			}
		).render('#<portlet:namespace />colorPicker');

		var colorBox = A.one('#<portlet:namespace />colorBox');
		var colorField = A.one('#<portlet:namespace />color');

		colorPicker.on('colorChange', function(event) {
			var rgb = colorPicker.get('rgb');

			colorBox.setStyle('backgroundColor', 'rgb(' + [rgb.r, rgb.g, rgb.b].join(',') + ')');
			//colorField.val = parseInt(colorPicker.get('hex'), 16);
			colorField.setAttribute("value", parseInt(colorPicker.get('hex'), 16));
		});
	});
</aui:script>