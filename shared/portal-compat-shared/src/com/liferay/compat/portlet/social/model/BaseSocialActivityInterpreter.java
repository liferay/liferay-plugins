/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.compat.portlet.social.model;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.model.SocialActivityInterpreter;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Ryan Park
 */
public abstract class BaseSocialActivityInterpreter
	implements SocialActivityInterpreter {

	public long getActivitySetId(long activityId) {
		return 0;
	}

	public String getSelector() {
		return StringPool.BLANK;
	}

	public SocialActivityFeedEntry interpret(
		SocialActivity activity, ServiceContext serviceContext) {

		try {
			return doInterpret(activity, serviceContext);
		}
		catch (Exception e) {
			_log.error("Unable to interpret activity", e);
		}

		return null;
	}

	/**
	 * @deprecated
	 */
	protected String cleanContent(String content) {
		return StringUtil.shorten(HtmlUtil.extractText(content), 200);
	}

	protected SocialActivityFeedEntry doInterpret(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		HttpServletRequest request = serviceContext.getRequest();

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		return doInterpret(activity, themeDisplay);
	}

	protected SocialActivityFeedEntry doInterpret(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (!hasPermissions(
				permissionChecker, activity, ActionKeys.VIEW, themeDisplay)) {

			return null;
		}

		String groupName = StringPool.BLANK;

		if (activity.getGroupId() != themeDisplay.getScopeGroupId()) {
			groupName = getGroupName(activity.getGroupId(), themeDisplay);
		}

		String link = getLink(activity, themeDisplay);

		Object[] titleArguments = getTitleArguments(
			groupName, activity, link, getTitle(activity, themeDisplay),
			themeDisplay);

		String titlePattern = getTitlePattern(groupName, activity);

		String title = themeDisplay.translate(titlePattern, titleArguments);

		String body = getBody(activity, themeDisplay);

		return new SocialActivityFeedEntry(link, title, body);
	}

	protected String getBody(SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		return StringPool.BLANK;
	}

	protected String getClassName(SocialActivity activity) {
		return activity.getClassName();
	}

	protected long getClassPK(SocialActivity activity) {
		return activity.getClassPK();
	}

	protected String getGroupName(long groupId, ThemeDisplay themeDisplay) {
		try {
			if (groupId <= 0) {
				return StringPool.BLANK;
			}

			Group group = GroupLocalServiceUtil.getGroup(groupId);

			String groupName = group.getDescriptiveName();

			if (group.getGroupId() == themeDisplay.getScopeGroupId()) {
				return HtmlUtil.escape(groupName);
			}

			String groupDisplayURL =
				themeDisplay.getPortalURL() + themeDisplay.getPathMain() +
					"/my_sites/view?groupId=" + group.getGroupId();

			if (group.hasPublicLayouts()) {
				groupDisplayURL = groupDisplayURL + "&privateLayout=0";
			}
			else if (group.hasPrivateLayouts()) {
				groupDisplayURL = groupDisplayURL + "&privateLayout=1";
			}
			else {
				return HtmlUtil.escape(groupName);
			}

			groupName =
				"<a class=\"group\" href=\"" + groupDisplayURL + "\">" +
					HtmlUtil.escape(groupName) + "</a>";

			return groupName;
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}
	}

	protected String getLink(SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		StringBundler sb = new StringBundler(4);

		sb.append(themeDisplay.getPortalURL());
		sb.append(themeDisplay.getPathMain());
		sb.append(getPath(activity));

		long classPK = getClassPK(activity);

		sb.append(classPK);

		return sb.toString();
	}

	protected String getPath(SocialActivity activity) throws Exception {
		return StringPool.BLANK;
	}

	protected String getTitle(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		return StringPool.BLANK;
	}

	protected Object[] getTitleArguments(
			String groupName, SocialActivity activity, String link,
			String title, ThemeDisplay themeDisplay)
		throws Exception {

		String userName = getUserName(activity.getUserId(), themeDisplay);

		if (Validator.isNotNull(link)) {
			title = wrapLink(link, title);
		}

		return new Object[] {groupName, userName, title};
	}

	protected String getTitlePattern(String groupName, SocialActivity activity)
		throws Exception {

		return StringPool.BLANK;
	}

	protected String getUserName(long userId, ThemeDisplay themeDisplay) {
		try {
			if (userId <= 0) {
				return StringPool.BLANK;
			}

			User user = UserLocalServiceUtil.getUserById(userId);

			if (user.getUserId() == themeDisplay.getUserId()) {
				return HtmlUtil.escape(user.getFirstName());
			}

			String userName = user.getFullName();

			Group group = user.getGroup();

			if (group.getGroupId() == themeDisplay.getScopeGroupId()) {
				return HtmlUtil.escape(userName);
			}

			String userDisplayURL = user.getDisplayURL(themeDisplay);

			userName =
				"<a class=\"user\" href=\"" + userDisplayURL + "\">" +
					HtmlUtil.escape(userName) + "</a>";

			return userName;
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}
	}

	protected String getValue(
		String extraData, String key, String defaultValue) {

		if (Validator.isNull(extraData)) {
			return HtmlUtil.escape(defaultValue);
		}

		try {
			JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject(
				extraData);

			String value = extraDataJSONObject.getString(key);

			if (Validator.isNotNull(value)) {
				return HtmlUtil.escape(value);
			}
		}
		catch (JSONException jsone) {
			_log.error("Unable to create JSON object from " + extraData);
		}

		return HtmlUtil.escape(defaultValue);
	}

	protected boolean hasPermissions(
			PermissionChecker permissionChecker, SocialActivity activity,
			String actionId, ThemeDisplay themeDisplay)
		throws Exception {

		return false;
	}

	protected String wrapLink(String link, String text) {
		StringBundler sb = new StringBundler(5);

		sb.append("<a href=\"");
		sb.append(link);
		sb.append("\">");
		sb.append(text);
		sb.append("</a>");

		return sb.toString();
	}

	protected String wrapLink(
		String link, String key, ThemeDisplay themeDisplay) {

		return wrapLink(link, themeDisplay.translate(key));
	}

	private static Log _log = LogFactoryUtil.getLog(
		BaseSocialActivityInterpreter.class);

}