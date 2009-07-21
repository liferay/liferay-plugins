<%
/**
 * Copyright (c) 2008-2009 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
%>

<%@ include file="/html/portlet/search/init.jsp" %>

<liferay-util:buffer var="html">
	<liferay-util:include page="/html/portlet/search/search.portal.jsp" />
</liferay-util:buffer>

<%
int x = html.indexOf("<input type=\"button\" value=\"" + LanguageUtil.get(pageContext, "add-liferay-as-a-search-provider") + "\"");
int y = html.indexOf("/>", x);

if (x != -1) {
	html = StringUtil.remove(html, html.substring(x, y + 2), StringPool.BLANK);
}
%>

<%= html %>