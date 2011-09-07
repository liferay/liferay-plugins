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

<%@ page import="com.liferay.portal.service.permission.OrganizationPermissionUtil" %>
<%@ page import="com.liferay.portal.service.permission.PortalPermissionUtil" %>

<%
String searchName = DAOParamUtil.getLike(request, "name");

List<Group> mySites = SitesUtil.getStarredSites(themeDisplay.getUserId());

if (PortalPermissionUtil.contains(permissionChecker, ActionKeys.VIEW_CONTROL_PANEL)) {
	Group controlPanelGroup = GroupLocalServiceUtil.getGroup(user.getCompanyId(), GroupConstants.CONTROL_PANEL);

	mySites.add(0, controlPanelGroup);
}
%>


<c:if test="<%= !mySites.isEmpty() %>">

	<%
	PortletURL portletURL = ((LiferayPortletResponse)renderResponse).createLiferayPortletURL(plid, PortletKeys.MY_SITES, PortletRequest.ACTION_PHASE);

	portletURL.setWindowState(WindowState.NORMAL);
	portletURL.setPortletMode(PortletMode.VIEW);

	portletURL.setParameter("struts_action", "/my_sites/view");

	for (Group mySite : mySites) {
		mySite = mySite.toEscapedModel();

		boolean organizationSite = mySite.isOrganization();
		boolean regularSite = mySite.isRegularSite();
		boolean userSite = mySite.isUser();
		int publicLayoutsPageCount = mySite.getPublicLayoutsPageCount();
		int privateLayoutsPageCount = mySite.getPrivateLayoutsPageCount();

		Organization organization = null;

		String publicAddPageHREF = null;
		String privateAddPageHREF = null;

		if (organizationSite) {
			organization = OrganizationLocalServiceUtil.getOrganization(mySite.getOrganizationId());

			if (OrganizationPermissionUtil.contains(permissionChecker, organization.getOrganizationId(), ActionKeys.MANAGE_LAYOUTS)) {
				PortletURL addPageURL = ((LiferayPortletResponse)renderResponse).createLiferayPortletURL(plid, PortletKeys.MY_SITES, PortletRequest.ACTION_PHASE);

				addPageURL.setWindowState(WindowState.NORMAL);
				addPageURL.setPortletMode(PortletMode.VIEW);

				addPageURL.setParameter("struts_action", "/my_sites/edit_layouts");
				addPageURL.setParameter("redirect", currentURL);
				addPageURL.setParameter("groupId", String.valueOf(mySite.getGroupId()));
				addPageURL.setParameter("privateLayout", Boolean.FALSE.toString());

				publicAddPageHREF = addPageURL.toString();

				addPageURL.setParameter("privateLayout", Boolean.TRUE.toString());

				privateAddPageHREF = addPageURL.toString();
			}
		}
		else if (regularSite) {
			if (PortalPermissionUtil.contains(permissionChecker, ActionKeys.VIEW_CONTROL_PANEL)) {
				privateAddPageHREF = themeDisplay.getURLControlPanel();
			}
			else if (GroupPermissionUtil.contains(permissionChecker, mySite.getGroupId(), ActionKeys.MANAGE_LAYOUTS)) {
				PortletURL addPageURL = ((LiferayPortletResponse)renderResponse).createLiferayPortletURL(plid, PortletKeys.MY_SITES, PortletRequest.ACTION_PHASE);

				addPageURL.setWindowState(WindowState.NORMAL);
				addPageURL.setPortletMode(PortletMode.VIEW);

				addPageURL.setParameter("struts_action", "/my_sites/edit_layouts");
				addPageURL.setParameter("redirect", currentURL);
				addPageURL.setParameter("groupId", String.valueOf(mySite.getGroupId()));
				addPageURL.setParameter("privateLayout", Boolean.FALSE.toString());

				publicAddPageHREF = addPageURL.toString();

				addPageURL.setParameter("privateLayout", Boolean.TRUE.toString());

				privateAddPageHREF = addPageURL.toString();
			}
		}
		else if (userSite) {
			PortletURL publicAddPageURL = ((LiferayPortletResponse)renderResponse).createLiferayPortletURL(plid, PortletKeys.MY_ACCOUNT, PortletRequest.RENDER_PHASE);

			publicAddPageURL.setWindowState(WindowState.MAXIMIZED);
			publicAddPageURL.setPortletMode(PortletMode.VIEW);

			publicAddPageURL.setParameter("struts_action", "/my_account/edit_layouts");
			publicAddPageURL.setParameter("tabs1", "public-pages");
			publicAddPageURL.setParameter("redirect", currentURL);
			publicAddPageURL.setParameter("groupId", String.valueOf(mySite.getGroupId()));

			publicAddPageHREF = publicAddPageURL.toString();

			long privateAddPagePlid = mySite.getDefaultPrivatePlid();

			PortletURL privateAddPageURL = ((LiferayPortletResponse)renderResponse).createLiferayPortletURL(plid, PortletKeys.MY_ACCOUNT, PortletRequest.RENDER_PHASE);

			privateAddPageURL.setWindowState(WindowState.MAXIMIZED);
			privateAddPageURL.setPortletMode(PortletMode.VIEW);

			privateAddPageURL.setParameter("struts_action", "/my_account/edit_layouts");
			privateAddPageURL.setParameter("tabs1", "private-pages");
			privateAddPageURL.setParameter("redirect", currentURL);
			privateAddPageURL.setParameter("groupId", String.valueOf(mySite.getGroupId()));

			privateAddPageHREF = privateAddPageURL.toString();
		}

		boolean showPublicPlace = true;
		boolean showPrivatePlace = true;

	%>

		<c:if test="<%= showPublicPlace || showPrivatePlace %>">

			<%
			portletURL.setParameter("groupId", String.valueOf(mySite.getGroupId()));
			portletURL.setParameter("privateLayout", Boolean.FALSE.toString());

			boolean firstSite = false;

			if (mySites.indexOf(mySite) == 0) {
				firstSite = true;
			}

			boolean lastSite = false;

			if (mySites.size()	== (mySites.indexOf(mySite) + 1)) {
				lastSite = true;
			}

			boolean selectedSite = false;

			if (layout != null) {
				if (layout.getGroupId() == mySite.getGroupId()) {
					selectedSite = true;
				}
			}

			boolean selectedPlace = false;

			if (layout != null) {
				if (!layout.isPrivateLayout() && (layout.getGroupId() == mySite.getGroupId())) {
					selectedPlace = true;
				}
			}

			String cssClass = "public-community";

			if (firstSite) {
				cssClass += " first";
			}

			if (lastSite) {
				cssClass += " last";
			}

			if (selectedSite) {
				cssClass += " current-community";
			}

			if (selectedPlace) {
				cssClass += " current-site";
			}
			%>

			<c:if test="<%= showPublicPlace && publicLayoutsPageCount > 0 %>">
				<li class="<%= cssClass %>">
					<a href="<%= HtmlUtil.escape(portletURL.toString()) %>" onclick="Liferay.Util.forcePost(this); return false;">

						<%
						String siteName = StringPool.BLANK;

						if (organizationSite) {
							siteName = HtmlUtil.escape(organization.getName());
						}
						else if (userSite) {
							siteName = LanguageUtil.get(pageContext, "my-public-pages");
						}
						else if (mySite.getName().equals(GroupConstants.GUEST)) {
							siteName = HtmlUtil.escape(themeDisplay.getAccount().getName());
						}
						else {
							siteName = mySite.getName();
						}
						%>

						<%@ include file="/sites/page_site_name.jspf" %>

						<c:if test="<%= privateLayoutsPageCount > 0 %>">
							<span class="site-type"><liferay-ui:message key="public" /></span>
						</c:if>
					</a>
				</li>
			</c:if>

			<%
			portletURL.setParameter("privateLayout", Boolean.TRUE.toString());

			selectedPlace = false;

			if (layout != null) {
				selectedPlace = layout.isPrivateLayout() && (layout.getGroupId() == mySite.getGroupId());
			}

			cssClass = "private-community";

			if (mySite.isControlPanel()) {
				cssClass += " control-panel";
			}

			if (selectedSite) {
				cssClass += " current-community";
			}

			if (selectedPlace) {
				cssClass += " current-site";
			}
			%>

			<c:if test="<%= showPrivatePlace && privateLayoutsPageCount > 0 %>">
				<li class="<%= cssClass %>">
					<a href="<%= HtmlUtil.escape(portletURL.toString()) %>" onclick="Liferay.Util.forcePost(this); return false;">

						<%
						String siteName = StringPool.BLANK;

						if (organizationSite) {
							siteName = HtmlUtil.escape(organization.getName());
						}
						else if (userSite) {
							siteName = LanguageUtil.get(pageContext, "my-private-pages");
						}
						else if (mySite.getName().equals(GroupConstants.GUEST)) {
							siteName = HtmlUtil.escape(themeDisplay.getAccount().getName());
						}
						else {
							siteName = mySite.getName();
						}
						%>

						<%@ include file="/sites/page_site_name.jspf" %>

						<c:if test="<%= publicLayoutsPageCount > 0 %>">
							<span class="site-type"><liferay-ui:message key="private" /></span>
						</c:if>
					</a>
				</li>
			</c:if>
		</c:if>

	<%
	}
	%>
</c:if>