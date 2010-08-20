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
%>

<liferay-util:buffer var="html">
	<liferay-ui:asset-categories-summary
		className="<%= Article.class.getName() %>"
		classPK="<%= article.getResourcePrimKey() %>"
	/>

	<liferay-ui:asset-tags-summary
		className="<%= Article.class.getName() %>"
		classPK="<%= article.getResourcePrimKey() %>"
	/>
</liferay-util:buffer>

<c:if test="<%= Validator.isNotNull(html.trim()) %>">
	<div class="kb-article-assets">
		<%= html %>
	</div>
</c:if>