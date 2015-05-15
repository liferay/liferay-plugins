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
import com.liferay.portal.kernel.util.LocaleUtil;

import com.liferay.screens.service.ScreensDDLRecordServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link com.liferay.screens.service.ScreensDDLRecordServiceUtil} service utility. The
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
 * @see ScreensDDLRecordServiceHttp
 * @see com.liferay.screens.service.ScreensDDLRecordServiceUtil
 * @generated
 */
public class ScreensDDLRecordServiceSoap {
	public static java.lang.String getDDLRecord(long ddlRecordId, String locale)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = ScreensDDLRecordServiceUtil.getDDLRecord(ddlRecordId,
					LocaleUtil.fromLanguageId(locale));

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getDDLRecords(long ddlRecordSetId,
		String locale, int start, int end) throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONArray returnValue = ScreensDDLRecordServiceUtil.getDDLRecords(ddlRecordSetId,
					LocaleUtil.fromLanguageId(locale), start, end);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getDDLRecords(long ddlRecordSetId,
		long userId, String locale, int start, int end)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONArray returnValue = ScreensDDLRecordServiceUtil.getDDLRecords(ddlRecordSetId,
					userId, LocaleUtil.fromLanguageId(locale), start, end);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getDDLRecordsCount(long ddlRecordSetId)
		throws RemoteException {
		try {
			int returnValue = ScreensDDLRecordServiceUtil.getDDLRecordsCount(ddlRecordSetId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getDDLRecordsCount(long ddlRecordSetId, long userId)
		throws RemoteException {
		try {
			int returnValue = ScreensDDLRecordServiceUtil.getDDLRecordsCount(ddlRecordSetId,
					userId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ScreensDDLRecordServiceSoap.class);
}