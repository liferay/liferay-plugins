/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.samplepersonaldata.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for SPD. This utility wraps
 * {@link com.liferay.samplepersonaldata.service.impl.SPDServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see SPDService
 * @see com.liferay.samplepersonaldata.service.base.SPDServiceBaseImpl
 * @see com.liferay.samplepersonaldata.service.impl.SPDServiceImpl
 * @generated
 */
public class SPDServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.samplepersonaldata.service.impl.SPDServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static void anonymizeBlogsEntries(long userId, long anonymousUserId)
		throws java.lang.Exception {
		getService().anonymizeBlogsEntries(userId, anonymousUserId);
	}

	public static void deleteBlogsEntries(long userId)
		throws java.lang.Exception {
		getService().deleteBlogsEntries(userId);
	}

	public static java.lang.String exportBlogsEntries(long userId)
		throws java.lang.Exception {
		return getService().exportBlogsEntries(userId);
	}

	public static void clearService() {
		_service = null;
	}

	public static SPDService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SPDService.class.getName());

			if (invokableService instanceof SPDService) {
				_service = (SPDService)invokableService;
			}
			else {
				_service = new SPDServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(SPDServiceUtil.class, "_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(SPDService service) {
	}

	private static SPDService _service;
}