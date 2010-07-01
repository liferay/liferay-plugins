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
 * <a href="KaleoInstanceLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link KaleoInstanceLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoInstanceLocalService
 * @generated
 */
public class KaleoInstanceLocalServiceWrapper
	implements KaleoInstanceLocalService {
	public KaleoInstanceLocalServiceWrapper(
		KaleoInstanceLocalService kaleoInstanceLocalService) {
		_kaleoInstanceLocalService = kaleoInstanceLocalService;
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstance addKaleoInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoInstance kaleoInstance)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceLocalService.addKaleoInstance(kaleoInstance);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstance createKaleoInstance(
		long kaleoInstanceId) {
		return _kaleoInstanceLocalService.createKaleoInstance(kaleoInstanceId);
	}

	public void deleteKaleoInstance(long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoInstanceLocalService.deleteKaleoInstance(kaleoInstanceId);
	}

	public void deleteKaleoInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoInstance kaleoInstance)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoInstanceLocalService.deleteKaleoInstance(kaleoInstance);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstance getKaleoInstance(
		long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceLocalService.getKaleoInstance(kaleoInstanceId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> getKaleoInstances(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceLocalService.getKaleoInstances(start, end);
	}

	public int getKaleoInstancesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceLocalService.getKaleoInstancesCount();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstance updateKaleoInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoInstance kaleoInstance)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceLocalService.updateKaleoInstance(kaleoInstance);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstance updateKaleoInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoInstance kaleoInstance,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceLocalService.updateKaleoInstance(kaleoInstance,
			merge);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstance addKaleoInstance(
		long kaleoDefinitionId, java.lang.String kaleoDefinitionName,
		int kaleoDefinitionVersion,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceLocalService.addKaleoInstance(kaleoDefinitionId,
			kaleoDefinitionName, kaleoDefinitionVersion, workflowContext,
			serviceContext);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstance completeKaleoInstance(
		long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceLocalService.completeKaleoInstance(kaleoInstanceId);
	}

	public void deleteKaleoInstances(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoInstanceLocalService.deleteKaleoInstances(kaleoDefinitionId);
	}

	public void deleteKaleoInstancesByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoInstanceLocalService.deleteKaleoInstancesByCompanyId(companyId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> getKaleoInstances(
		java.lang.Long userId, java.lang.String assetClassName,
		java.lang.Long assetClassPK, java.lang.Boolean completed, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceLocalService.getKaleoInstances(userId,
			assetClassName, assetClassPK, completed, start, end,
			orderByComparator, serviceContext);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> getKaleoInstances(
		java.lang.String kaleoDefinitionName, int kaleoDefinitionVersion,
		boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceLocalService.getKaleoInstances(kaleoDefinitionName,
			kaleoDefinitionVersion, completed, start, end, orderByComparator,
			serviceContext);
	}

	public int getKaleoInstancesCount(long kaleoDefinitionId, boolean completed)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceLocalService.getKaleoInstancesCount(kaleoDefinitionId,
			completed);
	}

	public int getKaleoInstancesCount(java.lang.Long userId,
		java.lang.String assetClassName, java.lang.Long assetClassPK,
		java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceLocalService.getKaleoInstancesCount(userId,
			assetClassName, assetClassPK, completed, serviceContext);
	}

	public int getKaleoInstancesCount(java.lang.String kaleoDefinitionName,
		int kaleoDefinitionVersion, boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceLocalService.getKaleoInstancesCount(kaleoDefinitionName,
			kaleoDefinitionVersion, completed, serviceContext);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstance updateKaleoInstance(
		long kaleoInstanceId,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceLocalService.updateKaleoInstance(kaleoInstanceId,
			workflowContext, serviceContext);
	}

	public KaleoInstanceLocalService getWrappedKaleoInstanceLocalService() {
		return _kaleoInstanceLocalService;
	}

	private KaleoInstanceLocalService _kaleoInstanceLocalService;
}