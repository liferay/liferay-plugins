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

<%@ include file="/init.jsp" %>

<%
String zipsString = StringUtil.merge(zips, StringPool.NEW_LINE);

zips = StringUtil.split(ParamUtil.getString(request, "zips", zipsString), StringPool.NEW_LINE);

zipsString = StringUtil.merge(zips, StringPool.NEW_LINE);
%>

<form action="<portlet:actionURL />" method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

<liferay-ui:error exception="<%= ValidatorException.class %>">

	<%
	ValidatorException ve = (ValidatorException)errorException;
	%>

	<liferay-ui:message key="the-following-are-invalid-cities-or-zip-codes" />

	<%
	Enumeration enu = ve.getFailedKeys();

	while (enu.hasMoreElements()) {
		String zip = (String)enu.nextElement();
	%>

		<strong><%= HtmlUtil.escape(zip) %></strong><%= (enu.hasMoreElements()) ? ", " : "." %>

	<%
	}
	%>

</liferay-ui:error>

<liferay-ui:message key="enter-one-city-or-zip-code-per-line" />

<br /><br />

<textarea class="lfr-textarea" name="<portlet:namespace />zips" wrap="soft"><%= HtmlUtil.escape(zipsString) %></textarea>

<br /><br />

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="temperature-format" />
	</td>
	<td>
		<select name="<portlet:namespace />fahrenheit">
			<option <%= fahrenheit ? "selected" : "" %> value="1"><liferay-ui:message key="fahrenheit" /></option>
			<option <%= !fahrenheit ? "selected" : "" %> value="0"><liferay-ui:message key="celsius" /></option>
		</select>
	</td>
</tr>
</table>

<br />

<input type="button" value="<liferay-ui:message key="save" />" onClick="submitForm(document.<portlet:namespace />fm);" />

</form>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<aui:script>
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />zips);
	</aui:script>
</c:if>