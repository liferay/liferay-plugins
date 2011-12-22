<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
KBArticle kbArticle = (KBArticle)request.getAttribute(WebKeys.KNOWLEDGE_BASE_KB_ARTICLE);

long resourcePrimKey = BeanParamUtil.getLong(kbArticle, request, "resourcePrimKey");

String dirName = ParamUtil.getString(request, "dirName");

String[] fileNames = new String[0];

if (Validator.isNotNull(dirName) && DLStoreUtil.hasDirectory(company.getCompanyId(), CompanyConstants.SYSTEM, dirName)) {
	fileNames = DLStoreUtil.getFileNames(company.getCompanyId(), CompanyConstants.SYSTEM, dirName);
}
%>

<div class="kb-attachments">

	<%
	for (String fileName : fileNames) {
	%>

		<div>
			<liferay-portlet:resourceURL id="attachment" var="clipURL">
				<portlet:param name="companyId" value="<%= String.valueOf(company.getCompanyId()) %>" />
				<portlet:param name="fileName" value="<%= fileName %>" />
			</liferay-portlet:resourceURL>

			<liferay-ui:icon
				image="clip"
				label="<%= true %>"
				message='<%= FileUtil.getShortFileName(fileName) + " (" + TextFormatter.formatKB(DLStoreUtil.getFileSize(company.getCompanyId(), CompanyConstants.SYSTEM, fileName), locale) + "k)" %>'
				method="get"
				url="<%= clipURL %>"
			/>
		</div>

	<%
	}
	%>

	<liferay-portlet:renderURL var="selectAttachmentsURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="jspPage" value='<%= jspPath + "select_attachments.jsp" %>' />
		<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
		<portlet:param name="status" value="<%= String.valueOf(WorkflowConstants.STATUS_ANY) %>" />
	</liferay-portlet:renderURL>

	<liferay-portlet:actionURL name="updateAttachments" var="updateAttachmentsURL">
		<portlet:param name="redirect" value="<%= selectAttachmentsURL %>" />
		<portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" />
	</liferay-portlet:actionURL>

	<%
	String taglibOnClick = "var selectAttachmentsWindow = window.open('" + updateAttachmentsURL + "&" + renderResponse.getNamespace() + "dirName=' + document." + renderResponse.getNamespace() + "fm." + renderResponse.getNamespace() + "dirName.value, 'selectAttachments', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); selectAttachmentsWindow.focus();";
	%>

	<div class="kb-edit-link">
		<aui:a href="javascript:;" onClick="<%= taglibOnClick %>"><liferay-ui:message key='<%= (fileNames.length != 0) ? "attachments" : "add-attachments" %>' /> &raquo;</aui:a>
	</div>
</div>