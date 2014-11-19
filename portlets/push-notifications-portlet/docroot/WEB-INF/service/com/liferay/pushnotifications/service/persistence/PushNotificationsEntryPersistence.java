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

package com.liferay.pushnotifications.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.pushnotifications.model.PushNotificationsEntry;

/**
 * The persistence interface for the push notifications entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Bruno Farache
 * @see PushNotificationsEntryPersistenceImpl
 * @see PushNotificationsEntryUtil
 * @generated
 */
@ProviderType
public interface PushNotificationsEntryPersistence extends BasePersistence<PushNotificationsEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PushNotificationsEntryUtil} to access the push notifications entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the push notifications entries where parentPushNotificationsEntryId = &#63;.
	*
	* @param parentPushNotificationsEntryId the parent push notifications entry ID
	* @return the matching push notifications entries
	*/
	public java.util.List<com.liferay.pushnotifications.model.PushNotificationsEntry> findByParentPushNotificationsEntryId(
		long parentPushNotificationsEntryId);

	/**
	* Returns a range of all the push notifications entries where parentPushNotificationsEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.pushnotifications.model.impl.PushNotificationsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentPushNotificationsEntryId the parent push notifications entry ID
	* @param start the lower bound of the range of push notifications entries
	* @param end the upper bound of the range of push notifications entries (not inclusive)
	* @return the range of matching push notifications entries
	*/
	public java.util.List<com.liferay.pushnotifications.model.PushNotificationsEntry> findByParentPushNotificationsEntryId(
		long parentPushNotificationsEntryId, int start, int end);

	/**
	* Returns an ordered range of all the push notifications entries where parentPushNotificationsEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.pushnotifications.model.impl.PushNotificationsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentPushNotificationsEntryId the parent push notifications entry ID
	* @param start the lower bound of the range of push notifications entries
	* @param end the upper bound of the range of push notifications entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching push notifications entries
	*/
	public java.util.List<com.liferay.pushnotifications.model.PushNotificationsEntry> findByParentPushNotificationsEntryId(
		long parentPushNotificationsEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.pushnotifications.model.PushNotificationsEntry> orderByComparator);

	/**
	* Returns the first push notifications entry in the ordered set where parentPushNotificationsEntryId = &#63;.
	*
	* @param parentPushNotificationsEntryId the parent push notifications entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push notifications entry
	* @throws com.liferay.pushnotifications.NoSuchEntryException if a matching push notifications entry could not be found
	*/
	public com.liferay.pushnotifications.model.PushNotificationsEntry findByParentPushNotificationsEntryId_First(
		long parentPushNotificationsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.pushnotifications.model.PushNotificationsEntry> orderByComparator)
		throws com.liferay.pushnotifications.NoSuchEntryException;

	/**
	* Returns the first push notifications entry in the ordered set where parentPushNotificationsEntryId = &#63;.
	*
	* @param parentPushNotificationsEntryId the parent push notifications entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push notifications entry, or <code>null</code> if a matching push notifications entry could not be found
	*/
	public com.liferay.pushnotifications.model.PushNotificationsEntry fetchByParentPushNotificationsEntryId_First(
		long parentPushNotificationsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.pushnotifications.model.PushNotificationsEntry> orderByComparator);

	/**
	* Returns the last push notifications entry in the ordered set where parentPushNotificationsEntryId = &#63;.
	*
	* @param parentPushNotificationsEntryId the parent push notifications entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push notifications entry
	* @throws com.liferay.pushnotifications.NoSuchEntryException if a matching push notifications entry could not be found
	*/
	public com.liferay.pushnotifications.model.PushNotificationsEntry findByParentPushNotificationsEntryId_Last(
		long parentPushNotificationsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.pushnotifications.model.PushNotificationsEntry> orderByComparator)
		throws com.liferay.pushnotifications.NoSuchEntryException;

	/**
	* Returns the last push notifications entry in the ordered set where parentPushNotificationsEntryId = &#63;.
	*
	* @param parentPushNotificationsEntryId the parent push notifications entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push notifications entry, or <code>null</code> if a matching push notifications entry could not be found
	*/
	public com.liferay.pushnotifications.model.PushNotificationsEntry fetchByParentPushNotificationsEntryId_Last(
		long parentPushNotificationsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.pushnotifications.model.PushNotificationsEntry> orderByComparator);

	/**
	* Returns the push notifications entries before and after the current push notifications entry in the ordered set where parentPushNotificationsEntryId = &#63;.
	*
	* @param pushNotificationsEntryId the primary key of the current push notifications entry
	* @param parentPushNotificationsEntryId the parent push notifications entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next push notifications entry
	* @throws com.liferay.pushnotifications.NoSuchEntryException if a push notifications entry with the primary key could not be found
	*/
	public com.liferay.pushnotifications.model.PushNotificationsEntry[] findByParentPushNotificationsEntryId_PrevAndNext(
		long pushNotificationsEntryId, long parentPushNotificationsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.pushnotifications.model.PushNotificationsEntry> orderByComparator)
		throws com.liferay.pushnotifications.NoSuchEntryException;

	/**
	* Removes all the push notifications entries where parentPushNotificationsEntryId = &#63; from the database.
	*
	* @param parentPushNotificationsEntryId the parent push notifications entry ID
	*/
	public void removeByParentPushNotificationsEntryId(
		long parentPushNotificationsEntryId);

	/**
	* Returns the number of push notifications entries where parentPushNotificationsEntryId = &#63;.
	*
	* @param parentPushNotificationsEntryId the parent push notifications entry ID
	* @return the number of matching push notifications entries
	*/
	public int countByParentPushNotificationsEntryId(
		long parentPushNotificationsEntryId);

	/**
	* Returns all the push notifications entries where createTime &gt; &#63; and parentPushNotificationsEntryId = &#63;.
	*
	* @param createTime the create time
	* @param parentPushNotificationsEntryId the parent push notifications entry ID
	* @return the matching push notifications entries
	*/
	public java.util.List<com.liferay.pushnotifications.model.PushNotificationsEntry> findByC_P(
		long createTime, long parentPushNotificationsEntryId);

	/**
	* Returns a range of all the push notifications entries where createTime &gt; &#63; and parentPushNotificationsEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.pushnotifications.model.impl.PushNotificationsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createTime the create time
	* @param parentPushNotificationsEntryId the parent push notifications entry ID
	* @param start the lower bound of the range of push notifications entries
	* @param end the upper bound of the range of push notifications entries (not inclusive)
	* @return the range of matching push notifications entries
	*/
	public java.util.List<com.liferay.pushnotifications.model.PushNotificationsEntry> findByC_P(
		long createTime, long parentPushNotificationsEntryId, int start, int end);

	/**
	* Returns an ordered range of all the push notifications entries where createTime &gt; &#63; and parentPushNotificationsEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.pushnotifications.model.impl.PushNotificationsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createTime the create time
	* @param parentPushNotificationsEntryId the parent push notifications entry ID
	* @param start the lower bound of the range of push notifications entries
	* @param end the upper bound of the range of push notifications entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching push notifications entries
	*/
	public java.util.List<com.liferay.pushnotifications.model.PushNotificationsEntry> findByC_P(
		long createTime, long parentPushNotificationsEntryId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.pushnotifications.model.PushNotificationsEntry> orderByComparator);

	/**
	* Returns the first push notifications entry in the ordered set where createTime &gt; &#63; and parentPushNotificationsEntryId = &#63;.
	*
	* @param createTime the create time
	* @param parentPushNotificationsEntryId the parent push notifications entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push notifications entry
	* @throws com.liferay.pushnotifications.NoSuchEntryException if a matching push notifications entry could not be found
	*/
	public com.liferay.pushnotifications.model.PushNotificationsEntry findByC_P_First(
		long createTime, long parentPushNotificationsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.pushnotifications.model.PushNotificationsEntry> orderByComparator)
		throws com.liferay.pushnotifications.NoSuchEntryException;

	/**
	* Returns the first push notifications entry in the ordered set where createTime &gt; &#63; and parentPushNotificationsEntryId = &#63;.
	*
	* @param createTime the create time
	* @param parentPushNotificationsEntryId the parent push notifications entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push notifications entry, or <code>null</code> if a matching push notifications entry could not be found
	*/
	public com.liferay.pushnotifications.model.PushNotificationsEntry fetchByC_P_First(
		long createTime, long parentPushNotificationsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.pushnotifications.model.PushNotificationsEntry> orderByComparator);

	/**
	* Returns the last push notifications entry in the ordered set where createTime &gt; &#63; and parentPushNotificationsEntryId = &#63;.
	*
	* @param createTime the create time
	* @param parentPushNotificationsEntryId the parent push notifications entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push notifications entry
	* @throws com.liferay.pushnotifications.NoSuchEntryException if a matching push notifications entry could not be found
	*/
	public com.liferay.pushnotifications.model.PushNotificationsEntry findByC_P_Last(
		long createTime, long parentPushNotificationsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.pushnotifications.model.PushNotificationsEntry> orderByComparator)
		throws com.liferay.pushnotifications.NoSuchEntryException;

	/**
	* Returns the last push notifications entry in the ordered set where createTime &gt; &#63; and parentPushNotificationsEntryId = &#63;.
	*
	* @param createTime the create time
	* @param parentPushNotificationsEntryId the parent push notifications entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push notifications entry, or <code>null</code> if a matching push notifications entry could not be found
	*/
	public com.liferay.pushnotifications.model.PushNotificationsEntry fetchByC_P_Last(
		long createTime, long parentPushNotificationsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.pushnotifications.model.PushNotificationsEntry> orderByComparator);

	/**
	* Returns the push notifications entries before and after the current push notifications entry in the ordered set where createTime &gt; &#63; and parentPushNotificationsEntryId = &#63;.
	*
	* @param pushNotificationsEntryId the primary key of the current push notifications entry
	* @param createTime the create time
	* @param parentPushNotificationsEntryId the parent push notifications entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next push notifications entry
	* @throws com.liferay.pushnotifications.NoSuchEntryException if a push notifications entry with the primary key could not be found
	*/
	public com.liferay.pushnotifications.model.PushNotificationsEntry[] findByC_P_PrevAndNext(
		long pushNotificationsEntryId, long createTime,
		long parentPushNotificationsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.pushnotifications.model.PushNotificationsEntry> orderByComparator)
		throws com.liferay.pushnotifications.NoSuchEntryException;

	/**
	* Removes all the push notifications entries where createTime &gt; &#63; and parentPushNotificationsEntryId = &#63; from the database.
	*
	* @param createTime the create time
	* @param parentPushNotificationsEntryId the parent push notifications entry ID
	*/
	public void removeByC_P(long createTime, long parentPushNotificationsEntryId);

	/**
	* Returns the number of push notifications entries where createTime &gt; &#63; and parentPushNotificationsEntryId = &#63;.
	*
	* @param createTime the create time
	* @param parentPushNotificationsEntryId the parent push notifications entry ID
	* @return the number of matching push notifications entries
	*/
	public int countByC_P(long createTime, long parentPushNotificationsEntryId);

	/**
	* Caches the push notifications entry in the entity cache if it is enabled.
	*
	* @param pushNotificationsEntry the push notifications entry
	*/
	public void cacheResult(
		com.liferay.pushnotifications.model.PushNotificationsEntry pushNotificationsEntry);

	/**
	* Caches the push notifications entries in the entity cache if it is enabled.
	*
	* @param pushNotificationsEntries the push notifications entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.pushnotifications.model.PushNotificationsEntry> pushNotificationsEntries);

	/**
	* Creates a new push notifications entry with the primary key. Does not add the push notifications entry to the database.
	*
	* @param pushNotificationsEntryId the primary key for the new push notifications entry
	* @return the new push notifications entry
	*/
	public com.liferay.pushnotifications.model.PushNotificationsEntry create(
		long pushNotificationsEntryId);

	/**
	* Removes the push notifications entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pushNotificationsEntryId the primary key of the push notifications entry
	* @return the push notifications entry that was removed
	* @throws com.liferay.pushnotifications.NoSuchEntryException if a push notifications entry with the primary key could not be found
	*/
	public com.liferay.pushnotifications.model.PushNotificationsEntry remove(
		long pushNotificationsEntryId)
		throws com.liferay.pushnotifications.NoSuchEntryException;

	public com.liferay.pushnotifications.model.PushNotificationsEntry updateImpl(
		com.liferay.pushnotifications.model.PushNotificationsEntry pushNotificationsEntry);

	/**
	* Returns the push notifications entry with the primary key or throws a {@link com.liferay.pushnotifications.NoSuchEntryException} if it could not be found.
	*
	* @param pushNotificationsEntryId the primary key of the push notifications entry
	* @return the push notifications entry
	* @throws com.liferay.pushnotifications.NoSuchEntryException if a push notifications entry with the primary key could not be found
	*/
	public com.liferay.pushnotifications.model.PushNotificationsEntry findByPrimaryKey(
		long pushNotificationsEntryId)
		throws com.liferay.pushnotifications.NoSuchEntryException;

	/**
	* Returns the push notifications entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param pushNotificationsEntryId the primary key of the push notifications entry
	* @return the push notifications entry, or <code>null</code> if a push notifications entry with the primary key could not be found
	*/
	public com.liferay.pushnotifications.model.PushNotificationsEntry fetchByPrimaryKey(
		long pushNotificationsEntryId);

	@Override
	public java.util.Map<java.io.Serializable, com.liferay.pushnotifications.model.PushNotificationsEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the push notifications entries.
	*
	* @return the push notifications entries
	*/
	public java.util.List<com.liferay.pushnotifications.model.PushNotificationsEntry> findAll();

	/**
	* Returns a range of all the push notifications entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.pushnotifications.model.impl.PushNotificationsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of push notifications entries
	* @param end the upper bound of the range of push notifications entries (not inclusive)
	* @return the range of push notifications entries
	*/
	public java.util.List<com.liferay.pushnotifications.model.PushNotificationsEntry> findAll(
		int start, int end);

	/**
	* Returns an ordered range of all the push notifications entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.pushnotifications.model.impl.PushNotificationsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of push notifications entries
	* @param end the upper bound of the range of push notifications entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of push notifications entries
	*/
	public java.util.List<com.liferay.pushnotifications.model.PushNotificationsEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.pushnotifications.model.PushNotificationsEntry> orderByComparator);

	/**
	* Removes all the push notifications entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of push notifications entries.
	*
	* @return the number of push notifications entries
	*/
	public int countAll();
}