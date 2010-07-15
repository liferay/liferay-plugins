/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.socialcoding.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.socialcoding.model.JIRAIssue;

/**
 * @author    Brian Wing Shun Chan
 * @see       JIRAIssuePersistenceImpl
 * @see       JIRAIssueUtil
 * @generated
 */
public interface JIRAIssuePersistence extends BasePersistence<JIRAIssue> {
	public void cacheResult(com.liferay.socialcoding.model.JIRAIssue jiraIssue);

	public void cacheResult(
		java.util.List<com.liferay.socialcoding.model.JIRAIssue> jiraIssues);

	public com.liferay.socialcoding.model.JIRAIssue create(long jiraIssueId);

	public com.liferay.socialcoding.model.JIRAIssue remove(long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public com.liferay.socialcoding.model.JIRAIssue updateImpl(
		com.liferay.socialcoding.model.JIRAIssue jiraIssue, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialcoding.model.JIRAIssue findByPrimaryKey(
		long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public com.liferay.socialcoding.model.JIRAIssue fetchByPrimaryKey(
		long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByProjectId(
		long projectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByProjectId(
		long projectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByProjectId(
		long projectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialcoding.model.JIRAIssue findByProjectId_First(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public com.liferay.socialcoding.model.JIRAIssue findByProjectId_Last(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public com.liferay.socialcoding.model.JIRAIssue[] findByProjectId_PrevAndNext(
		long jiraIssueId, long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public com.liferay.socialcoding.model.JIRAIssue findByKey(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public com.liferay.socialcoding.model.JIRAIssue fetchByKey(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialcoding.model.JIRAIssue fetchByKey(
		java.lang.String key, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByReporterJiraUserId(
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByReporterJiraUserId(
		java.lang.String reporterJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByReporterJiraUserId(
		java.lang.String reporterJiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialcoding.model.JIRAIssue findByReporterJiraUserId_First(
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public com.liferay.socialcoding.model.JIRAIssue findByReporterJiraUserId_Last(
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public com.liferay.socialcoding.model.JIRAIssue[] findByReporterJiraUserId_PrevAndNext(
		long jiraIssueId, java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByAssigneeJiraUserId(
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByAssigneeJiraUserId(
		java.lang.String assigneeJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByAssigneeJiraUserId(
		java.lang.String assigneeJiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialcoding.model.JIRAIssue findByAssigneeJiraUserId_First(
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public com.liferay.socialcoding.model.JIRAIssue findByAssigneeJiraUserId_Last(
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public com.liferay.socialcoding.model.JIRAIssue[] findByAssigneeJiraUserId_PrevAndNext(
		long jiraIssueId, java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P(
		java.util.Date modifiedDate, long projectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P(
		java.util.Date modifiedDate, long projectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P(
		java.util.Date modifiedDate, long projectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialcoding.model.JIRAIssue findByMD_P_First(
		java.util.Date modifiedDate, long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public com.liferay.socialcoding.model.JIRAIssue findByMD_P_Last(
		java.util.Date modifiedDate, long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public com.liferay.socialcoding.model.JIRAIssue[] findByMD_P_PrevAndNext(
		long jiraIssueId, java.util.Date modifiedDate, long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_RJUI(
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_RJUI(
		long projectId, java.lang.String reporterJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_RJUI(
		long projectId, java.lang.String reporterJiraUserId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialcoding.model.JIRAIssue findByP_RJUI_First(
		long projectId, java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public com.liferay.socialcoding.model.JIRAIssue findByP_RJUI_Last(
		long projectId, java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public com.liferay.socialcoding.model.JIRAIssue[] findByP_RJUI_PrevAndNext(
		long jiraIssueId, long projectId, java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_AJUI(
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_AJUI(
		long projectId, java.lang.String assigneeJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_AJUI(
		long projectId, java.lang.String assigneeJiraUserId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialcoding.model.JIRAIssue findByP_AJUI_First(
		long projectId, java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public com.liferay.socialcoding.model.JIRAIssue findByP_AJUI_Last(
		long projectId, java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public com.liferay.socialcoding.model.JIRAIssue[] findByP_AJUI_PrevAndNext(
		long jiraIssueId, long projectId, java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P_RJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P_RJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P_RJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialcoding.model.JIRAIssue findByMD_P_RJUI_First(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public com.liferay.socialcoding.model.JIRAIssue findByMD_P_RJUI_Last(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public com.liferay.socialcoding.model.JIRAIssue[] findByMD_P_RJUI_PrevAndNext(
		long jiraIssueId, java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P_AJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P_AJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P_AJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialcoding.model.JIRAIssue findByMD_P_AJUI_First(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public com.liferay.socialcoding.model.JIRAIssue findByMD_P_AJUI_Last(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public com.liferay.socialcoding.model.JIRAIssue[] findByMD_P_AJUI_PrevAndNext(
		long jiraIssueId, java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_RJUI_S(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_RJUI_S(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_RJUI_S(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialcoding.model.JIRAIssue findByP_RJUI_S_First(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public com.liferay.socialcoding.model.JIRAIssue findByP_RJUI_S_Last(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public com.liferay.socialcoding.model.JIRAIssue[] findByP_RJUI_S_PrevAndNext(
		long jiraIssueId, long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_AJUI_S(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_AJUI_S(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_AJUI_S(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialcoding.model.JIRAIssue findByP_AJUI_S_First(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public com.liferay.socialcoding.model.JIRAIssue findByP_AJUI_S_Last(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public com.liferay.socialcoding.model.JIRAIssue[] findByP_AJUI_S_PrevAndNext(
		long jiraIssueId, long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByProjectId(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException;

	public void removeByReporterJiraUserId(java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByAssigneeJiraUserId(java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByMD_P(java.util.Date modifiedDate, long projectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByP_RJUI(long projectId,
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByP_AJUI(long projectId,
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByMD_P_RJUI(java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByMD_P_AJUI(java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByP_RJUI_S(long projectId,
		java.lang.String reporterJiraUserId, java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByP_AJUI_S(long projectId,
		java.lang.String assigneeJiraUserId, java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByProjectId(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByReporterJiraUserId(java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByAssigneeJiraUserId(java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByMD_P(java.util.Date modifiedDate, long projectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByP_RJUI(long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByP_AJUI(long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByMD_P_RJUI(java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByMD_P_AJUI(java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByP_RJUI_S(long projectId,
		java.lang.String reporterJiraUserId, java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByP_AJUI_S(long projectId,
		java.lang.String assigneeJiraUserId, java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}