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

package com.liferay.skinny.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.liferay.skinny.service.SkinnyServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link SkinnyServiceUtil} service utility. The
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
 * @author Brian Wing Shun Chan
 * @see SkinnyServiceHttp
 * @see SkinnyServiceUtil
 * @generated
 */
@ProviderType
public class SkinnyServiceSoap {
	public static com.liferay.skinny.model.SkinnyDDLRecord[] getSkinnyDDLRecords(
		long ddlRecordSetId) throws RemoteException {
		try {
			java.util.List<com.liferay.skinny.model.SkinnyDDLRecord> returnValue =
				SkinnyServiceUtil.getSkinnyDDLRecords(ddlRecordSetId);

			return returnValue.toArray(new com.liferay.skinny.model.SkinnyDDLRecord[returnValue.size()]);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.skinny.model.SkinnyJournalArticle[] getSkinnyJournalArticles(
		long companyId, java.lang.String groupName, long ddmStructureId,
		java.lang.String locale) throws RemoteException {
		try {
			java.util.List<com.liferay.skinny.model.SkinnyJournalArticle> returnValue =
				SkinnyServiceUtil.getSkinnyJournalArticles(companyId,
					groupName, ddmStructureId, locale);

			return returnValue.toArray(new com.liferay.skinny.model.SkinnyJournalArticle[returnValue.size()]);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(SkinnyServiceSoap.class);
}