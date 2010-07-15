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
 * {@link KaleoTaskAssignmentInstanceLocalService} bean. The static methods of
 * this class calls the same methods of the bean instance. It's convenient to be
 * able to just write one line to call a method on a bean instead of writing a
 * lookup call and a method call.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskAssignmentInstanceLocalService
 * @generated
 */
public class KaleoTaskAssignmentInstanceLocalServiceUtil {
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance addKaleoTaskAssignmentInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addKaleoTaskAssignmentInstance(kaleoTaskAssignmentInstance);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance createKaleoTaskAssignmentInstance(
		long kaleoTaskAssignmentInstanceId) {
		return getService()
				   .createKaleoTaskAssignmentInstance(kaleoTaskAssignmentInstanceId);
	}

	public static void deleteKaleoTaskAssignmentInstance(
		long kaleoTaskAssignmentInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteKaleoTaskAssignmentInstance(kaleoTaskAssignmentInstanceId);
	}

	public static void deleteKaleoTaskAssignmentInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteKaleoTaskAssignmentInstance(kaleoTaskAssignmentInstance);
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

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance getKaleoTaskAssignmentInstance(
		long kaleoTaskAssignmentInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoTaskAssignmentInstance(kaleoTaskAssignmentInstanceId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> getKaleoTaskAssignmentInstances(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoTaskAssignmentInstances(start, end);
	}

	public static int getKaleoTaskAssignmentInstancesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoTaskAssignmentInstancesCount();
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance updateKaleoTaskAssignmentInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateKaleoTaskAssignmentInstance(kaleoTaskAssignmentInstance);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance updateKaleoTaskAssignmentInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateKaleoTaskAssignmentInstance(kaleoTaskAssignmentInstance,
			merge);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance addKaleoTaskAssignmentInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		java.lang.String assigneeClassName, long assigneeClassPK,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addKaleoTaskAssignmentInstance(kaleoTaskInstanceToken,
			assigneeClassName, assigneeClassPK, serviceContext);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> addTaskAssignmentInstances(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		java.util.Collection<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> kaleoTaskAssignments,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addTaskAssignmentInstances(kaleoTaskInstanceToken,
			kaleoTaskAssignments, serviceContext);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance assignKaleoTaskAssignmentInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		java.lang.String assigneeClassName, long assigneeClassPK,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .assignKaleoTaskAssignmentInstance(kaleoTaskInstanceToken,
			assigneeClassName, assigneeClassPK, serviceContext);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance completeKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .completeKaleoTaskInstanceToken(kaleoTaskInstanceTokenId,
			serviceContext);
	}

	public static void deleteCompanyKaleoTaskAssignmentInstances(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCompanyKaleoTaskAssignmentInstances(companyId);
	}

	public static void deleteKaleoDefinitionKaleoTaskAssignmentInstances(
		long kaleoDefintionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteKaleoDefinitionKaleoTaskAssignmentInstances(kaleoDefintionId);
	}

	public static void deleteKaleoInstanceKaleoTaskAssignmentInstances(
		long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteKaleoInstanceKaleoTaskAssignmentInstances(kaleoInstanceId);
	}

	public static void deleteKaleoTaskAssignmentInstances(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKaleoTaskAssignmentInstances(kaleoTaskInstanceToken);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> getKaleoTaskAssignmentInstances(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoTaskAssignmentInstances(kaleoTaskInstanceTokenId);
	}

	public static void clearService() {
		_service = null;
	}

	public static KaleoTaskAssignmentInstanceLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					KaleoTaskAssignmentInstanceLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					KaleoTaskAssignmentInstanceLocalService.class.getName(),
					portletClassLoader);

			_service = new KaleoTaskAssignmentInstanceLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(KaleoTaskAssignmentInstanceLocalService service) {
		_service = service;
	}

	private static KaleoTaskAssignmentInstanceLocalService _service;
}