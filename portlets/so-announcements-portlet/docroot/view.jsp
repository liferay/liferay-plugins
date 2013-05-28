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
portletURL.setParameter("mvcPath", "/view.jsp");

LinkedHashMap<Long, long[]> scopes = AnnouncementsUtil.getAnnouncementScopes(user.getUserId());

scopes.put(new Long(0), new long[] {0});

int flagValue = AnnouncementsFlagConstants.NOT_HIDDEN;

PortletPreferences preferences = renderRequest.getPreferences();

String portletResource = ParamUtil.getString(request, "portletResource");

if (Validator.isNotNull(portletResource)) {
	preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

int pageDelta = GetterUtil.getInteger(preferences.getValue("pageDelta", String.valueOf(SearchContainer.DEFAULT_DELTA)));

SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, "cur1", pageDelta, portletURL, null, "there-are-currently-no-unread-entries");

List<AnnouncementsEntry> results = null;

int total = 0;
%>

<liferay-ui:success key="announcementAdded" message="the-announcement-was-successfully-added" />
<liferay-ui:success key="announcementDeleted" message="the-announcement-was-successfully-deleted" />
<liferay-ui:success key="announcementUpdated" message="the-announcement-was-successfully-updated" />

<c:if test="<%= permissionChecker.isGroupAdmin(layout.getGroupId()) || permissionChecker.isGroupOwner(layout.getGroupId()) %>">
	<div class="admin-actions">
		<aui:button onClick='<%= renderResponse.getNamespace() + "addEntry()" %>' value="add-entry" />
		<aui:button onClick='<%= renderResponse.getNamespace() + "manageEntries()" %>' value="manage-entries" />
	</div>
</c:if>

<div class="unread-entries" id="unreadEntries">
	<%@ include file="/entry_iterator.jspf" %>
</div>

<%
int visibleMessagesCount = total;
%>

<c:if test="<%= visibleMessagesCount > 0 %>">
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
		<div class="content aui-toggler-content aui-toggler-content-collapsed">
			<%@ include file="/entry_iterator.jspf" %>

			<%
			int hiddenMessagesCount = total;

			String distributionScope = ParamUtil.getString(request, "distributionScope");
			%>

			<c:if test="<%= hiddenMessagesCount > 0 %>">
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
						content: '.content',
						expanded: false,
						header: '.header',
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

<aui:script use="aui-base">
	var announcementEntries = A.one('#p_p_id<portlet:namespace />');

	announcementEntries.delegate(
		'click',
		function(event) {
			Liferay.Announcements.toggleEntry(event,'<portlet:namespace />');
		},
		'.toggle-entry'
	);
</aui:script>

<aui:script>
	function <portlet:namespace />handleEntry(entryId) {
		var A = AUI();

		var entry = A.one('#<portlet:namespace />' + entryId);

		if (entry) {
			var container = entry.get('parentNode');

			if (container) {
				if (container.hasClass('unread-entries')) {
					<portlet:namespace />markEntry(entry, entryId);
				}
				else {
					<portlet:namespace />unmarkEntry(entry, entryId);
				}
			}
		}
	}

	function <portlet:namespace />markEntry(entry, entryId) {
		var A = AUI();

		Liferay.Service.Announcements.AnnouncementsFlag.addFlag({entryId : entryId, flag: <%= AnnouncementsFlagConstants.HIDDEN %>});

		Liferay.Portlet.refresh('#p_p_id<portlet:namespace />');
	}

	function <portlet:namespace />unmarkEntry(entry, entryId) {
		var A = AUI();

		flag = Liferay.Service.Announcements.AnnouncementsFlag.getFlag({entryId : entryId, flag: <%= AnnouncementsFlagConstants.HIDDEN %>});

		Liferay.Service.Announcements.AnnouncementsFlag.deleteFlag({flag: flag.flagId});

		Liferay.Portlet.refresh('#p_p_id<portlet:namespace />');
	}

	function <portlet:namespace />addEntry() {
		<portlet:renderURL var="addEntryURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/edit_entry.jsp" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:renderURL>

		<portlet:namespace />openWindow('<%= addEntryURL %>', '<%= LanguageUtil.get(pageContext, "add-entry") %>', true, 800);
	}

	function <portlet:namespace />editEntry(uri) {
		<portlet:namespace />openWindow(uri, '<%= LanguageUtil.get(pageContext, "edit-entry") %>', true, 800);
	}

	function <portlet:namespace />manageEntries() {
		<portlet:renderURL var="manageEntriesURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/manage_entries.jsp" /></portlet:renderURL>

		<portlet:namespace />openWindow('<%= manageEntriesURL %>', '<%= LanguageUtil.get(pageContext, "manage-entries") %>', true, 800);
	}

	function <portlet:namespace />openWindow(url, title, modal, width) {
		Liferay.Util.openWindow(
			{
				dialog: {
					align: Liferay.Util.Window.ALIGN_CENTER,
					modal: modal,
					width: width
				},
				cache: false,
				id: '<portlet:namespace />Dialog',
				title: title,
				uri: url
			}
		);
	}
</aui:script>