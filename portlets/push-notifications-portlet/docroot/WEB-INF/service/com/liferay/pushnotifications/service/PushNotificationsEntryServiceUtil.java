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

package com.liferay.pushnotifications.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for PushNotificationsEntry. This utility wraps
 * {@link com.liferay.pushnotifications.service.impl.PushNotificationsEntryServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Bruno Farache
 * @see PushNotificationsEntryService
 * @see com.liferay.pushnotifications.service.base.PushNotificationsEntryServiceBaseImpl
 * @see com.liferay.pushnotifications.service.impl.PushNotificationsEntryServiceImpl
 * @generated
 */
@ProviderType
public class PushNotificationsEntryServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.pushnotifications.service.impl.PushNotificationsEntryServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.pushnotifications.model.PushNotificationsEntry addPushNotificationsEntry(
		java.lang.String payload)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().addPushNotificationsEntry(payload);
	}

	public static com.liferay.pushnotifications.model.PushNotificationsEntry dislikePushNotificationsEntry(
		long pushNotificationsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .dislikePushNotificationsEntry(pushNotificationsEntryId);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	public static java.util.List<com.liferay.pushnotifications.model.PushNotificationsEntry> getPushNotificationsEntries(
		long parentPushNotificationsEntryId, long lastAccessTime, int start,
		int end) {
		return getService()
				   .getPushNotificationsEntries(parentPushNotificationsEntryId,
			lastAccessTime, start, end);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.pushnotifications.model.PushNotificationsEntry likePushNotificationsEntry(
		long pushNotificationsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().likePushNotificationsEntry(pushNotificationsEntryId);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static void clearService() {
		_service = null;
	}

	public static PushNotificationsEntryService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					PushNotificationsEntryService.class.getName());

			if (invokableService instanceof PushNotificationsEntryService) {
				_service = (PushNotificationsEntryService)invokableService;
			}
			else {
				_service = new PushNotificationsEntryServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(PushNotificationsEntryServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(PushNotificationsEntryService service) {
	}

	private static PushNotificationsEntryService _service;
}