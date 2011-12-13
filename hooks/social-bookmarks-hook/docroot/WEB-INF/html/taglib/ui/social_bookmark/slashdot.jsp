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

<%@ include file="/html/taglib/ui/social_bookmark/init.jsp" %>

<%
String slashdotDisplayStyle = StringPool.BLANK;

if (displayStyle.equals("vertical")) {
	slashdotDisplayStyle = "slashdot_badge_style='v0'";
}
%>

<script type="text/javascript">
	slashdot_title="<%= HtmlUtil.escapeJS(title) %>";
	slashdot_url="<%= url %>";
	<%= slashdotDisplayStyle %>
</script>

<script src="http://slashdot.org/slashdot-it.js" type="text/javascript"></script>