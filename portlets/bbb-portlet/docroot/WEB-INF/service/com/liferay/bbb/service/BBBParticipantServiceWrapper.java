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

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BBBParticipantService}.
 *
 * @author Shinn Lok
 * @see BBBParticipantService
 * @generated
 */
public class BBBParticipantServiceWrapper implements BBBParticipantService,
	ServiceWrapper<BBBParticipantService> {
	public BBBParticipantServiceWrapper(
		BBBParticipantService bbbParticipantService) {
		_bbbParticipantService = bbbParticipantService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _bbbParticipantService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_bbbParticipantService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _bbbParticipantService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.bbb.model.BBBParticipant deleteBBBParticipant(
		com.liferay.bbb.model.BBBParticipant bbbParticipant)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _bbbParticipantService.deleteBBBParticipant(bbbParticipant);
	}

	@Override
	public java.util.List<com.liferay.bbb.model.BBBParticipant> getBBBParticipants(
		long bbbMeetingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _bbbParticipantService.getBBBParticipants(bbbMeetingId);
	}

	@Override
	public int getBBBParticipantsCount(long bbbMeetingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _bbbParticipantService.getBBBParticipantsCount(bbbMeetingId);
	}

	@Override
	public com.liferay.bbb.model.BBBParticipant updateBBBParticipant(
		long bbbParticipantId, long bbbMeetingId, java.lang.String name,
		java.lang.String emailAddress, int type,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _bbbParticipantService.updateBBBParticipant(bbbParticipantId,
			bbbMeetingId, name, emailAddress, type, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public BBBParticipantService getWrappedBBBParticipantService() {
		return _bbbParticipantService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedBBBParticipantService(
		BBBParticipantService bbbParticipantService) {
		_bbbParticipantService = bbbParticipantService;
	}

	@Override
	public BBBParticipantService getWrappedService() {
		return _bbbParticipantService;
	}

	@Override
	public void setWrappedService(BBBParticipantService bbbParticipantService) {
		_bbbParticipantService = bbbParticipantService;
	}

	private BBBParticipantService _bbbParticipantService;
}