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
	<aui:fieldset id="fieldSet">

		<%
		boolean submitOnChange = true;
		%>

		<%@ include file="/html/portlet/announcements/entry_select_scope.jspf" %>

	</aui:fieldset>

	<c:choose>
		<c:when test="<%= distributionScopeArray.length > 0 %>">
			<aui:button onClick='<%= renderResponse.getNamespace() + "addEntry()" %>' value="add-entry" />
		</c:when>
		<c:otherwise>
			<div class="alert alert-info">
				<liferay-ui:message key="please-select-a-distribution-scope" />
			</div>
		</c:otherwise>
	</c:choose>

	<c:if test="<%= Validator.isNotNull(distributionScope) %>">
		<div class="separator"><!-- --></div>

		<%
		PortletURL iteratorURL = PortletURLUtil.clone(portletURL, renderResponse);

		iteratorURL.setParameter("distributionScope", distributionScope);

		List<String> headerNames = new ArrayList<String>();

		headerNames.add("title");
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

			ResultRow row = new ResultRow(entry, entry.getEntryId(), i);

			PortletURL rowURL = renderResponse.createRenderURL();

			rowURL.setParameter("struts_action", "/announcements/edit_entry");
			rowURL.setParameter("redirect", currentURL);
			rowURL.setParameter("entryId", String.valueOf(entry.getEntryId()));

			// Title

			row.addText(entry.getTitle(), rowURL);

			// Type

			row.addText(LanguageUtil.get(pageContext, entry.getType()), rowURL);

			// Modified date

			row.addText(dateFormatDate.format(entry.getModifiedDate()), rowURL);

			// Display date

			row.addText(dateFormatDate.format(entry.getDisplayDate()), rowURL);

			// Expiration date

			row.addText(dateFormatDate.format(entry.getExpirationDate()), rowURL);

			// Action

			row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/html/portlet/announcements/view_manage_entries_entry_action.jsp");

			// Add result row

			resultRows.add(row);
		}
		%>

		<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
	</c:if>
</aui:form>

<aui:script>
	function <portlet:namespace />addEntry() {
		location.href = '<portlet:renderURL><portlet:param name="struts_action" value="/announcements/edit_entry" /><portlet:param name="redirect" value="<%= currentURL %>" /><portlet:param name="distributionScope" value="<%= distributionScope %>" /></portlet:renderURL>';
	}

	function <portlet:namespace />selectDistributionScope(distributionScope) {
		var url = "<%= portletURL.toString() %>&<portlet:namespace />distributionScope=" + distributionScope;
		submitForm(document.<portlet:namespace />fm, url);
	}
</aui:script>