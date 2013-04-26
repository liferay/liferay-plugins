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
				<li class="config-item has-submenu">
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
		var userBar_Active = false;

		var dashboardNav = userBar.one('#dashboardNav');
		var notifications = userBar.one('.user-notification-events');
		var sitesResultContainer = userBar.one('.so-portlet-sites .portlet-body');

		var userBarMenuManager = function (params) {
			if (params.node != undefined) {
				var node = params.node;
			} else {
				var node = userBar;
			};

			if (params.after != undefined) {
				var after = params.after;
			} else {
				var after = null;
			};

			if (params.intent != undefined) {
				var intent = params.intent;
			} else {
				var intent = 'normal';
			};

			if (params.trigger != undefined) {
				var trigger = node.one(params.trigger.toString());
			} else {
				var trigger = node.one('a');
			};

			if (params.target != undefined) {
				var target = node.one(params.target);
				node.setAttribute('target', params.target);
			} else {
				var target = node;
			};

			if (params.changeClass != undefined) {
				var changeClass = params.changeClass;

				node.setAttribute('changeClass', changeClass);
			} else {
				var changeClass = 'menu-active';
			};

			if (params.eventType != undefined) {
				var eventType = params.eventType;
			} else {
				var eventType = 'click';
			};

			var closeAction = function () {
				if (userBar_Active && userBar.one('.menu-active')) {
					userBar.all('.menu-active').each(
						function (node) {
							if (node.hasAttribute('target')) {
								var target = node.one(node.getAttribute('target'));
							} else {
								var target = node;
							};

							if (node.hasAttribute('changeClass')) {
								var changeClass = node.getAttribute('changeClass').toString();
							} else {
								var changeClass = 'menu-active';
							};

							if (!target.hasClass('changeClass')) {
								target.addClass(changeClass);
							} else {
								target.removeClass(changeClass);
							};

							node.removeClass('menu-active');
						}
					);
				}
			};

			if (intent != 'close') {
				node.on(
					'click', function (event) {
						event.stopPropagation();
					}
				);

				trigger.on(
					eventType,
					function(event) {
						event.preventDefault();
						event.stopPropagation();

						closeAction(intent);

						if (intent == 'remove') {
							target.removeClass(changeClass)
						} else {
							target.addClass(changeClass);
						}

						if (!userBar_Active) {
							userBar_Active = true;
							userBar.addClass('user-bar-active');
						}

						if (after != null) {
							after;
						}

						node.addClass('menu-active');
					}
				);
			} else {
				node.on(
					eventType,
					function () {
						if (userBar_Active) {
							closeAction();

							userBar_Active = false;
							userBar.removeClass('user-bar-active');
						}
					}
				);
			};
		};

		new userBarMenuManager({
			node: userBar.one('.go-to'),
			trigger: '.search input',
			target: '.portlet-body',
			changeClass: 'search-focus',
			eventType: 'focus',
			after: function () {
				// body...
			}
		});

		new userBarMenuManager({
			node: userBar.one('.notifications-menu'),
			intent: 'remove',
			trigger: '.user-notification-events-icon',
			target: '.user-notification-events',
			changeClass: 'aui-overlaycontext-hidden'
		});

		new userBarMenuManager({
			node: userBar.one('.user-info')
		});

		new userBarMenuManager({
			node: userBar.one('.config-item')
		});

		new userBarMenuManager({
			node: A.one('html'),
			intent: 'close'
		});

		var searchInput = userBar.one('.search input');

		searchInput.set('value', defaultGoTo);

		searchInput.on(
			['focus', 'blur'],
			function (event) {
				var node = searchInput;

				if (node.get('value') == defaultGoTo) {
					node.set('value', '');
				} else if (node.get('value') == '' || !userBar_Active) {
					node.set('value', defaultGoTo);
				}
			}
		);

		var toggleDockbar = userBar.one("#toggleDockbar");

		if (toggleDockbar) {
			toggleDockbar.on(
				'click',
				function (event) {
					event.preventDefault();
					event.stopPropagation();

					body.toggleClass('show-dockbar');
				}
			)
		};
	</aui:script>
</c:if>
