/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.wol.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;

/**
 * <a href="JIRAIssueLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
@Transactional(rollbackFor =  {
	PortalException.class, SystemException.class})
public interface JIRAIssueLocalService {
	public com.liferay.wol.model.JIRAIssue addJIRAIssue(
		com.liferay.wol.model.JIRAIssue jiraIssue)
		throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.JIRAIssue createJIRAIssue(long jiraIssueId);

	public void deleteJIRAIssue(long jiraIssueId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void deleteJIRAIssue(com.liferay.wol.model.JIRAIssue jiraIssue)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.wol.model.JIRAIssue getJIRAIssue(long jiraIssueId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.wol.model.JIRAIssue> getJIRAIssues(
		int start, int end) throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getJIRAIssuesCount() throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.JIRAIssue updateJIRAIssue(
		com.liferay.wol.model.JIRAIssue jiraIssue)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.wol.model.JIRAIssue> getAssigneeJIRAIssues(
		long projectId, java.lang.String assigneeJiraUserId, int start, int end)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.wol.model.JIRAIssue> getAssigneeJIRAIssues(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId, int start, int end)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.wol.model.JIRAIssue> getAssigneeJIRAIssues(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status, int start, int end)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssigneeJIRAIssuesCount(long projectId,
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssigneeJIRAIssuesCount(java.util.Date modifiedDate,
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssigneeJIRAIssuesCount(long projectId,
		java.lang.String assigneeJiraUserId, java.lang.String status)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.wol.model.JIRAIssue getFirstAssigneeJIRAIssue(
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.wol.model.JIRAIssue getFirstReporterJIRAIssue(
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.wol.model.JIRAIssue getJIRAIssue(java.lang.String key)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.wol.model.JIRAIssue getLastAssigneeJIRAIssue(
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.wol.model.JIRAIssue getLastreporterJIRAIssue(
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.wol.model.JIRAIssue> getReporterJIRAIssues(
		long projectId, java.lang.String reporterJiraUserId, int start, int end)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.wol.model.JIRAIssue> getReporterJIRAIssues(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId, int start, int end)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.wol.model.JIRAIssue> getReporterJIRAIssues(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status, int start, int end)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getReporterJIRAIssuesCount(long projectId,
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getReporterJIRAIssuesCount(java.util.Date modifiedDate,
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getReporterJIRAIssuesCount(long projectId,
		java.lang.String reporterJiraUserId, java.lang.String status)
		throws com.liferay.portal.SystemException;

	public void updateJIRAIssues(long projectId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;
}