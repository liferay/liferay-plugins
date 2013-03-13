<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

<%@ include file="/html/taglib/init.jsp" %>

<%@ page import="com.liferay.portlet.messageboards.model.MBMessage" %>
<%@ page import="com.liferay.portlet.wiki.model.WikiPage" %>

<%
Object bean = request.getAttribute("aui:workflow-status:bean");
int status = GetterUtil.getInteger((String)request.getAttribute("aui:workflow-status:status"));
%>

<liferay-util:buffer var="html">
	<liferay-util:include page="/html/taglib/aui/workflow_status/page.portal.jsp" />
</liferay-util:buffer>

<%
boolean displayMessage = false;

if (status == WorkflowConstants.STATUS_DENIED) {
	if (bean instanceof MBMessage) {
		MBMessage message = (MBMessage)bean;

		if (message.getUserId() == themeDisplay.getUserId()) {
			displayMessage = true;

			String deniedMessage = LanguageUtil.get(pageContext, WorkflowConstants.toLabel(status));

			int pos = html.indexOf(deniedMessage);

			StringBundler sb = new StringBundler(4);

			sb.append(html.substring(0, pos + deniedMessage.length()));
			sb.append("<br />");
			sb.append(LanguageUtil.get(pageContext, "your-message-has-been-flagged-as-spam.-an-administrator-will-review-your-message-as-soon-as-possible"));
			sb.append(html.substring(pos + deniedMessage.length()));

			html = sb.toString();
		}
	}
	else if (bean instanceof WikiPage) {
		WikiPage wikiPage = (WikiPage)bean;

		if (wikiPage.getUserId() == themeDisplay.getUserId()) {
			displayMessage = true;

			String deniedMessage = LanguageUtil.get(pageContext, WorkflowConstants.toLabel(status));

			int pos = html.indexOf(deniedMessage);

			StringBundler sb = new StringBundler(4);

			sb.append(html.substring(0, pos + deniedMessage.length()));
			sb.append("<br />");
			sb.append(LanguageUtil.get(pageContext, "this-version-has-been-flagged-as-spam.-an-administrator-will-review-your-version-as-soon-as-possible"));
			sb.append(html.substring(pos + deniedMessage.length()));

			html = sb.toString();
		}
	}
}
%>

<c:if test="<%= displayMessage %>">
	<div class="portlet-msg-error">
</c:if>

<%= html %>

<c:if test="<%= displayMessage %>">
	</div>
</c:if>