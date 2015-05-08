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

package com.liferay.knowledgebase.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.knowledgebase.service.KBTemplateServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link KBTemplateServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.knowledgebase.model.KBTemplateSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.knowledgebase.model.KBTemplate}, that is translated to a
 * {@link com.liferay.knowledgebase.model.KBTemplateSoap}. Methods that SOAP cannot
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
 * @author Brian Wing Shun Chan
 * @see KBTemplateServiceHttp
 * @see com.liferay.knowledgebase.model.KBTemplateSoap
 * @see KBTemplateServiceUtil
 * @generated
 */
@ProviderType
public class KBTemplateServiceSoap {
	public static com.liferay.knowledgebase.model.KBTemplateSoap addKBTemplate(
		java.lang.String portletId, java.lang.String title,
		java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.knowledgebase.model.KBTemplate returnValue = KBTemplateServiceUtil.addKBTemplate(portletId,
					title, content, serviceContext);

			return com.liferay.knowledgebase.model.KBTemplateSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBTemplateSoap deleteKBTemplate(
		long kbTemplateId) throws RemoteException {
		try {
			com.liferay.knowledgebase.model.KBTemplate returnValue = KBTemplateServiceUtil.deleteKBTemplate(kbTemplateId);

			return com.liferay.knowledgebase.model.KBTemplateSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteKBTemplates(long groupId, long[] kbTemplateIds)
		throws RemoteException {
		try {
			KBTemplateServiceUtil.deleteKBTemplates(groupId, kbTemplateIds);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBTemplateSoap[] getGroupKBTemplates(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledgebase.model.KBTemplate> orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.knowledgebase.model.KBTemplate> returnValue =
				KBTemplateServiceUtil.getGroupKBTemplates(groupId, start, end,
					orderByComparator);

			return com.liferay.knowledgebase.model.KBTemplateSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getGroupKBTemplatesCount(long groupId)
		throws RemoteException {
		try {
			int returnValue = KBTemplateServiceUtil.getGroupKBTemplatesCount(groupId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBTemplateSoap getKBTemplate(
		long kbTemplateId) throws RemoteException {
		try {
			com.liferay.knowledgebase.model.KBTemplate returnValue = KBTemplateServiceUtil.getKBTemplate(kbTemplateId);

			return com.liferay.knowledgebase.model.KBTemplateSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBTemplateSearchDisplay getKBTemplateSearchDisplay(
		long groupId, java.lang.String title, java.lang.String content,
		java.util.Date startDate, java.util.Date endDate, boolean andOperator,
		int[] curStartValues, int cur, int delta,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledgebase.model.KBTemplate> orderByComparator)
		throws RemoteException {
		try {
			com.liferay.knowledgebase.model.KBTemplateSearchDisplay returnValue = KBTemplateServiceUtil.getKBTemplateSearchDisplay(groupId,
					title, content, startDate, endDate, andOperator,
					curStartValues, cur, delta, orderByComparator);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBTemplateSoap updateKBTemplate(
		long kbTemplateId, java.lang.String title, java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.knowledgebase.model.KBTemplate returnValue = KBTemplateServiceUtil.updateKBTemplate(kbTemplateId,
					title, content, serviceContext);

			return com.liferay.knowledgebase.model.KBTemplateSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(KBTemplateServiceSoap.class);
}