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

<%@ include file="/init.jsp" %>

<%
String name = portletDisplay.getRootPortletId();
String primKey = portletDisplay.getResourcePK();
%>

<div>
	<c:choose>
		<c:when test="<%= permissionChecker.hasPermission(scopeGroupId, name, primKey, ActionKeys.CONFIGURATION) %>">
			<span class="alert alert-error"><liferay-ui:message key="an-error-occurred-while-executing-the-validation.-please-review-the-following-errors" /></span>

			<pre><%= SessionErrors.get(renderRequest, "validationScriptError") %></pre>
		</c:when>
		<c:otherwise>
			<span class="alert alert-error"><liferay-ui:message key="an-error-occurred-while-executing-the-validation.-please-contact-an-administrator" /></span>
		</c:otherwise>
	</c:choose>
</div>