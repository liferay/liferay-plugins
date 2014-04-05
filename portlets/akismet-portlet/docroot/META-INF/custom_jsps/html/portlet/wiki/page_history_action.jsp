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

<%@ include file="/html/portlet/wiki/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

WikiPage wikiPage = (WikiPage)row.getObject();
%>

<c:if test="<%= (wikiPage.getStatus() == WorkflowConstants.STATUS_APPROVED) && WikiPagePermission.contains(permissionChecker, wikiPage, ActionKeys.UPDATE) && !_isSpam(wikiPage) && !_isPendingApproval(wikiPage) %>">
	<liferay-util:include page="/html/portlet/wiki/page_history_action.portal.jsp" />
</c:if>