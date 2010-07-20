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
 * This class is a wrapper for {@link KaleoTransitionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTransitionLocalService
 * @generated
 */
public class KaleoTransitionLocalServiceWrapper
	implements KaleoTransitionLocalService {
	public KaleoTransitionLocalServiceWrapper(
		KaleoTransitionLocalService kaleoTransitionLocalService) {
		_kaleoTransitionLocalService = kaleoTransitionLocalService;
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTransition addKaleoTransition(
		com.liferay.portal.workflow.kaleo.model.KaleoTransition kaleoTransition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTransitionLocalService.addKaleoTransition(kaleoTransition);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTransition createKaleoTransition(
		long kaleoTransitionId) {
		return _kaleoTransitionLocalService.createKaleoTransition(kaleoTransitionId);
	}

	public void deleteKaleoTransition(long kaleoTransitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoTransitionLocalService.deleteKaleoTransition(kaleoTransitionId);
	}

	public void deleteKaleoTransition(
		com.liferay.portal.workflow.kaleo.model.KaleoTransition kaleoTransition)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTransitionLocalService.deleteKaleoTransition(kaleoTransition);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTransitionLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTransitionLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTransitionLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTransitionLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTransition getKaleoTransition(
		long kaleoTransitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTransitionLocalService.getKaleoTransition(kaleoTransitionId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> getKaleoTransitions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTransitionLocalService.getKaleoTransitions(start, end);
	}

	public int getKaleoTransitionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTransitionLocalService.getKaleoTransitionsCount();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTransition updateKaleoTransition(
		com.liferay.portal.workflow.kaleo.model.KaleoTransition kaleoTransition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTransitionLocalService.updateKaleoTransition(kaleoTransition);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTransition updateKaleoTransition(
		com.liferay.portal.workflow.kaleo.model.KaleoTransition kaleoTransition,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTransitionLocalService.updateKaleoTransition(kaleoTransition,
			merge);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTransition addKaleoTransition(
		long kaleoDefinitionId, long kaleoNodeId,
		com.liferay.portal.workflow.kaleo.definition.Transition transition,
		com.liferay.portal.workflow.kaleo.model.KaleoNode sourceKaleoNode,
		com.liferay.portal.workflow.kaleo.model.KaleoNode targetKaleoNode,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTransitionLocalService.addKaleoTransition(kaleoDefinitionId,
			kaleoNodeId, transition, sourceKaleoNode, targetKaleoNode,
			serviceContext);
	}

	public void deleteCompanyKaleoTransitions(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTransitionLocalService.deleteCompanyKaleoTransitions(companyId);
	}

	public void deleteKaleoDefinitionKaleoTransitions(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTransitionLocalService.deleteKaleoDefinitionKaleoTransitions(kaleoDefinitionId);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTransition getDefaultKaleoTransition(
		long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTransitionLocalService.getDefaultKaleoTransition(kaleoNodeId);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTransition getKaleoTransition(
		long kaleoNodeId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTransitionLocalService.getKaleoTransition(kaleoNodeId, name);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> getKaleoTransitions(
		long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTransitionLocalService.getKaleoTransitions(kaleoNodeId);
	}

	public int getKaleoTransitionsCount(long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTransitionLocalService.getKaleoTransitionsCount(kaleoNodeId);
	}

	public KaleoTransitionLocalService getWrappedKaleoTransitionLocalService() {
		return _kaleoTransitionLocalService;
	}

	private KaleoTransitionLocalService _kaleoTransitionLocalService;
}