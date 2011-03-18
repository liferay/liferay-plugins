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

<%@ include file="/article/init.jsp" %>

<%
Article article = null;

if (resourcePrimKey > 0) {
	try {
		article = ArticleServiceUtil.getLatestArticle(resourcePrimKey, WorkflowConstants.STATUS_APPROVED);
	}
	catch (Exception e) {
	}
}
%>

<c:choose>
	<c:when test="<%= article != null %>">

		<%
		request.setAttribute(WebKeys.KNOWLEDGE_BASE_ARTICLE, article);
		%>

		<liferay-util:include page="/article/view_article.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:otherwise>

		<%
		renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.TRUE);
		%>

		<div class="portlet-configuration portlet-msg-info">
			<c:choose>
				<c:when test="<%= PortletPermissionUtil.contains(permissionChecker, plid, portletDisplay.getId(), ActionKeys.CONFIGURATION) %>">
					<aui:a href="<%= portletDisplay.getURLConfiguration() %>" label="please-configure-this-portlet-to-make-it-visible-to-all-users" />
				</c:when>
				<c:otherwise>
					<liferay-ui:message key="please-configure-this-portlet-to-make-it-visible-to-all-users" />
				</c:otherwise>
			</c:choose>
		</div>
	</c:otherwise>
</c:choose>