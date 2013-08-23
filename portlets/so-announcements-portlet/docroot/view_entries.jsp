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

<div class="unread-entries" id="unreadEntries">

	<%
	LinkedHashMap<Long, long[]> scopes = new LinkedHashMap<Long, long[]>();

	if (customizeAnnouncementsDisplayed) {
		long[] selectedScopeGroupIdsArray = GetterUtil.getLongValues(StringUtil.split(selectedScopeGroupIds));
		long[] selectedScopeOrganizationIdsArray = GetterUtil.getLongValues(StringUtil.split(selectedScopeGroupIds));
		long[] selectedScopeRoleIdsArray = GetterUtil.getLongValues(StringUtil.split(selectedScopeGroupIds));
		long[] selectedScopeUserGroupIdsArray = GetterUtil.getLongValues(StringUtil.split(selectedScopeGroupIds));

		if (selectedScopeGroupIdsArray.length != 0) {
			scopes.put(PortalUtil.getClassNameId(Group.class.getName()), selectedScopeGroupIdsArray);
		}

		if (selectedScopeOrganizationIdsArray.length != 0) {
			scopes.put(PortalUtil.getClassNameId(Organization.class.getName()), selectedScopeOrganizationIdsArray);
		}

		if (selectedScopeRoleIdsArray.length != 0) {
			scopes.put(PortalUtil.getClassNameId(Role.class.getName()), selectedScopeRoleIdsArray);
		}

		if (selectedScopeUserGroupIdsArray.length != 0) {
			scopes.put(PortalUtil.getClassNameId(UserGroup.class.getName()), selectedScopeUserGroupIdsArray);
		}
	}
	else {
		scopes = AnnouncementsUtil.getAnnouncementScopes(user.getUserId());
	}

	scopes.put(new Long(0), new long[] {0});

	int flagValue = AnnouncementsFlagConstants.NOT_HIDDEN;

	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("mvcPath", "/view.jsp");

	SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, "cur1", pageDelta, portletURL, null, "there-are-no-unread-entries");

	List<AnnouncementsEntry> results = null;
	int total = 0;
	%>

	<%@ include file="/entry_iterator.jspf" %>
</div>

<c:if test="<%= total > 0 %>">
	<liferay-ui:search-paginator id="pageIteratorTop" searchContainer="<%= searchContainer %>" type="article" />
</c:if>

<%
flagValue = AnnouncementsFlagConstants.HIDDEN;

searchContainer = new SearchContainer(renderRequest, null, null, "cur2", pageDelta, portletURL, null, "there-are-no-read-entries");

results = AnnouncementsEntryLocalServiceUtil.getEntries(user.getUserId(), scopes, portletName.equals(PortletKeys.ALERTS), flagValue, searchContainer.getStart(), searchContainer.getEnd());
%>

<c:if test="<%= themeDisplay.isSignedIn() && !results.isEmpty() %>">
	<div class="read-entries" id="readEntries">
		<div class="header">
			<span><%= LanguageUtil.get(pageContext, "read-entries") %></span>
		</div>

		<div class="content aui-toggler-content aui-toggler-content-collapsed">
			<%@ include file="/entry_iterator.jspf" %>

			<c:if test="<%= total > 0 %>">
				<liferay-ui:search-paginator id="pageIteratorBottom" searchContainer="<%= searchContainer %>" type="article" />
			</c:if>
		</div>
	</div>

	<aui:script>
		AUI().ready(
			'aui-toggler',
			function(A) {
				new A.Toggler(
					{
						animated: true,
						container: '#readEntries',
						content: '#readEntries .content',
						expanded: false,
						header: '#readEntries .header',
						transition: {
							duration: 0.5,
							easing: 'ease-in-out'
						}
					}
				);
			}
		);
	</aui:script>
</c:if>