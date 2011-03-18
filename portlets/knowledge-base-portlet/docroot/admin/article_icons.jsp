<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
Integer status = (Integer)request.getAttribute(WebKeys.KNOWLEDGE_BASE_STATUS);

Article article = (Article)request.getAttribute("article_icons.jsp-article");

long resourcePrimKey = ParamUtil.getLong(request, "resourcePrimKey");
%>

<c:if test="<%= (AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ARTICLE) && Validator.equals(portletDisplay.getRootPortletId(), PortletKeys.KNOWLEDGE_BASE_ADMIN)) || (DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ARTICLE) && DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADMINISTRATOR) && Validator.equals(portletDisplay.getRootPortletId(), PortletKeys.KNOWLEDGE_BASE_DISPLAY)) || ArticlePermission.contains(permissionChecker, article, ActionKeys.UPDATE) || (article.isRoot() && ArticlePermission.contains(permissionChecker, article, ActionKeys.PERMISSIONS)) || ArticlePermission.contains(permissionChecker, article, ActionKeys.MOVE) || ArticlePermission.contains(permissionChecker, article, ActionKeys.DELETE) %>">
	<div class="kb-article-icons">
		<table class="lfr-table">
		<tr>
			<c:if test="<%= (AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ARTICLE) && Validator.equals(portletDisplay.getRootPortletId(), PortletKeys.KNOWLEDGE_BASE_ADMIN)) || (DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ARTICLE) && DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADMINISTRATOR) && Validator.equals(portletDisplay.getRootPortletId(), PortletKeys.KNOWLEDGE_BASE_DISPLAY)) %>">
				<td>
					<portlet:renderURL var="addArticleURL">
						<portlet:param name="jspPage" value='<%= portletConfig.getInitParameter("jsp-path") + "edit_article.jsp" %>' />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
					</portlet:renderURL>

					<liferay-ui:icon
						image="add_article"
						label="<%= true %>"
						message="add-child-article"
						method="get"
						url="<%= addArticleURL %>"
					/>
				</td>
			</c:if>

			<c:if test="<%= ArticlePermission.contains(permissionChecker, article, ActionKeys.UPDATE) %>">
				<td>
					<portlet:renderURL var="editURL">
						<portlet:param name="jspPage" value='<%= portletConfig.getInitParameter("jsp-path") + "edit_article.jsp" %>' />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
						<portlet:param name="status" value="<%= String.valueOf(WorkflowConstants.STATUS_ANY) %>" />
					</portlet:renderURL>

					<liferay-ui:icon
						image="edit"
						label="<%= true %>"
						method="get"
						url="<%= editURL %>"
					/>
				</td>
			</c:if>

			<c:if test="<%= article.isRoot() && ArticlePermission.contains(permissionChecker, article, ActionKeys.PERMISSIONS) %>">
				<td>
					<liferay-security:permissionsURL
						modelResource="<%= Article.class.getName() %>"
						modelResourceDescription="<%= article.getTitle() %>"
						resourcePrimKey="<%= String.valueOf(article.getResourcePrimKey()) %>"
						var="permissionsURL"
					/>

					<liferay-ui:icon
						image="permissions"
						label="<%= true %>"
						method="get"
						url="<%= permissionsURL %>"
					/>
				</td>
			</c:if>

			<c:if test="<%= ArticlePermission.contains(permissionChecker, article, ActionKeys.MOVE) %>">
				<td>
					<portlet:renderURL var="moveURL">
						<portlet:param name="jspPage" value='<%= portletConfig.getInitParameter("jsp-path") + "move_article.jsp" %>' />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
						<portlet:param name="status" value="<%= String.valueOf(status.intValue()) %>" />
					</portlet:renderURL>

					<liferay-ui:icon
						image="forward"
						label="<%= true %>"
						message="move"
						method="get"
						url="<%= moveURL %>"
					/>
				</td>
			</c:if>

			<c:if test="<%= ArticlePermission.contains(permissionChecker, article, ActionKeys.DELETE) %>">
				<td>
					<portlet:renderURL var="homeURL">
						<portlet:param name="jspPage" value='<%= portletConfig.getInitParameter("jsp-path") + "view-jsp" %>' />
					</portlet:renderURL>

					<portlet:actionURL name="deleteArticle" var="deleteURL">
						<portlet:param name="jspPage" value='<%= portletConfig.getInitParameter("jsp-path") + "view_article.jsp" %>' />
						<portlet:param name="redirect" value="<%= (article.getResourcePrimKey() == resourcePrimKey) ? homeURL : currentURL %>" />
						<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
						<portlet:param name="status" value="<%= String.valueOf(status.intValue()) %>" />
					</portlet:actionURL>

					<liferay-ui:icon-delete
						label="<%= true %>"
						url="<%= deleteURL %>"
					/>
				</td>
			</c:if>

			<td>
				<aui:model-context bean="<%= article %>" model="<%= Article.class %>" />

				<aui:workflow-status status="<%= article.getStatus() %>" version="<%= String.valueOf(article.getVersion()) %>" />
			</td>
		</tr>
		</table>
	</div>
</c:if>