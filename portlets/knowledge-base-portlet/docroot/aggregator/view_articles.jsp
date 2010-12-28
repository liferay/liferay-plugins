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

<%@ include file="/aggregator/init.jsp" %>

<%
long categoryId = ParamUtil.getLong(request, "categoryId");
String tag = ParamUtil.getString(request, "tag");
%>

<liferay-portlet:renderURL varImpl="iteratorURL" />

<liferay-ui:search-container
	delta="<%= articlesDelta %>"
	iteratorURL="<%= iteratorURL %>"
>
	<liferay-ui:search-container-results>

		<%
		if (selectionMethod.equals("articles")) {
			List<AssetEntry> assetEntries = KnowledgeBaseUtil.getAssetEntries(plid, portletDisplay.getId(), categoryId, tag);

			if (assetEntries != null) {
				long[] classPKs = StringUtil.split(ListUtil.toString(assetEntries, "classPK"), 0L);

				Set<Long> classPKsSet = SetUtil.fromArray(classPKs);
				Set<Long> resourcePrimKeysSet = SetUtil.fromArray(resourcePrimKeys);

				resourcePrimKeysSet.retainAll(classPKsSet);

				resourcePrimKeys = StringUtil.split(StringUtil.merge(resourcePrimKeysSet), 0L);
			}

			results = KnowledgeBaseUtil.getArticles(scopeGroupId, resourcePrimKeys, searchContainer.getStart(), searchContainer.getEnd(), true);
			total = resourcePrimKeys.length;
		}
		else if (selectionMethod.equals("group")) {
			List<AssetEntry> assetEntries = KnowledgeBaseUtil.getAssetEntries(plid, portletDisplay.getId(), categoryId, tag);

			if (assetEntries != null) {
				long[] classPKs = StringUtil.split(ListUtil.toString(assetEntries, "classPK"), 0L);
				long[] viewableParentResourcePrimKeys = ArticleServiceUtil.getViewableParentResourcePrimKeys(scopeGroupId, WorkflowConstants.STATUS_APPROVED);

				results = ArticleServiceUtil.getArticles(scopeGroupId, classPKs, WorkflowConstants.STATUS_APPROVED, viewableParentResourcePrimKeys, searchContainer.getStart(), searchContainer.getEnd(), orderByComparator);
				total = ArticleServiceUtil.getArticlesCount(scopeGroupId, classPKs, WorkflowConstants.STATUS_APPROVED, viewableParentResourcePrimKeys);
			}
			else if (!allArticles) {
				results = ArticleServiceUtil.getSiblingArticles(scopeGroupId, ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY, WorkflowConstants.STATUS_APPROVED, searchContainer.getStart(), searchContainer.getEnd(), orderByComparator);
				total = ArticleServiceUtil.getSiblingArticlesCount(scopeGroupId, ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY, WorkflowConstants.STATUS_APPROVED);
			}
			else {
				long[] viewableParentResourcePrimKeys = ArticleServiceUtil.getViewableParentResourcePrimKeys(scopeGroupId, WorkflowConstants.STATUS_APPROVED);

				results = ArticleServiceUtil.getGroupArticles(scopeGroupId, WorkflowConstants.STATUS_APPROVED, viewableParentResourcePrimKeys, searchContainer.getStart(), searchContainer.getEnd(), orderByComparator);
				total = ArticleServiceUtil.getGroupArticlesCount(scopeGroupId, WorkflowConstants.STATUS_APPROVED, viewableParentResourcePrimKeys);
			}
		}

		pageContext.setAttribute("results", results);
		pageContext.setAttribute("total", total);
		%>

	</liferay-ui:search-container-results>

	<div class="kb-results-body">

		<%
		for (int i = 0; i < results.size(); i++) {
			Article article = (Article)results.get(i);
		%>

			<div class="kb-title-wrapper <%= ((Validator.isNull(articlesTitle) && (i == 0)) ? "kb-title-wrapper-first" : StringPool.BLANK) %>">
				<portlet:renderURL var="viewArticleURL" windowState="<%= articleWindowState %>">
					<portlet:param name="jspPage" value="/aggregator/view_article.jsp" />
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