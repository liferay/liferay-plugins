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
	<liferay-util:include page="/html/portlet/users_admin/common/addresses.portal.jsp" />
</liferay-util:buffer>

<%
String namespace = portletDisplay.getNamespace();

int i = 0;

while (true) {
	String street1Id = namespace + "addressStreet1_" + i;
	String street2Id = namespace + "addressStreet2_" + i;
	String street3Id = namespace + "addressStreet3_" + i;
	String countryId = namespace + "addressCountryId" + i;
	String regionId = namespace + "addressRegionId" + i ;
	String postalCodeId = namespace + "addressZip" + i ;
	String cityId = namespace + "addressCity" + i ;

	int street1Start = _getFormFieldStart(html, street1Id);
	int street1End = _getFormFieldEnd(html, street1Start);

	int street2Start = _getFormFieldStart(html, street2Id);
	int street2End = _getFormFieldEnd(html, street2Start);

	int street3Start = _getFormFieldStart(html, street3Id);
	int street3End = _getFormFieldEnd(html, street3Start);

	int countryStart = _getFormFieldStart(html, countryId);
	int countryEnd = _getFormFieldEnd(html, countryStart);

	int regionStart = _getFormFieldStart(html, regionId);
	int regionEnd = _getFormFieldEnd(html, regionStart);

	int postalCodeStart = _getFormFieldStart(html, postalCodeId);
	int postalCodeEnd = _getFormFieldEnd(html, postalCodeStart);

	int cityStart = _getFormFieldStart(html, cityId);
	int cityEnd = _getFormFieldEnd(html, cityStart);

	if ((street1Start < 0) || (street1Start < 0) || (street2Start < 0) || (street2End < 0) || (street3Start < 0) || (street3End < 0) || (countryStart < 0) || (countryEnd < 0) || (regionStart < 0) || (regionEnd < 0) || (postalCodeStart < 0) || (postalCodeEnd < 0)) {
		break;
	}

	html = _createAddressForm(html, street1Start, street1End, street2Start, street2End, street3Start, street3End, countryStart, countryEnd, regionStart, regionEnd, postalCodeStart, postalCodeEnd, cityStart, cityEnd);

	i++;
}
%>

<%= html %>

<%!
private String _createAddressForm(String html, int street1Start, int street1End, int street2Start, int street2End, int street3Start, int street3End, int countryStart, int countryEnd, int regionStart, int regionEnd, int postalCodeStart, int postalCodeEnd, int cityStart, int cityEnd) {
	StringBundler sb = new StringBundler(8);

	sb.append(html.substring(0, street1Start));
	sb.append(html.substring(countryStart, regionEnd));
	sb.append(html.substring(cityStart, cityEnd));
	sb.append(html.substring(street3Start, street3End));
	sb.append(html.substring(street1Start, street2End));
	sb.append(html.substring(postalCodeStart, postalCodeEnd));
	sb.append(html.substring(regionEnd, postalCodeStart));
	sb.append(html.substring(cityEnd));

	return sb.toString();
}

private int _getFormFieldEnd(String html, int fromIndex) {
	if (fromIndex < 0) {
		return -1;
	}

	return html.indexOf(_DIV_CLOSE, fromIndex) + _DIV_CLOSE.length();
}

private int _getFormFieldStart(String html, String id) {
	int x = html.indexOf("id=\"" + id + StringPool.QUOTE);

	if (x < 0) {
		return -1;
	}

	return html.lastIndexOf(_DIV_OPEN, x);
}

private static final String _DIV_CLOSE = "</div>";

private static final String _DIV_OPEN = "<div";
%>