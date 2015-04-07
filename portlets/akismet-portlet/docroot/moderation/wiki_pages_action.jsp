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

<%
portletURL.setParameter("tabs1", "wikis");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

WikiPage wikiPage = (WikiPage)row.getObject();

long wikiPlid = PortalUtil.getPlidFromPortletId(wikiPage.getGroupId(), WikiPortletKeys.WIKI);
%>

<liferay-ui:icon-menu icon="<%= StringPool.BLANK %>" message="<%= StringPool.BLANK %>">
	<liferay-portlet:renderURL plid="<%= wikiPlid %>" portletName="<%= WikiPortletKeys.WIKI %>" var="viewURL">
		<portlet:param name="struts_action" value="/wiki/view" />
		<portlet:param name="nodeName" value="<%= wikiPage.getNode().getName() %>" />
		<portlet:param name="title" value="<%= wikiPage.getTitle() %>" />
		<portlet:param name="version" value="<%= String.valueOf(wikiPage.getVersion()) %>" />
	</liferay-portlet:renderURL>

	<liferay-ui:icon iconCssClass="icon-search" message="view-in-context" target="_blank" url="<%= viewURL %>" />

	<portlet:actionURL name="markNotSpamWikiPages" var="markAsHamURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="notSpamWikiPageIds" value="<%= String.valueOf(wikiPage.getPageId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon iconCssClass="icon-envelope-alt" message="not-spam" url="<%= markAsHamURL %>" />

	<portlet:actionURL name="spamWikiPages" var="spamURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
		<portlet:param name="spamWikiPageIds" value="<%= String.valueOf(wikiPage.getPageId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon image="delete" message="spam" url="<%= spamURL %>" />
</liferay-ui:icon-menu>