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

package com.liferay.privatemessaging.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for UserThread. This utility wraps
 * {@link com.liferay.privatemessaging.service.impl.UserThreadServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see UserThreadService
 * @see com.liferay.privatemessaging.service.base.UserThreadServiceBaseImpl
 * @see com.liferay.privatemessaging.service.impl.UserThreadServiceImpl
 * @generated
 */
@ProviderType
public class UserThreadServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.privatemessaging.service.impl.UserThreadServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portlet.messageboards.model.MBMessage getLastThreadMessage(
		long mbThreadId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLastThreadMessage(mbThreadId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.portlet.messageboards.model.MBMessage> getThreadMessages(
		long mbThreadId, int start, int end, boolean ascending)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getThreadMessages(mbThreadId, start, end, ascending);
	}

	public static int getThreadMessagesCount(long mbThreadId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getThreadMessagesCount(mbThreadId);
	}

	public static java.util.List<com.liferay.privatemessaging.model.UserThread> getUserUserThreads(
		boolean deleted)
		throws com.liferay.portal.security.auth.PrincipalException {
		return getService().getUserUserThreads(deleted);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static void clearService() {
		_service = null;
	}

	public static UserThreadService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					UserThreadService.class.getName());

			if (invokableService instanceof UserThreadService) {
				_service = (UserThreadService)invokableService;
			}
			else {
				_service = new UserThreadServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(UserThreadServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static UserThreadService _service;
}