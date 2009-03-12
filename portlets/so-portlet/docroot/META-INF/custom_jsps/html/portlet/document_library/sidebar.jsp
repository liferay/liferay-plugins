<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ include file="/html/portlet/document_library/init.jsp" %>

<div class="sidebar">
	<div class="search">

		<%
		String redirect = ParamUtil.getString(request, "redirect");

		long breadcrumbsFolderId = ParamUtil.getLong(request, "breadcrumbsFolderId");

		long searchFolderId = ParamUtil.getLong(request, "searchFolderId");
		long searchFolderIds = ParamUtil.getLong(request, "searchFolderIds");

		long[] folderIdsArray = null;

		if (searchFolderId > 0) {
			folderIdsArray = new long[] {searchFolderId};
		}
		else {
			List folderIds = new ArrayList();

			folderIds.add(new Long(searchFolderIds));

			DLFolderLocalServiceUtil.getSubfolderIds(folderIds, scopeGroupId, searchFolderIds);

			folderIdsArray = StringUtil.split(StringUtil.merge(folderIds), 0L);
		}

		String keywords = ParamUtil.getString(request, "keywords");
		%>

		<liferay-portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" varImpl="sideSearchURL"><portlet:param name="struts_action" value="/document_library/search" /></liferay-portlet:renderURL>

		<form action="<%= sideSearchURL %>" method="get" name="<portlet:namespace />sidebarFm" onSubmit="submitForm(this); return false;">
		<liferay-portlet:renderURLParams varImpl="sideSearchURL" />
		<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
		<input name="<portlet:namespace />breadcrumbsFolderId" type="hidden" value="<%= breadcrumbsFolderId %>" />
		<input name="<portlet:namespace />searchFolderId" type="hidden" value="<%= searchFolderId %>" />
		<input name="<portlet:namespace />searchFolderIds" type="hidden" value="<%= searchFolderIds %>" />

		<div class="search">
			<input class="search-input" id="<portlet:namespace />keywords1" name="<portlet:namespace />keywords" type="text" />

			<input class="search-button" type="submit" value="<liferay-ui:message key="search" />" />
		</div>

		</form>
	</div>

	<h2>
		<liferay-ui:message key="quick-links" />
	</h2>

	<%
	String tabs1 = ParamUtil.getString(request, "tabs1", "folders");

	PortletURL tabs1URL = renderResponse.createRenderURL();

	tabs1URL.setParameter("tabs1", "folders");
	%>

	<ul class="disc">
		<li>
			<a href="<%= tabs1URL %>"><liferay-ui:message key="documents-home" /></a>
		</li>

		<%
		tabs1URL.setParameter("tabs1", "recent-documents");
		%>

		<li>
			<a href="<%= tabs1URL %>"><liferay-ui:message key="recent-documents" /></a>
		</li>

		<%
		if (themeDisplay.isSignedIn()) {
			tabs1URL.setParameter("tabs1", "my-documents");
		%>

			<li>
				<a href="<%= tabs1URL %>"><liferay-ui:message key="my-documents" /></a>
			</li>

		<%
		}
		%>

	</ul>

	<%
	int total = DLFileEntryLocalServiceUtil.getGroupFileEntriesCount(portletGroupId, 0);

	if (total > 0) {
		List<DLFileEntry> results = DLFileEntryLocalServiceUtil.getGroupFileEntries(portletGroupId, 0, 0, 5);
	%>

		<h2>
			<liferay-ui:message key="recent-documents" />
		</h2>

		<%@ include file="/html/portlet/document_library/sidebar_file_entries.jspf" %>

	<%
	}

	total = DLFileEntryLocalServiceUtil.getGroupFileEntriesCount(portletGroupId, user.getUserId());

	if (total > 0) {
		List<DLFileEntry> results = DLFileEntryLocalServiceUtil.getGroupFileEntries(portletGroupId, user.getUserId(), 0, 5);
	%>

		<h2>
			<liferay-ui:message key="my-documents" />
		</h2>

		<%@ include file="/html/portlet/document_library/sidebar_file_entries.jspf" %>

	<%
	}
	%>

</div>