<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
long groupId = scopeGroupId;
String name = portletDisplay.getRootPortletId();
String primKey = portletDisplay.getResourcePK();
String actionId = "ADD_SOMETHING";
%>

Do you have the <i><liferay-ui:message key='<%= "action." + actionId %>' /></i> permission for this portlet?

<strong>

<c:choose>
	<c:when test="<%= permissionChecker.hasPermission(groupId, name, primKey, actionId) %>">
		Yes
	</c:when>
	<c:otherwise>
		No
	</c:otherwise>
</c:choose>

</strong>

<br /><br />

<%
name = "com.sample.permissions.model.Something";
primKey = "1";
actionId = "VIEW";
%>

Do you have the <i><liferay-ui:message key='<%= "action." + actionId %>' /></i> permission for the model <i><liferay-ui:message key='<%= "model.resource." + name %>' /></i> with the primary key <i><%= primKey %></i>?

<strong>

<c:choose>
	<c:when test="<%= permissionChecker.hasPermission(groupId, name, primKey, actionId) %>">
		Yes
	</c:when>
	<c:otherwise>
		No
	</c:otherwise>
</c:choose>

</strong>

<br /><br />

<portlet:renderURL var="redirectURL" />

<liferay-security:permissionsURL
	modelResource="<%= name %>"
	modelResourceDescription='<%= "Hello World" %>'
	redirect="<%= redirectURL %>"
	resourcePrimKey="<%= primKey %>"
	var="permissionsURL"
/>

Click <a href="<%= permissionsURL %>">here</a> to edit the permissions for the <i><liferay-ui:message key='<%= "model.resource." + name %>' /></i> model.