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

package com.liferay.socialcoding.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <p>
 * This class provides static methods for the
 * {@link JIRAChangeItemLocalService} bean. The static methods of
 * this class calls the same methods of the bean instance. It's convenient to be
 * able to just write one line to call a method on a bean instead of writing a
 * lookup call and a method call.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       JIRAChangeItemLocalService
 * @generated
 */
public class JIRAChangeItemLocalServiceUtil {
	public static com.liferay.socialcoding.model.JIRAChangeItem addJIRAChangeItem(
		com.liferay.socialcoding.model.JIRAChangeItem jiraChangeItem)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addJIRAChangeItem(jiraChangeItem);
	}

	public static com.liferay.socialcoding.model.JIRAChangeItem createJIRAChangeItem(
		long jiraChangeItemId) {
		return getService().createJIRAChangeItem(jiraChangeItemId);
	}

	public static void deleteJIRAChangeItem(long jiraChangeItemId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteJIRAChangeItem(jiraChangeItemId);
	}

	public static void deleteJIRAChangeItem(
		com.liferay.socialcoding.model.JIRAChangeItem jiraChangeItem)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteJIRAChangeItem(jiraChangeItem);
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

	public static com.liferay.socialcoding.model.JIRAChangeItem getJIRAChangeItem(
		long jiraChangeItemId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getJIRAChangeItem(jiraChangeItemId);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> getJIRAChangeItems(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getJIRAChangeItems(start, end);
	}

	public static int getJIRAChangeItemsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getJIRAChangeItemsCount();
	}

	public static com.liferay.socialcoding.model.JIRAChangeItem updateJIRAChangeItem(
		com.liferay.socialcoding.model.JIRAChangeItem jiraChangeItem)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateJIRAChangeItem(jiraChangeItem);
	}

	public static com.liferay.socialcoding.model.JIRAChangeItem updateJIRAChangeItem(
		com.liferay.socialcoding.model.JIRAChangeItem jiraChangeItem,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateJIRAChangeItem(jiraChangeItem, merge);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> getJIRAChangeItems(
		long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getJIRAChangeItems(jiraChangeGroupId);
	}

	public static void clearService() {
		_service = null;
	}

	public static JIRAChangeItemLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					JIRAChangeItemLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					JIRAChangeItemLocalService.class.getName(),
					portletClassLoader);

			_service = new JIRAChangeItemLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(JIRAChangeItemLocalService service) {
		_service = service;
	}

	private static JIRAChangeItemLocalService _service;
}