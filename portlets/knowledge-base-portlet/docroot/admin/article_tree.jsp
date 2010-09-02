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

int status = GetterUtil.getInteger((Integer)request.getAttribute(WebKeys.KNOWLEDGE_BASE_STATUS));

List<Article> articles = (List<Article>)request.getAttribute("article_tree.jsp-articles");
long firstArticleId = GetterUtil.getLong((String)request.getAttribute("article_tree.jsp-firstArticleId"));
long lastArticleId = GetterUtil.getLong((String)request.getAttribute("article_tree.jsp-lastArticleId"));
Article selArticle = (Article)request.getAttribute("article_tree.jsp-selArticle");
%>

<c:choose>
	<c:when test="<%= articles == null %>">

		<%
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("groupId", scopeGroupId);
		params.put("parentResourcePrimKey", article.getResourcePrimKey());
		params.put("status", status);

		List<Article> childArticles = ArticleServiceUtil.getArticles(params, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new ArticlePriorityComparator(true));
		%>

		<c:if test="<%= !childArticles.isEmpty() %>">

			<%
			Article firstArticle = childArticles.get(0);
			Article lastArticle = childArticles.get(childArticles.size() - 1);

			request.setAttribute("article_tree.jsp-articles", childArticles);
			request.setAttribute("article_tree.jsp-firstArticleId", String.valueOf(firstArticle.getArticleId()));
			request.setAttribute("article_tree.jsp-lastArticleId", String.valueOf(lastArticle.getArticleId()));
			request.setAttribute("article_tree.jsp-selArticle", article);
			%>

			<liferay-util:include page="/admin/article_tree.jsp" servletContext="<%= application %>" />
		</c:if>
	</c:when>
	<c:otherwise>
		<c:if test="<%= articles.get(0).getArticleId() == firstArticleId %>">
			<div class="kb-article-tree">
				<div class="kb-elements">
		</c:if>

		<%
		for(Article curArticle : articles) {
		%>

			<div class='kb-element-header <%= childArticlesDisplayStyle.equals("title") ? "kb-title-only" : StringPool.BLANK %>'>
				<portlet:renderURL var="viewArticleURL">
					<portlet:param name="jspPage" value='<%= jspPath + "view_article.jsp" %>' />
					<portlet:param name="resourcePrimKey" value="<%= String.valueOf(curArticle.getResourcePrimKey()) %>" />
				</portlet:renderURL>

				<liferay-ui:icon
					image="../trees/page"
					label="<%= true %>"
					message="<%= curArticle.getTitle() %>"
					method="get"
					url="<%= viewArticleURL %>"
				/>
			</div>
			<div class="kb-element-body">

				<%
				request.setAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE, curArticle);
				%>

				<liferay-util:include page="/admin/article_icons.jsp" servletContext="<%= application %>" />

				<c:choose>
					<c:when test='<%= childArticlesDisplayStyle.equals("full-content") %>'>
						<%= curArticle.getContent() %>
					</c:when>
					<c:when test='<%= (childArticlesDisplayStyle.equals("abstract") && Validator.isNotNull(curArticle.getDescription())) %>'>
						<%= curArticle.getDescription() %>
					</c:when>
					<c:when test='<%= childArticlesDisplayStyle.equals("abstract") %>'>
						<%= StringUtil.shorten(HtmlUtil.extractText(curArticle.getContent()), 500) %>
					</c:when>
				</c:choose>

				<%
				Map<String, Object> params = new HashMap<String, Object>();

				params.put("groupId", scopeGroupId);
				params.put("parentResourcePrimKey", curArticle.getResourcePrimKey());
				params.put("status", status);

				List<Article> childArticles = ArticleServiceUtil.getArticles(params, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new ArticlePriorityComparator(true));
				%>

				<c:if test="<%= !childArticles.isEmpty() %>">

					<%
					request.setAttribute("article_tree.jsp-articles", childArticles);
					%>

					<liferay-util:include page="/admin/article_tree.jsp" servletContext="<%= application %>" />
				</c:if>
			</div>

		<%
		}
		%>

		<c:if test="<%= articles.get(articles.size() - 1).getArticleId() == lastArticleId %>">

				<%
				request.setAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE, selArticle);
				%>

				</div>
			</div>
		</c:if>
	</c:otherwise>
</c:choose>