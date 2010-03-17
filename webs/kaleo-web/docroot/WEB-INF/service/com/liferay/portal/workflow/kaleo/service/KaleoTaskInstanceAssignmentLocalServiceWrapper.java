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


/**
 * <a href="KaleoTaskInstanceAssignmentLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link KaleoTaskInstanceAssignmentLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskInstanceAssignmentLocalService
 * @generated
 */
public class KaleoTaskInstanceAssignmentLocalServiceWrapper
	implements KaleoTaskInstanceAssignmentLocalService {
	public KaleoTaskInstanceAssignmentLocalServiceWrapper(
		KaleoTaskInstanceAssignmentLocalService kaleoTaskInstanceAssignmentLocalService) {
		_kaleoTaskInstanceAssignmentLocalService = kaleoTaskInstanceAssignmentLocalService;
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment addKaleoTaskInstanceAssignment(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceAssignmentLocalService.addKaleoTaskInstanceAssignment(kaleoTaskInstanceAssignment);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment createKaleoTaskInstanceAssignment(
		long kaleoTaskInstanceAssignmentId) {
		return _kaleoTaskInstanceAssignmentLocalService.createKaleoTaskInstanceAssignment(kaleoTaskInstanceAssignmentId);
	}

	public void deleteKaleoTaskInstanceAssignment(
		long kaleoTaskInstanceAssignmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskInstanceAssignmentLocalService.deleteKaleoTaskInstanceAssignment(kaleoTaskInstanceAssignmentId);
	}

	public void deleteKaleoTaskInstanceAssignment(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskInstanceAssignmentLocalService.deleteKaleoTaskInstanceAssignment(kaleoTaskInstanceAssignment);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceAssignmentLocalService.dynamicQuery(dynamicQuery);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceAssignmentLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceAssignmentLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	public int dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceAssignmentLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment getKaleoTaskInstanceAssignment(
		long kaleoTaskInstanceAssignmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceAssignmentLocalService.getKaleoTaskInstanceAssignment(kaleoTaskInstanceAssignmentId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment> getKaleoTaskInstanceAssignments(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceAssignmentLocalService.getKaleoTaskInstanceAssignments(start,
			end);
	}

	public int getKaleoTaskInstanceAssignmentsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceAssignmentLocalService.getKaleoTaskInstanceAssignmentsCount();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment updateKaleoTaskInstanceAssignment(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceAssignmentLocalService.updateKaleoTaskInstanceAssignment(kaleoTaskInstanceAssignment);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment updateKaleoTaskInstanceAssignment(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceAssignmentLocalService.updateKaleoTaskInstanceAssignment(kaleoTaskInstanceAssignment,
			merge);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment addKaleoTaskInstanceAssignment(
		long kaleoTaskInstanceTokenId, long kaleoTaskAssignmentId,
		java.util.Map<String, java.io.Serializable> context)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceAssignmentLocalService.addKaleoTaskInstanceAssignment(kaleoTaskInstanceTokenId,
			kaleoTaskAssignmentId, context);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment assignKaleoTaskInstanceAssignment(
		long kaleoTaskInstanceAssignmentId, java.lang.String assigneeClassName,
		long assigneeClassPK,
		java.util.Map<String, java.io.Serializable> context,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceAssignmentLocalService.assignKaleoTaskInstanceAssignment(kaleoTaskInstanceAssignmentId,
			assigneeClassName, assigneeClassPK, context, serviceContext);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment completeKaleoTaskInstanceAssignment(
		long kaleoTaskInstanceAssignmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceAssignmentLocalService.completeKaleoTaskInstanceAssignment(kaleoTaskInstanceAssignmentId);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment getKaleoTaskInstanceAssignment(
		long kaleoTaskInstanceTokenId, java.lang.String assigneeClassName,
		long assigneeClassPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceAssignmentLocalService.getKaleoTaskInstanceAssignment(kaleoTaskInstanceTokenId,
			assigneeClassName, assigneeClassPK);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment getKaleoTaskInstanceAssignmentByKaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskInstanceAssignmentLocalService.getKaleoTaskInstanceAssignmentByKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
	}

	public KaleoTaskInstanceAssignmentLocalService getWrappedKaleoTaskInstanceAssignmentLocalService() {
		return _kaleoTaskInstanceAssignmentLocalService;
	}

	private KaleoTaskInstanceAssignmentLocalService _kaleoTaskInstanceAssignmentLocalService;
}