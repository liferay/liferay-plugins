<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String tabs1 = ParamUtil.getString(request, "tabs1", "timeline");

long receiverUserId = ParamUtil.getLong(request, "receiverUserId");
String tagName = ParamUtil.getString(request, "tagName");

String tabs1Names = "timeline,mentions";

if (receiverUserId > 0 || Validator.isNotNull(tagName)) {
	tabs1Names += "," + tabs1;
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.NORMAL);

portletURL.setParameter("jspPage", "/microblogs/view.jsp");
portletURL.setParameter("tabs1", tabs1);
%>

<c:if test="<%= MicroblogsPermission.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS) %>">
	<liferay-security:permissionsURL
		modelResource="com.liferay.microblogs"
		modelResourceDescription="<%= HtmlUtil.escape(themeDisplay.getScopeGroupName()) %>"
		resourcePrimKey="<%= String.valueOf(scopeGroupId) %>"
		var="permissionsURL"
	/>

	<div class="control-container">
		<aui:button onClick="<%= permissionsURL %>" value="permissions" />
	</div>
</c:if>

<portlet:actionURL name="updateMicroblogsEntry" var="updateMicroblogsEntryURL" />

<c:if test="<%= MicroblogsPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ENTRY) %>">
	<liferay-util:include page="/microblogs/edit_microblogs_entry.jsp" portletId="<%= portletDisplay.getId() %>" />
</c:if>

<liferay-ui:tabs
	names="<%= tabs1Names %>"
	param="tabs1"
	url="<%= portletURL.toString() %>"
/>

<div class="microblogs-container">

	<%
	SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, delta, portletURL, null, null);

	searchContainer.setDelta(delta);
	searchContainer.setDeltaConfigurable(false);

	List<MicroblogsEntry> results = null;
	int total = 0;

	if (tabs1.equals("timeline")) {
		List<User> followingUsers = UserLocalServiceUtil.getSocialUsers(themeDisplay.getUserId(), SocialRelationConstants.TYPE_UNI_FOLLOWER, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new UserLoginDateComparator());

		long[] userIds = new long[followingUsers.size() + 1];

		for (int i = 0; i < followingUsers.size(); i++) {
			User followingUser = followingUsers.get(i);

			userIds[i] = followingUser.getUserId();
		}

		userIds[userIds.length - 1] = themeDisplay.getUserId();

		results = MicroblogsEntryServiceUtil.getMicroblogsEntries(companyId, userIds, 0, 0, 0, 0, themeDisplay.getUserId(), searchContainer.getStart(), searchContainer.getEnd());
		total = MicroblogsEntryServiceUtil.getMicroblogsEntriesCount(companyId, userIds, 0, 0, 0, 0, themeDisplay.getUserId());
	}
	else if (tabs1.equals("mentions")) {
		Group group = themeDisplay.getScopeGroup();

		if (group.isUser()) {
			receiverUserId = group.getClassPK();
		}

		results = MicroblogsEntryServiceUtil.getMicroblogsEntries(companyId, null, MicroblogsEntryConstants.REPLY, receiverUserId, 0, 0, themeDisplay.getUserId(), searchContainer.getStart(), searchContainer.getEnd());
		total = MicroblogsEntryServiceUtil.getMicroblogsEntriesCount(companyId, null, MicroblogsEntryConstants.REPLY, receiverUserId, 0, 0, themeDisplay.getUserId());
	}
	else if (receiverUserId > 0) {
		results = MicroblogsEntryServiceUtil.getMicroblogsEntries(companyId, null, 0, receiverUserId, 0, 0, themeDisplay.getUserId(), searchContainer.getStart(), searchContainer.getEnd());
		total = MicroblogsEntryServiceUtil.getMicroblogsEntriesCount(companyId, null, 0, receiverUserId, 0, 0, themeDisplay.getUserId());

		portletURL.setParameter("receiverUserId", String.valueOf(receiverUserId));
	}
	else if (Validator.isNotNull(tagName)) {
		results = MicroblogsEntryServiceUtil.getMicroblogsEntriesByTag(tagName, themeDisplay.getUserId(), searchContainer.getStart(), searchContainer.getEnd());
		total = MicroblogsEntryServiceUtil.getMicroblogsEntriesCountByTag(tagName, themeDisplay.getUserId());

		portletURL.setParameter("tagName", String.valueOf(tagName));
	}

	searchContainer.setResults(results);
	searchContainer.setTotal(total);

	request.setAttribute(WebKeys.MICROBLOGS_ENTRIES, results);
	request.setAttribute(WebKeys.MICROBLOGS_ENTRIES_COUNT, new Integer(total));
	request.setAttribute(WebKeys.MICROBLOGS_URL, portletURL);
	%>

	<liferay-util:include page="/microblogs/view_microblogs_entries.jsp" portletId="<%= portletDisplay.getId() %>" />

	<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" type="article" />
</div>

<aui:script use="aui-base">
	AUI().ready(
		function() {
			Liferay.Microblogs.init(
				{
					microblogsEntriesURL: '<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="jspPage" value="/microblogs/view.jsp" /><portlet:param name="tabs1" value="timeline" /></portlet:renderURL>'
				}
			);
		}
	);
</aui:script>