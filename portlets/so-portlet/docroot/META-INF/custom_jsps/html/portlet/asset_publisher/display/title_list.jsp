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

<%@ include file="/html/portlet/asset_publisher/init.jsp" %>

<%
String className = (String)request.getAttribute("view.jsp-className");
long classPK = ((Long)request.getAttribute("view.jsp-classPK")).longValue();
%>

<liferay-util:buffer var="html">
	<liferay-util:include page="/html/portlet/asset_publisher/display/title_list.portal.jsp" />
</liferay-util:buffer>

<c:choose>
	<c:when test="<%= className.equals(DLFileEntry.class.getName()) %>">

		<%
		int x = html.indexOf("<li");
		int y = html.indexOf(">", x);
		int z = html.lastIndexOf("</li>");

		DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(classPK);

		long dlPlid = PortalUtil.getPlidFromPortletId(fileEntry.getGroupId(), PortletKeys.DOCUMENT_LIBRARY);

		PortletURL viewFolderURL = new PortletURLImpl(request, PortletKeys.DOCUMENT_LIBRARY, dlPlid, PortletRequest.RENDER_PHASE);

		viewFolderURL.setParameter("struts_action", "/document_library/view");
		viewFolderURL.setParameter("folderId", String.valueOf(fileEntry.getFolderId()));
		%>

		<%= html.substring(0, y + 1) %>

		<div id="<portlet:namespace />file-entry_<%= classPK %>">
			<%= html.substring(y + 1, z) %>
		</div>
		<div class="document-folder">
			<a href="<%= viewFolderURL.toString() %>"><liferay-ui:message key="go-to-folder" /></a>
		</div>

		<%= html.substring(z) %>

		<script type="text/javascript">
			var titleLink = jQuery('#<portlet:namespace />file-entry_<%= classPK %> > a:first');

			titleLink.attr(
				{
					'href': '<%= themeDisplay.getPortalURL() + "/document/" + themeDisplay.getScopeGroupId() + StringPool.SLASH + fileEntry.getFolderId() + StringPool.SLASH + HttpUtil.encodeURL(fileEntry.getTitle()) %>'
				}
			);
		</script>
	</c:when>
	<c:otherwise>
		<%= html %>
	</c:otherwise>
</c:choose>