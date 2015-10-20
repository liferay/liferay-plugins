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

<%@ include file="/html/taglib/ui/social_bookmark/init.jsp" %>

<%
String redditDisplayStyle = "button1";

if (displayStyle.equals("vertical")) {
	redditDisplayStyle = "button2";
}
%>

<c:choose>
	<c:when test='<%= displayStyle.equals("simple") %>'>
		<a href="<%= HttpUtil.getProtocol(request) %>://www.reddit.com/submit" onclick="window.location = '<%= HttpUtil.getProtocol(request) %>://www.reddit.com/submit?url=' + encodeURIComponent('<%= url %>'); return false" title="<liferay-ui:message escapeAttribute="<%= true %>" key="submit-to-reddit" />"><img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="submit-to-reddit" />" border="0" src="<%= HttpUtil.getProtocol(request) %>://www.reddit.com/static/spreddit7.gif" /></a>
	</c:when>
	<c:otherwise>
		<aui:script>
			reddit_newwindow = '1';
			reddit_title = '<%= HtmlUtil.escapeJS(title) %>';
			reddit_url = '<%= url %>';
		</aui:script>

		<script src="<%= HttpUtil.getProtocol(request) %>://www.reddit.com/static/button/<%= redditDisplayStyle %>.js?styled=off" type="text/javascript"></script>
	</c:otherwise>
</c:choose>