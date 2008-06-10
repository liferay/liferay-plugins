<%
/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<%@ page import="com.liferay.portal.model.Portlet" %>
<%@ page import="com.liferay.portal.service.PortletLocalServiceUtil" %>

<liferay-theme:defineObjects />

<%
Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), portletDisplay.getId());
%>

<link href="<%= request.getContextPath() %>/css.jsp?themeId=<%= themeDisplay.getTheme().getThemeId() %>&amp;colorSchemeId=<%= themeDisplay.getColorScheme().getColorSchemeId() %>&amp;t=<%= portlet.getTimestamp() %>" rel="stylesheet" type="text/css" />

<script src="<%= request.getContextPath() %>/javascript.js?t=<%= portlet.getTimestamp() %>" type="text/javascript"></script>

<ul class="chat-bar">
	<li class="item">
		<a class="item-link" href="javascript: ;">Chat (0)</a>

		<div class="popup">
			<div id="buddy-list">
				<ul class="chat-popup"></ul>
			</div>
		</div>
	</li>
</ul>