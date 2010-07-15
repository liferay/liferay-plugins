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
 * This class is a wrapper for {@link KaleoTaskLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskLocalService
 * @generated
 */
public class KaleoTaskLocalServiceWrapper implements KaleoTaskLocalService {
	public KaleoTaskLocalServiceWrapper(
		KaleoTaskLocalService kaleoTaskLocalService) {
		_kaleoTaskLocalService = kaleoTaskLocalService;
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTask addKaleoTask(
		com.liferay.portal.workflow.kaleo.model.KaleoTask kaleoTask)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskLocalService.addKaleoTask(kaleoTask);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTask createKaleoTask(
		long kaleoTaskId) {
		return _kaleoTaskLocalService.createKaleoTask(kaleoTaskId);
	}

	public void deleteKaleoTask(long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskLocalService.deleteKaleoTask(kaleoTaskId);
	}

	public void deleteKaleoTask(
		com.liferay.portal.workflow.kaleo.model.KaleoTask kaleoTask)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskLocalService.deleteKaleoTask(kaleoTask);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTask getKaleoTask(
		long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskLocalService.getKaleoTask(kaleoTaskId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTask> getKaleoTasks(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskLocalService.getKaleoTasks(start, end);
	}

	public int getKaleoTasksCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskLocalService.getKaleoTasksCount();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTask updateKaleoTask(
		com.liferay.portal.workflow.kaleo.model.KaleoTask kaleoTask)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskLocalService.updateKaleoTask(kaleoTask);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTask updateKaleoTask(
		com.liferay.portal.workflow.kaleo.model.KaleoTask kaleoTask,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskLocalService.updateKaleoTask(kaleoTask, merge);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTask addKaleoTask(
		long kaleoDefinitionId, long kaleoNodeId,
		com.liferay.portal.workflow.kaleo.definition.Task task,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskLocalService.addKaleoTask(kaleoDefinitionId,
			kaleoNodeId, task, serviceContext);
	}

	public void deleteCompanyKaleoTasks(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskLocalService.deleteCompanyKaleoTasks(companyId);
	}

	public void deleteKaleoDefinitionKaleoTasks(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskLocalService.deleteKaleoDefinitionKaleoTasks(kaleoDefinitionId);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTask getKaleoNodeKaleoTask(
		long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskLocalService.getKaleoNodeKaleoTask(kaleoNodeId);
	}

	public KaleoTaskLocalService getWrappedKaleoTaskLocalService() {
		return _kaleoTaskLocalService;
	}

	private KaleoTaskLocalService _kaleoTaskLocalService;
}