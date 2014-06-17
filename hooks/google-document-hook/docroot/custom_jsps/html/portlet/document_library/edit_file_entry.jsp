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

<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ include file="/html/portlet/document_library/init.jsp" %>
<%@ include file="/html/portlet/document_library/detect_google_document.jspf" %>

<c:choose>

	<c:when test="<%= googleDocument %>">
		<liferay-util:buffer var="html">
			<liferay-util:include page="/html/portlet/document_library/edit_file_entry_google_document.jsp" />
		</liferay-util:buffer>
		<liferay-util:buffer var="js">
			<liferay-util:include page="/html/portlet/document_library/add_google_shortcut_js.jsp" />
		</liferay-util:buffer>

		<style>
			#<portlet:namespace />file, label[for=<portlet:namespace />file],
			#<portlet:namespace />title, label[for=<portlet:namespace />title],
			#<portlet:namespace />description, label[for=<portlet:namespace />description],
			#<portlet:namespace />fileEntryTypeId, label[for=<portlet:namespace />fileEntryTypeId],
			.lfr-ddm-container {
				display: none;
			}
		</style>

		<%= html %>
		<%= js %>
	</c:when>

	<c:otherwise>
		<liferay-util:buffer var="html2">
			<liferay-util:include page="/html/portlet/document_library/edit_file_entry.portal.jsp" />
		</liferay-util:buffer>

		<%= html2 %>
	</c:otherwise>

</c:choose>