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
 * <a href="AMSCheckoutLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * {@link AMSCheckoutLocalService} bean. The static methods of
 * this class calls the same methods of the bean instance. It's convenient to be
 * able to just write one line to call a method on a bean instead of writing a
 * lookup call and a method call.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AMSCheckoutLocalService
 * @generated
 */
public class AMSCheckoutLocalServiceUtil {
	public static com.liferay.ams.model.AMSCheckout addAMSCheckout(
		com.liferay.ams.model.AMSCheckout amsCheckout)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAMSCheckout(amsCheckout);
	}

	public static com.liferay.ams.model.AMSCheckout createAMSCheckout(
		long amsCheckoutId) {
		return getService().createAMSCheckout(amsCheckoutId);
	}

	public static void deleteAMSCheckout(long amsCheckoutId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAMSCheckout(amsCheckoutId);
	}

	public static void deleteAMSCheckout(
		com.liferay.ams.model.AMSCheckout amsCheckout)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAMSCheckout(amsCheckout);
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

	public static com.liferay.ams.model.AMSCheckout getAMSCheckout(
		long amsCheckoutId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAMSCheckout(amsCheckoutId);
	}

	public static java.util.List<com.liferay.ams.model.AMSCheckout> getAMSCheckouts(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAMSCheckouts(start, end);
	}

	public static int getAMSCheckoutsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAMSCheckoutsCount();
	}

	public static com.liferay.ams.model.AMSCheckout updateAMSCheckout(
		com.liferay.ams.model.AMSCheckout amsCheckout)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAMSCheckout(amsCheckout);
	}

	public static com.liferay.ams.model.AMSCheckout updateAMSCheckout(
		com.liferay.ams.model.AMSCheckout amsCheckout, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAMSCheckout(amsCheckout, merge);
	}

	public static void clearService() {
		_service = null;
	}

	public static AMSCheckoutLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					AMSCheckoutLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					AMSCheckoutLocalService.class.getName(), portletClassLoader);

			_service = new AMSCheckoutLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(AMSCheckoutLocalService service) {
		_service = service;
	}

	private static AMSCheckoutLocalService _service;
}