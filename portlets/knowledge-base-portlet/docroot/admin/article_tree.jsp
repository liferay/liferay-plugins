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

ListTree<Article> tree = ArticleServiceUtil.getLatestArticleTree(article.getResourcePrimKey());

TreeNode<Article> rootNode = tree.getRootNode();

List<TreeNode<Article>> childNodes = rootNode.getChildNodes();
%>

<c:if test="<%= !childNodes.isEmpty() %>">
	<div class="kb-article-tree">
		<div class="kb-elements">

			<%
			request.setAttribute("article_tree_node.jsp-node", rootNode);
			%>

			<liferay-util:include page="/admin/article_tree_node.jsp" servletContext="<%= application %>" />

			<%
			request.setAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE, article);
			%>

		</div>
	</div>
</c:if>