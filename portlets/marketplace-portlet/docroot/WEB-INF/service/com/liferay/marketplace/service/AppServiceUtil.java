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

package com.liferay.marketplace.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the app remote service. This utility wraps {@link com.liferay.marketplace.service.impl.AppServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Ryan Park
 * @see AppService
 * @see com.liferay.marketplace.service.base.AppServiceBaseImpl
 * @see com.liferay.marketplace.service.impl.AppServiceImpl
 * @generated
 */
public class AppServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.marketplace.service.impl.AppServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.marketplace.model.App addApp(long remoteAppId,
		java.lang.String version, java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addApp(remoteAppId, version, inputStream);
	}

	public static void deleteApp(long appId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteApp(appId);
	}

	public static void installApp(long remoteAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().installApp(remoteAppId);
	}

	public static void uninstallApp(long remoteAppId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().uninstallApp(remoteAppId);
	}

	public static com.liferay.marketplace.model.App updateApp(long appId,
		java.lang.String version, java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateApp(appId, version, inputStream);
	}

	public static void clearService() {
		_service = null;
	}

	public static AppService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AppService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					AppService.class.getName(), portletClassLoader);

			_service = new AppServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(AppServiceUtil.class, "_service");
			MethodCache.remove(AppService.class);
		}

		return _service;
	}

	public void setService(AppService service) {
		MethodCache.remove(AppService.class);

		_service = service;

		ReferenceRegistry.registerReference(AppServiceUtil.class, "_service");
		MethodCache.remove(AppService.class);
	}

	private static AppService _service;
}