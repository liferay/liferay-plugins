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
int status = (Integer)request.getAttribute(WebKeys.KNOWLEDGE_BASE_STATUS);

KBArticle kbArticle = (KBArticle)request.getAttribute("article_icons.jsp-kb_article");

long resourcePrimKey = ParamUtil.getLong(request, "resourcePrimKey");
%>

<c:if test="<%= (AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_ARTICLE) && rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_ADMIN)) || (DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_ARTICLE) && DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADMINISTRATOR) && rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY)) || ((!rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY) || DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADMINISTRATOR)) && KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.UPDATE)) || (kbArticle.isRoot() && KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.PERMISSIONS)) || KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.MOVE_KB_ARTICLE) || KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.DELETE) %>">
	<div class="kb-article-icons">
		<table class="lfr-table">
		<tr>
			<c:if test="<%= (AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_ARTICLE) && rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_ADMIN)) || (DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_KB_ARTICLE) && DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADMINISTRATOR) && rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY)) %>">
				<td>
					<liferay-portlet:renderURL var="addKBArticleURL">
						<portlet:param name="mvcPath" value='<%= templatePath + "edit_article.jsp" %>' />
						<portlet:param name="redirect" value="<%= redirect %>" />
						<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
					</liferay-portlet:renderURL>

					<liferay-ui:icon
						iconCssClass="icon-plus"
						label="<%= true %>"
						message="add-child-article"
						url="<%= addKBArticleURL %>"
					/>
				</td>
			</c:if>

			<c:if test="<%= (!rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_DISPLAY) || DisplayPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADMINISTRATOR)) && KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.UPDATE) %>">
				<td>
					<liferay-portlet:renderURL var="editURL">
						<portlet:param name="mvcPath" value='<%= templatePath + "edit_article.jsp" %>' />
						<portlet:param name="redirect" value="<%= redirect %>" />
						<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
						<portlet:param name="status" value="<%= String.valueOf(WorkflowConstants.STATUS_ANY) %>" />
					</liferay-portlet:renderURL>

					<liferay-ui:icon
						iconCssClass="icon-edit"
						label="<%= true %>"
						message="edit"
						url="<%= editURL %>"
					/>
				</td>
			</c:if>

			<c:if test="<%= kbArticle.isRoot() && KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.PERMISSIONS) %>">
				<td>
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
						url="<%= permissionsURL %>"
						useDialog="<%= true %>"
					/>
				</td>
			</c:if>

			<c:if test="<%= KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.MOVE_KB_ARTICLE) %>">
				<td>
					<liferay-portlet:renderURL var="moveKBArticleURL">
						<portlet:param name="mvcPath" value='<%= templatePath + "move_article.jsp" %>' />
						<portlet:param name="redirect" value="<%= redirect %>" />
						<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
						<portlet:param name="status" value="<%= String.valueOf(status) %>" />
					</liferay-portlet:renderURL>

					<liferay-ui:icon
						iconCssClass="icon-move"
						label="<%= true %>"
						message="move"
						url="<%= moveKBArticleURL %>"
					/>
				</td>
			</c:if>

			<c:if test="<%= KBArticlePermission.contains(permissionChecker, kbArticle, ActionKeys.DELETE) %>">
				<td>
					<liferay-portlet:renderURL var="homeURL">
						<portlet:param name="mvcPath" value='<%= templatePath + "view.jsp" %>' />
					</liferay-portlet:renderURL>

					<liferay-portlet:actionURL name="deleteKBArticle" var="deleteURL">
						<portlet:param name="mvcPath" value='<%= templatePath + "view_article.jsp" %>' />
						<portlet:param name="redirect" value="<%= (kbArticle.getResourcePrimKey() == resourcePrimKey) ? homeURL : currentURL %>" />
						<portlet:param name="resourcePrimKey" value="<%= String.valueOf(kbArticle.getResourcePrimKey()) %>" />
						<portlet:param name="status" value="<%= String.valueOf(status) %>" />
					</liferay-portlet:actionURL>

					<liferay-ui:icon-delete
						label="<%= true %>"
						url="<%= deleteURL %>"
					/>
				</td>
			</c:if>

			<td>
				<aui:model-context bean="<%= kbArticle %>" model="<%= KBArticle.class %>" />

				<aui:workflow-status showIcon="<%= false %>" showLabel="<%= false %>" status="<%= kbArticle.getStatus() %>" />
			</td>
		</tr>
		</table>
	</div>
</c:if>