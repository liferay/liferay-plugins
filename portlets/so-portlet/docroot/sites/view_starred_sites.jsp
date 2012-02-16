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

List<Group> groups = SitesUtil.getFavoriteSitesGroups(themeDisplay.getUserId(), null, 8);

if (PortalPermissionUtil.contains(permissionChecker, ActionKeys.VIEW_CONTROL_PANEL)) {
	Group controlPanelGroup = GroupLocalServiceUtil.getGroup(user.getCompanyId(), GroupConstants.CONTROL_PANEL);

	groups.add(0, controlPanelGroup);
}
%>

<c:if test="<%= !groups.isEmpty() %>">

	<%
	PortletURL portletURL = ((LiferayPortletResponse)renderResponse).createLiferayPortletURL(plid, PortletKeys.MY_SITES, PortletRequest.ACTION_PHASE);

	portletURL.setWindowState(WindowState.NORMAL);
	portletURL.setPortletMode(PortletMode.VIEW);

	portletURL.setParameter("struts_action", "/my_sites/view");

	for (Group group : groups) {
		group = group.toEscapedModel();
	%>

		<c:if test="<%= (group.getPublicLayoutsPageCount() > 0) || (group.getPrivateLayoutsPageCount() > 0) %>">

			<%
			portletURL.setParameter("groupId", String.valueOf(group.getGroupId()));

			boolean firstSite = false;

			if (groups.indexOf(group) == 0) {
				firstSite = true;
			}

			boolean lastSite = false;

			if (groups.size() == (groups.indexOf(group) + 1)) {
				lastSite = true;
			}

			boolean selectedSite = false;

			if (layout != null) {
				if (layout.getGroupId() == group.getGroupId()) {
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
				<c:when test="<%= group.isControlPanel() %>">
					<li class="control-panel<%= cssClass %>">
						<a href="<%= themeDisplay.getURLControlPanel() %>">

							<%
							String siteName = group.getDescriptiveName(locale);
							%>

							<%@ include file="/sites/page_site_name.jspf" %>
						</a>
					</li>
				</c:when>
				<c:otherwise>

					<%
					portletURL.setParameter("privateLayout", Boolean.FALSE.toString());
					%>

					<c:if test="<%= group.getPublicLayoutsPageCount() > 0 %>">
						<li class="<%= (selectedSite && layout.isPublicLayout()) ? "current-site" : "public-site" %> <%= cssClass %>">
							<a href="<%= HtmlUtil.escape(portletURL.toString()) %>" onclick="Liferay.Util.forcePost(this); return false;">

								<%
								String siteName = StringPool.BLANK;

								if (group.isUser()) {
									siteName = LanguageUtil.get(pageContext, "my-public-pages");
								}
								else if (group.getName().equals(GroupConstants.GUEST)) {
									siteName = HtmlUtil.escape(themeDisplay.getAccount().getName());
								}
								else {
									siteName = group.getName();
								}
								%>

								<%@ include file="/sites/page_site_name.jspf" %>

								<c:if test="<%= group.getPrivateLayoutsPageCount() > 0 %>">
									<span class="site-type"><liferay-ui:message key="public" /></span>
								</c:if>
							</a>
						</li>
					</c:if>

					<%
					portletURL.setParameter("privateLayout", Boolean.TRUE.toString());
					%>

					<c:if test="<%= group.getPrivateLayoutsPageCount() > 0 %>">
						<li class="<%= (selectedSite && layout.isPrivateLayout()) ? "current-site" : "private-site" %> <%= cssClass %>">
							<a href="<%= HtmlUtil.escape(portletURL.toString()) %>" onclick="Liferay.Util.forcePost(this); return false;">

								<%
								String siteName = StringPool.BLANK;

								if (group.isUser()) {
									siteName = LanguageUtil.get(pageContext, "my-private-pages");
								}
								else if (group.getName().equals(GroupConstants.GUEST)) {
									siteName = HtmlUtil.escape(themeDisplay.getAccount().getName());
								}
								else {
									siteName = group.getName();
								}
								%>

								<%@ include file="/sites/page_site_name.jspf" %>

								<c:if test="<%= group.getPublicLayoutsPageCount() > 0 %>">
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