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
 * <a href="WorkflowDefinitionLocalServiceUtil.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowDefinitionLocalServiceUtil {
	public static com.liferay.portal.workflow.edoras.model.WorkflowDefinition addWorkflowDefinition(
		com.liferay.portal.workflow.edoras.model.WorkflowDefinition workflowDefinition)
		throws com.liferay.portal.SystemException {
		return getService().addWorkflowDefinition(workflowDefinition);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowDefinition createWorkflowDefinition(
		long workflowDefinitionId) {
		return getService().createWorkflowDefinition(workflowDefinitionId);
	}

	public static void deleteWorkflowDefinition(long workflowDefinitionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteWorkflowDefinition(workflowDefinitionId);
	}

	public static void deleteWorkflowDefinition(
		com.liferay.portal.workflow.edoras.model.WorkflowDefinition workflowDefinition)
		throws com.liferay.portal.SystemException {
		getService().deleteWorkflowDefinition(workflowDefinition);
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

	public static com.liferay.portal.workflow.edoras.model.WorkflowDefinition getWorkflowDefinition(
		long workflowDefinitionId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getWorkflowDefinition(workflowDefinitionId);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowDefinition> getWorkflowDefinitions(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getWorkflowDefinitions(start, end);
	}

	public static int getWorkflowDefinitionsCount()
		throws com.liferay.portal.SystemException {
		return getService().getWorkflowDefinitionsCount();
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowDefinition updateWorkflowDefinition(
		com.liferay.portal.workflow.edoras.model.WorkflowDefinition workflowDefinition)
		throws com.liferay.portal.SystemException {
		return getService().updateWorkflowDefinition(workflowDefinition);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowDefinition updateWorkflowDefinition(
		com.liferay.portal.workflow.edoras.model.WorkflowDefinition workflowDefinition,
		boolean merge) throws com.liferay.portal.SystemException {
		return getService().updateWorkflowDefinition(workflowDefinition, merge);
	}

	public static void clearService() {
		_service = null;
	}

	public static WorkflowDefinitionLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					WorkflowDefinitionLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new WorkflowDefinitionLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(WorkflowDefinitionLocalService service) {
		_service = service;
	}

	private static WorkflowDefinitionLocalService _service;
}