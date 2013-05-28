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
String distributionScope = ParamUtil.getString(request, "distributionScope");

long classNameId = -1;
long classPK = -1;

String[] distributionScopeArray = StringUtil.split(distributionScope);

if (distributionScopeArray.length == 2) {
	classNameId = GetterUtil.getLong(distributionScopeArray[0]);
	classPK = GetterUtil.getLong(distributionScopeArray[1]);
}

if ((classNameId == 0) && (classPK == 0) && !permissionChecker.isOmniadmin()) {
	throw new PrincipalException();
}
%>

<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">

	<liferay-ui:success key="announcementAdded" message="the-announcement-was-successfully-added" />
	<liferay-ui:success key="announcementDeleted" message="the-announcement-was-successfully-deleted" />
	<liferay-ui:success key="announcementUpdated" message="the-announcement-was-successfully-updated" />

	<aui:fieldset cssClass="distribution-scope-container">

		<%
		boolean submitOnChange = true;
		%>

		<%@ include file="/entry_select_scope.jspf" %>

	</aui:fieldset>

	<portlet:renderURL var="addEntryURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="mvcPath" value="/edit_entry.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
	</portlet:renderURL>

	<aui:button onClick='<%= renderResponse.getNamespace() + "manageAddEntry();" %>' value="add-entry" />

	<c:if test="<%= Validator.isNotNull(distributionScope) %>">
		<div class="separator"><!-- --></div>

		<%
		PortletURL iteratorURL = PortletURLUtil.clone(portletURL, renderResponse);

		iteratorURL.setParameter("distributionScope", distributionScope);

		iteratorURL.setParameter("mvcPath", "/manage_entries.jsp");

		List<String> headerNames = new ArrayList<String>();

		headerNames.add("title");
		headerNames.add("author");
		headerNames.add("type");
		headerNames.add("modified-date");
		headerNames.add("display-date");
		headerNames.add("expiration-date");
		headerNames.add(StringPool.BLANK);

		SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, iteratorURL, headerNames, "no-entries-were-found");

		int total = AnnouncementsEntryLocalServiceUtil.getEntriesCount(classNameId, classPK, portletName.equals(PortletKeys.ALERTS));

		searchContainer.setTotal(total);

		List<AnnouncementsEntry> results = AnnouncementsEntryLocalServiceUtil.getEntries(classNameId, classPK, portletName.equals(PortletKeys.ALERTS), searchContainer.getStart(), searchContainer.getEnd());

		searchContainer.setResults(results);

		List resultRows = searchContainer.getResultRows();

		for (int i = 0; i < results.size(); i++) {
			AnnouncementsEntry entry = results.get(i);

			entry = entry.toEscapedModel();

			User entryUser = null;

			try {
				entryUser = UserLocalServiceUtil.getUserById(entry.getUserId());
			}
			catch (Exception e) {
			}

			ResultRow row = new ResultRow(entry, entry.getEntryId(), i);
		%>

			<portlet:renderURL var="editURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcPath" value="/edit_entry.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="fromManageEntries" value="true" />
				<portlet:param name="entryId" value="<%= String.valueOf(entry.getEntryId()) %>" />
			</portlet:renderURL>

		<%

			// Title

			row.addText(entry.getTitle(), editURL);

			// Author

			row.addText(entryUser.getFullName());

			// Type

			row.addText(LanguageUtil.get(pageContext, entry.getType()));

			// Modified date

			row.addText(dateFormatDate.format(entry.getModifiedDate()));

			// Display date

			row.addText(dateFormatDate.format(entry.getDisplayDate()));

			// Expiration date

			row.addText(dateFormatDate.format(entry.getExpirationDate()));

			// Action

			row.addJSP("/view_manage_entries_entry_action.jsp", application, request, response);

			// Add result row

			resultRows.add(row);
		}
		%>

		<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
	</c:if>
</aui:form>

<%
	portletURL.setParameter("windowState", LiferayWindowState.POP_UP.toString());
	portletURL.setParameter("mvcPath", "/manage_entries.jsp");

	PortletURL addEntryURL = PortletURLUtil.clone(portletURL, renderResponse);
	addEntryURL.setParameter("fromManageEntries", "true");
	addEntryURL.setParameter("mvcPath", "/edit_entry.jsp");
	addEntryURL.setParameter("redirect", currentURL);
	addEntryURL.setParameter("windowState", LiferayWindowState.POP_UP.toString());
%>

<aui:script>
	function <portlet:namespace />selectDistributionScope(distributionScope) {
		var url = "<%= portletURL.toString() %>&<portlet:namespace />distributionScope=" + distributionScope;
		submitForm(document.<portlet:namespace />fm, url);
	}

	function <portlet:namespace />manageAddEntry() {
		var A = AUI();
		var optValue = A.one('select[name="<portlet:namespace />distributionScope"]').get('value');
		var addEntryURL = "<%= addEntryURL.toString() %>&distributionScope=" + optValue;

		window.location = addEntryURL;
	}
</aui:script>