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
	key="feedbackDeleted"
	message="feedback-deleted-successfully"
/>

<liferay-ui:success
	key="feedbackStatusUpdated"
	message="feedback-status-updated-successfully"
/>

<liferay-ui:success
	key="feedbackSaved"
	message="feedback-saved-successfully"
/>

<%
String navItem = ParamUtil.getString(request, "navItem", "viewNewFeedback");

KBFeedbackListDisplayContext kbFeedbackListDisplayContext = new KBFeedbackListDisplayContext(scopeGroupId, navItem);

request.setAttribute(WebKeys.KB_FEEDBACK_LIST_DISPLAY_CONTEXT, kbFeedbackListDisplayContext);
%>

<liferay-util:include page="/admin/common/view_feedback_by_status.jsp" servletContext="<%= application %>" />