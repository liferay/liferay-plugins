/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portlet.shindig.social.service;

import com.liferay.portal.kernel.servlet.ImageServletTokenUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringMaker;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shindig.gadgets.GadgetToken;
import org.apache.shindig.social.ResponseItem;
import org.apache.shindig.social.opensocial.PeopleService;
import org.apache.shindig.social.opensocial.model.ApiCollection;
import org.apache.shindig.social.opensocial.model.Email;
import org.apache.shindig.social.opensocial.model.Enum;
import org.apache.shindig.social.opensocial.model.IdSpec;
import org.apache.shindig.social.opensocial.model.Name;
import org.apache.shindig.social.opensocial.model.Person;
import org.apache.shindig.social.opensocial.model.Phone;

import org.json.JSONException;

public class BasicPeopleService implements PeopleService {

	private static final Comparator<Person> NAME_COMPARATOR =
		new Comparator<Person>() {
			public int compare(Person person1, Person person2) {
				String name1 = person1.getName().getUnstructured();
				String name2 = person2.getName().getUnstructured();

				return name1.compareTo(name2);
			}
		};

	public ResponseItem<ApiCollection<Person>> getPeople(
		List<String> userIds, SortOrder sortOrder, FilterType filter, int first,
		int max, Set<String> profileDetails, GadgetToken token) {

	    List<Person> people = new ArrayList<Person>();

		Person person = null;

		for (String userId : userIds) {
		    try {
				if (userId.startsWith("G-")) {
				    Group group = GroupLocalServiceUtil.getGroup(
					    Long.parseLong(userId.substring("G-".length())));

					if (group.isCommunity()) {
					    person = getPerson(group, profileDetails, token);
					}
					else if (group.isOrganization()) {
						Organization org =
							OrganizationLocalServiceUtil.getOrganization(
								group.getClassPK());

					    person = getPerson(org, profileDetails, token);
					}
				}
				else {
				    User user = UserLocalServiceUtil.getUserById(
						Long.parseLong(userId));

				    person = getPerson(user, profileDetails, token);
				}

				people.add(person);
		    }
		    catch (Exception e) {
		    	_log.error(e, e);
		    }
		}

	    // We can pretend that by default the people are in top friends order

		if (sortOrder.equals(SortOrder.name)) {
			Collections.sort(people, NAME_COMPARATOR);
	    }

	    // TODO: The samplecontainer doesn't really have the concept of HAS_APP
		// so we can't support any filters yet. We should fix this.

	    int totalSize = people.size();
	    int last = first + max;
	    people = people.subList(first, Math.min(last, totalSize));

	    ApiCollection<Person> collection = new ApiCollection<Person>(
	    	people, first, totalSize);

	    return new ResponseItem<ApiCollection<Person>>(collection);
	}

	public List<String> getIds(IdSpec idSpec, GadgetToken token)
		throws JSONException {

		List<String> ids = new ArrayList<String>();

		switch(idSpec.getType()) {
			case OWNER:
				ids.add(token.getOwnerId());
				break;
			case VIEWER:
				ids.add(token.getViewerId());
				break;
			case OWNER_FRIENDS:
				if (!token.getOwnerId().startsWith("G-")) {
					try {
						User owner = UserLocalServiceUtil.getUserById(
							Long.parseLong(token.getOwnerId()));

						List<User> friends =
							UserLocalServiceUtil.getSocialUsers(
								owner.getCompanyId(), owner.getUserId(), 1,
								-1, -1);

						for (User friend : friends) {
							ids.add(String.valueOf(friend.getUserId()));
						}
					}
					catch (Exception e) {
					}
				}
				break;
			case VIEWER_FRIENDS:
				if (!token.getViewerId().startsWith("G-")) {
					try {
						User viewer = UserLocalServiceUtil.getUserById(
							Long.parseLong(token.getViewerId()));

						List<User> friends =
							UserLocalServiceUtil.getSocialUsers(
								viewer.getCompanyId(), viewer.getUserId(), 1,
								-1, -1);

						for (User friend : friends) {
							ids.add(String.valueOf(friend.getUserId()));
						}
					}
					catch (Exception e) {
					}
				}
				break;
			case USER_IDS:
				ids.addAll(idSpec.fetchUserIds());
				break;
		}

		return ids;
	}

	protected Person getPerson(
		Group group, Set<String> profileDetails, GadgetToken token) {

		Person person = null;

		try {
			person = new Person(
				"G-" + group.getGroupId(),
				new Name(group.getName() + " (Community)"));

			person.setGender(new Enum<Enum.Gender>(
				Enum.Gender.MALE));

			if (token.getViewerId() == person.getId()) {
				person.setIsViewer(true);
			}
			else if (token.getOwnerId() == person.getId()) {
				person.setIsOwner(true);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return person;
	}

	protected Person getPerson(
		Organization org, Set<String> profileDetails, GadgetToken token) {

		Person person = null;

		try {
			person = new Person(
				"G-" + org.getGroup().getGroupId(),
				new Name(org.getName() + " (Organization)"));

			if (profileDetails.contains("phone_numbers")) {
				person.setPhoneNumbers(getPhoneNumbers(
					Organization.class.getName(), org.getOrganizationId()));
			}

			if (profileDetails.contains("gender")) {
				person.setGender(new Enum<Enum.Gender>(
					Enum.Gender.MALE));
			}

			if (token.getViewerId() == person.getId()) {
				person.setIsViewer(true);
			}
			else if (token.getOwnerId() == person.getId()) {
				person.setIsOwner(true);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return person;
	}

	protected Person getPerson(
		User user, Set<String> profileDetails, GadgetToken token) {

		Person person = null;

		try {
			person = new Person(
				String.valueOf(user.getUserId()), new Name(user.getFullName()));

			if (profileDetails.contains("about_me")) {
				person.setAboutMe(user.getComments());
			}

			if (profileDetails.contains("age")) {
			    Calendar dateOfBirth = new GregorianCalendar();
			    dateOfBirth.setTime(user.getBirthday());

			    Calendar today = Calendar.getInstance();
			    int age = today.get(
			    	Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

			    dateOfBirth.add(Calendar.YEAR, age);

			    if (today.before(dateOfBirth)) {
			        age--;
			    }

				person.setAge(age);
			}

			if (profileDetails.contains("date_of_birth")) {
				person.setDateOfBirth(user.getBirthday());
			}

			if (profileDetails.contains("emails")) {
				person.setEmails(getEmails(user));
			}

			if (profileDetails.contains("gender")) {
				if (user.isFemale()) {
					person.setGender(new Enum<Enum.Gender>(Enum.Gender.FEMALE));
				} else {
					person.setGender(new Enum<Enum.Gender>(Enum.Gender.MALE));
				}
			}

			if (profileDetails.contains("nickname")) {
				person.setNickname(user.getScreenName());
			}

			if (profileDetails.contains("phone_numbers")) {
				person.setPhoneNumbers(getPhoneNumbers(
					Contact.class.getName(), user.getContactId()));
			}

			if (profileDetails.contains("thumbnailUrl")) {
				long portraitId = user.getPortraitId();
				String tokenId = ImageServletTokenUtil.getToken(user.getPortraitId());

				StringMaker sm = new StringMaker();
				sm.append(PortalUtil.getPathImage());
				sm.append("/user_");
				sm.append(user.isFemale() ? "female" : "male");
				sm.append("_portrait?img_id=");
				sm.append(portraitId);
				sm.append("&t=");
				sm.append(tokenId);

				person.setThumbnailUrl(sm.toString());
			}

			if (profileDetails.contains("time_zone")) {
				person.setTimeZone(new Long(user.getTimeZone().getRawOffset()));
			}

			if (token.getViewerId() == person.getId()) {
				person.setIsViewer(true);
			}
			else if (token.getOwnerId() == person.getId()) {
				person.setIsOwner(true);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return person;
	}

	protected List<Email> getEmails(User user) {
		List<Email> emails = new ArrayList<Email>();

		emails.add(new Email(user.getEmailAddress(), "Primary"));

		try {
			List<EmailAddress> emailAddresses =
				EmailAddressLocalServiceUtil.getEmailAddresses(
					user.getCompanyId(), User.class.getName(),
					user.getUserId());

			for (EmailAddress emailAddress : emailAddresses) {
				emails.add(new Email(
					emailAddress.getAddress(),
					emailAddress.getType().getName()));
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return emails;
	}

	protected List<Phone> getPhoneNumbers(String className, long classPK) {
		List<Phone> phoneNumbers = new ArrayList<Phone>();

		try {
			List<com.liferay.portal.model.Phone> liferayPhones =
				PhoneServiceUtil.getPhones(className, classPK);

			for (com.liferay.portal.model.Phone liferayPhone : liferayPhones) {
				phoneNumbers.add(new Phone(
					liferayPhone.getNumber(),
					liferayPhone.getType().getName()));
			}
		}
		catch (Exception e) {
		}

		return phoneNumbers;
	}

	private static final Log _log = LogFactory.getLog(BasicPeopleService.class);

}
