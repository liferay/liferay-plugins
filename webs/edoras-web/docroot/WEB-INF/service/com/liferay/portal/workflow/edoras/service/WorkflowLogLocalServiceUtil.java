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

package com.liferay.portal.workflow.edoras.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="WorkflowLogLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowLogLocalServiceUtil {
	public static com.liferay.portal.workflow.edoras.model.WorkflowLog addWorkflowLog(
		com.liferay.portal.workflow.edoras.model.WorkflowLog workflowLog)
		throws com.liferay.portal.SystemException {
		return getService().addWorkflowLog(workflowLog);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowLog createWorkflowLog(
		long workflowLogId) {
		return getService().createWorkflowLog(workflowLogId);
	}

	public static void deleteWorkflowLog(long workflowLogId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteWorkflowLog(workflowLogId);
	}

	public static void deleteWorkflowLog(
		com.liferay.portal.workflow.edoras.model.WorkflowLog workflowLog)
		throws com.liferay.portal.SystemException {
		getService().deleteWorkflowLog(workflowLog);
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

	public static com.liferay.portal.workflow.edoras.model.WorkflowLog getWorkflowLog(
		long workflowLogId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getWorkflowLog(workflowLogId);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> getWorkflowLogs(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getWorkflowLogs(start, end);
	}

	public static int getWorkflowLogsCount()
		throws com.liferay.portal.SystemException {
		return getService().getWorkflowLogsCount();
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowLog updateWorkflowLog(
		com.liferay.portal.workflow.edoras.model.WorkflowLog workflowLog)
		throws com.liferay.portal.SystemException {
		return getService().updateWorkflowLog(workflowLog);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowLog updateWorkflowLog(
		com.liferay.portal.workflow.edoras.model.WorkflowLog workflowLog,
		boolean merge) throws com.liferay.portal.SystemException {
		return getService().updateWorkflowLog(workflowLog, merge);
	}

	public static void clearService() {
		_service = null;
	}

	public static WorkflowLogLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					WorkflowLogLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new WorkflowLogLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(WorkflowLogLocalService service) {
		_service = service;
	}

	private static WorkflowLogLocalService _service;
}