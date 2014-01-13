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
 
<%@ include file="/html/portlet/login/init.jsp" %>

<liferay-util:buffer var="html">
	<liferay-util:include page="/html/portlet/login/create_account.portal.jsp" />
</liferay-util:buffer>

<%
String namespace = portletDisplay.getNamespace();

String firstNameId = "id=\"" + namespace + "firstName\"";
String middleNameId = "id=\"" + namespace + "middleName\"";
String lastNameId = "id=\"" + namespace + "lastName\"";

int middleNameStart = _getFormFieldStart(html, middleNameId);
int middleNameEnd = _getFormFieldEnd(html, middleNameStart);

html = _removeMiddleName(html, middleNameStart, middleNameEnd);

int firstNameStart = _getFormFieldStart(html,firstNameId);
int firstNameEnd = _getFormFieldEnd(html, firstNameStart);

int lastNameStart = _getFormFieldStart(html, lastNameId);
int lastNameEnd = _getFormFieldEnd(html, lastNameStart);

html = _reverseFirstNameLastName(html, firstNameStart, firstNameEnd, lastNameStart, lastNameEnd);
%>

<%= html %>

<%!
private int _getFormFieldEnd(String html, int fromIndex) {
	if(fromIndex >= 0) {
		return html.indexOf(_DIV_CLOSE, fromIndex) + _DIV_CLOSE.length();
	}
	else {
		return fromIndex;
	}
}

private int _getFormFieldStart(String html, String queryString) {
	int x = html.indexOf(queryString);

	if(x >= 0) {
		return html.lastIndexOf(_DIV_OPEN, x);
	}
	else {
		return x;
	}
}

private String _removeMiddleName(String html, int fromIndex, int toIndex) {
	if(fromIndex >= 0 && toIndex >= 0) {
		String middleName = html.substring(fromIndex, toIndex);

		html = StringUtil.replaceFirst(html, middleName, StringPool.BLANK);
	}

	return html;
}

private String _reverseFirstNameLastName(String html, int firstNameStart, int firstNameEnd, int lastNameStart, int lastNameEnd) {
	if (firstNameStart >= 0 && firstNameEnd >= 0) {
		if (lastNameStart >= 0 && lastNameEnd >= 0) {
			String lastName = html.substring(lastNameStart, lastNameEnd);
			String firstName = html.substring(firstNameStart, firstNameEnd);

			html = StringUtil.replaceFirst(html, lastName, firstName);

			html = StringUtil.replaceFirst(html,firstName, lastName);
		}
	}

	return html;
}

private static final String _DIV_CLOSE = "</div>";

private static final String _DIV_OPEN = "<div";
%>