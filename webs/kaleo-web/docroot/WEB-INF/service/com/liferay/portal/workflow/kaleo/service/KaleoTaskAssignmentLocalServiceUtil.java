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

package com.liferay.portal.workflow.kaleo.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <p>
 * This class provides static methods for the
 * {@link KaleoTaskAssignmentLocalService} bean. The static methods of
 * this class calls the same methods of the bean instance. It's convenient to be
 * able to just write one line to call a method on a bean instead of writing a
 * lookup call and a method call.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskAssignmentLocalService
 * @generated
 */
public class KaleoTaskAssignmentLocalServiceUtil {
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment addKaleoTaskAssignment(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addKaleoTaskAssignment(kaleoTaskAssignment);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment createKaleoTaskAssignment(
		long kaleoTaskAssignmentId) {
		return getService().createKaleoTaskAssignment(kaleoTaskAssignmentId);
	}

	public static void deleteKaleoTaskAssignment(long kaleoTaskAssignmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKaleoTaskAssignment(kaleoTaskAssignmentId);
	}

	public static void deleteKaleoTaskAssignment(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKaleoTaskAssignment(kaleoTaskAssignment);
	}

	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment getKaleoTaskAssignment(
		long kaleoTaskAssignmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoTaskAssignment(kaleoTaskAssignmentId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> getKaleoTaskAssignments(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoTaskAssignments(start, end);
	}

	public static int getKaleoTaskAssignmentsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoTaskAssignmentsCount();
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment updateKaleoTaskAssignment(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateKaleoTaskAssignment(kaleoTaskAssignment);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment updateKaleoTaskAssignment(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateKaleoTaskAssignment(kaleoTaskAssignment, merge);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment addKaleoTaskAssignment(
		long kaleoDefinitionId, long kaleoNodeId, long kaleoTaskId,
		com.liferay.portal.workflow.kaleo.definition.Assignment assignment,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addKaleoTaskAssignment(kaleoDefinitionId, kaleoNodeId,
			kaleoTaskId, assignment, serviceContext);
	}

	public static void deleteCompanyKaleoTaskAssignments(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCompanyKaleoTaskAssignments(companyId);
	}

	public static void deleteKaleoDefinitionKaleoTaskAssignments(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKaleoDefinitionKaleoTaskAssignments(kaleoDefinitionId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> getKaleoTaskAssignments(
		long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoTaskAssignments(kaleoTaskId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> getKaleoTaskAssignments(
		java.lang.String assigneeClassName, long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoTaskAssignments(assigneeClassName, kaleoTaskId);
	}

	public static int getKaleoTaskAssignmentsCount(long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoTaskAssignmentsCount(kaleoTaskId);
	}

	public static int getKaleoTaskAssignmentsCount(
		java.lang.String assigneeClassName, long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoTaskAssignmentsCount(assigneeClassName, kaleoTaskId);
	}

	public static void clearService() {
		_service = null;
	}

	public static KaleoTaskAssignmentLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					KaleoTaskAssignmentLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					KaleoTaskAssignmentLocalService.class.getName(),
					portletClassLoader);

			_service = new KaleoTaskAssignmentLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(KaleoTaskAssignmentLocalService service) {
		_service = service;
	}

	private static KaleoTaskAssignmentLocalService _service;
}