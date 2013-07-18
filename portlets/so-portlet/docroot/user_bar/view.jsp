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
			.portlet-dockbar {
				overflow: hidden;
			}

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
							<a href="<%= mySite.getPathFriendlyURL(false,themeDisplay) + "/" + user.getScreenName() %>"><liferay-ui:message key="my-profile" /></a>
						</li>
						<li>
							<a href="<%= themeDisplay.getURLMyAccount().toString() %>"><liferay-ui:message key="my-account" /></a>
						</li>
						<c:if test="<%= themeDisplay.isShowControlPanelIcon() %>">
						<li>
							<a href="<%= themeDisplay.getURLControlPanel().toString() %>"><liferay-ui:message key="control-panel" /></a>
						</li>
						</c:if>
						<c:if test="<%= themeDisplay.isShowSignOutIcon() %>">
						<li>
							<a href="<%= themeDisplay.getURLSignOut().toString() %>"><liferay-ui:message key="sign-out" /></a>
						</li>
						</c:if>
					</ul>
				</li>
				<li class="config-item">
					<a class="config-icon" href="javascript:;" id="toggleDockbar"><img alt="Configuration Icon" src="<%= request.getContextPath() + "/user_bar/images/cog.png" %>" height="15" width="15" /><span class="aui-helper-hidden"><liferay-ui:message key="toggle" /> <liferay-ui:message key="javax.portlet.title.145" /></span></a>
				</li>
			</ul>
		</nav>
	</liferay-util:body-top>

	<aui:script use="aui-base, event">
		var defaultGoTo = '<liferay-ui:message key="go-to" /> ' + '\u25BE';

		var html = A.one('html');
		var body = html.one('body');

		var userBar = A.one('#so-portlet-user-bar');
		var userBar_Active = false;

		var dashboardNav = userBar.one('#dashboardNav');
		var notifications = userBar.one('.user-notification-events');
		var sitesResultContainer = userBar.one('.so-portlet-sites .portlet-body');

		var toggleUserBarMenus = function (params) {
			var parentNode = params.node;

			if (!params.changeClass) {
				params.changeClass = 'menu-active';
			} else {
				parentNode.setAttribute('change-class', params.changeClass);
			};

			if (!params.eventType) {
				params.eventType = 'click';
			};

			if (!params.intent) {
				params.intent = 'normal';
			};

			if (!params.target) {
				params.target = parentNode;
			} else {
				parentNode.setAttribute('target-menu', params.target);

				params.target = parentNode.one(params.target);
			};

			if (!params.trigger) {
				params.trigger = 'a';
			};

			params.trigger = parentNode.one(params.trigger);

			var closeAction = function () {
				if (userBar_Active && userBar.one('.menu-active')) {
					userBar.all('.menu-active').each(
						function (node) {
							var target = node;
							var changeClass = 'menu-active';

							if (node.hasAttribute('target-menu')) {
								var target = node.one(node.getAttribute('target-menu'));
							}

							if (node.hasAttribute('change-class')) {
								var changeClass = node.getAttribute('change-class');
							}

							if (!target.hasClass(changeClass)) {
								target.addClass(changeClass);
							} else {
								target.removeClass(changeClass);
							};

							node.removeClass('menu-active');
						}
					);
				}
			};

			if (params.intent != 'close') {
				parentNode.on(
					'click', function (event) {
						event.stopPropagation();
					}
				);

				params.trigger.on(
					params.eventType,
					function(event) {
						event.preventDefault();
						event.stopPropagation();

						closeAction();

						if (params.intent == 'remove') {
							params.target.removeClass(params.changeClass)
						} else {
							params.target.addClass(params.changeClass);
						}

						if (!userBar_Active) {
							userBar_Active = true;

							userBar.addClass('user-bar-active');
						}

						parentNode.addClass('menu-active');
					}
				);
			} else {
				parentNode.on(
					params.eventType,
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

		new toggleUserBarMenus({
			changeClass: 'search-focus',
			eventType: 'focus',
			node: userBar.one('.go-to'),
			target: '.portlet-body',
			trigger: '.search input'
		});

		new toggleUserBarMenus({
			changeClass: 'aui-overlaycontext-hidden',
			intent: 'remove',
			node: userBar.one('.notifications-menu'),
			target: '.user-notification-events',
			trigger: '.user-notification-events-icon'
		});

		new toggleUserBarMenus({
			node: userBar.one('.user-info')
		});

		new toggleUserBarMenus({
			intent: 'close',
			node: html
		});

		var searchInput = userBar.one('.search input');

		if (!userBar_Active) {
			searchInput.set('value', defaultGoTo);

			html.on(
				'click',
				function (event) {
					searchInput.set('value', defaultGoTo);
				}
			);
		};

		sitesResultContainer.on(
			'click',
			function (event) {
				if (searchInput.get('value') == defaultGoTo && userBar_Active) {
					searchInput.set('value', '');
				} else if (searchInput.get('value') == '' && !userBar_Active) {
					searchInput.set('value', defaultGoTo);
				}
			}
		);

		var toggleDockbar = userBar.one("#toggleDockbar");

		if (toggleDockbar) {
			toggleDockbar.on(
				'click',
				function (event) {
					event.preventDefault();

					body.toggleClass('show-dockbar');
				}
			)
		};
	</aui:script>
</c:if>
