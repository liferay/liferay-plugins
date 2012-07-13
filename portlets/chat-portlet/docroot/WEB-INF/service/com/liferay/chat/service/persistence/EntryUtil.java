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

package com.liferay.chat.service.persistence;

import com.liferay.chat.model.Entry;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the entry service. This utility wraps {@link EntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntryPersistence
 * @see EntryPersistenceImpl
 * @generated
 */
public class EntryUtil {
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
	public static void clearCache(Entry entry) {
		getPersistence().clearCache(entry);
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
	public static List<Entry> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Entry> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Entry> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static Entry update(Entry entry, boolean merge)
		throws SystemException {
		return getPersistence().update(entry, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static Entry update(Entry entry, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(entry, merge, serviceContext);
	}

	/**
	* Caches the entry in the entity cache if it is enabled.
	*
	* @param entry the entry
	*/
	public static void cacheResult(com.liferay.chat.model.Entry entry) {
		getPersistence().cacheResult(entry);
	}

	/**
	* Caches the entries in the entity cache if it is enabled.
	*
	* @param entries the entries
	*/
	public static void cacheResult(
		java.util.List<com.liferay.chat.model.Entry> entries) {
		getPersistence().cacheResult(entries);
	}

	/**
	* Creates a new entry with the primary key. Does not add the entry to the database.
	*
	* @param entryId the primary key for the new entry
	* @return the new entry
	*/
	public static com.liferay.chat.model.Entry create(long entryId) {
		return getPersistence().create(entryId);
	}

	/**
	* Removes the entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the entry
	* @return the entry that was removed
	* @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry remove(long entryId)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(entryId);
	}

	public static com.liferay.chat.model.Entry updateImpl(
		com.liferay.chat.model.Entry entry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(entry, merge);
	}

	/**
	* Returns the entry with the primary key or throws a {@link com.liferay.chat.NoSuchEntryException} if it could not be found.
	*
	* @param entryId the primary key of the entry
	* @return the entry
	* @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry findByPrimaryKey(long entryId)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(entryId);
	}

	/**
	* Returns the entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param entryId the primary key of the entry
	* @return the entry, or <code>null</code> if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry fetchByPrimaryKey(long entryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(entryId);
	}

	/**
	* Returns all the entries where createDate = &#63;.
	*
	* @param createDate the create date
	* @return the matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.chat.model.Entry> findByCreateDate(
		long createDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCreateDate(createDate);
	}

	/**
	* Returns a range of all the entries where createDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.chat.model.Entry> findByCreateDate(
		long createDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCreateDate(createDate, start, end);
	}

	/**
	* Returns an ordered range of all the entries where createDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.chat.model.Entry> findByCreateDate(
		long createDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCreateDate(createDate, start, end, orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry findByCreateDate_First(
		long createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCreateDate_First(createDate, orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry fetchByCreateDate_First(
		long createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCreateDate_First(createDate, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry findByCreateDate_Last(
		long createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCreateDate_Last(createDate, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry fetchByCreateDate_Last(
		long createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCreateDate_Last(createDate, orderByComparator);
	}

	/**
	* Returns the entries before and after the current entry in the ordered set where createDate = &#63;.
	*
	* @param entryId the primary key of the current entry
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entry
	* @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry[] findByCreateDate_PrevAndNext(
		long entryId, long createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCreateDate_PrevAndNext(entryId, createDate,
			orderByComparator);
	}

	/**
	* Returns all the entries where fromUserId = &#63;.
	*
	* @param fromUserId the from user ID
	* @return the matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.chat.model.Entry> findByFromUserId(
		long fromUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByFromUserId(fromUserId);
	}

	/**
	* Returns a range of all the entries where fromUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param fromUserId the from user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.chat.model.Entry> findByFromUserId(
		long fromUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByFromUserId(fromUserId, start, end);
	}

	/**
	* Returns an ordered range of all the entries where fromUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param fromUserId the from user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.chat.model.Entry> findByFromUserId(
		long fromUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByFromUserId(fromUserId, start, end, orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where fromUserId = &#63;.
	*
	* @param fromUserId the from user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry findByFromUserId_First(
		long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByFromUserId_First(fromUserId, orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where fromUserId = &#63;.
	*
	* @param fromUserId the from user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry fetchByFromUserId_First(
		long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByFromUserId_First(fromUserId, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where fromUserId = &#63;.
	*
	* @param fromUserId the from user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry findByFromUserId_Last(
		long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByFromUserId_Last(fromUserId, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where fromUserId = &#63;.
	*
	* @param fromUserId the from user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry fetchByFromUserId_Last(
		long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByFromUserId_Last(fromUserId, orderByComparator);
	}

	/**
	* Returns the entries before and after the current entry in the ordered set where fromUserId = &#63;.
	*
	* @param entryId the primary key of the current entry
	* @param fromUserId the from user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entry
	* @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry[] findByFromUserId_PrevAndNext(
		long entryId, long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByFromUserId_PrevAndNext(entryId, fromUserId,
			orderByComparator);
	}

	/**
	* Returns all the entries where toUserId = &#63;.
	*
	* @param toUserId the to user ID
	* @return the matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.chat.model.Entry> findByToUserId(
		long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByToUserId(toUserId);
	}

	/**
	* Returns a range of all the entries where toUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param toUserId the to user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.chat.model.Entry> findByToUserId(
		long toUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByToUserId(toUserId, start, end);
	}

	/**
	* Returns an ordered range of all the entries where toUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param toUserId the to user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.chat.model.Entry> findByToUserId(
		long toUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByToUserId(toUserId, start, end, orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where toUserId = &#63;.
	*
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry findByToUserId_First(
		long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByToUserId_First(toUserId, orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where toUserId = &#63;.
	*
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry fetchByToUserId_First(
		long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByToUserId_First(toUserId, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where toUserId = &#63;.
	*
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry findByToUserId_Last(
		long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByToUserId_Last(toUserId, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where toUserId = &#63;.
	*
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry fetchByToUserId_Last(
		long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByToUserId_Last(toUserId, orderByComparator);
	}

	/**
	* Returns the entries before and after the current entry in the ordered set where toUserId = &#63;.
	*
	* @param entryId the primary key of the current entry
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entry
	* @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry[] findByToUserId_PrevAndNext(
		long entryId, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByToUserId_PrevAndNext(entryId, toUserId,
			orderByComparator);
	}

	/**
	* Returns all the entries where createDate = &#63; and fromUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @return the matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.chat.model.Entry> findByC_F(
		long createDate, long fromUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_F(createDate, fromUserId);
	}

	/**
	* Returns a range of all the entries where createDate = &#63; and fromUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.chat.model.Entry> findByC_F(
		long createDate, long fromUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_F(createDate, fromUserId, start, end);
	}

	/**
	* Returns an ordered range of all the entries where createDate = &#63; and fromUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.chat.model.Entry> findByC_F(
		long createDate, long fromUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_F(createDate, fromUserId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where createDate = &#63; and fromUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry findByC_F_First(
		long createDate, long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_F_First(createDate, fromUserId, orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where createDate = &#63; and fromUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry fetchByC_F_First(
		long createDate, long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_F_First(createDate, fromUserId, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where createDate = &#63; and fromUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry findByC_F_Last(long createDate,
		long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_F_Last(createDate, fromUserId, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where createDate = &#63; and fromUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry fetchByC_F_Last(
		long createDate, long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_F_Last(createDate, fromUserId, orderByComparator);
	}

	/**
	* Returns the entries before and after the current entry in the ordered set where createDate = &#63; and fromUserId = &#63;.
	*
	* @param entryId the primary key of the current entry
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entry
	* @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry[] findByC_F_PrevAndNext(
		long entryId, long createDate, long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_F_PrevAndNext(entryId, createDate, fromUserId,
			orderByComparator);
	}

	/**
	* Returns all the entries where createDate = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param toUserId the to user ID
	* @return the matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.chat.model.Entry> findByC_T(
		long createDate, long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_T(createDate, toUserId);
	}

	/**
	* Returns a range of all the entries where createDate = &#63; and toUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param toUserId the to user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.chat.model.Entry> findByC_T(
		long createDate, long toUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_T(createDate, toUserId, start, end);
	}

	/**
	* Returns an ordered range of all the entries where createDate = &#63; and toUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param toUserId the to user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.chat.model.Entry> findByC_T(
		long createDate, long toUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_T(createDate, toUserId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where createDate = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry findByC_T_First(
		long createDate, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_T_First(createDate, toUserId, orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where createDate = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry fetchByC_T_First(
		long createDate, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_T_First(createDate, toUserId, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where createDate = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry findByC_T_Last(long createDate,
		long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_T_Last(createDate, toUserId, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where createDate = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry fetchByC_T_Last(
		long createDate, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_T_Last(createDate, toUserId, orderByComparator);
	}

	/**
	* Returns the entries before and after the current entry in the ordered set where createDate = &#63; and toUserId = &#63;.
	*
	* @param entryId the primary key of the current entry
	* @param createDate the create date
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entry
	* @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry[] findByC_T_PrevAndNext(
		long entryId, long createDate, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_T_PrevAndNext(entryId, createDate, toUserId,
			orderByComparator);
	}

	/**
	* Returns all the entries where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @return the matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.chat.model.Entry> findByC_F_T(
		long createDate, long fromUserId, long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_F_T(createDate, fromUserId, toUserId);
	}

	/**
	* Returns a range of all the entries where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.chat.model.Entry> findByC_F_T(
		long createDate, long fromUserId, long toUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_F_T(createDate, fromUserId, toUserId, start, end);
	}

	/**
	* Returns an ordered range of all the entries where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.chat.model.Entry> findByC_F_T(
		long createDate, long fromUserId, long toUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_F_T(createDate, fromUserId, toUserId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry findByC_F_T_First(
		long createDate, long fromUserId, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_F_T_First(createDate, fromUserId, toUserId,
			orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry fetchByC_F_T_First(
		long createDate, long fromUserId, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_F_T_First(createDate, fromUserId, toUserId,
			orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry findByC_F_T_Last(
		long createDate, long fromUserId, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_F_T_Last(createDate, fromUserId, toUserId,
			orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry fetchByC_F_T_Last(
		long createDate, long fromUserId, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_F_T_Last(createDate, fromUserId, toUserId,
			orderByComparator);
	}

	/**
	* Returns the entries before and after the current entry in the ordered set where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	*
	* @param entryId the primary key of the current entry
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entry
	* @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry[] findByC_F_T_PrevAndNext(
		long entryId, long createDate, long fromUserId, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_F_T_PrevAndNext(entryId, createDate, fromUserId,
			toUserId, orderByComparator);
	}

	/**
	* Returns all the entries where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param content the content
	* @return the matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.chat.model.Entry> findByF_T_C(
		long fromUserId, long toUserId, java.lang.String content)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByF_T_C(fromUserId, toUserId, content);
	}

	/**
	* Returns a range of all the entries where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param content the content
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.chat.model.Entry> findByF_T_C(
		long fromUserId, long toUserId, java.lang.String content, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByF_T_C(fromUserId, toUserId, content, start, end);
	}

	/**
	* Returns an ordered range of all the entries where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param content the content
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.chat.model.Entry> findByF_T_C(
		long fromUserId, long toUserId, java.lang.String content, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByF_T_C(fromUserId, toUserId, content, start, end,
			orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param content the content
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry findByF_T_C_First(
		long fromUserId, long toUserId, java.lang.String content,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByF_T_C_First(fromUserId, toUserId, content,
			orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param content the content
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry fetchByF_T_C_First(
		long fromUserId, long toUserId, java.lang.String content,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByF_T_C_First(fromUserId, toUserId, content,
			orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param content the content
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry findByF_T_C_Last(
		long fromUserId, long toUserId, java.lang.String content,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByF_T_C_Last(fromUserId, toUserId, content,
			orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param content the content
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry fetchByF_T_C_Last(
		long fromUserId, long toUserId, java.lang.String content,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByF_T_C_Last(fromUserId, toUserId, content,
			orderByComparator);
	}

	/**
	* Returns the entries before and after the current entry in the ordered set where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	*
	* @param entryId the primary key of the current entry
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param content the content
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entry
	* @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Entry[] findByF_T_C_PrevAndNext(
		long entryId, long fromUserId, long toUserId, java.lang.String content,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByF_T_C_PrevAndNext(entryId, fromUserId, toUserId,
			content, orderByComparator);
	}

	/**
	* Returns all the entries.
	*
	* @return the entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.chat.model.Entry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.chat.model.Entry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.chat.model.Entry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the entries where createDate = &#63; from the database.
	*
	* @param createDate the create date
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCreateDate(long createDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCreateDate(createDate);
	}

	/**
	* Removes all the entries where fromUserId = &#63; from the database.
	*
	* @param fromUserId the from user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByFromUserId(long fromUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByFromUserId(fromUserId);
	}

	/**
	* Removes all the entries where toUserId = &#63; from the database.
	*
	* @param toUserId the to user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByToUserId(long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByToUserId(toUserId);
	}

	/**
	* Removes all the entries where createDate = &#63; and fromUserId = &#63; from the database.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_F(long createDate, long fromUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_F(createDate, fromUserId);
	}

	/**
	* Removes all the entries where createDate = &#63; and toUserId = &#63; from the database.
	*
	* @param createDate the create date
	* @param toUserId the to user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_T(long createDate, long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_T(createDate, toUserId);
	}

	/**
	* Removes all the entries where createDate = &#63; and fromUserId = &#63; and toUserId = &#63; from the database.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_F_T(long createDate, long fromUserId,
		long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_F_T(createDate, fromUserId, toUserId);
	}

	/**
	* Removes all the entries where fromUserId = &#63; and toUserId = &#63; and content = &#63; from the database.
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param content the content
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByF_T_C(long fromUserId, long toUserId,
		java.lang.String content)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByF_T_C(fromUserId, toUserId, content);
	}

	/**
	* Removes all the entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of entries where createDate = &#63;.
	*
	* @param createDate the create date
	* @return the number of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCreateDate(long createDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCreateDate(createDate);
	}

	/**
	* Returns the number of entries where fromUserId = &#63;.
	*
	* @param fromUserId the from user ID
	* @return the number of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByFromUserId(long fromUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByFromUserId(fromUserId);
	}

	/**
	* Returns the number of entries where toUserId = &#63;.
	*
	* @param toUserId the to user ID
	* @return the number of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByToUserId(long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByToUserId(toUserId);
	}

	/**
	* Returns the number of entries where createDate = &#63; and fromUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @return the number of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_F(long createDate, long fromUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_F(createDate, fromUserId);
	}

	/**
	* Returns the number of entries where createDate = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param toUserId the to user ID
	* @return the number of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_T(long createDate, long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_T(createDate, toUserId);
	}

	/**
	* Returns the number of entries where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @return the number of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_F_T(long createDate, long fromUserId,
		long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_F_T(createDate, fromUserId, toUserId);
	}

	/**
	* Returns the number of entries where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param content the content
	* @return the number of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByF_T_C(long fromUserId, long toUserId,
		java.lang.String content)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByF_T_C(fromUserId, toUserId, content);
	}

	/**
	* Returns the number of entries.
	*
	* @return the number of entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static EntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (EntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.chat.service.ClpSerializer.getServletContextName(),
					EntryPersistence.class.getName());

			ReferenceRegistry.registerReference(EntryUtil.class, "_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(EntryPersistence persistence) {
	}

	private static EntryPersistence _persistence;
}