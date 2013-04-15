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

<%@ include file="/sites/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "my-sites");

String name = ParamUtil.getString(request, "name");
String searchName = DAOParamUtil.getLike(request, "name");

List<Group> groups = null;
int groupsCount = 0;

if (tabs1.equals("my-sites")) {
	groups = SitesUtil.getVisibleSites(themeDisplay.getCompanyId(), themeDisplay.getUserId(), searchName, true, 0, maxResultSize);
	groupsCount = SitesUtil.getVisibleSitesCount(themeDisplay.getCompanyId(), themeDisplay.getUserId(), searchName, true);

	if (groupsCount == 0) {
		tabs1 = "all-sites";

		groups = SitesUtil.getVisibleSites(themeDisplay.getCompanyId(), themeDisplay.getUserId(), searchName, false, 0, maxResultSize);
		groupsCount = SitesUtil.getVisibleSitesCount(themeDisplay.getCompanyId(), themeDisplay.getUserId(), searchName, false);
	}
}
else if (tabs1.equals("my-favorites")) {
	groups = SitesUtil.getFavoriteSitesGroups(themeDisplay.getUserId(), searchName, 0, maxResultSize);
	groupsCount = SitesUtil.getFavoriteSitesGroupsCount(themeDisplay.getUserId(), searchName);
}
else {
	groups = SitesUtil.getVisibleSites(themeDisplay.getCompanyId(), themeDisplay.getUserId(), searchName, false, 0, maxResultSize);
	groupsCount = SitesUtil.getVisibleSitesCount(themeDisplay.getCompanyId(), themeDisplay.getUserId(), searchName, false);
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.NORMAL);

pageContext.setAttribute("portletURL", portletURL);
%>

<div id="<portlet:namespace/>messages"><!-- --></div>

<form action="<%= portletURL.toString() %>" method="get" name="<portlet:namespace />fm">
<liferay-portlet:renderURLParams varImpl="portletURL" />

<div class="sites-tabs">
	<aui:select label="" name="tabs1">
		<aui:option label="all-sites" selected='<%= tabs1.equals("all-sites") %>' value="all-sites" />
		<aui:option label="my-sites" selected='<%= tabs1.equals("my-sites") %>' value="my-sites" />
		<aui:option label="my-favorites" selected='<%= tabs1.equals("my-favorites") %>' value="my-favorites" />
	</aui:select>
</div>

<div class="search">
	<input class="search-input" id="<portlet:namespace />name" name="<portlet:namespace />name" size="30" type="text" value="<%= HtmlUtil.escape(name) %>" />

	<input src="<%= themeDisplay.getPathThemeImages() %>/common/search.png" type="image" value='<liferay-ui:message key="search" />' />
</div>

<div class="site-list-container">

	<%
	boolean hideNotice = GetterUtil.getBoolean(preferences.getValue("hide-notice", StringPool.BLANK), false);
	%>

	<c:if test="<%= !hideNotice %>">
		<div class="portlet-msg-info favorite-msg-info <%= hideNotice %>">
			<liferay-ui:message key="favorite-some-sites-to-customize-this-list" />

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

				for (Group group : groups) {
					String className = StringPool.BLANK;

					ExpandoBridge expandoBridge = group.getExpandoBridge();

					if (SocialOfficeServiceUtil.isSocialOfficeGroup(group.getGroupId())) {
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
							<c:when test="<%= !FavoriteSiteLocalServiceUtil.isFavoriteSite(themeDisplay.getUserId(), group.getGroupId()) %>">
								<span class="action favorite">
									<liferay-portlet:actionURL name="updateFavorites" var="favoriteURL">
										<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD %>" />
										<portlet:param name="redirect" value="<%= currentURL %>" />
										<portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
									</liferay-portlet:actionURL>

									<a href="<%= favoriteURL %>"><liferay-ui:message key="favorite" /></a>
								</span>
							</c:when>
							<c:otherwise>
								<span class="action unfavorite">
									<liferay-portlet:actionURL name="updateFavorites" var="unfavoriteURL">
										<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
										<portlet:param name="redirect" value="<%= currentURL %>" />
										<portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
									</liferay-portlet:actionURL>

									<a href="<%= unfavoriteURL %>"><liferay-ui:message key="unfavorite" /></a>
								</span>
							</c:otherwise>
						</c:choose>

						<span class="name">
							<c:if test="<%= group.hasPublicLayouts() %>">
								<liferay-portlet:actionURL portletName="<%= PortletKeys.SITE_REDIRECTOR %>" var="publicLayoutsURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
									<portlet:param name="struts_action" value="/my_sites/view" />
									<portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
									<portlet:param name="privateLayout" value="<%= Boolean.FALSE.toString() %>" />
								</liferay-portlet:actionURL>

								<a href="<%= publicLayoutsURL %>"><%= HtmlUtil.escape(group.getDescriptiveName(locale)) %></a>
							</c:if>

							<c:if test="<%= (group.hasPrivateLayouts() && member) %>">
								<liferay-portlet:actionURL portletName="<%= PortletKeys.SITE_REDIRECTOR %>" var="privateLayoutsURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
									<portlet:param name="struts_action" value="/my_sites/view" />
									<portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
									<portlet:param name="privateLayout" value="<%= Boolean.TRUE.toString() %>" />
								</liferay-portlet:actionURL>

								<c:choose>
									<c:when test="<%= group.hasPublicLayouts() %>">
										<a class="private-pages" href="<%= privateLayoutsURL %>"> (<liferay-ui:message key="private-pages" />)</a>
									</c:when>
									<c:otherwise>
										<a href="<%= privateLayoutsURL %>"><%= HtmlUtil.escape(group.getDescriptiveName(locale)) %></a>
									</c:otherwise>
								</c:choose>

							</c:if>
						</span>
					</li>

				<%
					alternate = !alternate;
				}
				%>

				<c:if test="<%= groupsCount > maxResultSize %>">
					<li class="more">
						<a href="javascript:;"><liferay-ui:message key="view-all" /> (<%= groupsCount %>)</a>
					</li>
				</c:if>
			</c:when>
			<c:otherwise>
				<li class="empty">
					<c:choose>
						<c:when test='<%= tabs1.equals("my-sites") %>'>
							<liferay-ui:message key="you-are-not-a-member-of-any-sites.-search-or-open-the-directory-to-get-started" />
						</c:when>
						<c:when test='<%= tabs1.equals("my-favorites") %>'>
							<liferay-ui:message key="you-do-not-have-a-favorite-site" />
						</c:when>
						<c:otherwise>
							<liferay-ui:message key="there-are-no-results" />
						</c:otherwise>
					</c:choose>
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
			messages: '#<portlet:namespace />messages',
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
						label: '<liferay-ui:message key="add-site" unicode="<%= true %>" />',
						on: {
							click: function(event) {
								<liferay-portlet:renderURL var="addSiteURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
									<portlet:param name="mvcPath" value="/sites/edit_site.jsp" />
								</liferay-portlet:renderURL>

								Liferay.SO.Sites.displayPopup('<%= addSiteURL %>', '<liferay-ui:message key="add-site" unicode="<%= true %>" />');
							}
						}
					},
				</c:if>
				{
					label: '<liferay-ui:message key="sites-directory" unicode="<%= true %>" />',
					on: {
						click: function(event) {
							<liferay-portlet:renderURL var="viewSitesURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
								<portlet:param name="mvcPath" value="/sites/view_sites.jsp" />
							</liferay-portlet:renderURL>

							Liferay.SO.Sites.displayPopup('<%= viewSitesURL %>', '<liferay-ui:message key="sites-directory" unicode="<%= true %>" />');
						}
					}
				}
			]
		}
	).render(controlContainer);

	var searchInput = A.one('#<portlet:namespace />name');

	var siteList = A.one('.so-portlet-sites .site-list');

	var sitesTabsContainer = A.one('.so-portlet-sites .sites-tabs');

	var sitesTabsSelect = sitesTabsContainer.one('select[name=<portlet:namespace />tabs1]');

	sitesTabsSelect.on(
		'change',
		function(event) {
			searchInput.set('value', '');

			Liferay.SO.Sites.init(
				{
					messages: '#<portlet:namespace />messages',
					siteList: '.so-portlet-sites .site-list',
					siteListContainer: '.so-portlet-sites .site-list-container',
					siteListURL: '<portlet:resourceURL id="getSites"><portlet:param name="portletResource" value="<%= portletResource %>" /></portlet:resourceURL>',
					siteSearchInput: '#<portlet:namespace />name'
				}
			);

			Liferay.SO.Sites.updateSites();
		}
	);

	siteList.delegate(
		'click',
		function(event) {
			var keywords = searchInput.get('value');

			var data = {
				keywords: keywords,
				tabs1: sitesTabsSelect.get('value')
			};

			<liferay-portlet:renderURL var="viewSitesURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
				<portlet:param name="mvcPath" value="/sites/view_sites.jsp" />
			</liferay-portlet:renderURL>

			Liferay.SO.Sites.displayPopup('<%= viewSitesURL %>', '<liferay-ui:message key="sites" unicode="<%= true %>" />', data);
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
		A.one('.so-portlet-sites .favorite-msg-info .hide-notice a').on(
			'click',
			function(event) {
				event.preventDefault();

				var link = event.currentTarget;

				A.io.request(
					link.get('href'),
					{
						after: {
							success: function(event, id, obj) {
								link.ancestor('.favorite-msg-info').hide();
							}
						}
					}
				);
			}
		);
	</c:if>
</aui:script>