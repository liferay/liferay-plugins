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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.pushnotifications.model.PushNotificationsEntry;

import java.util.List;

/**
 * The persistence utility for the push notifications entry service. This utility wraps {@link PushNotificationsEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Bruno Farache
 * @see PushNotificationsEntryPersistence
 * @see PushNotificationsEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class PushNotificationsEntryUtil {
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
	public static void clearCache(PushNotificationsEntry pushNotificationsEntry) {
		getPersistence().clearCache(pushNotificationsEntry);
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
	public static List<PushNotificationsEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PushNotificationsEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PushNotificationsEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PushNotificationsEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static PushNotificationsEntry update(
		PushNotificationsEntry pushNotificationsEntry) {
		return getPersistence().update(pushNotificationsEntry);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static PushNotificationsEntry update(
		PushNotificationsEntry pushNotificationsEntry,
		ServiceContext serviceContext) {
		return getPersistence().update(pushNotificationsEntry, serviceContext);
	}

	/**
	* Returns all the push notifications entries where createTime &gt; &#63; and parentPushNotificationsEntryId = &#63;.
	*
	* @param createTime the create time
	* @param parentPushNotificationsEntryId the parent push notifications entry ID
	* @return the matching push notifications entries
	*/
	public static java.util.List<com.liferay.pushnotifications.model.PushNotificationsEntry> findByC_P(
		long createTime, long parentPushNotificationsEntryId) {
		return getPersistence()
				   .findByC_P(createTime, parentPushNotificationsEntryId);
	}

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
	public static java.util.List<com.liferay.pushnotifications.model.PushNotificationsEntry> findByC_P(
		long createTime, long parentPushNotificationsEntryId, int start, int end) {
		return getPersistence()
				   .findByC_P(createTime, parentPushNotificationsEntryId,
			start, end);
	}

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
	public static java.util.List<com.liferay.pushnotifications.model.PushNotificationsEntry> findByC_P(
		long createTime, long parentPushNotificationsEntryId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.pushnotifications.model.PushNotificationsEntry> orderByComparator) {
		return getPersistence()
				   .findByC_P(createTime, parentPushNotificationsEntryId,
			start, end, orderByComparator);
	}

	/**
	* Returns the first push notifications entry in the ordered set where createTime &gt; &#63; and parentPushNotificationsEntryId = &#63;.
	*
	* @param createTime the create time
	* @param parentPushNotificationsEntryId the parent push notifications entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push notifications entry
	* @throws com.liferay.pushnotifications.NoSuchEntryException if a matching push notifications entry could not be found
	*/
	public static com.liferay.pushnotifications.model.PushNotificationsEntry findByC_P_First(
		long createTime, long parentPushNotificationsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.pushnotifications.model.PushNotificationsEntry> orderByComparator)
		throws com.liferay.pushnotifications.NoSuchEntryException {
		return getPersistence()
				   .findByC_P_First(createTime, parentPushNotificationsEntryId,
			orderByComparator);
	}

	/**
	* Returns the first push notifications entry in the ordered set where createTime &gt; &#63; and parentPushNotificationsEntryId = &#63;.
	*
	* @param createTime the create time
	* @param parentPushNotificationsEntryId the parent push notifications entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push notifications entry, or <code>null</code> if a matching push notifications entry could not be found
	*/
	public static com.liferay.pushnotifications.model.PushNotificationsEntry fetchByC_P_First(
		long createTime, long parentPushNotificationsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.pushnotifications.model.PushNotificationsEntry> orderByComparator) {
		return getPersistence()
				   .fetchByC_P_First(createTime,
			parentPushNotificationsEntryId, orderByComparator);
	}

	/**
	* Returns the last push notifications entry in the ordered set where createTime &gt; &#63; and parentPushNotificationsEntryId = &#63;.
	*
	* @param createTime the create time
	* @param parentPushNotificationsEntryId the parent push notifications entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push notifications entry
	* @throws com.liferay.pushnotifications.NoSuchEntryException if a matching push notifications entry could not be found
	*/
	public static com.liferay.pushnotifications.model.PushNotificationsEntry findByC_P_Last(
		long createTime, long parentPushNotificationsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.pushnotifications.model.PushNotificationsEntry> orderByComparator)
		throws com.liferay.pushnotifications.NoSuchEntryException {
		return getPersistence()
				   .findByC_P_Last(createTime, parentPushNotificationsEntryId,
			orderByComparator);
	}

	/**
	* Returns the last push notifications entry in the ordered set where createTime &gt; &#63; and parentPushNotificationsEntryId = &#63;.
	*
	* @param createTime the create time
	* @param parentPushNotificationsEntryId the parent push notifications entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push notifications entry, or <code>null</code> if a matching push notifications entry could not be found
	*/
	public static com.liferay.pushnotifications.model.PushNotificationsEntry fetchByC_P_Last(
		long createTime, long parentPushNotificationsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.pushnotifications.model.PushNotificationsEntry> orderByComparator) {
		return getPersistence()
				   .fetchByC_P_Last(createTime, parentPushNotificationsEntryId,
			orderByComparator);
	}

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
	public static com.liferay.pushnotifications.model.PushNotificationsEntry[] findByC_P_PrevAndNext(
		long pushNotificationsEntryId, long createTime,
		long parentPushNotificationsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.pushnotifications.model.PushNotificationsEntry> orderByComparator)
		throws com.liferay.pushnotifications.NoSuchEntryException {
		return getPersistence()
				   .findByC_P_PrevAndNext(pushNotificationsEntryId, createTime,
			parentPushNotificationsEntryId, orderByComparator);
	}

	/**
	* Removes all the push notifications entries where createTime &gt; &#63; and parentPushNotificationsEntryId = &#63; from the database.
	*
	* @param createTime the create time
	* @param parentPushNotificationsEntryId the parent push notifications entry ID
	*/
	public static void removeByC_P(long createTime,
		long parentPushNotificationsEntryId) {
		getPersistence().removeByC_P(createTime, parentPushNotificationsEntryId);
	}

	/**
	* Returns the number of push notifications entries where createTime &gt; &#63; and parentPushNotificationsEntryId = &#63;.
	*
	* @param createTime the create time
	* @param parentPushNotificationsEntryId the parent push notifications entry ID
	* @return the number of matching push notifications entries
	*/
	public static int countByC_P(long createTime,
		long parentPushNotificationsEntryId) {
		return getPersistence()
				   .countByC_P(createTime, parentPushNotificationsEntryId);
	}

	/**
	* Caches the push notifications entry in the entity cache if it is enabled.
	*
	* @param pushNotificationsEntry the push notifications entry
	*/
	public static void cacheResult(
		com.liferay.pushnotifications.model.PushNotificationsEntry pushNotificationsEntry) {
		getPersistence().cacheResult(pushNotificationsEntry);
	}

	/**
	* Caches the push notifications entries in the entity cache if it is enabled.
	*
	* @param pushNotificationsEntries the push notifications entries
	*/
	public static void cacheResult(
		java.util.List<com.liferay.pushnotifications.model.PushNotificationsEntry> pushNotificationsEntries) {
		getPersistence().cacheResult(pushNotificationsEntries);
	}

	/**
	* Creates a new push notifications entry with the primary key. Does not add the push notifications entry to the database.
	*
	* @param pushNotificationsEntryId the primary key for the new push notifications entry
	* @return the new push notifications entry
	*/
	public static com.liferay.pushnotifications.model.PushNotificationsEntry create(
		long pushNotificationsEntryId) {
		return getPersistence().create(pushNotificationsEntryId);
	}

	/**
	* Removes the push notifications entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pushNotificationsEntryId the primary key of the push notifications entry
	* @return the push notifications entry that was removed
	* @throws com.liferay.pushnotifications.NoSuchEntryException if a push notifications entry with the primary key could not be found
	*/
	public static com.liferay.pushnotifications.model.PushNotificationsEntry remove(
		long pushNotificationsEntryId)
		throws com.liferay.pushnotifications.NoSuchEntryException {
		return getPersistence().remove(pushNotificationsEntryId);
	}

	public static com.liferay.pushnotifications.model.PushNotificationsEntry updateImpl(
		com.liferay.pushnotifications.model.PushNotificationsEntry pushNotificationsEntry) {
		return getPersistence().updateImpl(pushNotificationsEntry);
	}

	/**
	* Returns the push notifications entry with the primary key or throws a {@link com.liferay.pushnotifications.NoSuchEntryException} if it could not be found.
	*
	* @param pushNotificationsEntryId the primary key of the push notifications entry
	* @return the push notifications entry
	* @throws com.liferay.pushnotifications.NoSuchEntryException if a push notifications entry with the primary key could not be found
	*/
	public static com.liferay.pushnotifications.model.PushNotificationsEntry findByPrimaryKey(
		long pushNotificationsEntryId)
		throws com.liferay.pushnotifications.NoSuchEntryException {
		return getPersistence().findByPrimaryKey(pushNotificationsEntryId);
	}

	/**
	* Returns the push notifications entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param pushNotificationsEntryId the primary key of the push notifications entry
	* @return the push notifications entry, or <code>null</code> if a push notifications entry with the primary key could not be found
	*/
	public static com.liferay.pushnotifications.model.PushNotificationsEntry fetchByPrimaryKey(
		long pushNotificationsEntryId) {
		return getPersistence().fetchByPrimaryKey(pushNotificationsEntryId);
	}

	public static java.util.Map<java.io.Serializable, com.liferay.pushnotifications.model.PushNotificationsEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the push notifications entries.
	*
	* @return the push notifications entries
	*/
	public static java.util.List<com.liferay.pushnotifications.model.PushNotificationsEntry> findAll() {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.pushnotifications.model.PushNotificationsEntry> findAll(
		int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.pushnotifications.model.PushNotificationsEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.pushnotifications.model.PushNotificationsEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the push notifications entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of push notifications entries.
	*
	* @return the number of push notifications entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static PushNotificationsEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (PushNotificationsEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.pushnotifications.service.ClpSerializer.getServletContextName(),
					PushNotificationsEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(PushNotificationsEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(PushNotificationsEntryPersistence persistence) {
	}

	private static PushNotificationsEntryPersistence _persistence;
}