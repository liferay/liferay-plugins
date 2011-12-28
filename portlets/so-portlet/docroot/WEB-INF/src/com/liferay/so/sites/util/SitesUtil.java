/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.so.sites.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.GroupServiceUtil;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.comparator.GroupNameComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import javax.portlet.PortletPreferences;

/**
 * @author Ryan Park
 * @author Jonathan Lee
 */
public class SitesUtil {

	public static String getStarredGroupIds(long userId) {
		String starredGroupIds = StringPool.BLANK;

		try {
			User user = UserLocalServiceUtil.getUser(userId);

			Group group = user.getGroup();

			PortletPreferences portletPreferences =
				PortletPreferencesLocalServiceUtil.getPreferences(
					user.getCompanyId(), group.getGroupId(),
					PortletKeys.PREFS_OWNER_TYPE_GROUP, 0, "5_WAR_soportlet");

			if (portletPreferences != null) {
				starredGroupIds = portletPreferences.getValue(
					"starredGroupIds", StringPool.BLANK);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return starredGroupIds;
	}

	public static List<Group> getStarredSites(
			ThemeDisplay themeDisplay, String name)
		throws Exception {

		String starredGroupIds = StringPool.BLANK;

		User user = themeDisplay.getUser();

		Group group = user.getGroup();

		PortletPreferences portletPreferences =
			PortletPreferencesLocalServiceUtil.getPreferences(
				user.getCompanyId(), group.getGroupId(),
				PortletKeys.PREFS_OWNER_TYPE_GROUP, 0, "5_WAR_soportlet");

		if (portletPreferences != null) {
			starredGroupIds = portletPreferences.getValue(
				"starredGroupIds", StringPool.BLANK);
		}

		long[] groupIds = StringUtil.split(starredGroupIds, 0L);

		List<Group> groups = new ArrayList<Group>(groupIds.length);

		for (long groupId : groupIds) {
			try {
				Group curGroup = GroupServiceUtil.getGroup(groupId);

				if (Validator.isNull(name)) {
					groups.add(curGroup);
				}
				else {
					String groupDescriptiveName = curGroup.getDescriptiveName(
						themeDisplay.getLocale());

					groupDescriptiveName = groupDescriptiveName.toLowerCase();

					if (groupDescriptiveName.contains(name.toLowerCase())) {
						groups.add(curGroup);
					}
				}
			}
			catch (Exception e) {
				StringUtil.remove(starredGroupIds, String.valueOf(groupId));

				portletPreferences.setValue("starredGroupIds", starredGroupIds);

				portletPreferences.store();
			}
		}

		Collections.sort(groups, new GroupNameComparator(true));

		return groups;
	}

	public static List<Group> getVisibleSites(
		long companyId, long userId, String keywords, boolean usersSites,
		int maxResultSize) {

		try {
			return doGetVisibleSites(
				companyId, userId, keywords, usersSites, maxResultSize);
		}
		catch (Exception e) {
			_log.error(e, e);

			return new ArrayList<Group>(0);
		}
	}

	public static int getVisibleSitesCount(
		long companyId, long userId, String keywords, boolean usersSites) {

		try {
			return doGetVisibleSitesCount(
				companyId, userId, keywords, usersSites);
		}
		catch (Exception e) {
			_log.error(e, e);

			return 0;
		}
	}

	protected static List<Group> doGetVisibleSites(
			long companyId, long userId, String keywords, boolean usersSites,
			int maxResultSize)
		throws Exception {

		List<Group> groups = new ArrayList<Group>(maxResultSize);

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("usersGroups", userId);

		List<Group> usersGroups = GroupLocalServiceUtil.search(
			companyId, keywords, null, params, 0, maxResultSize,
			new GroupNameComparator(true));

		groups.addAll(usersGroups);

		if (usersSites || (groups.size() >= maxResultSize)) {
			return groups;
		}

		params.clear();

		List<Integer> types = new ArrayList<Integer>();

		types.add(GroupConstants.TYPE_SITE_OPEN);
		types.add(GroupConstants.TYPE_SITE_RESTRICTED);

		params.put("types", types);

		List<Group> visibleGroup = GroupLocalServiceUtil.search(
			companyId, keywords, null, params, 0, maxResultSize,
			new GroupNameComparator(true));

		for (Group group : visibleGroup) {
			if (!usersGroups.contains(group)) {
				groups.add(group);
			}

			if (groups.size() > maxResultSize) {
				break;
			}
		}

		return groups;
	}

	protected static int doGetVisibleSitesCount(
			long comapnyId, long userId, String keywords, boolean usersSites)
		throws Exception {

		if (usersSites) {
			LinkedHashMap<String, Object> params =
				new LinkedHashMap<String, Object>();

			params.put("usersGroups", userId);

			return GroupLocalServiceUtil.searchCount(
				comapnyId, keywords, null, params);
		}
		else{
			LinkedHashMap<String, Object> params =
				new LinkedHashMap<String, Object>();

			List<Integer> types = new ArrayList<Integer>();

			types.add(GroupConstants.TYPE_SITE_OPEN);
			types.add(GroupConstants.TYPE_SITE_RESTRICTED);

			params.put("types", types);

			int groupsCount = GroupLocalServiceUtil.searchCount(
				comapnyId, keywords, null, params);

			params.clear();

			params.put("usersGroups", userId);

			types.clear();

			types.add(GroupConstants.TYPE_SITE_PRIVATE);

			params.put("types", types);

			return groupsCount + GroupLocalServiceUtil.searchCount(
				comapnyId, keywords, null, params);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(SitesUtil.class);

}