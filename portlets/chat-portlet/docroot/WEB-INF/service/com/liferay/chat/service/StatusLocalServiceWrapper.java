/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.chat.service;

/**
 * <a href="StatusLocalServiceWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class StatusLocalServiceWrapper implements StatusLocalService {
	public StatusLocalServiceWrapper(StatusLocalService statusLocalService) {
		_statusLocalService = statusLocalService;
	}

	public com.liferay.chat.model.Status addStatus(
		com.liferay.chat.model.Status status)
		throws com.liferay.portal.SystemException {
		return _statusLocalService.addStatus(status);
	}

	public com.liferay.chat.model.Status createStatus(long statusId) {
		return _statusLocalService.createStatus(statusId);
	}

	public void deleteStatus(long statusId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_statusLocalService.deleteStatus(statusId);
	}

	public void deleteStatus(com.liferay.chat.model.Status status)
		throws com.liferay.portal.SystemException {
		_statusLocalService.deleteStatus(status);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return _statusLocalService.dynamicQuery(dynamicQuery);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return _statusLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	public com.liferay.chat.model.Status getStatus(long statusId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _statusLocalService.getStatus(statusId);
	}

	public java.util.List<com.liferay.chat.model.Status> getStatuses(
		int start, int end) throws com.liferay.portal.SystemException {
		return _statusLocalService.getStatuses(start, end);
	}

	public int getStatusesCount() throws com.liferay.portal.SystemException {
		return _statusLocalService.getStatusesCount();
	}

	public com.liferay.chat.model.Status updateStatus(
		com.liferay.chat.model.Status status)
		throws com.liferay.portal.SystemException {
		return _statusLocalService.updateStatus(status);
	}

	public com.liferay.chat.model.Status updateStatus(
		com.liferay.chat.model.Status status, boolean merge)
		throws com.liferay.portal.SystemException {
		return _statusLocalService.updateStatus(status, merge);
	}

	public java.util.List<Object[]> getAllStatuses(long userId,
		long modifiedDate, int start, int end)
		throws com.liferay.portal.SystemException {
		return _statusLocalService.getAllStatuses(userId, modifiedDate, start,
			end);
	}

	public java.util.List<Object[]> getGroupStatuses(long userId,
		long modifiedDate, int start, int end)
		throws com.liferay.portal.SystemException {
		return _statusLocalService.getGroupStatuses(userId, modifiedDate,
			start, end);
	}

	public java.util.List<Object[]> getSocialStatuses(long userId, int type,
		long modifiedDate, int start, int end)
		throws com.liferay.portal.SystemException {
		return _statusLocalService.getSocialStatuses(userId, type,
			modifiedDate, start, end);
	}

	public com.liferay.chat.model.Status getUserStatus(long userId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _statusLocalService.getUserStatus(userId);
	}

	public com.liferay.chat.model.Status updateStatus(long userId,
		long modifiedDate)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _statusLocalService.updateStatus(userId, modifiedDate);
	}

	public com.liferay.chat.model.Status updateStatus(long userId,
		long modifiedDate, int online, int awake,
		java.lang.String activePanelId, java.lang.String message, int playSound)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _statusLocalService.updateStatus(userId, modifiedDate, online,
			awake, activePanelId, message, playSound);
	}

	public StatusLocalService getWrappedStatusLocalService() {
		return _statusLocalService;
	}

	private StatusLocalService _statusLocalService;
}