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
List<User> users = (List<User>)request.getAttribute("view_users.jsp-users");
%>

<c:if test="<%= users != null %>">
	<div class="asset-image-container">

		<%
		PortletURL userURL = renderResponse.createRenderURL();

		userURL.setParameter("jspPage", "/contacts_center/view_user.jsp");
		userURL.setParameter("backURL", currentURL);

		for (User user2 : users) {
			userURL.setParameter("userId", String.valueOf(user2.getUserId()));
		%>

			<div class="asset-image">
				<a href="<%= userURL.toString() %>"><img alt="<%= HtmlUtil.escape(user2.getFullName()) %>" src="<%= user2.getPortraitURL(themeDisplay) %>" /></a>
			</div>

		<%
		}
		%>

	</div>
</c:if>