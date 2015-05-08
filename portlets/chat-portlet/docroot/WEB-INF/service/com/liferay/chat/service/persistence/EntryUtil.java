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

package com.liferay.chat.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.chat.model.Entry;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the entry service. This utility wraps {@link com.liferay.chat.service.persistence.impl.EntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntryPersistence
 * @see com.liferay.chat.service.persistence.impl.EntryPersistenceImpl
 * @generated
 */
@ProviderType
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
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Entry> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Entry> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Entry> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Entry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static Entry update(Entry entry) {
		return getPersistence().update(entry);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static Entry update(Entry entry, ServiceContext serviceContext) {
		return getPersistence().update(entry, serviceContext);
	}

	/**
	* Returns all the entries where createDate = &#63;.
	*
	* @param createDate the create date
	* @return the matching entries
	*/
	public static List<Entry> findByCreateDate(long createDate) {
		return getPersistence().findByCreateDate(createDate);
	}

	/**
	* Returns a range of all the entries where createDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	*/
	public static List<Entry> findByCreateDate(long createDate, int start,
		int end) {
		return getPersistence().findByCreateDate(createDate, start, end);
	}

	/**
	* Returns an ordered range of all the entries where createDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	*/
	public static List<Entry> findByCreateDate(long createDate, int start,
		int end, OrderByComparator<Entry> orderByComparator) {
		return getPersistence()
				   .findByCreateDate(createDate, start, end, orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public static Entry findByCreateDate_First(long createDate,
		OrderByComparator<Entry> orderByComparator)
		throws com.liferay.chat.NoSuchEntryException {
		return getPersistence()
				   .findByCreateDate_First(createDate, orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	*/
	public static Entry fetchByCreateDate_First(long createDate,
		OrderByComparator<Entry> orderByComparator) {
		return getPersistence()
				   .fetchByCreateDate_First(createDate, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public static Entry findByCreateDate_Last(long createDate,
		OrderByComparator<Entry> orderByComparator)
		throws com.liferay.chat.NoSuchEntryException {
		return getPersistence()
				   .findByCreateDate_Last(createDate, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	*/
	public static Entry fetchByCreateDate_Last(long createDate,
		OrderByComparator<Entry> orderByComparator) {
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
	* @throws NoSuchEntryException if a entry with the primary key could not be found
	*/
	public static Entry[] findByCreateDate_PrevAndNext(long entryId,
		long createDate, OrderByComparator<Entry> orderByComparator)
		throws com.liferay.chat.NoSuchEntryException {
		return getPersistence()
				   .findByCreateDate_PrevAndNext(entryId, createDate,
			orderByComparator);
	}

	/**
	* Removes all the entries where createDate = &#63; from the database.
	*
	* @param createDate the create date
	*/
	public static void removeByCreateDate(long createDate) {
		getPersistence().removeByCreateDate(createDate);
	}

	/**
	* Returns the number of entries where createDate = &#63;.
	*
	* @param createDate the create date
	* @return the number of matching entries
	*/
	public static int countByCreateDate(long createDate) {
		return getPersistence().countByCreateDate(createDate);
	}

	/**
	* Returns all the entries where fromUserId = &#63;.
	*
	* @param fromUserId the from user ID
	* @return the matching entries
	*/
	public static List<Entry> findByFromUserId(long fromUserId) {
		return getPersistence().findByFromUserId(fromUserId);
	}

	/**
	* Returns a range of all the entries where fromUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param fromUserId the from user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	*/
	public static List<Entry> findByFromUserId(long fromUserId, int start,
		int end) {
		return getPersistence().findByFromUserId(fromUserId, start, end);
	}

	/**
	* Returns an ordered range of all the entries where fromUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param fromUserId the from user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	*/
	public static List<Entry> findByFromUserId(long fromUserId, int start,
		int end, OrderByComparator<Entry> orderByComparator) {
		return getPersistence()
				   .findByFromUserId(fromUserId, start, end, orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where fromUserId = &#63;.
	*
	* @param fromUserId the from user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public static Entry findByFromUserId_First(long fromUserId,
		OrderByComparator<Entry> orderByComparator)
		throws com.liferay.chat.NoSuchEntryException {
		return getPersistence()
				   .findByFromUserId_First(fromUserId, orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where fromUserId = &#63;.
	*
	* @param fromUserId the from user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	*/
	public static Entry fetchByFromUserId_First(long fromUserId,
		OrderByComparator<Entry> orderByComparator) {
		return getPersistence()
				   .fetchByFromUserId_First(fromUserId, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where fromUserId = &#63;.
	*
	* @param fromUserId the from user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public static Entry findByFromUserId_Last(long fromUserId,
		OrderByComparator<Entry> orderByComparator)
		throws com.liferay.chat.NoSuchEntryException {
		return getPersistence()
				   .findByFromUserId_Last(fromUserId, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where fromUserId = &#63;.
	*
	* @param fromUserId the from user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	*/
	public static Entry fetchByFromUserId_Last(long fromUserId,
		OrderByComparator<Entry> orderByComparator) {
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
	* @throws NoSuchEntryException if a entry with the primary key could not be found
	*/
	public static Entry[] findByFromUserId_PrevAndNext(long entryId,
		long fromUserId, OrderByComparator<Entry> orderByComparator)
		throws com.liferay.chat.NoSuchEntryException {
		return getPersistence()
				   .findByFromUserId_PrevAndNext(entryId, fromUserId,
			orderByComparator);
	}

	/**
	* Removes all the entries where fromUserId = &#63; from the database.
	*
	* @param fromUserId the from user ID
	*/
	public static void removeByFromUserId(long fromUserId) {
		getPersistence().removeByFromUserId(fromUserId);
	}

	/**
	* Returns the number of entries where fromUserId = &#63;.
	*
	* @param fromUserId the from user ID
	* @return the number of matching entries
	*/
	public static int countByFromUserId(long fromUserId) {
		return getPersistence().countByFromUserId(fromUserId);
	}

	/**
	* Returns all the entries where toUserId = &#63;.
	*
	* @param toUserId the to user ID
	* @return the matching entries
	*/
	public static List<Entry> findByToUserId(long toUserId) {
		return getPersistence().findByToUserId(toUserId);
	}

	/**
	* Returns a range of all the entries where toUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param toUserId the to user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	*/
	public static List<Entry> findByToUserId(long toUserId, int start, int end) {
		return getPersistence().findByToUserId(toUserId, start, end);
	}

	/**
	* Returns an ordered range of all the entries where toUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param toUserId the to user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	*/
	public static List<Entry> findByToUserId(long toUserId, int start, int end,
		OrderByComparator<Entry> orderByComparator) {
		return getPersistence()
				   .findByToUserId(toUserId, start, end, orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where toUserId = &#63;.
	*
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public static Entry findByToUserId_First(long toUserId,
		OrderByComparator<Entry> orderByComparator)
		throws com.liferay.chat.NoSuchEntryException {
		return getPersistence().findByToUserId_First(toUserId, orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where toUserId = &#63;.
	*
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	*/
	public static Entry fetchByToUserId_First(long toUserId,
		OrderByComparator<Entry> orderByComparator) {
		return getPersistence()
				   .fetchByToUserId_First(toUserId, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where toUserId = &#63;.
	*
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public static Entry findByToUserId_Last(long toUserId,
		OrderByComparator<Entry> orderByComparator)
		throws com.liferay.chat.NoSuchEntryException {
		return getPersistence().findByToUserId_Last(toUserId, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where toUserId = &#63;.
	*
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	*/
	public static Entry fetchByToUserId_Last(long toUserId,
		OrderByComparator<Entry> orderByComparator) {
		return getPersistence().fetchByToUserId_Last(toUserId, orderByComparator);
	}

	/**
	* Returns the entries before and after the current entry in the ordered set where toUserId = &#63;.
	*
	* @param entryId the primary key of the current entry
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entry
	* @throws NoSuchEntryException if a entry with the primary key could not be found
	*/
	public static Entry[] findByToUserId_PrevAndNext(long entryId,
		long toUserId, OrderByComparator<Entry> orderByComparator)
		throws com.liferay.chat.NoSuchEntryException {
		return getPersistence()
				   .findByToUserId_PrevAndNext(entryId, toUserId,
			orderByComparator);
	}

	/**
	* Removes all the entries where toUserId = &#63; from the database.
	*
	* @param toUserId the to user ID
	*/
	public static void removeByToUserId(long toUserId) {
		getPersistence().removeByToUserId(toUserId);
	}

	/**
	* Returns the number of entries where toUserId = &#63;.
	*
	* @param toUserId the to user ID
	* @return the number of matching entries
	*/
	public static int countByToUserId(long toUserId) {
		return getPersistence().countByToUserId(toUserId);
	}

	/**
	* Returns all the entries where createDate = &#63; and fromUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @return the matching entries
	*/
	public static List<Entry> findByC_F(long createDate, long fromUserId) {
		return getPersistence().findByC_F(createDate, fromUserId);
	}

	/**
	* Returns a range of all the entries where createDate = &#63; and fromUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	*/
	public static List<Entry> findByC_F(long createDate, long fromUserId,
		int start, int end) {
		return getPersistence().findByC_F(createDate, fromUserId, start, end);
	}

	/**
	* Returns an ordered range of all the entries where createDate = &#63; and fromUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	*/
	public static List<Entry> findByC_F(long createDate, long fromUserId,
		int start, int end, OrderByComparator<Entry> orderByComparator) {
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
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public static Entry findByC_F_First(long createDate, long fromUserId,
		OrderByComparator<Entry> orderByComparator)
		throws com.liferay.chat.NoSuchEntryException {
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
	*/
	public static Entry fetchByC_F_First(long createDate, long fromUserId,
		OrderByComparator<Entry> orderByComparator) {
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
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public static Entry findByC_F_Last(long createDate, long fromUserId,
		OrderByComparator<Entry> orderByComparator)
		throws com.liferay.chat.NoSuchEntryException {
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
	*/
	public static Entry fetchByC_F_Last(long createDate, long fromUserId,
		OrderByComparator<Entry> orderByComparator) {
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
	* @throws NoSuchEntryException if a entry with the primary key could not be found
	*/
	public static Entry[] findByC_F_PrevAndNext(long entryId, long createDate,
		long fromUserId, OrderByComparator<Entry> orderByComparator)
		throws com.liferay.chat.NoSuchEntryException {
		return getPersistence()
				   .findByC_F_PrevAndNext(entryId, createDate, fromUserId,
			orderByComparator);
	}

	/**
	* Removes all the entries where createDate = &#63; and fromUserId = &#63; from the database.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	*/
	public static void removeByC_F(long createDate, long fromUserId) {
		getPersistence().removeByC_F(createDate, fromUserId);
	}

	/**
	* Returns the number of entries where createDate = &#63; and fromUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @return the number of matching entries
	*/
	public static int countByC_F(long createDate, long fromUserId) {
		return getPersistence().countByC_F(createDate, fromUserId);
	}

	/**
	* Returns all the entries where createDate = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param toUserId the to user ID
	* @return the matching entries
	*/
	public static List<Entry> findByC_T(long createDate, long toUserId) {
		return getPersistence().findByC_T(createDate, toUserId);
	}

	/**
	* Returns a range of all the entries where createDate = &#63; and toUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param toUserId the to user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	*/
	public static List<Entry> findByC_T(long createDate, long toUserId,
		int start, int end) {
		return getPersistence().findByC_T(createDate, toUserId, start, end);
	}

	/**
	* Returns an ordered range of all the entries where createDate = &#63; and toUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param toUserId the to user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	*/
	public static List<Entry> findByC_T(long createDate, long toUserId,
		int start, int end, OrderByComparator<Entry> orderByComparator) {
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
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public static Entry findByC_T_First(long createDate, long toUserId,
		OrderByComparator<Entry> orderByComparator)
		throws com.liferay.chat.NoSuchEntryException {
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
	*/
	public static Entry fetchByC_T_First(long createDate, long toUserId,
		OrderByComparator<Entry> orderByComparator) {
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
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public static Entry findByC_T_Last(long createDate, long toUserId,
		OrderByComparator<Entry> orderByComparator)
		throws com.liferay.chat.NoSuchEntryException {
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
	*/
	public static Entry fetchByC_T_Last(long createDate, long toUserId,
		OrderByComparator<Entry> orderByComparator) {
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
	* @throws NoSuchEntryException if a entry with the primary key could not be found
	*/
	public static Entry[] findByC_T_PrevAndNext(long entryId, long createDate,
		long toUserId, OrderByComparator<Entry> orderByComparator)
		throws com.liferay.chat.NoSuchEntryException {
		return getPersistence()
				   .findByC_T_PrevAndNext(entryId, createDate, toUserId,
			orderByComparator);
	}

	/**
	* Removes all the entries where createDate = &#63; and toUserId = &#63; from the database.
	*
	* @param createDate the create date
	* @param toUserId the to user ID
	*/
	public static void removeByC_T(long createDate, long toUserId) {
		getPersistence().removeByC_T(createDate, toUserId);
	}

	/**
	* Returns the number of entries where createDate = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param toUserId the to user ID
	* @return the number of matching entries
	*/
	public static int countByC_T(long createDate, long toUserId) {
		return getPersistence().countByC_T(createDate, toUserId);
	}

	/**
	* Returns all the entries where fromUserId = &#63; and toUserId = &#63;.
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @return the matching entries
	*/
	public static List<Entry> findByF_T(long fromUserId, long toUserId) {
		return getPersistence().findByF_T(fromUserId, toUserId);
	}

	/**
	* Returns a range of all the entries where fromUserId = &#63; and toUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	*/
	public static List<Entry> findByF_T(long fromUserId, long toUserId,
		int start, int end) {
		return getPersistence().findByF_T(fromUserId, toUserId, start, end);
	}

	/**
	* Returns an ordered range of all the entries where fromUserId = &#63; and toUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	*/
	public static List<Entry> findByF_T(long fromUserId, long toUserId,
		int start, int end, OrderByComparator<Entry> orderByComparator) {
		return getPersistence()
				   .findByF_T(fromUserId, toUserId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where fromUserId = &#63; and toUserId = &#63;.
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public static Entry findByF_T_First(long fromUserId, long toUserId,
		OrderByComparator<Entry> orderByComparator)
		throws com.liferay.chat.NoSuchEntryException {
		return getPersistence()
				   .findByF_T_First(fromUserId, toUserId, orderByComparator);
	}

	/**
	* Returns the first entry in the ordered set where fromUserId = &#63; and toUserId = &#63;.
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	*/
	public static Entry fetchByF_T_First(long fromUserId, long toUserId,
		OrderByComparator<Entry> orderByComparator) {
		return getPersistence()
				   .fetchByF_T_First(fromUserId, toUserId, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where fromUserId = &#63; and toUserId = &#63;.
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public static Entry findByF_T_Last(long fromUserId, long toUserId,
		OrderByComparator<Entry> orderByComparator)
		throws com.liferay.chat.NoSuchEntryException {
		return getPersistence()
				   .findByF_T_Last(fromUserId, toUserId, orderByComparator);
	}

	/**
	* Returns the last entry in the ordered set where fromUserId = &#63; and toUserId = &#63;.
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	*/
	public static Entry fetchByF_T_Last(long fromUserId, long toUserId,
		OrderByComparator<Entry> orderByComparator) {
		return getPersistence()
				   .fetchByF_T_Last(fromUserId, toUserId, orderByComparator);
	}

	/**
	* Returns the entries before and after the current entry in the ordered set where fromUserId = &#63; and toUserId = &#63;.
	*
	* @param entryId the primary key of the current entry
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entry
	* @throws NoSuchEntryException if a entry with the primary key could not be found
	*/
	public static Entry[] findByF_T_PrevAndNext(long entryId, long fromUserId,
		long toUserId, OrderByComparator<Entry> orderByComparator)
		throws com.liferay.chat.NoSuchEntryException {
		return getPersistence()
				   .findByF_T_PrevAndNext(entryId, fromUserId, toUserId,
			orderByComparator);
	}

	/**
	* Removes all the entries where fromUserId = &#63; and toUserId = &#63; from the database.
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	*/
	public static void removeByF_T(long fromUserId, long toUserId) {
		getPersistence().removeByF_T(fromUserId, toUserId);
	}

	/**
	* Returns the number of entries where fromUserId = &#63; and toUserId = &#63;.
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @return the number of matching entries
	*/
	public static int countByF_T(long fromUserId, long toUserId) {
		return getPersistence().countByF_T(fromUserId, toUserId);
	}

	/**
	* Returns all the entries where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @return the matching entries
	*/
	public static List<Entry> findByC_F_T(long createDate, long fromUserId,
		long toUserId) {
		return getPersistence().findByC_F_T(createDate, fromUserId, toUserId);
	}

	/**
	* Returns a range of all the entries where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	*/
	public static List<Entry> findByC_F_T(long createDate, long fromUserId,
		long toUserId, int start, int end) {
		return getPersistence()
				   .findByC_F_T(createDate, fromUserId, toUserId, start, end);
	}

	/**
	* Returns an ordered range of all the entries where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	*/
	public static List<Entry> findByC_F_T(long createDate, long fromUserId,
		long toUserId, int start, int end,
		OrderByComparator<Entry> orderByComparator) {
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
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public static Entry findByC_F_T_First(long createDate, long fromUserId,
		long toUserId, OrderByComparator<Entry> orderByComparator)
		throws com.liferay.chat.NoSuchEntryException {
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
	*/
	public static Entry fetchByC_F_T_First(long createDate, long fromUserId,
		long toUserId, OrderByComparator<Entry> orderByComparator) {
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
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public static Entry findByC_F_T_Last(long createDate, long fromUserId,
		long toUserId, OrderByComparator<Entry> orderByComparator)
		throws com.liferay.chat.NoSuchEntryException {
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
	*/
	public static Entry fetchByC_F_T_Last(long createDate, long fromUserId,
		long toUserId, OrderByComparator<Entry> orderByComparator) {
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
	* @throws NoSuchEntryException if a entry with the primary key could not be found
	*/
	public static Entry[] findByC_F_T_PrevAndNext(long entryId,
		long createDate, long fromUserId, long toUserId,
		OrderByComparator<Entry> orderByComparator)
		throws com.liferay.chat.NoSuchEntryException {
		return getPersistence()
				   .findByC_F_T_PrevAndNext(entryId, createDate, fromUserId,
			toUserId, orderByComparator);
	}

	/**
	* Removes all the entries where createDate = &#63; and fromUserId = &#63; and toUserId = &#63; from the database.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	*/
	public static void removeByC_F_T(long createDate, long fromUserId,
		long toUserId) {
		getPersistence().removeByC_F_T(createDate, fromUserId, toUserId);
	}

	/**
	* Returns the number of entries where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @return the number of matching entries
	*/
	public static int countByC_F_T(long createDate, long fromUserId,
		long toUserId) {
		return getPersistence().countByC_F_T(createDate, fromUserId, toUserId);
	}

	/**
	* Returns all the entries where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param content the content
	* @return the matching entries
	*/
	public static List<Entry> findByF_T_C(long fromUserId, long toUserId,
		java.lang.String content) {
		return getPersistence().findByF_T_C(fromUserId, toUserId, content);
	}

	/**
	* Returns a range of all the entries where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param content the content
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	*/
	public static List<Entry> findByF_T_C(long fromUserId, long toUserId,
		java.lang.String content, int start, int end) {
		return getPersistence()
				   .findByF_T_C(fromUserId, toUserId, content, start, end);
	}

	/**
	* Returns an ordered range of all the entries where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param content the content
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	*/
	public static List<Entry> findByF_T_C(long fromUserId, long toUserId,
		java.lang.String content, int start, int end,
		OrderByComparator<Entry> orderByComparator) {
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
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public static Entry findByF_T_C_First(long fromUserId, long toUserId,
		java.lang.String content, OrderByComparator<Entry> orderByComparator)
		throws com.liferay.chat.NoSuchEntryException {
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
	*/
	public static Entry fetchByF_T_C_First(long fromUserId, long toUserId,
		java.lang.String content, OrderByComparator<Entry> orderByComparator) {
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
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public static Entry findByF_T_C_Last(long fromUserId, long toUserId,
		java.lang.String content, OrderByComparator<Entry> orderByComparator)
		throws com.liferay.chat.NoSuchEntryException {
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
	*/
	public static Entry fetchByF_T_C_Last(long fromUserId, long toUserId,
		java.lang.String content, OrderByComparator<Entry> orderByComparator) {
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
	* @throws NoSuchEntryException if a entry with the primary key could not be found
	*/
	public static Entry[] findByF_T_C_PrevAndNext(long entryId,
		long fromUserId, long toUserId, java.lang.String content,
		OrderByComparator<Entry> orderByComparator)
		throws com.liferay.chat.NoSuchEntryException {
		return getPersistence()
				   .findByF_T_C_PrevAndNext(entryId, fromUserId, toUserId,
			content, orderByComparator);
	}

	/**
	* Removes all the entries where fromUserId = &#63; and toUserId = &#63; and content = &#63; from the database.
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param content the content
	*/
	public static void removeByF_T_C(long fromUserId, long toUserId,
		java.lang.String content) {
		getPersistence().removeByF_T_C(fromUserId, toUserId, content);
	}

	/**
	* Returns the number of entries where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param content the content
	* @return the number of matching entries
	*/
	public static int countByF_T_C(long fromUserId, long toUserId,
		java.lang.String content) {
		return getPersistence().countByF_T_C(fromUserId, toUserId, content);
	}

	/**
	* Caches the entry in the entity cache if it is enabled.
	*
	* @param entry the entry
	*/
	public static void cacheResult(Entry entry) {
		getPersistence().cacheResult(entry);
	}

	/**
	* Caches the entries in the entity cache if it is enabled.
	*
	* @param entries the entries
	*/
	public static void cacheResult(List<Entry> entries) {
		getPersistence().cacheResult(entries);
	}

	/**
	* Creates a new entry with the primary key. Does not add the entry to the database.
	*
	* @param entryId the primary key for the new entry
	* @return the new entry
	*/
	public static Entry create(long entryId) {
		return getPersistence().create(entryId);
	}

	/**
	* Removes the entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the entry
	* @return the entry that was removed
	* @throws NoSuchEntryException if a entry with the primary key could not be found
	*/
	public static Entry remove(long entryId)
		throws com.liferay.chat.NoSuchEntryException {
		return getPersistence().remove(entryId);
	}

	public static Entry updateImpl(Entry entry) {
		return getPersistence().updateImpl(entry);
	}

	/**
	* Returns the entry with the primary key or throws a {@link NoSuchEntryException} if it could not be found.
	*
	* @param entryId the primary key of the entry
	* @return the entry
	* @throws NoSuchEntryException if a entry with the primary key could not be found
	*/
	public static Entry findByPrimaryKey(long entryId)
		throws com.liferay.chat.NoSuchEntryException {
		return getPersistence().findByPrimaryKey(entryId);
	}

	/**
	* Returns the entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param entryId the primary key of the entry
	* @return the entry, or <code>null</code> if a entry with the primary key could not be found
	*/
	public static Entry fetchByPrimaryKey(long entryId) {
		return getPersistence().fetchByPrimaryKey(entryId);
	}

	public static java.util.Map<java.io.Serializable, Entry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the entries.
	*
	* @return the entries
	*/
	public static List<Entry> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of entries
	*/
	public static List<Entry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of entries
	*/
	public static List<Entry> findAll(int start, int end,
		OrderByComparator<Entry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of entries.
	*
	* @return the number of entries
	*/
	public static int countAll() {
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
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(EntryPersistence persistence) {
	}

	private static EntryPersistence _persistence;
}