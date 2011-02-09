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

package com.liferay.contacts.util;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.Country;
import com.liferay.portal.model.EmailAddress;
import com.liferay.portal.model.ListType;
import com.liferay.portal.model.Phone;
import com.liferay.portal.model.Region;
import com.liferay.portal.model.User;
import com.liferay.portal.model.Website;
import com.liferay.portal.service.AddressLocalServiceUtil;
import com.liferay.portal.service.CountryServiceUtil;
import com.liferay.portal.service.EmailAddressLocalServiceUtil;
import com.liferay.portal.service.ListTypeServiceUtil;
import com.liferay.portal.service.PhoneLocalServiceUtil;
import com.liferay.portal.service.RegionServiceUtil;
import com.liferay.portal.service.WebsiteLocalServiceUtil;

import java.lang.StringBuilder;

import java.util.List;

/**
 * @author Ryan Park
 */
public class ContactsUtil {

	public static String getVCard(User user) throws Exception {
		Contact contact = user.getContact();

		StringBuilder sb = new StringBuilder();

		// Heading

		sb.append("BEGIN:VCARD\nVERSION:3.0\n");

		// Name

		sb.append("N:");
		sb.append(user.getLastName());
		sb.append(StringPool.SEMICOLON);
		sb.append(user.getFirstName());
		sb.append(StringPool.SEMICOLON);
		sb.append(user.getMiddleName());
		sb.append(StringPool.SEMICOLON);

		int prefixId = contact.getPrefixId();

		if (prefixId > 0) {
			ListType listType = ListTypeServiceUtil.getListType(prefixId);

			sb.append(listType.getName());
		}

		sb.append(StringPool.SEMICOLON);

		int suffixId = contact.getSuffixId();

		if (suffixId > 0) {
			ListType listType = ListTypeServiceUtil.getListType(suffixId);

			sb.append(listType.getName());
		}

		sb.append(StringPool.NEW_LINE);

		sb.append("FN:");
		sb.append(user.getFullName());
		sb.append(StringPool.NEW_LINE);

		// Job Title

		String jobTitle = user.getJobTitle();

		if (Validator.isNotNull(jobTitle)) {
			sb.append("TITLE:");
			sb.append(jobTitle);
			sb.append(StringPool.NEW_LINE);
		}

		// Email

		sb.append("EMAIL;TYPE=INTERNET;TYPE=HOME:");
		sb.append(user.getEmailAddress());
		sb.append(StringPool.NEW_LINE);

		List<EmailAddress> emailAddresses =
			EmailAddressLocalServiceUtil.getEmailAddresses(
				user.getCompanyId(), Contact.class.getName(),
				contact.getContactId());

		for (EmailAddress emailAddress : emailAddresses) {
			sb.append("EMAIL;TYPE=INTERNET;TYPE=");

			ListType listType = emailAddress.getType();

			sb.append(listType.getName().toUpperCase());
			sb.append(StringPool.COLON);
			sb.append(emailAddress.getAddress());

			sb.append(StringPool.NEW_LINE);
		}

		// Phones

		List<Phone> phones = PhoneLocalServiceUtil.getPhones(
			user.getCompanyId(), Contact.class.getName(),
			contact.getContactId());

		for (Phone phone : phones) {
			sb.append("TEL;TYPE=");

			ListType listType = phone.getType();

			sb.append(listType.getName().toUpperCase());
			sb.append(StringPool.COLON);
			sb.append(phone.getNumber());
			sb.append(StringPool.SPACE);
			sb.append(phone.getExtension());

			sb.append(StringPool.NEW_LINE);
		}

		// Addresses

		List<Address> addresses = AddressLocalServiceUtil.getAddresses(
			user.getCompanyId(), Contact.class.getName(),
			contact.getContactId());

		for (Address address: addresses) {
			sb.append("ADR;TYPE=");

			ListType listType = address.getType();

			sb.append(listType.getName().toUpperCase());
			sb.append(StringPool.COLON);
			sb.append(StringPool.SEMICOLON);
			sb.append(StringPool.SEMICOLON);

			if (Validator.isNotNull(address.getStreet1())) {
				sb.append(address.getStreet1());
			}

			if (Validator.isNotNull(address.getStreet2())) {
				sb.append("\\n");
				sb.append(address.getStreet2());
			}

			if (Validator.isNotNull(address.getStreet3())) {
				sb.append("\\n");
				sb.append(address.getStreet3());
			}

			sb.append(StringPool.SEMICOLON);

			if (Validator.isNotNull(address.getCity())) {
				sb.append(address.getCity());
			}

			sb.append(StringPool.SEMICOLON);

			long regionId = address.getRegionId();

			if (regionId > 0) {
				Region region = RegionServiceUtil.getRegion(regionId);

				sb.append(region.getName());
			}

			sb.append(StringPool.SEMICOLON);

			if (Validator.isNotNull(address.getZip())) {
				sb.append(address.getZip());
			}

			sb.append(StringPool.SEMICOLON);

			long countryId = address.getCountryId();

			if (countryId > 0) {
				Country country = CountryServiceUtil.getCountry(countryId);

				sb.append(country.getName());
			}

			sb.append(StringPool.NEW_LINE);
		}

		// Websites

		List<Website> websites = WebsiteLocalServiceUtil.getWebsites(
			user.getCompanyId(), Contact.class.getName(),
			contact.getContactId());

		for (Website website: websites) {
			sb.append("URL;TYPE=");

			ListType listType = website.getType();

			sb.append(listType.getName().toUpperCase());
			sb.append(StringPool.COLON);

			String url = website.getUrl();

			sb.append(url.replaceAll(StringPool.COLON, "\\:"));
			sb.append(StringPool.NEW_LINE);
		}

		// Instant Messaging

		if (Validator.isNotNull(contact.getAimSn())) {
			sb.append("X-AIM;type=OTHER;type=pref:");
			sb.append(contact.getAimSn());
			sb.append(StringPool.NEW_LINE);
		}

		if (Validator.isNotNull(contact.getIcqSn())) {
			sb.append("X-ICQ;type=OTHER;type=pref:");
			sb.append(contact.getAimSn());
			sb.append(StringPool.NEW_LINE);
		}

		if (Validator.isNotNull(contact.getJabberSn())) {
			sb.append("X-JABBER;type=OTHER;type=pref:");
			sb.append(contact.getJabberSn());
			sb.append(StringPool.NEW_LINE);
		}

		if (Validator.isNotNull(contact.getMsnSn())) {
			sb.append("X-MSN;type=OTHER;type=pref:");
			sb.append(contact.getMsnSn());
			sb.append(StringPool.NEW_LINE);
		}

		if (Validator.isNotNull(contact.getSkypeSn())) {
			sb.append("X-SKYPE;type=OTHER;type=pref:");
			sb.append(contact.getSkypeSn());
			sb.append(StringPool.NEW_LINE);
		}

		if (Validator.isNotNull(contact.getYmSn())) {
			sb.append("X-YM;type=OTHER;type=pref:");
			sb.append(contact.getYmSn());
			sb.append(StringPool.NEW_LINE);
		}

		// Footing

		sb.append("END:VCARD\n");

		return sb.toString();
	}

	public static String getVCards(List<User> users) throws Exception {
		StringBuilder sb = new StringBuilder();

		for (User user : users) {
			sb.append(getVCard(user));
		}

		return sb.toString();
	}

}