<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%
LinkedHashMap<Long, long[]> scopes = AnnouncementsUtil.getAnnouncementScopes(user.getUserId());

SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, "cur1", pageDelta, portletURL, null, "no-entries-were-found");

List<AnnouncementsEntry> results = null;
int total = 0;

int flagValue = AnnouncementsFlagConstants.NOT_HIDDEN;
%>

<div class="unread-entries">
	<%@ include file="/html/portlet/announcements/entry_iterator.jspf" %>
</div>

<%
int visibleMessagesCount = total;
%>

<c:if test="<%= visibleMessagesCount > 0 %>">
	<liferay-ui:search-paginator id="pageIteratorTop" searchContainer="<%= searchContainer %>" type="article" />
</c:if>

<%
flagValue = AnnouncementsFlagConstants.HIDDEN;

searchContainer = new SearchContainer(renderRequest, null, null, "cur2", pageDelta, portletURL, null, "no-entries-were-found");
%>

<div class="read-entries">
	<%@ include file="/html/portlet/announcements/entry_iterator.jspf" %>
</div>

<%
int hiddenMessagesCount = total;
%>

<c:if test="<%= hiddenMessagesCount > 0 %>">
	<liferay-ui:search-paginator id="pageIteratorBottom" searchContainer="<%= searchContainer %>" type="article" />
</c:if>

<%
if (((hiddenMessagesCount == 0) && (visibleMessagesCount == 0)) && portletName.equals(PortletKeys.ALERTS) && !AnnouncementsEntryPermission.contains(permissionChecker, layout, PortletKeys.ALERTS, ActionKeys.ADD_ENTRY)) {
	renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.TRUE);
}
%>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />handleEntry',
		function(entryId) {
			var A = AUI();

			var entry = A.one('#<portlet:namespace />' + entryId);

			if (entry) {
				var container = entry.get('parentNode');

				if (container) {
					if (container.hasClass('unread-entries')) {
						<portlet:namespace />markEntry(entry, entryId);
					}
					else {
						<portlet:namespace />toggleContent(entry);
					}
				}
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />markEntry',
		function(entry, entryId) {
			var A = AUI();

			Liferay.Service.Announcements.AnnouncementsFlag.addFlag({entryId : entryId, flag: <%= AnnouncementsFlagConstants.HIDDEN %>});

			Liferay.Portlet.refresh('#p_p_id<portlet:namespace />');
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />toggleContent',
		function(entry) {
			var A = AUI();

			var content = entry.all('.entry-content');
			var control = entry.all('.control-entry a');

			if (entry.hasClass('visible')) {
				entry.removeClass('visible');

				if (content) {
					content.hide();
				}

				if (control) {
					control.html('<%= UnicodeLanguageUtil.get(pageContext, "show") %>');
				}
			}
			else {
				entry.addClass('visible');

				if (content) {
					content.show();
				}

				if (control) {
					control.html('<%= UnicodeLanguageUtil.get(pageContext, "hide") %>');
				}
			}
		},
		['aui-base']
	);
</aui:script>