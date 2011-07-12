<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

List<SocialActivity> activities = null;
int total = 0;
%>

<c:choose>
	<c:when test="<%= group.isUser() && (themeDisplay.getUserId() == group.getClassPK()) %>">
		<liferay-ui:tabs
			names="friends,coworkers,following,my-sites,me"
			url="<%= portletURL.toString() %>"
			value="<%= tabs1 %>"
		/>

		<%
		if (tabs1.equals("friends")) {
			activities = SocialActivityLocalServiceUtil.getRelationActivities(user.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND, searchContainer.getStart(), searchContainer.getEnd());
			total = SocialActivityLocalServiceUtil.getRelationActivitiesCount(user.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND);
		}
		else if (tabs1.equals("coworkers")) {
			activities = SocialActivityLocalServiceUtil.getRelationActivities(user.getUserId(), SocialRelationConstants.TYPE_BI_COWORKER, searchContainer.getStart(), searchContainer.getEnd());
			total = SocialActivityLocalServiceUtil.getRelationActivitiesCount(user.getUserId(), SocialRelationConstants.TYPE_BI_COWORKER);
		}
		else if (tabs1.equals("following")) {
			activities = SocialActivityLocalServiceUtil.getRelationActivities(user.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER, searchContainer.getStart(), searchContainer.getEnd());
			total = SocialActivityLocalServiceUtil.getRelationActivitiesCount(user.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER);
		}
		else if (tabs1.equals("my-sites")) {
			activities = SocialActivityLocalServiceUtil.getUserGroupsActivities(user.getUserId(), searchContainer.getStart(), searchContainer.getEnd());
			total = SocialActivityLocalServiceUtil.getUserGroupsActivitiesCount(user.getUserId());
		}
		else {
			activities = SocialActivityLocalServiceUtil.getUserActivities(user.getUserId(), searchContainer.getStart(), searchContainer.getEnd());
			total = SocialActivityLocalServiceUtil.getUserActivitiesCount(user.getUserId());
		}

		searchContainer.setTotal(total);
		%>

		<%@ include file="/activities/view_activities.jspf" %>
	</c:when>
	<c:otherwise>

		<%
		if (group.isUser()) {
			activities = SocialActivityLocalServiceUtil.getUserActivities(group.getClassPK(), searchContainer.getStart(), searchContainer.getEnd());
			total = SocialActivityLocalServiceUtil.getUserActivitiesCount(group.getClassPK());
		}
		else {
			activities = SocialActivityLocalServiceUtil.getGroupActivities(group.getGroupId(), searchContainer.getStart(), searchContainer.getEnd());
			total = SocialActivityLocalServiceUtil.getGroupActivitiesCount(group.getGroupId());
		}

		searchContainer.setTotal(total);
		%>

		<liferay-ui:social-activities
			activities="<%= activities %>"
			feedEnabled="<%= false %>"
		/>
	</c:otherwise>
</c:choose>

<c:if test="<%= (!activities.isEmpty()) %>">
	<liferay-ui:search-paginator
		searchContainer="<%= searchContainer %>"
		type="article"
	/>
</c:if>