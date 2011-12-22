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

<%@ include file="/display/init.jsp" %>

<%
String jspPage = ParamUtil.getString(request, "jspPage");

int status = (Integer)request.getAttribute(WebKeys.KNOWLEDGE_BASE_STATUS);

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

KBArticle kbArticle = (KBArticle)row.getObject();
%>

<liferay-ui:icon-menu cssClass="kb-article-action">
	<c:if test="<%= DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADMINISTRATOR) && KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.UPDATE) %>">
		<liferay-portlet:renderURL var="editURL">
			<portlet:param name="jspPage" value="/display/edit_article.jsp" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
			<portlet:param name="status" value="<%= String.valueOf(WorkflowConstants.STATUS_ANY) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			method="get"
			url="<%= editURL %>"
		/>
	</c:if>

	<c:if test="<%= kbArticle.isRoot() && KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= KBArticle.class.getName() %>"
			modelResourceDescription="<%= kbArticle.getTitle() %>"
			resourcePrimKey="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>"
			var="permissionsURL"
		/>

		<liferay-ui:icon
			image="permissions"
			method="get"
			url="<%= permissionsURL %>"
		/>
	</c:if>

	<c:if test="<%= kbArticle.isApproved() || !kbArticle.isFirstVersion() %>">
		<liferay-portlet:resourceURL id="kbArticleRSS" var="kbArticleRSSURL">
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
			<portlet:param name="rssDelta" value="<%= String.valueOf(rssDelta) %>" />
			<portlet:param name="rssDisplayStyle" value="<%= rssDisplayStyle %>" />
			<portlet:param name="rssFormat" value="<%= rssFormat %>" />
		</liferay-portlet:resourceURL>

		<liferay-ui:icon
			image="rss"
			method="get"
			target="_blank"
			url="<%= kbArticleRSSURL %>"
		/>
	</c:if>

	<c:if test="<%= (kbArticle.isApproved() || !kbArticle.isFirstVersion()) && KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.SUBSCRIBE) %>">
		<c:choose>
			<c:when test="<%= SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), KBArticle.class.getName(), kbArticle.getResourcePrimKey()) %>">
				<liferay-portlet:actionURL name="unsubscribeKBArticle" var="unsubscribeKBArticleURL">
					<portlet:param name="redirect" value="<%= redirect %>" />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
				</liferay-portlet:actionURL>

				<liferay-ui:icon
					image="unsubscribe"
					url="<%= unsubscribeKBArticleURL %>"
				/>
			</c:when>
			<c:otherwise>
				<liferay-portlet:actionURL name="subscribeKBArticle" var="subscribeKBArticleURL">
					<portlet:param name="redirect" value="<%= redirect %>" />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
				</liferay-portlet:actionURL>

				<liferay-ui:icon
					image="subscribe"
					url="<%= subscribeKBArticleURL %>"
				/>
			</c:otherwise>
		</c:choose>
	</c:if>

	<c:if test="<%= KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.MOVE_KB_ARTICLE) %>">
		<liferay-portlet:renderURL var="moveKBArticleURL">
			<portlet:param name="jspPage" value="/display/move_article.jsp" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
			<portlet:param name="status" value="<%= String.valueOf(status) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:icon
			image="forward"
			message="move"
			method="get"
			url="<%= moveKBArticleURL %>"
		/>
	</c:if>

	<c:if test="<%= KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.DELETE) %>">
		<liferay-portlet:actionURL name="deleteKBArticle" var="deleteURL">
			<portlet:param name="jspPage" value="<%= jspPage %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
			<portlet:param name="status" value="<%= String.valueOf(status) %>" />
		</liferay-portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>