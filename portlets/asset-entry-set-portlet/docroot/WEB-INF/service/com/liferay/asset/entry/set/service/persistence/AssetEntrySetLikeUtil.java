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

import com.liferay.asset.entry.set.model.AssetEntrySetLike;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the asset entry set like service. This utility wraps {@link AssetEntrySetLikePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySetLikePersistence
 * @see AssetEntrySetLikePersistenceImpl
 * @generated
 */
public class AssetEntrySetLikeUtil {
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
	public static void clearCache(AssetEntrySetLike assetEntrySetLike) {
		getPersistence().clearCache(assetEntrySetLike);
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
	public static List<AssetEntrySetLike> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AssetEntrySetLike> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AssetEntrySetLike> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static AssetEntrySetLike update(AssetEntrySetLike assetEntrySetLike)
		throws SystemException {
		return getPersistence().update(assetEntrySetLike);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static AssetEntrySetLike update(
		AssetEntrySetLike assetEntrySetLike, ServiceContext serviceContext)
		throws SystemException {
		return getPersistence().update(assetEntrySetLike, serviceContext);
	}

	/**
	* Returns all the asset entry set likes where assetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the asset entry set ID
	* @return the matching asset entry set likes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySetLike> findByAssetEntrySetId(
		long assetEntrySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAssetEntrySetId(assetEntrySetId);
	}

	/**
	* Returns a range of all the asset entry set likes where assetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetLikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetEntrySetId the asset entry set ID
	* @param start the lower bound of the range of asset entry set likes
	* @param end the upper bound of the range of asset entry set likes (not inclusive)
	* @return the range of matching asset entry set likes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySetLike> findByAssetEntrySetId(
		long assetEntrySetId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetEntrySetId(assetEntrySetId, start, end);
	}

	/**
	* Returns an ordered range of all the asset entry set likes where assetEntrySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetLikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetEntrySetId the asset entry set ID
	* @param start the lower bound of the range of asset entry set likes
	* @param end the upper bound of the range of asset entry set likes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset entry set likes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySetLike> findByAssetEntrySetId(
		long assetEntrySetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetEntrySetId(assetEntrySetId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first asset entry set like in the ordered set where assetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry set like
	* @throws com.liferay.asset.entry.set.NoSuchLikeException if a matching asset entry set like could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySetLike findByAssetEntrySetId_First(
		long assetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.asset.entry.set.NoSuchLikeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetEntrySetId_First(assetEntrySetId,
			orderByComparator);
	}

	/**
	* Returns the first asset entry set like in the ordered set where assetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry set like, or <code>null</code> if a matching asset entry set like could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySetLike fetchByAssetEntrySetId_First(
		long assetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAssetEntrySetId_First(assetEntrySetId,
			orderByComparator);
	}

	/**
	* Returns the last asset entry set like in the ordered set where assetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry set like
	* @throws com.liferay.asset.entry.set.NoSuchLikeException if a matching asset entry set like could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySetLike findByAssetEntrySetId_Last(
		long assetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.asset.entry.set.NoSuchLikeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetEntrySetId_Last(assetEntrySetId,
			orderByComparator);
	}

	/**
	* Returns the last asset entry set like in the ordered set where assetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry set like, or <code>null</code> if a matching asset entry set like could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySetLike fetchByAssetEntrySetId_Last(
		long assetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAssetEntrySetId_Last(assetEntrySetId,
			orderByComparator);
	}

	/**
	* Returns the asset entry set likes before and after the current asset entry set like in the ordered set where assetEntrySetId = &#63;.
	*
	* @param assetEntrySetLikePK the primary key of the current asset entry set like
	* @param assetEntrySetId the asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset entry set like
	* @throws com.liferay.asset.entry.set.NoSuchLikeException if a asset entry set like with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySetLike[] findByAssetEntrySetId_PrevAndNext(
		com.liferay.asset.entry.set.service.persistence.AssetEntrySetLikePK assetEntrySetLikePK,
		long assetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.asset.entry.set.NoSuchLikeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAssetEntrySetId_PrevAndNext(assetEntrySetLikePK,
			assetEntrySetId, orderByComparator);
	}

	/**
	* Removes all the asset entry set likes where assetEntrySetId = &#63; from the database.
	*
	* @param assetEntrySetId the asset entry set ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAssetEntrySetId(long assetEntrySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAssetEntrySetId(assetEntrySetId);
	}

	/**
	* Returns the number of asset entry set likes where assetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the asset entry set ID
	* @return the number of matching asset entry set likes
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAssetEntrySetId(long assetEntrySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAssetEntrySetId(assetEntrySetId);
	}

	/**
	* Caches the asset entry set like in the entity cache if it is enabled.
	*
	* @param assetEntrySetLike the asset entry set like
	*/
	public static void cacheResult(
		com.liferay.asset.entry.set.model.AssetEntrySetLike assetEntrySetLike) {
		getPersistence().cacheResult(assetEntrySetLike);
	}

	/**
	* Caches the asset entry set likes in the entity cache if it is enabled.
	*
	* @param assetEntrySetLikes the asset entry set likes
	*/
	public static void cacheResult(
		java.util.List<com.liferay.asset.entry.set.model.AssetEntrySetLike> assetEntrySetLikes) {
		getPersistence().cacheResult(assetEntrySetLikes);
	}

	/**
	* Creates a new asset entry set like with the primary key. Does not add the asset entry set like to the database.
	*
	* @param assetEntrySetLikePK the primary key for the new asset entry set like
	* @return the new asset entry set like
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySetLike create(
		com.liferay.asset.entry.set.service.persistence.AssetEntrySetLikePK assetEntrySetLikePK) {
		return getPersistence().create(assetEntrySetLikePK);
	}

	/**
	* Removes the asset entry set like with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntrySetLikePK the primary key of the asset entry set like
	* @return the asset entry set like that was removed
	* @throws com.liferay.asset.entry.set.NoSuchLikeException if a asset entry set like with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySetLike remove(
		com.liferay.asset.entry.set.service.persistence.AssetEntrySetLikePK assetEntrySetLikePK)
		throws com.liferay.asset.entry.set.NoSuchLikeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(assetEntrySetLikePK);
	}

	public static com.liferay.asset.entry.set.model.AssetEntrySetLike updateImpl(
		com.liferay.asset.entry.set.model.AssetEntrySetLike assetEntrySetLike)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(assetEntrySetLike);
	}

	/**
	* Returns the asset entry set like with the primary key or throws a {@link com.liferay.asset.entry.set.NoSuchLikeException} if it could not be found.
	*
	* @param assetEntrySetLikePK the primary key of the asset entry set like
	* @return the asset entry set like
	* @throws com.liferay.asset.entry.set.NoSuchLikeException if a asset entry set like with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySetLike findByPrimaryKey(
		com.liferay.asset.entry.set.service.persistence.AssetEntrySetLikePK assetEntrySetLikePK)
		throws com.liferay.asset.entry.set.NoSuchLikeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(assetEntrySetLikePK);
	}

	/**
	* Returns the asset entry set like with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetEntrySetLikePK the primary key of the asset entry set like
	* @return the asset entry set like, or <code>null</code> if a asset entry set like with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.asset.entry.set.model.AssetEntrySetLike fetchByPrimaryKey(
		com.liferay.asset.entry.set.service.persistence.AssetEntrySetLikePK assetEntrySetLikePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(assetEntrySetLikePK);
	}

	/**
	* Returns all the asset entry set likes.
	*
	* @return the asset entry set likes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySetLike> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the asset entry set likes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetLikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset entry set likes
	* @param end the upper bound of the range of asset entry set likes (not inclusive)
	* @return the range of asset entry set likes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySetLike> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the asset entry set likes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.entry.set.model.impl.AssetEntrySetLikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of asset entry set likes
	* @param end the upper bound of the range of asset entry set likes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of asset entry set likes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySetLike> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the asset entry set likes from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of asset entry set likes.
	*
	* @return the number of asset entry set likes
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AssetEntrySetLikePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AssetEntrySetLikePersistence)PortletBeanLocatorUtil.locate(com.liferay.asset.entry.set.service.ClpSerializer.getServletContextName(),
					AssetEntrySetLikePersistence.class.getName());

			ReferenceRegistry.registerReference(AssetEntrySetLikeUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(AssetEntrySetLikePersistence persistence) {
	}

	private static AssetEntrySetLikePersistence _persistence;
}