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
KBArticle kbArticle = (KBArticle)request.getAttribute("article_icons.jsp-kb_article");

int status = (Integer)request.getAttribute(WebKeys.KNOWLEDGE_BASE_STATUS);

long resourcePrimKey = ParamUtil.getLong(request, "resourcePrimKey");
%>

<c:if test="<%= (AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_ARTICLE) && rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_ADMIN)) || (DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_ARTICLE) && DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADMINISTRATOR) && rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY)) || ((!rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY) || DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADMINISTRATOR)) && KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.UPDATE)) || (kbArticle.isRoot() && KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.PERMISSIONS)) || KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.MOVE_KB_ARTICLE) || KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.DELETE) %>">
	<div class="kb-article-icons text-right">
		<liferay-ui:icon-menu cssClass="right" direction="down" extended="<%= false %>" triggerCssClass="btn">
			<c:if test="<%= (!rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY) || DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADMINISTRATOR)) && KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.UPDATE) %>">
				<liferay-portlet:renderURL var="editURL">
					<portlet:param name="mvcPath" value='<%= templatePath + "edit_article.jsp" %>' />
					<portlet:param name="redirect" value="<%= redirect %>" />
					<portlet:param name="resourceClassNameId" value="<%= String.valueOf(kbArticle.getClassNameId()) %>" />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
					<portlet:param name="status" value="<%= String.valueOf(WorkflowConstants.STATUS_ANY) %>" />
				</liferay-portlet:renderURL>

				<liferay-ui:icon
					iconCssClass="icon-edit"
					label="<%= true %>"
					message="edit"
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
					iconCssClass="icon-plus"
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
					iconCssClass="icon-lock"
					label="<%= true %>"
					message="permissions"
					method="get"
					url="<%= permissionsURL %>"
					useDialog="<%= true %>"
				/>
			</c:if>

			<c:if test="<%= KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.MOVE_KB_ARTICLE) %>">
				<liferay-portlet:renderURL var="moveKBArticleURL">
					<portlet:param name="mvcPath" value='<%= templatePath + "move_object.jsp" %>' />
					<portlet:param name="redirect" value="<%= redirect %>" />
					<portlet:param name="resourceClassNameId" value="<%= String.valueOf(kbArticle.getClassNameId()) %>" />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
					<portlet:param name="status" value="<%= String.valueOf(status) %>" />
				</liferay-portlet:renderURL>

				<liferay-ui:icon
					iconCssClass="icon-move"
					label="<%= true %>"
					message="move"
					method="get"
					url="<%= moveKBArticleURL %>"
				/>
			</c:if>

			<c:if test="<%= KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.DELETE) %>">
				<liferay-portlet:renderURL var="homeURL">
					<portlet:param name="mvcPath" value='<%= templatePath + "view.jsp" %>' />
				</liferay-portlet:renderURL>

				<liferay-portlet:actionURL name="deleteKBArticle" var="deleteURL">
					<portlet:param name="mvcPath" value='<%= templatePath + "view_article.jsp" %>' />
					<portlet:param name="redirect" value="<%= (kbArticle.getResourcePrimKey() == resourcePrimKey) ? homeURL : currentURL %>" />
					<portlet:param name="resourceClassNameId" value="<%= String.valueOf(kbArticle.getClassNameId()) %>" />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
					<portlet:param name="status" value="<%= String.valueOf(status) %>" />
				</liferay-portlet:actionURL>

				<liferay-ui:icon-delete
					label="<%= true %>"
					url="<%= deleteURL %>"
				/>
			</c:if>
		</liferay-ui:icon-menu>
	</div>
</c:if>