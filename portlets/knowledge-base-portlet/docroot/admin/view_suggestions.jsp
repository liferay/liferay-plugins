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

<%@ include file="/admin/init.jsp" %>

<liferay-util:include page="/admin/top_tabs.jsp" servletContext="<%= application %>" />

<liferay-ui:success
	key="suggestionDeleted"
	message="suggestion-deleted-successfully"
/>

<liferay-ui:success
	key="suggestionStatusUpdated"
	message="suggestion-status-updated-successfully"
/>

<liferay-ui:success
	key="suggestionSaved"
	message="suggestion-saved-successfully"
/>

<%
String navItem = ParamUtil.getString(request, "navItem", "viewNewSuggestions");

KBSuggestionListDisplayContext kbSuggestionListDisplayContext = new KBSuggestionListDisplayContext(request, templatePath, scopeGroupId, navItem);

request.setAttribute(WebKeys.KNOWLEDGE_BASE_KB_SUGGESTION_LIST_DISPLAY_CONTEXT, kbSuggestionListDisplayContext);
%>

<liferay-util:include page="/admin/common/view_suggestions_by_status.jsp" servletContext="<%= application %>" />