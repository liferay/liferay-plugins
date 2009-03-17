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

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "my-sites");

PortletURL portletURL = renderResponse.createRenderURL();
%>

<liferay-ui:tabs
	names="my-sites,my-friends,me"
	url="<%= portletURL.toString() %>"
/>

<%
List<SocialActivity> activities = null;

Group group = GroupLocalServiceUtil.getGroup(scopeGroupId);

User curUser = null;

if (group.isUser()) {
	curUser = UserLocalServiceUtil.getUserById(group.getClassPK());
}
else {
	curUser = user;
}

if (tabs1.equals("my-sites")) {
	activities = SocialActivityLocalServiceUtil.getUserGroupsActivities(curUser.getUserId(), 0, SearchContainer.DEFAULT_DELTA);
}
else if (tabs1.equals("my-friends")) {
	activities = SocialActivityLocalServiceUtil.getRelationActivities(curUser.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND, 0, SearchContainer.DEFAULT_DELTA);
}
else {
	activities = SocialActivityLocalServiceUtil.getUserActivities(curUser.getUserId(), 0, SearchContainer.DEFAULT_DELTA);
}

PortletURL rssURL = renderResponse.createRenderURL();

rssURL.setParameter("rss", "1");
%>

<liferay-ui:social-activities
	activities="<%= activities %>"
	feedEnabled="<%= true %>"
	feedTitle='<%= LanguageUtil.format(pageContext, "subscribe-to-these-activities", user.getFirstName()) %>'
	feedLink="<%= rssURL.toString() %>"
	feedLinkMessage='<%= LanguageUtil.format(pageContext, "subscribe-to-these-activities", user.getFirstName()) %>'
/>