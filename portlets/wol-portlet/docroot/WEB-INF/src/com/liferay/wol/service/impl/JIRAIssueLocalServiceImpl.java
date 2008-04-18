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

import com.liferay.portal.SystemException;
import com.liferay.wol.model.JIRAIssue;
import com.liferay.wol.service.base.JIRAIssueLocalServiceBaseImpl;

import java.util.List;

/**
 * <a href="JIRAIssueLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JIRAIssueLocalServiceImpl extends JIRAIssueLocalServiceBaseImpl {

	public List<JIRAIssue> getAssigneeJIRAIssues(
			long projectId, String assigneeJiraUserId, String status, int begin,
			int end)
		throws SystemException {

		return jiraIssuePersistence.findByP_AJUI_S(
			projectId, assigneeJiraUserId, status);
	}

	public int getAssigneeJIRAIssuesCount(
			long projectId, String assigneeJiraUserId, String status)
		throws SystemException {

		return jiraIssuePersistence.countByP_AJUI_S(
			projectId, assigneeJiraUserId, status);
	}

	public List<JIRAIssue> getReporterJIRAIssues(
			long projectId, String reporterJiraUserId, String status, int begin,
			int end)
		throws SystemException {

		return jiraIssuePersistence.findByP_RJUI_S(
			projectId, reporterJiraUserId, status);
	}

	public int getReporterJIRAIssuesCount(
			long projectId, String reporterJiraUserId, String status)
		throws SystemException {

		return jiraIssuePersistence.countByP_RJUI_S(
			projectId, reporterJiraUserId, status);
	}

}