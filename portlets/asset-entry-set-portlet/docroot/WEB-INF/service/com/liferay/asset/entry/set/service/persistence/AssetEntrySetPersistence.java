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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the asset entry set service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySetPersistenceImpl
 * @see AssetEntrySetUtil
 * @generated
 */
public interface AssetEntrySetPersistence extends BasePersistence<AssetEntrySet> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetEntrySetUtil} to access the asset entry set persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the asset entry set in the entity cache if it is enabled.
	*
	* @param assetEntrySet the asset entry set
	*/
	public void cacheResult(
		com.liferay.asset.entry.set.model.AssetEntrySet assetEntrySet);

	/**
	* Caches the asset entry sets in the entity cache if it is enabled.
	*
	* @param assetEntrySets the asset entry sets
	*/
	public void cacheResult(
		java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> assetEntrySets);

	/**
	* Creates a new asset entry set with the primary key. Does not add the asset entry set to the database.
	*
	* @param assetEntrySetId the primary key for the new asset entry set
	* @return the new asset entry set
	*/
	public com.liferay.asset.entry.set.model.AssetEntrySet create(
		long assetEntrySetId);

	/**
	* Removes the asset entry set with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetEntrySetId the primary key of the asset entry set
	* @return the asset entry set that was removed
	* @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.asset.entry.set.model.AssetEntrySet remove(
		long assetEntrySetId)
		throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.asset.entry.set.model.AssetEntrySet updateImpl(
		com.liferay.asset.entry.set.model.AssetEntrySet assetEntrySet)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset entry set with the primary key or throws a {@link com.liferay.asset.entry.set.NoSuchAssetEntrySetException} if it could not be found.
	*
	* @param assetEntrySetId the primary key of the asset entry set
	* @return the asset entry set
	* @throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException if a asset entry set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.asset.entry.set.model.AssetEntrySet findByPrimaryKey(
		long assetEntrySetId)
		throws com.liferay.asset.entry.set.NoSuchAssetEntrySetException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset entry set with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetEntrySetId the primary key of the asset entry set
	* @return the asset entry set, or <code>null</code> if a asset entry set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.asset.entry.set.model.AssetEntrySet fetchByPrimaryKey(
		long assetEntrySetId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset entry sets.
	*
	* @return the asset entry sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset entry sets from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset entry sets.
	*
	* @return the number of asset entry sets
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}