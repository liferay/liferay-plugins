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

<%@ include file="/friends_activities/init.jsp" %>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationActionURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<liferay-portlet:renderURL portletConfiguration="true" var="configurationRenderURL" />

	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />

	<aui:fieldset>
		<aui:select label="maximum-activities-to-display" name="preferences--max--" value="<%= max %>">
			<aui:option label="1" />
			<aui:option label="2" />
			<aui:option label="3" />
			<aui:option label="4" />
			<aui:option label="5" />
			<aui:option label="10" />
			<aui:option label="15" />
			<aui:option label="20" />
			<aui:option label="25" />
			<aui:option label="30" />
			<aui:option label="40" />
			<aui:option label="50" />
			<aui:option label="60" />
			<aui:option label="70" />
			<aui:option label="80" />
			<aui:option label="90" />
			<aui:option label="100" />
		</aui:select>
	</aui:fieldset>

	<c:if test="<%= PortalUtil.isRSSFeedsEnabled() %>">
		<liferay-ui:rss-settings
			delta="<%= rssDelta %>"
			displayStyle="<%= rssDisplayStyle %>"
			enabled="<%= enableRSS %>"
			feedType="<%= rssFeedType %>"
		/>
	</c:if>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>