<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

<%@ include file="/sites/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "my-sites");

String name = ParamUtil.getString(request, "name");
String searchName = DAOParamUtil.getLike(request, "name");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.NORMAL);

portletURL.setParameter("tabs1", tabs1);

pageContext.setAttribute("portletURL", portletURL);
%>

<form action="<%= portletURL.toString() %>" method="get" name="<portlet:namespace />fm">
<liferay-portlet:renderURLParams varImpl="portletURL" />

<div class="site-list-container">
	<div class="search">
		<input id="<portlet:namespace />name" name="<portlet:namespace />name" size="30" type="text" value="<%= HtmlUtil.escape(name) %>" />

		<input type="submit" value="<liferay-ui:message key="search" />" />
	</div>

	<%
	List<Group> groups = SitesUtil.getVisibleSites(themeDisplay.getCompanyId(), themeDisplay.getUserId(), searchName, maxResultSize);
	int count = SitesUtil.getVisibleSitesCount(themeDisplay.getCompanyId(), themeDisplay.getUserId(), searchName);
	%>

	<ul class="site-list">

		<c:choose>
			<c:when test="<%= !groups.isEmpty() %>">

				<%
				boolean alternate = false;

				for (Group group : groups) {
					String className = StringPool.BLANK;

					if (GetterUtil.getBoolean(group.getExpandoBridge().getAttribute("socialOfficeEnabled"))) {
						className += "social-office-enabled ";
					}

					boolean member = GroupLocalServiceUtil.hasUserGroup(themeDisplay.getUserId(), group.getGroupId());

					if (member) {
						className += "member ";
					}

					if (alternate) {
						className += "alt";
					}
				%>

					<li class="<%= className %>">
						<c:if test="<%= !member %>">
							<span class="join">
								<liferay-portlet:actionURL windowState="<%= WindowState.NORMAL.toString() %>" portletName="<%= PortletKeys.SITES_ADMIN %>" var="joinURL">
									<portlet:param name="struts_action" value="/sites_admin/edit_site_assignments" />
									<portlet:param name="<%= Constants.CMD %>" value="group_users" />
									<portlet:param name="redirect" value="<%= currentURL %>" />
									<portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
									<portlet:param name="addUserIds" value="<%= String.valueOf(user.getUserId()) %>" />
								</liferay-portlet:actionURL>

								<a href="<%= joinURL %>"><liferay-ui:message key="join" /></a>
							</span>
						</c:if>

						<span class="name">
							<c:choose>
								<c:when test="<%= group.hasPrivateLayouts() || group.hasPublicLayouts() %>">
									<liferay-portlet:actionURL windowState="<%= LiferayWindowState.NORMAL.toString() %>" portletName="<%= PortletKeys.MY_PLACES %>" var="siteURL">
										<portlet:param name="struts_action" value="/my_places/view" />
										<portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
										<portlet:param name="privateLayout" value="<%= String.valueOf(!group.hasPublicLayouts()) %>" />
									</liferay-portlet:actionURL>

									<a href="<%= siteURL %>"><%= group.getDescriptiveName() %></a>
								</c:when>
								<c:otherwise>
									<%= group.getDescriptiveName() %>
								</c:otherwise>
							</c:choose>
						</span>
					</li>

				<%
					alternate = !alternate;
				}
				%>

				<c:if test="<%= count > maxResultSize %>">
					<li class="more">
						<a href="javascript:;"><liferay-ui:message key="view-all" /> (<%= count %>)</a>
					</li>
				</c:if>
			</c:when>
			<c:otherwise>
				<li class="empty">
					<liferay-ui:message key="there-are-no-results" />
				</li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>

<div class="control-container">
</div>

</form>

<aui:script>
	AUI().ready(
		'aui-base', 'aui-io', 'aui-toolbar',
		function(A) {
			Liferay.SO.Sites.init(
				{
					siteList: '.so-portlet-sites .site-list',
					siteListContainer: '.so-portlet-sites .site-list-container',
					siteListURL: '<portlet:resourceURL id="getSites" />',
					siteSearchInput: '#<portlet:namespace />name'
				}
			);

			<c:if test="<%= PortalPermissionUtil.contains(permissionChecker, ActionKeys.ADD_COMMUNITY) %>">
				var controlContainer = A.one('.so-portlet-sites .control-container');

				var addSiteButton = new A.Toolbar(
					{
						children: [
							{
								icon: 'plusthick',
								label: '<liferay-ui:message key="add-site" />',
								on: {
									click: function(event) {
										<liferay-portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" var="addSiteURL">
											<portlet:param name="jspPage" value="/sites/edit_site.jsp" />
										</liferay-portlet:renderURL>

										Liferay.SO.Sites.displayPopup('<%= addSiteURL %>', '<liferay-ui:message key="add-site" />');
									}
								}
							},
							{
								label: '<liferay-ui:message key="directory" />',
								on: {
									click: function(event) {
										<liferay-portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" var="viewSitesURL">
											<portlet:param name="jspPage" value="/sites/view_sites.jsp" />
										</liferay-portlet:renderURL>

										Liferay.SO.Sites.displayPopup('<%= viewSitesURL %>', '<liferay-ui:message key="sites" />');
									}
								}
							}
						]
					}
				).render(controlContainer);
			</c:if>

			var searchInput = A.one('#<portlet:namespace />name');

			var siteList = A.one('.so-portlet-sites .site-list');

			siteList.delegate(
				'click',
				function(event) {
					var keywords = searchInput.get('value');

					var data = {
						keywords: keywords,
						userSites: (keywords == '')
					};

					<liferay-portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" var="viewSitesURL">
						<portlet:param name="jspPage" value="/sites/view_sites.jsp" />
					</liferay-portlet:renderURL>

					Liferay.SO.Sites.displayPopup('<%= viewSitesURL %>', '<liferay-ui:message key="sites" />', data);
				},
				'.more a'
			);

			siteList.delegate(
				'click',
				function(event) {
					event.preventDefault();

					A.io.request(
						event.currentTarget.get('href'),
						{
							after: {
								success: function(event, id, obj) {
									Liferay.SO.Sites.updateSites();
								}
							}
						}
					);
				},
				'.join a'
			);
		}
	);
</aui:script>