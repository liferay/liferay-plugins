package com.liferay.chat.util;

import com.liferay.chat.buddies.BuddiesFinder;
import com.liferay.chat.jabber.JabberUtil;
import com.liferay.chat.service.StatusLocalServiceUtil;
import com.liferay.chat.util.comparator.BuddyComparator;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portlet.social.model.SocialRelationConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author Ankit Srivastava
 */

public class DefaultBuddiesFinderImpl implements BuddiesFinder {
	
	public static final long MAX_POLL_LATENCY = Time.SECOND * 15;

	public static final long ONLINE_DELTA = Time.MINUTE;

	public List<Object[]> getBuddies(long companyId, long userId)
			throws SystemException 
			{
		
			long modifiedDate = System.currentTimeMillis() - ONLINE_DELTA;

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
			else if (PortletPropsValues.BUDDY_LIST_STRATEGY.equals("friends")) {
				buddies = StatusLocalServiceUtil.getSocialStatuses(
					userId, SocialRelationConstants.TYPE_BI_FRIEND, modifiedDate, 0,
					PortletPropsValues.BUDDY_LIST_MAX_BUDDIES);
			}
			else if (PortletPropsValues.BUDDY_LIST_STRATEGY.equals(
						"communities,friends") ||
					PortletPropsValues.BUDDY_LIST_STRATEGY.equals(
						"friends,sites")) {

				List<Object[]> groupBuddies =
					StatusLocalServiceUtil.getGroupStatuses(
						userId, modifiedDate,
						PortletPropsValues.BUDDY_LIST_SITE_EXCLUDES, 0,
						PortletPropsValues.BUDDY_LIST_MAX_BUDDIES);
				List<Object[]> socialBuddies =
					StatusLocalServiceUtil.getSocialStatuses(
						userId, SocialRelationConstants.TYPE_BI_FRIEND,
						modifiedDate, 0, PortletPropsValues.BUDDY_LIST_MAX_BUDDIES);

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

			buddies = JabberUtil.getStatuses(companyId, userId, buddies);

			return buddies;
		
			}

}
