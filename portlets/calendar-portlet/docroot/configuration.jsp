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
String tabs2 = ParamUtil.getString(request, "tabs2", "user-settings");

String redirect = ParamUtil.getString(request, "redirect");

String emailFromName = ParamUtil.getString(request, "emailFromName", NotificationUtil.getEmailFromName(preferences, themeDisplay.getCompanyId()));
String emailFromAddress = ParamUtil.getString(request, "emailFromAddress", NotificationUtil.getEmailFromAddress(preferences, themeDisplay.getCompanyId()));

NotificationType notificationType = NotificationType.parse(ParamUtil.getString(request, "notificationType", PortletPropsValues.CALENDAR_NOTIFICATION_DEFAULT_TYPE));
NotificationTemplateType notificationTemplateType = NotificationTemplateType.parse(ParamUtil.getString(request, "notificationTemplateType", "reminder"));

String notificationTemplateContentBodyParameterName = NotificationUtil.getPreferenceName(PortletPropsKeys.CALENDAR_NOTIFICATION_BODY, notificationType, notificationTemplateType);
String notificationTemplateContentSubjectParameterName = NotificationUtil.getPreferenceName(PortletPropsKeys.CALENDAR_NOTIFICATION_SUBJECT, notificationType, notificationTemplateType);

String notificationTemplateContentBody = PrefsParamUtil.getString(preferences, request, notificationTemplateContentBodyParameterName, NotificationUtil.getNotificationTemplateContent(PortletPropsKeys.CALENDAR_NOTIFICATION_BODY, notificationType, notificationTemplateType));
String notificationTemplateContentSubject = PrefsParamUtil.getString(preferences, request, notificationTemplateContentSubjectParameterName, NotificationUtil.getNotificationTemplateContent(PortletPropsKeys.CALENDAR_NOTIFICATION_SUBJECT, notificationType, notificationTemplateType));
%>

<liferay-portlet:renderURL portletConfiguration="true" var="portletURL">
	<portlet:param name="tabs2" value="<%= tabs2 %>" />
	<portlet:param name="redirect" value="<%= redirect %>" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL portletConfiguration="true" var="actionURL" />

<aui:form action="<%= actionURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveConfiguration();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="tabs2" type="hidden" value="<%= tabs2 %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="notificationTemplateContentBodyParameterName" type="hidden" value="<%= notificationTemplateContentBodyParameterName %>" />
	<aui:input name="notificationTemplateContentSubjectParameterName" type="hidden" value="<%= notificationTemplateContentSubjectParameterName %>" />

	<liferay-ui:tabs
		names="user-settings,templates,email-from"
		param="tabs2"
		url="<%= portletURL %>"
	/>

	<liferay-ui:error key="emailFromAddress" message="please-enter-a-valid-email-address" />
	<liferay-ui:error key="emailFromName" message="please-enter-a-valid-name" />
	<liferay-ui:error key="notificationTemplateContentBody" message="please-enter-a-valid-body" />
	<liferay-ui:error key="notificationTemplateContentSubject" message="please-enter-a-valid-subject" />

	<c:choose>
		<c:when test='<%= tabs2.equals("user-settings") %>'>
			<aui:fieldset>
				<aui:select label="time-format" name="isoTimeFormat">
					<aui:option label="am-pm" selected="<%= !isoTimeFormat %>" value="<%= false %>" />
					<aui:option label="24-hour" selected="<%= isoTimeFormat %>" value="<%= true %>" />
				</aui:select>

				<aui:select label="default-duration" name="defaultDuration">
					<aui:option label='<%= LanguageUtil.format(pageContext, "x-minutes", "15") %>' selected='<%= defaultDuration == 15 %>' value="15" />
					<aui:option label='<%= LanguageUtil.format(pageContext, "x-minutes", "30") %>' selected='<%= defaultDuration == 30 %>' value="30" />
					<aui:option label='<%= LanguageUtil.format(pageContext, "x-minutes", "60") %>' selected='<%= defaultDuration == 60 %>' value="60" />
					<aui:option label='<%= LanguageUtil.format(pageContext, "x-minutes", "120") %>' selected='<%= defaultDuration == 120 %>' value="120" />
				</aui:select>

				<aui:select label="default-view" name="defaultView">
					<aui:option label="day" selected='<%= defaultView.equals("day") %>' value="day" />
					<aui:option label="month" selected='<%= defaultView.equals("month") %>' value="month" />
					<aui:option label="week" selected='<%= defaultView.equals("week") %>' value="week" />
				</aui:select>

				<aui:select label="week-starts-on" name="weekStartsOn">
					<aui:option label="weekday.SU" selected='<%= weekStartsOn == 0 %>' value="0" />
					<aui:option label="weekday.MO" selected='<%= weekStartsOn == 1 %>' value="1" />
					<aui:option label="weekday.SA" selected='<%= weekStartsOn == 6 %>' value="6" />
				</aui:select>

				<aui:input cssClass="calendar-portlet-time-zone-field" disabled="<%= usePortalTimeZone %>" label="time-zone" name="timeZoneId" type="timeZone" value="<%= timeZoneId %>" />

				<aui:input label="use-global-timezone" name="usePortalTimeZone" type="checkbox" value="<%= usePortalTimeZone %>" />
			</aui:fieldset>
		</c:when>
		<c:when test='<%= tabs2.equals("templates") %>'>
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

				<aui:select name="notificationTemplateType" value="<%= notificationTemplateType.getValue() %>">

					<%
					for (NotificationTemplateType curNotificationTemplateType : NotificationTemplateType.values()) {
					%>

						<aui:option label="<%= curNotificationTemplateType.getValue() %>" value="<%= curNotificationTemplateType.getValue() %>" />

					<%
					}
					%>

				</aui:select>

				<aui:input cssClass="lfr-input-text-container" label="subject" name='<%= "preferences--" + notificationTemplateContentSubjectParameterName + "--" %>' type="text" value="<%= notificationTemplateContentSubject %>" />

				<aui:field-wrapper label="body">
					<liferay-ui:input-editor editorImpl="ckeditor" />

					<aui:input name='<%= "preferences--" + notificationTemplateContentBodyParameterName + "--" %>' type="hidden" value="<%= notificationTemplateContentBody %>" />
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
		</c:when>
		<c:when test='<%= tabs2.equals("email-from") %>'>
			<aui:fieldset>
				<aui:input cssClass="lfr-input-text-container" label="name" name="preferences--emailFromName--" type="text" value="<%= emailFromName %>" />

				<aui:input cssClass="lfr-input-text-container" label="address" name="preferences--emailFromAddress--" type="text" value="<%= emailFromAddress %>" />
			</aui:fieldset>
		</c:when>
	</c:choose>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>

<aui:script use="aui-base">
	var <portlet:namespace />changeNotificationTemplate = function(parameterName, parameterValue) {
		if (confirm('<liferay-ui:message key="changing-templates-could-loose-unsaved-data-do-you-want-to-proceed" />')) {
			window.location.href = '<%= portletURL %>&' + parameterName + '=' + parameterValue;
		}
	};

	var notificationType = A.one('#<portlet:namespace />notificationType');
	var notificationTemplateType = A.one('#<portlet:namespace />notificationTemplateType');
	var usePortalTimeZoneCheckbox = A.one('#<portlet:namespace />usePortalTimeZoneCheckbox');

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

	if (usePortalTimeZoneCheckbox) {
		usePortalTimeZoneCheckbox.on(
			'change',
			function(event) {
				document.<portlet:namespace />fm.<portlet:namespace />timeZoneId.disabled = usePortalTimeZoneCheckbox.attr('checked');
			}
		);
	}
</aui:script>

<aui:script>
	function <portlet:namespace />initEditor() {
		<c:if test='<%= tabs2.equals("templates") %>'>
			return '<%= UnicodeFormatter.toString(notificationTemplateContentBody) %>';
		</c:if>
	}

	function <portlet:namespace />saveConfiguration() {
		<c:if test='<%= tabs2.equals("templates") %>'>
			var notificationTemplateContentBody = document.getElementById('<portlet:namespace /><%= HtmlUtil.escapeJS(notificationTemplateContentBodyParameterName) %>');

			if (notificationTemplateContentBody) {
				notificationTemplateContentBody.value = window.<portlet:namespace />editor.getHTML();
			}
		</c:if>

		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>