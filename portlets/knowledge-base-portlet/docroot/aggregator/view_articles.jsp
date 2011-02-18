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
		Tuple articlesTuple = PortletPreferencesHelper.getArticlesTuple(scopeGroupId, portletDisplay.getId(), categoryId, tag, searchContainer.getStart(), searchContainer.getEnd(), preferences);

		pageContext.setAttribute("results", articlesTuple.getObject(0));
		pageContext.setAttribute("total", articlesTuple.getObject(1));
		%>

	</liferay-ui:search-container-results>

	<div class="kb-results-body">

		<%
		for (int i = 0; i < results.size(); i++) {
			Article article = (Article)results.get(i);
		%>

			<div class="kb-title <%= ((Validator.isNull(articlesTitle) && (i == 0)) ? "kb-title-first" : StringPool.BLANK) %>">
				<portlet:renderURL var="viewArticleURL" windowState="<%= articleWindowState %>">
					<portlet:param name="jspPage" value="/aggregator/view_article.jsp" />
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

	<c:if test="<%= (total == 0) || (total > searchContainer.getDelta()) %>">
		<div class="taglib-search-iterator-page-iterator-bottom">
			<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" />
		</div>
	</c:if>
</liferay-ui:search-container>