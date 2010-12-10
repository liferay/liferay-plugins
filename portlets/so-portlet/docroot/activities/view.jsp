<%--
/**
 * Copyright (c) 2008-2010 Liferay, Inc. All rights reserved.
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
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "my-sites");

Group group = GroupLocalServiceUtil.getGroup(scopeGroupId);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("tabs1", tabs1);

SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, 5, portletURL, null, null);

List<SocialActivity> activities = null;
int total = 0;

User curUser = null;

if (group.isUser()) {
	curUser = UserLocalServiceUtil.getUserById(group.getClassPK());
}
else {
	curUser = user;
}

if (group.isCommunity()) {
	activities = SocialActivityLocalServiceUtil.getGroupActivities(group.getGroupId(), searchContainer.getStart(), searchContainer.getEnd());
	total = SocialActivityLocalServiceUtil.getGroupActivitiesCount(group.getGroupId());
}
else if (tabs1.equals("my-sites")) {
	activities = SocialActivityLocalServiceUtil.getUserGroupsActivities(curUser.getUserId(), searchContainer.getStart(), searchContainer.getEnd());
	total = SocialActivityLocalServiceUtil.getUserGroupsActivitiesCount(curUser.getUserId());
}
else if (tabs1.equals("my-friends")) {
	activities = SocialActivityLocalServiceUtil.getRelationActivities(curUser.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND, searchContainer.getStart(), searchContainer.getEnd());
	total = SocialActivityLocalServiceUtil.getRelationActivitiesCount(curUser.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND);
}
else {
	activities = SocialActivityLocalServiceUtil.getUserActivities(curUser.getUserId(), searchContainer.getStart(), searchContainer.getEnd());
	total = SocialActivityLocalServiceUtil.getUserActivitiesCount(curUser.getUserId());
}

searchContainer.setTotal(total);

PortletURL rssURL = renderResponse.createRenderURL();

rssURL.setParameter("rss", "1");
%>

<c:if test="<%= group.isUser() && (themeDisplay.getUserId() == curUser.getUserId()) %>">
	<liferay-ui:tabs
		names="my-sites,my-friends,me"
		url="<%= portletURL.toString() %>"
	/>
</c:if>

<liferay-ui:social-activities
	activities="<%= activities %>"
	feedEnabled="<%= false %>"
/>

<c:if test="<%= (!activities.isEmpty()) && !themeDisplay.isStateExclusive() %>">
	<div class="taglib-search-iterator-page-iterator-bottom" id="<portlet:namespace />searchActivities">
		<liferay-ui:search-paginator
			searchContainer="<%= searchContainer %>"
			type="article"
		/>
	</div>
</c:if>