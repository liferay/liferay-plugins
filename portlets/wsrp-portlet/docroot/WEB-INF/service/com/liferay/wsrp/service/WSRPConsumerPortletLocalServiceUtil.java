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

package com.liferay.wsrp.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <p>
 * This class provides static methods for the
 * {@link WSRPConsumerPortletLocalService} bean. The static methods of
 * this class calls the same methods of the bean instance. It's convenient to be
 * able to just write one line to call a method on a bean instead of writing a
 * lookup call and a method call.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WSRPConsumerPortletLocalService
 * @generated
 */
public class WSRPConsumerPortletLocalServiceUtil {
	public static com.liferay.wsrp.model.WSRPConsumerPortlet addWSRPConsumerPortlet(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addWSRPConsumerPortlet(wsrpConsumerPortlet);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet createWSRPConsumerPortlet(
		long wsrpConsumerPortletId) {
		return getService().createWSRPConsumerPortlet(wsrpConsumerPortletId);
	}

	public static void deleteWSRPConsumerPortlet(long wsrpConsumerPortletId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteWSRPConsumerPortlet(wsrpConsumerPortletId);
	}

	public static void deleteWSRPConsumerPortlet(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteWSRPConsumerPortlet(wsrpConsumerPortlet);
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

	public static com.liferay.wsrp.model.WSRPConsumerPortlet getWSRPConsumerPortlet(
		long wsrpConsumerPortletId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getWSRPConsumerPortlet(wsrpConsumerPortletId);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> getWSRPConsumerPortlets(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getWSRPConsumerPortlets(start, end);
	}

	public static int getWSRPConsumerPortletsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getWSRPConsumerPortletsCount();
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet updateWSRPConsumerPortlet(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateWSRPConsumerPortlet(wsrpConsumerPortlet);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet updateWSRPConsumerPortlet(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateWSRPConsumerPortlet(wsrpConsumerPortlet, merge);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet addWSRPConsumerPortlet(
		long wsrpConsumerId, java.lang.String name,
		java.lang.String portletHandle, java.lang.String userToken)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addWSRPConsumerPortlet(wsrpConsumerId, name, portletHandle,
			userToken);
	}

	public static void deleteWSRPConsumerPortlets(long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteWSRPConsumerPortlets(wsrpConsumerId);
	}

	public static void destroyWSRPConsumerPortlet(long wsrpConsumerPortletId,
		java.lang.String url) {
		getService().destroyWSRPConsumerPortlet(wsrpConsumerPortletId, url);
	}

	public static void destroyWSRPConsumerPortlets()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().destroyWSRPConsumerPortlets();
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet getWSRPConsumerPortlet(
		long wsrpConsumerId, java.lang.String portletHandle)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getWSRPConsumerPortlet(wsrpConsumerId, portletHandle);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> getWSRPConsumerPortlets(
		long wsrpConsumerId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getWSRPConsumerPortlets(wsrpConsumerId, start, end);
	}

	public static int getWSRPConsumerPortletsCount(long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getWSRPConsumerPortletsCount(wsrpConsumerId);
	}

	public static void initWSRPConsumerPortlet(long companyId,
		long wsrpConsumerId, long wsrpConsumerPortletId, java.lang.String name,
		java.lang.String portletHandle, java.lang.String userToken)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.initWSRPConsumerPortlet(companyId, wsrpConsumerId,
			wsrpConsumerPortletId, name, portletHandle, userToken);
	}

	public static void initWSRPConsumerPortlets()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().initWSRPConsumerPortlets();
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet updateWSRPConsumerPortlet(
		long wsrpConsumerPortletId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateWSRPConsumerPortlet(wsrpConsumerPortletId, name);
	}

	public static void clearService() {
		_service = null;
	}

	public static WSRPConsumerPortletLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					WSRPConsumerPortletLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					WSRPConsumerPortletLocalService.class.getName(),
					portletClassLoader);

			_service = new WSRPConsumerPortletLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(WSRPConsumerPortletLocalService service) {
		_service = service;
	}

	private static WSRPConsumerPortletLocalService _service;
}