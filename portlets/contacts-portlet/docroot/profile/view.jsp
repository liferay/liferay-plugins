<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
Group group = themeDisplay.getScopeGroup();
%>

<c:choose>
	<c:when test="<%= group.isUser() %>">

		<%
		User user2 = UserLocalServiceUtil.getUserById(group.getClassPK());

		request.setAttribute(WebKeys.CONTACTS_USER, user2);
		%>

		<liferay-util:include page="/contacts_center/view_user.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:otherwise>
		<div class="lfr-message-info">
			<liferay-ui:message key="this-application-will-only-function-when-placed-on-a-user-page" />
		</div>
	</c:otherwise>
</c:choose>