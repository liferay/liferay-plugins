<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
--%>

<%@ include file="/activities/init.jsp" %>

<%
Group group = themeDisplay.getScopeGroup();

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("tabs1", tabs1);

SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, 10, portletURL, null, null);
%>

<c:choose>
	<c:when test="<%= GetterUtil.getBoolean(PropsUtil.get(PropsKeys.SOCIAL_ACTIVITY_SETS_ENABLED)) %>">
		<%@ include file="/activities/view_activity_sets.jspf" %>
	</c:when>
	<c:otherwise>
		<%@ include file="/activities/view_activities.jspf" %>
	</c:otherwise>
</c:choose>

<aui:script use="aui-base">
	var announcementEntries = A.one('#p_p_id<portlet:namespace />');

	announcementEntries.delegate(
		'click',
		function(event) {
		},
		'.toggle-entry'
	);
</aui:script>