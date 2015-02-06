<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
KBArticle kbArticle = (KBArticle)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

KBNavigationDisplayContext kbNavigationDisplayContext = new KBNavigationDisplayContext(renderRequest, portalPreferences, portletPreferences, kbArticle);

request.setAttribute(WebKeys.KNOWLEDGE_BASE_KB_NAVIGATION_DISPLAY_CONTEXT, kbNavigationDisplayContext);
%>

<c:choose>
	<c:when test="<%= kbArticle != null %>">
		<div class="row-fluid">
			<c:if test="<%= kbNavigationDisplayContext.isLeftNavigationVisible() %>">
				<div class="span3">
					<liferay-util:include page="/display/view_navigation.jsp" servletContext="<%= application %>" />
				</div>
			</c:if>

			<div class='<%= kbNavigationDisplayContext.isLeftNavigationVisible() ? "span9" : "span12" %>'>
				<c:if test="<%= kbNavigationDisplayContext.isTopNavigationVisible() %>">
					<div class="kbarticle-navigation">
						<liferay-util:include page="/display/content_root_selector.jsp" servletContext="<%= application %>" />
					</div>
				</c:if>

				<liferay-util:include page="/display/view_article.jsp" servletContext="<%= application %>" />
			</div>
		</div>
	</c:when>
	<c:otherwise>

		<%
		renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.TRUE);
		%>

		<div class="alert alert-info portlet-configuration">
			<aui:a href="<%= portletDisplay.getURLConfiguration() %>" label="please-configure-this-portlet-to-make-it-visible-to-all-users" onClick="<%= portletDisplay.getURLConfigurationJS() %>" />
		</div>
	</c:otherwise>
</c:choose>