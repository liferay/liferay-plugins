<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

String currentTab = ParamUtil.getString(request, "currentTab", "general");

Calendar calendar = (Calendar)request.getAttribute(WebKeys.CALENDAR);

CalendarResource calendarResource = (CalendarResource)request.getAttribute(WebKeys.CALENDAR_RESOURCE);

%>

<liferay-portlet:renderURL var="portletURL">
	<portlet:param name="mvcPath" value="/edit_calendar.jsp" />
	<portlet:param name="currentTab" value="<%= currentTab %>" />
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="calendarId" value="<%= (calendar != null) ? String.valueOf(calendar.getCalendarId()) : StringPool.BLANK %>" />
</liferay-portlet:renderURL>


<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= (calendar != null) ? calendar.getName(locale) : LanguageUtil.format(pageContext, "add-new-calendar-for-x", calendarResource.getName(locale)) %>'
/>


<liferay-portlet:actionURL name="updateCalendar" var="updateCalendarURL" />

<aui:form action="<%= updateCalendarURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "updateCalendar();" %>'>
	<aui:input name="mvcPath" type="hidden" value="/edit_calendar.jsp" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="currentTab" type="hidden" value="<%= currentTab %>" />
	<aui:input name="calendarId" type="hidden" value="<%= (calendar != null) ? String.valueOf(calendar.getCalendarId()) : StringPool.BLANK %>" />
	<aui:input name="calendarResourceId" type="hidden" value="<%= (calendarResource != null) ? String.valueOf(calendarResource.getCalendarResourceId()) : StringPool.BLANK %>" />

	<liferay-ui:error exception="<%= CalendarNameException.class %>" message="please-enter-a-valid-name" />
	<%
	String tabsNames = "general";
	if (calendar != null) {
		tabsNames += ",templates";
	}
	%>

	<liferay-ui:tabs
		names="<%= tabsNames %>"
		param="currentTab"
		url="<%= portletURL %>"
	/>

	<c:choose>
		<c:when test='<%= currentTab.equals("general") %>'>
			<liferay-ui:error exception="<%= CalendarNameException.class %>" message="please-enter-a-valid-name" />
			<liferay-ui:error key="emailFromAddress" message="invalid-email-address-using-user-email-address-for-notification-sender" />
			<liferay-ui:error key="emailFromName" message="invalid-notification-sender-name-using-user-name" />

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

			</aui:fieldset>

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

			<aui:script>
				function <portlet:namespace />updateCalendar() {
					submitForm(document.<portlet:namespace />fm);
				}

				Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
			</aui:script>
		</c:when>
		<c:when test='<%= currentTab.equals("templates") && calendar != null %>'>
			<%
				NotificationType notificationType = NotificationType.parse(ParamUtil.getString(request, "notificationType", PortletPropsValues.CALENDAR_NOTIFICATION_DEFAULT_TYPE));
				NotificationTemplateType templateType = NotificationTemplateType.parse(ParamUtil.getString(request, "notificationTemplateType", "reminder"));

				String bodyParameterName = NotificationUtil.getPreferenceName(notificationType, templateType, NotificationField.BODY);
				String subjectParameterName = NotificationUtil.getPreferenceName(notificationType, templateType, NotificationField.SUBJECT);

				CalendarNotificationTemplate template = NotificationUtil.getNotificationTemplate(calendar, notificationType, templateType);

				String emailFromAddress = NotificationUtil.getEmailFromAddress(template);
				String emailFromName = NotificationUtil.getEmailFromName(template);
			%>

			<liferay-ui:error key="<%= bodyParameterName %>" message="please-enter-a-valid-body" />
			<liferay-ui:error key="<%= subjectParameterName %>" message="please-enter-a-valid-subject" />

			<aui:fieldset>
				<aui:select name="notificationType" value="<%= notificationType.getValue() %>">

					<%
					for (NotificationType curNotificationType : NotificationType.values()) {
					%>

						<aui:option label="<%= curNotificationType.getValue() %>" value="<%= curNotificationType.getValue() %>" />

					<%
					}
					%>

				</aui:select>

				<aui:select name="notificationTemplateType" value="<%= templateType.getValue() %>">

					<%
					for (NotificationTemplateType curNotificationTemplateType : NotificationTemplateType.values()) {
					%>

						<aui:option label="<%= curNotificationTemplateType.getValue() %>" value="<%= curNotificationTemplateType.getValue() %>" />

					<%
					}
					%>

				</aui:select>

				<c:if test="<%= notificationType == NotificationType.EMAIL %>">

					<aui:input name="notificationSenderName" type="text" value="<%= NotificationUtil.getNotificationSenderName(template) %>" />

					<aui:input name="notificationSenderEmailAddress" type="text" value="<%= NotificationUtil.getNotificationSenderEmailAddress(template) %>" />

				</c:if>

				<aui:input cssClass="lfr-input-text-container" label="subject" name='<%= subjectParameterName %>' type="text" value="<%= template.getSubject() %>" />

				<aui:field-wrapper label="body">
					<liferay-ui:input-editor editorImpl="ckeditor" />

					<aui:input name='<%= bodyParameterName %>' type="hidden" value="<%= template.getBody() %>" />
				</aui:field-wrapper>
			</aui:fieldset>

			<div class="definition-of-terms">
				<h4><liferay-ui:message key="definition-of-terms" /></h4>

				<dl>
					<dt>
						[$BOOKING_LOCATION$]
					</dt>
					<dd>
						<liferay-ui:message key="the-booking-location" />
					</dd>
					<dt>
						[$BOOKING_START_DATE$]
					</dt>
					<dd>
						<liferay-ui:message key="the-booking-start-date" />
					</dd>
					<dt>
						[$BOOKING_TITLE$]
					</dt>
					<dd>
						<liferay-ui:message key="the-booking-title" />
					</dd>
					<dt>
						[$FROM_ADDRESS$]
					</dt>
					<dd>
						<%= HtmlUtil.escape(emailFromAddress) %>
					</dd>
					<dt>
						[$FROM_NAME$]
					</dt>
					<dd>
						<%= HtmlUtil.escape(emailFromName) %>
					</dd>
					<dt>
						[$PORTAL_URL$]
					</dt>
					<dd>
						<%= company.getVirtualHostname() %>
					</dd>
					<dt>
						[$TO_ADDRESS$]
					</dt>
					<dd>
						<liferay-ui:message key="the-address-of-the-email-recipient" />
					</dd>
					<dt>
						[$TO_NAME$]
					</dt>
					<dd>
						<liferay-ui:message key="the-name-of-the-email-recipient" />
					</dd>
				</dl>
			</div>

			<aui:script use="aui-base">
				var <portlet:namespace />changeNotificationTemplate = function(parameterName, parameterValue) {
					if (confirm('<liferay-ui:message key="changing-templates-could-lose-unsaved-data-do-you-want-to-proceed" />')) {
						window.location.href = '<%= portletURL %>&' + parameterName + '=' + parameterValue;
					}
				};

				var notificationType = A.one('#<portlet:namespace />notificationType');
				var notificationTemplateType = A.one('#<portlet:namespace />notificationTemplateType');

				if (notificationType) {
					notificationType.on(
						'change',
						function(event) {
							<portlet:namespace />changeNotificationTemplate('notificationType', event.currentTarget.val());
						}
					);
				}

				if (notificationTemplateType) {
					notificationTemplateType.on(
						'change',
						function(event) {
							<portlet:namespace />changeNotificationTemplate('notificationTemplateType', event.currentTarget.val());
						}
					);
				}
			</aui:script>

			<aui:script>
				function <portlet:namespace />initEditor() {
					<c:if test='<%= currentTab.equals("templates") %>'>
						return '<%= UnicodeFormatter.toString(template.getBody()) %>';
					</c:if>
				}

				function <portlet:namespace />updateCalendar() {
					<c:if test='<%= currentTab.equals("templates") %>'>
						var notificationTemplateContentBody = document.getElementById('<portlet:namespace /><%= HtmlUtil.escapeJS(bodyParameterName) %>');

						if (notificationTemplateContentBody) {
							notificationTemplateContentBody.value = window.<portlet:namespace />editor.getHTML();
						}
					</c:if>

					submitForm(document.<portlet:namespace />fm);
				}

				Liferay.Util.toggleBoxes('<portlet:namespace />enableRssCheckbox', '<portlet:namespace />rssTimeIntervalContainer');
			</aui:script>
		</c:when>
	</c:choose>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>

</aui:form>