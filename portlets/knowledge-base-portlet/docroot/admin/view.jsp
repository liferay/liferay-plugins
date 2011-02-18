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

<liferay-util:include page="/admin/top_links.jsp" servletContext="<%= application %>" />

<div class="float-container kb-results-header">
	<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ARTICLE) || (AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) && GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS)) %>">
		<div class="kb-buttons">
			<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ARTICLE) %>">
				<portlet:renderURL var="addArticleURL">
					<portlet:param name="jspPage" value="/admin/edit_article.jsp" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
				</portlet:renderURL>

				<aui:button onClick="<%= addArticleURL %>" value="add-article" />
			</c:if>

			<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) && GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) %>">
				<liferay-security:permissionsURL
					modelResource="com.liferay.knowledgebase.admin"
					modelResourceDescription="<%= HtmlUtil.escape(themeDisplay.getScopeGroupName()) %>"
					resourcePrimKey="<%= String.valueOf(scopeGroupId) %>"
					var="permissionsURL"
				/>

				<aui:button onClick="<%= permissionsURL %>" value="permissions" />
			</c:if>
		</div>
	</c:if>

	<div class="kb-tools">
		<table class="lfr-table">
		<tr>
			<td>
				<portlet:resourceURL id="groupArticlesRSS" var="groupArticlesRSSURL">
					<portlet:param name="rssDelta" value="<%= String.valueOf(rssDelta) %>" />
					<portlet:param name="rssDisplayStyle" value="<%= rssDisplayStyle %>" />
					<portlet:param name="rssFormat" value="<%= rssFormat %>" />
				</portlet:resourceURL>

				<liferay-ui:icon
					image="rss"
					label="<%= true %>"
					method="get"
					target="_blank"
					url="<%= groupArticlesRSSURL %>"
				/>
			</td>

			<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.SUBSCRIBE) %>">
				<td>
					<c:choose>
						<c:when test="<%= SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), Article.class.getName(), scopeGroupId) %>">
							<portlet:actionURL name="unsubscribeGroupArticles" var="unsubscribeGroupArticlesURL">
								<portlet:param name="redirect" value="<%= currentURL %>" />
							</portlet:actionURL>

							<liferay-ui:icon
								image="unsubscribe"
								label="<%= true %>"
								url="<%= unsubscribeGroupArticlesURL %>"
							/>
						</c:when>
						<c:otherwise>
							<portlet:actionURL name="subscribeGroupArticles" var="subscribeGroupArticlesURL">
								<portlet:param name="redirect" value="<%= currentURL %>" />
							</portlet:actionURL>

							<liferay-ui:icon
								image="subscribe"
								label="<%= true %>"
								url="<%= subscribeGroupArticlesURL %>"
							/>
						</c:otherwise>
					</c:choose>
				</td>
			</c:if>
		</tr>
		</table>
	</div>
</div>

<liferay-ui:panel-container extended="<%= false %>" id='<%= renderResponse.getNamespace() + "ArticlesPanelContainer" %>' persistState="<%= true %>">
	<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id='<%= renderResponse.getNamespace() + "ArticlesPanel" %>' persistState="<%= true %>" title="articles">
		<liferay-portlet:renderURL varImpl="iteratorURL">
			<portlet:param name="jspPage" value="/admin/view.jsp" />
			<portlet:param name="topLink" value="home" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container
			delta="<%= articlesDelta %>"
			iteratorURL="<%= iteratorURL %>"
		>
			<liferay-ui:search-container-results
				results="<%= ArticleServiceUtil.getSiblingArticles(scopeGroupId, ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY, WorkflowConstants.STATUS_ANY, searchContainer.getStart(), searchContainer.getEnd(), new ArticleModifiedDateComparator()) %>"
				total="<%= ArticleServiceUtil.getSiblingArticlesCount(scopeGroupId, ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY, WorkflowConstants.STATUS_ANY) %>"
			/>

			<div class="kb-results-body">

				<%
				for (Article article : (List<Article>)results) {
				%>

					<div class="kb-title">
						<portlet:renderURL var="viewArticleURL">
							<portlet:param name="jspPage" value='<%= jspPath + "view_article.jsp" %>' />
							<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
						</portlet:renderURL>

						<liferay-ui:icon
							image="../trees/page"
							label="<%= true %>"
							message="<%= article.getTitle() %>"
							method="get"
							url="<%= viewArticleURL %>"
						/>
					</div>

					<%
					request.setAttribute("article_icons.jsp-article", article);
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

			<div class="taglib-search-iterator-page-iterator-bottom">
				<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" />
			</div>
		</liferay-ui:search-container>
	</liferay-ui:panel>
</liferay-ui:panel-container>