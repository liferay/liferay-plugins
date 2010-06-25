<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
%>

<%@ include file="/init.jsp" %>

<form action="<liferay-portlet:actionURL portletConfiguration="true" />" method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

<liferay-ui:input-editor />

<input name="<portlet:namespace />message" type="hidden" value="" />

<br /><br />

<input type="button" value="<liferay-ui:message key="save" />" onClick="<portlet:namespace />saveMessage();" />

</form>

<aui:script>
	function <portlet:namespace />initEditor() {
		return "<%= UnicodeFormatter.toString(message) %>";
	}

	function <portlet:namespace />saveMessage() {
		var message = window.<portlet:namespace />editor.getHTML();

		document.<portlet:namespace />fm.<portlet:namespace />message.value = message;

		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>