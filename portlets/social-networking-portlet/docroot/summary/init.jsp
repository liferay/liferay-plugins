<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
PortletPreferences preferences = renderRequest.getPreferences();

String portletResource = ParamUtil.getString(request, "portletResource");

if (Validator.isNotNull(portletResource)) {
	preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

boolean enableRSS = !PortalUtil.isRSSFeedsEnabled() ? false : GetterUtil.getBoolean(preferences.getValue("enableRss", null), true);
int rssDelta = GetterUtil.getInteger(preferences.getValue("rssDelta", StringPool.BLANK), SearchContainer.DEFAULT_DELTA);
String rssDisplayStyle = preferences.getValue("rssDisplayStyle", RSSUtil.DISPLAY_STYLE_DEFAULT);
String rssFeedType = preferences.getValue("rssFeedType", RSSUtil.FEED_TYPE_DEFAULT);

Group guestGroup = GroupLocalServiceUtil.getGroup(themeDisplay.getCompanyId(), GroupConstants.GUEST);

long blogsPlid = PortalUtil.getPlidFromPortletId(group.getGroupId(), PortletKeys.BLOGS);

String blogsFriendlyURL = null;

if (blogsPlid != LayoutConstants.DEFAULT_PLID) {
	blogsFriendlyURL = PortalUtil.getLayoutFullURL(group.getGroupId(), PortletKeys.BLOGS, request.isSecure());
}

long mbPlid = PortalUtil.getPlidFromPortletId(guestGroup.getGroupId(), PortletKeys.MESSAGE_BOARDS);

String mbFriendlyURL = null;

if (mbPlid != LayoutConstants.DEFAULT_PLID) {
	mbFriendlyURL = PortalUtil.getLayoutFullURL(guestGroup.getGroupId(), PortletKeys.MESSAGE_BOARDS, request.isSecure());
}
%>