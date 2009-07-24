<%
/**
 * Copyright (c) 2008-2009 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */
%>

<%@ include file="/html/portlet/search/init.jsp" %>

<%
Portlet dlPortlet = PortletLocalServiceUtil.getPortletById(PortletKeys.DOCUMENT_LIBRARY);

String dlPortletTitle = PortalUtil.getPortletTitle(dlPortlet, application, locale);
%>

<liferay-util:buffer var="html">
	<liferay-util:include page="/html/portlet/search/search.portal.jsp" />
</liferay-util:buffer>

<%
int x = html.indexOf("<div class=\"add-search-provider\">");
int y = html.indexOf("</div>", x);

if (x != -1) {
	html = StringUtil.remove(html, html.substring(x, y + 6), StringPool.BLANK);
}
%>

<%
String[] htmlFragments = StringUtil.split(html, "<div class=\"section-title\">");

for (int i = 0; i < htmlFragments.length; i++) {
	String htmlFragment = htmlFragments[i];

	String portletTitle = StringPool.BLANK;

	if (i != 0) {
		htmlFragment = "<div class=\"section-title\">" + htmlFragment;

		portletTitle = htmlFragment.substring(27, htmlFragment.indexOf(StringPool.OPEN_PARENTHESIS)).trim();
	}
%>

	<c:choose>
		<c:when test="<%= portletTitle.equals(dlPortletTitle) %>">

			<%
			String[] entryHtmlFragments = StringUtil.split(htmlFragment, "<a class=\"entry-title\"");

			for (int j = 0; j < entryHtmlFragments.length; j++) {
				String entryHtmlFragment = entryHtmlFragments[j];

				String oldURL = null;

				if (j != 0) {
					entryHtmlFragment = "<a class=\"entry-title\"" + entryHtmlFragment;

					x = entryHtmlFragment.indexOf("href=\"");
					y = entryHtmlFragment.indexOf("\"", x + 6);

					oldURL = entryHtmlFragment.substring(x + 6, y);
				}

				if (oldURL != null) {
					long folderId = GetterUtil.getLong(HttpUtil.getParameter(oldURL, "_20_folderId", false));
					String name = GetterUtil.getString(HttpUtil.getParameter(oldURL, "_20_name", false));

					DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(folderId, name);

					long dlPlid = PortalUtil.getPlidFromPortletId(fileEntry.getGroupId(), PortletKeys.DOCUMENT_LIBRARY);

					PortletURL viewFolderURL = new PortletURLImpl(request, PortletKeys.DOCUMENT_LIBRARY, dlPlid, PortletRequest.RENDER_PHASE);

					viewFolderURL.setParameter("struts_action", "/document_library/view");
					viewFolderURL.setParameter("folderId", String.valueOf(fileEntry.getFolderId()));

					entryHtmlFragment = StringUtil.replace(entryHtmlFragment, oldURL, viewFolderURL.toString());
				}
			%>

				<%= entryHtmlFragment %>

			<%
			}
			%>

		</c:when>
		<c:otherwise>
			<%= htmlFragment %>
		</c:otherwise>
	</c:choose>

<%
}
%>