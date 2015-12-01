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

<%@ include file="/admin/init.jsp" %>

<%
long parentKBFolderId = ParamUtil.getLong(request, "parentKBFolderId");
%>

<portlet:actionURL name="importFile" var="importFileURL">
	<portlet:param name="redirect" value="<%= redirect %>" />
</portlet:actionURL>

<aui:form action="<%= importFileURL %>" class="lfr-dynamic-form" enctype="multipart/form-data" method="post" name="fm">
	<aui:input name="mvcPath" type="hidden" value="/admin/import.jsp" />
	<aui:input name="parentKBFolderId" type="hidden" value="<%= String.valueOf(parentKBFolderId) %>" />

	<liferay-ui:error exception="<%= KBArticleImportException.class %>">

		<%
		KBArticleImportException kbaie = (KBArticleImportException)errorException;
		%>

		<%= LanguageUtil.format(locale, "an-unexpected-error-occurred-while-importing-articles-x", kbaie.getLocalizedMessage()) %>
	</liferay-ui:error>

	<liferay-ui:error exception="<%= UploadRequestSizeException.class %>">
		<liferay-ui:message arguments="<%= TextFormatter.formatStorageSize(PrefsPropsUtil.getLong(PropsKeys.UPLOAD_SERVLET_REQUEST_IMPL_MAX_SIZE), locale) %>" key="request-is-larger-than-x-and-could-not-be-processed" translateArguments="<%= false %>" />
	</liferay-ui:error>

	<aui:fieldset class="kb-block-labels">
		<aui:field-wrapper>
			<div class="alert alert-info">
				<liferay-ui:message
					arguments="<%= StringUtil.merge(PortletPropsValues.MARKDOWN_IMPORTER_ARTICLE_EXTENSIONS, StringPool.COMMA_AND_SPACE) %>"
					key="upload-your-zip-file-help"
				/>
			</div>
		</aui:field-wrapper>

		<aui:input id="file" label="upload-your-zip-file" name="file" type="file" />
	</aui:fieldset>

	<aui:field-wrapper label="prioritization-strategy">
		<aui:input helpMessage="apply-numerical-prefixes-of-article-files-as-priorities-help" label="apply-numerical-prefixes-of-article-files-as-priorities" name="prioritizeByNumericalPrefix" type="checkbox" value="true" />
	</aui:field-wrapper>

	<aui:button-row>
		<aui:button name="submit" type="submit" />

		<portlet:renderURL var="cancelURL">
			<portlet:param name="mvcPath" value="/admin/view.jsp" />
			<portlet:param name="parentResourceClassNameId" value="<%= String.valueOf(PortalUtil.getClassNameId(KBFolderConstants.getClassName())) %>" />
			<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(parentKBFolderId) %>" />
		</portlet:renderURL>

		<aui:button href="<%= cancelURL %>" type="cancel" />
	</aui:button-row>
</aui:form>