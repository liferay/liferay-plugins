<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/init.jsp" %>

<%
long mbThreadId = ParamUtil.getLong(request, "mbThreadId");
%>

<div class="private-messaging-container">
	<c:choose>
		<c:when test="<%= !themeDisplay.isSignedIn() %>">
			<liferay-ui:message key="please-sign-in-to-use-the-private-messaging-portlet" />
		</c:when>
		<c:when test="<%= (mbThreadId != 0) && PrivateMessagingUtil.isUserPartOfThread(user.getUserId(), mbThreadId) %>">
			<aui:layout cssClass="thread">
				<%@ include file="/view_thread.jspf" %>
			</aui:layout>
		</c:when>
		<c:otherwise>
			<aui:layout cssClass="messages">
				<%@ include file="/view_messages.jspf" %>
			</aui:layout>
		</c:otherwise>
	</c:choose>
</div>

<aui:script use="liferay-plugin-privatemessaging">
	Liferay.PrivateMessaging.init(
		{
			deleteMessagesURL: '<%= _getActionURL("deleteMessages", layout.getPlid(), request, themeDisplay) %>',
			markMessagesAsReadURL: '<%= _getActionURL("markMessagesAsRead", layout.getPlid(), request, themeDisplay) %>',
			markMessagesAsUnreadURL: '<%= _getActionURL("markMessagesAsUnread", layout.getPlid(), request, themeDisplay) %>',
			namespace: '<portlet:namespace />',

			<%
			LiferayPortletURL redirectURL = PortletURLFactoryUtil.create(request, "1_WAR_privatemessagingportlet", layout.getPlid(), PortletRequest.RENDER_PHASE);

			redirectURL.setWindowState(LiferayWindowState.NORMAL);

			LiferayPortletURL newMessageURL = PortletURLFactoryUtil.create(request, "1_WAR_privatemessagingportlet", layout.getPlid(), PortletRequest.RESOURCE_PHASE);

			newMessageURL.setParameter("mvcPath", "/new_message.jsp");
			newMessageURL.setParameter("redirect", redirectURL.toString());
			newMessageURL.setWindowState(LiferayWindowState.EXCLUSIVE);
			%>

			newMessageURL: '<%= newMessageURL %>'
		}
	);
</aui:script>