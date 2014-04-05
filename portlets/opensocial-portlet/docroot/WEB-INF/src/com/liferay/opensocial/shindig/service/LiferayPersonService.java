/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.opensocial.shindig.service;

import com.liferay.opensocial.shindig.util.ShindigUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.EmailAddress;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.service.EmailAddressLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.PhoneServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.webserver.WebServerServletTokenUtil;
import com.liferay.portlet.social.model.SocialRelationConstants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletResponse;

import org.apache.shindig.auth.SecurityToken;
import org.apache.shindig.common.util.ImmediateFuture;
import org.apache.shindig.protocol.ProtocolException;
import org.apache.shindig.protocol.RestfulCollection;
import org.apache.shindig.social.core.model.ListFieldImpl;
import org.apache.shindig.social.core.model.NameImpl;
import org.apache.shindig.social.core.model.PersonImpl;
import org.apache.shindig.social.opensocial.model.ListField;
import org.apache.shindig.social.opensocial.model.ListField.Field;
import org.apache.shindig.social.opensocial.model.Name;
import org.apache.shindig.social.opensocial.model.Person;
import org.apache.shindig.social.opensocial.model.Person.Gender;
import org.apache.shindig.social.opensocial.spi.CollectionOptions;
import org.apache.shindig.social.opensocial.spi.GroupId;
import org.apache.shindig.social.opensocial.spi.PersonService;
import org.apache.shindig.social.opensocial.spi.UserId;

/**
 * @author Michael Young
 */
public class LiferayPersonService implements PersonService {

	public Future<RestfulCollection<Person>> getPeople(
			Set<UserId> userIds, GroupId groupId,
			CollectionOptions collectionOptions, Set<String> fields,
			SecurityToken securityToken)
		throws ProtocolException {

		try {
			RestfulCollection<Person> people = doGetPeople(
				userIds, groupId, collectionOptions, fields, securityToken);

			return ImmediateFuture.newInstance(people);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			throw new ProtocolException(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(),
				e);
		}
	}

	public Future<Person> getPerson(
			UserId userId, Set<String> fields, SecurityToken securityToken)
		throws ProtocolException {

		try {
			Person person = doGetPerson(userId, fields, securityToken);

			return ImmediateFuture.newInstance(person);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			throw new ProtocolException(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage(),
				e);
		}
	}

	protected RestfulCollection<Person> doGetPeople(
			Set<UserId> userIds, GroupId groupId,
			CollectionOptions collectionOptions, Set<String> fields,
			SecurityToken securityToken)
		throws Exception {

		List<Person> people = new ArrayList<Person>();

		for (UserId userId : userIds) {
			Person person = null;

			String userIdString = userId.getUserId(securityToken);

			GroupId.Type groupIdType = groupId.getType();

			if (groupIdType.equals(GroupId.Type.all) ||
				groupIdType.equals(GroupId.Type.friends) ||
				groupIdType.equals(GroupId.Type.groupId)) {

				long userIdLong = GetterUtil.getLong(userIdString);

				User user = UserLocalServiceUtil.getUserById(userIdLong);

				List<User> friends = UserLocalServiceUtil.getSocialUsers(
					user.getUserId(), SocialRelationConstants.TYPE_BI_FRIEND,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

				for (User friend : friends) {
					person = getUserPerson(friend, fields, securityToken);

					people.add(person);
				}
			}
			else if (groupIdType.equals(GroupId.Type.self)) {
				person = doGetPerson(userId, fields, securityToken);

				people.add(person);
			}
		}

		return new RestfulCollection<Person>(
			people, collectionOptions.getFirst(), people.size(),
			collectionOptions.getMax());
	}

	protected Person doGetPerson(
			UserId userId, Set<String> fields, SecurityToken securityToken)
		throws Exception {

		String userIdString = userId.getUserId(securityToken);

		Person person = null;

		if (userIdString.startsWith("G-")) {
			String groupId = userIdString.substring("G-".length());

			person = getGroupPerson(groupId);
		}
		else {
			long userIdLong = GetterUtil.getLong(userIdString);

			User user = UserLocalServiceUtil.getUserById(userIdLong);

			if (!ShindigUtil.isValidUser(user)) {
				return null;
			}

			person = getUserPerson(user, fields, securityToken);
		}

		return person;
	}

	protected List<ListField> getEmails(User user) throws Exception {
		List<ListField> emails = new ArrayList<ListField>();

		ListField email = new ListFieldImpl(
			Field.PRIMARY.toString(), user.getEmailAddress());

		emails.add(email);

		List<EmailAddress> emailAddresses =
			EmailAddressLocalServiceUtil.getEmailAddresses(
				user.getCompanyId(), User.class.getName(), user.getUserId());

		for (EmailAddress emailAddress : emailAddresses) {
			email = new ListFieldImpl(
				emailAddress.getType().getName(), emailAddress.getAddress());

			emails.add(email);
		}

		return emails;
	}

	protected Person getGroupPerson(String groupId) throws Exception {
		Person person = null;

		long groupIdLong = GetterUtil.getLong(groupId);

		Group group = GroupLocalServiceUtil.getGroup(groupIdLong);

		if (group.isOrganization()) {
			Organization organization =
				OrganizationLocalServiceUtil.getOrganization(
					group.getClassPK());

			Name name = new NameImpl(
				organization.getName() + " (Organization)");

			person = new PersonImpl(groupId, name.getFormatted(), name);

			List<ListField> phoneNumbers = getPhoneNumbers(
				Organization.class.getName(), organization.getOrganizationId());

			person.setPhoneNumbers(phoneNumbers);
		}
		else if (group.isRegularSite()) {
			Name name = new NameImpl(group.getName() + " (Site)");

			person = new PersonImpl(groupId, name.getFormatted(), name);
		}

		person.setGender(Gender.male);

		return person;
	}

	protected List<ListField> getPhoneNumbers(String className, long classPK)
		throws Exception {

		List<ListField> phoneNumbers = new ArrayList<ListField>();

		List<com.liferay.portal.model.Phone> liferayPhones =
			PhoneServiceUtil.getPhones(className, classPK);

		for (com.liferay.portal.model.Phone liferayPhone : liferayPhones) {
			ListField phoneNumber = new ListFieldImpl(
				liferayPhone.getType().getName(), liferayPhone.getNumber());

			phoneNumbers.add(phoneNumber);
		}

		return phoneNumbers;
	}

	protected Person getUserPerson(
			User user, Set<String> fields, SecurityToken securityToken)
		throws Exception {

		Name name = new NameImpl(user.getFullName());

		Person person = new PersonImpl(
			String.valueOf(user.getUserId()), user.getScreenName(), name);

		StringBundler sb = new StringBundler(4);

		sb.append(securityToken.getDomain());
		sb.append(PortalUtil.getPathFriendlyURLPublic());
		sb.append(StringPool.SLASH);
		sb.append(user.getScreenName());

		person.setProfileUrl(sb.toString());

		sb.setIndex(0);

		sb.append(securityToken.getDomain());
		sb.append(PortalUtil.getPathImage());
		sb.append("/user_");
		sb.append(user.isFemale() ? "female" : "male");
		sb.append("_portrait?img_id=");
		sb.append(user.getPortraitId());
		sb.append("&t=");
		sb.append(WebServerServletTokenUtil.getToken(user.getPortraitId()));

		person.setThumbnailUrl(sb.toString());

		if (fields.contains(Person.Field.ABOUT_ME.toString())) {
			person.setAboutMe(user.getComments());
		}

		if (fields.contains(Person.Field.AGE.toString())) {
			Calendar birthday = new GregorianCalendar();

			birthday.setTime(user.getBirthday());

			Calendar today = Calendar.getInstance();

			int age = today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);

			birthday.add(Calendar.YEAR, age);

			if (today.before(birthday)) {
				age--;
			}

			person.setAge(age);
		}

		if (fields.contains(Person.Field.BIRTHDAY.toString())) {
			person.setBirthday(user.getBirthday());
		}

		if (fields.contains(Person.Field.EMAILS)) {
			person.setEmails(getEmails(user));
		}

		if (fields.contains(Person.Field.GENDER.toString())) {
			if (user.isFemale()) {
				person.setGender(Gender.female);
			}
			else {
				person.setGender(Gender.male);
			}
		}

		if (fields.contains(Person.Field.NICKNAME.toString())) {
			person.setNickname(user.getScreenName());
		}

		if (fields.contains(Person.Field.PHONE_NUMBERS.toString())) {
			List<ListField> phoneNumbers = getPhoneNumbers(
				Contact.class.getName(), user.getContactId());

			person.setPhoneNumbers(phoneNumbers);
		}

		if (fields.contains(Person.Field.UTC_OFFSET.toString())) {
			person.setUtcOffset(new Long(user.getTimeZone().getRawOffset()));
		}

		if (securityToken.getOwnerId().equals(person.getId())) {
			person.setIsOwner(true);
		}

		if (securityToken.getViewerId().equals(person.getId())) {
			person.setIsViewer(true);
		}

		return person;
	}

	private static Log _log = LogFactoryUtil.getLog(LiferayPersonService.class);

}