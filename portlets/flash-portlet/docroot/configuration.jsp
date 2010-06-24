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

<%
if (movie.equals(StringPool.BLANK)) {
	movie = "http://www.youtube.com/v/WqjxI3kFPH0&hl=en";
}

if (flashAttributes.equals(StringPool.BLANK)) {
	flashAttributes =
		"align=left\n" +
		"allowScriptAccess=sameDomain\n" +
		"base=.\n" +
		"bgcolor=#FFFFFF\n" +
		"devicefont=true\n" +
		"height=200\n" +
		"loop=true\n" +
		"menu=false\n" +
		"play=false\n" +
		"quality=best\n" +
		"salign=\n" +
		"scale=showall\n" +
		"swliveconnect=false\n" +
		"width=100%\n" +
		"wmode=opaque";
}

if (flashVariables.equals(StringPool.BLANK)) {
	flashVariables = "var1=hello\nvar2=world";
}

movie = ParamUtil.getString(request, "movie", movie);
flashAttributes = ParamUtil.getString(request, "flashAttributes", flashAttributes);
flashVariables = ParamUtil.getString(request, "flashVariables", flashVariables);
%>

<form action="<liferay-portlet:actionURL portletConfiguration="true" />" method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="movie" />
	</td>
	<td>
		<input class="lfr-input-text" name="<portlet:namespace />movie" type="text" value="<%= movie %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="flash-attributes" />
	</td>
	<td>
		<textarea class="lfr-textarea" name="<portlet:namespace />flashAttributes" wrap="soft" onKeyDown="Liferay.Util.checkTab(this); disableEsc();"><%= flashAttributes %></textarea>
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="flash-variables" />
	</td>
	<td>
		<textarea class="lfr-textarea" name="<portlet:namespace />flashVariables" wrap="soft" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();"><%= flashVariables %></textarea>
	</td>
</tr>
</table>

<br />

<input type="button" value="<liferay-ui:message key="save" />" onClick="submitForm(document.<portlet:namespace />fm);" />

</form>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<aui:script>
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />movie);
	</aui:script>
</c:if>