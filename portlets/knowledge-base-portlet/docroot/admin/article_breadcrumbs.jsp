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
Article article = (Article)request.getAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE);

Article curArticle = (Article)request.getAttribute("article_breadcrumbs.jsp-curArticle");
%>

<c:choose>
	<c:when test="<%= curArticle == null %>">
		<c:if test="<%= article.getParentResourcePrimKey() != ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY %>">

			<%
			request.setAttribute("article_breadcrumbs.jsp-curArticle", article);
			%>

			<liferay-util:include page="/admin/article_breadcrumbs.jsp" servletContext="<%= application %>" />
		</c:if>
	</c:when>
	<c:otherwise>
		<c:if test="<%= curArticle.getParentResourcePrimKey() != ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY %>">

			<%
			request.setAttribute("article_breadcrumbs.jsp-curArticle", ArticleServiceUtil.getLatestArticle(curArticle.getParentResourcePrimKey()));
			%>

			<liferay-util:include page="/admin/article_breadcrumbs.jsp" servletContext="<%= application %>" />
		</c:if>

		<c:if test="<%= curArticle.getParentResourcePrimKey() == ArticleConstants.DEFAULT_PARENT_RESOURCE_PRIM_KEY %>">
			<div class="kb-article-breadcrumbs">
		</c:if>

		<portlet:renderURL var="viewArticleURL">
			<portlet:param name="jspPage" value='<%= jspPageParams.get("view_article.jsp") %>' />
			<portlet:param name="resourcePrimKey" value="<%= String.valueOf(curArticle.getResourcePrimKey()) %>" />
		</portlet:renderURL>

		<c:choose>
			<c:when test="<%= article.equals(curArticle) %>">
				<%= curArticle.getTitle() %>
			</c:when>
			<c:otherwise>
				<aui:a href="<%= viewArticleURL %>"><%= StringUtil.shorten(curArticle.getTitle(), 30) %></aui:a> &raquo;
			</c:otherwise>
		</c:choose>

		<c:if test="<%= article.equals(curArticle) %>">
			</div>
		</c:if>
	</c:otherwise>
</c:choose>