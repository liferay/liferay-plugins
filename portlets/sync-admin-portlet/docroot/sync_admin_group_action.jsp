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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Group group = (Group)row.getObject();

String groupId = String.valueOf(group.getGroupId());

PortletURL currentURLObj = PortletURLUtil.getCurrent(liferayPortletRequest, liferayPortletResponse);

String currentURL = currentURLObj.toString();
%>

<liferay-ui:icon-menu icon="" message="">
	<c:choose>
		<c:when test='<%= !GetterUtil.getBoolean(group.getTypeSettingsProperty("syncEnabled"), true) %>'>
			<portlet:actionURL name="configureSite" var="configureSiteURL">
				<portlet:param name="enableSyncSites" value="true" />
				<portlet:param name="groupIds" value="<%= groupId %>" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				iconCssClass="icon-ok-sign"
				label="<%= true %>"
				message="enable-sync-site"
				url="<%= configureSiteURL %>"
			/>
		</c:when>
		<c:otherwise>
			<portlet:actionURL name="configureSite" var="configureSiteURL">
				<portlet:param name="enableSyncSites" value="false" />
				<portlet:param name="groupIds" value="<%= groupId %>" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
			</portlet:actionURL>

			<liferay-ui:icon-delete
				confirmation="disabling-a-sync-site-will-delete-all-associated-files-from-all-clients"
				label="<%= true %>"
				message="disable-sync-site"
				url="<%= configureSiteURL %>"
			/>
		</c:otherwise>
	</c:choose>

	<portlet:actionURL name="configurePermissions" var="setViewPermissionURL">
		<portlet:param name="groupIds" value="<%= groupId %>" />
		<portlet:param name="permissions" value="view-permission" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
	</portlet:actionURL>

	<liferay-ui:icon
		iconCssClass="icon-ok-sign"
		label="<%= true %>"
		message="set-view-permission"
		url="<%= setViewPermissionURL %>"
	/>

	<portlet:actionURL name="configurePermissions" var="setViewAndAddDiscussionPermissionURL">
		<portlet:param name="groupIds" value="<%= groupId %>" />
		<portlet:param name="permissions" value="view-and-add-discussion-permission" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
	</portlet:actionURL>

	<liferay-ui:icon
		iconCssClass="icon-ok-sign"
		label="<%= true %>"
		message="set-view-and-add-discussion-permission"
		url="<%= setViewAndAddDiscussionPermissionURL %>"
	/>

	<portlet:actionURL name="configurePermissions" var="setFullAccessPermissionURL">
		<portlet:param name="groupIds" value="<%= groupId %>" />
		<portlet:param name="permissions" value="full-access-permission" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
	</portlet:actionURL>

	<liferay-ui:icon
		iconCssClass="icon-ok-sign"
		label="<%= true %>"
		message="set-full-access-permission"
		url="<%= setFullAccessPermissionURL %>"
	/>
</liferay-ui:icon-menu>