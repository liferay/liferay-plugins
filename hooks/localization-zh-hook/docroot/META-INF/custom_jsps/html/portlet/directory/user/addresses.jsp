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

<%@ include file="/html/portlet/directory/init.jsp" %>

<%
List<Address> addresses = _getAddresses((String)request.getAttribute("addresses.className"), (Long)request.getAttribute("addresses.classPK"));
%>

<liferay-util:buffer var="html">
	<liferay-util:include page="/html/portlet/directory/user/addresses.portal.jsp" />
</liferay-util:buffer>

<%
int addressDataStart = _getAddressDataStart(html);
int addressDataEnd = _getAddressDataEnd(html, addressDataStart);

if ((addressDataStart >= 0) && (addressDataEnd >= 0)) {
	StringBundler sb = new StringBundler(addresses.size() + 2);

	sb.append(html.substring(0, addressDataStart));

	for (Address address : addresses) {
		String street1 = address.getStreet1();
		String street2 = address.getStreet2();
		String street3 = address.getStreet3();

		String zipCode = address.getZip();
		String city = address.getCity();
		Region region = address.getRegion();

		String mailingName = address.getType().getName();
	%>

		<liferay-util:buffer var="newAddress">

			<li class="<%= address.isPrimary() ? "primary" : "" %>">
				<em class="mailing-name"> <liferay-ui:message key="<%= mailingName %>" /> </em>

				<c:if test="<%= Validator.isNotNull(region.getName()) %>">
					<%= HtmlUtil.escape(region.getName()) %>
				</c:if>

				<c:if test="<%= Validator.isNotNull(city) %>">
					<%= HtmlUtil.escape(city) %><liferay-ui:message key="city-label" />
				</c:if>

				<c:if test="<%= Validator.isNotNull(street3) %>">
					<%= HtmlUtil.escape(street3) %>
				</c:if>

				<c:if test="<%= Validator.isNotNull(street1) %>">
					<%= HtmlUtil.escape(street1) %>
				</c:if>

				<c:if test="<%= Validator.isNotNull(street2) %>">
					<%= HtmlUtil.escape(street2) %>
				</c:if>

				<c:if test="<%= Validator.isNotNull(region.getName()) || Validator.isNotNull(city) || Validator.isNotNull(street3) || Validator.isNotNull(street1) || Validator.isNotNull(street2) %>">
					<br />
				</c:if>

				<c:if test="<%= Validator.isNotNull(zipCode) %>">
					<liferay-ui:message key="zip-code-label" /> <%= HtmlUtil.escape(zipCode) %>
				</c:if>

				<c:if test="<%= address.isMailing() %>">(<liferay-ui:message key="mailing" />)</c:if>
			</li>
		</liferay-util:buffer>

<%
		sb.append(newAddress);
	}

	sb.append(html.substring(addressDataEnd));

	html = sb.toString();
}
%>

<%= html %>

<%!
private int _getAddressDataEnd(String html, int fromIndex) {
	if (fromIndex < 0) {
		return -1;
	}

	int x = html.indexOf(_UL_CLOSE, fromIndex);

	if (x < 0) {
		return -1;
	}

	return html.lastIndexOf(_LI_CLOSE, x) + _LI_CLOSE.length();
}

private int _getAddressDataStart(String html) {
	int x = html.indexOf(_CLASS_MAILING_NAME);

	if (x < 0) {
		return -1;
	}

	return html.lastIndexOf(_LI_OPEN, x);
}

private List<Address> _getAddresses(String className, long classPK) throws PortalException, SystemException {
	List<Address> personalAddresses = new ArrayList<Address>();

	if (classPK > 0) {
		personalAddresses = AddressServiceUtil.getAddresses(className, classPK);
	}

	return personalAddresses;
}

private static final String _CLASS_MAILING_NAME = "class=\"mailing-name\"";

private static final String _LI_CLOSE = "</li>";

private static final String _LI_OPEN = "<li";

private static final String _UL_CLOSE = "</ul>";
%>