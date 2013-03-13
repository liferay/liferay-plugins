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

<%@ include file="/html/portlet/dockbar/init.jsp" %>

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

<c:choose>
	<c:when test="<%= !socialOfficeUser %>">
		<liferay-util:include page="/html/portlet/dockbar/view.portal.jsp" />
	</c:when>
	<c:otherwise>
		<liferay-util:buffer var="html">
			<liferay-util:include page="/html/portlet/dockbar/view.portal.jsp" />
		</liferay-util:buffer>

		<%
		if (layout != null) {
			Group group = layout.getGroup();

			if (group.isControlPanel() && (themeDisplay.getRefererPlid() > 0)) {
				Layout refererLayout = LayoutLocalServiceUtil.fetchLayout(themeDisplay.getRefererPlid());

				if (refererLayout != null) {
					Group refererGroup = refererLayout.getGroup();

					if (refererGroup.isUser() && (refererGroup.getClassPK() == user.getUserId())) {
						if (refererLayout.isPublicLayout()) {
							html = html.replaceFirst(LanguageUtil.get(pageContext, "my-public-pages"), LanguageUtil.get(pageContext, "profile"));
						}
						else {
							html = html.replaceFirst(LanguageUtil.get(pageContext, "my-private-pages"), LanguageUtil.get(pageContext, "dashboard"));
						}
					}
				}
			}
		}

		int x = html.indexOf("<li class=\"user-avatar \" id=\"_145_userAvatar\">");
		int y = html.indexOf("<div class=\"dockbar-messages\"");
		%>

		<c:choose>
			<c:when test="<%= x > 0 %>">
				<%= html.substring(0, x) %>

				<%
				Group mySite = user.getGroup();

				PortletURL portletURL = new PortletURLImpl(request, PortletKeys.SITE_REDIRECTOR, plid, PortletRequest.ACTION_PHASE);

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

				<li class="has-submenu user-avatar" id="<portlet:namespace />userMenu">
					<a class="menu-button user-fullname user-portrait" href="javascript:;">
						<img src="<%= HtmlUtil.escape(user.getPortraitURL(themeDisplay)) %>" style="width: 18px" />

						<%= HtmlUtil.escape(user.getFullName()) %>
					</a>

					<div class="aui-menu aui-overlaycontext-hidden" id="<portlet:namespace />userMenuContainer">
						<div class="aui-menu-content" id="<portlet:namespace />userMenuContent">
							<ul>
								<li class="aui-menu-item first profile">

									<%
									portletURL.setParameter("privateLayout", Boolean.FALSE.toString());
									%>

									<a href="<%= portletURL %>">
										<liferay-ui:icon
											message="my-profile"
											src='/html/icons/users_admin.png'
										/>

										<liferay-ui:message key="my-profile" />
									</a>
								</li>

								<li class="aui-menu-item my-account" id="<portlet:namespace />userAvatar">
									<span class="user-links">
										<a class='<%= !layout.getGroup().isControlPanel() ? "use-dialog full-dialog" : StringPool.BLANK %>' data-controlPanelCategory="<%= !layout.getGroup().isControlPanel() ? PortletCategoryKeys.MY : StringPool.BLANK %>" href="<%= themeDisplay.getURLMyAccount().toString() %>">
											<liferay-ui:icon
												message="my-account"
												src='/html/icons/my_account.png'
											/>

											<liferay-ui:message key="my-account" />
										</a>
									</span>
								</li>

								<li class="aui-menu-item last sign-out">
									<a href="<%= themeDisplay.getURLSignOut().toString() %>">
										<liferay-ui:icon
											message="sign-out"
											src='<%= themeDisplay.getPathThemeImages() + "/dock/sign_out.png" %>'
										/>

										<liferay-ui:message key="sign-out" />
									</a>
								</li>
							</ul>
						</div>
					</div>
				</li>

				<%= "</ul>" + html.substring(y) %>

				<aui:script use="liferay-dockbar">
					Liferay.once(
						'dockbarLoaded',
						function() {
							var userMenuContainer = A.one('#<portlet:namespace />userMenuContainer');
							var userMenuTrigger = A.one('#<portlet:namespace />userMenu');

							Liferay.Dockbar.addMenu(
								{
									align: {
										node: userMenuTrigger,
										points: ['tr', 'br']
									},
									boundingBox: userMenuContainer,
									name: 'userMenu',
									trigger: userMenuTrigger
								}
							);

							userMenuContainer.all('li a').on(
								['mouseover', 'mouseout'],
								function(event) {
									event.currentTarget.toggleClass('aui-focus');
								}
							);
						}
					);
				</aui:script>
			</c:when>
			<c:otherwise>
				<%= html %>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>

<liferay-util:include page="/html/portlet/dockbar/license_warning.jsp" />