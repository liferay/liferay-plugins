<%--
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
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<%@ page import="com.liferay.portal.kernel.util.Validator" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
String twitterSn = contact.getTwitterSn();
%>

<c:choose>
	<c:when test="<%= Validator.isNotNull(twitterSn) %>">
		Your Twitter screen name is <a href="http://twitter.com/<%= twitterSn %>" target="_blank"><%= twitterSn %></a>. Your tweets will appear as activities.
	</c:when>
	<c:otherwise>
		<a href="<%= themeDisplay.getURLMyAccount() %>&tabs4=social-network-ids">Please configure your Twitter screen name.</a>
	</c:otherwise>
</c:choose>