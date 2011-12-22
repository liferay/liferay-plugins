<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
int status = (Integer)request.getAttribute(WebKeys.KNOWLEDGE_BASE_STATUS);

KBArticle kbArticle = (KBArticle)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);
%>

<c:if test="<%= !kbArticle.isRoot() %>">
	<div class="kb-article-breadcrumbs">

		<%
		List<KBArticle> selKBArticles = new ArrayList<KBArticle>();

		long selParentResourcePrimKey = kbArticle.getResourcePrimKey();

		while (selParentResourcePrimKey != KBArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY) {
			KBArticle selKBArticle = KBArticleServiceUtil.getLatestKBArticle(selParentResourcePrimKey, status);

			selKBArticles.add(selKBArticle);

			selParentResourcePrimKey = selKBArticle.getParentResourcePrimKey();
		}

		for (int i = selKBArticles.size(); i > 0; i--) {
			KBArticle selKBArticle = selKBArticles.get(i - 1);
		%>

			<liferay-portlet:renderURL var="viewKBArticleURL">
				<portlet:param name="jspPage" value='<%= jspPath + "view_article.jsp" %>' />
				<portlet:param name="resourcePrimKey" value="<%= String.valueOf(selKBArticle.getResourcePrimKey()) %>" />
			</liferay-portlet:renderURL>

			<c:choose>
				<c:when test="<%= kbArticle.equals(selKBArticle) %>">
					<%= selKBArticle.getTitle() %>
				</c:when>
				<c:otherwise>
					<aui:a href="<%= viewKBArticleURL %>"><%= StringUtil.shorten(selKBArticle.getTitle(), 30) %></aui:a> &raquo;
				</c:otherwise>
			</c:choose>

		<%
		}
		%>

	</div>
</c:if>