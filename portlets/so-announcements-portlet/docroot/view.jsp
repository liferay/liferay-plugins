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
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/view.jsp");

LinkedHashMap<Long, long[]> scopes = AnnouncementsUtil.getAnnouncementScopes(user.getUserId());

scopes.put(new Long(0), new long[] {0});

int flagValue = AnnouncementsFlagConstants.NOT_HIDDEN;

SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, "cur1", pageDelta, portletURL, null, "there-are-currently-no-unread-entries");

List<AnnouncementsEntry> results = null;

int total = 0;
%>

<liferay-ui:success key="announcementAdded" message="the-announcement-was-successfully-added" />
<liferay-ui:success key="announcementDeleted" message="the-announcement-was-successfully-deleted" />
<liferay-ui:success key="announcementUpdated" message="the-announcement-was-successfully-updated" />

<div class="unread-entries" id="unreadEntries">
	<%@ include file="/entry_iterator.jspf" %>
</div>

<c:if test="<%= total > 0 %>">
	<liferay-ui:search-paginator id="pageIteratorTop" searchContainer="<%= searchContainer %>" type="article" />
</c:if>

<%
flagValue = AnnouncementsFlagConstants.HIDDEN;

searchContainer = new SearchContainer(renderRequest, null, null, "cur2", pageDelta, portletURL, null, "there-are-currently-no-read-entries");

results = AnnouncementsEntryLocalServiceUtil.getEntries(user.getUserId(), scopes, portletName.equals(PortletKeys.ALERTS), flagValue, searchContainer.getStart(), searchContainer.getEnd());
%>

<c:if test="<%= (themeDisplay.isSignedIn()) && (results.size() != 0) %>">
	<div class="read-entries" id="readEntries">
		<div class="header">
			<span><%= LanguageUtil.get(pageContext, "read-entries") %></span>
		</div>

		<div class="content">
			<%@ include file="/entry_iterator.jspf" %>

			<c:if test="<%= total > 0 %>">
				<liferay-ui:search-paginator id="pageIteratorBottom" searchContainer="<%= searchContainer %>" type="article" />
			</c:if>
		</div>
	</div>
</c:if>

<aui:script>
	function <portlet:namespace />handleEntry(entryId) {
		var A = AUI();

		var entry = A.one('#<portlet:namespace />' + entryId);

		if (entry) {
			var container = entry.get('parentNode');

			if (container) {
				if (container.hasClass('unread-entries')) {
					<portlet:namespace />markEntry(entryId);
				}
				else {
					<portlet:namespace />unmarkEntry(entryId);
				}
			}
		}
	}

	function <portlet:namespace />markEntry(entryId) {
		Liferay.Service(
			'/announcementsflag/add-flag',
			{
				entryId : entryId,
				value: <%= AnnouncementsFlagConstants.HIDDEN %>
			}
		);

		Liferay.Portlet.refresh('#p_p_id<portlet:namespace />');
	}

	function <portlet:namespace />unmarkEntry(entryId) {
		Liferay.Service(
			'/announcementsflag/get-flag',
			{
				entryId : entryId,
				value: <%= AnnouncementsFlagConstants.HIDDEN %>
			},
			function(response) {
				Liferay.Service(
					'/announcementsflag/delete-flag',
					{
						flagId: response.flagId
					}
				);
			}
		);

		Liferay.Portlet.refresh('#p_p_id<portlet:namespace />');
	}
</aui:script>