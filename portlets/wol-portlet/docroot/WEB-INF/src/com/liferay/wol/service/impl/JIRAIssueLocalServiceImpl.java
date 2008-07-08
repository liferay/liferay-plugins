/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.wol.service.impl;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.wol.NoSuchJIRAIssueException;
import com.liferay.wol.jira.social.JIRAActivityKeys;
import com.liferay.wol.jira.util.JIRAUtil;
import com.liferay.wol.model.JIRAAction;
import com.liferay.wol.model.JIRAChangeGroup;
import com.liferay.wol.model.JIRAChangeItem;
import com.liferay.wol.model.JIRAIssue;
import com.liferay.wol.service.base.JIRAIssueLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * <a href="JIRAIssueLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JIRAIssueLocalServiceImpl extends JIRAIssueLocalServiceBaseImpl {

	public List<JIRAIssue> getAssigneeJIRAIssues(
			long projectId, String assigneeJiraUserId, int start, int end)
		throws SystemException {

		return jiraIssuePersistence.findByP_AJUI(projectId, assigneeJiraUserId);
	}

	public List<JIRAIssue> getAssigneeJIRAIssues(
			Date modifiedDate, long projectId, String assigneeJiraUserId,
			int start, int end)
		throws SystemException {

		return jiraIssuePersistence.findByMD_P_AJUI(
			modifiedDate, projectId, assigneeJiraUserId);
	}

	public List<JIRAIssue> getAssigneeJIRAIssues(
			long projectId, String assigneeJiraUserId, String status, int start,
			int end)
		throws SystemException {

		return jiraIssuePersistence.findByP_AJUI_S(
			projectId, assigneeJiraUserId, status);
	}

	public int getAssigneeJIRAIssuesCount(
			long projectId, String assigneeJiraUserId)
		throws SystemException {

		return jiraIssuePersistence.countByP_AJUI(
			projectId, assigneeJiraUserId);
	}

	public int getAssigneeJIRAIssuesCount(
			Date modifiedDate, long projectId, String assigneeJiraUserId)
		throws SystemException {

		return jiraIssuePersistence.countByMD_P_AJUI(
			modifiedDate, projectId, assigneeJiraUserId);
	}

	public int getAssigneeJIRAIssuesCount(
			long projectId, String assigneeJiraUserId, String status)
		throws SystemException {

		return jiraIssuePersistence.countByP_AJUI_S(
			projectId, assigneeJiraUserId, status);
	}

	public JIRAIssue getFirstAssigneeJIRAIssue(
			long projectId, String assigneeJiraUserId)
		throws PortalException, SystemException {

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
		throws PortalException, SystemException {

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

	public JIRAIssue getJIRAIssue(long jiraIssueId)
		throws PortalException, SystemException {

		return jiraIssuePersistence.findByPrimaryKey(jiraIssueId);
	}

	public JIRAIssue getJIRAIssue(String key)
		throws PortalException, SystemException {

		return jiraIssuePersistence.findByKey(key);
	}

	public JIRAIssue getLastAssigneeJIRAIssue(
			long projectId, String assigneeJiraUserId)
		throws PortalException, SystemException {

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
		throws PortalException, SystemException {

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
			long projectId, String reporterJiraUserId, int start, int end)
		throws SystemException {

		return jiraIssuePersistence.findByP_RJUI(projectId, reporterJiraUserId);
	}

	public List<JIRAIssue> getReporterJIRAIssues(
			Date modifiedDate, long projectId, String reporterJiraUserId,
			int start, int end)
		throws SystemException {

		return jiraIssuePersistence.findByMD_P_RJUI(
			modifiedDate, projectId, reporterJiraUserId);
	}

	public List<JIRAIssue> getReporterJIRAIssues(
			long projectId, String reporterJiraUserId, String status, int start,
			int end)
		throws SystemException {

		return jiraIssuePersistence.findByP_RJUI_S(
			projectId, reporterJiraUserId, status);
	}

	public int getReporterJIRAIssuesCount(
			long projectId, String reporterJiraUserId)
		throws SystemException {

		return jiraIssuePersistence.countByP_RJUI(
			projectId, reporterJiraUserId);
	}

	public int getReporterJIRAIssuesCount(
			Date modifiedDate, long projectId, String reporterJiraUserId)
		throws SystemException {

		return jiraIssuePersistence.countByMD_P_RJUI(
			modifiedDate, projectId, reporterJiraUserId);
	}

	public int getReporterJIRAIssuesCount(
			long projectId, String reporterJiraUserId, String status)
		throws SystemException {

		return jiraIssuePersistence.countByP_RJUI_S(
			projectId, reporterJiraUserId, status);
	}

	public void updateJIRAIssues(long projectId)
		throws PortalException, SystemException {

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

	protected Date getLastModifiedDate(long projectId) throws SystemException {
		Date modifiedDate = null;

		List<SocialActivity> socialActivities =
			SocialActivityLocalServiceUtil.getActivities(
				JIRAIssue.class.getName(), 0, 1);

		if (socialActivities.size() > 0) {
			SocialActivity socialActivity = socialActivities.get(0);

			modifiedDate = JIRAUtil.getJIRADate(socialActivity.getCreateDate());
		}
		else {
			modifiedDate = JIRAUtil.getJIRADate(-1);
		}

		return modifiedDate;
	}

	protected long getUserId(String jiraUserId) throws SystemException {
		List<ExpandoValue> expandoValues =
			ExpandoValueLocalServiceUtil.getColumnValues(
				User.class.getName(), "WOL", "jiraUserId", jiraUserId, 0, 1);

		if (expandoValues.size() == 0) {
			return 0;
		}

		ExpandoValue expandoValue = expandoValues.get(0);

		return expandoValue.getClassPK();
	}

}