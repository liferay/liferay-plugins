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

package com.liferay.mail.service.persistence;

import com.liferay.mail.model.Message;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the message service.
 *
 * <p>
 * Never modify or reference this interface directly. Always use {@link MessageUtil} to access the message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagePersistenceImpl
 * @see MessageUtil
 * @generated
 */
public interface MessagePersistence extends BasePersistence<Message> {
	/**
	* Caches the message in the entity cache if it is enabled.
	*
	* @param message the message to cache
	*/
	public void cacheResult(com.liferay.mail.model.Message message);

	/**
	* Caches the messages in the entity cache if it is enabled.
	*
	* @param messages the messages to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.mail.model.Message> messages);

	/**
	* Creates a new message with the primary key. Does not add the message to the database.
	*
	* @param messageId the primary key for the new message
	* @return the new message
	*/
	public com.liferay.mail.model.Message create(long messageId);

	/**
	* Removes the message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param messageId the primary key of the message to remove
	* @return the message that was removed
	* @throws com.liferay.mail.NoSuchMessageException if a message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Message remove(long messageId)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Message updateImpl(
		com.liferay.mail.model.Message message, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the message with the primary key or throws a {@link com.liferay.mail.NoSuchMessageException} if it could not be found.
	*
	* @param messageId the primary key of the message to find
	* @return the message
	* @throws com.liferay.mail.NoSuchMessageException if a message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Message findByPrimaryKey(long messageId)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the message with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param messageId the primary key of the message to find
	* @return the message, or <code>null</code> if a message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Message fetchByPrimaryKey(long messageId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the messages where companyId = &#63;.
	*
	* @param companyId the company id to search with
	* @return the matching messages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.mail.model.Message> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the messages where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param start the lower bound of the range of messages to return
	* @param end the upper bound of the range of messages to return (not inclusive)
	* @return the range of matching messages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.mail.model.Message> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the messages where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param start the lower bound of the range of messages to return
	* @param end the upper bound of the range of messages to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching messages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.mail.model.Message> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first message in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching message
	* @throws com.liferay.mail.NoSuchMessageException if a matching message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Message findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last message in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching message
	* @throws com.liferay.mail.NoSuchMessageException if a matching message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Message findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the messages before and after the current message in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param messageId the primary key of the current message
	* @param companyId the company id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next message
	* @throws com.liferay.mail.NoSuchMessageException if a message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Message[] findByCompanyId_PrevAndNext(
		long messageId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the messages where folderId = &#63;.
	*
	* @param folderId the folder id to search with
	* @return the matching messages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.mail.model.Message> findByFolderId(
		long folderId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the messages where folderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param folderId the folder id to search with
	* @param start the lower bound of the range of messages to return
	* @param end the upper bound of the range of messages to return (not inclusive)
	* @return the range of matching messages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.mail.model.Message> findByFolderId(
		long folderId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the messages where folderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param folderId the folder id to search with
	* @param start the lower bound of the range of messages to return
	* @param end the upper bound of the range of messages to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching messages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.mail.model.Message> findByFolderId(
		long folderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first message in the ordered set where folderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param folderId the folder id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching message
	* @throws com.liferay.mail.NoSuchMessageException if a matching message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Message findByFolderId_First(long folderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last message in the ordered set where folderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param folderId the folder id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching message
	* @throws com.liferay.mail.NoSuchMessageException if a matching message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Message findByFolderId_Last(long folderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the messages before and after the current message in the ordered set where folderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param messageId the primary key of the current message
	* @param folderId the folder id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next message
	* @throws com.liferay.mail.NoSuchMessageException if a message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Message[] findByFolderId_PrevAndNext(
		long messageId, long folderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the message where folderId = &#63; and remoteMessageId = &#63; or throws a {@link com.liferay.mail.NoSuchMessageException} if it could not be found.
	*
	* @param folderId the folder id to search with
	* @param remoteMessageId the remote message id to search with
	* @return the matching message
	* @throws com.liferay.mail.NoSuchMessageException if a matching message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Message findByF_R(long folderId,
		long remoteMessageId)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the message where folderId = &#63; and remoteMessageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param folderId the folder id to search with
	* @param remoteMessageId the remote message id to search with
	* @return the matching message, or <code>null</code> if a matching message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Message fetchByF_R(long folderId,
		long remoteMessageId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the message where folderId = &#63; and remoteMessageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param folderId the folder id to search with
	* @param remoteMessageId the remote message id to search with
	* @return the matching message, or <code>null</code> if a matching message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Message fetchByF_R(long folderId,
		long remoteMessageId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the messages.
	*
	* @return the messages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.mail.model.Message> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the messages.
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
	public java.util.List<com.liferay.mail.model.Message> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of messages to return
	* @param end the upper bound of the range of messages to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of messages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.mail.model.Message> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the messages where companyId = &#63; from the database.
	*
	* @param companyId the company id to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the messages where folderId = &#63; from the database.
	*
	* @param folderId the folder id to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByFolderId(long folderId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the message where folderId = &#63; and remoteMessageId = &#63; from the database.
	*
	* @param folderId the folder id to search with
	* @param remoteMessageId the remote message id to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByF_R(long folderId, long remoteMessageId)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the messages from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the messages where companyId = &#63;.
	*
	* @param companyId the company id to search with
	* @return the number of matching messages
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the messages where folderId = &#63;.
	*
	* @param folderId the folder id to search with
	* @return the number of matching messages
	* @throws SystemException if a system exception occurred
	*/
	public int countByFolderId(long folderId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the messages where folderId = &#63; and remoteMessageId = &#63;.
	*
	* @param folderId the folder id to search with
	* @param remoteMessageId the remote message id to search with
	* @return the number of matching messages
	* @throws SystemException if a system exception occurred
	*/
	public int countByF_R(long folderId, long remoteMessageId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the messages.
	*
	* @return the number of messages
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}