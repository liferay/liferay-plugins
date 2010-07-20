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

package com.liferay.chat.service;

/**
 * <p>
 * This class is a wrapper for {@link StatusLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       StatusLocalService
 * @generated
 */
public class StatusLocalServiceWrapper implements StatusLocalService {
	public StatusLocalServiceWrapper(StatusLocalService statusLocalService) {
		_statusLocalService = statusLocalService;
	}

	public com.liferay.chat.model.Status addStatus(
		com.liferay.chat.model.Status status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.addStatus(status);
	}

	public com.liferay.chat.model.Status createStatus(long statusId) {
		return _statusLocalService.createStatus(statusId);
	}

	public void deleteStatus(long statusId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_statusLocalService.deleteStatus(statusId);
	}

	public void deleteStatus(com.liferay.chat.model.Status status)
		throws com.liferay.portal.kernel.exception.SystemException {
		_statusLocalService.deleteStatus(status);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.chat.model.Status getStatus(long statusId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.getStatus(statusId);
	}

	public java.util.List<com.liferay.chat.model.Status> getStatuses(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.getStatuses(start, end);
	}

	public int getStatusesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.getStatusesCount();
	}

	public com.liferay.chat.model.Status updateStatus(
		com.liferay.chat.model.Status status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.updateStatus(status);
	}

	public com.liferay.chat.model.Status updateStatus(
		com.liferay.chat.model.Status status, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.updateStatus(status, merge);
	}

	public java.util.List<java.lang.Object[]> getAllStatuses(long companyId,
		long userId, long modifiedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.getAllStatuses(companyId, userId,
			modifiedDate, start, end);
	}

	public java.util.List<java.lang.Object[]> getGroupStatuses(long userId,
		long modifiedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.getGroupStatuses(userId, modifiedDate,
			start, end);
	}

	public java.util.List<java.lang.Object[]> getSocialStatuses(long userId,
		int type, long modifiedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.getSocialStatuses(userId, type,
			modifiedDate, start, end);
	}

	public com.liferay.chat.model.Status getUserStatus(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.getUserStatus(userId);
	}

	public com.liferay.chat.model.Status updateStatus(long userId,
		long modifiedDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.updateStatus(userId, modifiedDate);
	}

	public com.liferay.chat.model.Status updateStatus(long userId,
		long modifiedDate, int online, int awake,
		java.lang.String activePanelId, java.lang.String message, int playSound)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.updateStatus(userId, modifiedDate, online,
			awake, activePanelId, message, playSound);
	}

	public StatusLocalService getWrappedStatusLocalService() {
		return _statusLocalService;
	}

	private StatusLocalService _statusLocalService;
}