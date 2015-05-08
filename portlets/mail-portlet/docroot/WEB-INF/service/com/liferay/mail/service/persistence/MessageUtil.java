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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the message service. This utility wraps {@link com.liferay.mail.service.persistence.impl.MessagePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagePersistence
 * @see com.liferay.mail.service.persistence.impl.MessagePersistenceImpl
 * @generated
 */
@ProviderType
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
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Message> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Message> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Message> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Message> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static Message update(Message message) {
		return getPersistence().update(message);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static Message update(Message message, ServiceContext serviceContext) {
		return getPersistence().update(message, serviceContext);
	}

	/**
	* Returns all the messages where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching messages
	*/
	public static List<Message> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

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
	public static List<Message> findByCompanyId(long companyId, int start,
		int end) {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

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
	public static List<Message> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<Message> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first message in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching message
	* @throws NoSuchMessageException if a matching message could not be found
	*/
	public static Message findByCompanyId_First(long companyId,
		OrderByComparator<Message> orderByComparator)
		throws com.liferay.mail.NoSuchMessageException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first message in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching message, or <code>null</code> if a matching message could not be found
	*/
	public static Message fetchByCompanyId_First(long companyId,
		OrderByComparator<Message> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last message in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching message
	* @throws NoSuchMessageException if a matching message could not be found
	*/
	public static Message findByCompanyId_Last(long companyId,
		OrderByComparator<Message> orderByComparator)
		throws com.liferay.mail.NoSuchMessageException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last message in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching message, or <code>null</code> if a matching message could not be found
	*/
	public static Message fetchByCompanyId_Last(long companyId,
		OrderByComparator<Message> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the messages before and after the current message in the ordered set where companyId = &#63;.
	*
	* @param messageId the primary key of the current message
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next message
	* @throws NoSuchMessageException if a message with the primary key could not be found
	*/
	public static Message[] findByCompanyId_PrevAndNext(long messageId,
		long companyId, OrderByComparator<Message> orderByComparator)
		throws com.liferay.mail.NoSuchMessageException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(messageId, companyId,
			orderByComparator);
	}

	/**
	* Removes all the messages where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of messages where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching messages
	*/
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns all the messages where folderId = &#63;.
	*
	* @param folderId the folder ID
	* @return the matching messages
	*/
	public static List<Message> findByFolderId(long folderId) {
		return getPersistence().findByFolderId(folderId);
	}

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
	public static List<Message> findByFolderId(long folderId, int start, int end) {
		return getPersistence().findByFolderId(folderId, start, end);
	}

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
	public static List<Message> findByFolderId(long folderId, int start,
		int end, OrderByComparator<Message> orderByComparator) {
		return getPersistence()
				   .findByFolderId(folderId, start, end, orderByComparator);
	}

	/**
	* Returns the first message in the ordered set where folderId = &#63;.
	*
	* @param folderId the folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching message
	* @throws NoSuchMessageException if a matching message could not be found
	*/
	public static Message findByFolderId_First(long folderId,
		OrderByComparator<Message> orderByComparator)
		throws com.liferay.mail.NoSuchMessageException {
		return getPersistence().findByFolderId_First(folderId, orderByComparator);
	}

	/**
	* Returns the first message in the ordered set where folderId = &#63;.
	*
	* @param folderId the folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching message, or <code>null</code> if a matching message could not be found
	*/
	public static Message fetchByFolderId_First(long folderId,
		OrderByComparator<Message> orderByComparator) {
		return getPersistence()
				   .fetchByFolderId_First(folderId, orderByComparator);
	}

	/**
	* Returns the last message in the ordered set where folderId = &#63;.
	*
	* @param folderId the folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching message
	* @throws NoSuchMessageException if a matching message could not be found
	*/
	public static Message findByFolderId_Last(long folderId,
		OrderByComparator<Message> orderByComparator)
		throws com.liferay.mail.NoSuchMessageException {
		return getPersistence().findByFolderId_Last(folderId, orderByComparator);
	}

	/**
	* Returns the last message in the ordered set where folderId = &#63;.
	*
	* @param folderId the folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching message, or <code>null</code> if a matching message could not be found
	*/
	public static Message fetchByFolderId_Last(long folderId,
		OrderByComparator<Message> orderByComparator) {
		return getPersistence().fetchByFolderId_Last(folderId, orderByComparator);
	}

	/**
	* Returns the messages before and after the current message in the ordered set where folderId = &#63;.
	*
	* @param messageId the primary key of the current message
	* @param folderId the folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next message
	* @throws NoSuchMessageException if a message with the primary key could not be found
	*/
	public static Message[] findByFolderId_PrevAndNext(long messageId,
		long folderId, OrderByComparator<Message> orderByComparator)
		throws com.liferay.mail.NoSuchMessageException {
		return getPersistence()
				   .findByFolderId_PrevAndNext(messageId, folderId,
			orderByComparator);
	}

	/**
	* Removes all the messages where folderId = &#63; from the database.
	*
	* @param folderId the folder ID
	*/
	public static void removeByFolderId(long folderId) {
		getPersistence().removeByFolderId(folderId);
	}

	/**
	* Returns the number of messages where folderId = &#63;.
	*
	* @param folderId the folder ID
	* @return the number of matching messages
	*/
	public static int countByFolderId(long folderId) {
		return getPersistence().countByFolderId(folderId);
	}

	/**
	* Returns the message where folderId = &#63; and remoteMessageId = &#63; or throws a {@link NoSuchMessageException} if it could not be found.
	*
	* @param folderId the folder ID
	* @param remoteMessageId the remote message ID
	* @return the matching message
	* @throws NoSuchMessageException if a matching message could not be found
	*/
	public static Message findByF_R(long folderId, long remoteMessageId)
		throws com.liferay.mail.NoSuchMessageException {
		return getPersistence().findByF_R(folderId, remoteMessageId);
	}

	/**
	* Returns the message where folderId = &#63; and remoteMessageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param folderId the folder ID
	* @param remoteMessageId the remote message ID
	* @return the matching message, or <code>null</code> if a matching message could not be found
	*/
	public static Message fetchByF_R(long folderId, long remoteMessageId) {
		return getPersistence().fetchByF_R(folderId, remoteMessageId);
	}

	/**
	* Returns the message where folderId = &#63; and remoteMessageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param folderId the folder ID
	* @param remoteMessageId the remote message ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching message, or <code>null</code> if a matching message could not be found
	*/
	public static Message fetchByF_R(long folderId, long remoteMessageId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_R(folderId, remoteMessageId, retrieveFromCache);
	}

	/**
	* Removes the message where folderId = &#63; and remoteMessageId = &#63; from the database.
	*
	* @param folderId the folder ID
	* @param remoteMessageId the remote message ID
	* @return the message that was removed
	*/
	public static Message removeByF_R(long folderId, long remoteMessageId)
		throws com.liferay.mail.NoSuchMessageException {
		return getPersistence().removeByF_R(folderId, remoteMessageId);
	}

	/**
	* Returns the number of messages where folderId = &#63; and remoteMessageId = &#63;.
	*
	* @param folderId the folder ID
	* @param remoteMessageId the remote message ID
	* @return the number of matching messages
	*/
	public static int countByF_R(long folderId, long remoteMessageId) {
		return getPersistence().countByF_R(folderId, remoteMessageId);
	}

	/**
	* Caches the message in the entity cache if it is enabled.
	*
	* @param message the message
	*/
	public static void cacheResult(Message message) {
		getPersistence().cacheResult(message);
	}

	/**
	* Caches the messages in the entity cache if it is enabled.
	*
	* @param messages the messages
	*/
	public static void cacheResult(List<Message> messages) {
		getPersistence().cacheResult(messages);
	}

	/**
	* Creates a new message with the primary key. Does not add the message to the database.
	*
	* @param messageId the primary key for the new message
	* @return the new message
	*/
	public static Message create(long messageId) {
		return getPersistence().create(messageId);
	}

	/**
	* Removes the message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param messageId the primary key of the message
	* @return the message that was removed
	* @throws NoSuchMessageException if a message with the primary key could not be found
	*/
	public static Message remove(long messageId)
		throws com.liferay.mail.NoSuchMessageException {
		return getPersistence().remove(messageId);
	}

	public static Message updateImpl(Message message) {
		return getPersistence().updateImpl(message);
	}

	/**
	* Returns the message with the primary key or throws a {@link NoSuchMessageException} if it could not be found.
	*
	* @param messageId the primary key of the message
	* @return the message
	* @throws NoSuchMessageException if a message with the primary key could not be found
	*/
	public static Message findByPrimaryKey(long messageId)
		throws com.liferay.mail.NoSuchMessageException {
		return getPersistence().findByPrimaryKey(messageId);
	}

	/**
	* Returns the message with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param messageId the primary key of the message
	* @return the message, or <code>null</code> if a message with the primary key could not be found
	*/
	public static Message fetchByPrimaryKey(long messageId) {
		return getPersistence().fetchByPrimaryKey(messageId);
	}

	public static java.util.Map<java.io.Serializable, Message> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the messages.
	*
	* @return the messages
	*/
	public static List<Message> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<Message> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<Message> findAll(int start, int end,
		OrderByComparator<Message> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the messages from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of messages.
	*
	* @return the number of messages
	*/
	public static int countAll() {
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

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(MessagePersistence persistence) {
	}

	private static MessagePersistence _persistence;
}