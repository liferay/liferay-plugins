<%--
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
--%>

<%@ include file="/admin/init.jsp" %>

<%
Article article = (Article)request.getAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE);
%>

<liferay-util:include page="/admin/top_links.jsp" servletContext="<%= application %>" />

<liferay-util:include page="/admin/article_breadcrumbs.jsp" servletContext="<%= application %>" />

<div class="float-container kb-entity-header">
	<div class="kb-title">
		<%= article.getTitle() %>
	</div>

	<div class="kb-tools">
		<liferay-util:include page="/admin/article_tools.jsp" servletContext="<%= application %>" />
	</div>
</div>

<div class="kb-entity-body">
	<liferay-util:include page="/admin/article_icons.jsp" servletContext="<%= application %>" />

	<%= article.getContent() %>

	<liferay-util:include page="/admin/article_attachments.jsp" servletContext="<%= application %>" />

	<liferay-util:include page="/admin/article_assets.jsp" servletContext="<%= application %>" />

	<liferay-util:include page="/admin/article_ratings.jsp" servletContext="<%= application %>" />

	<liferay-util:include page="/admin/article_tree.jsp" servletContext="<%= application %>" />

	<liferay-util:include page="/admin/article_comments.jsp" servletContext="<%= application %>" />
</div>