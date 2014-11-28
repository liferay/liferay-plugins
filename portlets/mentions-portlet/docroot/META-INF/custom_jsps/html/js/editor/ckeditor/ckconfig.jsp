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

<%@ taglib prefix="liferay-util" uri="http://liferay.com/tld/util" %>

<%@ page import="com.liferay.portal.kernel.util.ContentTypes" %>

<liferay-util:buffer var="html">
	<liferay-util:include page="/html/js/editor/ckeditor/ckconfig.portal.jsp" />
</liferay-util:buffer>

<%
response.setContentType(ContentTypes.TEXT_JAVASCRIPT);

int pos = html.indexOf("config.extraPlugins");

pos = html.indexOf(";", pos);

if (pos != -1) {
	html = html.substring(0, pos) + ";\nconfig.extraPlugins += ',autocomplete';\n" + html.substring(pos, html.length());
}
%>

<%= html %>