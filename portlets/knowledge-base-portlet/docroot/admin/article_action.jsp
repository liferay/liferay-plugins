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

KBArticleURLHelper kbArticleURLHelper = new KBArticleURLHelper(renderRequest, renderResponse, templatePath);
%>

<liferay-ui:icon-menu cssClass="kb-article-action">

	<%
	PortletURL viewURL = kbArticleURLHelper.createViewWithRedirectURL(kbArticle, currentURL);
	%>

	<liferay-ui:icon
		image="view"
		method="get"
		url="<%= viewURL.toString() %>"
	/>

	<c:if test="<%= KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.UPDATE) %>">
		<liferay-portlet:renderURL var="editURL">
			<portlet:param name="mvcPath" value='<%= templatePath + "edit_article.jsp" %>' />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			method="get"
			url="<%= editURL %>"
		/>
	</c:if>

	<c:if test="<%= (AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_ARTICLE) && rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_ADMIN)) || (DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_ARTICLE) && DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADMINISTRATOR) && rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY)) %>">
		<liferay-portlet:renderURL var="addKBArticleURL">
			<portlet:param name="mvcPath" value='<%= templatePath + "edit_article.jsp" %>' />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="parentResourceClassNameId" value="<%= String.valueOf(kbArticle.getClassNameId()) %>" />
			<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:icon
			image="add_article"
			label="<%= true %>"
			message="add-child-article"
			method="get"
			url="<%= addKBArticleURL %>"
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
			image="permissions"
			method="get"
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
			<portlet:param name="mvcPath" value='<%= templatePath + "move_object.jsp" %>' />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="resourceClassNameId" value="<%= String.valueOf(kbArticle.getClassNameId()) %>" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
			<portlet:param name="parentResourceClassNameId" value="<%= String.valueOf(kbArticle.getParentResourceClassNameId()) %>" />
			<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(kbArticle.getParentResourcePrimKey()) %>" />
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
			<portlet:param name="mvcPath" value="<%= mvcPath %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
		</liferay-portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>