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
Portlet portlet = (Portlet)request.getAttribute(WebKeys.RENDER_PORTLET);

String keywords = ParamUtil.getString(request, "keywords");
%>

<liferay-util:include page="/admin/top_links.jsp" servletContext="<%= application %>" />

<liferay-ui:panel-container extended="<%= false %>" id='<%= renderResponse.getNamespace() + "SearchArticlesPanelContainer" %>' persistState="<%= true %>">
	<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id='<%= renderResponse.getNamespace() + "SearchArticlesPanel" %>' persistState="<%= true %>" title="search-articles">
		<liferay-portlet:renderURL varImpl="iteratorURL">
			<portlet:param name="jspPage" value="/admin/search.jsp" />
			<portlet:param name="keywords" value="<%= keywords %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container
			iteratorURL="<%= iteratorURL %>"
		>
			<liferay-ui:search-container-results>

				<%
				SearchContext searchContext = SearchContextFactory.getInstance(request);

				searchContext.setEnd(searchContainer.getEnd());
				searchContext.setKeywords(keywords);
				searchContext.setStart(searchContainer.getStart());

				Indexer indexer = portlet.getIndexerInstance();

				Hits hits = indexer.search(searchContext);

				Map<Long, String> resourcePrimKeyToUidMap = new HashMap<Long, String>();

				List<Object[]> objects = new ArrayList<Object[]>();

				for (int i = 0; i < hits.getDocs().length; i++) {
					Document document = hits.doc(i);

					long entryClassPK = GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK));

					String title = document.get(Field.TITLE);
					String snippet = hits.snippet(i);

					if (Validator.isNull(snippet)) {
						if (Validator.isNotNull(document.get(Field.DESCRIPTION))) {
							snippet = document.get(Field.DESCRIPTION);
						}
						else {
							snippet = StringUtil.shorten(document.get(Field.CONTENT), 500);
						}
					}

					resourcePrimKeyToUidMap.put(entryClassPK, document.getUID());

					objects.add(new Object[] {entryClassPK, StringUtil.highlight(title, hits.getQueryTerms()), StringUtil.highlight(snippet, hits.getQueryTerms())});
				}

				long[] indexerResourcePrimKeys = StringUtil.split(StringUtil.merge(resourcePrimKeyToUidMap.keySet()), 0L);

				List<Article> articles = ArticleLocalServiceUtil.getArticles(indexerResourcePrimKeys, WorkflowConstants.STATUS_ANY, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

				for (Article article : articles) {
					resourcePrimKeyToUidMap.remove(article.getResourcePrimKey());
				}

				if (!resourcePrimKeyToUidMap.isEmpty()) {
					SearchEngineUtil.deleteDocuments(company.getCompanyId(), resourcePrimKeyToUidMap.values());
				}

				pageContext.setAttribute("results", objects);
				pageContext.setAttribute("total", hits.getLength());
				%>

			</liferay-ui:search-container-results>

			<div class="kb-results-body">

				<%
				for (Object[] result : (List<Object[]>)results) {
					long entryClassPK = (Long)result[0];

					String title = (String)result[1];
					String snippet = (String)result[2];
				%>

					<div class="kb-title-wrapper">
						<portlet:renderURL var="viewArticleURL">
							<portlet:param name="jspPage" value='<%= jspPath + "view_article.jsp" %>' />
							<portlet:param name="resourcePrimKey" value="<%= String.valueOf(entryClassPK) %>" />
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

					<%= snippet %>

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