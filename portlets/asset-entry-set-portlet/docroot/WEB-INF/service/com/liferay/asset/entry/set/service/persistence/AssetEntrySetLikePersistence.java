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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the asset entry set like service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySetLikePersistenceImpl
 * @see AssetEntrySetLikeUtil
 * @generated
 */
public interface AssetEntrySetLikePersistence extends BasePersistence<AssetEntrySetLike> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetEntrySetLikeUtil} to access the asset entry set like persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the asset entry set likes where assetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the asset entry set ID
	* @return the matching asset entry set likes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.asset.entry.set.model.AssetEntrySetLike> findByAssetEntrySetId(
		long assetEntrySetId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.asset.entry.set.model.AssetEntrySetLike> findByAssetEntrySetId(
		long assetEntrySetId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.asset.entry.set.model.AssetEntrySetLike> findByAssetEntrySetId(
		long assetEntrySetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset entry set like in the ordered set where assetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry set like
	* @throws com.liferay.asset.entry.set.NoSuchLikeException if a matching asset entry set like could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.asset.entry.set.model.AssetEntrySetLike findByAssetEntrySetId_First(
		long assetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.asset.entry.set.NoSuchLikeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset entry set like in the ordered set where assetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset entry set like, or <code>null</code> if a matching asset entry set like could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.asset.entry.set.model.AssetEntrySetLike fetchByAssetEntrySetId_First(
		long assetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset entry set like in the ordered set where assetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry set like
	* @throws com.liferay.asset.entry.set.NoSuchLikeException if a matching asset entry set like could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.asset.entry.set.model.AssetEntrySetLike findByAssetEntrySetId_Last(
		long assetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.asset.entry.set.NoSuchLikeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset entry set like in the ordered set where assetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the asset entry set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset entry set like, or <code>null</code> if a matching asset entry set like could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.asset.entry.set.model.AssetEntrySetLike fetchByAssetEntrySetId_Last(
		long assetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.asset.entry.set.model.AssetEntrySetLike[] findByAssetEntrySetId_PrevAndNext(
		com.liferay.asset.entry.set.service.persistence.AssetEntrySetLikePK assetEntrySetLikePK,
		long assetEntrySetId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.asset.entry.set.NoSuchLikeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset entry set likes where assetEntrySetId = &#63; from the database.
	*
	* @param assetEntrySetId the asset entry set ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAssetEntrySetId(long assetEntrySetId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset entry set likes where assetEntrySetId = &#63;.
	*
	* @param assetEntrySetId the asset entry set ID
	* @return the number of matching asset entry set likes
	* @throws SystemException if a system exception occurred
	*/
	public int countByAssetEntrySetId(long assetEntrySetId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the asset entry set like in the entity cache if it is enabled.
	*
	* @param assetEntrySetLike the asset entry set like
	*/
	public void cacheResult(
		com.liferay.asset.entry.set.model.AssetEntrySetLike assetEntrySetLike);

	/**
	* Caches the asset entry set likes in the entity cache if it is enabled.
	*
	* @param assetEntrySetLikes the asset entry set likes
	*/
	public void cacheResult(
		java.util.List<com.liferay.asset.entry.set.model.AssetEntrySetLike> assetEntrySetLikes);

	/**
	* Creates a new asset entry set like with the primary key. Does not add the asset entry set like to the database.
	*
	* @param assetEntrySetLikePK the primary key for the new asset entry set like
	* @return the new asset entry set like
	*/
	public com.liferay.asset.entry.set.model.AssetEntrySetLike create(
		com.liferay.asset.entry.set.service.persistence.AssetEntrySetLikePK assetEntrySetLikePK);

	/**
	* Removes the asset entry set like with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntrySetLikePK the primary key of the asset entry set like
	* @return the asset entry set like that was removed
	* @throws com.liferay.asset.entry.set.NoSuchLikeException if a asset entry set like with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.asset.entry.set.model.AssetEntrySetLike remove(
		com.liferay.asset.entry.set.service.persistence.AssetEntrySetLikePK assetEntrySetLikePK)
		throws com.liferay.asset.entry.set.NoSuchLikeException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.asset.entry.set.model.AssetEntrySetLike updateImpl(
		com.liferay.asset.entry.set.model.AssetEntrySetLike assetEntrySetLike)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset entry set like with the primary key or throws a {@link com.liferay.asset.entry.set.NoSuchLikeException} if it could not be found.
	*
	* @param assetEntrySetLikePK the primary key of the asset entry set like
	* @return the asset entry set like
	* @throws com.liferay.asset.entry.set.NoSuchLikeException if a asset entry set like with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.asset.entry.set.model.AssetEntrySetLike findByPrimaryKey(
		com.liferay.asset.entry.set.service.persistence.AssetEntrySetLikePK assetEntrySetLikePK)
		throws com.liferay.asset.entry.set.NoSuchLikeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset entry set like with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetEntrySetLikePK the primary key of the asset entry set like
	* @return the asset entry set like, or <code>null</code> if a asset entry set like with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.asset.entry.set.model.AssetEntrySetLike fetchByPrimaryKey(
		com.liferay.asset.entry.set.service.persistence.AssetEntrySetLikePK assetEntrySetLikePK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset entry set likes.
	*
	* @return the asset entry set likes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.asset.entry.set.model.AssetEntrySetLike> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.asset.entry.set.model.AssetEntrySetLike> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.asset.entry.set.model.AssetEntrySetLike> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset entry set likes from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset entry set likes.
	*
	* @return the number of asset entry set likes
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}