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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <p>
 * This class provides static methods for the
 * {@link StatusLocalService} bean. The static methods of
 * this class calls the same methods of the bean instance. It's convenient to be
 * able to just write one line to call a method on a bean instead of writing a
 * lookup call and a method call.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       StatusLocalService
 * @generated
 */
public class StatusLocalServiceUtil {
	public static com.liferay.chat.model.Status addStatus(
		com.liferay.chat.model.Status status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addStatus(status);
	}

	public static com.liferay.chat.model.Status createStatus(long statusId) {
		return getService().createStatus(statusId);
	}

	public static void deleteStatus(long statusId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteStatus(statusId);
	}

	public static void deleteStatus(com.liferay.chat.model.Status status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteStatus(status);
	}

	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.chat.model.Status getStatus(long statusId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getStatus(statusId);
	}

	public static java.util.List<com.liferay.chat.model.Status> getStatuses(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getStatuses(start, end);
	}

	public static int getStatusesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getStatusesCount();
	}

	public static com.liferay.chat.model.Status updateStatus(
		com.liferay.chat.model.Status status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateStatus(status);
	}

	public static com.liferay.chat.model.Status updateStatus(
		com.liferay.chat.model.Status status, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateStatus(status, merge);
	}

	public static java.util.List<java.lang.Object[]> getAllStatuses(
		long companyId, long userId, long modifiedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAllStatuses(companyId, userId, modifiedDate, start, end);
	}

	public static java.util.List<java.lang.Object[]> getGroupStatuses(
		long userId, long modifiedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupStatuses(userId, modifiedDate, start, end);
	}

	public static java.util.List<java.lang.Object[]> getSocialStatuses(
		long userId, int type, long modifiedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSocialStatuses(userId, type, modifiedDate, start, end);
	}

	public static com.liferay.chat.model.Status getUserStatus(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserStatus(userId);
	}

	public static com.liferay.chat.model.Status updateStatus(long userId,
		long modifiedDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateStatus(userId, modifiedDate);
	}

	public static com.liferay.chat.model.Status updateStatus(long userId,
		long modifiedDate, int online, int awake,
		java.lang.String activePanelId, java.lang.String message, int playSound)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateStatus(userId, modifiedDate, online, awake,
			activePanelId, message, playSound);
	}

	public static void clearService() {
		_service = null;
	}

	public static StatusLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					StatusLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					StatusLocalService.class.getName(), portletClassLoader);

			_service = new StatusLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(StatusLocalService service) {
		_service = service;
	}

	private static StatusLocalService _service;
}