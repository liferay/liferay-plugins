<%
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
%>

<%@ include file="/init.jsp" %>

<c:if test="<%= themeDisplay.isSignedIn() %>">
	<liferay-util:html-top>
		<nav id="user-bar">

			<%
			Group mySite = user.getGroup();

			PortletURL mySiteURL = PortletURLFactoryUtil.create(request, PortletKeys.SITE_REDIRECTOR, plid, PortletRequest.ACTION_PHASE);

			mySiteURL.setWindowState(WindowState.NORMAL);
			mySiteURL.setPortletMode(PortletMode.VIEW);

			mySiteURL.setParameter("struts_action", "/my_sites/view");
			mySiteURL.setParameter("groupId", String.valueOf(mySite.getGroupId()));
			mySiteURL.setParameter("privateLayout", Boolean.TRUE.toString());
			%>

			<a href="<%= mySiteURL %>" id="so_logo">
				<img alt="Social Office Logo" src="<%= request.getContextPath() + "/user_bar/images/so_logo.png" %>" height="32" width="32" />
			</a>

			<ul id="dashboardNav">

			<%
			List<Layout> mylayouts = LayoutLocalServiceUtil.getLayouts(mySite.getGroupId(), true);

			for (Layout childLayout : mylayouts) {
				if (childLayout.isRootLayout() && !childLayout.isHidden()) {
					String selected = "";

					if (childLayout.getPlid() == layout.getPlid()) {
						selected = " class='selected'";
					}

			%>

					<li<%= selected %>><a href="<%= HtmlUtil.escapeHREF(PortalUtil.getLayoutURL(childLayout, themeDisplay)) %>"><%= HtmlUtil.escape(childLayout.getName(themeDisplay.getLocale())) %></a></li>

			<%
				}
			}
			%>

			</ul>

			<liferay-portlet:runtime portletName="5_WAR_soportlet" />

			<ul class="user-menu">
				<li class="notifications-menu has-submenu" id="notificationsMenu">
					<liferay-portlet:runtime portletName="7_WAR_soportlet" />
				</li>
				<li class="user-info"><a href="<%= mySite.getPathFriendlyURL(false,themeDisplay) + "/" + user.getScreenName() %>"><span class="avatar"><img src="<%= HtmlUtil.escape(user.getPortraitURL(themeDisplay)) %>" alt="<%= user.getFullName() %>"></span><span class="full-name"><%= user.getFullName() %></span></a></li>
				<li><a class="config-icon" href="javascript:;" id="userBarConfig"><img alt="Configuration Icon" src="<%= request.getContextPath() + "/user_bar/images/cog.png" %>" height="15" width="15" /><span class="aui-helper-hidden">Configuration</span></a></li>
			</ul>
		</nav>
	</liferay-util:html-top>

	<aui:script use="aui-base event">
		var userBar = A.one('#user-bar');
		var user_menus = userBar.all('.has-menu .child-menu');

		var notificationButton = userBar.one('.user-notification-events-icon');
		var notifications = userBar.one('.user-notification-events');
		var notificationEvents = notifications.all('user-notification-event-content');

		if (notificationButton) {
			notificationButton.on(
				'click',
				function (event) {
					event.preventDefault()
					event.stopPropagation();

					notifications.toggleClass('aui-overlaycontext-hidden');
				}
			);

			notifications.delegate(
				'click',
				function(event) {
					event.stopPropagation();

					var portletURL = event.currentTarget.getAttribute('data-portletUrl');

					if (portletURL) {
						window.location = portletURL;
					}
				},
				'.user-notification-event-content'
			);
		}
	</aui:script>
</c:if>
