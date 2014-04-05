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

import com.liferay.bbb.service.BBBParticipantServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link com.liferay.bbb.service.BBBParticipantServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.bbb.model.BBBParticipantSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.bbb.model.BBBParticipant}, that is translated to a
 * {@link com.liferay.bbb.model.BBBParticipantSoap}. Methods that SOAP cannot
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
 * @see BBBParticipantServiceHttp
 * @see com.liferay.bbb.model.BBBParticipantSoap
 * @see com.liferay.bbb.service.BBBParticipantServiceUtil
 * @generated
 */
public class BBBParticipantServiceSoap {
	public static com.liferay.bbb.model.BBBParticipantSoap deleteBBBParticipant(
		com.liferay.bbb.model.BBBParticipantSoap bbbParticipant)
		throws RemoteException {
		try {
			com.liferay.bbb.model.BBBParticipant returnValue = BBBParticipantServiceUtil.deleteBBBParticipant(com.liferay.bbb.model.impl.BBBParticipantModelImpl.toModel(
						bbbParticipant));

			return com.liferay.bbb.model.BBBParticipantSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.bbb.model.BBBParticipantSoap[] getBBBParticipants(
		long bbbMeetingId) throws RemoteException {
		try {
			java.util.List<com.liferay.bbb.model.BBBParticipant> returnValue = BBBParticipantServiceUtil.getBBBParticipants(bbbMeetingId);

			return com.liferay.bbb.model.BBBParticipantSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getBBBParticipantsCount(long bbbMeetingId)
		throws RemoteException {
		try {
			int returnValue = BBBParticipantServiceUtil.getBBBParticipantsCount(bbbMeetingId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.bbb.model.BBBParticipantSoap updateBBBParticipant(
		long bbbParticipantId, long bbbMeetingId, java.lang.String name,
		java.lang.String emailAddress, int type,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.bbb.model.BBBParticipant returnValue = BBBParticipantServiceUtil.updateBBBParticipant(bbbParticipantId,
					bbbMeetingId, name, emailAddress, type, serviceContext);

			return com.liferay.bbb.model.BBBParticipantSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(BBBParticipantServiceSoap.class);
}