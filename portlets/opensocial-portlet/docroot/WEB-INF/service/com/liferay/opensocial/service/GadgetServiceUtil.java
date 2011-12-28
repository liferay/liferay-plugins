/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.opensocial.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the gadget remote service. This utility wraps {@link com.liferay.opensocial.service.impl.GadgetServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GadgetService
 * @see com.liferay.opensocial.service.base.GadgetServiceBaseImpl
 * @see com.liferay.opensocial.service.impl.GadgetServiceImpl
 * @generated
 */
public class GadgetServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.opensocial.service.impl.GadgetServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.opensocial.model.Gadget addGadget(
		long companyId, java.lang.String url,
		java.lang.String portletCategoryNames,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addGadget(companyId, url, portletCategoryNames,
			serviceContext);
	}

	public static void deleteGadget(long gadgetId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteGadget(gadgetId, serviceContext);
	}

	public static void updateGadget(long gadgetId,
		java.lang.String portletCategoryNames,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().updateGadget(gadgetId, portletCategoryNames, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static GadgetService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					GadgetService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					GadgetService.class.getName(), portletClassLoader);

			_service = new GadgetServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(GadgetServiceUtil.class,
				"_service");
			MethodCache.remove(GadgetService.class);
		}

		return _service;
	}

	public void setService(GadgetService service) {
		MethodCache.remove(GadgetService.class);

		_service = service;

		ReferenceRegistry.registerReference(GadgetServiceUtil.class, "_service");
		MethodCache.remove(GadgetService.class);
	}

	private static GadgetService _service;
}