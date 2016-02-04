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
	* Returns all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63;.
	*
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @return the matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByM_R(
		long modifiedTime, long repositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByM_R(modifiedTime, repositoryId);
	}

	/**
	* Returns a range of all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @return the range of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByM_R(
		long modifiedTime, long repositoryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByM_R(modifiedTime, repositoryId, start, end);
	}

	/**
	* Returns an ordered range of all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByM_R(
		long modifiedTime, long repositoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByM_R(modifiedTime, repositoryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByM_R(
		long modifiedTime, long repositoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByM_R(modifiedTime, repositoryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first sync d l object in the ordered set where modifiedTime &gt; &#63; and repositoryId = &#63;.
	*
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync d l object
	* @throws com.liferay.sync.NoSuchDLObjectException if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject findByM_R_First(
		long modifiedTime, long repositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence()
				   .findByM_R_First(modifiedTime, repositoryId,
			orderByComparator);
	}

	/**
	* Returns the first sync d l object in the ordered set where modifiedTime &gt; &#63; and repositoryId = &#63;.
	*
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject fetchByM_R_First(
		long modifiedTime, long repositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByM_R_First(modifiedTime, repositoryId,
			orderByComparator);
	}

	/**
	* Returns the last sync d l object in the ordered set where modifiedTime &gt; &#63; and repositoryId = &#63;.
	*
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync d l object
	* @throws com.liferay.sync.NoSuchDLObjectException if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject findByM_R_Last(
		long modifiedTime, long repositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence()
				   .findByM_R_Last(modifiedTime, repositoryId, orderByComparator);
	}

	/**
	* Returns the last sync d l object in the ordered set where modifiedTime &gt; &#63; and repositoryId = &#63;.
	*
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject fetchByM_R_Last(
		long modifiedTime, long repositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByM_R_Last(modifiedTime, repositoryId,
			orderByComparator);
	}

	/**
	* Returns the sync d l objects before and after the current sync d l object in the ordered set where modifiedTime &gt; &#63; and repositoryId = &#63;.
	*
	* @param syncDLObjectId the primary key of the current sync d l object
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync d l object
	* @throws com.liferay.sync.NoSuchDLObjectException if a sync d l object with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject[] findByM_R_PrevAndNext(
		long syncDLObjectId, long modifiedTime, long repositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence()
				   .findByM_R_PrevAndNext(syncDLObjectId, modifiedTime,
			repositoryId, orderByComparator);
	}

	/**
	* Removes all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63; from the database.
	*
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByM_R(long modifiedTime, long repositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByM_R(modifiedTime, repositoryId);
	}

	/**
	* Returns the number of sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63;.
	*
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @return the number of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static int countByM_R(long modifiedTime, long repositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByM_R(modifiedTime, repositoryId);
	}

	/**
	* Returns all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63;.
	*
	* @param repositoryId the repository ID
	* @param parentFolderId the parent folder ID
	* @return the matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByR_P(
		long repositoryId, long parentFolderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_P(repositoryId, parentFolderId);
	}

	/**
	* Returns a range of all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param repositoryId the repository ID
	* @param parentFolderId the parent folder ID
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @return the range of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByR_P(
		long repositoryId, long parentFolderId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_P(repositoryId, parentFolderId, start, end);
	}

	/**
	* Returns an ordered range of all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param repositoryId the repository ID
	* @param parentFolderId the parent folder ID
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByR_P(
		long repositoryId, long parentFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_P(repositoryId, parentFolderId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param repositoryId the repository ID
	* @param parentFolderId the parent folder ID
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByR_P(
		long repositoryId, long parentFolderId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_P(repositoryId, parentFolderId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first sync d l object in the ordered set where repositoryId = &#63; and parentFolderId = &#63;.
	*
	* @param repositoryId the repository ID
	* @param parentFolderId the parent folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync d l object
	* @throws com.liferay.sync.NoSuchDLObjectException if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject findByR_P_First(
		long repositoryId, long parentFolderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence()
				   .findByR_P_First(repositoryId, parentFolderId,
			orderByComparator);
	}

	/**
	* Returns the first sync d l object in the ordered set where repositoryId = &#63; and parentFolderId = &#63;.
	*
	* @param repositoryId the repository ID
	* @param parentFolderId the parent folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject fetchByR_P_First(
		long repositoryId, long parentFolderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_P_First(repositoryId, parentFolderId,
			orderByComparator);
	}

	/**
	* Returns the last sync d l object in the ordered set where repositoryId = &#63; and parentFolderId = &#63;.
	*
	* @param repositoryId the repository ID
	* @param parentFolderId the parent folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync d l object
	* @throws com.liferay.sync.NoSuchDLObjectException if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject findByR_P_Last(
		long repositoryId, long parentFolderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence()
				   .findByR_P_Last(repositoryId, parentFolderId,
			orderByComparator);
	}

	/**
	* Returns the last sync d l object in the ordered set where repositoryId = &#63; and parentFolderId = &#63;.
	*
	* @param repositoryId the repository ID
	* @param parentFolderId the parent folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject fetchByR_P_Last(
		long repositoryId, long parentFolderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_P_Last(repositoryId, parentFolderId,
			orderByComparator);
	}

	/**
	* Returns the sync d l objects before and after the current sync d l object in the ordered set where repositoryId = &#63; and parentFolderId = &#63;.
	*
	* @param syncDLObjectId the primary key of the current sync d l object
	* @param repositoryId the repository ID
	* @param parentFolderId the parent folder ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync d l object
	* @throws com.liferay.sync.NoSuchDLObjectException if a sync d l object with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject[] findByR_P_PrevAndNext(
		long syncDLObjectId, long repositoryId, long parentFolderId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence()
				   .findByR_P_PrevAndNext(syncDLObjectId, repositoryId,
			parentFolderId, orderByComparator);
	}

	/**
	* Removes all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63; from the database.
	*
	* @param repositoryId the repository ID
	* @param parentFolderId the parent folder ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByR_P(long repositoryId, long parentFolderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByR_P(repositoryId, parentFolderId);
	}

	/**
	* Returns the number of sync d l objects where repositoryId = &#63; and parentFolderId = &#63;.
	*
	* @param repositoryId the repository ID
	* @param parentFolderId the parent folder ID
	* @return the number of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_P(long repositoryId, long parentFolderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_P(repositoryId, parentFolderId);
	}

	/**
	* Returns all the sync d l objects where repositoryId = &#63; and type = &#63;.
	*
	* @param repositoryId the repository ID
	* @param type the type
	* @return the matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByR_T(
		long repositoryId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_T(repositoryId, type);
	}

	/**
	* Returns a range of all the sync d l objects where repositoryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param repositoryId the repository ID
	* @param type the type
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @return the range of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByR_T(
		long repositoryId, java.lang.String type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_T(repositoryId, type, start, end);
	}

	/**
	* Returns an ordered range of all the sync d l objects where repositoryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param repositoryId the repository ID
	* @param type the type
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByR_T(
		long repositoryId, java.lang.String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_T(repositoryId, type, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync d l objects where repositoryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param repositoryId the repository ID
	* @param type the type
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByR_T(
		long repositoryId, java.lang.String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_T(repositoryId, type, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first sync d l object in the ordered set where repositoryId = &#63; and type = &#63;.
	*
	* @param repositoryId the repository ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync d l object
	* @throws com.liferay.sync.NoSuchDLObjectException if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject findByR_T_First(
		long repositoryId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence()
				   .findByR_T_First(repositoryId, type, orderByComparator);
	}

	/**
	* Returns the first sync d l object in the ordered set where repositoryId = &#63; and type = &#63;.
	*
	* @param repositoryId the repository ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject fetchByR_T_First(
		long repositoryId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_T_First(repositoryId, type, orderByComparator);
	}

	/**
	* Returns the last sync d l object in the ordered set where repositoryId = &#63; and type = &#63;.
	*
	* @param repositoryId the repository ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync d l object
	* @throws com.liferay.sync.NoSuchDLObjectException if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject findByR_T_Last(
		long repositoryId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence()
				   .findByR_T_Last(repositoryId, type, orderByComparator);
	}

	/**
	* Returns the last sync d l object in the ordered set where repositoryId = &#63; and type = &#63;.
	*
	* @param repositoryId the repository ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject fetchByR_T_Last(
		long repositoryId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_T_Last(repositoryId, type, orderByComparator);
	}

	/**
	* Returns the sync d l objects before and after the current sync d l object in the ordered set where repositoryId = &#63; and type = &#63;.
	*
	* @param syncDLObjectId the primary key of the current sync d l object
	* @param repositoryId the repository ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync d l object
	* @throws com.liferay.sync.NoSuchDLObjectException if a sync d l object with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject[] findByR_T_PrevAndNext(
		long syncDLObjectId, long repositoryId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence()
				   .findByR_T_PrevAndNext(syncDLObjectId, repositoryId, type,
			orderByComparator);
	}

	/**
	* Removes all the sync d l objects where repositoryId = &#63; and type = &#63; from the database.
	*
	* @param repositoryId the repository ID
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByR_T(long repositoryId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByR_T(repositoryId, type);
	}

	/**
	* Returns the number of sync d l objects where repositoryId = &#63; and type = &#63;.
	*
	* @param repositoryId the repository ID
	* @param type the type
	* @return the number of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_T(long repositoryId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_T(repositoryId, type);
	}

	/**
	* Returns all the sync d l objects where version = &#63; and type = &#63;.
	*
	* @param version the version
	* @param type the type
	* @return the matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByV_T(
		java.lang.String version, java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByV_T(version, type);
	}

	/**
	* Returns a range of all the sync d l objects where version = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param version the version
	* @param type the type
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @return the range of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByV_T(
		java.lang.String version, java.lang.String type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByV_T(version, type, start, end);
	}

	/**
	* Returns an ordered range of all the sync d l objects where version = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param version the version
	* @param type the type
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByV_T(
		java.lang.String version, java.lang.String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByV_T(version, type, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync d l objects where version = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param version the version
	* @param type the type
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByV_T(
		java.lang.String version, java.lang.String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByV_T(version, type, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first sync d l object in the ordered set where version = &#63; and type = &#63;.
	*
	* @param version the version
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync d l object
	* @throws com.liferay.sync.NoSuchDLObjectException if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject findByV_T_First(
		java.lang.String version, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence().findByV_T_First(version, type, orderByComparator);
	}

	/**
	* Returns the first sync d l object in the ordered set where version = &#63; and type = &#63;.
	*
	* @param version the version
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject fetchByV_T_First(
		java.lang.String version, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByV_T_First(version, type, orderByComparator);
	}

	/**
	* Returns the last sync d l object in the ordered set where version = &#63; and type = &#63;.
	*
	* @param version the version
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync d l object
	* @throws com.liferay.sync.NoSuchDLObjectException if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject findByV_T_Last(
		java.lang.String version, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence().findByV_T_Last(version, type, orderByComparator);
	}

	/**
	* Returns the last sync d l object in the ordered set where version = &#63; and type = &#63;.
	*
	* @param version the version
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject fetchByV_T_Last(
		java.lang.String version, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByV_T_Last(version, type, orderByComparator);
	}

	/**
	* Returns the sync d l objects before and after the current sync d l object in the ordered set where version = &#63; and type = &#63;.
	*
	* @param syncDLObjectId the primary key of the current sync d l object
	* @param version the version
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync d l object
	* @throws com.liferay.sync.NoSuchDLObjectException if a sync d l object with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject[] findByV_T_PrevAndNext(
		long syncDLObjectId, java.lang.String version, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence()
				   .findByV_T_PrevAndNext(syncDLObjectId, version, type,
			orderByComparator);
	}

	/**
	* Removes all the sync d l objects where version = &#63; and type = &#63; from the database.
	*
	* @param version the version
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByV_T(java.lang.String version,
		java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByV_T(version, type);
	}

	/**
	* Returns the number of sync d l objects where version = &#63; and type = &#63;.
	*
	* @param version the version
	* @param type the type
	* @return the number of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static int countByV_T(java.lang.String version, java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByV_T(version, type);
	}

	/**
	* Returns the sync d l object where type = &#63; and typePK = &#63; or throws a {@link com.liferay.sync.NoSuchDLObjectException} if it could not be found.
	*
	* @param type the type
	* @param typePK the type p k
	* @return the matching sync d l object
	* @throws com.liferay.sync.NoSuchDLObjectException if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject findByT_T(
		java.lang.String type, long typePK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence().findByT_T(type, typePK);
	}

	/**
	* Returns the sync d l object where type = &#63; and typePK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param type the type
	* @param typePK the type p k
	* @return the matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject fetchByT_T(
		java.lang.String type, long typePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByT_T(type, typePK);
	}

	/**
	* Returns the sync d l object where type = &#63; and typePK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param type the type
	* @param typePK the type p k
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject fetchByT_T(
		java.lang.String type, long typePK, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByT_T(type, typePK, retrieveFromCache);
	}

	/**
	* Removes the sync d l object where type = &#63; and typePK = &#63; from the database.
	*
	* @param type the type
	* @param typePK the type p k
	* @return the sync d l object that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject removeByT_T(
		java.lang.String type, long typePK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence().removeByT_T(type, typePK);
	}

	/**
	* Returns the number of sync d l objects where type = &#63; and typePK = &#63;.
	*
	* @param type the type
	* @param typePK the type p k
	* @return the number of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static int countByT_T(java.lang.String type, long typePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByT_T(type, typePK);
	}

	/**
	* Returns all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; &#63;.
	*
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param event the event
	* @return the matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByM_R_NotE(
		long modifiedTime, long repositoryId, java.lang.String event)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByM_R_NotE(modifiedTime, repositoryId, event);
	}

	/**
	* Returns a range of all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param event the event
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @return the range of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByM_R_NotE(
		long modifiedTime, long repositoryId, java.lang.String event,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByM_R_NotE(modifiedTime, repositoryId, event, start, end);
	}

	/**
	* Returns an ordered range of all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param event the event
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByM_R_NotE(
		long modifiedTime, long repositoryId, java.lang.String event,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByM_R_NotE(modifiedTime, repositoryId, event, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param event the event
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByM_R_NotE(
		long modifiedTime, long repositoryId, java.lang.String event,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByM_R_NotE(modifiedTime, repositoryId, event, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first sync d l object in the ordered set where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; &#63;.
	*
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param event the event
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync d l object
	* @throws com.liferay.sync.NoSuchDLObjectException if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject findByM_R_NotE_First(
		long modifiedTime, long repositoryId, java.lang.String event,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence()
				   .findByM_R_NotE_First(modifiedTime, repositoryId, event,
			orderByComparator);
	}

	/**
	* Returns the first sync d l object in the ordered set where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; &#63;.
	*
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param event the event
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject fetchByM_R_NotE_First(
		long modifiedTime, long repositoryId, java.lang.String event,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByM_R_NotE_First(modifiedTime, repositoryId, event,
			orderByComparator);
	}

	/**
	* Returns the last sync d l object in the ordered set where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; &#63;.
	*
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param event the event
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync d l object
	* @throws com.liferay.sync.NoSuchDLObjectException if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject findByM_R_NotE_Last(
		long modifiedTime, long repositoryId, java.lang.String event,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence()
				   .findByM_R_NotE_Last(modifiedTime, repositoryId, event,
			orderByComparator);
	}

	/**
	* Returns the last sync d l object in the ordered set where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; &#63;.
	*
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param event the event
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject fetchByM_R_NotE_Last(
		long modifiedTime, long repositoryId, java.lang.String event,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByM_R_NotE_Last(modifiedTime, repositoryId, event,
			orderByComparator);
	}

	/**
	* Returns the sync d l objects before and after the current sync d l object in the ordered set where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; &#63;.
	*
	* @param syncDLObjectId the primary key of the current sync d l object
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param event the event
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync d l object
	* @throws com.liferay.sync.NoSuchDLObjectException if a sync d l object with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject[] findByM_R_NotE_PrevAndNext(
		long syncDLObjectId, long modifiedTime, long repositoryId,
		java.lang.String event,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence()
				   .findByM_R_NotE_PrevAndNext(syncDLObjectId, modifiedTime,
			repositoryId, event, orderByComparator);
	}

	/**
	* Returns all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; all &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param events the events
	* @return the matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByM_R_NotE(
		long modifiedTime, long repositoryId, java.lang.String[] events)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByM_R_NotE(modifiedTime, repositoryId, events);
	}

	/**
	* Returns a range of all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; all &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param events the events
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @return the range of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByM_R_NotE(
		long modifiedTime, long repositoryId, java.lang.String[] events,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByM_R_NotE(modifiedTime, repositoryId, events, start,
			end);
	}

	/**
	* Returns an ordered range of all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; all &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param events the events
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByM_R_NotE(
		long modifiedTime, long repositoryId, java.lang.String[] events,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByM_R_NotE(modifiedTime, repositoryId, events, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; all &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param events the events
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByM_R_NotE(
		long modifiedTime, long repositoryId, java.lang.String[] events,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByM_R_NotE(modifiedTime, repositoryId, events, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; &#63; from the database.
	*
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param event the event
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByM_R_NotE(long modifiedTime, long repositoryId,
		java.lang.String event)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByM_R_NotE(modifiedTime, repositoryId, event);
	}

	/**
	* Returns the number of sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; &#63;.
	*
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param event the event
	* @return the number of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static int countByM_R_NotE(long modifiedTime, long repositoryId,
		java.lang.String event)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByM_R_NotE(modifiedTime, repositoryId, event);
	}

	/**
	* Returns the number of sync d l objects where modifiedTime &gt; &#63; and repositoryId = &#63; and event &ne; all &#63;.
	*
	* @param modifiedTime the modified time
	* @param repositoryId the repository ID
	* @param events the events
	* @return the number of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static int countByM_R_NotE(long modifiedTime, long repositoryId,
		java.lang.String[] events)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByM_R_NotE(modifiedTime, repositoryId, events);
	}

	/**
	* Returns all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63; and type = &#63;.
	*
	* @param repositoryId the repository ID
	* @param parentFolderId the parent folder ID
	* @param type the type
	* @return the matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByR_P_T(
		long repositoryId, long parentFolderId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_P_T(repositoryId, parentFolderId, type);
	}

	/**
	* Returns a range of all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param repositoryId the repository ID
	* @param parentFolderId the parent folder ID
	* @param type the type
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @return the range of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByR_P_T(
		long repositoryId, long parentFolderId, java.lang.String type,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_P_T(repositoryId, parentFolderId, type, start, end);
	}

	/**
	* Returns an ordered range of all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param repositoryId the repository ID
	* @param parentFolderId the parent folder ID
	* @param type the type
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByR_P_T(
		long repositoryId, long parentFolderId, java.lang.String type,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_P_T(repositoryId, parentFolderId, type, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param repositoryId the repository ID
	* @param parentFolderId the parent folder ID
	* @param type the type
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByR_P_T(
		long repositoryId, long parentFolderId, java.lang.String type,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_P_T(repositoryId, parentFolderId, type, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first sync d l object in the ordered set where repositoryId = &#63; and parentFolderId = &#63; and type = &#63;.
	*
	* @param repositoryId the repository ID
	* @param parentFolderId the parent folder ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync d l object
	* @throws com.liferay.sync.NoSuchDLObjectException if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject findByR_P_T_First(
		long repositoryId, long parentFolderId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence()
				   .findByR_P_T_First(repositoryId, parentFolderId, type,
			orderByComparator);
	}

	/**
	* Returns the first sync d l object in the ordered set where repositoryId = &#63; and parentFolderId = &#63; and type = &#63;.
	*
	* @param repositoryId the repository ID
	* @param parentFolderId the parent folder ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject fetchByR_P_T_First(
		long repositoryId, long parentFolderId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_P_T_First(repositoryId, parentFolderId, type,
			orderByComparator);
	}

	/**
	* Returns the last sync d l object in the ordered set where repositoryId = &#63; and parentFolderId = &#63; and type = &#63;.
	*
	* @param repositoryId the repository ID
	* @param parentFolderId the parent folder ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync d l object
	* @throws com.liferay.sync.NoSuchDLObjectException if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject findByR_P_T_Last(
		long repositoryId, long parentFolderId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence()
				   .findByR_P_T_Last(repositoryId, parentFolderId, type,
			orderByComparator);
	}

	/**
	* Returns the last sync d l object in the ordered set where repositoryId = &#63; and parentFolderId = &#63; and type = &#63;.
	*
	* @param repositoryId the repository ID
	* @param parentFolderId the parent folder ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync d l object, or <code>null</code> if a matching sync d l object could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject fetchByR_P_T_Last(
		long repositoryId, long parentFolderId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_P_T_Last(repositoryId, parentFolderId, type,
			orderByComparator);
	}

	/**
	* Returns the sync d l objects before and after the current sync d l object in the ordered set where repositoryId = &#63; and parentFolderId = &#63; and type = &#63;.
	*
	* @param syncDLObjectId the primary key of the current sync d l object
	* @param repositoryId the repository ID
	* @param parentFolderId the parent folder ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync d l object
	* @throws com.liferay.sync.NoSuchDLObjectException if a sync d l object with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject[] findByR_P_T_PrevAndNext(
		long syncDLObjectId, long repositoryId, long parentFolderId,
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence()
				   .findByR_P_T_PrevAndNext(syncDLObjectId, repositoryId,
			parentFolderId, type, orderByComparator);
	}

	/**
	* Returns all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63; and type = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param repositoryId the repository ID
	* @param parentFolderId the parent folder ID
	* @param types the types
	* @return the matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByR_P_T(
		long repositoryId, long parentFolderId, java.lang.String[] types)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_P_T(repositoryId, parentFolderId, types);
	}

	/**
	* Returns a range of all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63; and type = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param repositoryId the repository ID
	* @param parentFolderId the parent folder ID
	* @param types the types
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @return the range of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByR_P_T(
		long repositoryId, long parentFolderId, java.lang.String[] types,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_P_T(repositoryId, parentFolderId, types, start, end);
	}

	/**
	* Returns an ordered range of all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63; and type = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param repositoryId the repository ID
	* @param parentFolderId the parent folder ID
	* @param types the types
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByR_P_T(
		long repositoryId, long parentFolderId, java.lang.String[] types,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_P_T(repositoryId, parentFolderId, types, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63; and type = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param repositoryId the repository ID
	* @param parentFolderId the parent folder ID
	* @param types the types
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByR_P_T(
		long repositoryId, long parentFolderId, java.lang.String[] types,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_P_T(repositoryId, parentFolderId, types, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the sync d l objects where repositoryId = &#63; and parentFolderId = &#63; and type = &#63; from the database.
	*
	* @param repositoryId the repository ID
	* @param parentFolderId the parent folder ID
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByR_P_T(long repositoryId, long parentFolderId,
		java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByR_P_T(repositoryId, parentFolderId, type);
	}

	/**
	* Returns the number of sync d l objects where repositoryId = &#63; and parentFolderId = &#63; and type = &#63;.
	*
	* @param repositoryId the repository ID
	* @param parentFolderId the parent folder ID
	* @param type the type
	* @return the number of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_P_T(long repositoryId, long parentFolderId,
		java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_P_T(repositoryId, parentFolderId, type);
	}

	/**
	* Returns the number of sync d l objects where repositoryId = &#63; and parentFolderId = &#63; and type = any &#63;.
	*
	* @param repositoryId the repository ID
	* @param parentFolderId the parent folder ID
	* @param types the types
	* @return the number of matching sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_P_T(long repositoryId, long parentFolderId,
		java.lang.String[] types)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_P_T(repositoryId, parentFolderId, types);
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
	* @param syncDLObjectId the primary key for the new sync d l object
	* @return the new sync d l object
	*/
	public static com.liferay.sync.model.SyncDLObject create(
		long syncDLObjectId) {
		return getPersistence().create(syncDLObjectId);
	}

	/**
	* Removes the sync d l object with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param syncDLObjectId the primary key of the sync d l object
	* @return the sync d l object that was removed
	* @throws com.liferay.sync.NoSuchDLObjectException if a sync d l object with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject remove(
		long syncDLObjectId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence().remove(syncDLObjectId);
	}

	public static com.liferay.sync.model.SyncDLObject updateImpl(
		com.liferay.sync.model.SyncDLObject syncDLObject)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(syncDLObject);
	}

	/**
	* Returns the sync d l object with the primary key or throws a {@link com.liferay.sync.NoSuchDLObjectException} if it could not be found.
	*
	* @param syncDLObjectId the primary key of the sync d l object
	* @return the sync d l object
	* @throws com.liferay.sync.NoSuchDLObjectException if a sync d l object with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject findByPrimaryKey(
		long syncDLObjectId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDLObjectException {
		return getPersistence().findByPrimaryKey(syncDLObjectId);
	}

	/**
	* Returns the sync d l object with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param syncDLObjectId the primary key of the sync d l object
	* @return the sync d l object, or <code>null</code> if a sync d l object with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sync.model.SyncDLObject fetchByPrimaryKey(
		long syncDLObjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(syncDLObjectId);
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
	* Returns an ordered range of all the sync d l objects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDLObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync d l objects
	* @param end the upper bound of the range of sync d l objects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of sync d l objects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sync.model.SyncDLObject> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
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
	public void setPersistence(SyncDLObjectPersistence persistence) {
	}

	private static SyncDLObjectPersistence _persistence;
}