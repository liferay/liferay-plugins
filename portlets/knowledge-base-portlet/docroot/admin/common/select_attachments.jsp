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

if (DLStoreUtil.hasDirectory(company.getCompanyId(), CompanyConstants.SYSTEM, dirName)) {
	fileNames = DLStoreUtil.getFileNames(company.getCompanyId(), CompanyConstants.SYSTEM, dirName);
}
%>

<liferay-ui:header
	title="attachments"
/>

<aui:form method="post" name="fm">
	<aui:input name="fileName" type="hidden" />

	<liferay-ui:error exception="<%= DuplicateFileException.class %>" message="please-enter-a-unique-document-name" />
	<liferay-ui:error exception="<%= FileNameException.class %>" message="please-enter-a-file-with-a-valid-file-name" />
	<liferay-ui:error exception="<%= NoSuchFileException.class %>" message="the-document-could-not-be-found" />

	<liferay-ui:error exception="<%= FileSizeException.class %>">

		<%
		long fileMaxSize = PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE);

		if (fileMaxSize == 0) {
			fileMaxSize = PrefsPropsUtil.getLong(PropsKeys.UPLOAD_SERVLET_REQUEST_IMPL_MAX_SIZE);
		}

		fileMaxSize /= 1024;
		%>

		<liferay-ui:message arguments="<%= fileMaxSize %>" key="please-enter-a-file-with-a-valid-file-size-no-larger-than-x" />
	</liferay-ui:error>

	<aui:fieldset>
		<liferay-ui:search-container
			delta="<%= fileNames.length %>"
			emptyResultsMessage="there-are-no-attachments"
		>
			<liferay-ui:search-container-results
				results="<%= ListUtil.fromArray(fileNames) %>"
				total="<%= fileNames.length %>"
			/>

			<liferay-ui:search-container-row
				className="java.lang.String"
				modelVar="fileName"
				stringKey="<%= true %>"
			>
				<liferay-portlet:resourceURL id="attachment" var="rowURL">
					<portlet:param name="companyId" value="<%= String.valueOf(company.getCompanyId()) %>" />
					<portlet:param name="fileName" value="<%= fileName %>" />
				</liferay-portlet:resourceURL>

				<liferay-ui:search-container-column-text
					name="attachment"
				>
					<liferay-ui:icon
						image="clip"
						label="<%= true %>"
						message='<%= FileUtil.getShortFileName(fileName) + " (" + TextFormatter.formatKB(DLStoreUtil.getFileSize(company.getCompanyId(), CompanyConstants.SYSTEM, fileName), locale) + "k)" %>'
						method="get"
						url="<%= rowURL %>"
					/>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					align="right"
				>

					<%
					String taglibURL = "javascript:" + renderResponse.getNamespace() + "deleteAttachment('" + UnicodeFormatter.toString(fileName) + "');";
					%>

					<liferay-ui:icon-delete
						label="<%= true %>"
						url="<%= taglibURL %>"
					/>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<%
			String taglibOnChange = renderResponse.getNamespace() + "addAttachment();";
			%>

			<aui:input label="" name="file" onChange="<%= taglibOnChange %>" type="file" />

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</aui:fieldset>
</aui:form>

<liferay-util:buffer var="html">
	<liferay-util:include page="/admin/attachments.jsp" servletContext="<%= application %>" />
</liferay-util:buffer>

<aui:script>
	function <portlet:namespace />addAttachment() {
		document.<portlet:namespace />fm.encoding = "<%= ContentTypes.MULTIPART_FORM_DATA %>";
		submitForm(document.<portlet:namespace />fm, '<liferay-portlet:actionURL name="addAttachment"><portlet:param name="jspPage" value='<%= jspPath + "select_attachments.jsp" %>' /><portlet:param name="redirect" value="<%= redirect %>" /><portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" /><portlet:param name="status" value="<%= String.valueOf(WorkflowConstants.STATUS_ANY) %>" /><portlet:param name="dirName" value="<%= dirName %>" /></liferay-portlet:actionURL>');
	}

	function <portlet:namespace />deleteAttachment(fileName) {
		document.<portlet:namespace />fm.<portlet:namespace />fileName.value = fileName;
		submitForm(document.<portlet:namespace />fm, '<liferay-portlet:actionURL name="deleteAttachment"><portlet:param name="jspPage" value='<%= jspPath + "select_attachments.jsp" %>' /><portlet:param name="redirect" value="<%= redirect %>" /><portlet:param name="resourcePrimKey" value="<%= String.valueOf(resourcePrimKey) %>" /><portlet:param name="status" value="<%= String.valueOf(WorkflowConstants.STATUS_ANY) %>" /><portlet:param name="dirName" value="<%= dirName %>" /></liferay-portlet:actionURL>');
	}

	opener.<portlet:namespace />updateAttachments("<%= UnicodeFormatter.toString(dirName) %>", "<%= UnicodeFormatter.toString(html) %>");
</aui:script>