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
Article article = (Article)portletSession.getAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE);
%>

<c:choose>
	<c:when test="<%= article != null %>">

		<%
		request.setAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE, article);
		%>

		<liferay-util:include page="/display/view_article.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:otherwise>
		<liferay-ui:message key="there-are-no-articles" />
	</c:otherwise>
</c:choose>