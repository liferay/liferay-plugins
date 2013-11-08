/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.testblob.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.testblob.model.BlobEntry;

import java.util.List;

/**
 * The persistence utility for the blob entry service. This utility wraps {@link BlobEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BlobEntryPersistence
 * @see BlobEntryPersistenceImpl
 * @generated
 */
public class BlobEntryUtil {
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
	public static void clearCache(BlobEntry blobEntry) {
		getPersistence().clearCache(blobEntry);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<BlobEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<BlobEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<BlobEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static BlobEntry update(BlobEntry blobEntry)
		throws SystemException {
		return getPersistence().update(blobEntry);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static BlobEntry update(BlobEntry blobEntry,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(blobEntry, serviceContext);
	}

	/**
	* Returns all the blob entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching blob entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.testblob.model.BlobEntry> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the blob entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testblob.model.impl.BlobEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of blob entries
	* @param end the upper bound of the range of blob entries (not inclusive)
	* @return the range of matching blob entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.testblob.model.BlobEntry> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the blob entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testblob.model.impl.BlobEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of blob entries
	* @param end the upper bound of the range of blob entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching blob entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.testblob.model.BlobEntry> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first blob entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching blob entry
	* @throws com.liferay.testblob.NoSuchBlobEntryException if a matching blob entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.testblob.model.BlobEntry findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.testblob.NoSuchBlobEntryException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first blob entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching blob entry, or <code>null</code> if a matching blob entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.testblob.model.BlobEntry fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last blob entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching blob entry
	* @throws com.liferay.testblob.NoSuchBlobEntryException if a matching blob entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.testblob.model.BlobEntry findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.testblob.NoSuchBlobEntryException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last blob entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching blob entry, or <code>null</code> if a matching blob entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.testblob.model.BlobEntry fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the blob entries before and after the current blob entry in the ordered set where uuid = &#63;.
	*
	* @param blobEntryId the primary key of the current blob entry
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next blob entry
	* @throws com.liferay.testblob.NoSuchBlobEntryException if a blob entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.testblob.model.BlobEntry[] findByUuid_PrevAndNext(
		long blobEntryId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.testblob.NoSuchBlobEntryException {
		return getPersistence()
				   .findByUuid_PrevAndNext(blobEntryId, uuid, orderByComparator);
	}

	/**
	* Removes all the blob entries where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of blob entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching blob entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Caches the blob entry in the entity cache if it is enabled.
	*
	* @param blobEntry the blob entry
	*/
	public static void cacheResult(
		com.liferay.testblob.model.BlobEntry blobEntry) {
		getPersistence().cacheResult(blobEntry);
	}

	/**
	* Caches the blob entries in the entity cache if it is enabled.
	*
	* @param blobEntries the blob entries
	*/
	public static void cacheResult(
		java.util.List<com.liferay.testblob.model.BlobEntry> blobEntries) {
		getPersistence().cacheResult(blobEntries);
	}

	/**
	* Creates a new blob entry with the primary key. Does not add the blob entry to the database.
	*
	* @param blobEntryId the primary key for the new blob entry
	* @return the new blob entry
	*/
	public static com.liferay.testblob.model.BlobEntry create(long blobEntryId) {
		return getPersistence().create(blobEntryId);
	}

	/**
	* Removes the blob entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param blobEntryId the primary key of the blob entry
	* @return the blob entry that was removed
	* @throws com.liferay.testblob.NoSuchBlobEntryException if a blob entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.testblob.model.BlobEntry remove(long blobEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.testblob.NoSuchBlobEntryException {
		return getPersistence().remove(blobEntryId);
	}

	public static com.liferay.testblob.model.BlobEntry updateImpl(
		com.liferay.testblob.model.BlobEntry blobEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(blobEntry);
	}

	/**
	* Returns the blob entry with the primary key or throws a {@link com.liferay.testblob.NoSuchBlobEntryException} if it could not be found.
	*
	* @param blobEntryId the primary key of the blob entry
	* @return the blob entry
	* @throws com.liferay.testblob.NoSuchBlobEntryException if a blob entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.testblob.model.BlobEntry findByPrimaryKey(
		long blobEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.testblob.NoSuchBlobEntryException {
		return getPersistence().findByPrimaryKey(blobEntryId);
	}

	/**
	* Returns the blob entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param blobEntryId the primary key of the blob entry
	* @return the blob entry, or <code>null</code> if a blob entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.testblob.model.BlobEntry fetchByPrimaryKey(
		long blobEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(blobEntryId);
	}

	/**
	* Returns all the blob entries.
	*
	* @return the blob entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.testblob.model.BlobEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the blob entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testblob.model.impl.BlobEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of blob entries
	* @param end the upper bound of the range of blob entries (not inclusive)
	* @return the range of blob entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.testblob.model.BlobEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the blob entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.testblob.model.impl.BlobEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of blob entries
	* @param end the upper bound of the range of blob entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of blob entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.testblob.model.BlobEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the blob entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of blob entries.
	*
	* @return the number of blob entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static BlobEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (BlobEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.testblob.service.ClpSerializer.getServletContextName(),
					BlobEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(BlobEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(BlobEntryPersistence persistence) {
	}

	private static BlobEntryPersistence _persistence;
}