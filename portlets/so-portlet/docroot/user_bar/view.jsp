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
		<div class="so-portlet-user-bar" id="<portlet:namespace />userBar">

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
				<ul class="dashboard-nav" id="<portlet:namespace />dashboardNav">

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
		</div>
	</liferay-util:body-top>
</c:if>

<aui:script>
	function <portlet:namespace />openWindow() {
		<liferay-portlet:renderURL portletName="5_WAR_soportlet" var="viewSitesURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcPath" value="/sites/view_sites.jsp" />
		</liferay-portlet:renderURL>

		Liferay.Util.openWindow(
			{
				dialog: {
					align: {
						node: null,
						points: ['tc', 'tc']
					},
					constrain2view: true,
					cssClass: 'so-portlet-sites-dialog',
					modal: true,
					resizable: false,
					width: 650
				},
				title: '<%= UnicodeLanguageUtil.get(pageContext, "sites-directory") %>',
				uri: '<%= viewSitesURL %>'
			}
		);
	}
</aui:script>

<aui:script use="aui-base">
	var mySites = A.one('.portlet-dockbar .my-sites');

	if (mySites) {
		mySites.delegate(
			'click',
			function(event) {
				var sitesDirectory = mySites.one('.sites-directory');

				if (!sitesDirectory) {
					var mySitesMenu = mySites.one('.my-sites-menu');

					mySitesMenu.insert('<li class="sites-directory last"><a href="javascript:;" onclick="<portlet:namespace />openWindow()"><i class="icon-reorder"></i><span class="site-name"> ' + Liferay.Language.get('sites-directory') + '</span></a></li>')
				}
			},
			'.dropdown-toggle'
		);
	}
</aui:script>