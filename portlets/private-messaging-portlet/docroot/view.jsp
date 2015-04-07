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
Group group = themeDisplay.getScopeGroup();
LayoutSet layoutSet = themeDisplay.getLayoutSet();
%>

<c:choose>
	<c:when test="<%= group.isUser() && layoutSet.isPrivateLayout() %>">

		<%
		long mbThreadId = ParamUtil.getLong(request, "mbThreadId");
		%>

		<div class="private-messaging-container" id="<portlet:namespace />privateMessagingContainer">
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
			new Liferay.PrivateMessaging(
				{
					baseActionURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.ACTION_PHASE) %>',
					baseRenderURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.RENDER_PHASE) %>',
					namespace: '<portlet:namespace />',
					portletId: '<%= portletDisplay.getId() %>'
				}
			);
		</aui:script>
	</c:when>
	<c:otherwise>
		<div class="alert alert-danger">
			<liferay-ui:message key="this-application-only-functions-when-placed-on-a-user's-private-page" />
		</div>
	</c:otherwise>
</c:choose>