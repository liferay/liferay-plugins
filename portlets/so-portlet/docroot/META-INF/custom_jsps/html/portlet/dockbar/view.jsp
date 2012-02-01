<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

<%@ include file="/html/portlet/dockbar/init.jsp" %>

<%
Role role = RoleLocalServiceUtil.fetchRole(themeDisplay.getCompanyId(), "Social Office User");
%>

<c:choose>
	<c:when test="<%= (role == null) || !UserLocalServiceUtil.hasRoleUser(role.getRoleId(), themeDisplay.getUserId()) %>">
		<liferay-util:include page="/html/portlet/dockbar/view.portal.jsp" />
	</c:when>
	<c:otherwise>
		<liferay-util:buffer var="html">
			<liferay-util:include page="/html/portlet/dockbar/view.portal.jsp" />
		</liferay-util:buffer>

		<%
		int x = html.indexOf("<li class=\"user-avatar \" id=\"_145_userAvatar\">");
		int y = html.indexOf("<div class=\"dockbar-messages\"");
		%>

		<c:choose>
			<c:when test="<%= x > 0 %>">
				<%= html.substring(0, x) %>

				<%
				Group mySite = user.getGroup();

				PortletURL portletURL = new PortletURLImpl(request, PortletKeys.MY_SITES, plid, PortletRequest.ACTION_PHASE);

				portletURL.setWindowState(WindowState.NORMAL);
				portletURL.setPortletMode(PortletMode.VIEW);

				portletURL.setParameter("struts_action", "/my_sites/view");
				portletURL.setParameter("groupId", String.valueOf(mySite.getGroupId()));
				portletURL.setParameter("privateLayout", Boolean.TRUE.toString());
				%>

				<li>
					<liferay-ui:icon
						message="my-private-pages"
						src='<%= themeDisplay.getPathContext() + "/html/icons/social_office.png" %>'
					/>

					<a href="<%= portletURL %>"><liferay-ui:message key="dashboard" /></a>
				</li>

				<li class="aui-toolbar-separator">
					<span></span>
				</li>

				<li class="notifications-menu has-submenu" id="<portlet:namespace />notificationsMenu">
					<liferay-portlet:runtime portletName="7_WAR_soportlet" />
				</li>

				<li class="aui-toolbar-separator">
					<span></span>
				</li>

				<li class="user-menu has-submenu" id="<portlet:namespace />userMenu">
					<a class="menu-button" href="javascript:;">
						<span class="user-portrait">
							<img src="<%= HtmlUtil.escape(user.getPortraitURL(themeDisplay)) %>" />

							<%= HtmlUtil.escape(user.getFullName()) %>
						</span>
					</a>

					<div class="aui-menu user-menu aui-overlaycontext-hidden" id="<portlet:namespace />userMenuContainer">
						<div class="aui-menu-content user-menu-content" id="<portlet:namespace />userMenuContent">
							<ul>
								<li class="first profile">
									<aui:a href="<%= user.getDisplayURL(themeDisplay) %>" label="profile" />
								</li>

								<%
								String controlPanelCategory = StringPool.BLANK;
								String useDialog = StringPool.BLANK;

								if (!layout.getGroup().isControlPanel()) {
									controlPanelCategory = PortletCategoryKeys.MY;
									useDialog = StringPool.SPACE + "use-dialog full-dialog";
								}
								%>

								<li class="my-account" id="<portlet:namespace />userAvatar">
									<span class="user-links">
										<aui:a cssClass="<%= useDialog %>" data-controlPanelCategory="<%= controlPanelCategory %>" href="<%= themeDisplay.getURLMyAccount().toString() %>" label="my-account" title="manage-my-account" />
									</span>
								</li>

								<li class="last sign-out">
									<aui:a href="<%= themeDisplay.getURLSignOut().toString() %>" label="sign-out" />
								</li>
							</ul>
						</div>
					</div>
				</li>

				<style type="text/css">
					.dockbar .user-menu .user-portrait {
						padding-left: 0px;
					}

					.dockbar .user-menu .user-portrait img {
						width: 20px;
					}

					.dockbar .user-menu li a {
						background-repeat: no-repeat;
						background-position: 3px;
						padding-left: 25px;
					}

					.dockbar .user-menu .my-account a {
						background-image: url(/html/icons/my_account.png);
					}

					.dockbar .user-menu .profile a {
						background-image: url(/html/icons/users_admin.png);
					}

					.dockbar .user-menu	.sign-out a {
						background-image: url(<%= themeDisplay.getPathThemeImages() %>/dock/sign_out.png);
					}
				</style>

				<aui:script use="liferay-dockbar">
					Liferay.once(
						'dockbarLoaded',
						function() {
							var userMenuVars = {
								container: A.one('#<portlet:namespace />userMenuContainer'),
								contentBox: A.one('#<portlet:namespace />userMenuContent'),
								trigger: A.one('#<portlet:namespace />userMenu')
							};

							Liferay.Dockbar.addMenu(
								{
									align: {
										node: userMenuVars.trigger,
										points: ['tr', 'br']
									},
									boundingBox: userMenuVars.container,
									name: 'userMenu',
									trigger: userMenuVars.trigger
								}
							);

							var userMenuItems = userMenuVars.container.all('li a');

							userMenuItems.on(
								['mouseover', 'mouseout'],
								function(event) {
									event.currentTarget.toggleClass('aui-focus');
								}
							);
						}
					);
				</aui:script>

				<%= "</ul>" + html.substring(y) %>
			</c:when>
			<c:otherwise>
				<%= html %>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>