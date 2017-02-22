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

package com.liferay.screens.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.liferay.screens.service.ScreensRatingsEntryServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link com.liferay.screens.service.ScreensRatingsEntryServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
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
 * @author José Manuel Navarro
 * @see ScreensRatingsEntryServiceHttp
 * @see com.liferay.screens.service.ScreensRatingsEntryServiceUtil
 * @generated
 */
public class ScreensRatingsEntryServiceSoap {
	public static java.lang.String deleteRatingsEntry(long classPK,
		java.lang.String className, int ratingsLength)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = ScreensRatingsEntryServiceUtil.deleteRatingsEntry(classPK,
					className, ratingsLength);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getRatingsEntries(long assetEntryId,
		int ratingsLength) throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = ScreensRatingsEntryServiceUtil.getRatingsEntries(assetEntryId,
					ratingsLength);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getRatingsEntries(long classPK,
		java.lang.String className, int ratingsLength)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = ScreensRatingsEntryServiceUtil.getRatingsEntries(classPK,
					className, ratingsLength);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String updateRatingsEntry(long classPK,
		java.lang.String className, double score, int ratingsLength)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = ScreensRatingsEntryServiceUtil.updateRatingsEntry(classPK,
					className, score, ratingsLength);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ScreensRatingsEntryServiceSoap.class);
}