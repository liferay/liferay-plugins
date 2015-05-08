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

import com.liferay.knowledgebase.service.KBCommentServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link KBCommentServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.knowledgebase.model.KBCommentSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.knowledgebase.model.KBComment}, that is translated to a
 * {@link com.liferay.knowledgebase.model.KBCommentSoap}. Methods that SOAP cannot
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
 * @see KBCommentServiceHttp
 * @see com.liferay.knowledgebase.model.KBCommentSoap
 * @see KBCommentServiceUtil
 * @generated
 */
@ProviderType
public class KBCommentServiceSoap {
	public static com.liferay.knowledgebase.model.KBCommentSoap deleteKBComment(
		com.liferay.knowledgebase.model.KBCommentSoap kbComment)
		throws RemoteException {
		try {
			com.liferay.knowledgebase.model.KBComment returnValue = KBCommentServiceUtil.deleteKBComment(com.liferay.knowledgebase.model.impl.KBCommentModelImpl.toModel(
						kbComment));

			return com.liferay.knowledgebase.model.KBCommentSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBCommentSoap deleteKBComment(
		long kbCommentId) throws RemoteException {
		try {
			com.liferay.knowledgebase.model.KBComment returnValue = KBCommentServiceUtil.deleteKBComment(kbCommentId);

			return com.liferay.knowledgebase.model.KBCommentSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBCommentSoap getKBComment(
		long kbCommentId) throws RemoteException {
		try {
			com.liferay.knowledgebase.model.KBComment returnValue = KBCommentServiceUtil.getKBComment(kbCommentId);

			return com.liferay.knowledgebase.model.KBCommentSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBCommentSoap[] getKBComments(
		long groupId, int status, int start, int end) throws RemoteException {
		try {
			java.util.List<com.liferay.knowledgebase.model.KBComment> returnValue =
				KBCommentServiceUtil.getKBComments(groupId, status, start, end);

			return com.liferay.knowledgebase.model.KBCommentSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBCommentSoap[] getKBComments(
		long groupId, java.lang.String className, long classPK, int status,
		int start, int end) throws RemoteException {
		try {
			java.util.List<com.liferay.knowledgebase.model.KBComment> returnValue =
				KBCommentServiceUtil.getKBComments(groupId, className, classPK,
					status, start, end);

			return com.liferay.knowledgebase.model.KBCommentSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getKBCommentsCount(long groupId, int status)
		throws RemoteException {
		try {
			int returnValue = KBCommentServiceUtil.getKBCommentsCount(groupId,
					status);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getKBCommentsCount(long groupId,
		java.lang.String className, long classPK, int status)
		throws RemoteException {
		try {
			int returnValue = KBCommentServiceUtil.getKBCommentsCount(groupId,
					className, classPK, status);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBCommentSoap updateKBComment(
		long kbCommentId, long classNameId, long classPK,
		java.lang.String content, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.knowledgebase.model.KBComment returnValue = KBCommentServiceUtil.updateKBComment(kbCommentId,
					classNameId, classPK, content, status, serviceContext);

			return com.liferay.knowledgebase.model.KBCommentSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBCommentSoap updateKBComment(
		long kbCommentId, long classNameId, long classPK,
		java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.knowledgebase.model.KBComment returnValue = KBCommentServiceUtil.updateKBComment(kbCommentId,
					classNameId, classPK, content, serviceContext);

			return com.liferay.knowledgebase.model.KBCommentSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.knowledgebase.model.KBCommentSoap updateStatus(
		long kbCommentId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.knowledgebase.model.KBComment returnValue = KBCommentServiceUtil.updateStatus(kbCommentId,
					status, serviceContext);

			return com.liferay.knowledgebase.model.KBCommentSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(KBCommentServiceSoap.class);
}