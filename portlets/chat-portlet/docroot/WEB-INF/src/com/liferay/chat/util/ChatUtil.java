/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.chat.util;

import com.liferay.chat.service.StatusLocalServiceUtil;
import com.liferay.chat.util.comparator.BuddyComparator;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portlet.social.model.SocialRelationConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="ChatUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ChatUtil {

	public static List<Object[]> getBuddies(long userId)
		throws SystemException {

		long modifiedDate = System.currentTimeMillis() - Time.MINUTE;

		List<Object[]> buddies = null;

		if (PortletPropsValues.BUDDY_LIST_STRATEGY.equals("all")) {
			buddies = StatusLocalServiceUtil.getAllStatuses(
				userId, modifiedDate, 0, SearchContainer.DEFAULT_DELTA);
		}
		else if (PortletPropsValues.BUDDY_LIST_STRATEGY.equals("communities")) {
			buddies = StatusLocalServiceUtil.getGroupStatuses(
				userId, modifiedDate, 0, SearchContainer.DEFAULT_DELTA);
		}
		else if (PortletPropsValues.BUDDY_LIST_STRATEGY.equals("friends")) {
			buddies = StatusLocalServiceUtil.getSocialStatuses(
				userId, SocialRelationConstants.TYPE_BI_FRIEND,
				modifiedDate, 0, SearchContainer.DEFAULT_DELTA);
		}
		else if (PortletPropsValues.BUDDY_LIST_STRATEGY.equals(
					"communities,friends")) {

			List<Object[]> groupBuddies =
				StatusLocalServiceUtil.getGroupStatuses(
					userId, modifiedDate, 0, SearchContainer.DEFAULT_DELTA);
			List<Object[]> socialBuddies =
				StatusLocalServiceUtil.getSocialStatuses(
					userId, SocialRelationConstants.TYPE_BI_FRIEND,
					modifiedDate, 0, SearchContainer.DEFAULT_DELTA);

			buddies = new ArrayList<Object[]>(
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
			buddies = new ArrayList<Object[]>();
		}

		return buddies;
	}

}