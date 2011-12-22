/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the message service. This utility wraps {@link MessagePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagePersistence
 * @see MessagePersistenceImpl
 * @generated
 */
public class MessageUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(Message message) {
		getPersistence().clearCache(message);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Message> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Message> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Message> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static Message update(Message message, boolean merge)
		throws SystemException {
		return getPersistence().update(message, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static Message update(Message message, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(message, merge, serviceContext);
	}

	/**
	* Caches the message in the entity cache if it is enabled.
	*
	* @param message the message
	*/
	public static void cacheResult(com.liferay.mail.model.Message message) {
		getPersistence().cacheResult(message);
	}

	/**
	* Caches the messages in the entity cache if it is enabled.
	*
	* @param messages the messages
	*/
	public static void cacheResult(
		java.util.List<com.liferay.mail.model.Message> messages) {
		getPersistence().cacheResult(messages);
	}

	/**
	* Creates a new message with the primary key. Does not add the message to the database.
	*
	* @param messageId the primary key for the new message
	* @return the new message
	*/
	public static com.liferay.mail.model.Message create(long messageId) {
		return getPersistence().create(messageId);
	}

	/**
	* Removes the message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param messageId the primary key of the message
	* @return the message that was removed
	* @throws com.liferay.mail.NoSuchMessageException if a message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Message remove(long messageId)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(messageId);
	}

	public static com.liferay.mail.model.Message updateImpl(
		com.liferay.mail.model.Message message, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(message, merge);
	}

	/**
	* Returns the message with the primary key or throws a {@link com.liferay.mail.NoSuchMessageException} if it could not be found.
	*
	* @param messageId the primary key of the message
	* @return the message
	* @throws com.liferay.mail.NoSuchMessageException if a message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Message findByPrimaryKey(
		long messageId)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(messageId);
	}

	/**
	* Returns the message with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param messageId the primary key of the message
	* @return the message, or <code>null</code> if a message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Message fetchByPrimaryKey(
		long messageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(messageId);
	}

	/**
	* Returns all the messages where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching messages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.mail.model.Message> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the messages where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of messages
	* @param end the upper bound of the range of messages (not inclusive)
	* @return the range of matching messages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.mail.model.Message> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the messages where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of messages
	* @param end the upper bound of the range of messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching messages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.mail.model.Message> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first message in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching message
	* @throws com.liferay.mail.NoSuchMessageException if a matching message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Message findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last message in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching message
	* @throws com.liferay.mail.NoSuchMessageException if a matching message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Message findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the messages before and after the current message in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param messageId the primary key of the current message
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next message
	* @throws com.liferay.mail.NoSuchMessageException if a message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Message[] findByCompanyId_PrevAndNext(
		long messageId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(messageId, companyId,
			orderByComparator);
	}

	/**
	* Returns all the messages where folderId = &#63;.
	*
	* @param folderId the folder ID
	* @return the matching messages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.mail.model.Message> findByFolderId(
		long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByFolderId(folderId);
	}

	/**
	* Returns a range of all the messages where folderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param folderId the folder ID
	* @param start the lower bound of the range of messages
	* @param end the upper bound of the range of messages (not inclusive)
	* @return the range of matching messages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.mail.model.Message> findByFolderId(
		long folderId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByFolderId(folderId, start, end);
	}

	/**
	* Returns an ordered range of all the messages where folderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param folderId the folder ID
	* @param start the lower bound of the range of messages
	* @param end the upper bound of the range of messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching messages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.mail.model.Message> findByFolderId(
		long folderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByFolderId(folderId, start, end, orderByComparator);
	}

	/**
	* Returns the first message in the ordered set where folderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param folderId the folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching message
	* @throws com.liferay.mail.NoSuchMessageException if a matching message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Message findByFolderId_First(
		long folderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByFolderId_First(folderId, orderByComparator);
	}

	/**
	* Returns the last message in the ordered set where folderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param folderId the folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching message
	* @throws com.liferay.mail.NoSuchMessageException if a matching message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Message findByFolderId_Last(
		long folderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByFolderId_Last(folderId, orderByComparator);
	}

	/**
	* Returns the messages before and after the current message in the ordered set where folderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param messageId the primary key of the current message
	* @param folderId the folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next message
	* @throws com.liferay.mail.NoSuchMessageException if a message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Message[] findByFolderId_PrevAndNext(
		long messageId, long folderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByFolderId_PrevAndNext(messageId, folderId,
			orderByComparator);
	}

	/**
	* Returns the message where folderId = &#63; and remoteMessageId = &#63; or throws a {@link com.liferay.mail.NoSuchMessageException} if it could not be found.
	*
	* @param folderId the folder ID
	* @param remoteMessageId the remote message ID
	* @return the matching message
	* @throws com.liferay.mail.NoSuchMessageException if a matching message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Message findByF_R(long folderId,
		long remoteMessageId)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByF_R(folderId, remoteMessageId);
	}

	/**
	* Returns the message where folderId = &#63; and remoteMessageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param folderId the folder ID
	* @param remoteMessageId the remote message ID
	* @return the matching message, or <code>null</code> if a matching message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Message fetchByF_R(long folderId,
		long remoteMessageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByF_R(folderId, remoteMessageId);
	}

	/**
	* Returns the message where folderId = &#63; and remoteMessageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param folderId the folder ID
	* @param remoteMessageId the remote message ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching message, or <code>null</code> if a matching message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Message fetchByF_R(long folderId,
		long remoteMessageId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByF_R(folderId, remoteMessageId, retrieveFromCache);
	}

	/**
	* Returns all the messages.
	*
	* @return the messages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.mail.model.Message> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of messages
	* @param end the upper bound of the range of messages (not inclusive)
	* @return the range of messages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.mail.model.Message> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of messages
	* @param end the upper bound of the range of messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of messages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.mail.model.Message> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the messages where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Removes all the messages where folderId = &#63; from the database.
	*
	* @param folderId the folder ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByFolderId(long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByFolderId(folderId);
	}

	/**
	* Removes the message where folderId = &#63; and remoteMessageId = &#63; from the database.
	*
	* @param folderId the folder ID
	* @param remoteMessageId the remote message ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByF_R(long folderId, long remoteMessageId)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByF_R(folderId, remoteMessageId);
	}

	/**
	* Removes all the messages from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of messages where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching messages
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns the number of messages where folderId = &#63;.
	*
	* @param folderId the folder ID
	* @return the number of matching messages
	* @throws SystemException if a system exception occurred
	*/
	public static int countByFolderId(long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByFolderId(folderId);
	}

	/**
	* Returns the number of messages where folderId = &#63; and remoteMessageId = &#63;.
	*
	* @param folderId the folder ID
	* @param remoteMessageId the remote message ID
	* @return the number of matching messages
	* @throws SystemException if a system exception occurred
	*/
	public static int countByF_R(long folderId, long remoteMessageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByF_R(folderId, remoteMessageId);
	}

	/**
	* Returns the number of messages.
	*
	* @return the number of messages
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static MessagePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (MessagePersistence)PortletBeanLocatorUtil.locate(com.liferay.mail.service.ClpSerializer.getServletContextName(),
					MessagePersistence.class.getName());

			ReferenceRegistry.registerReference(MessageUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(MessagePersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(MessageUtil.class, "_persistence");
	}

	private static MessagePersistence _persistence;
}