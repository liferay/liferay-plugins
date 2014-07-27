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

package com.liferay.mobile.widgets.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for MWUser. This utility wraps
 * {@link com.liferay.mobile.widgets.service.impl.MWUserServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Jose M. Navarro
 * @see MWUserService
 * @see com.liferay.mobile.widgets.service.base.MWUserServiceBaseImpl
 * @see com.liferay.mobile.widgets.service.impl.MWUserServiceImpl
 * @generated
 */
public class MWUserServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.mobile.widgets.service.impl.MWUserServiceImpl} and rerun ServiceBuilder to regenerate this class.
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

	public static boolean sendPasswordByEmailAddress(long companyId,
		java.lang.String emailAddress,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return getService()
				   .sendPasswordByEmailAddress(companyId, emailAddress,
			serviceContext);
	}

	public static boolean sendPasswordByScreenName(long companyId,
		java.lang.String screenName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return getService()
				   .sendPasswordByScreenName(companyId, screenName,
			serviceContext);
	}

	public static boolean sendPasswordByUserId(long companyId, long userId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return getService()
				   .sendPasswordByUserId(companyId, userId, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static MWUserService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					MWUserService.class.getName());

			if (invokableService instanceof MWUserService) {
				_service = (MWUserService)invokableService;
			}
			else {
				_service = new MWUserServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(MWUserServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(MWUserService service) {
	}

	private static MWUserService _service;
}