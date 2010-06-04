<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
%>

<%@ include file="/admin/init.jsp" %>

<liferay-util:include page="/admin/top_links.jsp" servletContext="<%= application %>" />

<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ARTICLE) || (AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) && GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS)) %>">
	<div class="float-container kb-results-header">
		<div class="kb-buttons">
			<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ARTICLE) %>">
				<portlet:renderURL var="addArticleURL">
					<portlet:param name="jspPage" value="/admin/edit_article.jsp" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
				</portlet:renderURL>

				<input type="button" value="<liferay-ui:message key="add-article" />" onClick="location.href = '<%= addArticleURL %>';" />
			</c:if>

			<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) && GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) %>">
				<liferay-security:permissionsURL
					modelResource="com.liferay.knowledgebase.admin"
					modelResourceDescription="<%= HtmlUtil.escape(themeDisplay.getScopeGroupName()) %>"
					resourcePrimKey="<%= String.valueOf(scopeGroupId) %>"
					var="permissionsURL"
				/>

				<input type="button" value="<liferay-ui:message key="permissions" />" onClick="location.href = '<%= permissionsURL %>';" />
			</c:if>
		</div>
	</div>
</c:if>

<liferay-ui:panel-container extended="<%= false %>" id="knowledgeBaseAdminArticlesPanelContainer" persistState="<%= true %>">
	<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="knowledgeBaseAdminArticlesPanel" persistState="<%= true %>" title="Articles">
		<liferay-portlet:renderURL varImpl="iteratorURL">
			<portlet:param name="jspPage" value="/admin/view.jsp" />
			<portlet:param name="topLink" value="home" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container
			delta="<%= articlesDelta %>"
			iteratorURL="<%= iteratorURL %>"
		>
			<liferay-ui:search-container-results>

				<%
				Map<String, Object> params = new HashMap<String, Object>();

				params.put("groupId", scopeGroupId);
				params.put("parentResourcePrimKey", ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY);

				pageContext.setAttribute("results", ArticleServiceUtil.getArticles(params, false, searchContainer.getStart(), searchContainer.getEnd(), new ArticlePriorityComparator(true)));
				pageContext.setAttribute("total", ArticleServiceUtil.getArticlesCount(params, false));
				%>

			</liferay-ui:search-container-results>

			<div class="kb-results-body">

				<%
				for (Article article : (List<Article>)results) {
				%>

					<portlet:renderURL var="viewArticleURL">
						<portlet:param name="jspPage" value="/admin/view_article.jsp" />
						<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
					</portlet:renderURL>

					<liferay-ui:icon
						cssClass="kb-title"
						image="../trees/page"
						label="<%= true %>"
						message="<%= article.getTitle() %>"
						url="<%= viewArticleURL %>"
					/>

					<%
					request.setAttribute("KNOWLEDGE_BASE_ARTICLE", article);
					%>

					<liferay-util:include page="/admin/article_icons.jsp" servletContext="<%= application %>" />

					<c:choose>
						<c:when test='<%= articlesDisplayStyle.equals("full-content") %>'>
							<%= article.getContent() %>
						</c:when>
						<c:when test='<%= (articlesDisplayStyle.equals("abstract") && Validator.isNotNull(article.getDescription())) %>'>
							<%= article.getDescription() %>
						</c:when>
						<c:when test='<%= articlesDisplayStyle.equals("abstract") %>'>
							<%= StringUtil.shorten(HtmlUtil.extractText(article.getContent()), 500) %>
						</c:when>
					</c:choose>

				<%
				}
				%>

			</div>

			<div class="kb-results-footer">
				<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" />
			</div>
		</liferay-ui:search-container>
	</liferay-ui:panel>
</liferay-ui:panel-container>