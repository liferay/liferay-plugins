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

CalendarResource calendarResource = (CalendarResource)request.getAttribute(WebKeys.CALENDAR_RESOURCE);
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= LanguageUtil.format(pageContext, "x-calendars", calendarResource.getName(locale), false) %>'
/>

<c:if test="<%= CalendarResourcePermission.contains(permissionChecker, calendarResource, ActionKeys.ADD_CALENDAR) %>">
	<aui:button-row>
		<liferay-portlet:renderURL var="editCalendarURL">
			<liferay-portlet:param name="mvcPath" value="/edit_calendar.jsp" />
			<liferay-portlet:param name="redirect" value="<%= currentURL %>" />
			<liferay-portlet:param name="backURL" value="<%= currentURL %>" />
			<liferay-portlet:param name="calendarResourceId" value="<%= String.valueOf(calendarResource.getCalendarResourceId()) %>" />
		</liferay-portlet:renderURL>

		<aui:button onClick="<%= editCalendarURL %>" value="add-calendar" />
	</aui:button-row>
</c:if>

<liferay-ui:search-container
	emptyResultsMessage="there-are-no-calendars-for-the-selected-resource"
	iteratorURL="<%= renderResponse.createRenderURL() %>"
	total="<%= CalendarServiceUtil.searchCount(themeDisplay.getCompanyId(), new long[] {calendarResource.getGroupId()}, new long[] {calendarResource.getCalendarResourceId()}, null, false) %>"
>
	<liferay-ui:search-container-results
		results="<%= CalendarServiceUtil.search(themeDisplay.getCompanyId(), new long[] {calendarResource.getGroupId()}, new long[] {calendarResource.getCalendarResourceId()}, null, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new CalendarNameComparator(true)) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.calendar.model.Calendar"
		keyProperty="calendarId"
		modelVar="calendar"
	>
		<liferay-ui:search-container-column-text
			name="name"
			value="<%= HtmlUtil.escape(calendar.getName(locale)) %>"
		/>

		<liferay-ui:search-container-column-text
			name="description"
			value="<%= HtmlUtil.escape(StringUtil.shorten(calendar.getDescription(locale))) %>"
		/>

		<liferay-ui:search-container-column-text
			align="center"
			name="color"
		>
			<span class="calendar-portlet-color-box" style="background-color:<%= ColorUtil.toHexString(calendar.getColor()) %>;">&nbsp;</span>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text name="default">
			<c:choose>
				<c:when test="<%= calendar.isDefaultCalendar() %>">
					<liferay-ui:message key="yes" />
				</c:when>
				<c:otherwise>
					<liferay-ui:message key="no" />
				</c:otherwise>
			</c:choose>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/calendar_action.jsp"
		/>

	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<div class="hide calendar-portlet-import-container" id="<portlet:namespace />importCalendarContainer">
	<div class="hide portlet-msg-error" id="<portlet:namespace />portletErrorMessage"></div>

	<div class="hide portlet-msg-success" id="<portlet:namespace />portletSuccessMessage">
		<liferay-ui:message key="your-request-completed-successfully" />
	</div>

	<aui:form enctype="multipart/form-data" method="post" name="importFm">
		<aui:input id="file" name="file" type="file" />
		<div class="portlet-msg-help">
			<liferay-ui:message key="choose-the-file-that-contains-your-events.this-calendar-can-import-event-information-in-ical-format" />
		</div>
	</aui:form>
</div>

<aui:script>
	var <portlet:namespace />importDialog;

	Liferay.provide(
		window,
		'<portlet:namespace />importCalendar',
		function(url) {
			var A = AUI();

			if (!<portlet:namespace />importDialog) {
				var importCalendarContainer = A.one('#<portlet:namespace />importCalendarContainer');

				var buttons = [
					{
						label: Liferay.Language.get('import'),
						on: {
							click: function() {
								A.io.request(
									url,
									{
										dataType: 'json',
										form: {
											id: '<portlet:namespace />importFm',
											upload: true
										},
										method: 'post',
										on: {
											complete: function() {
												var responseData = this.get('responseData');

												var portletErrorMessage = A.one('#<portlet:namespace />portletErrorMessage');

												var portletSuccessMessage = A.one('#<portlet:namespace />portletSuccessMessage');

												var error = responseData && responseData.error;

												if (error) {
													portletErrorMessage.show();
													portletSuccessMessage.hide();

													portletErrorMessage.html(error);
												}
												else {
													portletErrorMessage.hide();
													portletSuccessMessage.show();
												}
											}
										}
									}
								);
							}
						}
					}
				];

				var buttonClose = [
					{
						cssClass: 'close',
						label: '\u00D7',
						render: true,
						on: {
							click: function() {
								<portlet:namespace />importDialog.hide();
							}
						}
					}
				];

				<portlet:namespace />importDialog = Liferay.Util.Window.getWindow(
					{
						dialog: {
							bodyContent: importCalendarContainer.html(),
							toolbars: {
								footer: buttons,
								header: buttonClose
							},
							modal: true,
							on: {
								visibleChange: function(event) {
									A.one('#<portlet:namespace />importFm').reset();
									A.one('#<portlet:namespace />portletErrorMessage').hide();
									A.one('#<portlet:namespace />portletSuccessMessage').hide();
								}
							}
						},
						title: Liferay.Language.get('import')
					}
				).render();
			}

			<portlet:namespace />importDialog.show();
		},
		['aui-io', 'liferay-util-window']
	);
</aui:script>