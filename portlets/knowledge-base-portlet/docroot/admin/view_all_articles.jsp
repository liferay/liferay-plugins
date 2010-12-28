<%--
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
--%>

<%@ include file="/admin/init.jsp" %>

<liferay-util:include page="/admin/top_links.jsp" servletContext="<%= application %>" />

<liferay-ui:panel-container extended="<%= false %>" id='<%= renderResponse.getNamespace() + "AllArticlesPanelContainer" %>' persistState="<%= true %>">
	<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id='<%= renderResponse.getNamespace() + "AllArticlesPanel" %>' persistState="<%= true %>" title='<%= LanguageUtil.get(pageContext, "all-articles") %>'>
		<liferay-portlet:renderURL varImpl="iteratorURL">
			<portlet:param name="jspPage" value="/admin/view_all_articles.jsp" />
			<portlet:param name="topLink" value="all-articles" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container
			iteratorURL="<%= iteratorURL %>"
		>
			<liferay-ui:search-container-results>

				<%
				long[] viewableParentResourcePrimKeys = ArticleServiceUtil.getViewableParentResourcePrimKeys(scopeGroupId, WorkflowConstants.STATUS_ANY);

				pageContext.setAttribute("results", ArticleServiceUtil.getGroupArticles(scopeGroupId, WorkflowConstants.STATUS_ANY, viewableParentResourcePrimKeys, searchContainer.getStart(), searchContainer.getEnd(), new ArticleModifiedDateComparator()));
				pageContext.setAttribute("total", ArticleServiceUtil.getGroupArticlesCount(scopeGroupId, WorkflowConstants.STATUS_ANY, viewableParentResourcePrimKeys));
				%>

			</liferay-ui:search-container-results>

			<div class="kb-results-body">

				<%
				for (Article article : (List<Article>)results) {
				%>

					<div class="kb-title-wrapper">
						<portlet:renderURL var="viewArticleURL">
							<portlet:param name="jspPage" value='<%= jspPath + "view_article.jsp" %>' />
							<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
						</portlet:renderURL>

						<liferay-ui:icon
							cssClass="kb-title"
							image="../trees/page"
							label="<%= true %>"
							message="<%= article.getTitle() %>"
							method="get"
							url="<%= viewArticleURL %>"
						/>
					</div>

					<%
					request.setAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE, article);
					%>

					<liferay-util:include page="/admin/article_icons.jsp" servletContext="<%= application %>" />

					<c:choose>
						<c:when test="<%= Validator.isNotNull(article.getDescription()) %>">
							<%= article.getDescription() %>
						</c:when>
						<c:otherwise>
							<%= StringUtil.shorten(HtmlUtil.extractText(article.getContent()), 500) %>
						</c:otherwise>
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