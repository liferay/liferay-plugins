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
request.setAttribute("toolbarItem", "import");

redirect = ParamUtil.getString(request, "redirect", currentURL);
%>

<portlet:actionURL name="addFile" var="addFileURL" windowState="normal" />

<aui:form action="<%= addFileURL %>" class="uni-form" enctype="multipart/form-data" method="post" name="fm">
	<aui:input id="uploadFileName" name="uploadFileName" type="hidden" />

	Upload your ZIP file

	<aui:fieldset class="block-labels">

		<%
		String taglibOnChange = renderResponse.getNamespace() + "setUploadFileName(this.value);";
		%>

		<aui:input id="file" name="file" onchange="<%= taglibOnChange %>" size="50" type="file" />

	</aui:fieldset>

	<%
	String taglibOnSubmit = renderResponse.getNamespace() + "addFile();";
	%>

	<aui:button name="submit" onClick="<%= taglibOnSubmit %>" type="submit" value="submit" />
</aui:form>

<aui:script>
	function <portlet:namespace />setUploadFileName(fileName) {
		document.<portlet:namespace />fm.<portlet:namespace />uploadFileName.value = fileName;

		if (!document.<portlet:namespace />fm.<portlet:namespace />title) {
			return;
		}

		var title = document.<portlet:namespace />fm.<portlet:namespace />title.value;

		if (title == '') {
			var pos = fileName.lastIndexOf(".");

			if (pos > 0) {
				fileName = fileName.substr(0, pos);
			}

			document.<portlet:namespace />fm.<portlet:namespace />title.value = fileName;
		}
	}
</aui:script>

<aui:script>
	function <portlet:namespace />addFile() {
		submitForm(document.<portlet:namespace />fm, '<liferay-portlet:actionURL name="addFile"></liferay-portlet:actionURL>');
	}
</aui:script>