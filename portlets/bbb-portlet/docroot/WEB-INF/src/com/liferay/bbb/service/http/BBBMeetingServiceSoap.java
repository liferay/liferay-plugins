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

package com.liferay.bbb.service.http;

import com.liferay.bbb.service.BBBMeetingServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link com.liferay.bbb.service.BBBMeetingServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.bbb.model.BBBMeetingSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.bbb.model.BBBMeeting}, that is translated to a
 * {@link com.liferay.bbb.model.BBBMeetingSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Shinn Lok
 * @see BBBMeetingServiceHttp
 * @see com.liferay.bbb.model.BBBMeetingSoap
 * @see com.liferay.bbb.service.BBBMeetingServiceUtil
 * @generated
 */
public class BBBMeetingServiceSoap {
	public static com.liferay.bbb.model.BBBMeetingSoap addBBBMeeting(
		long groupId, java.lang.String portletId, long bbbServerId,
		java.lang.String name, java.lang.String description,
		java.lang.String attendeePassword, java.lang.String moderatorPassword,
		int status, com.liferay.bbb.model.BBBParticipantSoap[] bbbParticipants,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.bbb.model.BBBMeeting returnValue = BBBMeetingServiceUtil.addBBBMeeting(groupId,
					portletId, bbbServerId, name, description,
					attendeePassword, moderatorPassword, status,
					com.liferay.bbb.model.impl.BBBParticipantModelImpl.toModels(
						bbbParticipants), serviceContext);

			return com.liferay.bbb.model.BBBMeetingSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.bbb.model.BBBMeetingSoap deleteBBBMeeting(
		long bbbMeetingId) throws RemoteException {
		try {
			com.liferay.bbb.model.BBBMeeting returnValue = BBBMeetingServiceUtil.deleteBBBMeeting(bbbMeetingId);

			return com.liferay.bbb.model.BBBMeetingSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.bbb.model.BBBMeetingSoap getBBBMeeting(
		long bbbMeetingId) throws RemoteException {
		try {
			com.liferay.bbb.model.BBBMeeting returnValue = BBBMeetingServiceUtil.getBBBMeeting(bbbMeetingId);

			return com.liferay.bbb.model.BBBMeetingSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.bbb.model.BBBMeetingSoap[] getBBBMeetings(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.bbb.model.BBBMeeting> obc)
		throws RemoteException {
		try {
			java.util.List<com.liferay.bbb.model.BBBMeeting> returnValue = BBBMeetingServiceUtil.getBBBMeetings(groupId,
					start, end, obc);

			return com.liferay.bbb.model.BBBMeetingSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getBBBMeetingsCount(long groupId)
		throws RemoteException {
		try {
			int returnValue = BBBMeetingServiceUtil.getBBBMeetingsCount(groupId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.bbb.model.BBBMeetingSoap updateBBBMeeting(
		long bbbMeetingId, long bbbServerId, java.lang.String name,
		java.lang.String description, java.lang.String attendeePassword,
		java.lang.String moderatorPassword,
		com.liferay.bbb.model.BBBParticipantSoap[] bbbParticipants,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.bbb.model.BBBMeeting returnValue = BBBMeetingServiceUtil.updateBBBMeeting(bbbMeetingId,
					bbbServerId, name, description, attendeePassword,
					moderatorPassword,
					com.liferay.bbb.model.impl.BBBParticipantModelImpl.toModels(
						bbbParticipants), serviceContext);

			return com.liferay.bbb.model.BBBMeetingSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(BBBMeetingServiceSoap.class);
}