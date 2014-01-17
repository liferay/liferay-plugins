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
	<liferay-util:include page="/html/portlet/users_admin/view_tree.portal.jsp" />
</liferay-util:buffer>

<%
String namespace = portletDisplay.getNamespace();

String firstNameThId = namespace + "usersSearchContainer_col-first-name";

String lasteNameThId = namespace + "usersSearchContainer_col-last-name";

_TD_NAME_ROWCHECKER ="name=\"" + namespace + "rowIds\"";

int firstNameThStart = _getTableElementStart(html, firstNameThId, _TH);

int firstNameThEnd = _getTableElementEnd(html, firstNameThStart, _TH);

int lastNameThStart = _getTableElementStart(html, lasteNameThId, _TH);

int lastNameThEnd = _getTableElementEnd(html, lastNameThStart, _TH);

html = _reverseFirstNameThLastNameTh(html, firstNameThStart, firstNameThEnd, lastNameThStart, lastNameThEnd);

int tbodyStart = _getTableElementStart(html, lastNameThEnd, _TBODY);

int tbodyEnd = _getTableElementEnd(html, tbodyStart, _TBODY);

html = _reverseFirstNameTdLastNameTd(html, tbodyStart, tbodyEnd);
%>

<%= html %>

<%!
private int _getTableElementStart(String html, int fromIndex, int elementId) {
	if (fromIndex < 0) {
		return fromIndex;
	}

	return html.indexOf(_ELEMENTS_OPEN[elementId], fromIndex);
}

private int _getTableElementStart(String html, String id, int elementId) {
	int x = html.indexOf("id=\"" + id + StringPool.QUOTE);
	if (x < 0) {
		return x;
	}

	return html.lastIndexOf(_ELEMENTS_OPEN[elementId], x);
}

private int _getTableElementEnd(String html, int fromIndex, int elementId) {
	if (fromIndex < 0) {
		return fromIndex;
	}

	return html.indexOf(_ELEMENTS_CLOSE[elementId], fromIndex) + _ELEMENTS_CLOSE[elementId].length();
}

private String _reverseFirstNameTdLastNameTd(String html, int fromIndex, int toIndex) {

	int tdCheckBoxIndex = html.indexOf(_TD_NAME_ROWCHECKER, fromIndex);

	if (tdCheckBoxIndex < 0) {
		return html;
	}

	int firstNameTdStart = _getTableElementStart(html, tdCheckBoxIndex, _TD);
	int firstNameTdEnd = _getTableElementEnd(html, firstNameTdStart, _TD);
	int lastNameTdStart = _getTableElementStart(html, firstNameTdEnd, _TD);
	int lastNameTdEnd = _getTableElementEnd(html, lastNameTdStart, _TD);

	if ((firstNameTdStart < 0) || (firstNameTdEnd < 0) || (lastNameTdStart < 0) || (lastNameTdEnd < 0)) {
		return html;
	}

	int tdAndTdSpace = lastNameTdStart - firstNameTdEnd; 

	StringBundler sb = new StringBundler(4);
	sb.append(html.substring(0, firstNameTdStart));
	sb.append(html.substring(lastNameTdStart, lastNameTdEnd + tdAndTdSpace));
	sb.append(html.substring(firstNameTdStart, firstNameTdEnd + tdAndTdSpace));
	sb.append(html.substring(lastNameTdEnd + tdAndTdSpace));

	html = sb.toString();

	int nextTrStart = _getTableElementStart(html, firstNameTdStart, _TR);

	toIndex = _getTableElementEnd(html, firstNameTdStart, _TBODY);

	if (nextTrStart < 0 || nextTrStart > toIndex) {
		return html;
	}

	html = _reverseFirstNameTdLastNameTd(html, nextTrStart, toIndex);

	return html;
}

private String _reverseFirstNameThLastNameTh(String html, int firstNameThStart, int firstNameThEnd, int lastNameThStart, int lastNameThEnd) {
	if ((firstNameThStart < 0) || (firstNameThEnd < 0) || (lastNameThStart < 0) || (lastNameThEnd < 0)) {
		return html;
	}

	int thAndThSpace = lastNameThStart - firstNameThEnd;

	StringBundler sb = new StringBundler(4);
	sb.append(html.substring(0, firstNameThStart));
	sb.append(html.substring(lastNameThStart, lastNameThEnd + thAndThSpace));
	sb.append(html.substring(firstNameThStart, firstNameThEnd + thAndThSpace));
	sb.append(html.substring(lastNameThEnd + thAndThSpace));

	return sb.toString();
}

private static final String _ELEMENTS_OPEN[] = {"<tbody","<th","<tr","<td"};
private static final String _ELEMENTS_CLOSE[] = {"</tbody>","</th>","</tr>","</td>"};
private static final int _TH = 1;
private static final int _TD = 3;
private static final int _TR = 2;
private static final int _TBODY = 0;
private static String _TD_NAME_ROWCHECKER = new String();
%>

