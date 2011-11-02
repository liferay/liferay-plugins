/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.microblogs.util;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.comparator.UserFirstNameComparator;
import com.liferay.portlet.social.model.SocialRelationConstants;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Jonathan Lee
 */
public class MicroblogsUtil {

	public static JSONArray getJSONRecipients(
			long userId, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		Set<User> users = new HashSet<User>();

		users.addAll(
			UserLocalServiceUtil.getSocialUsers(
				userId, SocialRelationConstants.TYPE_BI_CONNECTION,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new UserFirstNameComparator(true)));

		JSONArray userJSONArray = JSONFactoryUtil.createJSONArray();

		for (User user : users) {
			if (user.isDefaultUser() || (userId == user.getUserId())) {
				continue;
			}

			JSONObject userJSONObject = JSONFactoryUtil.createJSONObject();

			userJSONObject.put("userScreenName", user.getScreenName());
			userJSONObject.put("userId", user.getUserId());
			userJSONObject.put("userFullName", user.getFullName());
			userJSONObject.put("email", user.getEmailAddress());
			userJSONObject.put(
				"portraitURL", user.getPortraitURL(themeDisplay));
			userJSONObject.put(
				"jobTitle", user.getJobTitle());

			userJSONArray.put(userJSONObject);
		}

		return userJSONArray;
	}

}