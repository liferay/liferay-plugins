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

long calendarResourceId = 0;

List<Calendar> calendars = null;

if (calendarResource != null) {
	calendarResourceId = calendarResource.getCalendarResourceId();

	calendars = CalendarServiceUtil.getCalendarResourceCalendars(themeDisplay.getScopeGroupId(), calendarResourceId);
}

String code = BeanParamUtil.getString(calendarResource, request, "code");
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= (calendarResource == null) ? "new-calendar-resource" : calendarResource.getName(locale) %>'
/>

<liferay-portlet:actionURL name="updateCalendarResource" var="updateCalendarResourceURL" />

<aui:form action="<%= updateCalendarResourceURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "updateCalendarResource();" %>'>
	<aui:input name="mvcPath" type="hidden" value="/edit_calendar_resource.jsp" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="calendarResourceId" type="hidden" value="<%= String.valueOf(calendarResourceId) %>" />

	<liferay-ui:error exception="<%= CalendarResourceCodeException.class %>" message="please-enter-a-valid-code" />
	<liferay-ui:error exception="<%= CalendarResourceNameException.class %>" message="please-enter-a-valid-name" />
	<liferay-ui:error exception="<%= DuplicateCalendarResourceException.class %>" message="please-enter-a-unique-resource-code" />

	<liferay-ui:asset-categories-error />

	<liferay-ui:asset-tags-error />

	<aui:model-context bean="<%= calendarResource %>" model="<%= CalendarResource.class %>" />

	<aui:fieldset>
		<aui:input name="name" />

		<liferay-ui:panel-container extended="<%= true %>" id="calendarResourceDetailsPanelContainer" persistState="<%= true %>">
			<liferay-ui:panel defaultState="closed" extended="<%= false %>" id="calendarResourceDetailsPanel" persistState="<%= true %>" title="details">
				<c:choose>
					<c:when test="<%= calendarResource == null %>">
						<c:if test="<%= !PortletPropsValues.CALENDAR_RESOURCE_FORCE_AUTOGENERATE_CODE %>">
							<aui:input name="code" />
						</c:if>
					</c:when>
					<c:otherwise>
						<aui:field-wrapper label="code">
							<liferay-ui:input-resource url="<%= code %>" />
						</aui:field-wrapper>
					</c:otherwise>
				</c:choose>

				<aui:input name="description" />

				<c:if test="<%= calendars != null %>">
					<aui:select label="default-calendar" name="defaultCalendarId" value="<%= calendarResource.getDefaultCalendarId() %>">

						<%
						for (Calendar calendar : calendars) {
						%>

							<aui:option label="<%= HtmlUtil.escapeAttribute(calendar.getName(locale)) %>" value="<%= calendar.getCalendarId() %>" />

						<%
						}
						%>

					</aui:select>
				</c:if>

				<aui:input inlineLabel="left" name="active" type="checkbox" value="<%= (calendarResource == null) ? true : calendarResource.isActive() %>" />
			</liferay-ui:panel>
			<liferay-ui:panel defaultState="closed" extended="<%= false %>" id="calendarResourceCategorizationPanel" persistState="<%= true %>" title="categorization">
				<aui:input classPK="<%= calendarResourceId %>" name="categories" type="assetCategories" />

				<aui:input classPK="<%= calendarResourceId %>" name="tags" type="assetTags" />
			</liferay-ui:panel>
		</liferay-ui:panel-container>

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
	function <portlet:namespace />getSuggestionsContent() {
		return document.<portlet:namespace />fm.<portlet:namespace />name.value + ' ' + document.<portlet:namespace />fm.<portlet:namespace />description.value;
	}

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