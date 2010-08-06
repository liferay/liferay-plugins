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

package com.liferay.socialcoding.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * @author Brian Wing Shun Chan
 */
public class JIRAIssueLocalServiceClp implements JIRAIssueLocalService {
	public JIRAIssueLocalServiceClp(String className,
		ClassLoaderProxy classLoaderProxy) {
		_className = className;
		_classLoaderProxy = classLoaderProxy;
	}

	public com.liferay.socialcoding.model.JIRAIssue addJIRAIssue(
		com.liferay.socialcoding.model.JIRAIssue jiraIssue)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addJIRAIssueMethodKey0,
				jiraIssue);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.socialcoding.model.JIRAIssue)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.socialcoding.model.JIRAIssue createJIRAIssue(
		long jiraIssueId) {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_createJIRAIssueMethodKey1,
				jiraIssueId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.socialcoding.model.JIRAIssue)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteJIRAIssue(long jiraIssueId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteJIRAIssueMethodKey2,
				jiraIssueId);

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public void deleteJIRAIssue(
		com.liferay.socialcoding.model.JIRAIssue jiraIssue)
		throws com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteJIRAIssueMethodKey3,
				jiraIssue);

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey4,
				dynamicQuery);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey5,
				dynamicQuery, start, end);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey6,
				dynamicQuery, start, end, orderByComparator);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_dynamicQueryCountMethodKey7,
				dynamicQuery);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Long)returnObj).longValue();
	}

	public com.liferay.socialcoding.model.JIRAIssue getJIRAIssue(
		long jiraIssueId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getJIRAIssueMethodKey8,
				jiraIssueId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.socialcoding.model.JIRAIssue)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> getJIRAIssues(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getJIRAIssuesMethodKey9,
				start, end);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.socialcoding.model.JIRAIssue>)ClpSerializer.translateOutput(returnObj);
	}

	public int getJIRAIssuesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getJIRAIssuesCountMethodKey10);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public com.liferay.socialcoding.model.JIRAIssue updateJIRAIssue(
		com.liferay.socialcoding.model.JIRAIssue jiraIssue)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateJIRAIssueMethodKey11,
				jiraIssue);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.socialcoding.model.JIRAIssue)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.socialcoding.model.JIRAIssue updateJIRAIssue(
		com.liferay.socialcoding.model.JIRAIssue jiraIssue, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateJIRAIssueMethodKey12,
				jiraIssue, merge);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.socialcoding.model.JIRAIssue)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> getAssigneeJIRAIssues(
		long projectId, java.lang.String assigneeJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getAssigneeJIRAIssuesMethodKey13,
				projectId, assigneeJiraUserId, start, end);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.socialcoding.model.JIRAIssue>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> getAssigneeJIRAIssues(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getAssigneeJIRAIssuesMethodKey14,
				modifiedDate, projectId, assigneeJiraUserId, start, end);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.socialcoding.model.JIRAIssue>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> getAssigneeJIRAIssues(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getAssigneeJIRAIssuesMethodKey15,
				projectId, assigneeJiraUserId, status, start, end);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.socialcoding.model.JIRAIssue>)ClpSerializer.translateOutput(returnObj);
	}

	public int getAssigneeJIRAIssuesCount(long projectId,
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getAssigneeJIRAIssuesCountMethodKey16,
				projectId, assigneeJiraUserId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public int getAssigneeJIRAIssuesCount(java.util.Date modifiedDate,
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getAssigneeJIRAIssuesCountMethodKey17,
				modifiedDate, projectId, assigneeJiraUserId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public int getAssigneeJIRAIssuesCount(long projectId,
		java.lang.String assigneeJiraUserId, java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getAssigneeJIRAIssuesCountMethodKey18,
				projectId, assigneeJiraUserId, status);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public com.liferay.socialcoding.model.JIRAIssue getFirstAssigneeJIRAIssue(
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getFirstAssigneeJIRAIssueMethodKey19,
				projectId, assigneeJiraUserId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.socialcoding.model.JIRAIssue)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.socialcoding.model.JIRAIssue getFirstReporterJIRAIssue(
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getFirstReporterJIRAIssueMethodKey20,
				projectId, reporterJiraUserId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.socialcoding.model.JIRAIssue)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.socialcoding.model.JIRAIssue getJIRAIssue(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getJIRAIssueMethodKey21,
				key);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.socialcoding.model.JIRAIssue)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.socialcoding.model.JIRAIssue getLastAssigneeJIRAIssue(
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getLastAssigneeJIRAIssueMethodKey22,
				projectId, assigneeJiraUserId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.socialcoding.model.JIRAIssue)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.socialcoding.model.JIRAIssue getLastreporterJIRAIssue(
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getLastreporterJIRAIssueMethodKey23,
				projectId, reporterJiraUserId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.socialcoding.model.JIRAIssue)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> getReporterJIRAIssues(
		long projectId, java.lang.String reporterJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getReporterJIRAIssuesMethodKey24,
				projectId, reporterJiraUserId, start, end);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.socialcoding.model.JIRAIssue>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> getReporterJIRAIssues(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getReporterJIRAIssuesMethodKey25,
				modifiedDate, projectId, reporterJiraUserId, start, end);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.socialcoding.model.JIRAIssue>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.socialcoding.model.JIRAIssue> getReporterJIRAIssues(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getReporterJIRAIssuesMethodKey26,
				projectId, reporterJiraUserId, status, start, end);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.socialcoding.model.JIRAIssue>)ClpSerializer.translateOutput(returnObj);
	}

	public int getReporterJIRAIssuesCount(long projectId,
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getReporterJIRAIssuesCountMethodKey27,
				projectId, reporterJiraUserId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public int getReporterJIRAIssuesCount(java.util.Date modifiedDate,
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getReporterJIRAIssuesCountMethodKey28,
				modifiedDate, projectId, reporterJiraUserId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public int getReporterJIRAIssuesCount(long projectId,
		java.lang.String reporterJiraUserId, java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getReporterJIRAIssuesCountMethodKey29,
				projectId, reporterJiraUserId, status);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public void updateJIRAIssues(long projectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_updateJIRAIssuesMethodKey30,
				projectId);

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public ClassLoaderProxy getClassLoaderProxy() {
		return _classLoaderProxy;
	}

	private String _className;
	private ClassLoaderProxy _classLoaderProxy;
	private final MethodKey _addJIRAIssueMethodKey0 = new MethodKey(_className,
			"addJIRAIssue", com.liferay.socialcoding.model.JIRAIssue.class);
	private final MethodKey _createJIRAIssueMethodKey1 = new MethodKey(_className,
			"createJIRAIssue", long.class);
	private final MethodKey _deleteJIRAIssueMethodKey2 = new MethodKey(_className,
			"deleteJIRAIssue", long.class);
	private final MethodKey _deleteJIRAIssueMethodKey3 = new MethodKey(_className,
			"deleteJIRAIssue", com.liferay.socialcoding.model.JIRAIssue.class);
	private final MethodKey _dynamicQueryMethodKey4 = new MethodKey(_className,
			"dynamicQuery", com.liferay.portal.kernel.dao.orm.DynamicQuery.class);
	private final MethodKey _dynamicQueryMethodKey5 = new MethodKey(_className,
			"dynamicQuery",
			com.liferay.portal.kernel.dao.orm.DynamicQuery.class, int.class,
			int.class);
	private final MethodKey _dynamicQueryMethodKey6 = new MethodKey(_className,
			"dynamicQuery",
			com.liferay.portal.kernel.dao.orm.DynamicQuery.class, int.class,
			int.class, com.liferay.portal.kernel.util.OrderByComparator.class);
	private final MethodKey _dynamicQueryCountMethodKey7 = new MethodKey(_className,
			"dynamicQueryCount",
			com.liferay.portal.kernel.dao.orm.DynamicQuery.class);
	private final MethodKey _getJIRAIssueMethodKey8 = new MethodKey(_className,
			"getJIRAIssue", long.class);
	private final MethodKey _getJIRAIssuesMethodKey9 = new MethodKey(_className,
			"getJIRAIssues", int.class, int.class);
	private final MethodKey _getJIRAIssuesCountMethodKey10 = new MethodKey(_className,
			"getJIRAIssuesCount");
	private final MethodKey _updateJIRAIssueMethodKey11 = new MethodKey(_className,
			"updateJIRAIssue", com.liferay.socialcoding.model.JIRAIssue.class);
	private final MethodKey _updateJIRAIssueMethodKey12 = new MethodKey(_className,
			"updateJIRAIssue", com.liferay.socialcoding.model.JIRAIssue.class,
			boolean.class);
	private final MethodKey _getAssigneeJIRAIssuesMethodKey13 = new MethodKey(_className,
			"getAssigneeJIRAIssues", long.class, java.lang.String.class,
			int.class, int.class);
	private final MethodKey _getAssigneeJIRAIssuesMethodKey14 = new MethodKey(_className,
			"getAssigneeJIRAIssues", java.util.Date.class, long.class,
			java.lang.String.class, int.class, int.class);
	private final MethodKey _getAssigneeJIRAIssuesMethodKey15 = new MethodKey(_className,
			"getAssigneeJIRAIssues", long.class, java.lang.String.class,
			java.lang.String.class, int.class, int.class);
	private final MethodKey _getAssigneeJIRAIssuesCountMethodKey16 = new MethodKey(_className,
			"getAssigneeJIRAIssuesCount", long.class, java.lang.String.class);
	private final MethodKey _getAssigneeJIRAIssuesCountMethodKey17 = new MethodKey(_className,
			"getAssigneeJIRAIssuesCount", java.util.Date.class, long.class,
			java.lang.String.class);
	private final MethodKey _getAssigneeJIRAIssuesCountMethodKey18 = new MethodKey(_className,
			"getAssigneeJIRAIssuesCount", long.class, java.lang.String.class,
			java.lang.String.class);
	private final MethodKey _getFirstAssigneeJIRAIssueMethodKey19 = new MethodKey(_className,
			"getFirstAssigneeJIRAIssue", long.class, java.lang.String.class);
	private final MethodKey _getFirstReporterJIRAIssueMethodKey20 = new MethodKey(_className,
			"getFirstReporterJIRAIssue", long.class, java.lang.String.class);
	private final MethodKey _getJIRAIssueMethodKey21 = new MethodKey(_className,
			"getJIRAIssue", java.lang.String.class);
	private final MethodKey _getLastAssigneeJIRAIssueMethodKey22 = new MethodKey(_className,
			"getLastAssigneeJIRAIssue", long.class, java.lang.String.class);
	private final MethodKey _getLastreporterJIRAIssueMethodKey23 = new MethodKey(_className,
			"getLastreporterJIRAIssue", long.class, java.lang.String.class);
	private final MethodKey _getReporterJIRAIssuesMethodKey24 = new MethodKey(_className,
			"getReporterJIRAIssues", long.class, java.lang.String.class,
			int.class, int.class);
	private final MethodKey _getReporterJIRAIssuesMethodKey25 = new MethodKey(_className,
			"getReporterJIRAIssues", java.util.Date.class, long.class,
			java.lang.String.class, int.class, int.class);
	private final MethodKey _getReporterJIRAIssuesMethodKey26 = new MethodKey(_className,
			"getReporterJIRAIssues", long.class, java.lang.String.class,
			java.lang.String.class, int.class, int.class);
	private final MethodKey _getReporterJIRAIssuesCountMethodKey27 = new MethodKey(_className,
			"getReporterJIRAIssuesCount", long.class, java.lang.String.class);
	private final MethodKey _getReporterJIRAIssuesCountMethodKey28 = new MethodKey(_className,
			"getReporterJIRAIssuesCount", java.util.Date.class, long.class,
			java.lang.String.class);
	private final MethodKey _getReporterJIRAIssuesCountMethodKey29 = new MethodKey(_className,
			"getReporterJIRAIssuesCount", long.class, java.lang.String.class,
			java.lang.String.class);
	private final MethodKey _updateJIRAIssuesMethodKey30 = new MethodKey(_className,
			"updateJIRAIssues", long.class);
}