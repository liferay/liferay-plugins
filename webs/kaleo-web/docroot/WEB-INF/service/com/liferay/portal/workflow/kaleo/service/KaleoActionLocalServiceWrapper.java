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
 * <a href="KaleoActionLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link KaleoActionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoActionLocalService
 * @generated
 */
public class KaleoActionLocalServiceWrapper implements KaleoActionLocalService {
	public KaleoActionLocalServiceWrapper(
		KaleoActionLocalService kaleoActionLocalService) {
		_kaleoActionLocalService = kaleoActionLocalService;
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoAction addKaleoAction(
		com.liferay.portal.workflow.kaleo.model.KaleoAction kaleoAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.addKaleoAction(kaleoAction);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoAction createKaleoAction(
		long kaleoActionId) {
		return _kaleoActionLocalService.createKaleoAction(kaleoActionId);
	}

	public void deleteKaleoAction(long kaleoActionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoActionLocalService.deleteKaleoAction(kaleoActionId);
	}

	public void deleteKaleoAction(
		com.liferay.portal.workflow.kaleo.model.KaleoAction kaleoAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoActionLocalService.deleteKaleoAction(kaleoAction);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoAction getKaleoAction(
		long kaleoActionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.getKaleoAction(kaleoActionId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoAction> getKaleoActions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.getKaleoActions(start, end);
	}

	public int getKaleoActionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.getKaleoActionsCount();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoAction updateKaleoAction(
		com.liferay.portal.workflow.kaleo.model.KaleoAction kaleoAction)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.updateKaleoAction(kaleoAction);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoAction updateKaleoAction(
		com.liferay.portal.workflow.kaleo.model.KaleoAction kaleoAction,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.updateKaleoAction(kaleoAction, merge);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoAction addKaleoAction(
		long kaleoDefinitionId, long kaleoNodeId,
		java.lang.String kaleoNodeName,
		com.liferay.portal.workflow.kaleo.definition.Action action,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.addKaleoAction(kaleoDefinitionId,
			kaleoNodeId, kaleoNodeName, action, serviceContext);
	}

	public void deleteKaleoActions(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoActionLocalService.deleteKaleoActions(kaleoDefinitionId);
	}

	public void deleteKaleoActionsByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoActionLocalService.deleteKaleoActionsByCompanyId(companyId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoAction> getKaleoActions(
		long kaleoNodeId, java.lang.String executionType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoActionLocalService.getKaleoActions(kaleoNodeId,
			executionType);
	}

	public KaleoActionLocalService getWrappedKaleoActionLocalService() {
		return _kaleoActionLocalService;
	}

	private KaleoActionLocalService _kaleoActionLocalService;
}