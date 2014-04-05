<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
	socialOfficeUser = UserLocalServiceUtil.hasRoleUser(themeDisplay.getCompanyId(), RoleConstants.SOCIAL_OFFICE_USER, themeDisplay.getUserId(), true);
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
		<link href="<%= PortalUtil.getStaticResourceURL(request, PortalUtil.getPathContext(request) + "/user_bar/css/main.css", portlet.getTimestamp()) %>" rel="stylesheet" type="text/css" />
	</liferay-util:html-top>

	<liferay-util:body-top>
		<div class="so-portlet-user-bar" id="<portlet:namespace/>userBar">

			<%
			Group group = user.getGroup();
			%>

			<liferay-portlet:actionURL portletName="<%= PortletKeys.SITE_REDIRECTOR %>" var="dashboardURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
				<portlet:param name="struts_action" value="/my_sites/view" />
				<portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
				<portlet:param name="privateLayout" value="<%= Boolean.TRUE.toString() %>" />
			</liferay-portlet:actionURL>

			<a class="so-logo" href="<%= dashboardURL %>">
				<img alt="<liferay-ui:message key="social-office" /> <liferay-ui:message key="logo" />" height="32" src="<%= PortalUtil.getPathContext(request) + "/user_bar/images/so_logo.png" %>" width="32" />
			</a>

			<nav>
				<ul class="dashboard-nav" id="<portlet:namespace/>dashboardNav">

					<%
					List<Layout> mylayouts = LayoutLocalServiceUtil.getLayouts(group.getGroupId(), true);

					for (Layout myLayout : mylayouts) {
						if (myLayout.isRootLayout() && !myLayout.isHidden()) {
							String selected = StringPool.BLANK;

							if (myLayout.getPlid() == layout.getPlid()) {
								selected = "class=\"selected\"";
							}
					%>

							<li <%= selected %>>
								<a href="<%= HtmlUtil.escapeHREF(PortalUtil.getLayoutURL(myLayout, themeDisplay)) %>"><%= HtmlUtil.escape(myLayout.getName(themeDisplay.getLocale())) %></a>
							</li>

					<%
						}
					}
					%>

				</ul>
			</nav>

			<ul class="user-toolbar">
				<li class="go-to">
					<liferay-portlet:runtime portletName="<%= PortletKeys.SO_SITES %>" />
				</li>
				<li class="notifications-menu" id="<portlet:namespace/>notificationsMenu">
					<liferay-util:include page="/dockbar_notifications/view.jsp" servletContext="<%= application %>" />
				</li>
				<li class="user-menu has-submenu">
					<a class="user-info" href="<%= group.getPathFriendlyURL(false, themeDisplay) + "/" + user.getScreenName() %>">
						<span class="avatar">
							<img alt="<%= user.getFullName() %>" src="<%= HtmlUtil.escape(user.getPortraitURL(themeDisplay)) %>">
						</span>

						<span class="full-name"><%= user.getFullName() %></span> &#x25BE;
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
							<a href="<%= themeDisplay.getURLMyAccount() %>"><liferay-ui:message key="my-account" /></a>
						</li>

						<c:if test="<%= themeDisplay.isShowControlPanelIcon() %>">
							<li>
								<a href="<%= themeDisplay.getURLControlPanel() %>"><liferay-ui:message key="control-panel" /></a>
							</li>
						</c:if>

						<c:if test="<%= themeDisplay.isShowSignOutIcon() %>">
							<li>
								<a href="<%= themeDisplay.getURLSignOut() %>"><liferay-ui:message key="sign-out" /></a>
							</li>
						</c:if>
					</ul>
				</li>

				<%
				Group layoutGroup = null;

				if (layout != null) {
					layoutGroup = layout.getGroup();
				}
				%>

				<c:if test="<%= (layoutGroup != null) || !layoutGroup.isControlPanel() %>">
					<li class="config-item">
						<a class="config-icon" href="javascript:;" id="<portlet:namespace/>toggleDockbar">
							<img alt="<liferay-ui:message key="configuration" /> <liferay-ui:message key="icon" />" height="15" src="<%= PortalUtil.getPathContext(request) + "/user_bar/images/cog.png" %>" width="15" />

							<span class="aui-helper-hidden">
								<liferay-ui:message key="toggle" /> <liferay-ui:message key="javax.portlet.title.145" />
							</span>
						</a>
					</li>
				</c:if>
			</ul>
		</div>
	</liferay-util:body-top>

	<aui:script use="aui-base,liferay-so-user-menu">
		var body = A.one('body');

		var userBar = A.one('#<portlet:namespace/>userBar');

		var searchInput = userBar.one('.search input');

		var goToString = '<liferay-ui:message key="go-to" /> ' + '\u25BE';

		body.on(
			'click',
			function(event) {
				A.fire('close-menus');
			}
		);

		searchInput.set('value', goToString);

		searchInput.on(
			'click',
			function(event) {
				if (searchInput.get('value') == goToString) {
					searchInput.set('value', '');
				}
			}
		);

		A.on(
			'close-menus',
			function(event) {
				if (!userBar.one('.go-to').hasClass('search-focus') || (searchInput.get('value') == "")) {
					searchInput.set('value', goToString);
				}
			}
		);

		var toggleDockbar = userBar.one('#<portlet:namespace/>toggleDockbar');

		toggleDockbar.on(
			'click',
			function(event) {
				event.preventDefault();

				var body = A.one('body');

				body.toggleClass('show-dockbar');

				A.fire('close-menus', event);
			}
		);

		new Liferay.SO.UserMenu(
			{
				node: '#<portlet:namespace/>userBar .go-to',
				showClass: 'search-focus',
				showOn: 'focus',
				trigger: '#<portlet:namespace/>userBar .go-to .search input'
			}
		);

		new Liferay.SO.UserMenu(
			{
				hideClass: 'aui-overlaycontext-hidden',
				node: '#<portlet:namespace/>userBar .notifications-menu',
				target: '#<portlet:namespace/>userBar .notifications-menu .user-notification-events',
				trigger: '#<portlet:namespace/>userBar .user-notification-events-icon'
			}
		);

		new Liferay.SO.UserMenu(
			{
				node: '#<portlet:namespace/>userBar .user-menu',
				preventDefault: true,
				showClass: 'menu-active',
				trigger: '#<portlet:namespace/>userBar .user-info'
			}
		);
	</aui:script>
</c:if>