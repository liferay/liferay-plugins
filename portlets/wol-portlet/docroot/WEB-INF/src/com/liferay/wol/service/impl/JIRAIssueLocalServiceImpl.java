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
import com.liferay.wol.NoSuchJIRAIssueException;
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
			long projectId, String assigneeJiraUserId, int begin, int end)
		throws SystemException {

		return jiraIssuePersistence.findByP_AJUI(projectId, assigneeJiraUserId);
	}

	public List<JIRAIssue> getAssigneeJIRAIssues(
			Date modifiedDate, long projectId, String assigneeJiraUserId,
			int begin, int end)
		throws SystemException {

		return jiraIssuePersistence.findByMD_P_AJUI(
			modifiedDate, projectId, assigneeJiraUserId);
	}

	public List<JIRAIssue> getAssigneeJIRAIssues(
			long projectId, String assigneeJiraUserId, String status, int begin,
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
			long projectId, String reporterJiraUserId, int begin, int end)
		throws SystemException {

		return jiraIssuePersistence.findByP_RJUI(projectId, reporterJiraUserId);
	}

	public List<JIRAIssue> getReporterJIRAIssues(
			Date modifiedDate, long projectId, String reporterJiraUserId,
			int begin, int end)
		throws SystemException {

		return jiraIssuePersistence.findByMD_P_RJUI(
			modifiedDate, projectId, reporterJiraUserId);
	}

	public List<JIRAIssue> getReporterJIRAIssues(
			long projectId, String reporterJiraUserId, String status, int begin,
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

}