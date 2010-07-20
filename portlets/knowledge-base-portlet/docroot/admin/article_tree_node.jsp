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
TreeNode<Article> node = (TreeNode<Article>)request.getAttribute("article_tree_node.jsp-node");

for(TreeNode<Article> childNode : node.getChildNodes()) {
	Article article = childNode.getValue();
%>

	<div class='kb-element-header <%= childArticlesDisplayStyle.equals("title") ? "kb-title-only" : StringPool.BLANK %>'>
		<portlet:renderURL var="viewArticleURL">
			<portlet:param name="jspPage" value='<%= jspPath + "view_article.jsp" %>' />
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

	<div class="kb-element-body">

		<%
		request.setAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE, article);
		%>

		<liferay-util:include page="/admin/article_icons.jsp" servletContext="<%= application %>" />

		<c:choose>
			<c:when test='<%= childArticlesDisplayStyle.equals("full-content") %>'>
				<%= article.getContent() %>
			</c:when>
			<c:when test='<%= (childArticlesDisplayStyle.equals("abstract") && Validator.isNotNull(article.getDescription())) %>'>
				<%= article.getDescription() %>
			</c:when>
			<c:when test='<%= childArticlesDisplayStyle.equals("abstract") %>'>
				<%= StringUtil.shorten(HtmlUtil.extractText(article.getContent()), 500) %>
			</c:when>
		</c:choose>

		<%
		request.setAttribute("article_tree_node.jsp-node", childNode);
		%>

		<liferay-util:include page="/admin/article_tree_node.jsp" servletContext="<%= application %>" />
	</div>

<%
}
%>