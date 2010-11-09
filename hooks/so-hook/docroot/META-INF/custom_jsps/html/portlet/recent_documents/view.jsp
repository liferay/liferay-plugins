<%--
/**
 * Copyright (c) 2008-2010 Liferay, Inc. All rights reserved.
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
--%>

<%@ include file="/html/portlet/recent_documents/init.jsp" %>

<%
List<DLFileEntry> fileEntries = DLAppLocalServiceUtil.getGroupFileEntries(themeDisplay.getScopeGroupId(), 0, SearchContainer.DEFAULT_DELTA);
%>

<c:choose>
	<c:when test="<%= fileEntries.isEmpty() %>">
		<liferay-ui:message key="there-are-no-recent-documents" />
	</c:when>
	<c:otherwise>
		<table class="lfr-table">

		<%
		for (DLFileEntry fileEntry : fileEntries) {
			fileEntry = fileEntry.toEscapedModel();

			PortletURL rowURL = renderResponse.createActionURL();

			rowURL.setWindowState(LiferayWindowState.EXCLUSIVE);

			rowURL.setParameter("struts_action", "/recent_documents/get_file");
			rowURL.setParameter("folderId", String.valueOf(fileEntry.getFolderId()));
			rowURL.setParameter("name", HtmlUtil.unescape(fileEntry.getName()));
		%>

			<tr>
				<td>
					<a href="<%= rowURL.toString() %>"><img align="left" border="0" src="<%= themeDisplay.getPathThemeImages() %>/file_system/small/<%= fileEntry.getIcon() %>.png" /><%= fileEntry.getTitle() %></a>
				</td>
			</tr>

		<%
		}
		%>

		</table>
	</c:otherwise>
</c:choose>