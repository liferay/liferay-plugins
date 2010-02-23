<%
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
%>

<%@ include file="/html/portlet/document_library/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Object[] objArray = (Object[])row.getObject();

DLFileEntry fileEntry = (DLFileEntry)objArray[0];
DLFileVersion fileVersion = (DLFileVersion)objArray[1];
String[] conversions = (String[])objArray[2];
PortletURL redirectURL = (PortletURL)objArray[3];
Boolean isLocked = (Boolean)objArray[4];
Boolean hasLock = (Boolean)objArray[5];

String strutsAction = ParamUtil.getString(request, "struts_action");

String rowHREF = themeDisplay.getPathMain() + "/document_library/get_file?p_l_id=" + themeDisplay.getPlid() + "&folderId=" + fileEntry.getFolderId() + "&name=" + HttpUtil.encodeURL(fileEntry.getName()) + "&version=" + fileVersion.getVersion();
%>

<div>
	<div class="result-title">
		<a href="<%= rowHREF %>"><liferay-ui:message key="revision" /> <%= fileVersion.getVersion() %></a>
	</div>

	<div class="result-data">
		<span><liferay-ui:message key="by" />: <%= fileVersion.getUserName() %></span>
		<span><liferay-ui:message key="modified" />: <%= dateFormatDateTime.format(fileVersion.getCreateDate()) %></span>
		<span><liferay-ui:message key="size" />: <%= TextFormatter.formatKB(fileVersion.getSize(), locale) %>k</span>
		<span><a class="comments" href="javascript:;"><liferay-ui:message key="show-comments" /></a></span>
	</div>

	<c:if test="<%= conversions.length > 0 %>">
		<liferay-util:include page="/html/portlet/document_library/file_version_convert_to.jsp" />
	</c:if>

	<c:if test='<%= strutsAction.equals("/document_library/edit_file_entry") %>'>
		<liferay-util:include page="/html/portlet/document_library/file_version_action.jsp" />
	</c:if>

	<div class="result-comments" style="display: none;">
		<portlet:actionURL var="discussionURL">
			<portlet:param name="struts_action" value="/document_library/edit_file_entry_discussion" />
		</portlet:actionURL>

		<liferay-ui:discussion
			formName='<%= "fm2" + fileVersion.getFileVersionId() %>'
			formAction="<%= discussionURL %>"
			className="<%= DLFileVersion.class.getName() %>"
			classPK="<%= fileVersion.getFileVersionId() %>"
			userId="<%= fileVersion.getUserId() %>"
			subject="<%= fileEntry.getTitle() %>"
			redirect="<%= currentURL %>"
			ratingsEnabled="<%= enableCommentRatings %>"
		/>
	</div>
</div>