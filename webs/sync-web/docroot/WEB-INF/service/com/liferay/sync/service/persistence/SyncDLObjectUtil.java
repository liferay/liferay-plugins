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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.sync.model.SyncDLObject;

import java.util.List;

/**
 * The persistence utility for the sync d l object service. This utility wraps {@link SyncDLObjectPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SyncDLObjectPersistence
 * @see SyncDLObjectPersistenceImpl
 * @generated
 */
public class SyncDLObjectUtil {
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
	public static void clearCache(SyncDLObject syncDLObject) {
		getPersistence().clearCache(syncDLObject);
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
	public static List<SyncDLObject> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SyncDLObject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SyncDLObject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static SyncDLObject update(SyncDLObject syncDLObject)
		throws SystemException {
		return getPersistence().update(syncDLObject);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static SyncDLObject update(SyncDLObject syncDLObject,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(syncDLObject, serviceContext);
	}

	/**
	* Returns the sync d l object where typePK = &#63; or throws a {@link com.liferay.sync.NoSuchDLObjectException} if it could not be found.
	*
	* @param typePK the type p k
	* @return the matching sync d l object
	* @throws com.liferay.sync.NoSuchDLObjectException if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject findByTypePK(long typePK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence().findByTypePK(typePK);
	}

	/**
	* Returns the sync d l object where typePK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param typePK the type p k
	* @return the matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject fetchByTypePK(long typePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByTypePK(typePK);
	}

	/**
	* Returns the sync d l object where typePK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param typePK the type p k
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject fetchByTypePK(
		long typePK, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByTypePK(typePK, retrieveFromCache);
	}

	/**
	* Removes the sync d l object where typePK = &#63; from the database.
	*
	* @param typePK the type p k
	* @return the sync d l object that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject removeByTypePK(
		long typePK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence().removeByTypePK(typePK);
	}

	/**
	* Returns the number of sync d l objects where typePK = &#63;.
	*
	* @param typePK the type p k
	* @return the number of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTypePK(long typePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTypePK(typePK);
	}

	/**
	* Returns all the sync d l objects where companyId = &#63; and modifiedTime &gt; &#63; and repositoryId = &#63;.
	*
	* @param companyId the company ID
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @return the matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByC_M_R(
		long companyId, long modifiedTime, long repositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_M_R(companyId, modifiedTime, repositoryId);
	}

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
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByC_M_R(
		long companyId, long modifiedTime, long repositoryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_M_R(companyId, modifiedTime, repositoryId, start,
			end);
	}

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
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByC_M_R(
		long companyId, long modifiedTime, long repositoryId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_M_R(companyId, modifiedTime, repositoryId, start,
			end, orderByComparator);
	}

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
	public static com.liferay.sync.model.SyncDLObject findByC_M_R_First(
		long companyId, long modifiedTime, long repositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence()
				   .findByC_M_R_First(companyId, modifiedTime, repositoryId,
			orderByComparator);
	}

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
	public static com.liferay.sync.model.SyncDLObject fetchByC_M_R_First(
		long companyId, long modifiedTime, long repositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_M_R_First(companyId, modifiedTime, repositoryId,
			orderByComparator);
	}

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
	public static com.liferay.sync.model.SyncDLObject findByC_M_R_Last(
		long companyId, long modifiedTime, long repositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence()
				   .findByC_M_R_Last(companyId, modifiedTime, repositoryId,
			orderByComparator);
	}

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
	public static com.liferay.sync.model.SyncDLObject fetchByC_M_R_Last(
		long companyId, long modifiedTime, long repositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_M_R_Last(companyId, modifiedTime, repositoryId,
			orderByComparator);
	}

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
	public static com.liferay.sync.model.SyncDLObject[] findByC_M_R_PrevAndNext(
		long objectId, long companyId, long modifiedTime, long repositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence()
				   .findByC_M_R_PrevAndNext(objectId, companyId, modifiedTime,
			repositoryId, orderByComparator);
	}

	/**
	* Removes all the sync d l objects where companyId = &#63; and modifiedTime &gt; &#63; and repositoryId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_M_R(long companyId, long modifiedTime,
		long repositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_M_R(companyId, modifiedTime, repositoryId);
	}

	/**
	* Returns the number of sync d l objects where companyId = &#63; and modifiedTime &gt; &#63; and repositoryId = &#63;.
	*
	* @param companyId the company ID
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @return the number of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_M_R(long companyId, long modifiedTime,
		long repositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByC_M_R(companyId, modifiedTime, repositoryId);
	}

	/**
	* Caches the sync d l object in the entity cache if it is enabled.
	*
	* @param syncDLObject the sync d l object
	*/
	public static void cacheResult(
		com.liferay.sync.model.SyncDLObject syncDLObject) {
		getPersistence().cacheResult(syncDLObject);
	}

	/**
	* Caches the sync d l objects in the entity cache if it is enabled.
	*
	* @param syncDLObjects the sync d l objects
	*/
	public static void cacheResult(
		java.util.List<com.liferay.sync.model.SyncDLObject> syncDLObjects) {
		getPersistence().cacheResult(syncDLObjects);
	}

	/**
	* Creates a new sync d l object with the primary key. Does not add the sync d l object to the database.
	*
	* @param objectId the primary key for the new sync d l object
	* @return the new sync d l object
	*/
	public static com.liferay.sync.model.SyncDLObject create(long objectId) {
		return getPersistence().create(objectId);
	}

	/**
	* Removes the sync d l object with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param objectId the primary key of the sync d l object
	* @return the sync d l object that was removed
	* @throws com.liferay.sync.NoSuchDLObjectException if a sync d l object with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject remove(long objectId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence().remove(objectId);
	}

	public static com.liferay.sync.model.SyncDLObject updateImpl(
		com.liferay.sync.model.SyncDLObject syncDLObject)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(syncDLObject);
	}

	/**
	* Returns the sync d l object with the primary key or throws a {@link com.liferay.sync.NoSuchDLObjectException} if it could not be found.
	*
	* @param objectId the primary key of the sync d l object
	* @return the sync d l object
	* @throws com.liferay.sync.NoSuchDLObjectException if a sync d l object with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject findByPrimaryKey(
		long objectId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence().findByPrimaryKey(objectId);
	}

	/**
	* Returns the sync d l object with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param objectId the primary key of the sync d l object
	* @return the sync d l object, or <code>null</code> if a sync d l object with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject fetchByPrimaryKey(
		long objectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(objectId);
	}

	/**
	* Returns all the sync d l objects.
	*
	* @return the sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the sync d l objects from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of sync d l objects.
	*
	* @return the number of sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static SyncDLObjectPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SyncDLObjectPersistence)PortletBeanLocatorUtil.locate(com.liferay.sync.service.ClpSerializer.getServletContextName(),
					SyncDLObjectPersistence.class.getName());

			ReferenceRegistry.registerReference(SyncDLObjectUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(SyncDLObjectPersistence persistence) {
	}

	private static SyncDLObjectPersistence _persistence;
}