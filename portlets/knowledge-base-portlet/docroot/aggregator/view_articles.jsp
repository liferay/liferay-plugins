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

<%@ include file="/aggregator/init.jsp" %>

<liferay-portlet:renderURL varImpl="iteratorURL" />

<liferay-ui:search-container
	delta="<%= articlesDelta %>"
	iteratorURL="<%= iteratorURL %>"
>
	<liferay-ui:search-container-results>

		<%
		if (selectionMethod.equals("articles")) {
			results = KnowledgeBaseUtil.getArticles(resourcePrimKeys, searchContainer.getStart(), searchContainer.getEnd(), true);
			total = resourcePrimKeys.length;
		}
		else if (selectionMethod.equals("parent-group")) {
			Map<String, Object> params = new HashMap<String, Object>();

			params.put("parentGroupId", themeDisplay.getParentGroupId());

			if (!allArticles) {
				params.put("parentResourcePrimKey", ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY);
			}

			results = ArticleServiceUtil.getArticles(params, false, searchContainer.getStart(), searchContainer.getEnd(), orderByComparator);
			total = ArticleServiceUtil.getArticlesCount(params, false);
		}
		else if (selectionMethod.equals("scope-groups")) {
			Map<String, Object> params = new HashMap<String, Object>();

			params.put("groupId", ArrayUtil.toArray(scopeGroupIds));

			if (!allArticles) {
				params.put("parentResourcePrimKey", ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY);
			}

			results = ArticleServiceUtil.getArticles(params, false, searchContainer.getStart(), searchContainer.getEnd(), orderByComparator);
			total = ArticleServiceUtil.getArticlesCount(params, false);
		}

		pageContext.setAttribute("results", results);
		pageContext.setAttribute("total", total);
		%>

	</liferay-ui:search-container-results>

	<c:if test="<%= total == 0 %>">

		<%
		renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.TRUE);
		%>

	</c:if>

	<div class="kb-results-body">

		<%
		for (int i = 0; i < results.size(); i++) {
			Article article = (Article)results.get(i);
		%>

			<portlet:renderURL var="viewArticleURL" windowState="<%= articleWindowState %>">
				<portlet:param name="jspPage" value="/aggregator/view_article.jsp" />
				<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
			</portlet:renderURL>

			<liferay-ui:icon
				cssClass='<%= "kb-title" + ((Validator.isNull(articlesTitle) && (i == 0)) ? " kb-title-first" : StringPool.BLANK) %>'
				image="../trees/page"
				label="<%= true %>"
				message="<%= article.getTitle() %>"
				method="get"
				url="<%= viewArticleURL %>"
			/>

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