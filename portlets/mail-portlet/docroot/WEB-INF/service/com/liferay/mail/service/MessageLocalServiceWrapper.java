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

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MessageLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MessageLocalService
 * @generated
 */
@ProviderType
public class MessageLocalServiceWrapper implements MessageLocalService,
	ServiceWrapper<MessageLocalService> {
	public MessageLocalServiceWrapper(MessageLocalService messageLocalService) {
		_messageLocalService = messageLocalService;
	}

	/**
	* Adds the message to the database. Also notifies the appropriate model listeners.
	*
	* @param message the message
	* @return the message that was added
	*/
	@Override
	public com.liferay.mail.model.Message addMessage(
		com.liferay.mail.model.Message message) {
		return _messageLocalService.addMessage(message);
	}

	@Override
	public com.liferay.mail.model.Message addMessage(long userId,
		long folderId, java.lang.String sender, java.lang.String to,
		java.lang.String cc, java.lang.String bcc, java.util.Date sentDate,
		java.lang.String subject, java.lang.String body,
		java.lang.String flags, long remoteMessageId,
		java.lang.String contentType)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _messageLocalService.addMessage(userId, folderId, sender, to,
			cc, bcc, sentDate, subject, body, flags, remoteMessageId,
			contentType);
	}

	/**
	* Creates a new message with the primary key. Does not add the message to the database.
	*
	* @param messageId the primary key for the new message
	* @return the new message
	*/
	@Override
	public com.liferay.mail.model.Message createMessage(long messageId) {
		return _messageLocalService.createMessage(messageId);
	}

	/**
	* Deletes the message from the database. Also notifies the appropriate model listeners.
	*
	* @param message the message
	* @return the message that was removed
	* @throws PortalException
	*/
	@Override
	public com.liferay.mail.model.Message deleteMessage(
		com.liferay.mail.model.Message message)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _messageLocalService.deleteMessage(message);
	}

	/**
	* Deletes the message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param messageId the primary key of the message
	* @return the message that was removed
	* @throws PortalException if a message with the primary key could not be found
	*/
	@Override
	public com.liferay.mail.model.Message deleteMessage(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _messageLocalService.deleteMessage(messageId);
	}

	@Override
	public void deleteMessages(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_messageLocalService.deleteMessages(folderId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _messageLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _messageLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _messageLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _messageLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _messageLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _messageLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _messageLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.mail.model.Message fetchMessage(long messageId) {
		return _messageLocalService.fetchMessage(messageId);
	}

	@Override
	public int getAccountUnreadMessagesCount(long accountId) {
		return _messageLocalService.getAccountUnreadMessagesCount(accountId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _messageLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.liferay.mail.model.Message> getCompanyMessages(
		long companyId, int start, int end) {
		return _messageLocalService.getCompanyMessages(companyId, start, end);
	}

	@Override
	public int getCompanyMessagesCount(long companyId) {
		return _messageLocalService.getCompanyMessagesCount(companyId);
	}

	@Override
	public java.util.List<com.liferay.mail.model.Message> getFolderMessages(
		long folderId) {
		return _messageLocalService.getFolderMessages(folderId);
	}

	@Override
	public int getFolderMessagesCount(long folderId) {
		return _messageLocalService.getFolderMessagesCount(folderId);
	}

	@Override
	public int getFolderUnreadMessagesCount(long folderId) {
		return _messageLocalService.getFolderUnreadMessagesCount(folderId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _messageLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public com.liferay.mail.model.Message getMessage(long folderId,
		long remoteMessageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _messageLocalService.getMessage(folderId, remoteMessageId);
	}

	/**
	* Returns the message with the primary key.
	*
	* @param messageId the primary key of the message
	* @return the message
	* @throws PortalException if a message with the primary key could not be found
	*/
	@Override
	public com.liferay.mail.model.Message getMessage(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _messageLocalService.getMessage(messageId);
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
	@Override
	public java.util.List<com.liferay.mail.model.Message> getMessages(
		int start, int end) {
		return _messageLocalService.getMessages(start, end);
	}

	/**
	* Returns the number of messages.
	*
	* @return the number of messages
	*/
	@Override
	public int getMessagesCount() {
		return _messageLocalService.getMessagesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _messageLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _messageLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.mail.model.Message getRemoteMessage(long folderId,
		boolean oldest)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _messageLocalService.getRemoteMessage(folderId, oldest);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _messageLocalService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public int populateMessages(
		java.util.List<com.liferay.mail.model.Message> messages, long folderId,
		java.lang.String keywords, int pageNumber, int messagesPerPage,
		java.lang.String orderByField, java.lang.String orderByType) {
		return _messageLocalService.populateMessages(messages, folderId,
			keywords, pageNumber, messagesPerPage, orderByField, orderByType);
	}

	@Override
	public com.liferay.mail.model.Message updateContent(long messageId,
		java.lang.String body, java.lang.String flags)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _messageLocalService.updateContent(messageId, body, flags);
	}

	@Override
	public com.liferay.mail.model.Message updateFlag(long messageId, int flag,
		boolean value)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _messageLocalService.updateFlag(messageId, flag, value);
	}

	/**
	* Updates the message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param message the message
	* @return the message that was updated
	*/
	@Override
	public com.liferay.mail.model.Message updateMessage(
		com.liferay.mail.model.Message message) {
		return _messageLocalService.updateMessage(message);
	}

	@Override
	public com.liferay.mail.model.Message updateMessage(long messageId,
		long folderId, java.lang.String sender, java.lang.String to,
		java.lang.String cc, java.lang.String bcc, java.util.Date sentDate,
		java.lang.String subject, java.lang.String body,
		java.lang.String flags, long remoteMessageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _messageLocalService.updateMessage(messageId, folderId, sender,
			to, cc, bcc, sentDate, subject, body, flags, remoteMessageId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public MessageLocalService getWrappedMessageLocalService() {
		return _messageLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedMessageLocalService(
		MessageLocalService messageLocalService) {
		_messageLocalService = messageLocalService;
	}

	@Override
	public MessageLocalService getWrappedService() {
		return _messageLocalService;
	}

	@Override
	public void setWrappedService(MessageLocalService messageLocalService) {
		_messageLocalService = messageLocalService;
	}

	private MessageLocalService _messageLocalService;
}