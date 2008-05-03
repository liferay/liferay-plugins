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

package com.liferay.wol.service.persistence;

/**
 * <a href="JIRAIssueUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JIRAIssueUtil {
	public static com.liferay.wol.model.JIRAIssue create(long jiraIssueId) {
		return getPersistence().create(jiraIssueId);
	}

	public static com.liferay.wol.model.JIRAIssue remove(long jiraIssueId)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence().remove(jiraIssueId);
	}

	public static com.liferay.wol.model.JIRAIssue remove(
		com.liferay.wol.model.JIRAIssue jiraIssue)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(jiraIssue);
	}

	public static com.liferay.wol.model.JIRAIssue update(
		com.liferay.wol.model.JIRAIssue jiraIssue)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(jiraIssue);
	}

	public static com.liferay.wol.model.JIRAIssue update(
		com.liferay.wol.model.JIRAIssue jiraIssue, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(jiraIssue, merge);
	}

	public static com.liferay.wol.model.JIRAIssue updateImpl(
		com.liferay.wol.model.JIRAIssue jiraIssue, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(jiraIssue, merge);
	}

	public static com.liferay.wol.model.JIRAIssue findByPrimaryKey(
		long jiraIssueId)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence().findByPrimaryKey(jiraIssueId);
	}

	public static com.liferay.wol.model.JIRAIssue fetchByPrimaryKey(
		long jiraIssueId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(jiraIssueId);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByProjectId(
		long projectId) throws com.liferay.portal.SystemException {
		return getPersistence().findByProjectId(projectId);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByProjectId(
		long projectId, int begin, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByProjectId(projectId, begin, end);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByProjectId(
		long projectId, int begin, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByProjectId(projectId, begin, end, obc);
	}

	public static com.liferay.wol.model.JIRAIssue findByProjectId_First(
		long projectId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence().findByProjectId_First(projectId, obc);
	}

	public static com.liferay.wol.model.JIRAIssue findByProjectId_Last(
		long projectId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence().findByProjectId_Last(projectId, obc);
	}

	public static com.liferay.wol.model.JIRAIssue[] findByProjectId_PrevAndNext(
		long jiraIssueId, long projectId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByProjectId_PrevAndNext(jiraIssueId, projectId, obc);
	}

	public static com.liferay.wol.model.JIRAIssue findByKey(
		java.lang.String key)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence().findByKey(key);
	}

	public static com.liferay.wol.model.JIRAIssue fetchByKey(
		java.lang.String key) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByKey(key);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByReporterJiraUserId(
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByReporterJiraUserId(reporterJiraUserId);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByReporterJiraUserId(
		java.lang.String reporterJiraUserId, int begin, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByReporterJiraUserId(reporterJiraUserId, begin, end);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByReporterJiraUserId(
		java.lang.String reporterJiraUserId, int begin, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByReporterJiraUserId(reporterJiraUserId, begin, end, obc);
	}

	public static com.liferay.wol.model.JIRAIssue findByReporterJiraUserId_First(
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByReporterJiraUserId_First(reporterJiraUserId, obc);
	}

	public static com.liferay.wol.model.JIRAIssue findByReporterJiraUserId_Last(
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByReporterJiraUserId_Last(reporterJiraUserId, obc);
	}

	public static com.liferay.wol.model.JIRAIssue[] findByReporterJiraUserId_PrevAndNext(
		long jiraIssueId, java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByReporterJiraUserId_PrevAndNext(jiraIssueId,
			reporterJiraUserId, obc);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByAssigneeJiraUserId(
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByAssigneeJiraUserId(assigneeJiraUserId);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByAssigneeJiraUserId(
		java.lang.String assigneeJiraUserId, int begin, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByAssigneeJiraUserId(assigneeJiraUserId, begin, end);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByAssigneeJiraUserId(
		java.lang.String assigneeJiraUserId, int begin, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByAssigneeJiraUserId(assigneeJiraUserId, begin, end, obc);
	}

	public static com.liferay.wol.model.JIRAIssue findByAssigneeJiraUserId_First(
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByAssigneeJiraUserId_First(assigneeJiraUserId, obc);
	}

	public static com.liferay.wol.model.JIRAIssue findByAssigneeJiraUserId_Last(
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByAssigneeJiraUserId_Last(assigneeJiraUserId, obc);
	}

	public static com.liferay.wol.model.JIRAIssue[] findByAssigneeJiraUserId_PrevAndNext(
		long jiraIssueId, java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByAssigneeJiraUserId_PrevAndNext(jiraIssueId,
			assigneeJiraUserId, obc);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByMD_P(
		java.util.Date modifiedDate, long projectId)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByMD_P(modifiedDate, projectId);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByMD_P(
		java.util.Date modifiedDate, long projectId, int begin, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByMD_P(modifiedDate, projectId, begin, end);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByMD_P(
		java.util.Date modifiedDate, long projectId, int begin, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByMD_P(modifiedDate, projectId, begin, end, obc);
	}

	public static com.liferay.wol.model.JIRAIssue findByMD_P_First(
		java.util.Date modifiedDate, long projectId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence().findByMD_P_First(modifiedDate, projectId, obc);
	}

	public static com.liferay.wol.model.JIRAIssue findByMD_P_Last(
		java.util.Date modifiedDate, long projectId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence().findByMD_P_Last(modifiedDate, projectId, obc);
	}

	public static com.liferay.wol.model.JIRAIssue[] findByMD_P_PrevAndNext(
		long jiraIssueId, java.util.Date modifiedDate, long projectId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByMD_P_PrevAndNext(jiraIssueId, modifiedDate,
			projectId, obc);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByP_RJUI(
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByP_RJUI(projectId, reporterJiraUserId);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByP_RJUI(
		long projectId, java.lang.String reporterJiraUserId, int begin, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByP_RJUI(projectId, reporterJiraUserId, begin, end);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByP_RJUI(
		long projectId, java.lang.String reporterJiraUserId, int begin,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByP_RJUI(projectId, reporterJiraUserId, begin, end, obc);
	}

	public static com.liferay.wol.model.JIRAIssue findByP_RJUI_First(
		long projectId, java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_RJUI_First(projectId, reporterJiraUserId, obc);
	}

	public static com.liferay.wol.model.JIRAIssue findByP_RJUI_Last(
		long projectId, java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_RJUI_Last(projectId, reporterJiraUserId, obc);
	}

	public static com.liferay.wol.model.JIRAIssue[] findByP_RJUI_PrevAndNext(
		long jiraIssueId, long projectId, java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_RJUI_PrevAndNext(jiraIssueId, projectId,
			reporterJiraUserId, obc);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByP_AJUI(
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByP_AJUI(projectId, assigneeJiraUserId);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByP_AJUI(
		long projectId, java.lang.String assigneeJiraUserId, int begin, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByP_AJUI(projectId, assigneeJiraUserId, begin, end);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByP_AJUI(
		long projectId, java.lang.String assigneeJiraUserId, int begin,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByP_AJUI(projectId, assigneeJiraUserId, begin, end, obc);
	}

	public static com.liferay.wol.model.JIRAIssue findByP_AJUI_First(
		long projectId, java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_AJUI_First(projectId, assigneeJiraUserId, obc);
	}

	public static com.liferay.wol.model.JIRAIssue findByP_AJUI_Last(
		long projectId, java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_AJUI_Last(projectId, assigneeJiraUserId, obc);
	}

	public static com.liferay.wol.model.JIRAIssue[] findByP_AJUI_PrevAndNext(
		long jiraIssueId, long projectId, java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_AJUI_PrevAndNext(jiraIssueId, projectId,
			assigneeJiraUserId, obc);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByMD_P_RJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByMD_P_RJUI(modifiedDate, projectId, reporterJiraUserId);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByMD_P_RJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId, int begin, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByMD_P_RJUI(modifiedDate, projectId,
			reporterJiraUserId, begin, end);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByMD_P_RJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId, int begin, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByMD_P_RJUI(modifiedDate, projectId,
			reporterJiraUserId, begin, end, obc);
	}

	public static com.liferay.wol.model.JIRAIssue findByMD_P_RJUI_First(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByMD_P_RJUI_First(modifiedDate, projectId,
			reporterJiraUserId, obc);
	}

	public static com.liferay.wol.model.JIRAIssue findByMD_P_RJUI_Last(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByMD_P_RJUI_Last(modifiedDate, projectId,
			reporterJiraUserId, obc);
	}

	public static com.liferay.wol.model.JIRAIssue[] findByMD_P_RJUI_PrevAndNext(
		long jiraIssueId, java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByMD_P_RJUI_PrevAndNext(jiraIssueId, modifiedDate,
			projectId, reporterJiraUserId, obc);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByMD_P_AJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByMD_P_AJUI(modifiedDate, projectId, assigneeJiraUserId);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByMD_P_AJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId, int begin, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByMD_P_AJUI(modifiedDate, projectId,
			assigneeJiraUserId, begin, end);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByMD_P_AJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId, int begin, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByMD_P_AJUI(modifiedDate, projectId,
			assigneeJiraUserId, begin, end, obc);
	}

	public static com.liferay.wol.model.JIRAIssue findByMD_P_AJUI_First(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByMD_P_AJUI_First(modifiedDate, projectId,
			assigneeJiraUserId, obc);
	}

	public static com.liferay.wol.model.JIRAIssue findByMD_P_AJUI_Last(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByMD_P_AJUI_Last(modifiedDate, projectId,
			assigneeJiraUserId, obc);
	}

	public static com.liferay.wol.model.JIRAIssue[] findByMD_P_AJUI_PrevAndNext(
		long jiraIssueId, java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByMD_P_AJUI_PrevAndNext(jiraIssueId, modifiedDate,
			projectId, assigneeJiraUserId, obc);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByP_RJUI_S(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status) throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByP_RJUI_S(projectId, reporterJiraUserId, status);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByP_RJUI_S(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status, int begin, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByP_RJUI_S(projectId, reporterJiraUserId, status,
			begin, end);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByP_RJUI_S(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status, int begin, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByP_RJUI_S(projectId, reporterJiraUserId, status,
			begin, end, obc);
	}

	public static com.liferay.wol.model.JIRAIssue findByP_RJUI_S_First(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_RJUI_S_First(projectId, reporterJiraUserId, status,
			obc);
	}

	public static com.liferay.wol.model.JIRAIssue findByP_RJUI_S_Last(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_RJUI_S_Last(projectId, reporterJiraUserId, status,
			obc);
	}

	public static com.liferay.wol.model.JIRAIssue[] findByP_RJUI_S_PrevAndNext(
		long jiraIssueId, long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_RJUI_S_PrevAndNext(jiraIssueId, projectId,
			reporterJiraUserId, status, obc);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByP_AJUI_S(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status) throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByP_AJUI_S(projectId, assigneeJiraUserId, status);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByP_AJUI_S(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status, int begin, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByP_AJUI_S(projectId, assigneeJiraUserId, status,
			begin, end);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findByP_AJUI_S(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status, int begin, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByP_AJUI_S(projectId, assigneeJiraUserId, status,
			begin, end, obc);
	}

	public static com.liferay.wol.model.JIRAIssue findByP_AJUI_S_First(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_AJUI_S_First(projectId, assigneeJiraUserId, status,
			obc);
	}

	public static com.liferay.wol.model.JIRAIssue findByP_AJUI_S_Last(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_AJUI_S_Last(projectId, assigneeJiraUserId, status,
			obc);
	}

	public static com.liferay.wol.model.JIRAIssue[] findByP_AJUI_S_PrevAndNext(
		long jiraIssueId, long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_AJUI_S_PrevAndNext(jiraIssueId, projectId,
			assigneeJiraUserId, status, obc);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer)
		throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(queryInitializer);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer,
		int begin, int end) throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findWithDynamicQuery(queryInitializer, begin, end);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findAll(
		int begin, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(begin, end);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> findAll(
		int begin, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(begin, end, obc);
	}

	public static void removeByProjectId(long projectId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByProjectId(projectId);
	}

	public static void removeByKey(java.lang.String key)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAIssueException {
		getPersistence().removeByKey(key);
	}

	public static void removeByReporterJiraUserId(
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByReporterJiraUserId(reporterJiraUserId);
	}

	public static void removeByAssigneeJiraUserId(
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByAssigneeJiraUserId(assigneeJiraUserId);
	}

	public static void removeByMD_P(java.util.Date modifiedDate, long projectId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByMD_P(modifiedDate, projectId);
	}

	public static void removeByP_RJUI(long projectId,
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByP_RJUI(projectId, reporterJiraUserId);
	}

	public static void removeByP_AJUI(long projectId,
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByP_AJUI(projectId, assigneeJiraUserId);
	}

	public static void removeByMD_P_RJUI(java.util.Date modifiedDate,
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.SystemException {
		getPersistence()
			.removeByMD_P_RJUI(modifiedDate, projectId, reporterJiraUserId);
	}

	public static void removeByMD_P_AJUI(java.util.Date modifiedDate,
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.SystemException {
		getPersistence()
			.removeByMD_P_AJUI(modifiedDate, projectId, assigneeJiraUserId);
	}

	public static void removeByP_RJUI_S(long projectId,
		java.lang.String reporterJiraUserId, java.lang.String status)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByP_RJUI_S(projectId, reporterJiraUserId, status);
	}

	public static void removeByP_AJUI_S(long projectId,
		java.lang.String assigneeJiraUserId, java.lang.String status)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByP_AJUI_S(projectId, assigneeJiraUserId, status);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByProjectId(long projectId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByProjectId(projectId);
	}

	public static int countByKey(java.lang.String key)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByKey(key);
	}

	public static int countByReporterJiraUserId(
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByReporterJiraUserId(reporterJiraUserId);
	}

	public static int countByAssigneeJiraUserId(
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByAssigneeJiraUserId(assigneeJiraUserId);
	}

	public static int countByMD_P(java.util.Date modifiedDate, long projectId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByMD_P(modifiedDate, projectId);
	}

	public static int countByP_RJUI(long projectId,
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByP_RJUI(projectId, reporterJiraUserId);
	}

	public static int countByP_AJUI(long projectId,
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByP_AJUI(projectId, assigneeJiraUserId);
	}

	public static int countByMD_P_RJUI(java.util.Date modifiedDate,
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .countByMD_P_RJUI(modifiedDate, projectId, reporterJiraUserId);
	}

	public static int countByMD_P_AJUI(java.util.Date modifiedDate,
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .countByMD_P_AJUI(modifiedDate, projectId, assigneeJiraUserId);
	}

	public static int countByP_RJUI_S(long projectId,
		java.lang.String reporterJiraUserId, java.lang.String status)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .countByP_RJUI_S(projectId, reporterJiraUserId, status);
	}

	public static int countByP_AJUI_S(long projectId,
		java.lang.String assigneeJiraUserId, java.lang.String status)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .countByP_AJUI_S(projectId, assigneeJiraUserId, status);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static JIRAIssuePersistence getPersistence() {
		return _getUtil()._persistence;
	}

	public void setPersistence(JIRAIssuePersistence persistence) {
		_persistence = persistence;
	}

	private static JIRAIssueUtil _getUtil() {
		if (_util == null) {
			_util = (JIRAIssueUtil)com.liferay.portlet.service.BeanLocatorUtil.locate(_UTIL);
		}

		return _util;
	}

	private static final String _UTIL = JIRAIssueUtil.class.getName();
	private static JIRAIssueUtil _util;
	private JIRAIssuePersistence _persistence;
}