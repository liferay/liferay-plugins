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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="JIRAIssueLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JIRAIssueLocalServiceUtil {
	public static com.liferay.wol.model.JIRAIssue addJIRAIssue(
		com.liferay.wol.model.JIRAIssue jiraIssue)
		throws com.liferay.portal.SystemException {
		return getService().addJIRAIssue(jiraIssue);
	}

	public static com.liferay.wol.model.JIRAIssue createJIRAIssue(
		long jiraIssueId) {
		return getService().createJIRAIssue(jiraIssueId);
	}

	public static void deleteJIRAIssue(long jiraIssueId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteJIRAIssue(jiraIssueId);
	}

	public static void deleteJIRAIssue(
		com.liferay.wol.model.JIRAIssue jiraIssue)
		throws com.liferay.portal.SystemException {
		getService().deleteJIRAIssue(jiraIssue);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	public static com.liferay.wol.model.JIRAIssue getJIRAIssue(long jiraIssueId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getJIRAIssue(jiraIssueId);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> getJIRAIssues(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getJIRAIssues(start, end);
	}

	public static int getJIRAIssuesCount()
		throws com.liferay.portal.SystemException {
		return getService().getJIRAIssuesCount();
	}

	public static com.liferay.wol.model.JIRAIssue updateJIRAIssue(
		com.liferay.wol.model.JIRAIssue jiraIssue)
		throws com.liferay.portal.SystemException {
		return getService().updateJIRAIssue(jiraIssue);
	}

	public static com.liferay.wol.model.JIRAIssue updateJIRAIssue(
		com.liferay.wol.model.JIRAIssue jiraIssue, boolean merge)
		throws com.liferay.portal.SystemException {
		return getService().updateJIRAIssue(jiraIssue, merge);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> getAssigneeJIRAIssues(
		long projectId, java.lang.String assigneeJiraUserId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService()
				   .getAssigneeJIRAIssues(projectId, assigneeJiraUserId, start,
			end);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> getAssigneeJIRAIssues(
		java.util.Date modifiedDate, long projectId,
		java.lang.String assigneeJiraUserId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService()
				   .getAssigneeJIRAIssues(modifiedDate, projectId,
			assigneeJiraUserId, start, end);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> getAssigneeJIRAIssues(
		long projectId, java.lang.String assigneeJiraUserId,
		java.lang.String status, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService()
				   .getAssigneeJIRAIssues(projectId, assigneeJiraUserId,
			status, start, end);
	}

	public static int getAssigneeJIRAIssuesCount(long projectId,
		java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.SystemException {
		return getService()
				   .getAssigneeJIRAIssuesCount(projectId, assigneeJiraUserId);
	}

	public static int getAssigneeJIRAIssuesCount(java.util.Date modifiedDate,
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.SystemException {
		return getService()
				   .getAssigneeJIRAIssuesCount(modifiedDate, projectId,
			assigneeJiraUserId);
	}

	public static int getAssigneeJIRAIssuesCount(long projectId,
		java.lang.String assigneeJiraUserId, java.lang.String status)
		throws com.liferay.portal.SystemException {
		return getService()
				   .getAssigneeJIRAIssuesCount(projectId, assigneeJiraUserId,
			status);
	}

	public static com.liferay.wol.model.JIRAIssue getFirstAssigneeJIRAIssue(
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .getFirstAssigneeJIRAIssue(projectId, assigneeJiraUserId);
	}

	public static com.liferay.wol.model.JIRAIssue getFirstReporterJIRAIssue(
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .getFirstReporterJIRAIssue(projectId, reporterJiraUserId);
	}

	public static com.liferay.wol.model.JIRAIssue getJIRAIssue(
		java.lang.String key)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getJIRAIssue(key);
	}

	public static com.liferay.wol.model.JIRAIssue getLastAssigneeJIRAIssue(
		long projectId, java.lang.String assigneeJiraUserId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .getLastAssigneeJIRAIssue(projectId, assigneeJiraUserId);
	}

	public static com.liferay.wol.model.JIRAIssue getLastreporterJIRAIssue(
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .getLastreporterJIRAIssue(projectId, reporterJiraUserId);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> getReporterJIRAIssues(
		long projectId, java.lang.String reporterJiraUserId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService()
				   .getReporterJIRAIssues(projectId, reporterJiraUserId, start,
			end);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> getReporterJIRAIssues(
		java.util.Date modifiedDate, long projectId,
		java.lang.String reporterJiraUserId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService()
				   .getReporterJIRAIssues(modifiedDate, projectId,
			reporterJiraUserId, start, end);
	}

	public static java.util.List<com.liferay.wol.model.JIRAIssue> getReporterJIRAIssues(
		long projectId, java.lang.String reporterJiraUserId,
		java.lang.String status, int start, int end)
		throws com.liferay.portal.SystemException {
		return getService()
				   .getReporterJIRAIssues(projectId, reporterJiraUserId,
			status, start, end);
	}

	public static int getReporterJIRAIssuesCount(long projectId,
		java.lang.String reporterJiraUserId)
		throws com.liferay.portal.SystemException {
		return getService()
				   .getReporterJIRAIssuesCount(projectId, reporterJiraUserId);
	}

	public static int getReporterJIRAIssuesCount(java.util.Date modifiedDate,
		long projectId, java.lang.String reporterJiraUserId)
		throws com.liferay.portal.SystemException {
		return getService()
				   .getReporterJIRAIssuesCount(modifiedDate, projectId,
			reporterJiraUserId);
	}

	public static int getReporterJIRAIssuesCount(long projectId,
		java.lang.String reporterJiraUserId, java.lang.String status)
		throws com.liferay.portal.SystemException {
		return getService()
				   .getReporterJIRAIssuesCount(projectId, reporterJiraUserId,
			status);
	}

	public static void updateJIRAIssues(long projectId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().updateJIRAIssues(projectId);
	}

	public static JIRAIssueLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate("wol-portlet",
					JIRAIssueLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate("wol-portlet",
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