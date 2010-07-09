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
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * {@link WSRPProducerLocalService} bean. The static methods of
 * this class calls the same methods of the bean instance. It's convenient to be
 * able to just write one line to call a method on a bean instead of writing a
 * lookup call and a method call.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WSRPProducerLocalService
 * @generated
 */
public class WSRPProducerLocalServiceUtil {
	public static com.liferay.wsrp.model.WSRPProducer addWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addWSRPProducer(wsrpProducer);
	}

	public static com.liferay.wsrp.model.WSRPProducer createWSRPProducer(
		long wsrpProducerId) {
		return getService().createWSRPProducer(wsrpProducerId);
	}

	public static void deleteWSRPProducer(long wsrpProducerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteWSRPProducer(wsrpProducerId);
	}

	public static void deleteWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteWSRPProducer(wsrpProducer);
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

	public static com.liferay.wsrp.model.WSRPProducer getWSRPProducer(
		long wsrpProducerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getWSRPProducer(wsrpProducerId);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPProducer> getWSRPProducers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getWSRPProducers(start, end);
	}

	public static int getWSRPProducersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getWSRPProducersCount();
	}

	public static com.liferay.wsrp.model.WSRPProducer updateWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateWSRPProducer(wsrpProducer);
	}

	public static com.liferay.wsrp.model.WSRPProducer updateWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateWSRPProducer(wsrpProducer, merge);
	}

	public static com.liferay.wsrp.model.WSRPProducer addWSRPProducer(
		long userId, java.lang.String name, java.lang.String portletIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addWSRPProducer(userId, name, portletIds);
	}

	public static com.liferay.wsrp.model.WSRPProducer addWSRPProducer(
		long userId, long groupId, java.lang.String name,
		java.lang.String portletIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addWSRPProducer(userId, groupId, name, portletIds);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPProducer> getWSRPProducers(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getWSRPProducers(companyId, start, end);
	}

	public static int getWSRPProducersCount(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getWSRPProducersCount(companyId);
	}

	public static com.liferay.wsrp.model.WSRPProducer updateWSRPProducer(
		long wsrpProducerId, java.lang.String name, java.lang.String portletIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateWSRPProducer(wsrpProducerId, name, portletIds);
	}

	public static void clearService() {
		_service = null;
	}

	public static WSRPProducerLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					WSRPProducerLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					WSRPProducerLocalService.class.getName(), portletClassLoader);

			_service = new WSRPProducerLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(WSRPProducerLocalService service) {
		_service = service;
	}

	private static WSRPProducerLocalService _service;
}