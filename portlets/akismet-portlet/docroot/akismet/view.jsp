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

<liferay-portlet:actionURL name="updateConfiguration" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<liferay-ui:error key="apiKeyError" message="unable-to-validate-akismet-api-key" />

	<aui:fieldset>
		<aui:input label="enable-for-message-boards" name="messageBoardsEnabled" type="checkbox" value="<%= AkismetUtil.isMessageBoardsEnabled(company.getCompanyId()) %>" />

		<aui:input label="enable-for-discussions" name="discussionsEnabled" type="checkbox" value="<%= AkismetUtil.isDiscussionsEnabled(company.getCompanyId()) %>" />

		<aui:input label="enable-for-wiki" name="wikiEnabled" type="checkbox" value="<%= AkismetUtil.isWikiEnabled(company.getCompanyId()) %>" />

		<aui:input label="api-key" name="apiKey" type="text" value="<%= PrefsPortletPropsUtil.getString(company.getCompanyId(), PortletPropsKeys.AKISMET_API_KEY) %>" />

		<aui:input helpMessage="reportable-time-help" label="reportable-time" name="reportableTime" type="text" value="<%= PrefsPortletPropsUtil.getString(company.getCompanyId(), PortletPropsKeys.AKISMET_REPORTABLE_TIME) %>" />

		<aui:input helpMessage="check-threshold-help" label="check-threshold" name="checkThreshold" type="text" value="<%= PrefsPortletPropsUtil.getInteger(company.getCompanyId(), PortletPropsKeys.AKISMET_CHECK_THRESHOLD) %>" />

		<aui:button-row>
			<aui:button type="submit" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>