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
String tabs1 = ParamUtil.getString(request, "tabs1", "devices");
String tabs2 = ParamUtil.getString(request, "tabs2", "android");

String orderByCol = ParamUtil.getString(request, "orderByCol", "platform");
String orderByType = ParamUtil.getString(request, "orderByType", "asc");

OrderByComparator orderByComparator = PushNotificationsUtil.getPushNotificationsDeviceOrderByComparator(orderByCol, orderByType);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("tabs2", tabs2);
%>

<liferay-ui:tabs
	names="devices,configuration"
	param="tabs1"
	portletURL="<%= portletURL %>"
/>

<c:choose>
	<c:when test='<%= tabs1.equals("devices") %>'>
		<liferay-ui:success key="pushNotificationsDeviceDeleted" message="the-device-was-deleted-successfully" />

		<liferay-ui:search-container
			emptyResultsMessage="no-devices-were-found"
			iteratorURL="<%= portletURL %>"
			orderByCol="<%= orderByCol %>"
			orderByComparator="<%= orderByComparator %>"
			orderByType="<%= orderByType %>"
			total="<%= PushNotificationsDeviceLocalServiceUtil.getPushNotificationsDevicesCount() %>"
		>
			<liferay-ui:search-container-results
				results="<%= PushNotificationsDeviceLocalServiceUtil.getPushNotificationsDevices(searchContainer.getStart(), searchContainer.getEnd(), orderByComparator) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.pushnotifications.model.PushNotificationsDevice"
				escapedModel="<%= true %>"
				keyProperty="pushNotificationsDeviceId"
				modelVar="device"
			>

				<%
				User deviceUser = UserLocalServiceUtil.getUser(device.getUserId());
				%>

				<liferay-ui:search-container-column-text name="full-name" value="<%= deviceUser.getFullName() %>" />

				<liferay-ui:search-container-column-text name="token" />

				<liferay-ui:search-container-column-text name="platform" orderable="<%= true %>" value="<%= LanguageUtil.get(request, device.getPlatform()) %>" />

				<liferay-ui:search-container-column-jsp align="right" path="/push_notifications_device_action.jsp" />
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</c:when>
	<c:otherwise>
		<%@ include file="/configuration.jspf" %>
	</c:otherwise>
</c:choose>