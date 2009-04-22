<%--
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
  --%>

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

<%@ include file="/html/portlet/communities/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Group group = (Group)row.getObject();

PortletURL rowURL = renderResponse.createActionURL();

rowURL.setWindowState(WindowState.NORMAL);

rowURL.setParameter("struts_action", "/communities/page");
rowURL.setParameter("groupId", String.valueOf(group.getGroupId()));
rowURL.setParameter("redirect", currentURL);
%>

<div>
	<a href="<%= rowURL %>">
		<%= group.getName() %>
	</a>

	<c:if test="<%= Validator.isNotNull(group.getDescription()) %>">
		<img alt="arrow" class="description-toggle" src="<%= themeDisplay.getPathThemeImage() %>/arrows/01_plus.png" />
	</c:if>

	<div class="description" style="display:none;">
		<%= StringUtil.shorten(group.getDescription(), 200) %>
	</div>
</div>