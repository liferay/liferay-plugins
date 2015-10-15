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

package com.liferay.mail.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.mail.model.Message;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the message service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.mail.service.persistence.impl.MessagePersistenceImpl
 * @see MessageUtil
 * @generated
 */
@ProviderType
public interface MessagePersistence extends BasePersistence<Message> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MessageUtil} to access the message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the messages where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching messages
	*/
	public java.util.List<Message> findByCompanyId(long companyId);

	/**
	* Returns a range of all the messages where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of messages
	* @param end the upper bound of the range of messages (not inclusive)
	* @return the range of matching messages
	*/
	public java.util.List<Message> findByCompanyId(long companyId, int start,
		int end);

	/**
	* Returns an ordered range of all the messages where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of messages
	* @param end the upper bound of the range of messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching messages
	*/
	public java.util.List<Message> findByCompanyId(long companyId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Message> orderByComparator);

	/**
	* Returns an ordered range of all the messages where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of messages
	* @param end the upper bound of the range of messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching messages
	*/
	public java.util.List<Message> findByCompanyId(long companyId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Message> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first message in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching message
	* @throws NoSuchMessageException if a matching message could not be found
	*/
	public Message findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Message> orderByComparator)
		throws com.liferay.mail.NoSuchMessageException;

	/**
	* Returns the first message in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching message, or <code>null</code> if a matching message could not be found
	*/
	public Message fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Message> orderByComparator);

	/**
	* Returns the last message in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching message
	* @throws NoSuchMessageException if a matching message could not be found
	*/
	public Message findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Message> orderByComparator)
		throws com.liferay.mail.NoSuchMessageException;

	/**
	* Returns the last message in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching message, or <code>null</code> if a matching message could not be found
	*/
	public Message fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Message> orderByComparator);

	/**
	* Returns the messages before and after the current message in the ordered set where companyId = &#63;.
	*
	* @param messageId the primary key of the current message
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next message
	* @throws NoSuchMessageException if a message with the primary key could not be found
	*/
	public Message[] findByCompanyId_PrevAndNext(long messageId,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Message> orderByComparator)
		throws com.liferay.mail.NoSuchMessageException;

	/**
	* Removes all the messages where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of messages where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching messages
	*/
	public int countByCompanyId(long companyId);

	/**
	* Returns all the messages where folderId = &#63;.
	*
	* @param folderId the folder ID
	* @return the matching messages
	*/
	public java.util.List<Message> findByFolderId(long folderId);

	/**
	* Returns a range of all the messages where folderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param folderId the folder ID
	* @param start the lower bound of the range of messages
	* @param end the upper bound of the range of messages (not inclusive)
	* @return the range of matching messages
	*/
	public java.util.List<Message> findByFolderId(long folderId, int start,
		int end);

	/**
	* Returns an ordered range of all the messages where folderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param folderId the folder ID
	* @param start the lower bound of the range of messages
	* @param end the upper bound of the range of messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching messages
	*/
	public java.util.List<Message> findByFolderId(long folderId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Message> orderByComparator);

	/**
	* Returns an ordered range of all the messages where folderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param folderId the folder ID
	* @param start the lower bound of the range of messages
	* @param end the upper bound of the range of messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching messages
	*/
	public java.util.List<Message> findByFolderId(long folderId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Message> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first message in the ordered set where folderId = &#63;.
	*
	* @param folderId the folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching message
	* @throws NoSuchMessageException if a matching message could not be found
	*/
	public Message findByFolderId_First(long folderId,
		com.liferay.portal.kernel.util.OrderByComparator<Message> orderByComparator)
		throws com.liferay.mail.NoSuchMessageException;

	/**
	* Returns the first message in the ordered set where folderId = &#63;.
	*
	* @param folderId the folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching message, or <code>null</code> if a matching message could not be found
	*/
	public Message fetchByFolderId_First(long folderId,
		com.liferay.portal.kernel.util.OrderByComparator<Message> orderByComparator);

	/**
	* Returns the last message in the ordered set where folderId = &#63;.
	*
	* @param folderId the folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching message
	* @throws NoSuchMessageException if a matching message could not be found
	*/
	public Message findByFolderId_Last(long folderId,
		com.liferay.portal.kernel.util.OrderByComparator<Message> orderByComparator)
		throws com.liferay.mail.NoSuchMessageException;

	/**
	* Returns the last message in the ordered set where folderId = &#63;.
	*
	* @param folderId the folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching message, or <code>null</code> if a matching message could not be found
	*/
	public Message fetchByFolderId_Last(long folderId,
		com.liferay.portal.kernel.util.OrderByComparator<Message> orderByComparator);

	/**
	* Returns the messages before and after the current message in the ordered set where folderId = &#63;.
	*
	* @param messageId the primary key of the current message
	* @param folderId the folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next message
	* @throws NoSuchMessageException if a message with the primary key could not be found
	*/
	public Message[] findByFolderId_PrevAndNext(long messageId, long folderId,
		com.liferay.portal.kernel.util.OrderByComparator<Message> orderByComparator)
		throws com.liferay.mail.NoSuchMessageException;

	/**
	* Removes all the messages where folderId = &#63; from the database.
	*
	* @param folderId the folder ID
	*/
	public void removeByFolderId(long folderId);

	/**
	* Returns the number of messages where folderId = &#63;.
	*
	* @param folderId the folder ID
	* @return the number of matching messages
	*/
	public int countByFolderId(long folderId);

	/**
	* Returns the message where folderId = &#63; and remoteMessageId = &#63; or throws a {@link NoSuchMessageException} if it could not be found.
	*
	* @param folderId the folder ID
	* @param remoteMessageId the remote message ID
	* @return the matching message
	* @throws NoSuchMessageException if a matching message could not be found
	*/
	public Message findByF_R(long folderId, long remoteMessageId)
		throws com.liferay.mail.NoSuchMessageException;

	/**
	* Returns the message where folderId = &#63; and remoteMessageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param folderId the folder ID
	* @param remoteMessageId the remote message ID
	* @return the matching message, or <code>null</code> if a matching message could not be found
	*/
	public Message fetchByF_R(long folderId, long remoteMessageId);

	/**
	* Returns the message where folderId = &#63; and remoteMessageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param folderId the folder ID
	* @param remoteMessageId the remote message ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching message, or <code>null</code> if a matching message could not be found
	*/
	public Message fetchByF_R(long folderId, long remoteMessageId,
		boolean retrieveFromCache);

	/**
	* Removes the message where folderId = &#63; and remoteMessageId = &#63; from the database.
	*
	* @param folderId the folder ID
	* @param remoteMessageId the remote message ID
	* @return the message that was removed
	*/
	public Message removeByF_R(long folderId, long remoteMessageId)
		throws com.liferay.mail.NoSuchMessageException;

	/**
	* Returns the number of messages where folderId = &#63; and remoteMessageId = &#63;.
	*
	* @param folderId the folder ID
	* @param remoteMessageId the remote message ID
	* @return the number of matching messages
	*/
	public int countByF_R(long folderId, long remoteMessageId);

	/**
	* Caches the message in the entity cache if it is enabled.
	*
	* @param message the message
	*/
	public void cacheResult(Message message);

	/**
	* Caches the messages in the entity cache if it is enabled.
	*
	* @param messages the messages
	*/
	public void cacheResult(java.util.List<Message> messages);

	/**
	* Creates a new message with the primary key. Does not add the message to the database.
	*
	* @param messageId the primary key for the new message
	* @return the new message
	*/
	public Message create(long messageId);

	/**
	* Removes the message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param messageId the primary key of the message
	* @return the message that was removed
	* @throws NoSuchMessageException if a message with the primary key could not be found
	*/
	public Message remove(long messageId)
		throws com.liferay.mail.NoSuchMessageException;

	public Message updateImpl(Message message);

	/**
	* Returns the message with the primary key or throws a {@link NoSuchMessageException} if it could not be found.
	*
	* @param messageId the primary key of the message
	* @return the message
	* @throws NoSuchMessageException if a message with the primary key could not be found
	*/
	public Message findByPrimaryKey(long messageId)
		throws com.liferay.mail.NoSuchMessageException;

	/**
	* Returns the message with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param messageId the primary key of the message
	* @return the message, or <code>null</code> if a message with the primary key could not be found
	*/
	public Message fetchByPrimaryKey(long messageId);

	@Override
	public java.util.Map<java.io.Serializable, Message> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the messages.
	*
	* @return the messages
	*/
	public java.util.List<Message> findAll();

	/**
	* Returns a range of all the messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of messages
	* @param end the upper bound of the range of messages (not inclusive)
	* @return the range of messages
	*/
	public java.util.List<Message> findAll(int start, int end);

	/**
	* Returns an ordered range of all the messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of messages
	* @param end the upper bound of the range of messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of messages
	*/
	public java.util.List<Message> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Message> orderByComparator);

	/**
	* Returns an ordered range of all the messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of messages
	* @param end the upper bound of the range of messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of messages
	*/
	public java.util.List<Message> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Message> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the messages from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of messages.
	*
	* @return the number of messages
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}