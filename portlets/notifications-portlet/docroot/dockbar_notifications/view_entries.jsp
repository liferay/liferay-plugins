<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
List<UserNotificationEvent> userNotificationEvents = UserNotificationEventLocalServiceUtil.getUserNotificationEvents(themeDisplay.getUserId(), 0, 8);
%>

<c:if test="<%= userNotificationEvents.isEmpty() %>">
	<li class="user-notification">
		<a href="javascript:;"><liferay-ui:message key="you-do-not-have-any-notifications" /></a>
	</li>
</c:if>

<%
for (UserNotificationEvent userNotificationEvent : userNotificationEvents) {
	JSONObject userNotificationEventJSONObject = JSONFactoryUtil.createJSONObject(userNotificationEvent.getPayload());

	long userId = userNotificationEventJSONObject.getLong("userId");

	String userFullName = HtmlUtil.escape(PortalUtil.getUserName(userId, StringPool.BLANK));

	String userPortaitURL = StringPool.BLANK;

	User curUser = UserLocalServiceUtil.fetchUserById(userId);

	if (curUser != null) {
		userPortaitURL = curUser.getPortraitURL(themeDisplay);
	}

	String url = userNotificationEventJSONObject.getString("url");

	boolean read = userNotificationEvent.isArchived();
%>

	<li class="user-notification<%= read ? "" : " unread" %>">
		<c:choose>
			<c:when test="<%= read %>">
				<a href="<%= url %>">
			</c:when>
			<c:otherwise>
				<liferay-portlet:actionURL name="markAsRead" var="markAsReadURL"><portlet:param name="userNotificationEventId" value="<%= String.valueOf(userNotificationEvent.getUserNotificationEventId()) %>" /></liferay-portlet:actionURL>

				<a data-markAsReadURL="<%= markAsReadURL %>" href="<%= url %>">
			</c:otherwise>
		</c:choose>

			<div class="sender">
				<span class="thumbnail">
					<img alt="<%= userFullName %>" src="<%= userPortaitURL %>" />
				</span>
			</div>

			<div class="content">
				<div class="body">
					<%= HtmlUtil.escape(userNotificationEventJSONObject.getString("notificationMessage")) %>
				</div>

				<div class="timestamp">
					<span class="portlet-icon">
						<liferay-portlet:icon-portlet
							portlet="<%= PortletLocalServiceUtil.getPortletById(company.getCompanyId(), userNotificationEvent.getType()) %>"
						/>
					</span>

					<%= simpleDateFormat.format(userNotificationEvent.getTimestamp()) %>
				</div>

				<div class="read">
					<liferay-ui:message key='<%= read ? "read" : "unread" %>' />
				</div>
			</div>
		</a>
	</li>

<%
}
%>