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
List<Address> organizationAddresses = new ArrayList<Address>();

for (int i = 0; i < organizations.size(); i++) {
	organizationAddresses.addAll(_getAddresses(Organization.class.getName(), organizations.get(i).getOrganizationId()));
}

List<Address> personalAddresses = _getAddresses((String)request.getAttribute("addresses.className"), (Long)request.getAttribute("addresses.classPK"));
%>

<liferay-util:buffer var="html">
	<liferay-util:include page="/html/portlet/directory/user/addresses.portal.jsp" />
</liferay-util:buffer>

<%

String organzationAddressTitle = LanguageUtil.get(pageContext, "organization-address");
String personalAddressTitle = LanguageUtil.get(pageContext, "personal-address");

int organizationAddressDataStart = _getAddressDataStart(html, organzationAddressTitle);
int organizationAddressDataEnd = _getAddressDataEnd(html, organizationAddressDataStart);

if ((organizationAddressDataStart >= 0) && (organizationAddressDataEnd >= 0)) {
	StringBundler sb = new StringBundler(organizationAddresses.size() + 2);

	sb.append(html.substring(0, organizationAddressDataStart));

	for (Address origanizationAddress : organizationAddresses) {
		String street1 = origanizationAddress.getStreet1();
		String street2 = origanizationAddress.getStreet2();
		String street3 = origanizationAddress.getStreet3();

		String zipCode = origanizationAddress.getZip();
		String city = origanizationAddress.getCity();
		Region region = origanizationAddress.getRegion();

		String mailingName = origanizationAddress.getType().getName();
	%>

		<liferay-util:buffer var="newOriganizationAddress">

			<li class="<%= origanizationAddress.isPrimary() ? "primary" : "" %>">
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

				<c:if test="<%= origanizationAddress.isMailing() %>">(<liferay-ui:message key="mailing" />)</c:if>
			</li>
		</liferay-util:buffer>

<%
		sb.append(newOriganizationAddress);
	}

	sb.append(html.substring(organizationAddressDataEnd));

	html = sb.toString();
}

int personalAddressDataStart = _getAddressDataStart(html, personalAddressTitle);
int personalAddressDataEnd = _getAddressDataEnd(html, personalAddressDataStart);

if ((personalAddressDataStart >= 0) && (personalAddressDataEnd >= 0)) {
	StringBundler sb = new StringBundler(personalAddresses.size() + 2);

	sb.append(html.substring(0, personalAddressDataStart));

	for (Address personalAddress : personalAddresses) {
		String street1 = personalAddress.getStreet1();
		String street2 = personalAddress.getStreet2();
		String street3 = personalAddress.getStreet3();

		String zipCode = personalAddress.getZip();
		String city = personalAddress.getCity();
		Region region = personalAddress.getRegion();

		String mailingName = personalAddress.getType().getName();
	%>

		<liferay-util:buffer var="newPersonalAddress">

			<li class="<%= personalAddress.isPrimary() ? "primary" : "" %>">
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

				<c:if test="<%= personalAddress.isMailing() %>">(<liferay-ui:message key="mailing" />)</c:if>
			</li>
		</liferay-util:buffer>

<%
		sb.append(newPersonalAddress);
	}

	sb.append(html.substring(personalAddressDataEnd));

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

private int _getAddressDataStart(String html, String title) {
	int x = html.indexOf(_H4_OPEN + title + _H4_CLOSE);

	if (x < 0) {
		return -1;
	}

	return html.indexOf(_LI_OPEN, x);
}

private List<Address> _getAddresses(String className, long classPK) throws PortalException, SystemException {
	List<Address> addresses = new ArrayList<Address>();

	if (classPK > 0) {
		addresses = AddressServiceUtil.getAddresses(className, classPK);
	}

	return addresses;
}

private static final String _CLASS_MAILING_NAME = "class=\"mailing-name\"";

private static final String _H4_CLOSE = "</h4>";

private static final String _H4_OPEN = "<h4>";

private static final String _LI_CLOSE = "</li>";

private static final String _LI_OPEN = "<li";

private static final String _UL_CLOSE = "</ul>";
%>