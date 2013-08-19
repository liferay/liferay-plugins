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

List<SocialActivity> results = null;
int total = 0;

int start = ParamUtil.getInteger(request, "start", 0);
int end = start + delta;
%>

<c:choose>
	<c:when test="<%= group.isUser() %>">

		<%
		if (!layout.isPublicLayout()) {
			if (tabs1.equals("connections")) {
				results = SocialActivityLocalServiceUtil.getRelationActivities(themeDisplay.getUserId(), SocialRelationConstants.TYPE_BI_CONNECTION, searchContainer.getStart(), searchContainer.getEnd());
				total = SocialActivityLocalServiceUtil.getRelationActivitiesCount(themeDisplay.getUserId(), SocialRelationConstants.TYPE_BI_CONNECTION);
			}
			else if (tabs1.equals("following")) {
				results = SocialActivityLocalServiceUtil.getRelationActivities(themeDisplay.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER, searchContainer.getStart(), searchContainer.getEnd());
				total = SocialActivityLocalServiceUtil.getRelationActivitiesCount(themeDisplay.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER);
			}
			else if (tabs1.equals("my-sites")) {
				results = SocialActivityLocalServiceUtil.getUserGroupsActivities(themeDisplay.getUserId(), searchContainer.getStart(), searchContainer.getEnd());
				total = SocialActivityLocalServiceUtil.getUserGroupsActivitiesCount(themeDisplay.getUserId());
			}
			else {
				results = SocialActivityLocalServiceUtil.getUserActivities(themeDisplay.getUserId(), searchContainer.getStart(), searchContainer.getEnd());
				total = SocialActivityLocalServiceUtil.getUserActivitiesCount(themeDisplay.getUserId());
			}
		}
		else {
			results = SocialActivityLocalServiceUtil.getUserActivities(group.getClassPK(), searchContainer.getStart(), searchContainer.getEnd());
			total = SocialActivityLocalServiceUtil.getUserActivitiesCount(group.getClassPK());
		}
		%>

	</c:when>
	<c:otherwise>

		<%
		results = SocialActivityLocalServiceUtil.getGroupActivities(group.getGroupId(), searchContainer.getStart(), searchContainer.getEnd());
		total = SocialActivityLocalServiceUtil.getGroupActivitiesCount(group.getGroupId());
		%>

	</c:otherwise>
</c:choose>

<%@ include file="/activities/view_activities_feed.jspf" %>

<c:if test="<%= (results.isEmpty()) %>">
	<div class="no-activities">
		<liferay-ui:message key="there-are-no-activities" />
	</div>
</c:if>