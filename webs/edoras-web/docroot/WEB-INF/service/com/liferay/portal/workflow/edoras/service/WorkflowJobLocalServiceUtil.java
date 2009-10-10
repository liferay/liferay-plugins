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
 * <a href="WorkflowJobLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowJobLocalServiceUtil {
	public static com.liferay.portal.workflow.edoras.model.WorkflowJob addWorkflowJob(
		com.liferay.portal.workflow.edoras.model.WorkflowJob workflowJob)
		throws com.liferay.portal.SystemException {
		return getService().addWorkflowJob(workflowJob);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowJob createWorkflowJob(
		long workflowJobId) {
		return getService().createWorkflowJob(workflowJobId);
	}

	public static void deleteWorkflowJob(long workflowJobId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteWorkflowJob(workflowJobId);
	}

	public static void deleteWorkflowJob(
		com.liferay.portal.workflow.edoras.model.WorkflowJob workflowJob)
		throws com.liferay.portal.SystemException {
		getService().deleteWorkflowJob(workflowJob);
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

	public static com.liferay.portal.workflow.edoras.model.WorkflowJob getWorkflowJob(
		long workflowJobId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getWorkflowJob(workflowJobId);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> getWorkflowJobs(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getWorkflowJobs(start, end);
	}

	public static int getWorkflowJobsCount()
		throws com.liferay.portal.SystemException {
		return getService().getWorkflowJobsCount();
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowJob updateWorkflowJob(
		com.liferay.portal.workflow.edoras.model.WorkflowJob workflowJob)
		throws com.liferay.portal.SystemException {
		return getService().updateWorkflowJob(workflowJob);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowJob updateWorkflowJob(
		com.liferay.portal.workflow.edoras.model.WorkflowJob workflowJob,
		boolean merge) throws com.liferay.portal.SystemException {
		return getService().updateWorkflowJob(workflowJob, merge);
	}

	public static void clearService() {
		_service = null;
	}

	public static WorkflowJobLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					WorkflowJobLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new WorkflowJobLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(WorkflowJobLocalService service) {
		_service = service;
	}

	private static WorkflowJobLocalService _service;
}