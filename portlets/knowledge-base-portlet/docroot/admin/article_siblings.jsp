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
Article article = (Article)request.getAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE);

List<Article> siblingArticles = ArticleServiceUtil.getSiblingArticles(scopeGroupId, article.getResourcePrimKey(), article.getStatus(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, new ArticlePriorityComparator(true));
%>

<c:if test="<%= !siblingArticles.isEmpty() %>">
	<div class="kb-article-siblings">
		<div class="kb-elements">

			<%
			for (Article siblingArticle : siblingArticles) {
			%>

				<div class='kb-element-header <%= childArticlesDisplayStyle.equals("title") ? "kb-title-only" : StringPool.BLANK %>'>
					<portlet:renderURL var="viewArticleURL">
						<portlet:param name="jspPage" value='<%= jspPath + "view_article.jsp" %>' />
						<portlet:param name="resourcePrimKey" value="<%= String.valueOf(siblingArticle.getResourcePrimKey()) %>" />
					</portlet:renderURL>

					<liferay-ui:icon
						image="../trees/page"
						label="<%= true %>"
						message="<%= siblingArticle.getTitle() %>"
						method="get"
						url="<%= viewArticleURL %>"
					/>
				</div>
				<div class="kb-element-body">

					<%
					request.setAttribute("article_icons.jsp-article", siblingArticle);
					%>

					<liferay-util:include page="/admin/article_icons.jsp" servletContext="<%= application %>" />

					<c:choose>
						<c:when test='<%= childArticlesDisplayStyle.equals("full-content") %>'>
							<%= siblingArticle.getContent() %>
						</c:when>
						<c:when test='<%= childArticlesDisplayStyle.equals("abstract") && Validator.isNotNull(siblingArticle.getDescription()) %>'>
							<%= siblingArticle.getDescription() %>
						</c:when>
						<c:when test='<%= childArticlesDisplayStyle.equals("abstract") %>'>
							<%= StringUtil.shorten(HtmlUtil.extractText(siblingArticle.getContent()), 500) %>
						</c:when>
					</c:choose>
				</div>

			<%
			}
			%>

		</div>
	</div>
</c:if>