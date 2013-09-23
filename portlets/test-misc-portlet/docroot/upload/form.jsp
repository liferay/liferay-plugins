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
String actionName = ParamUtil.getString(renderRequest, ActionRequest.ACTION_NAME);
%>

<form action='<portlet:actionURL name="<%= actionName %>" />' method="post">
	<aui:fieldset>
		<aui:input label="title" name="title" />
		<aui:input label="file" name="fileName" size="50" type="file" />
	</aui:fieldset>

	<aui:button type="submit" value="save" />
</form>