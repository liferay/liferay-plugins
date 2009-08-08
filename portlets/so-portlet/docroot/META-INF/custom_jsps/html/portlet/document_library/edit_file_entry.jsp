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

<%@ include file="/html/portlet/document_library/init.jsp" %>

<%
String strutsAction = ParamUtil.getString(request, "struts_action");

String redirect = ParamUtil.getString(request, "redirect");

DLFileEntry fileEntry = (DLFileEntry)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_FILE_ENTRY);
%>

<liferay-util:include page="/html/portlet/document_library/sidebar.jsp" />

<liferay-util:buffer var="html">
	<liferay-util:include page="/html/portlet/document_library/edit_file_entry.portal.jsp" />
</liferay-util:buffer>

<c:choose>
	<c:when test='<%= strutsAction.equals("/document_library/view_file_entry") %>'>

		<%
		int x = html.indexOf("<div class=\"breadcrumbs\">");
		int y = html.indexOf("<span class=\"last\">", x);
		int z = html.indexOf("</span>", y);
		%>

		<%= html.substring(0, y) %>

		<span class="last"><liferay-ui:message key="view-file-entry" /></span>

		<%= html.substring(z + 7) %>
	</c:when>
	<c:otherwise>
		<%= html %>
	</c:otherwise>
</c:choose>

<c:if test='<%= DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE) && strutsAction.equals("/document_library/view_file_entry") %>'>
	<br />

	<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="editURL">
		<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
		<portlet:param name="redirect" value="<%= redirect %>" />
		<portlet:param name="folderId" value="<%= String.valueOf(fileEntry.getFolderId()) %>" />
		<portlet:param name="name" value="<%= HtmlUtil.unescape(fileEntry.getName()) %>" />
	</portlet:renderURL>

	<input type="button" value="<liferay-ui:message key="edit" />" onClick="location = '<%= editURL %>';" />
</c:if>

<script type="text/javascript">
	var folderLinks = jQuery('.portlet-document-library .breadcrumbs a');

	folderLinks.each(
		function(i) {
			var folderLink = jQuery(this);

			var folderURL = this.href;

			folderLink.attr(
				{
					'href': folderURL.replace('&p_p_state=maximized', '&p_p_state=normal')
				}
			);
		}
	);
</script>