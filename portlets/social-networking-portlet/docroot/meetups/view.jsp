<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
String tabs1 = ParamUtil.getString(request, "tabs1", "all-meetups");

List<MeetupsEntry> meetupsEntries = null;

if (tabs1.equals("all-meetups")) {
	meetupsEntries = MeetupsEntryLocalServiceUtil.getMeetupsEntriesByCompany(themeDisplay.getCompanyId());
}
else if (tabs1.equals("my-meetups")) {
	meetupsEntries = MeetupsEntryLocalServiceUtil.getMeetupsEntriesByUser(PortalUtil.getUserId(request));
}
%>

<c:if test="<%= permissionChecker.isCompanyAdmin(company.getCompanyId()) %>">

	<%
	PortletURL portletURL = renderResponse.createRenderURL();
	%>

	<liferay-ui:tabs
		names="all-meetups,my-meetups"
		param="tabs1"
		url="<%= portletURL.toString() %>"
	/>

	<%
	PortletURL addMeetupsEntryURL = renderResponse.createRenderURL();

	addMeetupsEntryURL.setWindowState(WindowState.MAXIMIZED);

	addMeetupsEntryURL.setParameter("mvcPath", "/meetups/edit_entry.jsp");
	addMeetupsEntryURL.setParameter("redirect", currentURL);
	%>

	<aui:button-row>
		<aui:button href="<%= addMeetupsEntryURL.toString() %>" primary="<%= true %>" value="add-meetup" />
	</aui:button-row>

	<br />
</c:if>

<table class="table" width="100%">

<%
for (int i = 0; i < meetupsEntries.size(); i++) {
	MeetupsEntry meetupsEntry = meetupsEntries.get(i);

	int yesTotal = MeetupsRegistrationLocalServiceUtil.getMeetupsRegistrationsCount(meetupsEntry.getMeetupsEntryId(), MeetupsConstants.STATUS_YES);
%>

	<tr>
		<td align="center" width="20%">

			<%
			PortletURL viewMeetupsEntryURL = renderResponse.createRenderURL();

			viewMeetupsEntryURL.setWindowState(WindowState.MAXIMIZED);

			viewMeetupsEntryURL.setParameter("mvcPath", "/meetups/view_entry.jsp");
			viewMeetupsEntryURL.setParameter("meetupsEntryId", String.valueOf(meetupsEntry.getMeetupsEntryId()));

			String thumbnailURL = null;

			if (meetupsEntry.getThumbnailId() == 0) {
				thumbnailURL = PortalUtil.getPathContext(request) + "/meetups/images/calendar.png";
			}
			else {
				thumbnailURL = themeDisplay.getPathImage() + "/meetups?img_id=" + meetupsEntry.getThumbnailId() + "&t=" + WebServerServletTokenUtil.getToken(meetupsEntry.getThumbnailId());
			}
			%>

			<a href="<%= viewMeetupsEntryURL %>"><img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="view-meetup" />" src="<%= thumbnailURL %>" /></a>
		</td>
		<td valign="top" width="80%">
			<div>
				<%= meetupsEntry.getTitle() %>
			</div>

			<br />

			<div>
				<%= meetupsEntry.getDescription() %>
			</div>

			<br />

			<div>
				<%= dateFormatDateTime.format(meetupsEntry.getStartDate()) %> &#150; <%= dateFormatDateTime.format(meetupsEntry.getEndDate()) %>
			</div>

			<br />

			<c:if test="<%= yesTotal > 1 %>">
				<div>
					<%= LanguageUtil.format(request, "x-people-are-planning-to-attend-this-meetup", String.valueOf(yesTotal), false) %>
				</div>

				<br />
			</c:if>

			<liferay-ui:icon-list>

				<c:if test="<%= permissionChecker.isCompanyAdmin(company.getCompanyId()) %>">

					<%
					PortletURL editMeetupsEntryURL = renderResponse.createRenderURL();

					editMeetupsEntryURL.setWindowState(WindowState.MAXIMIZED);

					editMeetupsEntryURL.setParameter("mvcPath", "/meetups/edit_entry.jsp");
					editMeetupsEntryURL.setParameter("redirect", currentURL);
					editMeetupsEntryURL.setParameter("meetupsEntryId", String.valueOf(meetupsEntry.getMeetupsEntryId()));
					%>

					<liferay-ui:icon
						iconCssClass="icon-edit"
						message="edit"
						method="get"
						url="<%= editMeetupsEntryURL.toString() %>"
					/>
				</c:if>

				<liferay-ui:icon
					iconCssClass="icon-group"
					message="register"
					method="get"
					url="<%= viewMeetupsEntryURL.toString() %>"
				/>

				<c:if test="<%= permissionChecker.isCompanyAdmin(company.getCompanyId()) %>">

					<%
					PortletURL deleteMeetupsEntryURL = renderResponse.createActionURL();

					deleteMeetupsEntryURL.setWindowState(WindowState.MAXIMIZED);

					deleteMeetupsEntryURL.setParameter(ActionRequest.ACTION_NAME, "deleteMeetupsEntry");
					deleteMeetupsEntryURL.setParameter("redirect", currentURL);
					deleteMeetupsEntryURL.setParameter("meetupsEntryId", String.valueOf(meetupsEntry.getMeetupsEntryId()));
					%>

					<liferay-ui:icon-delete
						url="<%= deleteMeetupsEntryURL.toString() %>"
					/>
				</c:if>
			</liferay-ui:icon-list>
		</td>
	</tr>

	<c:if test="<%= (i + 1) < meetupsEntries.size() %>">
		<tr>
			<td colspan="2">
				<div class="separator"><!-- --></div>
			</td>
		</tr>
	</c:if>

<%
}
%>

</table>