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
 * <p>
 * This class is a wrapper for {@link KaleoTaskAssignmentLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskAssignmentLocalService
 * @generated
 */
public class KaleoTaskAssignmentLocalServiceWrapper
	implements KaleoTaskAssignmentLocalService {
	public KaleoTaskAssignmentLocalServiceWrapper(
		KaleoTaskAssignmentLocalService kaleoTaskAssignmentLocalService) {
		_kaleoTaskAssignmentLocalService = kaleoTaskAssignmentLocalService;
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment addKaleoTaskAssignment(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentLocalService.addKaleoTaskAssignment(kaleoTaskAssignment);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment createKaleoTaskAssignment(
		long kaleoTaskAssignmentId) {
		return _kaleoTaskAssignmentLocalService.createKaleoTaskAssignment(kaleoTaskAssignmentId);
	}

	public void deleteKaleoTaskAssignment(long kaleoTaskAssignmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskAssignmentLocalService.deleteKaleoTaskAssignment(kaleoTaskAssignmentId);
	}

	public void deleteKaleoTaskAssignment(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskAssignmentLocalService.deleteKaleoTaskAssignment(kaleoTaskAssignment);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment getKaleoTaskAssignment(
		long kaleoTaskAssignmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentLocalService.getKaleoTaskAssignment(kaleoTaskAssignmentId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> getKaleoTaskAssignments(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentLocalService.getKaleoTaskAssignments(start,
			end);
	}

	public int getKaleoTaskAssignmentsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentLocalService.getKaleoTaskAssignmentsCount();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment updateKaleoTaskAssignment(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentLocalService.updateKaleoTaskAssignment(kaleoTaskAssignment);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment updateKaleoTaskAssignment(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentLocalService.updateKaleoTaskAssignment(kaleoTaskAssignment,
			merge);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment addKaleoTaskAssignment(
		long kaleoDefinitionId, long kaleoNodeId, long kaleoTaskId,
		com.liferay.portal.workflow.kaleo.definition.Assignment assignment,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentLocalService.addKaleoTaskAssignment(kaleoDefinitionId,
			kaleoNodeId, kaleoTaskId, assignment, serviceContext);
	}

	public void deleteCompanyKaleoTaskAssignments(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskAssignmentLocalService.deleteCompanyKaleoTaskAssignments(companyId);
	}

	public void deleteKaleoDefinitionKaleoTaskAssignments(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskAssignmentLocalService.deleteKaleoDefinitionKaleoTaskAssignments(kaleoDefinitionId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> getKaleoTaskAssignments(
		long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentLocalService.getKaleoTaskAssignments(kaleoTaskId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> getKaleoTaskAssignments(
		java.lang.String assigneeClassName, long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentLocalService.getKaleoTaskAssignments(assigneeClassName,
			kaleoTaskId);
	}

	public int getKaleoTaskAssignmentsCount(long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentLocalService.getKaleoTaskAssignmentsCount(kaleoTaskId);
	}

	public int getKaleoTaskAssignmentsCount(
		java.lang.String assigneeClassName, long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentLocalService.getKaleoTaskAssignmentsCount(assigneeClassName,
			kaleoTaskId);
	}

	public KaleoTaskAssignmentLocalService getWrappedKaleoTaskAssignmentLocalService() {
		return _kaleoTaskAssignmentLocalService;
	}

	private KaleoTaskAssignmentLocalService _kaleoTaskAssignmentLocalService;
}