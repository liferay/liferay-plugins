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

long receiverMicroblogsEntryId = ParamUtil.getLong(request, "receiverMicroblogsEntryId");
long receiverUserId = ParamUtil.getLong(request, "receiverUserId");

String tabs1 = ParamUtil.getString(request, "tabs1", "timeline");
String tabs1Names = "timeline,mentions";
String assetTagName = ParamUtil.getString(request, "assetTagName");

if (!tabs1.equals("timeline")) {
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
		<aui:button href="<%= permissionsURL %>" value="permissions" />
	</div>
</c:if>

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
	SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, 10, portletURL, null, null);

	searchContainer.setDeltaConfigurable(false);

	List<MicroblogsEntry> results = new ArrayList<MicroblogsEntry>();
	int total = 0;

	if (tabs1.equals("timeline")) {
		long microblogsEntryUserId = themeDisplay.getUserId();

		Group group = themeDisplay.getScopeGroup();

		if (group.isUser()) {
			microblogsEntryUserId = group.getClassPK();
		}

		if (microblogsEntryUserId == themeDisplay.getUserId()) {
			results = MicroblogsEntryServiceUtil.getMicroblogsEntries(searchContainer.getStart(), searchContainer.getEnd());
			total = MicroblogsEntryServiceUtil.getMicroblogsEntriesCount();
		}
		else {
			results = MicroblogsEntryServiceUtil.getUserMicroblogsEntries(microblogsEntryUserId, searchContainer.getStart(), searchContainer.getEnd());
			total = MicroblogsEntryServiceUtil.getUserMicroblogsEntriesCount(microblogsEntryUserId);
		}
	}
	else if (tabs1.equals("mentions")) {
		receiverUserId = themeDisplay.getUserId();

		Group group = themeDisplay.getScopeGroup();

		if (group.isUser()) {
			receiverUserId = group.getClassPK();
		}

		results = MicroblogsEntryLocalServiceUtil.getReceiverUserMicroblogsEntries(MicroblogsEntryConstants.TYPE_REPLY, receiverUserId, searchContainer.getStart(), searchContainer.getEnd());
		total = MicroblogsEntryLocalServiceUtil.getReceiverUserMicroblogsEntriesCount(MicroblogsEntryConstants.TYPE_REPLY, receiverUserId);
	}
	else if (receiverMicroblogsEntryId > 0) {
		results.addAll(MicroblogsEntryLocalServiceUtil.getReceiverMicroblogsEntryMicroblogsEntries(MicroblogsEntryConstants.TYPE_REPLY, receiverMicroblogsEntryId, searchContainer.getStart(), searchContainer.getEnd(), new MicroblogsEntryCreateDateComparator(true)));
		total = MicroblogsEntryLocalServiceUtil.getReceiverMicroblogsEntryMicroblogsEntriesCount(MicroblogsEntryConstants.TYPE_REPLY, receiverMicroblogsEntryId);

		try {
			MicroblogsEntry receiverMicroblogsEntry = MicroblogsEntryServiceUtil.getMicroblogsEntry(receiverMicroblogsEntryId);
			results.add(0, receiverMicroblogsEntry);
			total++;
		}
		catch (NoSuchEntryException nsee) {
		}

		portletURL.setParameter("receiverMicroblogsEntryId", String.valueOf(receiverMicroblogsEntryId));
	}
	else if (receiverUserId > 0) {
		results = MicroblogsEntryServiceUtil.getUserMicroblogsEntries(receiverUserId, searchContainer.getStart(), searchContainer.getEnd());
		total = MicroblogsEntryServiceUtil.getUserMicroblogsEntriesCount(receiverUserId);

		portletURL.setParameter("assetTagName", String.valueOf(receiverUserId));
	}
	else if (Validator.isNotNull(assetTagName)) {
		results = MicroblogsEntryServiceUtil.getMicroblogsEntries(assetTagName, searchContainer.getStart(), searchContainer.getEnd());
		total = MicroblogsEntryServiceUtil.getMicroblogsEntriesCount(assetTagName);

		portletURL.setParameter("assetTagName", String.valueOf(assetTagName));
	}

	searchContainer.setResults(results);
	searchContainer.setTotal(total);

	request.setAttribute(WebKeys.MICROBLOGS_ENTRIES, results);
	request.setAttribute(WebKeys.MICROBLOGS_ENTRIES_URL, portletURL);
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