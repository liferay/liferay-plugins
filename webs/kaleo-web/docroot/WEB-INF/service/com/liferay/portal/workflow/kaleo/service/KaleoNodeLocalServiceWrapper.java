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
 * <a href="KaleoNodeLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link KaleoNodeLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoNodeLocalService
 * @generated
 */
public class KaleoNodeLocalServiceWrapper implements KaleoNodeLocalService {
	public KaleoNodeLocalServiceWrapper(
		KaleoNodeLocalService kaleoNodeLocalService) {
		_kaleoNodeLocalService = kaleoNodeLocalService;
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNode addKaleoNode(
		com.liferay.portal.workflow.kaleo.model.KaleoNode kaleoNode)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNodeLocalService.addKaleoNode(kaleoNode);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNode createKaleoNode(
		long kaleoNodeId) {
		return _kaleoNodeLocalService.createKaleoNode(kaleoNodeId);
	}

	public void deleteKaleoNode(long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoNodeLocalService.deleteKaleoNode(kaleoNodeId);
	}

	public void deleteKaleoNode(
		com.liferay.portal.workflow.kaleo.model.KaleoNode kaleoNode)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoNodeLocalService.deleteKaleoNode(kaleoNode);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNodeLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNodeLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNodeLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNodeLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNode getKaleoNode(
		long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNodeLocalService.getKaleoNode(kaleoNodeId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> getKaleoNodes(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNodeLocalService.getKaleoNodes(start, end);
	}

	public int getKaleoNodesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNodeLocalService.getKaleoNodesCount();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNode updateKaleoNode(
		com.liferay.portal.workflow.kaleo.model.KaleoNode kaleoNode)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNodeLocalService.updateKaleoNode(kaleoNode);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNode updateKaleoNode(
		com.liferay.portal.workflow.kaleo.model.KaleoNode kaleoNode,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNodeLocalService.updateKaleoNode(kaleoNode, merge);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNode addKaleoNode(
		long kaleoDefinitionId,
		com.liferay.portal.workflow.kaleo.definition.Node node,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNodeLocalService.addKaleoNode(kaleoDefinitionId, node,
			serviceContext);
	}

	public void deleteKaleoNodes(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoNodeLocalService.deleteKaleoNodes(kaleoDefinitionId);
	}

	public void deleteKaleoNodesByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoNodeLocalService.deleteKaleoNodesByCompanyId(companyId);
	}

	public KaleoNodeLocalService getWrappedKaleoNodeLocalService() {
		return _kaleoNodeLocalService;
	}

	private KaleoNodeLocalService _kaleoNodeLocalService;
}