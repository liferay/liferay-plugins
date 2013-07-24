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

<%@ include file="/init.jsp" %>

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

	<%
	Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), portletDisplay.getId());
	%>

	<liferay-util:html-top>
		<link href="<%= PortalUtil.getStaticResourceURL(request, request.getContextPath() + "/user_bar/css/main.css", portlet.getTimestamp()) %>" rel="stylesheet" type="text/css" />
	</liferay-util:html-top>

	<liferay-util:body-top>
		<div id="so-portlet-user-bar">

			<%
			Group group = user.getGroup();
			%>

			<liferay-portlet:actionURL portletName="<%= PortletKeys.SITE_REDIRECTOR %>" var="dashboardURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
				<portlet:param name="struts_action" value="/my_sites/view" />
				<portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
				<portlet:param name="privateLayout" value="<%= Boolean.TRUE.toString() %>" />
			</liferay-portlet:actionURL>

			<a href="<%= dashboardURL %>" id="so_logo">
				<img alt="Social Office Logo" height="32" src="<%= request.getContextPath() + "/user_bar/images/so_logo.png" %>" width="32" />
			</a>

			<nav>
				<ul id="dashboardNav">

					<%
					List<Layout> mylayouts = LayoutLocalServiceUtil.getLayouts(group.getGroupId(), true);

					for (Layout childLayout : mylayouts) {
						if (childLayout.isRootLayout() && !childLayout.isHidden()) {
							String selected = "";

							if (childLayout.getPlid() == layout.getPlid()) {
								selected = "class=\"selected\"";
							}
					%>

							<li <%= selected %>>
								<a href="<%= HtmlUtil.escapeHREF(PortalUtil.getLayoutURL(childLayout, themeDisplay)) %>"><%= HtmlUtil.escape(childLayout.getName(themeDisplay.getLocale())) %></a>
							</li>

					<%
						}
					}
					%>

				</ul>
			</nav>

			<ul class="user-toolbar">
				<li class="go-to">
					<liferay-portlet:runtime portletName="5_WAR_soportlet" />
				</li>
				<li class="notifications-menu" id="notificationsMenu">
					<liferay-util:include page="/dockbar_notifications/view.jsp" servletContext="<%= application %>" />
				</li>
				<li class="user-menu has-submenu">
					<a class="user-info" href="<%= group.getPathFriendlyURL(false,themeDisplay) + "/" + user.getScreenName() %>">
						<span class="avatar"><img src="<%= HtmlUtil.escape(user.getPortraitURL(themeDisplay)) %>" alt="<%= user.getFullName() %>"></span>
						
						<span class="full-name"><%= user.getFullName() %></span>
					</a>

					<ul class="child-menu">
						<li>
							<liferay-portlet:actionURL portletName="<%= PortletKeys.SITE_REDIRECTOR %>" var="profileURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
								<portlet:param name="struts_action" value="/my_sites/view" />
								<portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
								<portlet:param name="privateLayout" value="<%= Boolean.FALSE.toString() %>" />
							</liferay-portlet:actionURL>

							<a href="<%= profileURL %>"><liferay-ui:message key="my-profile" /></a>
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
					<a class="config-icon" href="javascript:;" id="toggleDockbar">
						<img alt="<liferay-ui:message key="configuration" /> <liferay-ui:message key="icon" />" height="15" src="<%= request.getContextPath() + "/user_bar/images/cog.png" %>" width="15" />
						
						<span class="aui-helper-hidden">
							<liferay-ui:message key="toggle" /> <liferay-ui:message key="javax.portlet.title.145" />
						</span>
					</a>
				</li>
			</ul>
		</div>
	</liferay-util:body-top>

	<aui:script use="aui-base,liferay-so-user-menu">
		var userBar = A.one('#so-portlet-user-bar');

		var searchInput = userBar.one('.search input');

		var goToString = '<liferay-ui:message key="go-to" /> ' + '\u25BE';

		searchInput.set('value', goToString);

		searchInput.on(
			'click',
			function(event) {
				searchInput.set('value', '');
			}
		);

		searchInput.on(
			'blur',
			function(event) {
				var sitesPortlet = userBar.one('.so-portlet-sites .portlet-body');

				if (!sitesPortlet.hasClass('search-focus')) {
					searchInput.set('value', goToString);
				}
			}
		);

		var toggleDockbar = userBar.one('#toggleDockbar');

		toggleDockbar.on(
			'click',
			function(event) {
				var body = A.one('body');

				body.toggleClass('show-dockbar');
			}
		)

		new Liferay.SO.UserMenu(
			{
				node: '#so-portlet-user-bar .go-to',
				showClass: 'search-focus',
				showOn: 'focus',
				target: '#so-portlet-user-bar .so-portlet-sites .portlet-body',
				trigger: '#so-portlet-user-bar .go-to .search input'
			}
		);

		new Liferay.SO.UserMenu(
			{
				hideClass: 'aui-overlaycontext-hidden',
				node: '#so-portlet-user-bar .notifications-menu',
				target: '#so-portlet-user-bar .notifications-menu .user-notification-events',
				trigger: '#so-portlet-user-bar .user-notification-events-icon'
			}
		);

		new Liferay.SO.UserMenu(
			{
				node: '#so-portlet-user-bar .user-menu',
				showClass: 'menu-active',
				trigger: '#so-portlet-user-bar .user-info'
			}
		);
	</aui:script>
</c:if>
