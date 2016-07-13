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

SyncDevice syncDevice = (SyncDevice)row.getObject();

String syncDeviceId = String.valueOf(syncDevice.getSyncDeviceId());

PortletURL currentURLObj = PortletURLUtil.getCurrent(liferayPortletRequest, liferayPortletResponse);

String currentURL = currentURLObj.toString();
%>

<liferay-ui:icon-menu icon="<%= StringPool.BLANK %>" message="<%= StringPool.BLANK %>" showWhenSingleIcon="<%= true %>">
	<c:choose>
		<c:when test="<%= syncDevice.getStatus() == SyncDeviceConstants.STATUS_ACTIVE %>">
			<portlet:actionURL name="updateDevice" var="disableDeviceURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="status" value="<%= String.valueOf(SyncDeviceConstants.STATUS_INACTIVE) %>" />
				<portlet:param name="syncDeviceId" value="<%= syncDeviceId %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				image="unlink"
				label="<%= true %>"
				message="disable-sync-device"
				url="<%= disableDeviceURL %>"
			/>
		</c:when>
		<c:otherwise>
			<c:if test="<%= syncDevice.getStatus() != SyncDeviceConstants.STATUS_WIPED %>">
				<portlet:actionURL name="updateDevice" var="enableDeviceURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="status" value="<%= String.valueOf(SyncDeviceConstants.STATUS_ACTIVE) %>" />
					<portlet:param name="syncDeviceId" value="<%= syncDeviceId %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					image="check"
					label="<%= true %>"
					message="enable-sync-device"
					url="<%= enableDeviceURL %>"
				/>
			</c:if>

			<c:if test="<%= syncDevice.getStatus() == SyncDeviceConstants.STATUS_INACTIVE %>">
				<portlet:actionURL name="updateDevice" var="wipeDeviceURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="status" value="<%= String.valueOf(SyncDeviceConstants.STATUS_PENDING_WIPE) %>" />
					<portlet:param name="syncDeviceId" value="<%= syncDeviceId %>" />
				</portlet:actionURL>

				<liferay-ui:icon-delete
					confirmation="wiping-a-sync-device-will-delete-all-associated-files-from-the-client"
					image="trash"
					label="<%= true %>"
					message="wipe-sync-device"
					url="<%= wipeDeviceURL %>"
				/>
			</c:if>

			<portlet:actionURL name="deleteDevice" var="deleteDeviceURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="syncDeviceId" value="<%= syncDeviceId %>" />
			</portlet:actionURL>

			<liferay-ui:icon-delete
				label="<%= true %>"
				message="delete-sync-device"
				url="<%= deleteDeviceURL %>"
			/>
		</c:otherwise>
	</c:choose>
</liferay-ui:icon-menu>