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

<%@ include file="/display/init.jsp" %>

<%
Article article = (Article)request.getAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE);

int status = GetterUtil.getInteger((Integer)request.getAttribute(WebKeys.KNOWLEDGE_BASE_STATUS));
%>

<%@ include file="/admin/article_breadcrumbs.jspf" %>

<div class="float-container kb-entity-header">
	<div class="kb-title">
		<%= article.getTitle() %>
	</div>

	<div class="kb-tools">
		<%@ include file="/admin/article_tools.jspf" %>
	</div>
</div>

<div class="kb-entity-body">
	<%= article.getContent() %>

	<%@ include file="/admin/article_attachments.jspf" %>

	<%@ include file="/admin/article_assets.jspf" %>

	<%@ include file="/admin/article_ratings.jspf" %>

	<%@ include file="/admin/article_tree.jspf" %>

	<%@ include file="/admin/article_comments.jspf" %>
</div>