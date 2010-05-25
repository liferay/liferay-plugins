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

package com.liferay.ams.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="AMSTypeLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * {@link AMSTypeLocalService} bean. The static methods of
 * this class calls the same methods of the bean instance. It's convenient to be
 * able to just write one line to call a method on a bean instead of writing a
 * lookup call and a method call.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AMSTypeLocalService
 * @generated
 */
public class AMSTypeLocalServiceUtil {
	public static com.liferay.ams.model.AMSType addAMSType(
		com.liferay.ams.model.AMSType amsType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAMSType(amsType);
	}

	public static com.liferay.ams.model.AMSType createAMSType(long amsTypeId) {
		return getService().createAMSType(amsTypeId);
	}

	public static void deleteAMSType(long amsTypeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAMSType(amsTypeId);
	}

	public static void deleteAMSType(com.liferay.ams.model.AMSType amsType)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAMSType(amsType);
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

	public static com.liferay.ams.model.AMSType getAMSType(long amsTypeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAMSType(amsTypeId);
	}

	public static java.util.List<com.liferay.ams.model.AMSType> getAMSTypes(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAMSTypes(start, end);
	}

	public static int getAMSTypesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAMSTypesCount();
	}

	public static com.liferay.ams.model.AMSType updateAMSType(
		com.liferay.ams.model.AMSType amsType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAMSType(amsType);
	}

	public static com.liferay.ams.model.AMSType updateAMSType(
		com.liferay.ams.model.AMSType amsType, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAMSType(amsType, merge);
	}

	public static void clearService() {
		_service = null;
	}

	public static AMSTypeLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					AMSTypeLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					AMSTypeLocalService.class.getName(), portletClassLoader);

			_service = new AMSTypeLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(AMSTypeLocalService service) {
		_service = service;
	}

	private static AMSTypeLocalService _service;
}