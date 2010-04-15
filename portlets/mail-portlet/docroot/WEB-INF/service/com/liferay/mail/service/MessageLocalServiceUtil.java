/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.mail.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="MessageLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * {@link MessageLocalService} bean. The static methods of
 * this class calls the same methods of the bean instance. It's convenient to be
 * able to just write one line to call a method on a bean instead of writing a
 * lookup call and a method call.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MessageLocalService
 * @generated
 */
public class MessageLocalServiceUtil {
	public static com.liferay.mail.model.Message addMessage(
		com.liferay.mail.model.Message message)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addMessage(message);
	}

	public static com.liferay.mail.model.Message createMessage(long messageId) {
		return getService().createMessage(messageId);
	}

	public static void deleteMessage(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteMessage(messageId);
	}

	public static void deleteMessage(com.liferay.mail.model.Message message)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteMessage(message);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public static int dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.mail.model.Message getMessage(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getMessage(messageId);
	}

	public static java.util.List<com.liferay.mail.model.Message> getMessages(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getMessages(start, end);
	}

	public static int getMessagesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getMessagesCount();
	}

	public static com.liferay.mail.model.Message updateMessage(
		com.liferay.mail.model.Message message)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateMessage(message);
	}

	public static com.liferay.mail.model.Message updateMessage(
		com.liferay.mail.model.Message message, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateMessage(message, merge);
	}

	public static com.liferay.mail.model.Message addMessage(long userId,
		long folderId, java.lang.String sender, java.lang.String to,
		java.lang.String cc, java.lang.String bcc, java.util.Date sentDate,
		java.lang.String subject, java.lang.String body,
		java.lang.String flags, long size, long remoteMessageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addMessage(userId, folderId, sender, to, cc, bcc, sentDate,
			subject, body, flags, size, remoteMessageId);
	}

	public static void deleteMessages(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteMessages(folderId);
	}

	public static int getAccountUnreadMessagesCount(long accountId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountUnreadMessagesCount(accountId);
	}

	public static java.util.List<com.liferay.mail.model.Message> getCompanyMessages(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCompanyMessages(companyId, start, end);
	}

	public static int getCompanyMessagesCount(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCompanyMessagesCount(companyId);
	}

	public static java.util.List<com.liferay.mail.model.Message> getFolderMessages(
		long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFolderMessages(folderId);
	}

	public static int getFolderMessagesCount(long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFolderMessagesCount(folderId);
	}

	public static int getFolderUnreadMessagesCount(long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFolderUnreadMessagesCount(folderId);
	}

	public static com.liferay.mail.model.Message getMessage(long folderId,
		long remoteMessageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getMessage(folderId, remoteMessageId);
	}

	public static int populateMessages(
		java.util.List<com.liferay.mail.model.Message> messages, long folderId,
		java.lang.String keywords, int pageNumber, int messagesPerPage,
		java.lang.String orderByField, java.lang.String orderByType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .populateMessages(messages, folderId, keywords, pageNumber,
			messagesPerPage, orderByField, orderByType);
	}

	public static com.liferay.mail.model.Message updateFlag(long messageId,
		int flag, boolean value)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateFlag(messageId, flag, value);
	}

	public static com.liferay.mail.model.Message updateMessage(long messageId,
		long folderId, java.lang.String sender, java.lang.String to,
		java.lang.String cc, java.lang.String bcc, java.util.Date sentDate,
		java.lang.String subject, java.lang.String body,
		java.lang.String flags, long size, long remoteMessageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateMessage(messageId, folderId, sender, to, cc, bcc,
			sentDate, subject, body, flags, size, remoteMessageId);
	}

	public static com.liferay.mail.model.Message updateMessageSize(
		long messageId, long size)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateMessageSize(messageId, size);
	}

	public static void clearService() {
		_service = null;
	}

	public static MessageLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					MessageLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					MessageLocalService.class.getName(), portletClassLoader);

			_service = new MessageLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(MessageLocalService service) {
		_service = service;
	}

	private static MessageLocalService _service;
}