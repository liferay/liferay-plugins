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
 * This class is a wrapper for {@link KaleoInstanceTokenLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoInstanceTokenLocalService
 * @generated
 */
public class KaleoInstanceTokenLocalServiceWrapper
	implements KaleoInstanceTokenLocalService {
	public KaleoInstanceTokenLocalServiceWrapper(
		KaleoInstanceTokenLocalService kaleoInstanceTokenLocalService) {
		_kaleoInstanceTokenLocalService = kaleoInstanceTokenLocalService;
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken addKaleoInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.addKaleoInstanceToken(kaleoInstanceToken);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken createKaleoInstanceToken(
		long kaleoInstanceTokenId) {
		return _kaleoInstanceTokenLocalService.createKaleoInstanceToken(kaleoInstanceTokenId);
	}

	public void deleteKaleoInstanceToken(long kaleoInstanceTokenId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoInstanceTokenLocalService.deleteKaleoInstanceToken(kaleoInstanceTokenId);
	}

	public void deleteKaleoInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoInstanceTokenLocalService.deleteKaleoInstanceToken(kaleoInstanceToken);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken getKaleoInstanceToken(
		long kaleoInstanceTokenId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.getKaleoInstanceToken(kaleoInstanceTokenId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> getKaleoInstanceTokens(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.getKaleoInstanceTokens(start, end);
	}

	public int getKaleoInstanceTokensCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.getKaleoInstanceTokensCount();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken updateKaleoInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.updateKaleoInstanceToken(kaleoInstanceToken);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken updateKaleoInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.updateKaleoInstanceToken(kaleoInstanceToken,
			merge);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken addKaleoInstanceToken(
		long parentKaleoInstanceTokenId,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.addKaleoInstanceToken(parentKaleoInstanceTokenId,
			workflowContext, serviceContext);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken completeKaleoInstanceToken(
		long kaleoInstanceTokenId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.completeKaleoInstanceToken(kaleoInstanceTokenId);
	}

	public void deleteCompanyKaleoInstanceTokens(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoInstanceTokenLocalService.deleteCompanyKaleoInstanceTokens(companyId);
	}

	public void deleteKaleoDefinitionKaleoInstanceTokens(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoInstanceTokenLocalService.deleteKaleoDefinitionKaleoInstanceTokens(kaleoDefinitionId);
	}

	public void deleteKaleoInstanceKaleoInstanceTokens(long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoInstanceTokenLocalService.deleteKaleoInstanceKaleoInstanceTokens(kaleoInstanceId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> getKaleoInstanceTokens(
		long parentKaleoInstanceTokenId, java.util.Date completionDate,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.getKaleoInstanceTokens(parentKaleoInstanceTokenId,
			completionDate, serviceContext);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> getKaleoInstanceTokens(
		long parentKaleoInstanceTokenId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.getKaleoInstanceTokens(parentKaleoInstanceTokenId,
			serviceContext);
	}

	public int getKaleoInstanceTokensCount(long parentKaleoInstanceTokenId,
		java.util.Date completionDate,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.getKaleoInstanceTokensCount(parentKaleoInstanceTokenId,
			completionDate, serviceContext);
	}

	public int getKaleoInstanceTokensCount(long parentKaleoInstanceTokenId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.getKaleoInstanceTokensCount(parentKaleoInstanceTokenId,
			serviceContext);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken getRootKaleoInstanceToken(
		long kaleoInstanceId,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.getRootKaleoInstanceToken(kaleoInstanceId,
			workflowContext, serviceContext);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken updateKaleoInstanceToken(
		long kaleoInstanceTokenId, long currentKaleoNodeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceTokenLocalService.updateKaleoInstanceToken(kaleoInstanceTokenId,
			currentKaleoNodeId);
	}

	public KaleoInstanceTokenLocalService getWrappedKaleoInstanceTokenLocalService() {
		return _kaleoInstanceTokenLocalService;
	}

	private KaleoInstanceTokenLocalService _kaleoInstanceTokenLocalService;
}