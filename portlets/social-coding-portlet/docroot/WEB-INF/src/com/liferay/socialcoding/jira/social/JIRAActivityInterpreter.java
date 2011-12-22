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

package com.liferay.socialcoding.jira.social;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
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
	protected SocialActivityFeedEntry doInterpret(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		String creatorUserName = getUserName(
			activity.getUserId(), themeDisplay);

		int activityType = activity.getType();

		JSONObject extraData = null;

		if (Validator.isNotNull(activity.getExtraData())) {
			extraData = JSONFactoryUtil.createJSONObject(
				activity.getExtraData());
		}

		JIRAAction jiraAction = null;

		if (activityType == JIRAActivityKeys.ADD_COMMENT) {
			long jiraActionId = extraData.getLong("jiraActionId");

			jiraAction = JIRAActionLocalServiceUtil.getJIRAAction(jiraActionId);
		}

		// Link

		JIRAIssue jiraIssue = JIRAIssueLocalServiceUtil.getJIRAIssue(
			activity.getClassPK());

		StringBuilder sb = new StringBuilder();

		sb.append("http://support.liferay.com/browse/");
		sb.append(jiraIssue.getKey());

		if (activityType == JIRAActivityKeys.ADD_COMMENT) {
			sb.append("#action_");
			sb.append(jiraAction.getJiraActionId());
		}

		String link = sb.toString();

		// Title

		String title = StringPool.BLANK;

		if (activityType == JIRAActivityKeys.ADD_CHANGE) {
			title = themeDisplay.translate(
				"activity-social-coding-jira-add-change",
				new Object[] {creatorUserName, jiraIssue.getKey()});
		}
		else if (activityType == JIRAActivityKeys.ADD_COMMENT) {
			title = themeDisplay.translate(
				"activity-social-coding-jira-add-comment",
				new Object[] {creatorUserName, jiraIssue.getKey()});
		}
		else if (activityType == JIRAActivityKeys.ADD_ISSUE) {
			title = themeDisplay.translate(
				"activity-social-coding-jira-add-issue",
				new Object[] {creatorUserName, jiraIssue.getKey()});
		}

		// Body

		sb = new StringBuilder();

		sb.append("<a href=\"");
		sb.append(link);
		sb.append("\" target=\"_blank\">");

		if (activityType == JIRAActivityKeys.ADD_CHANGE) {
			sb.append(
				interpretJIRAChangeItems(
					extraData.getJSONArray("jiraChangeItems"), themeDisplay));
		}
		else if (activityType == JIRAActivityKeys.ADD_COMMENT) {
			sb.append(HtmlUtil.escape(cleanContent(jiraAction.getBody())));
		}
		else if (activityType == JIRAActivityKeys.ADD_ISSUE) {
			sb.append(HtmlUtil.escape(cleanContent(jiraIssue.getSummary())));
		}

		sb.append("</a>");

		String body = sb.toString();

		return new SocialActivityFeedEntry(link, title, body);
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

		StringBuilder sb = new StringBuilder();

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
		JSONArray jiraChangeItems, ThemeDisplay themeDisplay) {

		if (jiraChangeItems == null) {
			return StringPool.BLANK;
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < jiraChangeItems.length(); i++) {
			JSONObject jiraChangeItem = jiraChangeItems.getJSONObject(i);

			sb.append(interpretJIRAChangeItem(jiraChangeItem, themeDisplay));
		}

		if (sb.length() == 0) {
			sb.append(
				themeDisplay.translate(
					"activity-social-coding-jira-add-change-default"));
		}

		return sb.toString();
	}

	private static final String[] _CLASS_NAMES = new String[] {
		JIRAIssue.class.getName()
	};

}