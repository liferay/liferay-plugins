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

<c:choose>
	<c:when test="<%= user2 == null %>">
		<div class="alert alert-error">
			<liferay-ui:message key="this-application-will-only-function-when-placed-on-a-user-page" />
		</div>
	</c:when>
	<c:otherwise>
		<%@ include file="/friends/view_friends.jspf" %>
	</c:otherwise>
</c:choose>