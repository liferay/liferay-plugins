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
String tuentiDisplayStyle = StringPool.BLANK;

if (displayStyle.equals("simple")) {
	tuentiDisplayStyle = "icon-style=\"small\"";
}
%>

<liferay-util:html-bottom outputKey="taglib_ui_social_bookmark_tuenti">
	<script src="<%= HttpUtil.getProtocol(request) %>://widgets.tuenti.com/widgets.js" type="text/javascript"></script>
</liferay-util:html-bottom>

<a class="tuenti-share-button" <%= tuentiDisplayStyle %> href="<%= HttpUtil.getProtocol(request) %>://www.tuenti.com/share" share-url="<%= url %>"></a>