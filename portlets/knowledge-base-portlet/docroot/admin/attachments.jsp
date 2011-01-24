<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

<%@ include file="/admin/init.jsp" %>

<%
Map<String, String> fileNameToFileMessageMap = (Map<String, String>)request.getAttribute("attachments.jsp-fileNameToFileMessageMap");

long resourcePrimKey = ParamUtil.getLong(request, "resourcePrimKey");
%>

<div class="kb-attachments">

	<%
	for (Map.Entry<String, String> entry : fileNameToFileMessageMap.entrySet()) {
	%>

		<div>
			<portlet:resourceURL id="attachment" var="clipURL">
				<portlet:param name="companyId" value="<%= String.valueOf(company.getCompanyId()) %>" />
				<portlet:param name="fileName" value="<%= entry.getKey() %>" />
			</portlet:resourceURL>

			<liferay-ui:icon
				image="clip"
				label="<%= true %>"
				message="<%= entry.getValue() %>"
				method="get"
				url="<%= clipURL %>"
			/>
		</div>

	<%
	}
	%>

	<portlet:renderURL var="selectAttachmentsURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="jspPage" value="/admin/select_attachments.jsp" />
		<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
	</portlet:renderURL>

	<portlet:actionURL name="updateAttachments" var="updateAttachmentsURL">
		<portlet:param name="redirect" value="<%= selectAttachmentsURL %>" />
		<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
	</portlet:actionURL>

	<%
	String taglibOnClick = "var selectAttachmentsWindow = window.open('" + updateAttachmentsURL + "&" + renderResponse.getNamespace() + "dirName=' + document." + renderResponse.getNamespace() + "fm." + renderResponse.getNamespace() + "dirName.value, 'selectAttachments', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); selectAttachmentsWindow.focus();";
	%>

	<div class="kb-edit-link">
		<aui:a href="javascript:;" onClick="<%= taglibOnClick %>"><liferay-ui:message key='<%= fileNameToFileMessageMap.isEmpty() ? "add-attachments" : "attachments" %>' /> &raquo;</aui:a>
	</div>
</div>