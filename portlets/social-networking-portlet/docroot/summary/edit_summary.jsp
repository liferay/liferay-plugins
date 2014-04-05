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
String aboutMe = HtmlUtil.escape(ExpandoValueLocalServiceUtil.getData(User.class.getName(), "SN", "aboutMe", user2.getUserId(), StringPool.BLANK));
%>

<portlet:renderURL var="redirectURL" windowState="<%= WindowState.NORMAL.toString() %>" />

<portlet:actionURL name="updateSummary" var="updateSummaryURL" />

<aui:form action="<%= updateSummaryURL %>" method="post" name="<portlet:namespace />fm">
	<aui:input name="redirect" type="hidden" value="<%= redirectURL %>" />

	<aui:model-context bean="<%= user2.getContact() %>" model="<%= Contact.class %>" />

	<div class="alert alert-info">
		<liferay-ui:message arguments="<%= new Object[] {themeDisplay.getURLMyAccount()} %>" key="use-my-account-to-change-regular-account-settings" translateArguments="<%= false %>" />
	</div>

	<aui:input name="jobTitle" />

	<aui:input label="about-me" name="aboutMe" type="textarea" value="<%= aboutMe %>" />

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirectURL %>" value="cancel" />
	</aui:button-row>
</aui:form>