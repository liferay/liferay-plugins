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

	<%
	List<Object[]> results = new ArrayList<Object[]>();

	for (Article siblingArticle : siblingArticles) {
		results.add(new Object[] {siblingArticle, "</div>"});
	}
	%>

	<div class="kb-article-tree">
		<div class="kb-elements">

			<%
			for (int i = 0; i < results.size(); i++) {
				Article curArticle = (Article)results.get(i)[0];
				String curHTML = (String)results.get(i)[1];

				List<Article> curSiblingArticles = ArticleServiceUtil.getSiblingArticles(scopeGroupId, curArticle.getResourcePrimKey(), article.getStatus(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, new ArticlePriorityComparator(true));

				for (int j = 0; j < curSiblingArticles.size(); j++) {
					String html = "</div>";

					if (j == (curSiblingArticles.size() - 1)) {
						html = curHTML.concat("</div>");

						curHTML = StringPool.BLANK;
					}

					results.add(i + j + 1, new Object[] {curSiblingArticles.get(j), html});
				}
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
					request.setAttribute("article_icons.jsp-article", curArticle);
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

				<%= curHTML %>

			<%
			}
			%>

		</div>
	</div>
</c:if>