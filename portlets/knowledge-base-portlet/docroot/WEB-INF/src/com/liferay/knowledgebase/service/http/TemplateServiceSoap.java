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

import com.liferay.knowledgebase.service.TemplateServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link com.liferay.knowledgebase.service.TemplateServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.knowledgebase.model.TemplateSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.knowledgebase.model.Template}, that is translated to a
 * {@link com.liferay.knowledgebase.model.TemplateSoap}. Methods that SOAP cannot
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
 * @see       TemplateServiceHttp
 * @see       com.liferay.knowledgebase.model.TemplateSoap
 * @see       com.liferay.knowledgebase.service.TemplateServiceUtil
 * @generated
 */
public class TemplateServiceSoap {
	public static com.liferay.knowledgebase.model.TemplateSoap addTemplate(
		java.lang.String title, java.lang.String content,
		java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.knowledgebase.model.Template returnValue = TemplateServiceUtil.addTemplate(title,
					content, description, serviceContext);

			return com.liferay.knowledgebase.model.TemplateSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteTemplate(long templateId)
		throws RemoteException {
		try {
			TemplateServiceUtil.deleteTemplate(templateId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.TemplateSoap[] getGroupTemplates(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.knowledgebase.model.Template> returnValue =
				TemplateServiceUtil.getGroupTemplates(groupId, start, end,
					orderByComparator);

			return com.liferay.knowledgebase.model.TemplateSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getGroupTemplatesCount(long groupId)
		throws RemoteException {
		try {
			int returnValue = TemplateServiceUtil.getGroupTemplatesCount(groupId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.TemplateSoap getTemplate(
		long templateId) throws RemoteException {
		try {
			com.liferay.knowledgebase.model.Template returnValue = TemplateServiceUtil.getTemplate(templateId);

			return com.liferay.knowledgebase.model.TemplateSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.TemplateSearchDisplay getTemplateSearchDisplay(
		long groupId, java.lang.String title, java.lang.String content,
		java.util.Date startDate, java.util.Date endDate, boolean andOperator,
		int[] curStartValues, int cur, int delta,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			com.liferay.knowledgebase.model.TemplateSearchDisplay returnValue = TemplateServiceUtil.getTemplateSearchDisplay(groupId,
					title, content, startDate, endDate, andOperator,
					curStartValues, cur, delta, orderByComparator);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.TemplateSoap updateTemplate(
		long templateId, java.lang.String title, java.lang.String content,
		java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.knowledgebase.model.Template returnValue = TemplateServiceUtil.updateTemplate(templateId,
					title, content, description, serviceContext);

			return com.liferay.knowledgebase.model.TemplateSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TemplateServiceSoap.class);
}