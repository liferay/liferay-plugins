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

<%
Portlet portlet = (Portlet)request.getAttribute(WebKeys.RENDER_PORTLET);

String keywords = ParamUtil.getString(request, "keywords");
%>

<liferay-util:include page="/admin/top_links.jsp" servletContext="<%= application %>" />

<liferay-ui:panel-container extended="<%= false %>" id='<%= renderResponse.getNamespace() + "SearchArticlesPanelContainer" %>' persistState="<%= true %>">
	<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id='<%= renderResponse.getNamespace() + "SearchArticlesPanel" %>' persistState="<%= true %>" title='<%= LanguageUtil.get(pageContext, "search-articles") %>'>
		<liferay-portlet:renderURL varImpl="iteratorURL">
			<portlet:param name="jspPage" value='<%= jspPageParams.get("search.jsp") %>' />
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

				Hits hits = portlet.getIndexerInstance().search(searchContext);

				List<Object[]> objects = new ArrayList<Object[]>();

				for (int i = 0; i < hits.getDocs().length; i++) {
					Article article = null;

					try {
						article = ArticleServiceUtil.getLatestArticle(GetterUtil.getLong(hits.doc(i).get(Field.ENTRY_CLASS_PK)));
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

					<portlet:renderURL var="viewArticleURL">
						<portlet:param name="jspPage" value='<%= jspPageParams.get("view_article.jsp") %>' />
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

					<%
					request.setAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE, article);
					%>

					<liferay-util:include page="/admin/article_icons.jsp" servletContext="<%= application %>" />

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