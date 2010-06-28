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

<%@ include file="/display/init.jsp" %>

<%
Article article = (Article)request.getAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE);

if (article == null) {
	article = KnowledgeBaseUtil.getDisplayArticle(plid, portletDisplay.getId(), permissionChecker);
}
%>

<c:choose>
	<c:when test="<%= article == null %>">

		<%
		renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.TRUE);
		%>

		<div class="kb-portlet-configurator-msg portlet-msg-info">
			<liferay-ui:message key="there-are-no-articles" />
		</div>
	</c:when>
	<c:otherwise>

		<%
		request.setAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE, article);
		%>

		<liferay-util:include page="/display/view_article.jsp" servletContext="<%= application %>" />
	</c:otherwise>
</c:choose>