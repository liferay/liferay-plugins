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
 * Provides a wrapper for {@link BBBMeetingService}.
 *
 * @author Shinn Lok
 * @see BBBMeetingService
 * @generated
 */
public class BBBMeetingServiceWrapper implements BBBMeetingService,
	ServiceWrapper<BBBMeetingService> {
	public BBBMeetingServiceWrapper(BBBMeetingService bbbMeetingService) {
		_bbbMeetingService = bbbMeetingService;
	}

	@Override
	public com.liferay.bbb.model.BBBMeeting addBBBMeeting(long groupId,
		java.lang.String portletId, long bbbServerId, java.lang.String name,
		java.lang.String description, java.lang.String attendeePassword,
		java.lang.String moderatorPassword, int status,
		java.util.List<com.liferay.bbb.model.BBBParticipant> bbbParticipants,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bbbMeetingService.addBBBMeeting(groupId, portletId,
			bbbServerId, name, description, attendeePassword,
			moderatorPassword, status, bbbParticipants, serviceContext);
	}

	@Override
	public com.liferay.bbb.model.BBBMeeting deleteBBBMeeting(long bbbMeetingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bbbMeetingService.deleteBBBMeeting(bbbMeetingId);
	}

	@Override
	public com.liferay.bbb.model.BBBMeeting getBBBMeeting(long bbbMeetingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bbbMeetingService.getBBBMeeting(bbbMeetingId);
	}

	@Override
	public java.util.List<com.liferay.bbb.model.BBBMeeting> getBBBMeetings(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _bbbMeetingService.getBBBMeetings(groupId, start, end, obc);
	}

	@Override
	public int getBBBMeetingsCount(long groupId) {
		return _bbbMeetingService.getBBBMeetingsCount(groupId);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _bbbMeetingService.getBeanIdentifier();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _bbbMeetingService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_bbbMeetingService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public com.liferay.bbb.model.BBBMeeting updateBBBMeeting(
		long bbbMeetingId, long bbbServerId, java.lang.String name,
		java.lang.String description, java.lang.String attendeePassword,
		java.lang.String moderatorPassword,
		java.util.List<com.liferay.bbb.model.BBBParticipant> bbbParticipants,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bbbMeetingService.updateBBBMeeting(bbbMeetingId, bbbServerId,
			name, description, attendeePassword, moderatorPassword,
			bbbParticipants, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public BBBMeetingService getWrappedBBBMeetingService() {
		return _bbbMeetingService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedBBBMeetingService(BBBMeetingService bbbMeetingService) {
		_bbbMeetingService = bbbMeetingService;
	}

	@Override
	public BBBMeetingService getWrappedService() {
		return _bbbMeetingService;
	}

	@Override
	public void setWrappedService(BBBMeetingService bbbMeetingService) {
		_bbbMeetingService = bbbMeetingService;
	}

	private BBBMeetingService _bbbMeetingService;
}