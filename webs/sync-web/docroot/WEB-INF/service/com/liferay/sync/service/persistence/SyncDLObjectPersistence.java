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

package com.liferay.sync.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.sync.model.SyncDLObject;

/**
 * The persistence interface for the sync d l object service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SyncDLObjectPersistenceImpl
 * @see SyncDLObjectUtil
 * @generated
 */
public interface SyncDLObjectPersistence extends BasePersistence<SyncDLObject> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SyncDLObjectUtil} to access the sync d l object persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the sync d l object where typePK = &#63; or throws a {@link com.liferay.sync.NoSuchDLObjectException} if it could not be found.
	*
	* @param typePK the type p k
	* @return the matching sync d l object
	* @throws com.liferay.sync.NoSuchDLObjectException if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sync.model.SyncDLObject findByTypePK(long typePK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException;

	/**
	* Returns the sync d l object where typePK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param typePK the type p k
	* @return the matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sync.model.SyncDLObject fetchByTypePK(long typePK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the sync d l object where typePK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param typePK the type p k
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sync.model.SyncDLObject fetchByTypePK(long typePK,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the sync d l object where typePK = &#63; from the database.
	*
	* @param typePK the type p k
	* @return the sync d l object that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sync.model.SyncDLObject removeByTypePK(long typePK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException;

	/**
	* Returns the number of sync d l objects where typePK = &#63;.
	*
	* @param typePK the type p k
	* @return the number of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public int countByTypePK(long typePK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the sync d l objects where companyId = &#63; and modifiedTime &gt; &#63; and repositoryId = &#63;.
	*
	* @param companyId the company ID
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @return the matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.sync.model.SyncDLObject> findByC_M_R(
		long companyId, long modifiedTime, long repositoryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the sync d l objects where companyId = &#63; and modifiedTime &gt; &#63; and repositoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @return the range of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.sync.model.SyncDLObject> findByC_M_R(
		long companyId, long modifiedTime, long repositoryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the sync d l objects where companyId = &#63; and modifiedTime &gt; &#63; and repositoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.sync.model.SyncDLObject> findByC_M_R(
		long companyId, long modifiedTime, long repositoryId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first sync d l object in the ordered set where companyId = &#63; and modifiedTime &gt; &#63; and repositoryId = &#63;.
	*
	* @param companyId the company ID
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync d l object
	* @throws com.liferay.sync.NoSuchDLObjectException if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sync.model.SyncDLObject findByC_M_R_First(
		long companyId, long modifiedTime, long repositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException;

	/**
	* Returns the first sync d l object in the ordered set where companyId = &#63; and modifiedTime &gt; &#63; and repositoryId = &#63;.
	*
	* @param companyId the company ID
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sync.model.SyncDLObject fetchByC_M_R_First(
		long companyId, long modifiedTime, long repositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last sync d l object in the ordered set where companyId = &#63; and modifiedTime &gt; &#63; and repositoryId = &#63;.
	*
	* @param companyId the company ID
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync d l object
	* @throws com.liferay.sync.NoSuchDLObjectException if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sync.model.SyncDLObject findByC_M_R_Last(
		long companyId, long modifiedTime, long repositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException;

	/**
	* Returns the last sync d l object in the ordered set where companyId = &#63; and modifiedTime &gt; &#63; and repositoryId = &#63;.
	*
	* @param companyId the company ID
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sync.model.SyncDLObject fetchByC_M_R_Last(
		long companyId, long modifiedTime, long repositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the sync d l objects before and after the current sync d l object in the ordered set where companyId = &#63; and modifiedTime &gt; &#63; and repositoryId = &#63;.
	*
	* @param objectId the primary key of the current sync d l object
	* @param companyId the company ID
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync d l object
	* @throws com.liferay.sync.NoSuchDLObjectException if a sync d l object with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sync.model.SyncDLObject[] findByC_M_R_PrevAndNext(
		long objectId, long companyId, long modifiedTime, long repositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException;

	/**
	* Removes all the sync d l objects where companyId = &#63; and modifiedTime &gt; &#63; and repositoryId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_M_R(long companyId, long modifiedTime,
		long repositoryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of sync d l objects where companyId = &#63; and modifiedTime &gt; &#63; and repositoryId = &#63;.
	*
	* @param companyId the company ID
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @return the number of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_M_R(long companyId, long modifiedTime, long repositoryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the sync d l object in the entity cache if it is enabled.
	*
	* @param syncDLObject the sync d l object
	*/
	public void cacheResult(com.liferay.sync.model.SyncDLObject syncDLObject);

	/**
	* Caches the sync d l objects in the entity cache if it is enabled.
	*
	* @param syncDLObjects the sync d l objects
	*/
	public void cacheResult(
		java.util.List<com.liferay.sync.model.SyncDLObject> syncDLObjects);

	/**
	* Creates a new sync d l object with the primary key. Does not add the sync d l object to the database.
	*
	* @param objectId the primary key for the new sync d l object
	* @return the new sync d l object
	*/
	public com.liferay.sync.model.SyncDLObject create(long objectId);

	/**
	* Removes the sync d l object with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param objectId the primary key of the sync d l object
	* @return the sync d l object that was removed
	* @throws com.liferay.sync.NoSuchDLObjectException if a sync d l object with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sync.model.SyncDLObject remove(long objectId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException;

	public com.liferay.sync.model.SyncDLObject updateImpl(
		com.liferay.sync.model.SyncDLObject syncDLObject)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the sync d l object with the primary key or throws a {@link com.liferay.sync.NoSuchDLObjectException} if it could not be found.
	*
	* @param objectId the primary key of the sync d l object
	* @return the sync d l object
	* @throws com.liferay.sync.NoSuchDLObjectException if a sync d l object with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sync.model.SyncDLObject findByPrimaryKey(long objectId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException;

	/**
	* Returns the sync d l object with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param objectId the primary key of the sync d l object
	* @return the sync d l object, or <code>null</code> if a sync d l object with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sync.model.SyncDLObject fetchByPrimaryKey(long objectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the sync d l objects.
	*
	* @return the sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.sync.model.SyncDLObject> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the sync d l objects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @return the range of sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.sync.model.SyncDLObject> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the sync d l objects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.sync.model.SyncDLObject> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the sync d l objects from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of sync d l objects.
	*
	* @return the number of sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}