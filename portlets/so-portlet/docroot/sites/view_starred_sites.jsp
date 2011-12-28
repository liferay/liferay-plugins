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

<%@ include file="/sites/init.jsp" %>

<%
String searchName = DAOParamUtil.getLike(request, "name");

List<Group> starredSites = SitesUtil.getStarredSites(themeDisplay, null);

if (PortalPermissionUtil.contains(permissionChecker, ActionKeys.VIEW_CONTROL_PANEL)) {
	Group controlPanelGroup = GroupLocalServiceUtil.getGroup(user.getCompanyId(), GroupConstants.CONTROL_PANEL);

	starredSites.add(0, controlPanelGroup);
}
%>

<c:if test="<%= !starredSites.isEmpty() %>">

	<%
	PortletURL portletURL = ((LiferayPortletResponse)renderResponse).createLiferayPortletURL(plid, PortletKeys.MY_SITES, PortletRequest.ACTION_PHASE);

	portletURL.setWindowState(WindowState.NORMAL);
	portletURL.setPortletMode(PortletMode.VIEW);

	portletURL.setParameter("struts_action", "/my_sites/view");

	for (Group starredSite : starredSites) {
		starredSite = starredSite.toEscapedModel();
	%>

		<c:if test="<%= (starredSite.getPublicLayoutsPageCount() > 0) || (starredSite.getPrivateLayoutsPageCount() > 0) %>">

			<%
			portletURL.setParameter("groupId", String.valueOf(starredSite.getGroupId()));

			boolean firstSite = false;

			if (starredSites.indexOf(starredSite) == 0) {
				firstSite = true;
			}

			boolean lastSite = false;

			if (starredSites.size() == (starredSites.indexOf(starredSite) + 1)) {
				lastSite = true;
			}

			boolean selectedSite = false;

			if (layout != null) {
				if (layout.getGroupId() == starredSite.getGroupId()) {
					selectedSite = true;
				}
			}

			String cssClass = StringPool.BLANK;

			if (firstSite) {
				cssClass += " first";
			}

			if (lastSite) {
				cssClass += " last";
			}
			%>

			<c:choose>
				<c:when test="<%= starredSite.isControlPanel() %>">
					<li class="control-panel<%= cssClass %>">
						<a href="<%= themeDisplay.getURLControlPanel() %>">

							<%
							String siteName = starredSite.getDescriptiveName(locale);
							%>

							<%@ include file="/sites/page_site_name.jspf" %>
						</a>
					</li>
				</c:when>
				<c:otherwise>

					<%
					portletURL.setParameter("privateLayout", Boolean.FALSE.toString());
					%>

					<c:if test="<%= starredSite.getPublicLayoutsPageCount() > 0 %>">
						<li class="<%= (selectedSite && layout.isPublicLayout()) ? "current-site" : "public-site" %> <%= cssClass %>">
							<a href="<%= HtmlUtil.escape(portletURL.toString()) %>" onclick="Liferay.Util.forcePost(this); return false;">

								<%
								String siteName = StringPool.BLANK;

								if (starredSite.isUser()) {
									siteName = LanguageUtil.get(pageContext, "my-public-pages");
								}
								else if (starredSite.getName().equals(GroupConstants.GUEST)) {
									siteName = HtmlUtil.escape(themeDisplay.getAccount().getName());
								}
								else {
									siteName = starredSite.getName();
								}
								%>

								<%@ include file="/sites/page_site_name.jspf" %>

								<c:if test="<%= starredSite.getPrivateLayoutsPageCount() > 0 %>">
									<span class="site-type"><liferay-ui:message key="public" /></span>
								</c:if>
							</a>
						</li>
					</c:if>

					<%
					portletURL.setParameter("privateLayout", Boolean.TRUE.toString());
					%>

					<c:if test="<%= starredSite.getPrivateLayoutsPageCount() > 0 %>">
						<li class="<%= (selectedSite && layout.isPrivateLayout()) ? "current-site" : "private-site" %> <%= cssClass %>">
							<a href="<%= HtmlUtil.escape(portletURL.toString()) %>" onclick="Liferay.Util.forcePost(this); return false;">

								<%
								String siteName = StringPool.BLANK;

								if (starredSite.isUser()) {
									siteName = LanguageUtil.get(pageContext, "my-private-pages");
								}
								else if (starredSite.getName().equals(GroupConstants.GUEST)) {
									siteName = HtmlUtil.escape(themeDisplay.getAccount().getName());
								}
								else {
									siteName = starredSite.getName();
								}
								%>

								<%@ include file="/sites/page_site_name.jspf" %>

								<c:if test="<%= starredSite.getPublicLayoutsPageCount() > 0 %>">
									<span class="site-type"><liferay-ui:message key="private" /></span>
								</c:if>
							</a>
						</li>
					</c:if>
				</c:otherwise>
			</c:choose>
		</c:if>

	<%
	}
	%>

</c:if>