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

package com.liferay.socialcoding.jira.social;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.socialcoding.model.JIRAAction;
import com.liferay.socialcoding.model.JIRAIssue;
import com.liferay.socialcoding.service.JIRAActionLocalServiceUtil;
import com.liferay.socialcoding.service.JIRAIssueLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class JIRAActivityInterpreter extends BaseSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected String getBody(SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		String link = getLink(activity, themeDisplay);

		String text = StringPool.BLANK;

		int activityType = activity.getType();

		if (activityType == JIRAActivityKeys.ADD_CHANGE) {
			JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject(
				activity.getExtraData());

			text = interpretJIRAChangeItems(
				extraDataJSONObject.getJSONArray("jiraChangeItems"),
				themeDisplay);
		}
		else if (activityType == JIRAActivityKeys.ADD_COMMENT) {
			long jiraActionId = GetterUtil.getLong(
				getJSONValue(activity.getExtraData(), "jiraActionId"));

			JIRAAction jiraAction = JIRAActionLocalServiceUtil.getJIRAAction(
				jiraActionId);

			text = HtmlUtil.escape(jiraAction.getBody());
		}
		else if (activityType == JIRAActivityKeys.ADD_ISSUE) {
			JIRAIssue jiraIssue = JIRAIssueLocalServiceUtil.getJIRAIssue(
				activity.getClassPK());

			text = HtmlUtil.escape(jiraIssue.getSummary());
		}

		return wrapLink(link, text);
	}

	@Override
	protected String getLink(SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		StringBundler sb = new StringBundler(4);

		sb.append("http://support.liferay.com/browse/");

		JIRAIssue jiraIssue = JIRAIssueLocalServiceUtil.getJIRAIssue(
			activity.getClassPK());

		sb.append(jiraIssue.getKey());

		int activityType = activity.getType();

		if (activityType == JIRAActivityKeys.ADD_COMMENT) {
			sb.append("#action_");

			long jiraActionId = GetterUtil.getLong(
				getJSONValue(activity.getExtraData(), "jiraActionId"));

			JIRAAction jiraAction = JIRAActionLocalServiceUtil.getJIRAAction(
				jiraActionId);

			sb.append(jiraAction.getJiraActionId());
		}

		return sb.toString();
	}

	@Override
	protected Object[] getTitleArguments(
			String groupName, SocialActivity activity, String link,
			String title, ThemeDisplay themeDisplay)
		throws Exception {

		String creatorUserName = getUserName(
			activity.getUserId(), themeDisplay);

		JIRAIssue jiraIssue = JIRAIssueLocalServiceUtil.getJIRAIssue(
			activity.getClassPK());

		return new Object[] {creatorUserName, jiraIssue.getKey()};
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivity activity) {

		int activityType = activity.getType();

		if (activityType == JIRAActivityKeys.ADD_CHANGE) {
			return "activity-social-coding-jira-add-change";
		}
		else if (activityType == JIRAActivityKeys.ADD_COMMENT) {
			return "activity-social-coding-jira-add-comment";
		}
		else if (activityType == JIRAActivityKeys.ADD_ISSUE) {
			return "activity-social-coding-jira-add-issue";
		}

		return StringPool.BLANK;
	}

	@Override
	protected boolean hasPermissions(
		PermissionChecker permissionChecker, SocialActivity activity,
		String actionId, ThemeDisplay themeDisplay) {

		return true;
	}

	protected String interpretJIRAChangeItem(
		JSONObject jiraChangeItem, ThemeDisplay themeDisplay) {

		String field = jiraChangeItem.getString("field");

		field = StringUtil.replace(
			field.toLowerCase(), StringPool.SPACE, StringPool.DASH);

		String newString = jiraChangeItem.getString("newString");
		String newValue = jiraChangeItem.getString("newValue");

		if (Validator.isNull(newString)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(2);

		if (field.equals("description") || field.equals("summary")) {
			sb.append(
				themeDisplay.translate(
					"activity-social-coding-jira-add-change-" + field));
			sb.append("<br />");
		}
		else if (field.equals("assignee") || field.equals("attachment") ||
				 field.equals("fix-version") || field.equals("issuetype") ||
				 field.equals("priority") || field.equals("resolution") ||
				 field.equals("status") || field.equals("version")) {

			sb.append(
				themeDisplay.translate(
					"activity-social-coding-jira-add-change-" + field,
					new Object[] {HtmlUtil.escape(newString)}));
			sb.append("<br />");
		}
		else if (field.equals("link") && newValue.startsWith("LEP-")) {
			sb.append(
				themeDisplay.translate(
					"activity-social-coding-jira-add-change-" + field,
					new Object[] {HtmlUtil.escape(newValue)}));
			sb.append("<br />");
		}

		return sb.toString();
	}

	protected String interpretJIRAChangeItems(
		JSONArray jiraChangeItemsJSONArray, ThemeDisplay themeDisplay) {

		if (jiraChangeItemsJSONArray == null) {
			return StringPool.BLANK;
		}

		if (jiraChangeItemsJSONArray.length() == 0) {
			return(
				themeDisplay.translate(
					"activity-social-coding-jira-add-change-default"));
		}

		StringBundler sb = new StringBundler(jiraChangeItemsJSONArray.length());

		for (int i = 0; i < jiraChangeItemsJSONArray.length(); i++) {
			JSONObject jiraChangeItemJSONObject =
				jiraChangeItemsJSONArray.getJSONObject(i);

			sb.append(
				interpretJIRAChangeItem(
					jiraChangeItemJSONObject, themeDisplay));
		}

		return sb.toString();
	}

	private static final String[] _CLASS_NAMES = {JIRAIssue.class.getName()};

}