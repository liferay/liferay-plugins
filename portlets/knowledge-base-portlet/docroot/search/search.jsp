<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

long assetCategoryId = ParamUtil.getLong(request, "categoryId");
String assetTagName = ParamUtil.getString(request, "tag");

if (assetCategoryId > 0) {
	AssetCategory assetCategory = AssetCategoryLocalServiceUtil.getAssetCategory(assetCategoryId);

	keywords = assetCategory.getName();
}

if (Validator.isNotNull(assetTagName)) {
	keywords = assetTagName;
}

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
	<liferay-ui:search-container-results>

		<%
		SearchContext searchContext = SearchContextFactory.getInstance(request);

		searchContext.setEnd(searchContainer.getEnd());
		searchContext.setKeywords(keywords);
		searchContext.setStart(searchContainer.getStart());
		searchContext.setSorts(KnowledgeBaseUtil.getKBArticleSorts(orderByCol, orderByType));

		Indexer indexer = IndexerRegistryUtil.getIndexer(KBArticle.class);

		Hits hits = indexer.search(searchContext);

		List<Tuple> tuples = new ArrayList<Tuple>();

		for (int i = 0; i < hits.getDocs().length; i++) {
			Object[] array = new Object[5];

			array[0] = hits.doc(i).get(Field.ENTRY_CLASS_PK);
			array[1] = hits.doc(i).get(Field.TITLE);
			array[2] = hits.doc(i).get(Field.USER_NAME);
			array[3] = hits.doc(i).getDate(Field.CREATE_DATE);
			array[4] = hits.doc(i).getDate(Field.MODIFIED_DATE);

			tuples.add(new Tuple(array));
		}

		pageContext.setAttribute("results", tuples);
		pageContext.setAttribute("total", hits.getLength());
		%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.liferay.portal.kernel.util.Tuple"
		modelVar="tuple"
	>
		<liferay-portlet:renderURL varImpl="rowURL">
			<portlet:param name="mvcPath" value="/search/view_article.jsp" />
			<portlet:param name="resourcePrimKey" value="<%= (String)tuple.getObject(0) %>" />
		</liferay-portlet:renderURL>

		<liferay-ui:search-container-column-text
			href="<%= rowURL %>"
			name="title"
			orderable="<%= true %>"
			value="<%= (String)tuple.getObject(1) %>"
		/>

		<c:if test="<%= showKBArticleAuthorColumn %>">
			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="author"
				orderable="<%= true %>"
				orderableProperty="user-name"
				value="<%= (String)tuple.getObject(2) %>"
			/>
		</c:if>

		<c:if test="<%= showKBArticleCreateDateColumn %>">
			<liferay-ui:search-container-column-text
				cssClass="kb-column-no-wrap"
				href="<%= rowURL %>"
				name="create-date"
				orderable="<%= true %>"
				value='<%= dateFormatDate.format(tuple.getObject(3)) + "<br />" + dateFormatTime.format(tuple.getObject(3)) %>'
			/>
		</c:if>

		<c:if test="<%= showKBArticleModifiedDateColumn %>">
			<liferay-ui:search-container-column-text
				cssClass="kb-column-no-wrap"
				href="<%= rowURL %>"
				name="modified-date"
				orderable="<%= true %>"
				value='<%= dateFormatDate.format(tuple.getObject(4)) + "<br />" + dateFormatTime.format(tuple.getObject(4)) %>'
			/>
		</c:if>

		<c:if test="<%= showKBArticleViewsColumn %>">
			<liferay-ui:search-container-column-text
				buffer="buffer"
				cssClass="kb-column-no-wrap"
				href="<%= rowURL %>"
			>

				<%
				KBArticle kbArticle = null;

				try {
					kbArticle = KBArticleLocalServiceUtil.getLatestKBArticle(GetterUtil.getLong((String)tuple.getObject(0)), WorkflowConstants.STATUS_APPROVED);
				}
				catch (NoSuchArticleException nsae) {
				}

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