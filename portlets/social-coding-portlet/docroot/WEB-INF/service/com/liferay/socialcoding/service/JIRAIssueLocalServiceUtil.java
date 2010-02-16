/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.socialcoding.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="JIRAIssueLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class JIRAIssueLocalServiceUtil {
	public static com.liferay.socialcoding.model.JIRAIssue addJIRAIssue(
		com.liferay.socialcoding.model.JIRAIssue jiraIssue)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addJIRAIssue(jiraIssue);
	}

	public static com.liferay.socialcoding.model.JIRAIssue createJIRAIssue(
		long jiraIssueId) {
		return getService().createJIRAIssue(jiraIssueId);
	}

	public static void deleteJIRAIssue(long jiraIssueId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteJIRAIssue(jiraIssueId);
	}

	public static void deleteJIRAIssue(
		com.liferay.socialcoding.model.JIRAIssue jiraIssue)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteJIRAIssue(jiraIssue);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	public static com.liferay.socialcoding.model.JIRAIssue getJIRAIssue(
		long jiraIssueId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getJIRAIssue(jiraIssueId);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> getJIRAIssues(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getJIRAIssues(start, end);
	}

	public static int getJIRAIssuesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getJIRAIssuesCount();
	}

	public static com.liferay.socialcoding.model.JIRAIssue updateJIRAIssue(
		com.liferay.socialcoding.model.JIRAIssue jiraIssue)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateJIRAIssue(jiraIssue);
	}

	public static com.liferay.socialcoding.model.JIRAIssue updateJIRAIssue(
		com.liferay.socialcoding.model.JIRAIssue jiraIssue, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateJIRAIssue(jiraIssue, merge);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> getAssigneeJIRAIssues(
		long projectId, java.lang.String assigneeJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssigneeJIRAIssues(projectId, assigneeJiraUserId, start,
			end);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> getAssigneeJIRAIssues(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssigneeJIRAIssues(modifiedDate, projectId,
			assigneeJiraUserId, start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> getAssigneeJIRAIssues(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssigneeJIRAIssues(projectId, assigneeJiraUserId,
			status, start, end);
	}

	public static int getAssigneeJIRAIssuesCount(long projectId,
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssigneeJIRAIssuesCount(projectId, assigneeJiraUserId);
	}

	public static int getAssigneeJIRAIssuesCount(java.util.Date modifiedDate,
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssigneeJIRAIssuesCount(modifiedDate, projectId,
			assigneeJiraUserId);
	}

	public static int getAssigneeJIRAIssuesCount(long projectId,
		java.lang.String assigneeJiraUserId, java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssigneeJIRAIssuesCount(projectId, assigneeJiraUserId,
			status);
	}

	public static com.liferay.socialcoding.model.JIRAIssue getFirstAssigneeJIRAIssue(
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getFirstAssigneeJIRAIssue(projectId, assigneeJiraUserId);
	}

	public static com.liferay.socialcoding.model.JIRAIssue getFirstReporterJIRAIssue(
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getFirstReporterJIRAIssue(projectId, reporterJiraUserId);
	}

	public static com.liferay.socialcoding.model.JIRAIssue getJIRAIssue(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getJIRAIssue(key);
	}

	public static com.liferay.socialcoding.model.JIRAIssue getLastAssigneeJIRAIssue(
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getLastAssigneeJIRAIssue(projectId, assigneeJiraUserId);
	}

	public static com.liferay.socialcoding.model.JIRAIssue getLastreporterJIRAIssue(
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getLastreporterJIRAIssue(projectId, reporterJiraUserId);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> getReporterJIRAIssues(
		long projectId, java.lang.String reporterJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getReporterJIRAIssues(projectId, reporterJiraUserId, start,
			end);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> getReporterJIRAIssues(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getReporterJIRAIssues(modifiedDate, projectId,
			reporterJiraUserId, start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> getReporterJIRAIssues(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getReporterJIRAIssues(projectId, reporterJiraUserId,
			status, start, end);
	}

	public static int getReporterJIRAIssuesCount(long projectId,
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getReporterJIRAIssuesCount(projectId, reporterJiraUserId);
	}

	public static int getReporterJIRAIssuesCount(java.util.Date modifiedDate,
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getReporterJIRAIssuesCount(modifiedDate, projectId,
			reporterJiraUserId);
	}

	public static int getReporterJIRAIssuesCount(long projectId,
		java.lang.String reporterJiraUserId, java.lang.String status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getReporterJIRAIssuesCount(projectId, reporterJiraUserId,
			status);
	}

	public static void updateJIRAIssues(long projectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().updateJIRAIssues(projectId);
	}

	public static void clearService() {
		_service = null;
	}

	public static JIRAIssueLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					JIRAIssueLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new JIRAIssueLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(JIRAIssueLocalService service) {
		_service = service;
	}

	private static JIRAIssueLocalService _service;
}