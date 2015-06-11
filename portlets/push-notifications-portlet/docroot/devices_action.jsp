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

PushNotificationsDevice pushNotificationDevice = (PushNotificationsDevice)row.getObject();

long pushNotificationDeviceId = pushNotificationDevice.getPushNotificationsDeviceId();
long userId = pushNotificationDevice.getUserId();
%>

<liferay-ui:icon-menu>

	<c:if test="<%= UserPermissionUtil.contains(permissionChecker, userId, ActionKeys.DELETE) %>">

		<liferay-portlet:actionURL name="deleteDevice" var="deleteDeviceUrl">
			<portlet:param name="pushNotificationDeviceId" value="<%= String.valueOf(pushNotificationDeviceId) %>" />
			<portlet:param name="tabs1" value="devices" />
		</liferay-portlet:actionURL>

		<liferay-ui:icon-delete confirmation="are-you-sure-you-want-to-delete-the-device" url="<%= deleteDeviceUrl %>"  />
	</c:if>
</liferay-ui:icon-menu>