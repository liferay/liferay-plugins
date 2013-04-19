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

<%@ page import="com.liferay.portal.NoSuchRoleException" %>

<%
boolean socialOfficeUser = false;

try {
	socialOfficeUser = UserLocalServiceUtil.hasRoleUser(themeDisplay.getCompanyId(), "Social Office User", themeDisplay.getUserId(), true);
}
catch (NoSuchRoleException nsre) {
	// This exception should never be thrown except while SO is being uninstalled
}
%>

<c:if test="<%= themeDisplay.isSignedIn() && socialOfficeUser %>">
	<liferay-util:html-top>
		<style type="text/css" media="screen">
			#dockbar {
				margin-top: -31px;
				opacity: 0;
				position: relative;
				z-index: 1000;
			}

			#dockbar .user-toolbar {
				display: none;
			}
		</style>
	</liferay-util:html-top>

	<liferay-util:body-top>
		<nav id="so-portlet-user-bar">

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

			<ul class="user-menu">
				<li class="go-to">
					<liferay-portlet:runtime portletName="5_WAR_soportlet" />
				</li>
				<li class="notifications-menu" id="notificationsMenu">
					<liferay-portlet:runtime portletName="7_WAR_soportlet" />
				</li>
				<li class="user-info has-submenu">
					<a href="<%= mySite.getPathFriendlyURL(false,themeDisplay) + "/" + user.getScreenName() %>"><span class="avatar"><img src="<%= HtmlUtil.escape(user.getPortraitURL(themeDisplay)) %>" alt="<%= user.getFullName() %>"></span><span class="full-name"><%= user.getFullName() %></span></a>

					<ul class="child-menu">
						<li>
							<a href="<%= themeDisplay.getURLMyAccount().toString() %>"><liferay-ui:message key="my-account" /></a>
						</li>
						<c:if test="<%= themeDisplay.isShowSignOutIcon() %>">
						<li>
							<a href="<%= themeDisplay.getURLSignOut().toString() %>"><liferay-ui:message key="sign-out" /></a>
						</li>
						</c:if>
					</ul>
				</li>
				<li class="has-submenu">
					<a class="config-icon" href="javascript:;"><img alt="Configuration Icon" src="<%= request.getContextPath() + "/user_bar/images/cog.png" %>" height="15" width="15" /><span class="aui-helper-hidden">Configuration</span></a>

					<ul class="child-menu">
						<li>
							<a href="javascript:;" id="toggleDockbar">Toggle Dockbar</a>
						</li>
						<c:if test="<%= themeDisplay.isShowControlPanelIcon() %>">
						<li>
							<a href="<%= themeDisplay.getURLControlPanel().toString() %>"><liferay-ui:message key="control-panel" /></a>
						</li>
						</c:if>
					</ul>
				</li>
			</ul>
		</nav>
	</liferay-util:body-top>

	<aui:script use="aui-base, event">
		var defaultGoTo = '<liferay-ui:message key="go-to" /> ' + '\u25BE';

		var body = A.one('body');

		var userBar = A.one('#so-portlet-user-bar');

		var dashboardNav = userBar.one('#dashboardNav');
		var notifications = userBar.one('.user-notification-events');
		var notificationButton = userBar.one('.user-notification-events-icon');
		var searchInput = userBar.one('.search input');
		var sitesResultContainer = userBar.one('.so-portlet-sites .portlet-body');

		var activateUserBar = function (event, active) {
			var setActive = function (active) {
				if (active && !userBar.hasClass('user-bar-active')) {
					userBar.addClass('user-bar-active');
				} else if (!active) {
					userBar.removeClass('user-bar-active');
				}
			};

			var target = event.currentTarget;

			if (target == notificationButton && active) {
				if (sitesResultContainer.hasClass('search-focus')) {
					if (searchInput.get('value') == '') {
						searchInput.set('value', defaultGoTo);
					}

					sitesResultContainer.removeClass('search-focus');
				}

				setActive(active);
			}
			else if (target == searchInput && active) {
				if (!notifications.hasClass('aui-overlaycontext-hidden')) {
					notifications.addClass('aui-overlaycontext-hidden');
				}

				setActive(active);
			}
			else if (target.hasClass('hover') && active) {
				if (!notifications.hasClass('aui-overlaycontext-hidden')) {
					notifications.addClass('aui-overlaycontext-hidden');
				}

				if (sitesResultContainer.hasClass('search-focus')) {
					if (searchInput.get('value') == '') {
						searchInput.set('value', defaultGoTo);
					}

					sitesResultContainer.removeClass('search-focus');
				}

				if (event.type != 'mouseout') {
					setActive(active);
				} else {
					setActive(false);
				}
			}
			else if (target == body || (event == 'hideOtherMenus') && !active) {
				if (sitesResultContainer.hasClass('search-focus') || userBar.hasClass('user-bar-active')) {
					sitesResultContainer.removeClass('search-focus');

					userBar.removeClass('user-bar-active');

					dashboardNav.setStyle('position','relative');
				}

				if (searchInput.get('value') == '') {
					searchInput.set('value', defaultGoTo);
				}

				if (!notifications.hasClass('aui-overlaycontext-hidden')) {
					notifications.addClass('aui-overlaycontext-hidden');
				}

				setActive(active);
			}
		};

		userBar.on(
			'click',
			function (event) {
				activateUserBar(event, true);
			}
		);

		userBar.all('.has-submenu').each(
			function (subMenu) {
				if (subMenu.one('.child-menu')) {
					subMenu.one('.child-menu').on('click', function(event){event.stopPropagation});

					subMenu.on(
						['mouseover','mouseout'],
						function (event) {
							event.currentTarget.toggleClass('hover');

							activateUserBar(event, true);
						}
					);
				}
			}
		);

		if (notificationButton) {
			notificationButton.on(
				'click',
				function (event) {
					event.preventDefault();
					event.stopPropagation();

					activateUserBar(event, true);

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

		var toggleDockbar = userBar.one("#toggleDockbar");

		if (toggleDockbar) {
			toggleDockbar.on(
				'click',
				function (event) {
					event.preventDefault();

					activateUserBar(event, true);

					body.toggleClass('show-dockbar');
				}
			)
		};

		searchInput.on(
			'focus',
			function (event) {
				if (event.target.get('value') == defaultGoTo) {
					event.target.set('value', '');
				}

				if (!sitesResultContainer.hasClass('search-focus')) {
					sitesResultContainer.addClass('search-focus');
					dashboardNav.setStyle('position','static');
				}

				activateUserBar(event, true);
			}
		);

		sitesResultContainer.on(
			'click',
			function(event) {
				event.stopPropagation();
			}
		);

		body.on(
			'click',
			function (event) {
				activateUserBar(event, false);
			}
		);

		searchInput.set('value', defaultGoTo);
	</aui:script>
</c:if>
