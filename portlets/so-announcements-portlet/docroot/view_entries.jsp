<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
boolean readEntries = ParamUtil.getBoolean(request, "readEntries", true);

int flagValue = AnnouncementsFlagConstants.NOT_HIDDEN;

if (readEntries) {
	flagValue = AnnouncementsFlagConstants.HIDDEN;
}

LinkedHashMap<Long, long[]> scopes = new LinkedHashMap<Long, long[]>();

if (customizeAnnouncementsDisplayed) {
	long[] selectedScopeGroupIdsArray = GetterUtil.getLongValues(StringUtil.split(selectedScopeGroupIds));
	long[] selectedScopeOrganizationIdsArray = GetterUtil.getLongValues(StringUtil.split(selectedScopeOrganizationIds));
	long[] selectedScopeRoleIdsArray = GetterUtil.getLongValues(StringUtil.split(selectedScopeRoleIds));
	long[] selectedScopeUserGroupIdsArray = GetterUtil.getLongValues(StringUtil.split(selectedScopeUserGroupIds));

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

scopes.put(Long.valueOf(0), new long[] {0});

int start = ParamUtil.getInteger(request, "start", 0);

int end = ParamUtil.getInteger(request, "end", start + pageDelta);

int total = AnnouncementsEntryLocalServiceUtil.getEntriesCount(user.getUserId(), scopes, portletName.equals(alertsEntryPortletId), flagValue);

int[] startAndEnd = SearchPaginationUtil.calculateStartAndEnd(start, end, total);

List<AnnouncementsEntry> results = AnnouncementsEntryLocalServiceUtil.getEntries(user.getUserId(), scopes, portletName.equals(alertsEntryPortletId), flagValue, startAndEnd[0], startAndEnd[1]);
%>

<c:if test="<%= results.isEmpty() && !readEntries %>">
	<div class="no-announcements">
		<liferay-ui:message key="there-are-no-new-announcements" />
	</div>
</c:if>

<div class="entries <%= readEntries ? "read-entries" : "unread-entries" %>" data-start="<%= start %>">
	<c:choose>
		<c:when test="<%= readEntries %>">
			<c:if test="<%= themeDisplay.isSignedIn() && !results.isEmpty() %>">
				<div class="header">
					<span><%= LanguageUtil.get(request, "read-entries") %></span>
				</div>

				<%
				boolean expanded = ParamUtil.getBoolean(request, "expanded");
				%>

				<div class="content toggler-content toggler-content-<%= expanded ? "expanded" : "collapsed" %>">
					<%@ include file="/entry_iterator.jspf" %>
				</div>

				<aui:script>
					AUI().ready(
						'aui-toggler',
						function(A) {
							new A.Toggler(
								{
									animated: true,
									container: '#<portlet:namespace />readEntriesContainer .entries',
									content: '#<portlet:namespace />readEntriesContainer .entries .content',
									expanded: <%= expanded %>,
									header: '#<portlet:namespace />readEntriesContainer .entries .header',
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
		</c:when>
		<c:otherwise>
			<%@ include file="/entry_iterator.jspf" %>
		</c:otherwise>
	</c:choose>
</div>

<aui:script use="aui-base">
	<c:choose>
		<c:when test="<%= readEntries %>">
			var container = A.one('#<portlet:namespace />readEntriesContainer');
		</c:when>
		<c:otherwise>
			var container = A.one('#<portlet:namespace />unreadEntriesContainer');
		</c:otherwise>
	</c:choose>

	container.delegate(
		'click',
		function(event) {
			event.preventDefault();

			if (container) {
				var navLink = event.currentTarget;

				var navParent = navLink.ancestor();

				var start = navParent.hasClass('left-nav') ? '<%= String.valueOf(start - pageDelta) %>' : '<%= String.valueOf(start + pageDelta) %>';

				Liferay.Announcements.updateEntries(<%= readEntries %>, start);
			}
		},
		'.navigation span a'
	);
</aui:script>