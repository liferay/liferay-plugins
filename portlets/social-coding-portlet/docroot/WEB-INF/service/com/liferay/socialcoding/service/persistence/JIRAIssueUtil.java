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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

import com.liferay.socialcoding.model.JIRAIssue;

import java.util.List;

/**
 * <a href="JIRAIssueUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       JIRAIssuePersistence
 * @see       JIRAIssuePersistenceImpl
 * @generated
 */
public class JIRAIssueUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(JIRAIssue jiraIssue) {
		getPersistence().clearCache(jiraIssue);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<JIRAIssue> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<JIRAIssue> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<JIRAIssue> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static JIRAIssue remove(JIRAIssue jiraIssue)
		throws SystemException {
		return getPersistence().remove(jiraIssue);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static JIRAIssue update(JIRAIssue jiraIssue, boolean merge)
		throws SystemException {
		return getPersistence().update(jiraIssue, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static JIRAIssue update(JIRAIssue jiraIssue, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(jiraIssue, merge, serviceContext);
	}

	public static void cacheResult(
		com.liferay.socialcoding.model.JIRAIssue jiraIssue) {
		getPersistence().cacheResult(jiraIssue);
	}

	public static void cacheResult(
		java.util.List<com.liferay.socialcoding.model.JIRAIssue> jiraIssues) {
		getPersistence().cacheResult(jiraIssues);
	}

	public static com.liferay.socialcoding.model.JIRAIssue create(
		long jiraIssueId) {
		return getPersistence().create(jiraIssueId);
	}

	public static com.liferay.socialcoding.model.JIRAIssue remove(
		long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence().remove(jiraIssueId);
	}

	public static com.liferay.socialcoding.model.JIRAIssue updateImpl(
		com.liferay.socialcoding.model.JIRAIssue jiraIssue, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(jiraIssue, merge);
	}

	public static com.liferay.socialcoding.model.JIRAIssue findByPrimaryKey(
		long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence().findByPrimaryKey(jiraIssueId);
	}

	public static com.liferay.socialcoding.model.JIRAIssue fetchByPrimaryKey(
		long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(jiraIssueId);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByProjectId(
		long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByProjectId(projectId);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByProjectId(
		long projectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByProjectId(projectId, start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByProjectId(
		long projectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByProjectId(projectId, start, end, orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue findByProjectId_First(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByProjectId_First(projectId, orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue findByProjectId_Last(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByProjectId_Last(projectId, orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue[] findByProjectId_PrevAndNext(
		long jiraIssueId, long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByProjectId_PrevAndNext(jiraIssueId, projectId,
			orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue findByKey(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence().findByKey(key);
	}

	public static com.liferay.socialcoding.model.JIRAIssue fetchByKey(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByKey(key);
	}

	public static com.liferay.socialcoding.model.JIRAIssue fetchByKey(
		java.lang.String key, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByKey(key, retrieveFromCache);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByReporterJiraUserId(
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByReporterJiraUserId(reporterJiraUserId);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByReporterJiraUserId(
		java.lang.String reporterJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByReporterJiraUserId(reporterJiraUserId, start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByReporterJiraUserId(
		java.lang.String reporterJiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByReporterJiraUserId(reporterJiraUserId, start, end,
			orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue findByReporterJiraUserId_First(
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByReporterJiraUserId_First(reporterJiraUserId,
			orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue findByReporterJiraUserId_Last(
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByReporterJiraUserId_Last(reporterJiraUserId,
			orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue[] findByReporterJiraUserId_PrevAndNext(
		long jiraIssueId, java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByReporterJiraUserId_PrevAndNext(jiraIssueId,
			reporterJiraUserId, orderByComparator);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByAssigneeJiraUserId(
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAssigneeJiraUserId(assigneeJiraUserId);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByAssigneeJiraUserId(
		java.lang.String assigneeJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssigneeJiraUserId(assigneeJiraUserId, start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByAssigneeJiraUserId(
		java.lang.String assigneeJiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssigneeJiraUserId(assigneeJiraUserId, start, end,
			orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue findByAssigneeJiraUserId_First(
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByAssigneeJiraUserId_First(assigneeJiraUserId,
			orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue findByAssigneeJiraUserId_Last(
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByAssigneeJiraUserId_Last(assigneeJiraUserId,
			orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue[] findByAssigneeJiraUserId_PrevAndNext(
		long jiraIssueId, java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByAssigneeJiraUserId_PrevAndNext(jiraIssueId,
			assigneeJiraUserId, orderByComparator);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P(
		java.util.Date modifiedDate, long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByMD_P(modifiedDate, projectId);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P(
		java.util.Date modifiedDate, long projectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByMD_P(modifiedDate, projectId, start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P(
		java.util.Date modifiedDate, long projectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMD_P(modifiedDate, projectId, start, end,
			orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue findByMD_P_First(
		java.util.Date modifiedDate, long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByMD_P_First(modifiedDate, projectId, orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue findByMD_P_Last(
		java.util.Date modifiedDate, long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByMD_P_Last(modifiedDate, projectId, orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue[] findByMD_P_PrevAndNext(
		long jiraIssueId, java.util.Date modifiedDate, long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByMD_P_PrevAndNext(jiraIssueId, modifiedDate,
			projectId, orderByComparator);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_RJUI(
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByP_RJUI(projectId, reporterJiraUserId);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_RJUI(
		long projectId, java.lang.String reporterJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_RJUI(projectId, reporterJiraUserId, start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_RJUI(
		long projectId, java.lang.String reporterJiraUserId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_RJUI(projectId, reporterJiraUserId, start, end,
			orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue findByP_RJUI_First(
		long projectId, java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_RJUI_First(projectId, reporterJiraUserId,
			orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue findByP_RJUI_Last(
		long projectId, java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_RJUI_Last(projectId, reporterJiraUserId,
			orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue[] findByP_RJUI_PrevAndNext(
		long jiraIssueId, long projectId, java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_RJUI_PrevAndNext(jiraIssueId, projectId,
			reporterJiraUserId, orderByComparator);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_AJUI(
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByP_AJUI(projectId, assigneeJiraUserId);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_AJUI(
		long projectId, java.lang.String assigneeJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_AJUI(projectId, assigneeJiraUserId, start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_AJUI(
		long projectId, java.lang.String assigneeJiraUserId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_AJUI(projectId, assigneeJiraUserId, start, end,
			orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue findByP_AJUI_First(
		long projectId, java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_AJUI_First(projectId, assigneeJiraUserId,
			orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue findByP_AJUI_Last(
		long projectId, java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_AJUI_Last(projectId, assigneeJiraUserId,
			orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue[] findByP_AJUI_PrevAndNext(
		long jiraIssueId, long projectId, java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_AJUI_PrevAndNext(jiraIssueId, projectId,
			assigneeJiraUserId, orderByComparator);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P_RJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMD_P_RJUI(modifiedDate, projectId, reporterJiraUserId);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P_RJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMD_P_RJUI(modifiedDate, projectId,
			reporterJiraUserId, start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P_RJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMD_P_RJUI(modifiedDate, projectId,
			reporterJiraUserId, start, end, orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue findByMD_P_RJUI_First(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByMD_P_RJUI_First(modifiedDate, projectId,
			reporterJiraUserId, orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue findByMD_P_RJUI_Last(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByMD_P_RJUI_Last(modifiedDate, projectId,
			reporterJiraUserId, orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue[] findByMD_P_RJUI_PrevAndNext(
		long jiraIssueId, java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByMD_P_RJUI_PrevAndNext(jiraIssueId, modifiedDate,
			projectId, reporterJiraUserId, orderByComparator);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P_AJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMD_P_AJUI(modifiedDate, projectId, assigneeJiraUserId);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P_AJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMD_P_AJUI(modifiedDate, projectId,
			assigneeJiraUserId, start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByMD_P_AJUI(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMD_P_AJUI(modifiedDate, projectId,
			assigneeJiraUserId, start, end, orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue findByMD_P_AJUI_First(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByMD_P_AJUI_First(modifiedDate, projectId,
			assigneeJiraUserId, orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue findByMD_P_AJUI_Last(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByMD_P_AJUI_Last(modifiedDate, projectId,
			assigneeJiraUserId, orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue[] findByMD_P_AJUI_PrevAndNext(
		long jiraIssueId, java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByMD_P_AJUI_PrevAndNext(jiraIssueId, modifiedDate,
			projectId, assigneeJiraUserId, orderByComparator);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_RJUI_S(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_RJUI_S(projectId, reporterJiraUserId, status);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_RJUI_S(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_RJUI_S(projectId, reporterJiraUserId, status,
			start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_RJUI_S(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_RJUI_S(projectId, reporterJiraUserId, status,
			start, end, orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue findByP_RJUI_S_First(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_RJUI_S_First(projectId, reporterJiraUserId, status,
			orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue findByP_RJUI_S_Last(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_RJUI_S_Last(projectId, reporterJiraUserId, status,
			orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue[] findByP_RJUI_S_PrevAndNext(
		long jiraIssueId, long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_RJUI_S_PrevAndNext(jiraIssueId, projectId,
			reporterJiraUserId, status, orderByComparator);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_AJUI_S(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_AJUI_S(projectId, assigneeJiraUserId, status);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_AJUI_S(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_AJUI_S(projectId, assigneeJiraUserId, status,
			start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByP_AJUI_S(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_AJUI_S(projectId, assigneeJiraUserId, status,
			start, end, orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue findByP_AJUI_S_First(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_AJUI_S_First(projectId, assigneeJiraUserId, status,
			orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue findByP_AJUI_S_Last(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_AJUI_S_Last(projectId, assigneeJiraUserId, status,
			orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAIssue[] findByP_AJUI_S_PrevAndNext(
		long jiraIssueId, long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		return getPersistence()
				   .findByP_AJUI_S_PrevAndNext(jiraIssueId, projectId,
			assigneeJiraUserId, status, orderByComparator);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	public static void removeByProjectId(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByProjectId(projectId);
	}

	public static void removeByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAIssueException {
		getPersistence().removeByKey(key);
	}

	public static void removeByReporterJiraUserId(
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByReporterJiraUserId(reporterJiraUserId);
	}

	public static void removeByAssigneeJiraUserId(
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAssigneeJiraUserId(assigneeJiraUserId);
	}

	public static void removeByMD_P(java.util.Date modifiedDate, long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByMD_P(modifiedDate, projectId);
	}

	public static void removeByP_RJUI(long projectId,
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByP_RJUI(projectId, reporterJiraUserId);
	}

	public static void removeByP_AJUI(long projectId,
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByP_AJUI(projectId, assigneeJiraUserId);
	}

	public static void removeByMD_P_RJUI(java.util.Date modifiedDate,
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByMD_P_RJUI(modifiedDate, projectId, reporterJiraUserId);
	}

	public static void removeByMD_P_AJUI(java.util.Date modifiedDate,
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByMD_P_AJUI(modifiedDate, projectId, assigneeJiraUserId);
	}

	public static void removeByP_RJUI_S(long projectId,
		java.lang.String reporterJiraUserId, java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByP_RJUI_S(projectId, reporterJiraUserId, status);
	}

	public static void removeByP_AJUI_S(long projectId,
		java.lang.String assigneeJiraUserId, java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByP_AJUI_S(projectId, assigneeJiraUserId, status);
	}

	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	public static int countByProjectId(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByProjectId(projectId);
	}

	public static int countByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKey(key);
	}

	public static int countByReporterJiraUserId(
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByReporterJiraUserId(reporterJiraUserId);
	}

	public static int countByAssigneeJiraUserId(
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAssigneeJiraUserId(assigneeJiraUserId);
	}

	public static int countByMD_P(java.util.Date modifiedDate, long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByMD_P(modifiedDate, projectId);
	}

	public static int countByP_RJUI(long projectId,
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByP_RJUI(projectId, reporterJiraUserId);
	}

	public static int countByP_AJUI(long projectId,
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByP_AJUI(projectId, assigneeJiraUserId);
	}

	public static int countByMD_P_RJUI(java.util.Date modifiedDate,
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByMD_P_RJUI(modifiedDate, projectId, reporterJiraUserId);
	}

	public static int countByMD_P_AJUI(java.util.Date modifiedDate,
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByMD_P_AJUI(modifiedDate, projectId, assigneeJiraUserId);
	}

	public static int countByP_RJUI_S(long projectId,
		java.lang.String reporterJiraUserId, java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByP_RJUI_S(projectId, reporterJiraUserId, status);
	}

	public static int countByP_AJUI_S(long projectId,
		java.lang.String assigneeJiraUserId, java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByP_AJUI_S(projectId, assigneeJiraUserId, status);
	}

	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static JIRAIssuePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (JIRAIssuePersistence)PortletBeanLocatorUtil.locate(com.liferay.socialcoding.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					JIRAIssuePersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(JIRAIssuePersistence persistence) {
		_persistence = persistence;
	}

	private static JIRAIssuePersistence _persistence;
}