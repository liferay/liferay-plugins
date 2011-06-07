<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<%@ page import="com.liferay.portal.model.Contact" %>
<%@ page import="com.liferay.portal.model.User" %>
<%@ page import="com.liferay.portal.service.ContactLocalServiceUtil" %>
<%@ page import="com.liferay.portal.service.UserLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringBundler" %>

<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map" %>

<%@ page import="javax.portlet.PortletRequest" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<table class="lfr-table">
<tr>
	<th>
		Key
	</th>
	<th>
		Expected Value
	</th>
	<th>
		Actual Value
	</th>
	<th></th>
	<th></th>
</tr>

<%
long companyId = user.getCompanyId();
long userId = user.getUserId();
int birthdayMonth = Calendar.DECEMBER;
int birthdayDay = 29;
int birthdayYear = 1987;

Date birthdate = CalendarFactoryUtil.getCalendar(birthdayYear, birthdayMonth, birthdayDay).getTime();
String gender = "female";
Boolean isMale = (gender.equals("male") ? true : false);
String employer = "";
String department = "";
String jobTitle = "manager";
String namePrefix = "Dr.";
String firstName = "Test";
String lastName = "User";
String middleName = "Ing";
String nameSuffix = "Phd.";
String nickName = "test";

// String homePostalName = "";
// String homeStreet = "1234 Liferay St.";
// String homeCity = "Liferay City";
// String homeState = "CA";
// String homePostalCode = "91234";
// String homeCountry = "USA";
// String homeOrganization = "";
// String homePersonalIntCode = "";
// String homePersonalLocCode = "";
// String homePersonalPhoneNumber = "123-456-7890";
// String homePersonalPhoneExt = "1";
// String homePersonalPhoneComment = "";
// String homeFaxIntCode = "";
// String homeFaxLocCode = "";
// String homeFaxPhoneNumber = "999-424-4645";
// String homeFaxPhoneExt = "2";
// String homeFaxPhoneComment = "";
// String homeMobileIntCode = "";
// String homeMobileLocCode = "";
// String homeMobilePhoneNumber = "759-534-6745";
// String homeMobilePhoneExt = "3";
// String homeMobilePhoneComment = "";
// String homePagerIntCode = "";
// String homePagerLocCode = "";
// String homePagerPhoneNumber = "119-434-8215";
// String homePagerPhoneExt = "4";
// String homePagerPhoneComment = "";

String homePostalName = "";
String homeStreet = "";
String homeCity = "";
String homeState = "";
String homePostalCode = "";
String homeCountry = "";
String homeOrganization = "";
String homePersonalIntCode = "";
String homePersonalLocCode = "";
String homePersonalPhoneNumber = "";
String homePersonalPhoneExt = "";
String homePersonalPhoneComment = "";
String homeFaxIntCode = "";
String homeFaxLocCode = "";
String homeFaxPhoneNumber = "";
String homeFaxPhoneExt = "";
String homeFaxPhoneComment = "";
String homeMobileIntCode = "";
String homeMobileLocCode = "";
String homeMobilePhoneNumber = "";
String homeMobilePhoneExt = "";
String homeMobilePhoneComment = "";
String homePagerIntCode = "";
String homePagerLocCode = "";
String homePagerPhoneNumber = "";
String homePagerPhoneExt = "";
String homePagerPhoneComment = "";

String homeEmailAddress = "test2@liferay.com";
String homeURI = "";

// String businessPostalName = "";
// String businessStreet = "1220 Brea Canyon Rd.";
// String businessCity = "Walnut";
// String businessState = "CA";
// String businessPostalCode = "91789";
// String businessCountry = "USA";
// String businessOrganization = "";
// String businessIntCode = "";
// String businessLocCode = "";
// String businessPhoneNumber = "877-543-7890";
// String businessPhoneExt = "1";
// String businessPhoneComment = "";
// String businessFaxIntCode = "";
// String businessFaxLocCode = "";
// String businessFaxPhoneNumber = "877-424-4645";
// String businessFaxPhoneExt = "2";
// String businessFaxPhoneComment = "";
// String businessMobileIntCode = "";
// String businessMobileLocCode = "";
// String businessMobilePhoneNumber = "759-534-6745";
// String businessMobilePhoneExt = "3";
// String businessMobilePhoneComment = "";
// String businessPagerIntCode = "";
// String businessPagerLocCode = "";
// String businessPagerPhoneNumber = "119-434-8215";
// String businessPagerPhoneExt = "4";
// String businessPagerPhoneComment = "";

String businessPostalName = "";
String businessStreet = "";
String businessCity = "";
String businessState = "";
String businessPostalCode = "";
String businessCountry = "";
String businessOrganization = "";
String businessIntCode = "";
String businessLocCode = "";
String businessPhoneNumber = "";
String businessPhoneExt = "";
String businessPhoneComment = "";
String businessFaxIntCode = "";
String businessFaxLocCode = "";
String businessFaxPhoneNumber = "";
String businessFaxPhoneExt = "";
String businessFaxPhoneComment = "";
String businessMobileIntCode = "";
String businessMobileLocCode = "";
String businessMobilePhoneNumber = "";
String businessMobilePhoneExt = "";
String businessMobilePhoneComment = "";
String businessPagerIntCode = "";
String businessPagerLocCode = "";
String businessPagerPhoneNumber = "";
String businessPagerPhoneExt = "";
String businessPagerPhoneComment = "";

String businessEmailAddress = "test2@liferay.com";
String businessURI = "";

Object[] expectedValues = new Object[] {
		companyId,
		userId,
		birthdate,
		gender,
		employer,
		department,
		jobTitle,
		namePrefix,
		firstName,
		lastName,
		middleName,
		nameSuffix,
		nickName,
		homePostalName,
		homeStreet,
		homeCity,
		homeState,
		homePostalCode,
		homeCountry,
		homeOrganization,
		homePersonalIntCode,
		homePersonalLocCode,
		homePersonalPhoneNumber,
		homePersonalPhoneExt,
		homePersonalPhoneComment,
		homeFaxIntCode,
		homeFaxLocCode,
		homeFaxPhoneNumber,
		homeFaxPhoneExt,
		homeFaxPhoneComment,
		homeMobileIntCode,
		homeMobileLocCode,
		homeMobilePhoneNumber,
		homeMobilePhoneExt,
		homeMobilePhoneComment,
		homePagerIntCode,
		homePagerLocCode,
		homePagerPhoneNumber,
		homePagerPhoneExt,
		homePagerPhoneComment,
		homeEmailAddress,
		homeURI,
		homePostalName,
		homeStreet,
		homeCity,
		homeState,
		homePostalCode,
		homeCountry,
		homeOrganization,
		businessIntCode,
		businessLocCode,
		businessPhoneNumber,
		businessPhoneExt,
		businessPhoneComment,
		businessFaxIntCode,
		businessFaxLocCode,
		businessFaxPhoneNumber,
		businessFaxPhoneExt,
		businessFaxPhoneComment,
		businessMobileIntCode,
		businessMobileLocCode,
		businessMobilePhoneNumber,
		businessMobilePhoneExt,
		businessMobilePhoneComment,
		businessPagerIntCode,
		businessPagerLocCode,
		businessPagerPhoneNumber,
		businessPagerPhoneExt,
		businessPagerPhoneComment,
		businessEmailAddress,
		businessURI,
	};

	user.setJobTitle(jobTitle);
	user.setFirstName(firstName);
	user.setLastName(lastName);
	user.setMiddleName(middleName);
	user.setEmailAddress(homeEmailAddress);

	UserLocalServiceUtil.updateUser(user);

	contact.setBirthday(CalendarFactoryUtil.getCalendar(birthdayYear, birthdayMonth, birthdayDay).getTime());
	contact.setMale(isMale);

	ContactLocalServiceUtil.updateContact(contact);

Map userInfo = (Map)renderRequest.getAttribute(PortletRequest.USER_INFO);

if (userInfo != null) {
	Object[] keys = userInfo.keySet().toArray();
	Object[] values = userInfo.values().toArray();

	for (int i = 0; i < keys.length; i++){
	String expectedValue = expectedValues[i].toString();
	String key = keys[i].toString();
	String value = values[i].toString();
		%>
		<tr>
		<td>
		<%= key %>
		</td>
		<td>
		<%= expectedValue %>
		</td>
		<td>
		<%= value %>
		</td>
		<td>
		<%
			if (expectedValue.equals(value)) {
			%>

				PASSED

			<%
			}
			else {
			%>

				FAILED

			<%
			}
			%>
		</tr>
		</tr>
		<%
	}
}
%>
</table>