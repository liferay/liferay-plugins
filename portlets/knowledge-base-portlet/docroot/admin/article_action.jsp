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

<%
String mvcPath = ParamUtil.getString(request, "mvcPath");

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

KBArticle kbArticle = (KBArticle)row.getObject();
%>

<liferay-ui:icon-menu cssClass="kb-article-action" icon="<%= StringPool.BLANK %>" message="<%= StringPool.BLANK %>">
	<liferay-portlet:renderURL var="viewURL">
		<portlet:param name="mvcPath" value='<%= templatePath + "view_article.jsp" %>' />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
	</liferay-portlet:renderURL>

	<liferay-ui:icon
		iconCssClass="icon-search"
		message="view"
		url="<%= viewURL %>"
	/>

	<c:if test="<%= KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.UPDATE) %>">
		<liferay-portlet:renderURL var="editURL">
			<portlet:param name="mvcPath" value='<%= templatePath + "edit_article.jsp" %>' />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:icon
			iconCssClass="icon-edit"
			message="edit"
			url="<%= editURL %>"
		/>
	</c:if>

	<c:if test="<%= kbArticle.isRoot() && KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= KBArticle.class.getName() %>"
			modelResourceDescription="<%= kbArticle.getTitle() %>"
			resourcePrimKey="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>"
			var="permissionsURL"
			windowState="<%= LiferayWindowState.POP_UP.toString() %>"
		/>

		<liferay-ui:icon
			iconCssClass="icon-lock"
			message="permissions"
			url="<%= permissionsURL %>"
			useDialog="<%= true %>"
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
					iconCssClass="icon-remove-sign"
					message="unsubscribe"
					url="<%= unsubscribeKBArticleURL %>"
				/>
			</c:when>
			<c:otherwise>
				<liferay-portlet:actionURL name="subscribeKBArticle" var="subscribeKBArticleURL">
					<portlet:param name="redirect" value="<%= redirect %>" />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
				</liferay-portlet:actionURL>

				<liferay-ui:icon
					iconCssClass="icon-ok-sign"
					message="subscribe"
					url="<%= subscribeKBArticleURL %>"
				/>
			</c:otherwise>
		</c:choose>
	</c:if>

	<c:if test="<%= KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.MOVE_KB_ARTICLE) %>">
		<liferay-portlet:renderURL var="moveKBArticleURL">
			<portlet:param name="mvcPath" value='<%= templatePath + "move_article.jsp" %>' />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:icon
			iconCssClass="icon-move"
			message="move"
			url="<%= moveKBArticleURL %>"
		/>
	</c:if>

	<c:if test="<%= KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.DELETE) %>">
		<liferay-portlet:actionURL name="deleteKBArticle" var="deleteURL">
			<portlet:param name="mvcPath" value="<%= mvcPath %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
		</liferay-portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>