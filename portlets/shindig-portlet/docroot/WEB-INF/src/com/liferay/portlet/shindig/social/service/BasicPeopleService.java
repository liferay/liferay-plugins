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

import com.liferay.portal.NoSuchClassNameException;
import com.liferay.portal.PortalException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.ContactLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.PhoneServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shindig.gadgets.GadgetToken;
import org.apache.shindig.social.ResponseItem;
import org.apache.shindig.social.opensocial.PeopleService;
import org.apache.shindig.social.opensocial.model.ApiCollection;
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
		List<String> ids, SortOrder sortOrder, FilterType filter, int first,
		int max, Set<String> profileDetails, GadgetToken token) {

	    List<Person> people = new ArrayList<Person>();

	    String ownerToken = token.getOwnerId();
		Person owner = null;
	    Person viewer = null;

	    long ownerUserId = 0;
	    long viewerUserId = 0;

	    try {
		    User viewerUser = UserLocalServiceUtil.getUserById(
				Long.parseLong(token.getViewerId()));

		    viewerUserId = viewerUser.getUserId();

			viewer = new Person(
				String.valueOf(viewerUser.getUserId()),
				new Name(viewerUser.getFullName()));

			List<com.liferay.portal.model.Phone> liferayPhones =
				PhoneServiceUtil.getPhones(
					Contact.class.getName(), viewerUser.getContactId());

			List<Phone> phoneNumbers = new ArrayList<Phone>();

			for (com.liferay.portal.model.Phone liferayPhone : liferayPhones) {
				phoneNumbers.add(new Phone(
					liferayPhone.getNumber(),
					liferayPhone.getType().getName()));
			}

			viewer.setPhoneNumbers(phoneNumbers);

			if (viewerUser.isFemale()) {
				viewer.setGender(new Enum<Enum.Gender>(Enum.Gender.FEMALE));
			} else {
				viewer.setGender(new Enum<Enum.Gender>(Enum.Gender.MALE));
			}

			viewer.setIsViewer(true);
			people.add(viewer);
	    }
	    catch (Exception e) {
	    	_log.error(e, e);
	    }

		if (ownerToken.contains(StringPool.UNDERLINE)) {
			String[] parts = ownerToken.split(StringPool.UNDERLINE);

			long classNameId = Long.parseLong(parts[0]);
			long classPK = Long.parseLong(parts[1]);

			if (classNameId == _GROUP_CLASSNAME_ID) {
				try {
					Group ownerGroup = GroupLocalServiceUtil.getGroup(classPK);

					owner = new Person(
						String.valueOf(classNameId + StringPool.UNDERLINE +
							ownerGroup.getGroupId()),
						new Name(ownerGroup.getName() + " (Community)"));

					List<Phone> phoneNumbers = new ArrayList<Phone>();

					owner.setPhoneNumbers(phoneNumbers);

					owner.setGender(new Enum<Enum.Gender>(Enum.Gender.MALE));

					owner.setIsOwner(true);
				}
				catch (Exception e) {
			    	_log.error(e, e);
				}
			}
			else if (classNameId == _ORG_CLASSNAME_ID) {
				try {
					Organization ownerOrg =
						OrganizationLocalServiceUtil.getOrganization(classPK);

					owner = new Person(
						String.valueOf(classNameId + StringPool.UNDERLINE +
							ownerOrg.getOrganizationId()),
						new Name(ownerOrg.getName() + " (Organization)"));

					List<com.liferay.portal.model.Phone> liferayPhones =
						PhoneServiceUtil.getPhones(
							Organization.class.getName(),
							ownerOrg.getOrganizationId());

					List<Phone> phoneNumbers = new ArrayList<Phone>();

					for (com.liferay.portal.model.Phone liferayPhone : liferayPhones) {
						phoneNumbers.add(new Phone(
							liferayPhone.getNumber(),
							liferayPhone.getType().getName()));
					}

					owner.setPhoneNumbers(phoneNumbers);

					owner.setGender(new Enum<Enum.Gender>(Enum.Gender.MALE));

					owner.setIsOwner(true);
				}
				catch (Exception e) {
			    	_log.error(e, e);
				}
			}
			else if (classNameId == _USER_CLASSNAME_ID) {
				try {
				    User ownerUser = UserLocalServiceUtil.getUserById(classPK);

				    ownerUserId = ownerUser.getUserId();

					owner = new Person(
						String.valueOf(classNameId + StringPool.UNDERLINE +
							ownerUser.getUserId()),
						new Name(ownerUser.getFullName()));

					List<com.liferay.portal.model.Phone> liferayPhones =
						PhoneServiceUtil.getPhones(
							Contact.class.getName(), ownerUser.getContactId());

					List<Phone> phoneNumbers = new ArrayList<Phone>();

					for (com.liferay.portal.model.Phone liferayPhone : liferayPhones) {
						phoneNumbers.add(new Phone(
							liferayPhone.getNumber(),
							liferayPhone.getType().getName()));
					}

					owner.setPhoneNumbers(phoneNumbers);

					if (ownerUser.isFemale()) {
						owner.setGender(new Enum<Enum.Gender>(Enum.Gender.FEMALE));
					} else {
						owner.setGender(new Enum<Enum.Gender>(Enum.Gender.MALE));
					}

					owner.setIsOwner(true);
				}
				catch (Exception e) {
			    	_log.error(e, e);
				}
			}
		}

		if (owner != null && ownerUserId != viewerUserId) {
			people.add(owner);
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
				//ids.addAll(friendIds.get(token.getOwnerId()));
				break;
			case VIEWER_FRIENDS:
				//ids.addAll(friendIds.get(token.getViewerId()));
				break;
			case USER_IDS:
				ids.addAll(idSpec.fetchUserIds());
				break;
		}

		return ids;
	}

	private static long _GROUP_CLASSNAME_ID;
	private static long _ORG_CLASSNAME_ID;
	private static long _USER_CLASSNAME_ID;

	static {
		try {
			_GROUP_CLASSNAME_ID = ClassNameLocalServiceUtil.getClassName(
				Group.class.getName()).getClassNameId();
		}
		catch (Exception e) {
		}

		try {
			_ORG_CLASSNAME_ID = ClassNameLocalServiceUtil.getClassName(
				Organization.class.getName()).getClassNameId();
		}
		catch (Exception e) {
		}

		try {
			_USER_CLASSNAME_ID = ClassNameLocalServiceUtil.getClassName(
				User.class.getName()).getClassNameId();
		}
		catch (Exception e) {
		}
	}

	private static final Log _log = LogFactory.getLog(BasicPeopleService.class);

}
