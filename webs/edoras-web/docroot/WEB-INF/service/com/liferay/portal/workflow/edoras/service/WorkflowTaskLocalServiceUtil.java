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
 * <a href="WorkflowTaskLocalServiceUtil.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowTaskLocalServiceUtil {
	public static com.liferay.portal.workflow.edoras.model.WorkflowTask addWorkflowTask(
		com.liferay.portal.workflow.edoras.model.WorkflowTask workflowTask)
		throws com.liferay.portal.SystemException {
		return getService().addWorkflowTask(workflowTask);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask createWorkflowTask(
		long workflowTaskId) {
		return getService().createWorkflowTask(workflowTaskId);
	}

	public static void deleteWorkflowTask(long workflowTaskId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteWorkflowTask(workflowTaskId);
	}

	public static void deleteWorkflowTask(
		com.liferay.portal.workflow.edoras.model.WorkflowTask workflowTask)
		throws com.liferay.portal.SystemException {
		getService().deleteWorkflowTask(workflowTask);
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

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask getWorkflowTask(
		long workflowTaskId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getWorkflowTask(workflowTaskId);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> getWorkflowTasks(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getWorkflowTasks(start, end);
	}

	public static int getWorkflowTasksCount()
		throws com.liferay.portal.SystemException {
		return getService().getWorkflowTasksCount();
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask updateWorkflowTask(
		com.liferay.portal.workflow.edoras.model.WorkflowTask workflowTask)
		throws com.liferay.portal.SystemException {
		return getService().updateWorkflowTask(workflowTask);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask updateWorkflowTask(
		com.liferay.portal.workflow.edoras.model.WorkflowTask workflowTask,
		boolean merge) throws com.liferay.portal.SystemException {
		return getService().updateWorkflowTask(workflowTask, merge);
	}

	public static void clearService() {
		_service = null;
	}

	public static WorkflowTaskLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					WorkflowTaskLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new WorkflowTaskLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(WorkflowTaskLocalService service) {
		_service = service;
	}

	private static WorkflowTaskLocalService _service;
}