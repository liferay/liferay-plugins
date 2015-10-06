/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.contacts.util;

import com.liferay.contacts.model.Entry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
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
import com.liferay.portlet.social.model.SocialRequestConstants;
import com.liferay.portlet.social.service.SocialRelationLocalServiceUtil;
import com.liferay.portlet.social.service.SocialRequestLocalServiceUtil;

import java.lang.reflect.Field;

import java.util.List;

/**
 * @author Ryan Park
 * @author Jonathan Lee
 */
public class ContactsUtil {

	public static JSONObject getEntryJSONObject(Entry entry) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("emailAddress", entry.getEmailAddress());
		jsonObject.put("entryId", String.valueOf(entry.getEntryId()));
		jsonObject.put("comments", entry.getComments());
		jsonObject.put("fullName", entry.getFullName());
		jsonObject.put("portalUser", false);

		return jsonObject;
	}

	public static long getGroupId(String filterBy) {
		String groupIdString = filterBy.substring(
			ContactsConstants.FILTER_BY_GROUP.length());

		return GetterUtil.getLong(groupIdString);
	}

	public static String[] getPortalPropsValue(String key) {
		try {
			ClassLoader portalClassLoader =
				PortalClassLoaderUtil.getClassLoader();

			Class<?> targetClass = portalClassLoader.loadClass(
				"com.liferay.portal.util.PropsValues");

			Field field = targetClass.getField(key);

			return (String[])field.get((Object)null);
		}
		catch (Exception e) {
		}

		return null;
	}

	public static long getSocialRelationType(String filterBy) {
		String socialRelationTypeString = filterBy.substring(
			ContactsConstants.FILTER_BY_TYPE.length());

		return GetterUtil.getLong(socialRelationTypeString);
	}

	public static JSONObject getUserJSONObject(long userId, User user)
		throws PortalException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		boolean block = SocialRelationLocalServiceUtil.hasRelation(
			userId, user.getUserId(), SocialRelationConstants.TYPE_UNI_ENEMY);

		jsonObject.put("block", block);

		jsonObject.put("contactId", String.valueOf(user.getContactId()));
		jsonObject.put("emailAddress", user.getEmailAddress());
		jsonObject.put("firstName", user.getFirstName());
		jsonObject.put("fullName", user.getFullName());
		jsonObject.put("jobTitle", user.getJobTitle());
		jsonObject.put("lastName", user.getLastName());
		jsonObject.put("portalUser", true);
		jsonObject.put("portraitId", String.valueOf(user.getPortraitId()));
		jsonObject.put("userId", String.valueOf(user.getUserId()));
		jsonObject.put("uuid", user.getUuid());

		if (!SocialRelationLocalServiceUtil.hasRelation(
				user.getUserId(), userId,
				SocialRelationConstants.TYPE_UNI_ENEMY) &&
			!SocialRelationLocalServiceUtil.hasRelation(
				userId, user.getUserId(),
				SocialRelationConstants.TYPE_UNI_ENEMY)) {

			boolean connectionRequested =
				SocialRequestLocalServiceUtil.hasRequest(
					userId, User.class.getName(), userId,
					SocialRelationConstants.TYPE_BI_CONNECTION,
					user.getUserId(), SocialRequestConstants.STATUS_PENDING);

			jsonObject.put("connectionRequested", connectionRequested);

			boolean connected =
				!connectionRequested &&
				SocialRelationLocalServiceUtil.hasRelation(
					userId, user.getUserId(),
					SocialRelationConstants.TYPE_BI_CONNECTION);

			jsonObject.put("connected", connected);

			boolean following = SocialRelationLocalServiceUtil.hasRelation(
				userId, user.getUserId(),
				SocialRelationConstants.TYPE_UNI_FOLLOWER);

			jsonObject.put("following", following);
		}

		return jsonObject;
	}

	public static String getVCard(User user) throws Exception {
		StringBundler sb = new StringBundler();

		sb.append(_getHeader());

		Contact contact = user.getContact();

		sb.append(_getName(user, contact));
		sb.append(_getJobTitle(user));
		sb.append(_getEmailAddresses(user));
		sb.append(_getPhones(user));
		sb.append(_getAddresses(user));
		sb.append(_getWebsites(user));
		sb.append(_getInstantMessaging(contact));
		sb.append(_getFooter());

		return sb.toString();
	}

	public static String getVCards(List<User> users) throws Exception {
		StringBundler sb = new StringBundler(users.size());

		for (User user : users) {
			sb.append(getVCard(user));
		}

		return sb.toString();
	}

	private static String _getAddresses(User user) throws Exception {
		List<Address> addresses = AddressLocalServiceUtil.getAddresses(
			user.getCompanyId(), Contact.class.getName(), user.getContactId());

		StringBundler sb = new StringBundler(addresses.size() * 19);

		for (Address address : addresses) {
			sb.append("ADR;TYPE=");

			ListType listType = address.getType();

			sb.append(StringUtil.toUpperCase(_getVCardListTypeName(listType)));

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

		return sb.toString();
	}

	private static String _getEmailAddresses(User user) throws Exception {
		List<EmailAddress> emailAddresses =
			EmailAddressLocalServiceUtil.getEmailAddresses(
				user.getCompanyId(), Contact.class.getName(),
				user.getContactId());

		StringBundler sb = new StringBundler(3 + (emailAddresses.size() * 5));

		sb.append("EMAIL;TYPE=INTERNET;TYPE=HOME:");
		sb.append(user.getEmailAddress());
		sb.append(StringPool.NEW_LINE);

		for (EmailAddress emailAddress : emailAddresses) {
			sb.append("EMAIL;TYPE=INTERNET;TYPE=");

			ListType listType = emailAddress.getType();

			sb.append(StringUtil.toUpperCase(listType.getName()));

			sb.append(StringPool.COLON);
			sb.append(emailAddress.getAddress());
			sb.append(StringPool.NEW_LINE);
		}

		return sb.toString();
	}

	private static String _getFooter() {
		return "END:VCARD\n";
	}

	private static String _getHeader() {
		return "BEGIN:VCARD\nVERSION:3.0\n";
	}

	private static String _getInstantMessaging(Contact contact) {
		StringBundler sb = new StringBundler(18);

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

		return sb.toString();
	}

	private static String _getJobTitle(User user) {
		String jobTitle = user.getJobTitle();

		if (Validator.isNotNull(jobTitle)) {
			return "TITLE:".concat(jobTitle).concat(StringPool.NEW_LINE);
		}

		return StringPool.BLANK;
	}

	private static String _getName(User user, Contact contact)
		throws Exception {

		StringBundler sb = new StringBundler(14);

		sb.append("N:");
		sb.append(user.getLastName());
		sb.append(StringPool.SEMICOLON);
		sb.append(user.getFirstName());
		sb.append(StringPool.SEMICOLON);
		sb.append(user.getMiddleName());
		sb.append(StringPool.SEMICOLON);

		long prefixId = contact.getPrefixId();

		if (prefixId > 0) {
			ListType listType = ListTypeServiceUtil.getListType(prefixId);

			sb.append(listType.getName());
		}

		sb.append(StringPool.SEMICOLON);

		long suffixId = contact.getSuffixId();

		if (suffixId > 0) {
			ListType listType = ListTypeServiceUtil.getListType(suffixId);

			sb.append(listType.getName());
		}

		sb.append(StringPool.NEW_LINE);
		sb.append("FN:");
		sb.append(user.getFullName());
		sb.append(StringPool.NEW_LINE);

		return sb.toString();
	}

	private static String _getPhones(User user) throws Exception {
		List<Phone> phones = PhoneLocalServiceUtil.getPhones(
			user.getCompanyId(), Contact.class.getName(), user.getContactId());

		StringBundler sb = new StringBundler(phones.size() * 7);

		for (Phone phone : phones) {
			sb.append("TEL;TYPE=");

			ListType listType = phone.getType();

			sb.append(StringUtil.toUpperCase(_getVCardListTypeName(listType)));

			sb.append(StringPool.COLON);
			sb.append(phone.getNumber());
			sb.append(StringPool.SPACE);
			sb.append(phone.getExtension());
			sb.append(StringPool.NEW_LINE);
		}

		return sb.toString();
	}

	private static String _getVCardListTypeName(ListType listType) {
		String listTypeName = listType.getName();

		if (StringUtil.equalsIgnoreCase(listTypeName, "business")) {
			listTypeName = "work";
		}
		else if (StringUtil.equalsIgnoreCase(listTypeName, "personal")) {
			listTypeName = "home";
		}

		return listTypeName;
	}

	private static String _getWebsites(User user) throws Exception {
		List<Website> websites = WebsiteLocalServiceUtil.getWebsites(
			user.getCompanyId(), Contact.class.getName(), user.getContactId());

		StringBundler sb = new StringBundler(websites.size() * 5);

		for (Website website : websites) {
			sb.append("URL;TYPE=");

			ListType listType = website.getType();

			sb.append(StringUtil.toUpperCase(_getVCardListTypeName(listType)));

			sb.append(StringPool.COLON);

			String url = website.getUrl();

			sb.append(url.replaceAll(StringPool.COLON, "\\:"));

			sb.append(StringPool.NEW_LINE);
		}

		return sb.toString();
	}

}