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

<%@ include file="/html/portal/init.jsp" %>

<liferay-util:buffer var="html">
	<liferay-util:include page="/html/portal/update_reminder_query.portal.jsp" />
</liferay-util:buffer>

<%
int x = _indexOfOptionStart(html);
int y = _indexOfOptionEnd(html, x);

if ((x >= 0) && (y >= 0)) {
	html = html.substring(0, x) + html.substring(y + 1);
}
%>

<%= html %>

<%!
private int _indexOfOptionStart(String html) {
	int x = _indexOfReminderQueryQuestionId(html);

	if (x < 0) {
		return x;
	}

	int y = html.indexOf(_VALUE_WHAT_IS_YOUR_FATHERS_MIDDLE_NAME, x);

	if (y < 0) {
		return y;
	}

	return html.lastIndexOf(StringPool.LESS_THAN, y);
}

private int _indexOfOptionEnd(String html, int fromIndex) {
	int x = html.indexOf(_OPTION_CLOSE, fromIndex);

	if (x < 0) {
		return x;
	}

	return x + _OPTION_CLOSE.length();
}

private int _indexOfReminderQueryQuestionId(String html) {
	return html.indexOf(_ID_REMINDER_QUERY_QUESTION);
}

private static final String _ID_REMINDER_QUERY_QUESTION = "id=\"reminderQueryQuestion\"";

private static final String _OPTION_CLOSE = "</option>";

private static final String _VALUE_WHAT_IS_YOUR_FATHERS_MIDDLE_NAME = "value=\"what-is-your-father's-middle-name\"";
%>