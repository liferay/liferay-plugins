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

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<%@ page import="com.liferay.portal.kernel.model.Organization" %>
<%@ page import="com.liferay.portal.kernel.service.OrganizationServiceUtil" %>

<%@ page import="java.util.List" %>

<liferay-theme:defineObjects />

You belong to the following organizations:

<br /><br />

<%
List organizations = OrganizationServiceUtil.getUserOrganizations(themeDisplay.getUserId());

for (int i = 0; i < organizations.size(); i++) {
	Organization organization = (Organization)organizations.get(i);
%>

	<%= organization.getName() %><br />

<%
}
%>