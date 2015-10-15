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

package com.liferay.ams.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.ams.model.Asset;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the asset service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.ams.service.persistence.impl.AssetPersistenceImpl
 * @see AssetUtil
 * @generated
 */
@ProviderType
public interface AssetPersistence extends BasePersistence<Asset> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetUtil} to access the asset persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the asset in the entity cache if it is enabled.
	*
	* @param asset the asset
	*/
	public void cacheResult(Asset asset);

	/**
	* Caches the assets in the entity cache if it is enabled.
	*
	* @param assets the assets
	*/
	public void cacheResult(java.util.List<Asset> assets);

	/**
	* Creates a new asset with the primary key. Does not add the asset to the database.
	*
	* @param assetId the primary key for the new asset
	* @return the new asset
	*/
	public Asset create(long assetId);

	/**
	* Removes the asset with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetId the primary key of the asset
	* @return the asset that was removed
	* @throws NoSuchAssetException if a asset with the primary key could not be found
	*/
	public Asset remove(long assetId)
		throws com.liferay.ams.NoSuchAssetException;

	public Asset updateImpl(Asset asset);

	/**
	* Returns the asset with the primary key or throws a {@link NoSuchAssetException} if it could not be found.
	*
	* @param assetId the primary key of the asset
	* @return the asset
	* @throws NoSuchAssetException if a asset with the primary key could not be found
	*/
	public Asset findByPrimaryKey(long assetId)
		throws com.liferay.ams.NoSuchAssetException;

	/**
	* Returns the asset with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetId the primary key of the asset
	* @return the asset, or <code>null</code> if a asset with the primary key could not be found
	*/
	public Asset fetchByPrimaryKey(long assetId);

	@Override
	public java.util.Map<java.io.Serializable, Asset> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the assets.
	*
	* @return the assets
	*/
	public java.util.List<Asset> findAll();

	/**
	* Returns a range of all the assets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of assets
	* @param end the upper bound of the range of assets (not inclusive)
	* @return the range of assets
	*/
	public java.util.List<Asset> findAll(int start, int end);

	/**
	* Returns an ordered range of all the assets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of assets
	* @param end the upper bound of the range of assets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of assets
	*/
	public java.util.List<Asset> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Asset> orderByComparator);

	/**
	* Returns an ordered range of all the assets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of assets
	* @param end the upper bound of the range of assets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of assets
	*/
	public java.util.List<Asset> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Asset> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the assets from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of assets.
	*
	* @return the number of assets
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}