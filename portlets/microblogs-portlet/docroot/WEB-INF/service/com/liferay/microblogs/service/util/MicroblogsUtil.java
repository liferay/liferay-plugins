/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.microblogs.service.util;

import com.liferay.compat.portal.service.ServiceContext;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.comparator.UserFirstNameComparator;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.social.model.SocialRelationConstants;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;

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

	public static String getTaggedContent(
			MicroblogsEntry microblogsEntry, ServiceContext serviceContext)
		throws PortalException, SystemException {

		String content = HtmlUtil.escape(microblogsEntry.getContent());

		ThemeDisplay themeDisplay = serviceContext.getThemeDisplay();

		Pattern pattern = Pattern.compile("\\#\\S*");

		Matcher matcher = pattern.matcher(microblogsEntry.getContent());

		while (matcher.find()) {
			String result = matcher.group();

			String assetTagName = result.substring(1);

			PortletURL viewURL = null;

			long userId = themeDisplay.getUserId();

			Group group = GroupLocalServiceUtil.getUserGroup(
				themeDisplay.getCompanyId(), userId);

			long portletPlid = PortalUtil.getPlidFromPortletId(
				group.getGroupId(), true, "1_WAR_microblogsportlet");

			if (portletPlid != 0) {
				viewURL = PortletURLFactoryUtil.create(
					serviceContext.getLiferayPortletRequest(),
					"1_WAR_microblogsportlet", portletPlid,
					PortletRequest.RENDER_PHASE);

				try {
					viewURL.setWindowState(LiferayWindowState.NORMAL);
				}
				catch (WindowStateException wse) {
				}
			}
			else {
				LiferayPortletResponse liferayPortletResponse =
					serviceContext.getLiferayPortletResponse();

				viewURL = liferayPortletResponse.createRenderURL(
					"1_WAR_microblogsportlet");

				try {
					viewURL.setWindowState(WindowState.MAXIMIZED);
				}
				catch (WindowStateException wse) {
				}
			}

			viewURL.setParameter("mvcPath", "/microblogs/view.jsp");
			viewURL.setParameter("tabs1", assetTagName);
			viewURL.setParameter("assetTagName", assetTagName);

			StringBuilder sb = new StringBuilder(5);

			sb.append("<a href=\"");
			sb.append(viewURL);
			sb.append("\">");
			sb.append(assetTagName);
			sb.append("</a>");

			String tagLink = sb.toString();

			content = StringUtil.replace(content, result, tagLink);
		}

		pattern = Pattern.compile("\\[\\@\\S*\\]");

		matcher = pattern.matcher(content);

		while (matcher.find()) {
			String result = matcher.group();

			String assetTagName = result.replace("[@", StringPool.BLANK);

			assetTagName = assetTagName.replace("]", StringPool.BLANK);

			try {
				User taggedUser = UserLocalServiceUtil.getUserByScreenName(
					microblogsEntry.getCompanyId(), assetTagName);

				assetTagName = PortalUtil.getUserName(
					taggedUser.getUserId(), assetTagName);

				StringBuilder sb = new StringBuilder(5);

				sb.append("<a href=\"");
				sb.append(taggedUser.getDisplayURL(themeDisplay));
				sb.append("\">");
				sb.append(assetTagName);
				sb.append("</a>");

				String userLink = sb.toString();

				content = StringUtil.replace(content, result, userLink);
			}
			catch (NoSuchUserException nsue) {
			}
		}

		return content;
	}

}