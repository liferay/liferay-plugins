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
 * <a href="KaleoTaskInstanceAssignmentLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * {@link KaleoTaskInstanceAssignmentLocalService} bean. The static methods of
 * this class calls the same methods of the bean instance. It's convenient to be
 * able to just write one line to call a method on a bean instead of writing a
 * lookup call and a method call.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskInstanceAssignmentLocalService
 * @generated
 */
public class KaleoTaskInstanceAssignmentLocalServiceUtil {
	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment addKaleoTaskInstanceAssignment(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addKaleoTaskInstanceAssignment(kaleoTaskInstanceAssignment);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment createKaleoTaskInstanceAssignment(
		long kaleoTaskInstanceAssignmentId) {
		return getService()
				   .createKaleoTaskInstanceAssignment(kaleoTaskInstanceAssignmentId);
	}

	public static void deleteKaleoTaskInstanceAssignment(
		long kaleoTaskInstanceAssignmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteKaleoTaskInstanceAssignment(kaleoTaskInstanceAssignmentId);
	}

	public static void deleteKaleoTaskInstanceAssignment(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteKaleoTaskInstanceAssignment(kaleoTaskInstanceAssignment);
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

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public static int dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment getKaleoTaskInstanceAssignment(
		long kaleoTaskInstanceAssignmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoTaskInstanceAssignment(kaleoTaskInstanceAssignmentId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment> getKaleoTaskInstanceAssignments(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoTaskInstanceAssignments(start, end);
	}

	public static int getKaleoTaskInstanceAssignmentsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoTaskInstanceAssignmentsCount();
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment updateKaleoTaskInstanceAssignment(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateKaleoTaskInstanceAssignment(kaleoTaskInstanceAssignment);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment updateKaleoTaskInstanceAssignment(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateKaleoTaskInstanceAssignment(kaleoTaskInstanceAssignment,
			merge);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment addKaleoTaskInstanceAssignment(
		long kaleoTaskInstanceTokenId, long kaleoTaskAssignmentId,
		java.util.Map<String, java.io.Serializable> context)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addKaleoTaskInstanceAssignment(kaleoTaskInstanceTokenId,
			kaleoTaskAssignmentId, context);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment assignKaleoTaskInstanceAssignment(
		long kaleoTaskInstanceAssignmentId, java.lang.String assigneeClassName,
		long assigneeClassPK,
		java.util.Map<String, java.io.Serializable> context,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .assignKaleoTaskInstanceAssignment(kaleoTaskInstanceAssignmentId,
			assigneeClassName, assigneeClassPK, context, serviceContext);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment completeKaleoTaskInstanceAssignment(
		long kaleoTaskInstanceAssignmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .completeKaleoTaskInstanceAssignment(kaleoTaskInstanceAssignmentId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment getKaleoTaskInstanceAssignment(
		long kaleoTaskInstanceTokenId, java.lang.String assigneeClassName,
		long assigneeClassPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoTaskInstanceAssignment(kaleoTaskInstanceTokenId,
			assigneeClassName, assigneeClassPK);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment getKaleoTaskInstanceAssignmentByKaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoTaskInstanceAssignmentByKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
	}

	public static void clearService() {
		_service = null;
	}

	public static KaleoTaskInstanceAssignmentLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					KaleoTaskInstanceAssignmentLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					KaleoTaskInstanceAssignmentLocalService.class.getName(),
					portletClassLoader);

			_service = new KaleoTaskInstanceAssignmentLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(KaleoTaskInstanceAssignmentLocalService service) {
		_service = service;
	}

	private static KaleoTaskInstanceAssignmentLocalService _service;
}