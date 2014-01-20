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
	<liferay-util:include page="/html/portlet/users_admin/view.portal.jsp" />
</liferay-util:buffer>

<%
String namespace = portletDisplay.getNamespace();

String firstNameHeaderColId = namespace + "usersSearchContainer_col-first-name";
String lastNameHeaderColId = namespace + "usersSearchContainer_col-last-name";

int firstNameHeaderColStart = _getHeaderColStart(html, firstNameHeaderColId);
int firstNameHeaderColEnd = _getHeaderColEnd(html, firstNameHeaderColStart);

int lastNameHeaderColStart = _getHeaderColStart(html, lastNameHeaderColId);
int lastNameHeaderColEnd = _getHeaderColEnd(html, lastNameHeaderColStart);

html = _reverseHeaderCols(html, firstNameHeaderColStart, firstNameHeaderColEnd, lastNameHeaderColStart, lastNameHeaderColEnd);

int tableDataStart = _getTableDataStart(html, firstNameHeaderColStart);
int tableDataEnd = _getTableDataEnd(html, tableDataStart);

if ((tableDataStart >= 0) && (tableDataEnd >= 0)) {
	String tableData = html.substring(tableDataStart, tableDataEnd);

	List<String> userRows = _getUserRows(tableData);

	StringBundler sb = new StringBundler(userRows.size() + 2);

	sb.append(html.substring(0, tableDataStart));

	for (String userRow : userRows) {
		String reversedUserRow = _reverseName(userRow);

		sb.append(reversedUserRow);
	}

	sb.append(html.substring(tableDataEnd));

	html = sb.toString();
}
%>

<%= html %>

<%!
private int _getHeaderColEnd(String html, int fromIndex) {
	if (fromIndex < 0) {
		return -1;
	}

	return html.indexOf(_TH_CLOSE, fromIndex) + _TH_CLOSE.length();
}

private int _getHeaderColStart(String html, String id) {
	int x = html.indexOf("id=\"" + id + StringPool.QUOTE);

	if (x < 0) {
		return -1;
	}

	return html.lastIndexOf(_TH_OPEN, x);
}

private int _getTableDataEnd(String html, int fromIndex) {
	if (fromIndex < 0) {
		return -1;
	}

	int x = html.indexOf(_TBODY_CLOSE, fromIndex);

	if (x < 0) {
		return -1;
	}

	return html.lastIndexOf(_TR_CLOSE, x) + _TR_CLOSE.length();
}

private int _getTableDataStart(String html, int fromIndex) {
	if (fromIndex < 0) {
		return -1;
	}

	int x = html.indexOf(_TBODY_OPEN, fromIndex);

	if (x < 0) {
		return -1;
	}

	return html.indexOf(_TR_OPEN, x);
}

private List<String> _getUserRows(String tableData) {
	List<String> userRows = new ArrayList<String>();

	int pos = 0;

	while (true) {
		int x = tableData.indexOf(_TR_OPEN, pos);

		if (x < 0) {
			break;
		}

		int y = tableData.indexOf(_TR_CLOSE, x);

		if (y < 0) {
			userRows.add(tableData.substring(x));

			break;
		}
		else {
			y = y + _TR_CLOSE.length();

			userRows.add(tableData.substring(x, y));
		}

		pos = y;
	}

	return userRows;
}

private String _reverseHeaderCols(String html, int firstNameHeaderColStart, int firstNameHeaderColEnd, int lastNameHeaderColStart, int lastNameHeaderColEnd) {
	if ((firstNameHeaderColStart < 0) || (firstNameHeaderColEnd < 0) || (lastNameHeaderColStart < 0) || (lastNameHeaderColEnd < 0)) {
		return html;
	}

	StringBundler sb = new StringBundler(4);

	sb.append(html.substring(0, firstNameHeaderColStart));
	sb.append(html.substring(lastNameHeaderColStart, lastNameHeaderColEnd));
	sb.append(html.substring(firstNameHeaderColStart, firstNameHeaderColEnd));
	sb.append(html.substring(lastNameHeaderColEnd));

	return sb.toString();
}

private String _reverseName(String userRow) {
	int checkboxStart = userRow.indexOf(_TD_OPEN);
	int checkboxEnd = userRow.indexOf(_TD_CLOSE, checkboxStart);

	int firstNameStart = userRow.indexOf(_TD_OPEN, checkboxEnd);
	int firstNameEnd = userRow.indexOf(_TD_CLOSE, firstNameStart);

	int lastNameStart = userRow.indexOf(_TD_OPEN, firstNameEnd);
	int lastNameEnd = userRow.indexOf(_TD_CLOSE, lastNameStart);

	if ((lastNameStart < 0) || (lastNameEnd < 0)) {
		return userRow;
	}

	StringBundler sb = new StringBundler();

	sb.append(userRow.substring(0, firstNameStart));
	sb.append(userRow.substring(lastNameStart, lastNameEnd));
	sb.append(userRow.substring(firstNameStart, firstNameEnd));
	sb.append(userRow.substring(lastNameEnd));

	return sb.toString();
}

private static final String _TBODY_CLOSE ="</tbody>";

private static final String _TBODY_OPEN ="<tbody";

private static final String _TD_CLOSE = "</td>";

private static final String _TD_OPEN = "<td";

private static final String _TH_CLOSE = "</th>";

private static final String _TH_OPEN = "<th";

private static final String _TR_CLOSE = "</tr>";

private static final String _TR_OPEN = "<tr";
%>