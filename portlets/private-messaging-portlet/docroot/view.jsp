<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
			namespace: '<portlet:namespace />'
		}
	);
</aui:script>