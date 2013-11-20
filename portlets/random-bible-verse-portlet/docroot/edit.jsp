<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

<portlet:actionURL var="updateURL" />

<aui:form action="<%= updateURL %>" method="post" name="fm" onSubmit="submitForm(document.<portlet:namespace />fm);">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<aui:select inlineLabel="true" name="language">
		<aui:option label="default-language" selected='<%= language.equals("") %>' value="" />

		<%
		Iterator itr = bibles.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry entry = (Map.Entry)itr.next();

			Bible bible = (Bible)entry.getValue();
		%>

			<aui:option label="<%= StringUtil.toLowerCase(bible.getLanguageName()) %>" selected="<%= language.equals(bible.getLanguage()) %>" value="<%= bible.getLanguage() %>" />

		<%
		}
		%>

	</aui:select>

	<aui:button-row>
		<aui:button type="submit" value="save" />
	</aui:button-row>
</aui:form>