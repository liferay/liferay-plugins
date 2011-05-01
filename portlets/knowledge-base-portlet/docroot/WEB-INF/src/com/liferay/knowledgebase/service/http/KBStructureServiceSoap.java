/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.knowledgebase.service.http;

import com.liferay.knowledgebase.service.KBStructureServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link com.liferay.knowledgebase.service.KBStructureServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.knowledgebase.model.KBStructureSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.knowledgebase.model.KBStructure}, that is translated to a
 * {@link com.liferay.knowledgebase.model.KBStructureSoap}. Methods that SOAP cannot
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
 * You can see a list of services at
 * http://localhost:8080/tunnel-web/secure/axis. Set the property
 * <b>tunnel.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KBStructureServiceHttp
 * @see       com.liferay.knowledgebase.model.KBStructureSoap
 * @see       com.liferay.knowledgebase.service.KBStructureServiceUtil
 * @generated
 */
public class KBStructureServiceSoap {
	public static com.liferay.knowledgebase.model.KBStructureSoap addKBStructure(
		java.lang.String portletId, java.lang.String localizedLanguageId,
		java.lang.String title,
		java.util.List<com.liferay.knowledgebase.model.KBStructureField> kbStructureFields,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.knowledgebase.model.KBStructure returnValue = KBStructureServiceUtil.addKBStructure(portletId,
					localizedLanguageId, title, kbStructureFields,
					serviceContext);

			return com.liferay.knowledgebase.model.KBStructureSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteKBStructure(long kbStructureId)
		throws RemoteException {
		try {
			KBStructureServiceUtil.deleteKBStructure(kbStructureId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteKBStructureLocalization(long kbStructureId,
		java.lang.String localizedLanguageId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			KBStructureServiceUtil.deleteKBStructureLocalization(kbStructureId,
				localizedLanguageId, serviceContext);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteKBStructures(long groupId, long[] kbStructureIds)
		throws RemoteException {
		try {
			KBStructureServiceUtil.deleteKBStructures(groupId, kbStructureIds);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBStructureSoap[] getGroupKBStructures(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.knowledgebase.model.KBStructure> returnValue =
				KBStructureServiceUtil.getGroupKBStructures(groupId, start,
					end, orderByComparator);

			return com.liferay.knowledgebase.model.KBStructureSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getGroupKBStructuresCount(long groupId)
		throws RemoteException {
		try {
			int returnValue = KBStructureServiceUtil.getGroupKBStructuresCount(groupId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBStructureSoap getKBStructure(
		long kbStructureId) throws RemoteException {
		try {
			com.liferay.knowledgebase.model.KBStructure returnValue = KBStructureServiceUtil.getKBStructure(kbStructureId);

			return com.liferay.knowledgebase.model.KBStructureSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBStructureSearchDisplay getKBStructureSearchDisplay(
		long groupId, java.lang.String title, java.lang.String content,
		java.util.Date startDate, java.util.Date endDate, boolean andOperator,
		int[] curStartValues, int cur, int delta,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			com.liferay.knowledgebase.model.KBStructureSearchDisplay returnValue =
				KBStructureServiceUtil.getKBStructureSearchDisplay(groupId,
					title, content, startDate, endDate, andOperator,
					curStartValues, cur, delta, orderByComparator);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBStructureSoap updateKBStructure(
		long kbStructureId, java.lang.String localizedLanguageId,
		java.lang.String title,
		java.util.List<com.liferay.knowledgebase.model.KBStructureField> kbStructureFields,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.knowledgebase.model.KBStructure returnValue = KBStructureServiceUtil.updateKBStructure(kbStructureId,
					localizedLanguageId, title, kbStructureFields,
					serviceContext);

			return com.liferay.knowledgebase.model.KBStructureSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(KBStructureServiceSoap.class);
}