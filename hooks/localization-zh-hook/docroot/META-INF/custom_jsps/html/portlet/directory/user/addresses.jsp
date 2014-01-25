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
List<Organization> organizations = (List<Organization>)request.getAttribute("user.organizations");

String className = (String)request.getAttribute("addresses.className");
long classPK = (Long)request.getAttribute("addresses.classPK");

List<Address> personalAddresses = Collections.emptyList();
List<Address> organizationAddresses = new ArrayList<Address>();

if (classPK > 0) {
	personalAddresses = AddressServiceUtil.getAddresses(className, classPK);
}

for (int i = 0; i < organizations.size(); i++) {
	try {
		organizationAddresses.addAll(AddressServiceUtil.getAddresses(Organization.class.getName(), organizations.get(i).getOrganizationId()));
	}
	catch (Exception e) {
	}
}
%>

<liferay-util:buffer var="html">
	<liferay-util:include page="/html/portlet/directory/user/addresses.portal.jsp" />
</liferay-util:buffer>

<%
String organizationAddressLabel = LanguageUtil.get(pageContext, "organization-address");

int organizationAddressesStart = _getAddressesStart(html, organizationAddressLabel);
int organizationAddressesEnd = _getAddressesEnd(html, organizationAddressesStart);

if ((organizationAddressesStart >= 0) && (organizationAddressesEnd >= 0)) {
	StringBundler sb = new StringBundler(organizationAddresses.size() + 2);

	sb.append(html.substring(0, organizationAddressesStart));

	for (Address address : organizationAddresses) {
		String street1 = address.getStreet1();
		String street2 = address.getStreet2();
		String street3 = address.getStreet3();

		String zipCode = address.getZip();
		String city = address.getCity();
		Region region = address.getRegion();

		String mailingName = address.getType().getName();
	%>

		<liferay-util:buffer var="organizationAddress">
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
		sb.append(organizationAddress);
	}

	sb.append(html.substring(organizationAddressesEnd));

	html = sb.toString();
}

String personalAddressLabel = LanguageUtil.get(pageContext, "personal-address");

int personalAddressesStart = _getAddressesStart(html, personalAddressLabel);
int personalAddressesEnd = _getAddressesEnd(html, personalAddressesStart);

if ((personalAddressesStart >= 0) && (personalAddressesEnd >= 0)) {
	StringBundler sb = new StringBundler(personalAddresses.size() + 2);

	sb.append(html.substring(0, personalAddressesStart));

	for (Address address : personalAddresses) {
		String street1 = address.getStreet1();
		String street2 = address.getStreet2();
		String street3 = address.getStreet3();

		String zipCode = address.getZip();
		String city = address.getCity();
		Region region = address.getRegion();

		String mailingName = address.getType().getName();
	%>

		<liferay-util:buffer var="personalAddress">
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
		sb.append(personalAddress);
	}

	sb.append(html.substring(personalAddressesEnd));

	html = sb.toString();
}
%>

<%= html %>

<%!
private int _getAddressesEnd(String html, int fromIndex) {
	if (fromIndex < 0) {
		return -1;
	}

	int x = html.indexOf(_UL_CLOSE, fromIndex);

	if (x < 0) {
		return -1;
	}

	return html.lastIndexOf(_LI_CLOSE, x) + _LI_CLOSE.length();
}

private int _getAddressesStart(String html, String label) {
	int x = html.indexOf(_H4_OPEN + label + _H4_CLOSE);

	if (x < 0) {
		return -1;
	}

	return html.indexOf(_LI_OPEN, x);
}

private static final String _H4_CLOSE = "</h4>";

private static final String _H4_OPEN = "<h4>";

private static final String _LI_CLOSE = "</li>";

private static final String _LI_OPEN = "<li";

private static final String _UL_CLOSE = "</ul>";
%>