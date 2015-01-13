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

	<liferay-ui:message key="upload-your-zip-file" />

	<aui:fieldset class="kb-block-labels">
		<aui:input id="file" name="file" type="file" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button name="submit" type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>