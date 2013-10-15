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

List<Group> groups = null;
int groupsCount = 0;

if (tabs1.equals("my-sites")) {
	groups = SitesUtil.getVisibleSites(themeDisplay.getCompanyId(), themeDisplay.getUserId(), name, true, 0, maxResultSize);
	groupsCount = SitesUtil.getVisibleSitesCount(themeDisplay.getCompanyId(), themeDisplay.getUserId(), name, true);

	if (groupsCount == 0) {
		tabs1 = "all-sites";

		groups = SitesUtil.getVisibleSites(themeDisplay.getCompanyId(), themeDisplay.getUserId(), name, false, 0, maxResultSize);
		groupsCount = SitesUtil.getVisibleSitesCount(themeDisplay.getCompanyId(), themeDisplay.getUserId(), name, false);
	}
}
else if (tabs1.equals("my-favorites")) {
	groups = SitesUtil.getFavoriteSitesGroups(themeDisplay.getUserId(), name, 0, maxResultSize);
	groupsCount = SitesUtil.getFavoriteSitesGroupsCount(themeDisplay.getUserId(), name);
}
else {
	groups = SitesUtil.getVisibleSites(themeDisplay.getCompanyId(), themeDisplay.getUserId(), name, false, 0, maxResultSize);
	groupsCount = SitesUtil.getVisibleSitesCount(themeDisplay.getCompanyId(), themeDisplay.getUserId(), name, false);
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
	<input class="search-input" id="<portlet:namespace />name" name="<portlet:namespace />name" placeholder="<liferay-ui:message key="go-to" />" size="30" type="text" value="<%= HtmlUtil.escape(name) %>" />

	<input src="<%= themeDisplay.getPathThemeImages() %>/common/search.png" type="image" value='<liferay-ui:message key="search" />' />
</div>

<div class="site-list-container">
	<ul class="site-list">
	</ul>
</div>

<div class="control-container">
</div>

</form>

<aui:script use="aui-base,aui-io-deprecated,aui-toolbar">
	Liferay.SO.Sites.init(
		{
			messages: '#<portlet:namespace />messages',
			namespace: '<portlet:namespace />',
			siteList: '.so-portlet-sites .site-list',
			siteListContainer: '.so-portlet-sites .site-list-container',
			siteListURL: '<portlet:resourceURL id="getSites"><portlet:param name="portletResource" value="<%= portletResource %>" /></portlet:resourceURL>',
			siteSearchInput: '#<portlet:namespace />name'
		}
	);

	Liferay.SO.Sites.updateSites();

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
				<portlet:namespace />keywords: keywords,
				<portlet:namespace />tabs1: sitesTabsSelect.get('value')
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
</aui:script>