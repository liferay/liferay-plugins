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

<%@ include file="/search/init.jsp" %>

<%
Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), PortletKeys.KNOWLEDGE_BASE_ADMIN);

String keywords = ParamUtil.getString(request, "keywords");
%>

<div class="kb-search-header">
	<liferay-util:include page="/search/view.jsp" servletContext="<%= application %>" />
</div>

<liferay-ui:panel-container extended="<%= false %>" id='<%= renderResponse.getNamespace() + "SearchArticlesPanelContainer" %>' persistState="<%= true %>">
	<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id='<%= renderResponse.getNamespace() + "SearchArticlesPanel" %>' persistState="<%= true %>" title='<%= LanguageUtil.get(pageContext, "search-articles") %>'>
		<liferay-portlet:renderURL varImpl="iteratorURL">
			<portlet:param name="jspPage" value="/search/search.jsp" />
			<portlet:param name="keywords" value="<%= keywords %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container
			delta="<%= articlesDelta %>"
			iteratorURL="<%= iteratorURL %>"
		>
			<liferay-ui:search-container-results>

				<%
				SearchContext searchContext = SearchContextFactory.getInstance(request);

				searchContext.setEnd(searchContainer.getEnd());
				searchContext.setKeywords(keywords);
				searchContext.setScopeStrict(false);
				searchContext.setStart(searchContainer.getStart());

				searchContext.setAttribute("assetEntryQueryContains", assetEntryQueryContains);
				searchContext.setAttribute("assetEntryQueryAndOperator", assetEntryQueryAndOperator);
				searchContext.setAttribute("assetEntryQueryName", assetEntryQueryName);
				searchContext.setAttribute("assetCategoryIds", assetCategoryIds);
				searchContext.setAttribute("assetTagNames", assetTagNames);

				if (selectionMethod.equals("articles")) {
					searchContext.setAttribute("KNOWLEDGE_BASE_RESOURCE_PRIM_KEYS", resourcePrimKeys);
				}

				Indexer indexer = portlet.getIndexerInstance();

				Hits hits = indexer.search(searchContext);

				List<Object[]> objects = new ArrayList<Object[]>();

				for (int i = 0; i < hits.getDocs().length; i++) {
					Article article = null;

					try {
						article = ArticleServiceUtil.getLatestArticle(GetterUtil.getLong(hits.doc(i).get(Field.ENTRY_CLASS_PK)), WorkflowConstants.STATUS_APPROVED);
					}
					catch (NoSuchArticleException nsae) {
						continue;
					}
					catch (PrincipalException pe) {
						continue;
					}

					String title = StringUtil.highlight(article.getTitle(), hits.getQueryTerms());
					String content = StringUtil.highlight(hits.snippet(i), hits.getQueryTerms());

					objects.add(new Object[] {article, title, content});
				}

				pageContext.setAttribute("results", objects);
				pageContext.setAttribute("total", hits.getLength());
				%>

			</liferay-ui:search-container-results>

			<div class="kb-results-body">

				<%
				for (Object[] result : (List<Object[]>)results) {
					Article article = (Article)result[0];

					String title = (String)result[1];
					String content = (String)result[2];
				%>

					<div class="kb-title-wrapper">
						<portlet:renderURL var="viewArticleURL">
							<portlet:param name="jspPage" value="/search/view_article.jsp" />
							<portlet:param name="resourcePrimKey" value="<%= String.valueOf(article.getResourcePrimKey()) %>" />
						</portlet:renderURL>

						<liferay-ui:icon
							cssClass="kb-title"
							image="../trees/page"
							label="<%= true %>"
							message="<%= title %>"
							method="get"
							url="<%= viewArticleURL %>"
						/>
					</div>

					<c:choose>
						<c:when test="<%= Validator.isNotNull(content) %>">
							<%= content %>
						</c:when>
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