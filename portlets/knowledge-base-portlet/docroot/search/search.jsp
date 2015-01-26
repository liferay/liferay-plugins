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

<%@ include file="/search/init.jsp" %>

<%
String keywords = ParamUtil.getString(request, "keywords");

String orderByCol = ParamUtil.getString(request, "orderByCol", "score");
String orderByType = ParamUtil.getString(request, "orderByType", "desc");
%>

<div class="kb-search-header">
	<liferay-util:include page="/search/view.jsp" servletContext="<%= application %>" />
</div>

<liferay-portlet:renderURL varImpl="iteratorURL">
	<portlet:param name="mvcPath" value="/search/search.jsp" />
	<portlet:param name="keywords" value="<%= keywords %>" />
</liferay-portlet:renderURL>

<liferay-ui:search-container
	emptyResultsMessage='<%= LanguageUtil.format(pageContext, "no-articles-were-found-that-matched-the-keywords-x", "<strong>" + HtmlUtil.escape(keywords) + "</strong>", false) %>'
	iteratorURL="<%= iteratorURL %>"
	orderByCol="<%= orderByCol %>"
	orderByType="<%= orderByType %>"
>

	<%
	SearchContext searchContext = SearchContextFactory.getInstance(request);

	searchContext.setAttribute("paginationType", "regular");
	searchContext.setEnd(searchContainer.getEnd());
	searchContext.setKeywords(keywords);
	searchContext.setStart(searchContainer.getStart());
	searchContext.setSorts(KnowledgeBaseUtil.getKBArticleSorts(orderByCol, orderByType));

	Indexer indexer = IndexerRegistryUtil.getIndexer(KBArticle.class);

	Hits hits = indexer.search(searchContext);

	List<Tuple> tuples = new ArrayList<Tuple>();

	for (int i = 0; i < hits.getDocs().length; i++) {
		Object[] array = new Object[5];

		Document document = hits.doc(i);

		array[0] = document.get(Field.ENTRY_CLASS_PK);
		array[1] = document.get(Field.TITLE);

		long userId = GetterUtil.getLong(document.get(Field.USER_ID));
		String userName = document.get(Field.USER_NAME);

		array[2] = PortalUtil.getUserName(userId, userName);

		array[3] = document.getDate(Field.CREATE_DATE);
		array[4] = document.getDate(Field.MODIFIED_DATE);

		tuples.add(new Tuple(array));
	}

	searchContainer.setResults(tuples);
	searchContainer.setTotal(hits.getLength());
	%>

	<liferay-ui:search-container-row
		className="com.liferay.portal.kernel.util.Tuple"
		modelVar="tuple"
	>
		<liferay-portlet:renderURL varImpl="rowURL">
			<portlet:param name="mvcPath" value="/search/view_article.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="resourcePrimKey" value="<%= (String)tuple.getObject(0) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="title"
			orderable="<%= true %>"
			value="<%= HtmlUtil.escape((String)tuple.getObject(1)) %>"
		/>

		<c:if test="<%= showKBArticleAuthorColumn %>">
			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="author"
				orderable="<%= true %>"
				orderableProperty="user-name"
				value="<%= HtmlUtil.escape((String)tuple.getObject(2)) %>"
			/>
		</c:if>

		<c:if test="<%= showKBArticleCreateDateColumn %>">
			<liferay-ui:search-container-column-date
				cssClass="kb-column-no-wrap"
				href="<%= rowURL %>"
				name="create-date"
				orderable="<%= true %>"
				value="<%= (Date)tuple.getObject(3) %>"
			/>
		</c:if>

		<c:if test="<%= showKBArticleModifiedDateColumn %>">
			<liferay-ui:search-container-column-date
				cssClass="kb-column-no-wrap"
				href="<%= rowURL %>"
				name="modified-date"
				orderable="<%= true %>"
				value="<%= (Date)tuple.getObject(4) %>"
			/>
		</c:if>

		<c:if test="<%= showKBArticleViewsColumn %>">
			<liferay-ui:search-container-column-text
				buffer="buffer"
				cssClass="kb-column-no-wrap"
				href="<%= rowURL %>"
			>

				<%
				KBArticle kbArticle = KBArticleLocalServiceUtil.fetchLatestKBArticle(GetterUtil.getLong((String)tuple.getObject(0)), WorkflowConstants.STATUS_APPROVED);

				int viewCount = (kbArticle != null) ? kbArticle.getViewCount() : 0;

				buffer.append(viewCount);
				buffer.append(StringPool.SPACE);
				buffer.append((viewCount == 1) ? LanguageUtil.get(pageContext, "view") : LanguageUtil.get(pageContext, "views"));
				%>

			</liferay-ui:search-container-column-text>
		</c:if>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>