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
					<a class="config-icon" href="javascript:;" id="userBarConfigButton"><img alt="Configuration Icon" src="<%= request.getContextPath() + "/user_bar/images/cog.png" %>" height="15" width="15" /><span class="aui-helper-hidden">Configuration</span></a>

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
		lang = {
			defaultGoTo: 'Go To ' + '\u25BE',
			search: 'Search'
		};

		var body = A.one('body');

		var userBar = body.one('#user-bar');
		var hasMenu = userBar.all('.has-submenu');
		var notificationButton = userBar.one('.user-notification-events-icon');
		var notifications = userBar.one('.user-notification-events');
		var notificationEvents = notifications.all('user-notification-event-content');
		var dashboardNav = userBar.one('#dashboardNav');
		var searchInput = userBar.one('.search input');
		var sitesResultContainer = userBar.one('.so-portlet-sites .portlet-body');
		var userBarConfigButton = userBar.one('#userBarConfigButton');
		var toggleDockbar = userBar.one("#toggleDockbar");

		var userBarActive = function (event, Boolean) {
			var target = event.currentTarget;
			var subMenu = {};
			var active = function (Boolean) {
				if (Boolean && !userBar.hasClass('user-bar-active')) {
					userBar.addClass('user-bar-active');
				} else if (!Boolean) {
					userBar.removeClass('user-bar-active');
				}

				return Boolean;
			};

			if (target == notificationButton && Boolean) {
				if (sitesResultContainer.hasClass('search-focus')) {
					if (searchInput.get('value') == '') {
						searchInput.attr('value', lang.defaultGoTo);
					}

					sitesResultContainer.removeClass('search-focus');
				}

				active(Boolean);
			} else if (target == searchInput && Boolean) {
				if (!notifications.hasClass('aui-overlaycontext-hidden')) {
					notifications.addClass('aui-overlaycontext-hidden');
				}

				active(Boolean);
			} else if (target.hasClass('hover') && Boolean) {
				if (!notifications.hasClass('aui-overlaycontext-hidden')) {
					notifications.addClass('aui-overlaycontext-hidden');
				}

				if (sitesResultContainer.hasClass('search-focus')) {
					if (searchInput.get('value') == '') {
						searchInput.attr('value', lang.defaultGoTo);
					}

					sitesResultContainer.removeClass('search-focus');
				}

				if (event.type != 'mouseout') {
					active(Boolean);
				} else {
					active(false);
				}
			} else if (target == body || (event == 'hideOtherMenus') && !Boolean) {
				if (sitesResultContainer.hasClass('search-focus') || userBar.hasClass('user-bar-active')) {
					sitesResultContainer.removeClass('search-focus');

					userBar.removeClass('user-bar-active');

					dashboardNav.setStyle('position','relative');
				}

				if (searchInput.get('value') == '') {
					searchInput.attr('value', lang.defaultGoTo);
				}

				if (!notifications.hasClass('aui-overlaycontext-hidden')) {
					notifications.addClass('aui-overlaycontext-hidden');
				}

				active(Boolean);
			}
		};

		userBar.on(
			'click',
			function (event) {
				userBarActive(event, true);
			}
		);

		hasMenu.each(
			function (subMenu) {
				if (subMenu.one('.child-menu')) {
					subMenu.one('.child-menu').on('click', function(event){event.stopPropagation});

					subMenu.on(
						['mouseover','mouseout'],
						function (event) {
							event.currentTarget.toggleClass('hover');

							userBarActive(event, true);
						}
					);
				}
			}
		);

		if (notificationButton) {
			notificationButton.on(
				'click',
				function (event) {
					event.preventDefault()
					event.stopPropagation();

					userBarActive(event, true);

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

		if (toggleDockbar) {
			toggleDockbar.on(
				'click',
				function (event) {
					event.preventDefault();

					userBarActive(event, true);

					body.toggleClass('show-dockbar');
				}
			)
		};

		searchInput.attr('value', lang.defaultGoTo);

		searchInput.on(
			'focus',
			function (event) {
				if (event.target.get('value') == lang.defaultGoTo) {
					event.target.attr('value', '');
				}

				if (!sitesResultContainer.hasClass('search-focus')) {
					sitesResultContainer.addClass('search-focus');
					dashboardNav.setStyle('position','static');
				}

				userBarActive(event, true);
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
				userBarActive(event, false);
			}
		);
	</aui:script>
</c:if>
