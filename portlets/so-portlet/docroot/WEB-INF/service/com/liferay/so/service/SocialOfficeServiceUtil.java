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

package com.liferay.so.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for SocialOffice. This utility wraps
 * {@link com.liferay.so.service.impl.SocialOfficeServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see SocialOfficeService
 * @see com.liferay.so.service.base.SocialOfficeServiceBaseImpl
 * @see com.liferay.so.service.impl.SocialOfficeServiceImpl
 * @generated
 */
@ProviderType
public class SocialOfficeServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.so.service.impl.SocialOfficeServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static long[] getUserSocialOfficeGroupIds()
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserSocialOfficeGroupIds();
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static boolean isSocialOfficeGroup(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().isSocialOfficeGroup(groupId);
	}

	public static void clearService() {
		_service = null;
	}

	public static SocialOfficeService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SocialOfficeService.class.getName());

			if (invokableService instanceof SocialOfficeService) {
				_service = (SocialOfficeService)invokableService;
			}
			else {
				_service = new SocialOfficeServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(SocialOfficeServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(SocialOfficeService service) {
	}

	private static SocialOfficeService _service;
}