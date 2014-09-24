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
String tabs1 = ParamUtil.getString(request, "tabs1", "timeline");

int cur = ParamUtil.getInteger(request, SearchContainer.DEFAULT_CUR_PARAM);

String redirect = ParamUtil.getString(request, "redirect");

long receiverUserId = ParamUtil.getLong(request, "receiverUserId");
long receiverMicroblogsEntryId = ParamUtil.getLong(request, "receiverMicroblogsEntryId");

String assetTagName = ParamUtil.getString(request, "assetTagName");

boolean userPublicPage = false;

Group group = themeDisplay.getScopeGroup();

if (group.isUser() && layout.isPublicLayout()) {
	userPublicPage = true;
}

String tabs1Names = "timeline,mentions";

if (!tabs1.equals("mentions") && !tabs1.equals("timeline")) {
	tabs1Names += "," + tabs1;

	assetTagName = StringUtil.toLowerCase(tabs1);
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.NORMAL);

portletURL.setParameter("mvcPath", "/microblogs/view.jsp");
portletURL.setParameter("tabs1", tabs1);
%>

<div class="microblogs-container">
	<c:if test="<%= MicroblogsPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ENTRY) && !userPublicPage %>">
		<liferay-util:include page="/microblogs/edit_microblogs_entry.jsp" servletContext="<%= application %>" />
	</c:if>

	<liferay-ui:tabs
		names="<%= tabs1Names %>"
		param="tabs1"
		url="<%= portletURL.toString() %>"
	/>

	<%
	SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, 10, portletURL, null, null);

	searchContainer.setDeltaConfigurable(false);

	List<MicroblogsEntry> results = new ArrayList<MicroblogsEntry>();
	int total = 0;

	if (tabs1.equals("mentions")) {
		receiverUserId = themeDisplay.getUserId();

		if (userPublicPage) {
			receiverUserId = group.getClassPK();
		}

		try {
			User taggedUser = UserLocalServiceUtil.getUserById(receiverUserId);

			assetTagName = taggedUser.getScreenName();
		}
		catch (NoSuchUserException nsue) {
		}

		total = MicroblogsEntryServiceUtil.getMicroblogsEntriesCount(assetTagName);

		searchContainer.setTotal(total);

		results = MicroblogsEntryServiceUtil.getMicroblogsEntries(assetTagName, searchContainer.getStart(), searchContainer.getEnd());
	}
	else if (receiverMicroblogsEntryId > 0) {
		MicroblogsEntry microblogsEntry = MicroblogsEntryLocalServiceUtil.fetchMicroblogsEntry(receiverMicroblogsEntryId);

		if (microblogsEntry != null) {
			results.add(microblogsEntry);
			total = 1;
		}

		portletURL.setParameter("receiverMicroblogsEntryId", String.valueOf(receiverMicroblogsEntryId));
	}
	else if ((receiverUserId > 0) && (receiverUserId == themeDisplay.getUserId())) {
		total = MicroblogsEntryLocalServiceUtil.getUserMicroblogsEntriesCount(receiverUserId);

		searchContainer.setTotal(total);

		results = MicroblogsEntryLocalServiceUtil.getUserMicroblogsEntries(receiverUserId, searchContainer.getStart(), searchContainer.getEnd());

		portletURL.setParameter("receiverUserId", String.valueOf(receiverUserId));
	}
	else if (receiverUserId > 0) {
		total = MicroblogsEntryServiceUtil.getUserMicroblogsEntriesCount(receiverUserId);

		searchContainer.setTotal(total);

		results = MicroblogsEntryServiceUtil.getUserMicroblogsEntries(receiverUserId, searchContainer.getStart(), searchContainer.getEnd());

		portletURL.setParameter("receiverUserId", String.valueOf(receiverUserId));
	}
	else if (Validator.isNotNull(assetTagName)) {
		total = MicroblogsEntryServiceUtil.getMicroblogsEntriesCount(assetTagName);

		searchContainer.setTotal(total);

		results = MicroblogsEntryServiceUtil.getMicroblogsEntries(assetTagName, searchContainer.getStart(), searchContainer.getEnd());

		portletURL.setParameter("assetTagName", String.valueOf(assetTagName));
	}
	else if (tabs1.equals("timeline")) {
		if (userPublicPage) {
			total = MicroblogsEntryServiceUtil.getUserMicroblogsEntriesCount(group.getClassPK());

			searchContainer.setTotal(total);

			results = MicroblogsEntryServiceUtil.getUserMicroblogsEntries(group.getClassPK(), searchContainer.getStart(), searchContainer.getEnd());
		}
		else {
			total = MicroblogsEntryServiceUtil.getMicroblogsEntriesCount();

			searchContainer.setTotal(total);

			results = MicroblogsEntryServiceUtil.getMicroblogsEntries(searchContainer.getStart(), searchContainer.getEnd());
		}
	}

	searchContainer.setResults(results);

	PortletURL microblogsEntriesURL = renderResponse.createRenderURL();

	microblogsEntriesURL.setWindowState(LiferayWindowState.EXCLUSIVE);

	microblogsEntriesURL.setParameter("mvcPath", "/microblogs/view.jsp");
	microblogsEntriesURL.setParameter("tabs1", tabs1);
	microblogsEntriesURL.setParameter("cur", String.valueOf(cur));

	request.setAttribute(WebKeys.MICROBLOGS_ENTRIES, results);
	request.setAttribute(WebKeys.MICROBLOGS_ENTRIES_URL, microblogsEntriesURL);
	%>

	<liferay-util:include page="/microblogs/view_microblogs_entries.jsp" servletContext="<%= application %>" />

	<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" type="article" />
</div>

<aui:script use="aui-base,aui-io-deprecated">
	AUI().ready(
		function() {
			Liferay.Microblogs.init(
				{
					baseActionURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), themeDisplay.getPlid(), PortletRequest.ACTION_PHASE) %>',
					microblogsEntriesURL: '<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/microblogs/view.jsp" /><portlet:param name="tabs1" value="timeline" /></portlet:renderURL>'
				}
			);

			Liferay.Microblogs.updateViewCount(<%= receiverMicroblogsEntryId %>);
		}
	);

	var microblogsContainer = A.one('#p_p_id<portlet:namespace /> .microblogs-container');

	var showComments = function(microblogsEntryId) {
		var uri = '<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/microblogs/view_comments.jsp" /></portlet:renderURL>';

		uri = Liferay.Util.addParams('<portlet:namespace />receiverMicroblogsEntryId=' + microblogsEntryId, uri) || uri;

		var commentsContainer = A.one('#<portlet:namespace />commentsContainer' + microblogsEntryId);

		var commentsContainerContent = commentsContainer.one('.comments-container-content');

		if (!commentsContainerContent) {
			if (!commentsContainer.io) {
				commentsContainer.plug(
					A.Plugin.IO,
					{
						autoLoad: false,
						method: 'POST'
					}
				);
			}

			commentsContainer.io.set('uri', uri);

			commentsContainer.io.start();
		}

		var microblogsEntry = microblogsContainer.one('#<portlet:namespace />microblogsEntry' + microblogsEntryId);

		microblogsEntry.toggleClass('show-comments');
	}

	microblogsContainer.delegate(
		'click',
		function(event) {
			event.preventDefault();

			showComments(event.currentTarget.getAttribute('data-microblogsEntryId'));
		},
		'.microblogs-entry .comment a'
	);

	microblogsContainer.delegate(
		'click',
		function(event) {
			event.preventDefault();

			var uri = event.currentTarget.getAttribute('href');

			var microblogsEntryId = event.currentTarget.getAttribute('data-microblogsEntryId');

			var microblogsEntry = A.one('#<portlet:namespace />microblogsEntry' + microblogsEntryId);

			var editContainer = microblogsEntry.one('.edit-container');

			var editForm = editContainer.one('#<portlet:namespace />fm' + microblogsEntryId);

			if (!editForm) {
				if (!editContainer.io) {
					editContainer.plug(
						A.Plugin.IO,
						{
							autoLoad: false,
							method: 'GET'
						}
					);
				}

				editContainer.io.set('uri', uri);
				editContainer.io.start();
			}
			else {
				editForm.toggle();
			}

			var content = microblogsEntry.one('.content');

			content.toggle();
		},
		'.microblogs-entry .edit a'
	);

	microblogsContainer.delegate(
		'click',
		function(event) {
			event.preventDefault();

			if (confirm('Are you sure you want to delete this post?')) {

				A.io.request(
					event.currentTarget.getAttribute('href'),
					{
						on: {
							success: function(event, id, obj) {
								var updateContainer = A.one('#p_p_id<portlet:namespace /> .portlet-body');

								Liferay.Microblogs.updateMicroblogsList('<%= microblogsEntriesURL %>', updateContainer);
							}
						}
					}
				);
			}
		},
		'.microblogs-entry .delete a'
	);

	<c:if test="<%= receiverMicroblogsEntryId > 0 %>">
		showComments('<%= receiverMicroblogsEntryId %>');
	</c:if>
</aui:script>