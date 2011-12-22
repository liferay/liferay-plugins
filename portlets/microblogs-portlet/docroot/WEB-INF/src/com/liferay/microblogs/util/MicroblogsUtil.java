/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import java.util.List;

/**
 * @author Jonathan Lee
 */
public class MicroblogsUtil {

	public static JSONArray getJSONRecipients(
			long userId, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<User> users = UserLocalServiceUtil.getSocialUsers(
			userId, SocialRelationConstants.TYPE_BI_CONNECTION,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new UserFirstNameComparator(true));

		for (User user : users) {
			if (user.isDefaultUser() || (userId == user.getUserId())) {
				continue;
			}

			JSONObject userJSONObject = JSONFactoryUtil.createJSONObject();

			userJSONObject.put("emailAddress", user.getEmailAddress());
			userJSONObject.put("fullName", user.getFullName());
			userJSONObject.put("jobTitle", user.getJobTitle());
			userJSONObject.put(
				"portraitURL", user.getPortraitURL(themeDisplay));
			userJSONObject.put("screenName", user.getScreenName());
			userJSONObject.put("userId", user.getUserId());

			jsonArray.put(userJSONObject);
		}

		return jsonArray;
	}

}