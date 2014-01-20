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

<%@ include file="/html/portlet/users_admin/init.jsp" %>

<liferay-util:buffer var="html">
	<liferay-util:include page="/html/portlet/users_admin/user/password.portal.jsp" />
</liferay-util:buffer>

<%
String namespace = portletDisplay.getNamespace();

String reminderQueryQuestionId = namespace + "reminderQueryQuestion";

int optionStart = _getOptionStart(html, reminderQueryQuestionId);
int optionEnd = _getOptionEnd(html, optionStart);

html = _removeOption(html, optionStart, optionEnd);
%>

<%= html %>

<%!
private int _getOptionEnd(String html, int fromIndex) {
	int x = html.indexOf(_OPTION_CLOSE, fromIndex);

	if (x < 0) {
		return -1;
	}

	return x + _OPTION_CLOSE.length();
}

private int _getOptionStart(String html, String id) {
	int x = html.indexOf("id=\"" + id + StringPool.QUOTE);

	if (x < 0) {
		return -1;
	}

	int y = html.indexOf(_VALUE_WHAT_IS_YOUR_FATHERS_MIDDLE_NAME, x);

	if (y < 0) {
		return -1;
	}

	return html.lastIndexOf(_OPTION_OPEN, y);
}

private String _removeOption(String html, int optionStart, int optionEnd) {
	if ((optionStart < 0) || (optionEnd < 0)) {
		return html;
	}

	return html.substring(0, optionStart) + html.substring(optionEnd);
}

private static final String _OPTION_CLOSE = "</option>";

private static final String _OPTION_OPEN = "<option";

private static final String _VALUE_WHAT_IS_YOUR_FATHERS_MIDDLE_NAME = "value=\"what-is-your-father's-middle-name\"";
%>