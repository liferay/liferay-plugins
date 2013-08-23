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

<liferay-ui:success key="announcementAdded" message="the-announcement-was-successfully-added" />
<liferay-ui:success key="announcementDeleted" message="the-announcement-was-successfully-deleted" />
<liferay-ui:success key="announcementUpdated" message="the-announcement-was-successfully-updated" />

<c:if test="<%= (permissionChecker.isGroupAdmin(layout.getGroupId()) || permissionChecker.isGroupOwner(layout.getGroupId())) && (!group.isUser() || permissionChecker.isOmniadmin()) %>">
	<div class="admin-actions">
		<aui:button onClick='<%= renderResponse.getNamespace() + "addEntry()" %>' value="add-entry" />

		<aui:button onClick='<%= renderResponse.getNamespace() + "manageEntries()" %>' value="manage-entries" />
	</div>
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
	function <portlet:namespace />addEntry() {
		<portlet:renderURL var="addEntryURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcPath" value="/edit_entry.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
		</portlet:renderURL>

		<portlet:namespace />openWindow('<%= addEntryURL %>', '<%= LanguageUtil.get(pageContext, "add-entry") %>', true, 800);
	}

	function <portlet:namespace />editEntry(uri) {
		<portlet:namespace />openWindow(uri, '<%= LanguageUtil.get(pageContext, "edit-entry") %>', true, 800);
	}

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

	function <portlet:namespace />manageEntries() {
		<portlet:renderURL var="manageEntriesURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/manage_entries.jsp" /></portlet:renderURL>

		<portlet:namespace />openWindow('<%= manageEntriesURL %>', '<%= LanguageUtil.get(pageContext, "manage-entries") %>', true, 800);
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

	function <portlet:namespace />openWindow(url, title, modal, width) {
		Liferay.Util.openWindow(
			{
				cache: false,
				dialog: {
					align: Liferay.Util.Window.ALIGN_CENTER,
					modal: modal,
					on: {
						close: function() {
							Liferay.Portlet.refresh('#p_p_id<portlet:namespace />');
						}
					},
					width: width
				},
				id: '<portlet:namespace />Dialog',
				title: title,
				uri: url
			}
		);
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