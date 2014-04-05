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

<%@ include file="/members_activities/init.jsp" %>

<c:choose>
	<c:when test="<%= group.isUser() %>">
		<div class="portlet-msg-error">
			<liferay-ui:message key="this-application-will-only-function-when-placed-on-a-site-page" />
		</div>
	</c:when>
	<c:otherwise>
		<%@ include file="/members_activities/view_members_activities.jspf" %>
	</c:otherwise>
</c:choose>