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

package com.liferay.bbb.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for BBBServer. This utility wraps
 * {@link com.liferay.bbb.service.impl.BBBServerServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Shinn Lok
 * @see BBBServerService
 * @see com.liferay.bbb.service.base.BBBServerServiceBaseImpl
 * @see com.liferay.bbb.service.impl.BBBServerServiceImpl
 * @generated
 */
public class BBBServerServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.bbb.service.impl.BBBServerServiceImpl} and rerun ServiceBuilder to regenerate this class.
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

	public static com.liferay.bbb.model.BBBServer addBBBServer(long groupId,
		java.lang.String name, java.lang.String url, java.lang.String secret,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addBBBServer(groupId, name, url, secret, serviceContext);
	}

	public static com.liferay.bbb.model.BBBServer deleteBBBServer(
		long bbbServerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteBBBServer(bbbServerId);
	}

	public static java.util.List<com.liferay.bbb.model.BBBServer> getBBBServers(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getBBBServers(groupId, start, end, obc);
	}

	public static int getBBBServersCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getBBBServersCount(groupId);
	}

	public static com.liferay.bbb.model.BBBServer updateBBBServer(
		long bbbServerId, java.lang.String name, java.lang.String url,
		java.lang.String secret,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateBBBServer(bbbServerId, name, url, secret,
			serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static BBBServerService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					BBBServerService.class.getName());

			if (invokableService instanceof BBBServerService) {
				_service = (BBBServerService)invokableService;
			}
			else {
				_service = new BBBServerServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(BBBServerServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(BBBServerService service) {
	}

	private static BBBServerService _service;
}