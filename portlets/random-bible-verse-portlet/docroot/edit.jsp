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
Map bibles = RBVUtil.getBibles();
%>

<form action="<portlet:actionURL />" method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="language" />
	</td>
	<td>
		<select name="<portlet:namespace />language">
			<option <%= language.equals("") ? "selected" : "" %> value=""><liferay-ui:message key="default-language" /></option>

			<%
			Iterator itr = bibles.entrySet().iterator();

			while (itr.hasNext()) {
				Map.Entry entry = (Map.Entry)itr.next();

				Bible bible = (Bible)entry.getValue();
			%>

				<option <%= language.equals(bible.getLanguage()) ? "selected" : "" %> value="<%= bible.getLanguage() %>"><%= LanguageUtil.get(pageContext, bible.getLanguageName().toLowerCase()) %></option>

			<%
			}
			%>

		</select>
	</td>
</tr>
</table>

<br />

<input type="button" value="<liferay-ui:message key="save" />" onClick="submitForm(document.<portlet:namespace />fm);" />

</form>