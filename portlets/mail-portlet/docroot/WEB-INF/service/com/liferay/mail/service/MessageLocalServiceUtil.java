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

package com.liferay.mail.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for Message. This utility wraps
 * {@link com.liferay.mail.service.impl.MessageLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see MessageLocalService
 * @see com.liferay.mail.service.base.MessageLocalServiceBaseImpl
 * @see com.liferay.mail.service.impl.MessageLocalServiceImpl
 * @generated
 */
@ProviderType
public class MessageLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.mail.service.impl.MessageLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the message to the database. Also notifies the appropriate model listeners.
	*
	* @param message the message
	* @return the message that was added
	*/
	public static com.liferay.mail.model.Message addMessage(
		com.liferay.mail.model.Message message) {
		return getService().addMessage(message);
	}

	public static com.liferay.mail.model.Message addMessage(long userId,
		long folderId, java.lang.String sender, java.lang.String to,
		java.lang.String cc, java.lang.String bcc, java.util.Date sentDate,
		java.lang.String subject, java.lang.String body,
		java.lang.String flags, long remoteMessageId,
		java.lang.String contentType)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addMessage(userId, folderId, sender, to, cc, bcc, sentDate,
			subject, body, flags, remoteMessageId, contentType);
	}

	/**
	* Creates a new message with the primary key. Does not add the message to the database.
	*
	* @param messageId the primary key for the new message
	* @return the new message
	*/
	public static com.liferay.mail.model.Message createMessage(long messageId) {
		return getService().createMessage(messageId);
	}

	/**
	* Deletes the message from the database. Also notifies the appropriate model listeners.
	*
	* @param message the message
	* @return the message that was removed
	* @throws PortalException
	*/
	public static com.liferay.mail.model.Message deleteMessage(
		com.liferay.mail.model.Message message)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteMessage(message);
	}

	/**
	* Deletes the message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param messageId the primary key of the message
	* @return the message that was removed
	* @throws PortalException if a message with the primary key could not be found
	*/
	public static com.liferay.mail.model.Message deleteMessage(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteMessage(messageId);
	}

	public static com.liferay.mail.model.Message fetchMessage(long messageId) {
		return getService().fetchMessage(messageId);
	}

	public static com.liferay.mail.model.Message getMessage(long folderId,
		long remoteMessageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getMessage(folderId, remoteMessageId);
	}

	/**
	* Returns the message with the primary key.
	*
	* @param messageId the primary key of the message
	* @return the message
	* @throws PortalException if a message with the primary key could not be found
	*/
	public static com.liferay.mail.model.Message getMessage(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getMessage(messageId);
	}

	public static com.liferay.mail.model.Message getRemoteMessage(
		long folderId, boolean oldest)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRemoteMessage(folderId, oldest);
	}

	public static com.liferay.mail.model.Message updateContent(long messageId,
		java.lang.String body, java.lang.String flags)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateContent(messageId, body, flags);
	}

	public static com.liferay.mail.model.Message updateFlag(long messageId,
		int flag, boolean value)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateFlag(messageId, flag, value);
	}

	/**
	* Updates the message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param message the message
	* @return the message that was updated
	*/
	public static com.liferay.mail.model.Message updateMessage(
		com.liferay.mail.model.Message message) {
		return getService().updateMessage(message);
	}

	public static com.liferay.mail.model.Message updateMessage(long messageId,
		long folderId, java.lang.String sender, java.lang.String to,
		java.lang.String cc, java.lang.String bcc, java.util.Date sentDate,
		java.lang.String subject, java.lang.String body,
		java.lang.String flags, long remoteMessageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateMessage(messageId, folderId, sender, to, cc, bcc,
			sentDate, subject, body, flags, remoteMessageId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static int getAccountUnreadMessagesCount(long accountId) {
		return getService().getAccountUnreadMessagesCount(accountId);
	}

	public static int getCompanyMessagesCount(long companyId) {
		return getService().getCompanyMessagesCount(companyId);
	}

	public static int getFolderMessagesCount(long folderId) {
		return getService().getFolderMessagesCount(folderId);
	}

	public static int getFolderUnreadMessagesCount(long folderId) {
		return getService().getFolderUnreadMessagesCount(folderId);
	}

	/**
	* Returns the number of messages.
	*
	* @return the number of messages
	*/
	public static int getMessagesCount() {
		return getService().getMessagesCount();
	}

	public static int populateMessages(
		java.util.List<com.liferay.mail.model.Message> messages, long folderId,
		java.lang.String keywords, int pageNumber, int messagesPerPage,
		java.lang.String orderByField, java.lang.String orderByType) {
		return getService()
				   .populateMessages(messages, folderId, keywords, pageNumber,
			messagesPerPage, orderByField, orderByType);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.mail.model.impl.MessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.mail.model.impl.MessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.mail.model.Message> getCompanyMessages(
		long companyId, int start, int end) {
		return getService().getCompanyMessages(companyId, start, end);
	}

	public static java.util.List<com.liferay.mail.model.Message> getFolderMessages(
		long folderId) {
		return getService().getFolderMessages(folderId);
	}

	/**
	* Returns a range of all the messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.mail.model.impl.MessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of messages
	* @param end the upper bound of the range of messages (not inclusive)
	* @return the range of messages
	*/
	public static java.util.List<com.liferay.mail.model.Message> getMessages(
		int start, int end) {
		return getService().getMessages(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static void deleteMessages(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteMessages(folderId);
	}

	public static void clearService() {
		_service = null;
	}

	public static MessageLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					MessageLocalService.class.getName());

			if (invokableLocalService instanceof MessageLocalService) {
				_service = (MessageLocalService)invokableLocalService;
			}
			else {
				_service = new MessageLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(MessageLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static MessageLocalService _service;
}