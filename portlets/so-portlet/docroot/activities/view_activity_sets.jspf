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

<%
List<SocialActivitySet> results = null;
int total = 0;
%>

<c:choose>
	<c:when test="<%= group.isUser() %>">
		<liferay-ui:tabs
			names="all,connections,following,my-sites,me"
			url="<%= portletURL.toString() %>"
			value="<%= tabs1 %>"
		/>

		<%
		if (!layout.isPublicLayout()) {
			if (tabs1.equals("connections")) {
				results = SocialActivitySetLocalServiceUtil.getRelationActivitySets(group.getClassPK(), SocialRelationConstants.TYPE_BI_CONNECTION, searchContainer.getStart(), searchContainer.getEnd());
				total = SocialActivitySetLocalServiceUtil.getRelationActivitySetsCount(group.getClassPK(), SocialRelationConstants.TYPE_BI_CONNECTION);
			}
			else if (tabs1.equals("following")) {
				results = SocialActivitySetLocalServiceUtil.getRelationActivitySets(group.getClassPK(), SocialRelationConstants.TYPE_UNI_FOLLOWER, searchContainer.getStart(), searchContainer.getEnd());
				total = SocialActivitySetLocalServiceUtil.getRelationActivitySetsCount(group.getClassPK(), SocialRelationConstants.TYPE_UNI_FOLLOWER);
			}
			else if (tabs1.equals("me")) {
				results = SocialActivitySetLocalServiceUtil.getUserActivitySets(group.getClassPK(), searchContainer.getStart(), searchContainer.getEnd());
				total = SocialActivitySetLocalServiceUtil.getUserActivitySetsCount(group.getClassPK());
			}
			else if (tabs1.equals("my-sites")) {
				results = SocialActivitySetLocalServiceUtil.getUserGroupsActivitySets(group.getClassPK(), searchContainer.getStart(), searchContainer.getEnd());
				total = SocialActivitySetLocalServiceUtil.getUserGroupsActivitySetsCount(group.getClassPK());
			}
			else {
				results = SocialActivitySetLocalServiceUtil.getUserViewableActivitySets(group.getClassPK(), searchContainer.getStart(), searchContainer.getEnd());
				total = SocialActivitySetLocalServiceUtil.getUserViewableActivitySetsCount(group.getClassPK());
			}
		}
		else {
			results = SocialActivitySetLocalServiceUtil.getUserActivitySets(group.getClassPK(), searchContainer.getStart(), searchContainer.getEnd());
			total = SocialActivitySetLocalServiceUtil.getUserActivitySetsCount(group.getClassPK());
		}
		%>

	</c:when>
	<c:otherwise>

		<%
		results = SocialActivitySetLocalServiceUtil.getGroupActivitySets(group.getGroupId(), searchContainer.getStart(), searchContainer.getEnd());
		total = SocialActivitySetLocalServiceUtil.getGroupActivitySetsCount(group.getGroupId());
		%>

	</c:otherwise>
</c:choose>

<%
searchContainer.setResults(results);
searchContainer.setTotal(total);
%>

<%@ include file="/activities/view_activity_sets_feed.jspf" %>

<c:if test="<%= (!results.isEmpty()) %>">
	<liferay-ui:search-paginator
		searchContainer="<%= searchContainer %>"
		type="article"
	/>
</c:if>