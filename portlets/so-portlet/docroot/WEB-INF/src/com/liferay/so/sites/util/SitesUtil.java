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

package com.liferay.so.sites.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.comparator.GroupNameComparator;
import com.liferay.so.service.FavoriteSiteLocalServiceUtil;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Ryan Park
 * @author Jonathan Lee
 */
public class SitesUtil {

	public static List<Group> getFavoriteSitesGroups(
			long userId, String name, int start, int end)
		throws Exception {

		List<Object[]> favoriteSites =
			FavoriteSiteLocalServiceUtil.getFavoriteSites(
				userId, name, start, end);

		List<Group> groups = new ArrayList<>(favoriteSites.size());

		for (Object[] favoriteSite : favoriteSites) {
			long curUserId = (Long)favoriteSite[0];
			long groupId = (Long)favoriteSite[1];

			try {
				groups.add(GroupLocalServiceUtil.getGroup(groupId));
			}
			catch (Exception e) {
				FavoriteSiteLocalServiceUtil.deleteFavoriteSites(
					curUserId, groupId);
			}
		}

		return groups;
	}

	public static int getFavoriteSitesGroupsCount(long userId, String name)
		throws Exception {

		return FavoriteSiteLocalServiceUtil.getFavoriteSitesCount(userId, name);
	}

	public static List<Group> getVisibleSites(
		long companyId, long userId, String keywords, boolean usersSites,
		int start, int end) {

		try {
			return doGetVisibleSites(
				companyId, userId, keywords, usersSites, start, end);
		}
		catch (Exception e) {
			_log.error(e, e);

			return new ArrayList<>(0);
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
			int start, int end)
		throws Exception {

		keywords = CustomSQLUtil.keywords(keywords)[0];

		if (usersSites) {
			LinkedHashMap<String, Object> params = new LinkedHashMap<>();

			params.put("active", Boolean.TRUE);
			params.put("pageCount", Boolean.TRUE);
			params.put("usersGroups", userId);

			List<Group> groups = GroupLocalServiceUtil.search(
				companyId, keywords, null, params, true, start, end,
				new GroupNameComparator(true));

			return groups;
		}

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("active", Boolean.TRUE);
		params.put("pageCount", Boolean.TRUE);

		List<Integer> types = new ArrayList<>();

		types.add(GroupConstants.TYPE_SITE_OPEN);
		types.add(GroupConstants.TYPE_SITE_RESTRICTED);

		params.put("types", types);

		List<Group> groups = GroupLocalServiceUtil.search(
			companyId, keywords, null, params, true, start, end,
			new GroupNameComparator(true));

		return groups;
	}

	protected static int doGetVisibleSitesCount(
			long companyId, long userId, String keywords, boolean usersSites)
		throws Exception {

		keywords = CustomSQLUtil.keywords(keywords)[0];

		if (usersSites) {
			LinkedHashMap<String, Object> params = new LinkedHashMap<>();

			params.put("active", Boolean.TRUE);
			params.put("pageCount", Boolean.TRUE);
			params.put("usersGroups", userId);

			return GroupLocalServiceUtil.searchCount(
				companyId, keywords, null, params, true);
		}
		else {
			LinkedHashMap<String, Object> params = new LinkedHashMap<>();

			params.put("active", Boolean.TRUE);
			params.put("pageCount", Boolean.TRUE);

			List<Integer> types = new ArrayList<>();

			types.add(GroupConstants.TYPE_SITE_OPEN);
			types.add(GroupConstants.TYPE_SITE_RESTRICTED);

			params.put("types", types);

			return GroupLocalServiceUtil.searchCount(
				companyId, keywords, null, params, true);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(SitesUtil.class);

}