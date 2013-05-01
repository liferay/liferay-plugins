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
	<c:when test="<%= group.isUser() && (themeDisplay.getUserId() == group.getClassPK()) && !layout.isPublicLayout() %>">
		<liferay-ui:tabs
			names="all,connections,following,my-sites"
			url="<%= portletURL.toString() %>"
			value="<%= tabs1 %>"
		/>

		<%
		List<SocialActivitySet> results = null;
		int total = 0;

		if (tabs1.equals("connections")) {
			results = SocialActivitySetLocalServiceUtil.getRelationActivitySets(themeDisplay.getUserId(), SocialRelationConstants.TYPE_BI_CONNECTION, searchContainer.getStart(), searchContainer.getEnd());
			total = SocialActivitySetLocalServiceUtil.getRelationActivitySetsCount(themeDisplay.getUserId(), SocialRelationConstants.TYPE_BI_CONNECTION);
		}
		else if (tabs1.equals("following")) {
			results = SocialActivitySetLocalServiceUtil.getRelationActivitySets(themeDisplay.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER, searchContainer.getStart(), searchContainer.getEnd());
			total = SocialActivitySetLocalServiceUtil.getRelationActivitySetsCount(themeDisplay.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER);
		}
		else if (tabs1.equals("my-sites")) {
			results = SocialActivitySetLocalServiceUtil.getUserGroupsActivitySets(themeDisplay.getUserId(), searchContainer.getStart(), searchContainer.getEnd());
			total = SocialActivitySetLocalServiceUtil.getUserGroupsActivitySetsCount(themeDisplay.getUserId());
		}
		else {
			results = SocialActivitySetLocalServiceUtil.getUserActivitySets(themeDisplay.getUserId(), searchContainer.getStart(), searchContainer.getEnd());
			total = SocialActivitySetLocalServiceUtil.getUserActivitySetsCount(themeDisplay.getUserId());
		}

		searchContainer.setResults(results);
		searchContainer.setTotal(total);
		%>

		<%@ include file="/activities/view_activities.jspf" %>
	</c:when>
	<c:otherwise>

		<%
		results = SocialActivitySetLocalServiceUtil.getGroupActivitySets(group.getGroupId(), searchContainer.getStart(), searchContainer.getEnd());
		total = SocialActivitySetLocalServiceUtil.getGroupActivitySetsCount(group.getGroupId());

		searchContainer.setResults(results);
		searchContainer.setTotal(total);
		%>

		<%@ include file="/activities/view_activities.jspf" %>
	</c:otherwise>
</c:choose>

<c:if test="<%= (!results.isEmpty()) %>">
	<liferay-ui:search-paginator
		searchContainer="<%= searchContainer %>"
		type="article"
	/>
</c:if>