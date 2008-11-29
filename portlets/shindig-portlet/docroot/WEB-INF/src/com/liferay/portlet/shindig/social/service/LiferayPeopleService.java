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

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.shindig.util.ShindigUtil;
import com.liferay.portlet.social.model.SocialRelationConstants;

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
import org.apache.shindig.social.opensocial.model.IdSpec;
import org.apache.shindig.social.opensocial.model.Person;

import org.json.JSONException;

/**
 * <a href="BasicPeopleService.java.html"><b><i>View Source</i></b></a>
 *
 * @author Raymond Augé
 *
 */
public class LiferayPeopleService implements PeopleService {

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
					    person = ShindigUtil.getPerson(
					    	group, profileDetails, token);
					}
					else if (group.isOrganization()) {
						Organization org =
							OrganizationLocalServiceUtil.getOrganization(
								group.getClassPK());

					    person = ShindigUtil.getPerson(
					    	org, profileDetails, token);
					}
				}
				else {
				    User user = UserLocalServiceUtil.getUserById(
						Long.parseLong(userId));

				    person = ShindigUtil.getPerson(user, profileDetails, token);
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
								owner.getUserId(),
								SocialRelationConstants.TYPE_BI_FRIEND,
								QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

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
								viewer.getUserId(),
								SocialRelationConstants.TYPE_BI_FRIEND,
								QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

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

	private static final Log _log = LogFactory.getLog(LiferayPeopleService.class);

}
