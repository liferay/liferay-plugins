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

<%@ include file="/admin/init.jsp" %>

<%
String jspPage = ParamUtil.getString(request, "jspPage");
%>

<c:if test="<%= AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.VIEW_KB_TEMPLATES) %>">

	<%
	PortletURL kbArticlesURL = renderResponse.createRenderURL();

	kbArticlesURL.setParameter("jspPage", "/admin/view.jsp");

	PortletURL kbTemplatesURL = renderResponse.createRenderURL();

	kbTemplatesURL.setParameter("jspPage", "/admin/view_templates.jsp");
	%>

	<liferay-ui:tabs
		names="articles,templates"
		param="topTabs"
		url0="<%= kbArticlesURL.toString() %>"
		url1="<%= kbTemplatesURL.toString() %>"
		value='<%= jspPage.contains("template") ? "templates" : "articles" %>'
	/>
</c:if>