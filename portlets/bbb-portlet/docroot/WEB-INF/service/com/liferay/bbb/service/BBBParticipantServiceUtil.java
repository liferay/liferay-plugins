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
 * Provides the remote service utility for BBBParticipant. This utility wraps
 * {@link com.liferay.bbb.service.impl.BBBParticipantServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Shinn Lok
 * @see BBBParticipantService
 * @see com.liferay.bbb.service.base.BBBParticipantServiceBaseImpl
 * @see com.liferay.bbb.service.impl.BBBParticipantServiceImpl
 * @generated
 */
public class BBBParticipantServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.bbb.service.impl.BBBParticipantServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.bbb.model.BBBParticipant deleteBBBParticipant(
		com.liferay.bbb.model.BBBParticipant bbbParticipant)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteBBBParticipant(bbbParticipant);
	}

	public static java.util.List<com.liferay.bbb.model.BBBParticipant> getBBBParticipants(
		long bbbMeetingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getBBBParticipants(bbbMeetingId);
	}

	public static int getBBBParticipantsCount(long bbbMeetingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getBBBParticipantsCount(bbbMeetingId);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static com.liferay.bbb.model.BBBParticipant updateBBBParticipant(
		long bbbParticipantId, long bbbMeetingId, java.lang.String name,
		java.lang.String emailAddress, int type,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateBBBParticipant(bbbParticipantId, bbbMeetingId, name,
			emailAddress, type, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static BBBParticipantService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					BBBParticipantService.class.getName());

			if (invokableService instanceof BBBParticipantService) {
				_service = (BBBParticipantService)invokableService;
			}
			else {
				_service = new BBBParticipantServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(BBBParticipantServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(BBBParticipantService service) {
	}

	private static BBBParticipantService _service;
}