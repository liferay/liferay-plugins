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

List<Group> groups = SitesUtil.getStarredSites(preferences);

int count = groups.size();
%>

<form action="<%= portletURL.toString() %>" method="get" name="<portlet:namespace />fm">
<liferay-portlet:renderURLParams varImpl="portletURL" />

<div class="site-list-container">
	<div class="search">
		<input id="<portlet:namespace />name" name="<portlet:namespace />name" size="30" type="text" value="<%= HtmlUtil.escape(name) %>" />

		<input type="submit" value="<liferay-ui:message key="search" />" />
	</div>

	<%
	if (groups.isEmpty()) {
		groups = SitesUtil.getVisibleSites(themeDisplay.getCompanyId(), themeDisplay.getUserId(), searchName, maxResultSize);
		count = SitesUtil.getVisibleSitesCount(themeDisplay.getCompanyId(), themeDisplay.getUserId(), searchName);
	}

	boolean hideNotice = GetterUtil.getBoolean(preferences.getValue("hide-notice", StringPool.BLANK), false);
	%>

	<c:if test="<%= groups.isEmpty() && !hideNotice %>">
		<div class="portlet-msg-info star-msg-info <%= hideNotice %>">
			<liferay-ui:message key="star-some-sites-to-customize-your-sites-list" />

			<span class="hide-notice">
				<liferay-portlet:actionURL name="hideNotice" var="hideNoticeURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
				</liferay-portlet:actionURL>

				<a href="<%= hideNoticeURL %>"><liferay-ui:message key="hide" /></a>
			</span>
		</div>
	</c:if>

	<ul class="site-list">
		<c:choose>
			<c:when test="<%= !groups.isEmpty() %>">

				<%
				boolean alternate = false;

				String starredGroupIds = preferences.getValue("starredGroupIds", StringPool.BLANK);

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
						<c:choose>
							<c:when test="<%= !StringUtil.contains(starredGroupIds, String.valueOf(group.getGroupId())) %>">
								<span class="action star">
									<liferay-portlet:actionURL name="updateStars" var="starURL">
										<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD %>" />
										<portlet:param name="redirect" value="<%= currentURL %>" />
										<portlet:param name="starredGroupId" value="<%= String.valueOf(group.getGroupId()) %>" />
									</liferay-portlet:actionURL>

									<a class="star" href="<%= starURL %>"><liferay-ui:message key="star" /></a>
								</span>
							</c:when>
							<c:otherwise>
								<span class="action unstar">
									<liferay-portlet:actionURL name="updateStars" var="unstarURL">
										<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
										<portlet:param name="redirect" value="<%= currentURL %>" />
										<portlet:param name="starredGroupId" value="<%= String.valueOf(group.getGroupId()) %>" />
									</liferay-portlet:actionURL>

									<a href="<%= unstarURL %>"><liferay-ui:message key="unstar" /></a>
								</span>
							</c:otherwise>
						</c:choose>

						<c:if test="<%= !member %>">
							<span class="action join">
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
									<liferay-portlet:actionURL windowState="<%= LiferayWindowState.NORMAL.toString() %>" portletName="<%= PortletKeys.MY_SITES %>" var="siteURL">
										<portlet:param name="struts_action" value="/my_sites/view" />
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
					<liferay-ui:message key="you-are-not-a-member-of-any-sites.-search-or-open-the-directory-to-get-started" />
				</li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>

<div class="control-container">
</div>

</form>

<aui:script use="aui-base,aui-io,aui-toolbar">
	Liferay.SO.Sites.init(
		{
			siteList: '.so-portlet-sites .site-list',
			siteListContainer: '.so-portlet-sites .site-list-container',
			siteListURL: '<portlet:resourceURL id="getSites"><portlet:param name="portletResource" value="<%= portletResource %>" /></portlet:resourceURL>',
			siteSearchInput: '#<portlet:namespace />name'
		}
	);

	var controlContainer = A.one('.so-portlet-sites .control-container');

	var addSiteButton = new A.Toolbar(
		{
			children: [
				<c:if test="<%= PortalPermissionUtil.contains(permissionChecker, ActionKeys.ADD_COMMUNITY) %>">
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
				</c:if>
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
		'.action a'
	);

	<c:if test="<%= groups.isEmpty() && !hideNotice %>">
		A.one('.so-portlet-sites .star-msg-info .hide-notice a').on(
			'click',
			function(event) {
				event.preventDefault();

				var link = event.currentTarget;

				A.io.request(
					link.get('href'),
					{
						after: {
							success: function(event, id, obj) {
								link.ancestor('.star-msg-info').hide();
							}
						}
					}
				);
			}
		);
	</c:if>
</aui:script>