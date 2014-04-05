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

import com.liferay.bbb.service.BBBServerServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link com.liferay.bbb.service.BBBServerServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.bbb.model.BBBServerSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.bbb.model.BBBServer}, that is translated to a
 * {@link com.liferay.bbb.model.BBBServerSoap}. Methods that SOAP cannot
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
 * @see BBBServerServiceHttp
 * @see com.liferay.bbb.model.BBBServerSoap
 * @see com.liferay.bbb.service.BBBServerServiceUtil
 * @generated
 */
public class BBBServerServiceSoap {
	public static com.liferay.bbb.model.BBBServerSoap addBBBServer(
		long groupId, java.lang.String name, java.lang.String url,
		java.lang.String secret,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.bbb.model.BBBServer returnValue = BBBServerServiceUtil.addBBBServer(groupId,
					name, url, secret, serviceContext);

			return com.liferay.bbb.model.BBBServerSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.bbb.model.BBBServerSoap deleteBBBServer(
		long bbbServerId) throws RemoteException {
		try {
			com.liferay.bbb.model.BBBServer returnValue = BBBServerServiceUtil.deleteBBBServer(bbbServerId);

			return com.liferay.bbb.model.BBBServerSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.bbb.model.BBBServerSoap[] getBBBServers(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws RemoteException {
		try {
			java.util.List<com.liferay.bbb.model.BBBServer> returnValue = BBBServerServiceUtil.getBBBServers(groupId,
					start, end, obc);

			return com.liferay.bbb.model.BBBServerSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getBBBServersCount(long groupId)
		throws RemoteException {
		try {
			int returnValue = BBBServerServiceUtil.getBBBServersCount(groupId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.bbb.model.BBBServerSoap updateBBBServer(
		long bbbServerId, java.lang.String name, java.lang.String url,
		java.lang.String secret,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.bbb.model.BBBServer returnValue = BBBServerServiceUtil.updateBBBServer(bbbServerId,
					name, url, secret, serviceContext);

			return com.liferay.bbb.model.BBBServerSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(BBBServerServiceSoap.class);
}