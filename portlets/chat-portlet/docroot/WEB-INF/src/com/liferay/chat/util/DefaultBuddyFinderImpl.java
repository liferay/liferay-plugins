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

package com.liferay.chat.util;

import com.liferay.chat.jabber.JabberUtil;
import com.liferay.chat.service.StatusLocalServiceUtil;
import com.liferay.chat.util.comparator.BuddyComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Ankit Srivastava
 * @author Tibor Lipusz
 */
public class DefaultBuddyFinderImpl implements BuddyFinder {

	@Override
	public List<Object[]> getBuddies(long companyId, long userId) {
		long modifiedDate =
			System.currentTimeMillis() - ChatConstants.ONLINE_DELTA;

		List<Object[]> buddies = null;

		if (PortletPropsValues.BUDDY_LIST_STRATEGY.equals("all")) {
			buddies = StatusLocalServiceUtil.getAllStatuses(
				companyId, userId, modifiedDate, 0,
				PortletPropsValues.BUDDY_LIST_MAX_BUDDIES);
		}
		else if (PortletPropsValues.BUDDY_LIST_STRATEGY.equals("communities") ||
				 PortletPropsValues.BUDDY_LIST_STRATEGY.equals("sites")) {

			buddies = StatusLocalServiceUtil.getGroupStatuses(
				userId, modifiedDate,
				PortletPropsValues.BUDDY_LIST_SITE_EXCLUDES, 0,
				PortletPropsValues.BUDDY_LIST_MAX_BUDDIES);
		}
		else if (PortletPropsValues.BUDDY_LIST_STRATEGY.equals("friends") ||
				 PortletPropsValues.BUDDY_LIST_STRATEGY.equals("social")) {

			buddies = StatusLocalServiceUtil.getSocialStatuses(
				userId,
				PortletPropsValues.BUDDY_LIST_ALLOWED_SOCIAL_RELATION_TYPES,
				modifiedDate, 0, PortletPropsValues.BUDDY_LIST_MAX_BUDDIES);
		}
		else if (PortletPropsValues.BUDDY_LIST_STRATEGY.equals(
					"communities,friends") ||
				 PortletPropsValues.BUDDY_LIST_STRATEGY.equals(
					"sites,social") ||
				 PortletPropsValues.BUDDY_LIST_STRATEGY.equals(
					"friends,sites")) {

			List<Object[]> groupBuddies =
				StatusLocalServiceUtil.getGroupStatuses(
					userId, modifiedDate,
					PortletPropsValues.BUDDY_LIST_SITE_EXCLUDES, 0,
					PortletPropsValues.BUDDY_LIST_MAX_BUDDIES);
			List<Object[]> socialBuddies =
				StatusLocalServiceUtil.getSocialStatuses(
					userId,
					PortletPropsValues.BUDDY_LIST_ALLOWED_SOCIAL_RELATION_TYPES,
					modifiedDate, 0, PortletPropsValues.BUDDY_LIST_MAX_BUDDIES);

			buddies = new ArrayList<>(
				groupBuddies.size() + socialBuddies.size());

			buddies.addAll(groupBuddies);

			BuddyComparator buddyComparator = new BuddyComparator(true);

			for (Object[] socialBuddy : socialBuddies) {
				if (Collections.binarySearch(
						groupBuddies, socialBuddy, buddyComparator) < 0) {

					buddies.add(socialBuddy);
				}
			}

			Collections.sort(buddies, buddyComparator);
		}
		else {
			buddies = new ArrayList<>();
		}

		buddies = JabberUtil.getStatuses(companyId, userId, buddies);

		return buddies;
	}

}