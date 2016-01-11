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
boolean showManageEntries = GroupPermissionUtil.contains(permissionChecker, group.getGroupId(), ActionKeys.MANAGE_ANNOUNCEMENTS);

if (group.isUser() && !showManageEntries) {
	showManageEntries = SOAnnouncementsUtil.hasGroups(themeDisplay) || SOAnnouncementsUtil.hasOrganizations(themeDisplay) || SOAnnouncementsUtil.hasRoles(themeDisplay) || SOAnnouncementsUtil.hasUserGroups(themeDisplay);

}
%>

<liferay-ui:success key="announcementAdded" message="the-announcement-was-successfully-added" />
<liferay-ui:success key="announcementDeleted" message="the-announcement-was-successfully-deleted" />
<liferay-ui:success key="announcementUpdated" message="the-announcement-was-successfully-updated" />

<div id="<portlet:namespace />errorMessage"></div>

<c:if test="<%= showManageEntries %>">
	<div class="admin-actions">
		<aui:button onClick='<%= renderResponse.getNamespace() + "addEntry()" %>' value="add-entry" />

		<aui:button onClick='<%= renderResponse.getNamespace() + "manageEntries()" %>' value="manage-entries" />
	</div>
</c:if>

<div class="unread-entries-container" id="<portlet:namespace />unreadEntriesContainer"></div>

<div class="read-entries-container" id="<portlet:namespace />readEntriesContainer"></div>

<aui:script use="aui-base">
	Liferay.Announcements.init(
		{
			namespace: '<portlet:namespace />',
			viewEntriesURL: '<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/view_entries.jsp" /></portlet:renderURL>'
		}
	);

	AUI().ready(
		function() {
			Liferay.Announcements.updateEntries(false, null);
			Liferay.Announcements.updateEntries(true, null);
		}
	);

	var announcementEntries = A.one('#p_p_id<portlet:namespace />');

	announcementEntries.delegate(
		'click',
		function(event) {
			Liferay.Announcements.toggleEntry(event);
		},
		'.toggle-entry'
	);

	announcementEntries.delegate(
		'click',
		function(event) {
			event.preventDefault();

			if (confirm('<%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-delete-the-selected-entry") %>')) {
				var entry = event.currentTarget.ancestor('.entry');

				var entryId = entry.attr('data-entryId');

				var uri = '<liferay-portlet:actionURL name="deleteEntry"></liferay-portlet:actionURL>';

				uri = Liferay.Util.addParams('<portlet:namespace />entryId=' + entryId, uri);

				A.io.request(
					uri,
					{
						after: {
							success: function(event, id, obj) {
								var responseData = this.get('responseData');

								if (!responseData.success) {
									var message = A.one('#<portlet:namespace />errorMessage');

									if (message) {
										message.html('<span class="alert alert-danger">' + responseData.message + '</span>');
									}
								}
								else {
									Liferay.Announcements.transitionEntry('#<portlet:namespace />' + entryId);

									setTimeout(
										function() {
											Liferay.Announcements.updateEntries(false, null);
											Liferay.Announcements.updateEntries(true, null);
										},
										200
									);
								}

							}
						},
						dataType: 'JSON'
					}
				);
			}
		},
		'.delete-entry a'
	);
</aui:script>

<aui:script>
	function <portlet:namespace />addEntry() {
		<portlet:renderURL var="addEntryURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcPath" value="/edit_entry.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
		</portlet:renderURL>

		<portlet:namespace />openWindow('<%= addEntryURL %>', '<%= UnicodeLanguageUtil.get(request, "add-entry") %>', true, 800);
	}

	function <portlet:namespace />editEntry(uri) {
		<portlet:namespace />openWindow(uri, '<%= UnicodeLanguageUtil.get(request, "edit-entry") %>', true, 800);
	}

	function <portlet:namespace />handleEntry(entryId) {
		var entry = AUI().one('#<portlet:namespace />' + entryId);

		if (entry) {
			var container = entry.get('parentNode');

			if (container) {
				Liferay.Announcements.transitionEntry('#<portlet:namespace />' + entryId);

				setTimeout(
					function() {
						if (container.hasClass('unread-entries')) {
							<portlet:namespace />markEntry(entryId);
						}
						else {
							<portlet:namespace />unmarkEntry(entryId);
						}
					},
					200
				);
			}
		}
	}

	function <portlet:namespace />manageEntries() {
		<portlet:renderURL var="manageEntriesURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/manage_entries.jsp" /></portlet:renderURL>

		<portlet:namespace />openWindow('<%= manageEntriesURL %>', '<%= UnicodeLanguageUtil.get(request, "manage-entries") %>', true, 800);
	}

	function <portlet:namespace />markEntry(entryId) {
		Liferay.Service(
			'/announcementsflag/add-flag',
			{
				entryId : entryId,
				value: <%= AnnouncementsFlagConstants.HIDDEN %>
			},
			function() {
				Liferay.Announcements.updateEntries(false, null);
				Liferay.Announcements.updateEntries(true, null);
			}
		);
	}

	function <portlet:namespace />openWindow(url, title, modal, width) {
		Liferay.Util.openWindow(
			{
				cache: false,
				dialog: {
					after: {
						visibleChange: function(event) {
							if (!event.currentTarget.get('visible')) {
								Liferay.Announcements.updateEntries(false, null);
								Liferay.Announcements.updateEntries(true, null);
							}
						}
					},
					align: Liferay.Util.Window.ALIGN_CENTER,
					modal: modal,
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
					},
					function() {
						Liferay.Announcements.updateEntries(false, null);
						Liferay.Announcements.updateEntries(true, null);
					}
				);
			}
		);
	}
</aui:script>