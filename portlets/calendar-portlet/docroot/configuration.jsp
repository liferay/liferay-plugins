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
String tabs2 = ParamUtil.getString(request, "tabs2", "email-from");

String redirect = ParamUtil.getString(request, "redirect");

String emailFromName = ParamUtil.getString(request, "emailFromName", CalendarUtil.getEmailFromName(preferences, themeDisplay.getCompanyId()));
String emailFromAddress = ParamUtil.getString(request, "emailFromAddress", CalendarUtil.getEmailFromAddress(preferences, themeDisplay.getCompanyId()));

String emailBookingReminderSubject = ParamUtil.getString(request, "emailBookingReminderSubject", CalendarUtil.getEmailBookingReminderSubject(preferences));
String emailBookingReminderBody = ParamUtil.getString(request, "emailBookingReminderBody", CalendarUtil.getEmailBookingReminderBody(preferences));

String emailBookingNotificationSubject = ParamUtil.getString(request, "emailNotificationReminderSubject", CalendarUtil.getEmailBookingNotificationSubject(preferences));
String emailBookingNotificationBody = ParamUtil.getString(request, "emailBookingNotificationBody", CalendarUtil.getEmailBookingNotificationBody(preferences));

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

	<liferay-ui:tabs
		names="email-from,booking-reminder-email,booking-notification-email,display-settings"
		param="tabs2"
		url="<%= portletURL %>"
	/>

	<liferay-ui:error key="emailBookingNotificationBody" message="please-enter-a-valid-body" />
	<liferay-ui:error key="emailBookingNotificationSubject" message="please-enter-a-valid-subject" />
	<liferay-ui:error key="emailBookingReminderBody" message="please-enter-a-valid-body" />
	<liferay-ui:error key="emailBookingReminderSubject" message="please-enter-a-valid-subject" />
	<liferay-ui:error key="emailFromAddress" message="please-enter-a-valid-email-address" />
	<liferay-ui:error key="emailFromName" message="please-enter-a-valid-name" />

	<c:choose>
		<c:when test='<%= tabs2.equals("email-from") %>'>
			<aui:fieldset>
				<aui:input cssClass="lfr-input-text-container" label="name" name="preferences--emailFromName--" type="text" value="<%= emailFromName %>" />

				<aui:input cssClass="lfr-input-text-container" label="address" name="preferences--emailFromAddress--" type="text" value="<%= emailFromAddress %>" />
			</aui:fieldset>
		</c:when>
		<c:when test='<%= tabs2.equals("booking-reminder-email") %>'>
			<aui:fieldset>
				<aui:input label="enabled" name="preferences--emailBookingReminderEnabled--" type="checkbox" value="<%= CalendarUtil.getEmailBookingReminderEnabled(preferences) %>" />

				<aui:input cssClass="lfr-input-text-container" label="subject" name="preferences--emailBookingReminderSubject--" type="text" value="<%= emailBookingReminderSubject %>" />

				<aui:field-wrapper label="body">
					<liferay-ui:input-editor editorImpl="ckeditor" />

					<aui:input name="preferences--emailBookingReminderBody--" type="hidden" />
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
						[$PORTLET_NAME$]
					</dt>
					<dd>
						<%= PortalUtil.getPortletTitle(renderResponse) %>
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
		<c:when test='<%= tabs2.equals("booking-notification-email") %>'>
			<aui:fieldset>
				<aui:input label="enabled" name="preferences--emailBookingNotificationEnabled--" type="checkbox" value="<%= CalendarUtil.getEmailBookingNotificationEnabled(preferences) %>" />

				<aui:input cssClass="lfr-input-text-container" label="subject" name="preferences--emailBookingNotificationSubject--" type="text" value="<%= emailBookingNotificationSubject %>" />

				<aui:field-wrapper label="body">
					<liferay-ui:input-editor editorImpl="ckeditor" />

					<aui:input name="preferences--emailBookingNotificationBody--" type="hidden" />
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
						[$PORTLET_NAME$]
					</dt>
					<dd>
						<%= PortalUtil.getPortletTitle(renderResponse) %>
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
		<c:when test='<%= tabs2.equals("display-settings") %>'>
			<aui:fieldset>
				<aui:input name="preferences--dayViewHeaderDateFormat--" type="text" value="<%= dayViewHeaderDateFormat %>" />

				<aui:input name="preferences--navigationHeaderDateFormat--" type="text" value="<%= navigationHeaderDateFormat %>" />

				<aui:input name="preferences--isoTimeFormat--" type="checkbox" value="<%= isoTimeFormat %>" />
			</aui:fieldset>
		</c:when>
	</c:choose>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />initEditor() {
		<c:choose>
			<c:when test='<%= tabs2.equals("booking-reminder-email") %>'>
				return "<%= UnicodeFormatter.toString(emailBookingReminderBody) %>";
			</c:when>
			<c:when test='<%= tabs2.equals("booking-notification-email") %>'>
				return "<%= UnicodeFormatter.toString(emailBookingNotificationBody) %>";
			</c:when>
		</c:choose>
	}

	function <portlet:namespace />saveConfiguration() {
		<c:choose>
			<c:when test='<%= tabs2.equals("booking-reminder-email") %>'>
				document.<portlet:namespace />fm.<portlet:namespace />emailBookingReminderBody.value = window.<portlet:namespace />editor.getHTML();
			</c:when>
			<c:when test='<%= tabs2.equals("booking-notification-email") %>'>
				document.<portlet:namespace />fm.<portlet:namespace />emailBookingNotificationBody.value = window.<portlet:namespace />editor.getHTML();
			</c:when>
		</c:choose>

		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>
