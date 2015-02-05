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
	<aui:input name="parentKBFolderId" type="hidden" value="<%= String.valueOf(parentKBFolderId) %>" />

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
		<aui:input checked="<%= true %>" helpMessage="prioritize-only-new-articles-help" label="prioritize-only-new-articles" name="prioritizeUpdatedKBArticles" type="radio" value="false" />
		<aui:input checked="<%= false %>" helpMessage="prioritize-new-and-updated-articles-help" label="prioritize-new-and-updated-articles" name="prioritizeUpdatedKBArticles" type="radio" value="true" />
		<aui:input helpMessage="prioritize-by-numerical-prefix-help" label="prioritize-by-numerical-prefix" name="prioritizeByNumericalPrefix" type="checkbox" value="false" />
	</aui:field-wrapper>

	<aui:button-row>
		<aui:button name="submit" type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>