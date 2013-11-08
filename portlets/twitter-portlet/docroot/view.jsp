<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portal.util.PortletKeys" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
String twitterSn = contact.getTwitterSn();
%>

<c:choose>
	<c:when test="<%= Validator.isNotNull(twitterSn) %>">

		<%
		StringBuilder sb = new StringBuilder(5);

		sb.append("<a href=\"http://twitter.com/");
		sb.append(twitterSn);
		sb.append("\" target=\"_blank\">");
		sb.append(twitterSn);
		sb.append("</a>");
		%>

		<liferay-ui:message arguments="<%= sb.toString() %>" key="your-twitter-screen-name-is-x-your-tweets-will-appear-as-activities" />
	</c:when>
	<c:otherwise>

		<%
		String configureURL = "javascript:Liferay.Util.openWindow({id: '" + renderResponse.getNamespace() + "configureTwitter', title:'" + UnicodeLanguageUtil.get(pageContext, "my-account") + "',uri:'" + HtmlUtil.escapeURL(themeDisplay.getURLMyAccount() + "#_" + PortletKeys.MY_ACCOUNT + "_tab=_" + PortletKeys.MY_ACCOUNT + "_socialNetwork") + "'});";
		%>

		<div class="alert alert-info">
			<a href="<%= configureURL %>"><liferay-ui:message key="please-configure-your-twitter-screen-name" /></a>
		</div>
	</c:otherwise>
</c:choose>