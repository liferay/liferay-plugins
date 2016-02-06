/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.socialcoding.service.impl;

import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.expando.kernel.service.ExpandoValueLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.social.kernel.model.SocialActivity;
import com.liferay.social.kernel.service.SocialActivityLocalServiceUtil;
import com.liferay.socialcoding.exception.NoSuchJIRAIssueException;
import com.liferay.socialcoding.jira.social.JIRAActivityKeys;
import com.liferay.socialcoding.jira.util.JIRAUtil;
import com.liferay.socialcoding.model.JIRAAction;
import com.liferay.socialcoding.model.JIRAChangeGroup;
import com.liferay.socialcoding.model.JIRAChangeItem;
import com.liferay.socialcoding.model.JIRAIssue;
import com.liferay.socialcoding.service.base.JIRAIssueLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class JIRAIssueLocalServiceImpl extends JIRAIssueLocalServiceBaseImpl {

	public List<JIRAIssue> getAssigneeJIRAIssues(
		Date modifiedDate, long projectId, String assigneeJiraUserId, int start,
		int end) {

		return jiraIssuePersistence.findByMD_P_AJUI(
			modifiedDate, projectId, assigneeJiraUserId);
	}

	public List<JIRAIssue> getAssigneeJIRAIssues(
		long projectId, String assigneeJiraUserId, int start, int end) {

		return jiraIssuePersistence.findByP_AJUI(projectId, assigneeJiraUserId);
	}

	public List<JIRAIssue> getAssigneeJIRAIssues(
		long projectId, String assigneeJiraUserId, String status, int start,
		int end) {

		return jiraIssuePersistence.findByP_AJUI_S(
			projectId, assigneeJiraUserId, status);
	}

	public int getAssigneeJIRAIssuesCount(
		Date modifiedDate, long projectId, String assigneeJiraUserId) {

		return jiraIssuePersistence.countByMD_P_AJUI(
			modifiedDate, projectId, assigneeJiraUserId);
	}

	public int getAssigneeJIRAIssuesCount(
		long projectId, String assigneeJiraUserId) {

		return jiraIssuePersistence.countByP_AJUI(
			projectId, assigneeJiraUserId);
	}

	public int getAssigneeJIRAIssuesCount(
		long projectId, String assigneeJiraUserId, String status) {

		return jiraIssuePersistence.countByP_AJUI_S(
			projectId, assigneeJiraUserId, status);
	}

	public JIRAIssue getFirstAssigneeJIRAIssue(
			long projectId, String assigneeJiraUserId)
		throws PortalException {

		int count = jiraIssuePersistence.countByP_AJUI(
			projectId, assigneeJiraUserId);

		List<JIRAIssue> jiraIssues = jiraIssuePersistence.findByP_AJUI(
			projectId, assigneeJiraUserId, count - 1, count);

		if (jiraIssues.size() > 0) {
			return jiraIssues.get(0);
		}
		else {
			throw new NoSuchJIRAIssueException();
		}
	}

	public JIRAIssue getFirstReporterJIRAIssue(
			long projectId, String reporterJiraUserId)
		throws PortalException {

		int count = jiraIssuePersistence.countByP_AJUI(
			projectId, reporterJiraUserId);

		List<JIRAIssue> jiraIssues = jiraIssuePersistence.findByP_RJUI(
			projectId, reporterJiraUserId, count - 1, count);

		if (jiraIssues.size() > 0) {
			return jiraIssues.get(0);
		}
		else {
			throw new NoSuchJIRAIssueException();
		}
	}

	@Override
	public JIRAIssue getJIRAIssue(long jiraIssueId) throws PortalException {
		return jiraIssuePersistence.findByPrimaryKey(jiraIssueId);
	}

	public JIRAIssue getJIRAIssue(String key) throws PortalException {
		return jiraIssueFinder.findByKey(key);
	}

	public JIRAIssue getLastAssigneeJIRAIssue(
			long projectId, String assigneeJiraUserId)
		throws PortalException {

		List<JIRAIssue> jiraIssues = jiraIssuePersistence.findByP_AJUI(
			projectId, assigneeJiraUserId, 0, 1);

		if (jiraIssues.size() > 0) {
			return jiraIssues.get(0);
		}
		else {
			throw new NoSuchJIRAIssueException();
		}
	}

	public JIRAIssue getLastreporterJIRAIssue(
			long projectId, String reporterJiraUserId)
		throws PortalException {

		List<JIRAIssue> jiraIssues = jiraIssuePersistence.findByP_RJUI(
			projectId, reporterJiraUserId, 0, 1);

		if (jiraIssues.size() > 0) {
			return jiraIssues.get(0);
		}
		else {
			throw new NoSuchJIRAIssueException();
		}
	}

	public List<JIRAIssue> getReporterJIRAIssues(
		Date modifiedDate, long projectId, String reporterJiraUserId, int start,
		int end) {

		return jiraIssuePersistence.findByMD_P_RJUI(
			modifiedDate, projectId, reporterJiraUserId);
	}

	public List<JIRAIssue> getReporterJIRAIssues(
		long projectId, String reporterJiraUserId, int start, int end) {

		return jiraIssuePersistence.findByP_RJUI(projectId, reporterJiraUserId);
	}

	public List<JIRAIssue> getReporterJIRAIssues(
		long projectId, String reporterJiraUserId, String status, int start,
		int end) {

		return jiraIssuePersistence.findByP_RJUI_S(
			projectId, reporterJiraUserId, status);
	}

	public int getReporterJIRAIssuesCount(
		Date modifiedDate, long projectId, String reporterJiraUserId) {

		return jiraIssuePersistence.countByMD_P_RJUI(
			modifiedDate, projectId, reporterJiraUserId);
	}

	public int getReporterJIRAIssuesCount(
		long projectId, String reporterJiraUserId) {

		return jiraIssuePersistence.countByP_RJUI(
			projectId, reporterJiraUserId);
	}

	public int getReporterJIRAIssuesCount(
		long projectId, String reporterJiraUserId, String status) {

		return jiraIssuePersistence.countByP_RJUI_S(
			projectId, reporterJiraUserId, status);
	}

	public void updateJIRAIssues(long projectId) throws PortalException {
		Date modifiedDate = getLastModifiedDate(projectId);

		List<JIRAAction> jiraActions = jiraActionFinder.findByCD_P(
			modifiedDate, projectId);

		for (JIRAAction jiraAction : jiraActions) {
			long userId = getUserId(jiraAction.getJiraUserId());

			if ((userId <= 0) ||
				Validator.isNotNull(jiraAction.getJiraGroupName())) {

				continue;
			}

			JSONObject extraData = JSONFactoryUtil.createJSONObject();

			extraData.put("jiraActionId", jiraAction.getJiraActionId());

			SocialActivityLocalServiceUtil.addUniqueActivity(
				userId, 0, JIRAUtil.getLiferayDate(jiraAction.getCreateDate()),
				JIRAIssue.class.getName(), jiraAction.getJiraIssueId(),
				JIRAActivityKeys.ADD_COMMENT, extraData.toString(), 0);
		}

		List<JIRAChangeGroup> jiraChangeGroups =
			jiraChangeGroupFinder.findByCD_P(modifiedDate, projectId);

		for (JIRAChangeGroup jiraChangeGroup : jiraChangeGroups) {
			long userId = getUserId(jiraChangeGroup.getJiraUserId());

			if (userId <= 0) {
				continue;
			}

			JSONObject extraData = JSONFactoryUtil.createJSONObject();

			extraData.put(
				"jiraChangeGroupId", jiraChangeGroup.getJiraChangeGroupId());

			JSONArray jiraChangeItemsJSON = JSONFactoryUtil.createJSONArray();

			extraData.put("jiraChangeItems", jiraChangeItemsJSON);

			List<JIRAChangeItem> jiraChangeItems =
				jiraChangeItemPersistence.findByJiraChangeGroupId(
					jiraChangeGroup.getJiraChangeGroupId());

			for (JIRAChangeItem jiraChangeItem : jiraChangeItems) {
				JSONObject jiraChangeItemJSON =
					JSONFactoryUtil.createJSONObject();

				jiraChangeItemJSON.put("field", jiraChangeItem.getField());
				jiraChangeItemJSON.put(
					"oldValue", jiraChangeItem.getOldValue());
				jiraChangeItemJSON.put(
					"oldString", jiraChangeItem.getOldString());
				jiraChangeItemJSON.put(
					"newValue", jiraChangeItem.getNewValue());
				jiraChangeItemJSON.put(
					"newString", jiraChangeItem.getNewString());

				jiraChangeItemsJSON.put(jiraChangeItemJSON);
			}

			SocialActivityLocalServiceUtil.addUniqueActivity(
				userId, 0,
				JIRAUtil.getLiferayDate(jiraChangeGroup.getCreateDate()),
				JIRAIssue.class.getName(), jiraChangeGroup.getJiraIssueId(),
				JIRAActivityKeys.ADD_CHANGE, extraData.toString(), 0);
		}

		List<JIRAIssue> jiraIssues = jiraIssueFinder.findByCD_P(
			modifiedDate, projectId);

		for (JIRAIssue jiraIssue : jiraIssues) {
			long userId = getUserId(jiraIssue.getReporterJiraUserId());

			if (userId <= 0) {
				continue;
			}

			SocialActivityLocalServiceUtil.addUniqueActivity(
				userId, 0, JIRAUtil.getLiferayDate(jiraIssue.getCreateDate()),
				JIRAIssue.class.getName(), jiraIssue.getJiraIssueId(),
				JIRAActivityKeys.ADD_ISSUE, StringPool.BLANK, 0);
		}
	}

	protected Date getLastModifiedDate(long projectId) {
		Date modifiedDate = null;

		List<SocialActivity> socialActivities =
			SocialActivityLocalServiceUtil.getActivities(
				JIRAIssue.class.getName(), 0, 1);

		if (socialActivities.size() > 0) {
			SocialActivity socialActivity = socialActivities.get(0);

			modifiedDate = JIRAUtil.getJIRADate(
				new Date(socialActivity.getCreateDate()));
		}
		else {
			modifiedDate = JIRAUtil.getJIRADate(-1);
		}

		return modifiedDate;
	}

	protected long getUserId(String jiraUserId) {
		List<ExpandoValue> expandoValues =
			ExpandoValueLocalServiceUtil.getColumnValues(
				PortalUtil.getDefaultCompanyId(), User.class.getName(), "SC",
				"jiraUserId", jiraUserId, 0, 1);

		if (expandoValues.size() == 0) {
			return 0;
		}

		ExpandoValue expandoValue = expandoValues.get(0);

		return expandoValue.getClassPK();
	}

}