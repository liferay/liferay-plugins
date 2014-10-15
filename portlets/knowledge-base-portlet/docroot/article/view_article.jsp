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

<%@ include file="/article/init.jsp" %>

<div id="<portlet:namespace/>message-container"></div>

<c:if test="<%= Validator.equals(portletDisplay.getId(), PortletKeys.KNOWLEDGE_BASE_ARTICLE_DEFAULT_INSTANCE) && PortletPermissionUtil.contains(permissionChecker, plid, portletDisplay.getId(), ActionKeys.CONFIGURATION) %>">
	<div class="alert alert-info portlet-configuration">
		<aui:a href="<%= portletDisplay.getURLConfiguration() %>" label='<%= LanguageUtil.format(pageContext, "portlet-configuration-page-x-instance-id-x", new String[] {layout.getName(locale), portletDisplay.getInstanceId()}, false) %>' onClick="<%= portletDisplay.getURLConfigurationJS() %>" />
	</div>
</c:if>

<div class="kb-article-container">
	<liferay-util:include page="/admin/common/view_article.jsp" servletContext="<%= application %>" />
</div>