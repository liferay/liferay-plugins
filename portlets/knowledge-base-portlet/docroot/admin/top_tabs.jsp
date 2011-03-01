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

<c:if test="<%= rootPortletId.equals(PortletKeys.KNOWLEDGE_BASE_ADMIN) %>">

	<%
	String[] names = new String[] {"articles"};

	if (AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.VIEW_TEMPLATES)) {
		names = ArrayUtil.append(names, "templates");
	}

	String[] urls = new String[] {StringPool.BLANK, StringPool.BLANK};

	if (ArrayUtil.contains(names, "articles")) {
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("jspPage", "/admin/view.jsp");

		urls[0] = portletURL.toString();
	}

	if (ArrayUtil.contains(names, "templates")) {
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("jspPage", "/admin/view_templates.jsp");

		urls[1] = portletURL.toString();
	}

	String value = "articles";

	if (jspPage.contains("template")) {
		value = "templates";
	}
	%>

	<liferay-ui:tabs
		names="<%= StringUtil.merge(names) %>"
		param="topTabs"
		url0="<%= urls[0] %>"
		url1="<%= urls[1] %>"
		value="<%= value %>"
	/>
</c:if>