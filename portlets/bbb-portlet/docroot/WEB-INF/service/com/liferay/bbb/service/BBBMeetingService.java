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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.security.ac.AccessControlled;
import com.liferay.portal.service.BaseService;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service interface for BBBMeeting. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Shinn Lok
 * @see BBBMeetingServiceUtil
 * @see com.liferay.bbb.service.base.BBBMeetingServiceBaseImpl
 * @see com.liferay.bbb.service.impl.BBBMeetingServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface BBBMeetingService extends BaseService, InvokableService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BBBMeetingServiceUtil} to access the b b b meeting remote service. Add custom service methods to {@link com.liferay.bbb.service.impl.BBBMeetingServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public com.liferay.bbb.model.BBBMeeting addBBBMeeting(long groupId,
		java.lang.String portletId, long bbbServerId, java.lang.String name,
		java.lang.String description, java.lang.String attendeePassword,
		java.lang.String moderatorPassword, int status,
		java.util.List<com.liferay.bbb.model.BBBParticipant> bbbParticipants,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException;

	public com.liferay.bbb.model.BBBMeeting deleteBBBMeeting(long bbbMeetingId)
		throws com.liferay.portal.kernel.exception.PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.bbb.model.BBBMeeting getBBBMeeting(long bbbMeetingId)
		throws com.liferay.portal.kernel.exception.PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.bbb.model.BBBMeeting> getBBBMeetings(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.bbb.model.BBBMeeting> obc);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getBBBMeetingsCount(long groupId);

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	public com.liferay.bbb.model.BBBMeeting updateBBBMeeting(
		long bbbMeetingId, long bbbServerId, java.lang.String name,
		java.lang.String description, java.lang.String attendeePassword,
		java.lang.String moderatorPassword,
		java.util.List<com.liferay.bbb.model.BBBParticipant> bbbParticipants,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException;
}