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

package com.liferay.samplealloymvc.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import com.liferay.samplealloymvc.model.Asset;

import java.util.List;

/**
 * The persistence utility for the asset service. This utility wraps {@link com.liferay.samplealloymvc.service.persistence.impl.AssetPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetPersistence
 * @see com.liferay.samplealloymvc.service.persistence.impl.AssetPersistenceImpl
 * @generated
 */
@ProviderType
public class AssetUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Asset asset) {
		getPersistence().clearCache(asset);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Asset> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Asset> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Asset> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Asset> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Asset update(Asset asset) {
		return getPersistence().update(asset);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Asset update(Asset asset, ServiceContext serviceContext) {
		return getPersistence().update(asset, serviceContext);
	}

	/**
	* Caches the asset in the entity cache if it is enabled.
	*
	* @param asset the asset
	*/
	public static void cacheResult(Asset asset) {
		getPersistence().cacheResult(asset);
	}

	/**
	* Caches the assets in the entity cache if it is enabled.
	*
	* @param assets the assets
	*/
	public static void cacheResult(List<Asset> assets) {
		getPersistence().cacheResult(assets);
	}

	/**
	* Creates a new asset with the primary key. Does not add the asset to the database.
	*
	* @param assetId the primary key for the new asset
	* @return the new asset
	*/
	public static Asset create(long assetId) {
		return getPersistence().create(assetId);
	}

	/**
	* Removes the asset with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetId the primary key of the asset
	* @return the asset that was removed
	* @throws NoSuchAssetException if a asset with the primary key could not be found
	*/
	public static Asset remove(long assetId)
		throws com.liferay.samplealloymvc.exception.NoSuchAssetException {
		return getPersistence().remove(assetId);
	}

	public static Asset updateImpl(Asset asset) {
		return getPersistence().updateImpl(asset);
	}

	/**
	* Returns the asset with the primary key or throws a {@link NoSuchAssetException} if it could not be found.
	*
	* @param assetId the primary key of the asset
	* @return the asset
	* @throws NoSuchAssetException if a asset with the primary key could not be found
	*/
	public static Asset findByPrimaryKey(long assetId)
		throws com.liferay.samplealloymvc.exception.NoSuchAssetException {
		return getPersistence().findByPrimaryKey(assetId);
	}

	/**
	* Returns the asset with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetId the primary key of the asset
	* @return the asset, or <code>null</code> if a asset with the primary key could not be found
	*/
	public static Asset fetchByPrimaryKey(long assetId) {
		return getPersistence().fetchByPrimaryKey(assetId);
	}

	public static java.util.Map<java.io.Serializable, Asset> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the assets.
	*
	* @return the assets
	*/
	public static List<Asset> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<Asset> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<Asset> findAll(int start, int end,
		OrderByComparator<Asset> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<Asset> findAll(int start, int end,
		OrderByComparator<Asset> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the assets from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of assets.
	*
	* @return the number of assets
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static AssetPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AssetPersistence)PortletBeanLocatorUtil.locate(com.liferay.samplealloymvc.service.ClpSerializer.getServletContextName(),
					AssetPersistence.class.getName());

			ReferenceRegistry.registerReference(AssetUtil.class, "_persistence");
		}

		return _persistence;
	}

	private static AssetPersistence _persistence;
}