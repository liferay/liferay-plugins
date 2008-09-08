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

package com.liferay.wol.service;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.LongWrapper;
import com.liferay.portal.kernel.util.NullWrapper;
import com.liferay.portal.model.BaseModel;

import com.liferay.wol.model.JIRAIssueClp;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="JIRAIssueLocalServiceClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JIRAIssueLocalServiceClp implements JIRAIssueLocalService {
	public JIRAIssueLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;
		_classLoader = classLoaderProxy.getClassLoader();
	}

	public com.liferay.wol.model.JIRAIssue addJIRAIssue(
		com.liferay.wol.model.JIRAIssue jiraIssue)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(jiraIssue);

		if (jiraIssue == null) {
			paramObj0 = new NullWrapper("com.liferay.wol.model.JIRAIssue");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addJIRAIssue",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.wol.model.JIRAIssue)translateOutput(returnObj);
	}

	public com.liferay.wol.model.JIRAIssue createJIRAIssue(long jiraIssueId) {
		Object paramObj0 = new LongWrapper(jiraIssueId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("createJIRAIssue",
					new Object[] { paramObj0 });
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

		return (com.liferay.wol.model.JIRAIssue)translateOutput(returnObj);
	}

	public void deleteJIRAIssue(long jiraIssueId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(jiraIssueId);

		try {
			_classLoaderProxy.invoke("deleteJIRAIssue",
				new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
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

	public void deleteJIRAIssue(com.liferay.wol.model.JIRAIssue jiraIssue)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(jiraIssue);

		if (jiraIssue == null) {
			paramObj0 = new NullWrapper("com.liferay.wol.model.JIRAIssue");
		}

		try {
			_classLoaderProxy.invoke("deleteJIRAIssue",
				new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
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

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(dynamicQuery);

		if (dynamicQuery == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.kernel.dao.orm.DynamicQuery");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("dynamicQuery",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<Object>)translateOutput(returnObj);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(dynamicQuery);

		if (dynamicQuery == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.kernel.dao.orm.DynamicQuery");
		}

		Object paramObj1 = new IntegerWrapper(start);

		Object paramObj2 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("dynamicQuery",
					new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<Object>)translateOutput(returnObj);
	}

	public com.liferay.wol.model.JIRAIssue getJIRAIssue(long jiraIssueId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(jiraIssueId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getJIRAIssue",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.wol.model.JIRAIssue)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.wol.model.JIRAIssue> getJIRAIssues(
		int start, int end) throws com.liferay.portal.SystemException {
		Object paramObj0 = new IntegerWrapper(start);

		Object paramObj1 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getJIRAIssues",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.wol.model.JIRAIssue>)translateOutput(returnObj);
	}

	public int getJIRAIssuesCount() throws com.liferay.portal.SystemException {
		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getJIRAIssuesCount",
					new Object[0]);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
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

	public com.liferay.wol.model.JIRAIssue updateJIRAIssue(
		com.liferay.wol.model.JIRAIssue jiraIssue)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(jiraIssue);

		if (jiraIssue == null) {
			paramObj0 = new NullWrapper("com.liferay.wol.model.JIRAIssue");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateJIRAIssue",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.wol.model.JIRAIssue)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.wol.model.JIRAIssue> getAssigneeJIRAIssues(
		long projectId, java.lang.String assigneeJiraUserId, int start, int end)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(projectId);

		Object paramObj1 = translateInput(assigneeJiraUserId);

		if (assigneeJiraUserId == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = new IntegerWrapper(start);

		Object paramObj3 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getAssigneeJIRAIssues",
					new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.wol.model.JIRAIssue>)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.wol.model.JIRAIssue> getAssigneeJIRAIssues(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId, int start, int end)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(modifiedDate);

		if (modifiedDate == null) {
			paramObj0 = new NullWrapper("java.util.Date");
		}

		Object paramObj1 = new LongWrapper(projectId);

		Object paramObj2 = translateInput(assigneeJiraUserId);

		if (assigneeJiraUserId == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = new IntegerWrapper(start);

		Object paramObj4 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getAssigneeJIRAIssues",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.wol.model.JIRAIssue>)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.wol.model.JIRAIssue> getAssigneeJIRAIssues(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status, int start, int end)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(projectId);

		Object paramObj1 = translateInput(assigneeJiraUserId);

		if (assigneeJiraUserId == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = translateInput(status);

		if (status == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = new IntegerWrapper(start);

		Object paramObj4 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getAssigneeJIRAIssues",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.wol.model.JIRAIssue>)translateOutput(returnObj);
	}

	public int getAssigneeJIRAIssuesCount(long projectId,
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(projectId);

		Object paramObj1 = translateInput(assigneeJiraUserId);

		if (assigneeJiraUserId == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getAssigneeJIRAIssuesCount",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
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
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(modifiedDate);

		if (modifiedDate == null) {
			paramObj0 = new NullWrapper("java.util.Date");
		}

		Object paramObj1 = new LongWrapper(projectId);

		Object paramObj2 = translateInput(assigneeJiraUserId);

		if (assigneeJiraUserId == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getAssigneeJIRAIssuesCount",
					new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
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
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(projectId);

		Object paramObj1 = translateInput(assigneeJiraUserId);

		if (assigneeJiraUserId == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = translateInput(status);

		if (status == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getAssigneeJIRAIssuesCount",
					new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
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

	public com.liferay.wol.model.JIRAIssue getFirstAssigneeJIRAIssue(
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(projectId);

		Object paramObj1 = translateInput(assigneeJiraUserId);

		if (assigneeJiraUserId == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getFirstAssigneeJIRAIssue",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.wol.model.JIRAIssue)translateOutput(returnObj);
	}

	public com.liferay.wol.model.JIRAIssue getFirstReporterJIRAIssue(
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(projectId);

		Object paramObj1 = translateInput(reporterJiraUserId);

		if (reporterJiraUserId == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getFirstReporterJIRAIssue",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.wol.model.JIRAIssue)translateOutput(returnObj);
	}

	public com.liferay.wol.model.JIRAIssue getJIRAIssue(java.lang.String key)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(key);

		if (key == null) {
			paramObj0 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getJIRAIssue",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.wol.model.JIRAIssue)translateOutput(returnObj);
	}

	public com.liferay.wol.model.JIRAIssue getLastAssigneeJIRAIssue(
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(projectId);

		Object paramObj1 = translateInput(assigneeJiraUserId);

		if (assigneeJiraUserId == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getLastAssigneeJIRAIssue",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.wol.model.JIRAIssue)translateOutput(returnObj);
	}

	public com.liferay.wol.model.JIRAIssue getLastreporterJIRAIssue(
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(projectId);

		Object paramObj1 = translateInput(reporterJiraUserId);

		if (reporterJiraUserId == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getLastreporterJIRAIssue",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.wol.model.JIRAIssue)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.wol.model.JIRAIssue> getReporterJIRAIssues(
		long projectId, java.lang.String reporterJiraUserId, int start, int end)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(projectId);

		Object paramObj1 = translateInput(reporterJiraUserId);

		if (reporterJiraUserId == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = new IntegerWrapper(start);

		Object paramObj3 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getReporterJIRAIssues",
					new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.wol.model.JIRAIssue>)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.wol.model.JIRAIssue> getReporterJIRAIssues(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId, int start, int end)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(modifiedDate);

		if (modifiedDate == null) {
			paramObj0 = new NullWrapper("java.util.Date");
		}

		Object paramObj1 = new LongWrapper(projectId);

		Object paramObj2 = translateInput(reporterJiraUserId);

		if (reporterJiraUserId == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = new IntegerWrapper(start);

		Object paramObj4 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getReporterJIRAIssues",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.wol.model.JIRAIssue>)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.wol.model.JIRAIssue> getReporterJIRAIssues(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status, int start, int end)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(projectId);

		Object paramObj1 = translateInput(reporterJiraUserId);

		if (reporterJiraUserId == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = translateInput(status);

		if (status == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = new IntegerWrapper(start);

		Object paramObj4 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getReporterJIRAIssues",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.wol.model.JIRAIssue>)translateOutput(returnObj);
	}

	public int getReporterJIRAIssuesCount(long projectId,
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(projectId);

		Object paramObj1 = translateInput(reporterJiraUserId);

		if (reporterJiraUserId == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getReporterJIRAIssuesCount",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
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
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(modifiedDate);

		if (modifiedDate == null) {
			paramObj0 = new NullWrapper("java.util.Date");
		}

		Object paramObj1 = new LongWrapper(projectId);

		Object paramObj2 = translateInput(reporterJiraUserId);

		if (reporterJiraUserId == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getReporterJIRAIssuesCount",
					new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
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
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(projectId);

		Object paramObj1 = translateInput(reporterJiraUserId);

		if (reporterJiraUserId == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = translateInput(status);

		if (status == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getReporterJIRAIssuesCount",
					new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
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
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(projectId);

		try {
			_classLoaderProxy.invoke("updateJIRAIssues",
				new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
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

	protected Object translateInput(BaseModel oldModel) {
		Class oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(JIRAIssueClp.class.getName())) {
			JIRAIssueClp oldCplModel = (JIRAIssueClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class newModelClass = Class.forName("com.liferay.wol.model.impl.JIRAIssueImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setJiraIssueId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getJiraIssueId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setCreateDate",
							new Class[] { Date.class });

					Date value1 = oldCplModel.getCreateDate();

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setModifiedDate",
							new Class[] { Date.class });

					Date value2 = oldCplModel.getModifiedDate();

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setProjectId",
							new Class[] { Long.TYPE });

					Long value3 = new Long(oldCplModel.getProjectId());

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setKey",
							new Class[] { String.class });

					String value4 = oldCplModel.getKey();

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setSummary",
							new Class[] { String.class });

					String value5 = oldCplModel.getSummary();

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setDescription",
							new Class[] { String.class });

					String value6 = oldCplModel.getDescription();

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setReporterJiraUserId",
							new Class[] { String.class });

					String value7 = oldCplModel.getReporterJiraUserId();

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setAssigneeJiraUserId",
							new Class[] { String.class });

					String value8 = oldCplModel.getAssigneeJiraUserId();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setResolution",
							new Class[] { String.class });

					String value9 = oldCplModel.getResolution();

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setStatus",
							new Class[] { String.class });

					String value10 = oldCplModel.getStatus();

					method10.invoke(newModel, value10);

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		return oldModel;
	}

	protected Object translateInput(List oldList) {
		List newList = new ArrayList(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateInput(curObj));
		}

		return newList;
	}

	protected Object translateInput(Object obj) {
		if (obj instanceof BaseModel) {
			return translateInput((BaseModel)obj);
		}
		else if (obj instanceof List) {
			return translateInput((List)obj);
		}
		else {
			return obj;
		}
	}

	protected Object translateOutput(BaseModel oldModel) {
		Class oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals("com.liferay.wol.model.impl.JIRAIssueImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					JIRAIssueClp newModel = new JIRAIssueClp();

					Method method0 = oldModelClass.getMethod("getJiraIssueId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setJiraIssueId(value0.longValue());

					Method method1 = oldModelClass.getMethod("getCreateDate");

					Date value1 = (Date)method1.invoke(oldModel, (Object[])null);

					newModel.setCreateDate(value1);

					Method method2 = oldModelClass.getMethod("getModifiedDate");

					Date value2 = (Date)method2.invoke(oldModel, (Object[])null);

					newModel.setModifiedDate(value2);

					Method method3 = oldModelClass.getMethod("getProjectId");

					Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

					newModel.setProjectId(value3.longValue());

					Method method4 = oldModelClass.getMethod("getKey");

					String value4 = (String)method4.invoke(oldModel,
							(Object[])null);

					newModel.setKey(value4);

					Method method5 = oldModelClass.getMethod("getSummary");

					String value5 = (String)method5.invoke(oldModel,
							(Object[])null);

					newModel.setSummary(value5);

					Method method6 = oldModelClass.getMethod("getDescription");

					String value6 = (String)method6.invoke(oldModel,
							(Object[])null);

					newModel.setDescription(value6);

					Method method7 = oldModelClass.getMethod(
							"getReporterJiraUserId");

					String value7 = (String)method7.invoke(oldModel,
							(Object[])null);

					newModel.setReporterJiraUserId(value7);

					Method method8 = oldModelClass.getMethod(
							"getAssigneeJiraUserId");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setAssigneeJiraUserId(value8);

					Method method9 = oldModelClass.getMethod("getResolution");

					String value9 = (String)method9.invoke(oldModel,
							(Object[])null);

					newModel.setResolution(value9);

					Method method10 = oldModelClass.getMethod("getStatus");

					String value10 = (String)method10.invoke(oldModel,
							(Object[])null);

					newModel.setStatus(value10);

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		return oldModel;
	}

	protected Object translateOutput(List oldList) {
		List newList = new ArrayList(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateOutput(curObj));
		}

		return newList;
	}

	protected Object translateOutput(Object obj) {
		if (obj instanceof BaseModel) {
			return translateOutput((BaseModel)obj);
		}
		else if (obj instanceof List) {
			return translateOutput((List)obj);
		}
		else {
			return obj;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(JIRAIssueLocalServiceClp.class);
	private ClassLoaderProxy _classLoaderProxy;
	private ClassLoader _classLoader;
}