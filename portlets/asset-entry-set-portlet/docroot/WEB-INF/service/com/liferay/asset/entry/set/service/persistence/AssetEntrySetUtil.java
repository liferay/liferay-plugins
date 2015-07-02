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

package com.liferay.asset.entry.set.service.persistence;

import com.liferay.asset.entry.set.model.AssetEntrySet;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the asset entry set service. This utility wraps {@link AssetEntrySetPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySetPersistence
 * @see AssetEntrySetPersistenceImpl
 * @generated
 */
public class AssetEntrySetUtil {
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
	public static void clearCache(AssetEntrySet assetEntrySet) {
		getPersistence().clearCache(assetEntrySet);
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
	public static List<AssetEntrySet> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AssetEntrySet> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AssetEntrySet> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static AssetEntrySet update(AssetEntrySet assetEntrySet)
		throws SystemException {
		return getPersistence().update(assetEntrySet);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static AssetEntrySet update(AssetEntrySet assetEntrySet,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(assetEntrySet, serviceContext);
	}

	/**
	* Returns all the asset entry sets where parentAssetEntrySetId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @return the matching asset entry sets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findByParentAssetEntrySetId(
		long parentAssetEntrySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByParentAssetEntrySetId(parentAssetEntrySetId);
	}

	/**
	* Returns a range of all the asset entry sets where parentAssetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @return the range of matching asset entry sets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findByParentAssetEntrySetId(
		long parentAssetEntrySetId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByParentAssetEntrySetId(parentAssetEntrySetId, start,
			end);
	}

	/**
	* Returns an ordered range of all the asset entry sets where parentAssetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset entry sets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findByParentAssetEntrySetId(
		long parentAssetEntrySetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByParentAssetEntrySetId(parentAssetEntrySetId, start,
			end, orderByComparator);
	}

	/**
	* Returns the first asset entry set in the ordered set where parentAssetEntrySetId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry set
	* @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a matching asset entry set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet findByParentAssetEntrySetId_First(
		long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByParentAssetEntrySetId_First(parentAssetEntrySetId,
			orderByComparator);
	}

	/**
	* Returns the first asset entry set in the ordered set where parentAssetEntrySetId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet fetchByParentAssetEntrySetId_First(
		long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByParentAssetEntrySetId_First(parentAssetEntrySetId,
			orderByComparator);
	}

	/**
	* Returns the last asset entry set in the ordered set where parentAssetEntrySetId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry set
	* @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a matching asset entry set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet findByParentAssetEntrySetId_Last(
		long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByParentAssetEntrySetId_Last(parentAssetEntrySetId,
			orderByComparator);
	}

	/**
	* Returns the last asset entry set in the ordered set where parentAssetEntrySetId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet fetchByParentAssetEntrySetId_Last(
		long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByParentAssetEntrySetId_Last(parentAssetEntrySetId,
			orderByComparator);
	}

	/**
	* Returns the asset entry sets before and after the current asset entry set in the ordered set where parentAssetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the primary key of the current asset entry set
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry set
	* @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet[] findByParentAssetEntrySetId_PrevAndNext(
		long assetEntrySetId, long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByParentAssetEntrySetId_PrevAndNext(assetEntrySetId,
			parentAssetEntrySetId, orderByComparator);
	}

	/**
	* Returns all the asset entry sets that the user has permission to view where parentAssetEntrySetId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @return the matching asset entry sets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> filterFindByParentAssetEntrySetId(
		long parentAssetEntrySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByParentAssetEntrySetId(parentAssetEntrySetId);
	}

	/**
	* Returns a range of all the asset entry sets that the user has permission to view where parentAssetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @return the range of matching asset entry sets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> filterFindByParentAssetEntrySetId(
		long parentAssetEntrySetId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByParentAssetEntrySetId(parentAssetEntrySetId,
			start, end);
	}

	/**
	* Returns an ordered range of all the asset entry sets that the user has permissions to view where parentAssetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset entry sets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> filterFindByParentAssetEntrySetId(
		long parentAssetEntrySetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByParentAssetEntrySetId(parentAssetEntrySetId,
			start, end, orderByComparator);
	}

	/**
	* Returns the asset entry sets before and after the current asset entry set in the ordered set of asset entry sets that the user has permission to view where parentAssetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the primary key of the current asset entry set
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry set
	* @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet[] filterFindByParentAssetEntrySetId_PrevAndNext(
		long assetEntrySetId, long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByParentAssetEntrySetId_PrevAndNext(assetEntrySetId,
			parentAssetEntrySetId, orderByComparator);
	}

	/**
	* Removes all the asset entry sets where parentAssetEntrySetId = &#63; from the database.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByParentAssetEntrySetId(long parentAssetEntrySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByParentAssetEntrySetId(parentAssetEntrySetId);
	}

	/**
	* Returns the number of asset entry sets where parentAssetEntrySetId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @return the number of matching asset entry sets
	* @throws SystemException if a system exception occurred
	*/
	public static int countByParentAssetEntrySetId(long parentAssetEntrySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByParentAssetEntrySetId(parentAssetEntrySetId);
	}

	/**
	* Returns the number of asset entry sets that the user has permission to view where parentAssetEntrySetId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @return the number of matching asset entry sets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByParentAssetEntrySetId(
		long parentAssetEntrySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByParentAssetEntrySetId(parentAssetEntrySetId);
	}

	/**
	* Returns all the asset entry sets where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @return the matching asset entry sets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findByGtCT_PAESI(
		long createTime, long parentAssetEntrySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGtCT_PAESI(createTime, parentAssetEntrySetId);
	}

	/**
	* Returns a range of all the asset entry sets where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @return the range of matching asset entry sets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findByGtCT_PAESI(
		long createTime, long parentAssetEntrySetId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGtCT_PAESI(createTime, parentAssetEntrySetId, start,
			end);
	}

	/**
	* Returns an ordered range of all the asset entry sets where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset entry sets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findByGtCT_PAESI(
		long createTime, long parentAssetEntrySetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGtCT_PAESI(createTime, parentAssetEntrySetId, start,
			end, orderByComparator);
	}

	/**
	* Returns the first asset entry set in the ordered set where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry set
	* @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a matching asset entry set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet findByGtCT_PAESI_First(
		long createTime, long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGtCT_PAESI_First(createTime, parentAssetEntrySetId,
			orderByComparator);
	}

	/**
	* Returns the first asset entry set in the ordered set where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet fetchByGtCT_PAESI_First(
		long createTime, long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGtCT_PAESI_First(createTime, parentAssetEntrySetId,
			orderByComparator);
	}

	/**
	* Returns the last asset entry set in the ordered set where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry set
	* @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a matching asset entry set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet findByGtCT_PAESI_Last(
		long createTime, long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGtCT_PAESI_Last(createTime, parentAssetEntrySetId,
			orderByComparator);
	}

	/**
	* Returns the last asset entry set in the ordered set where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet fetchByGtCT_PAESI_Last(
		long createTime, long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByGtCT_PAESI_Last(createTime, parentAssetEntrySetId,
			orderByComparator);
	}

	/**
	* Returns the asset entry sets before and after the current asset entry set in the ordered set where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the primary key of the current asset entry set
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry set
	* @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet[] findByGtCT_PAESI_PrevAndNext(
		long assetEntrySetId, long createTime, long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGtCT_PAESI_PrevAndNext(assetEntrySetId, createTime,
			parentAssetEntrySetId, orderByComparator);
	}

	/**
	* Returns all the asset entry sets that the user has permission to view where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @return the matching asset entry sets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> filterFindByGtCT_PAESI(
		long createTime, long parentAssetEntrySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGtCT_PAESI(createTime, parentAssetEntrySetId);
	}

	/**
	* Returns a range of all the asset entry sets that the user has permission to view where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @return the range of matching asset entry sets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> filterFindByGtCT_PAESI(
		long createTime, long parentAssetEntrySetId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGtCT_PAESI(createTime, parentAssetEntrySetId,
			start, end);
	}

	/**
	* Returns an ordered range of all the asset entry sets that the user has permissions to view where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset entry sets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> filterFindByGtCT_PAESI(
		long createTime, long parentAssetEntrySetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGtCT_PAESI(createTime, parentAssetEntrySetId,
			start, end, orderByComparator);
	}

	/**
	* Returns the asset entry sets before and after the current asset entry set in the ordered set of asset entry sets that the user has permission to view where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the primary key of the current asset entry set
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry set
	* @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet[] filterFindByGtCT_PAESI_PrevAndNext(
		long assetEntrySetId, long createTime, long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByGtCT_PAESI_PrevAndNext(assetEntrySetId,
			createTime, parentAssetEntrySetId, orderByComparator);
	}

	/**
	* Removes all the asset entry sets where createTime &gt; &#63; and parentAssetEntrySetId = &#63; from the database.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGtCT_PAESI(long createTime,
		long parentAssetEntrySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGtCT_PAESI(createTime, parentAssetEntrySetId);
	}

	/**
	* Returns the number of asset entry sets where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @return the number of matching asset entry sets
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGtCT_PAESI(long createTime,
		long parentAssetEntrySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByGtCT_PAESI(createTime, parentAssetEntrySetId);
	}

	/**
	* Returns the number of asset entry sets that the user has permission to view where createTime &gt; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @return the number of matching asset entry sets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByGtCT_PAESI(long createTime,
		long parentAssetEntrySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByGtCT_PAESI(createTime, parentAssetEntrySetId);
	}

	/**
	* Returns all the asset entry sets where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @return the matching asset entry sets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findByLtCT_PAESI(
		long createTime, long parentAssetEntrySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByLtCT_PAESI(createTime, parentAssetEntrySetId);
	}

	/**
	* Returns a range of all the asset entry sets where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @return the range of matching asset entry sets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findByLtCT_PAESI(
		long createTime, long parentAssetEntrySetId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByLtCT_PAESI(createTime, parentAssetEntrySetId, start,
			end);
	}

	/**
	* Returns an ordered range of all the asset entry sets where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset entry sets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findByLtCT_PAESI(
		long createTime, long parentAssetEntrySetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByLtCT_PAESI(createTime, parentAssetEntrySetId, start,
			end, orderByComparator);
	}

	/**
	* Returns the first asset entry set in the ordered set where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry set
	* @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a matching asset entry set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet findByLtCT_PAESI_First(
		long createTime, long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByLtCT_PAESI_First(createTime, parentAssetEntrySetId,
			orderByComparator);
	}

	/**
	* Returns the first asset entry set in the ordered set where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet fetchByLtCT_PAESI_First(
		long createTime, long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByLtCT_PAESI_First(createTime, parentAssetEntrySetId,
			orderByComparator);
	}

	/**
	* Returns the last asset entry set in the ordered set where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry set
	* @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a matching asset entry set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet findByLtCT_PAESI_Last(
		long createTime, long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByLtCT_PAESI_Last(createTime, parentAssetEntrySetId,
			orderByComparator);
	}

	/**
	* Returns the last asset entry set in the ordered set where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet fetchByLtCT_PAESI_Last(
		long createTime, long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByLtCT_PAESI_Last(createTime, parentAssetEntrySetId,
			orderByComparator);
	}

	/**
	* Returns the asset entry sets before and after the current asset entry set in the ordered set where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the primary key of the current asset entry set
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry set
	* @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet[] findByLtCT_PAESI_PrevAndNext(
		long assetEntrySetId, long createTime, long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByLtCT_PAESI_PrevAndNext(assetEntrySetId, createTime,
			parentAssetEntrySetId, orderByComparator);
	}

	/**
	* Returns all the asset entry sets that the user has permission to view where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @return the matching asset entry sets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> filterFindByLtCT_PAESI(
		long createTime, long parentAssetEntrySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByLtCT_PAESI(createTime, parentAssetEntrySetId);
	}

	/**
	* Returns a range of all the asset entry sets that the user has permission to view where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @return the range of matching asset entry sets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> filterFindByLtCT_PAESI(
		long createTime, long parentAssetEntrySetId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByLtCT_PAESI(createTime, parentAssetEntrySetId,
			start, end);
	}

	/**
	* Returns an ordered range of all the asset entry sets that the user has permissions to view where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset entry sets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> filterFindByLtCT_PAESI(
		long createTime, long parentAssetEntrySetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByLtCT_PAESI(createTime, parentAssetEntrySetId,
			start, end, orderByComparator);
	}

	/**
	* Returns the asset entry sets before and after the current asset entry set in the ordered set of asset entry sets that the user has permission to view where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the primary key of the current asset entry set
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry set
	* @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet[] filterFindByLtCT_PAESI_PrevAndNext(
		long assetEntrySetId, long createTime, long parentAssetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByLtCT_PAESI_PrevAndNext(assetEntrySetId,
			createTime, parentAssetEntrySetId, orderByComparator);
	}

	/**
	* Removes all the asset entry sets where createTime &le; &#63; and parentAssetEntrySetId = &#63; from the database.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByLtCT_PAESI(long createTime,
		long parentAssetEntrySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByLtCT_PAESI(createTime, parentAssetEntrySetId);
	}

	/**
	* Returns the number of asset entry sets where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @return the number of matching asset entry sets
	* @throws SystemException if a system exception occurred
	*/
	public static int countByLtCT_PAESI(long createTime,
		long parentAssetEntrySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByLtCT_PAESI(createTime, parentAssetEntrySetId);
	}

	/**
	* Returns the number of asset entry sets that the user has permission to view where createTime &le; &#63; and parentAssetEntrySetId = &#63;.
	*
	* @param createTime the create time
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @return the number of matching asset entry sets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByLtCT_PAESI(long createTime,
		long parentAssetEntrySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByLtCT_PAESI(createTime, parentAssetEntrySetId);
	}

	/**
	* Returns all the asset entry sets where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @return the matching asset entry sets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findByPAESI_CCNI(
		long parentAssetEntrySetId, long creatorClassNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPAESI_CCNI(parentAssetEntrySetId, creatorClassNameId);
	}

	/**
	* Returns a range of all the asset entry sets where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @return the range of matching asset entry sets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findByPAESI_CCNI(
		long parentAssetEntrySetId, long creatorClassNameId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPAESI_CCNI(parentAssetEntrySetId, creatorClassNameId,
			start, end);
	}

	/**
	* Returns an ordered range of all the asset entry sets where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset entry sets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findByPAESI_CCNI(
		long parentAssetEntrySetId, long creatorClassNameId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPAESI_CCNI(parentAssetEntrySetId, creatorClassNameId,
			start, end, orderByComparator);
	}

	/**
	* Returns the first asset entry set in the ordered set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry set
	* @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a matching asset entry set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet findByPAESI_CCNI_First(
		long parentAssetEntrySetId, long creatorClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPAESI_CCNI_First(parentAssetEntrySetId,
			creatorClassNameId, orderByComparator);
	}

	/**
	* Returns the first asset entry set in the ordered set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet fetchByPAESI_CCNI_First(
		long parentAssetEntrySetId, long creatorClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPAESI_CCNI_First(parentAssetEntrySetId,
			creatorClassNameId, orderByComparator);
	}

	/**
	* Returns the last asset entry set in the ordered set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry set
	* @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a matching asset entry set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet findByPAESI_CCNI_Last(
		long parentAssetEntrySetId, long creatorClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPAESI_CCNI_Last(parentAssetEntrySetId,
			creatorClassNameId, orderByComparator);
	}

	/**
	* Returns the last asset entry set in the ordered set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet fetchByPAESI_CCNI_Last(
		long parentAssetEntrySetId, long creatorClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPAESI_CCNI_Last(parentAssetEntrySetId,
			creatorClassNameId, orderByComparator);
	}

	/**
	* Returns the asset entry sets before and after the current asset entry set in the ordered set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* @param assetEntrySetId the primary key of the current asset entry set
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry set
	* @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet[] findByPAESI_CCNI_PrevAndNext(
		long assetEntrySetId, long parentAssetEntrySetId,
		long creatorClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPAESI_CCNI_PrevAndNext(assetEntrySetId,
			parentAssetEntrySetId, creatorClassNameId, orderByComparator);
	}

	/**
	* Returns all the asset entry sets that the user has permission to view where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @return the matching asset entry sets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> filterFindByPAESI_CCNI(
		long parentAssetEntrySetId, long creatorClassNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByPAESI_CCNI(parentAssetEntrySetId,
			creatorClassNameId);
	}

	/**
	* Returns a range of all the asset entry sets that the user has permission to view where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @return the range of matching asset entry sets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> filterFindByPAESI_CCNI(
		long parentAssetEntrySetId, long creatorClassNameId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByPAESI_CCNI(parentAssetEntrySetId,
			creatorClassNameId, start, end);
	}

	/**
	* Returns an ordered range of all the asset entry sets that the user has permissions to view where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset entry sets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> filterFindByPAESI_CCNI(
		long parentAssetEntrySetId, long creatorClassNameId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByPAESI_CCNI(parentAssetEntrySetId,
			creatorClassNameId, start, end, orderByComparator);
	}

	/**
	* Returns the asset entry sets before and after the current asset entry set in the ordered set of asset entry sets that the user has permission to view where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* @param assetEntrySetId the primary key of the current asset entry set
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry set
	* @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet[] filterFindByPAESI_CCNI_PrevAndNext(
		long assetEntrySetId, long parentAssetEntrySetId,
		long creatorClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByPAESI_CCNI_PrevAndNext(assetEntrySetId,
			parentAssetEntrySetId, creatorClassNameId, orderByComparator);
	}

	/**
	* Removes all the asset entry sets where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63; from the database.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByPAESI_CCNI(long parentAssetEntrySetId,
		long creatorClassNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByPAESI_CCNI(parentAssetEntrySetId, creatorClassNameId);
	}

	/**
	* Returns the number of asset entry sets where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @return the number of matching asset entry sets
	* @throws SystemException if a system exception occurred
	*/
	public static int countByPAESI_CCNI(long parentAssetEntrySetId,
		long creatorClassNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByPAESI_CCNI(parentAssetEntrySetId, creatorClassNameId);
	}

	/**
	* Returns the number of asset entry sets that the user has permission to view where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @return the number of matching asset entry sets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByPAESI_CCNI(long parentAssetEntrySetId,
		long creatorClassNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByPAESI_CCNI(parentAssetEntrySetId,
			creatorClassNameId);
	}

	/**
	* Returns the asset entry set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63; and creatorClassPK = &#63; or throws a {@link com.liferay.asset.entry.set.NoSuchAssetEntrySetException} if it could not be found.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @return the matching asset entry set
	* @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a matching asset entry set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet findByPAESI_CCNI_CCPK(
		long parentAssetEntrySetId, long creatorClassNameId, long creatorClassPK)
		throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPAESI_CCNI_CCPK(parentAssetEntrySetId,
			creatorClassNameId, creatorClassPK);
	}

	/**
	* Returns the asset entry set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63; and creatorClassPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @return the matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet fetchByPAESI_CCNI_CCPK(
		long parentAssetEntrySetId, long creatorClassNameId, long creatorClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPAESI_CCNI_CCPK(parentAssetEntrySetId,
			creatorClassNameId, creatorClassPK);
	}

	/**
	* Returns the asset entry set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63; and creatorClassPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching asset entry set, or <code>null</code> if a matching asset entry set could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet fetchByPAESI_CCNI_CCPK(
		long parentAssetEntrySetId, long creatorClassNameId,
		long creatorClassPK, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPAESI_CCNI_CCPK(parentAssetEntrySetId,
			creatorClassNameId, creatorClassPK, retrieveFromCache);
	}

	/**
	* Removes the asset entry set where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63; and creatorClassPK = &#63; from the database.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @return the asset entry set that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet removeByPAESI_CCNI_CCPK(
		long parentAssetEntrySetId, long creatorClassNameId, long creatorClassPK)
		throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .removeByPAESI_CCNI_CCPK(parentAssetEntrySetId,
			creatorClassNameId, creatorClassPK);
	}

	/**
	* Returns the number of asset entry sets where parentAssetEntrySetId = &#63; and creatorClassNameId = &#63; and creatorClassPK = &#63;.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID
	* @param creatorClassNameId the creator class name ID
	* @param creatorClassPK the creator class p k
	* @return the number of matching asset entry sets
	* @throws SystemException if a system exception occurred
	*/
	public static int countByPAESI_CCNI_CCPK(long parentAssetEntrySetId,
		long creatorClassNameId, long creatorClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByPAESI_CCNI_CCPK(parentAssetEntrySetId,
			creatorClassNameId, creatorClassPK);
	}

	/**
	* Caches the asset entry set in the entity cache if it is enabled.
	*
	* @param assetEntrySet the asset entry set
	*/
	public static void cacheResult(
		com.liferay.asset.entry.set.model.AssetEntrySet assetEntrySet) {
		getPersistence().cacheResult(assetEntrySet);
	}

	/**
	* Caches the asset entry sets in the entity cache if it is enabled.
	*
	* @param assetEntrySets the asset entry sets
	*/
	public static void cacheResult(
		java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> assetEntrySets) {
		getPersistence().cacheResult(assetEntrySets);
	}

	/**
	* Creates a new asset entry set with the primary key. Does not add the asset entry set to the database.
	*
	* @param assetEntrySetId the primary key for the new asset entry set
	* @return the new asset entry set
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet create(
		long assetEntrySetId) {
		return getPersistence().create(assetEntrySetId);
	}

	/**
	* Removes the asset entry set with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntrySetId the primary key of the asset entry set
	* @return the asset entry set that was removed
	* @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet remove(
		long assetEntrySetId)
		throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(assetEntrySetId);
	}

	public static com.liferay.asset.entry.set.model.AssetEntrySet updateImpl(
		com.liferay.asset.entry.set.model.AssetEntrySet assetEntrySet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(assetEntrySet);
	}

	/**
	* Returns the asset entry set with the primary key or throws a {@link com.liferay.asset.entry.set.NoSuchAssetEntrySetException} if it could not be found.
	*
	* @param assetEntrySetId the primary key of the asset entry set
	* @return the asset entry set
	* @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet findByPrimaryKey(
		long assetEntrySetId)
		throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(assetEntrySetId);
	}

	/**
	* Returns the asset entry set with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetEntrySetId the primary key of the asset entry set
	* @return the asset entry set, or <code>null</code> if a asset entry set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySet fetchByPrimaryKey(
		long assetEntrySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(assetEntrySetId);
	}

	/**
	* Returns all the asset entry sets.
	*
	* @return the asset entry sets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the asset entry sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @return the range of asset entry sets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the asset entry sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset entry sets
	* @param end the upper bound of the range of asset entry sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of asset entry sets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the asset entry sets from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of asset entry sets.
	*
	* @return the number of asset entry sets
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AssetEntrySetPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AssetEntrySetPersistence)PortletBeanLocatorUtil.locate(com.liferay.asset.entry.set.service.ClpSerializer.getServletContextName(),
					AssetEntrySetPersistence.class.getName());

			ReferenceRegistry.registerReference(AssetEntrySetUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(AssetEntrySetPersistence persistence) {
	}

	private static AssetEntrySetPersistence _persistence;
}