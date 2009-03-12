<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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