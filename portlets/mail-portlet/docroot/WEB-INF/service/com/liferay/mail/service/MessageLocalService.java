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

import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The interface for the message local service.
 *
 * <p>
 * Never modify or reference this interface directly. Always use {@link MessageLocalServiceUtil} to access the message local service. Add custom service methods to {@link com.liferay.mail.service.impl.MessageLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageLocalServiceUtil
 * @see com.liferay.mail.service.base.MessageLocalServiceBaseImpl
 * @see com.liferay.mail.service.impl.MessageLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface MessageLocalService {
	/**
	* Adds the message to the database. Also notifies the appropriate model listeners.
	*
	* @param message the message to add
	* @return the message that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Message addMessage(
		com.liferay.mail.model.Message message)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new message with the primary key. Does not add the message to the database.
	*
	* @param messageId the primary key for the new message
	* @return the new message
	*/
	public com.liferay.mail.model.Message createMessage(long messageId);

	/**
	* Deletes the message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param messageId the primary key of the message to delete
	* @throws PortalException if a message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteMessage(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the message from the database. Also notifies the appropriate model listeners.
	*
	* @param message the message to delete
	* @throws SystemException if a system exception occurred
	*/
	public void deleteMessage(com.liferay.mail.model.Message message)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Gets the message with the primary key.
	*
	* @param messageId the primary key of the message to get
	* @return the message
	* @throws PortalException if a message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.mail.model.Message getMessage(long messageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Gets a range of all the messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of messages to return
	* @param end the upper bound of the range of messages to return (not inclusive)
	* @return the range of messages
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.mail.model.Message> getMessages(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Gets the number of messages.
	*
	* @return the number of messages
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getMessagesCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the message in the database. Also notifies the appropriate model listeners.
	*
	* @param message the message to update
	* @return the message that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Message updateMessage(
		com.liferay.mail.model.Message message)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the message in the database. Also notifies the appropriate model listeners.
	*
	* @param message the message to update
	* @param merge whether to merge the message with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the message that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Message updateMessage(
		com.liferay.mail.model.Message message, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Message addMessage(long userId,
		long folderId, java.lang.String sender, java.lang.String to,
		java.lang.String cc, java.lang.String bcc, java.util.Date sentDate,
		java.lang.String subject, java.lang.String body,
		java.lang.String flags, long remoteMessageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void deleteMessages(long folderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAccountUnreadMessagesCount(long accountId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.mail.model.Message> getCompanyMessages(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCompanyMessagesCount(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.mail.model.Message> getFolderMessages(
		long folderId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getFolderMessagesCount(long folderId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getFolderUnreadMessagesCount(long folderId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.mail.model.Message getMessage(long folderId,
		long remoteMessageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.mail.model.Message getRemoteMessage(long folderId,
		boolean oldest)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public int populateMessages(
		java.util.List<com.liferay.mail.model.Message> messages, long folderId,
		java.lang.String keywords, int pageNumber, int messagesPerPage,
		java.lang.String orderByField, java.lang.String orderByType)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Message updateContent(long messageId,
		java.lang.String body, java.lang.String flags)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Message updateFlag(long messageId, int flag,
		boolean value)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Message updateMessage(long messageId,
		long folderId, java.lang.String sender, java.lang.String to,
		java.lang.String cc, java.lang.String bcc, java.util.Date sentDate,
		java.lang.String subject, java.lang.String body,
		java.lang.String flags, long remoteMessageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}