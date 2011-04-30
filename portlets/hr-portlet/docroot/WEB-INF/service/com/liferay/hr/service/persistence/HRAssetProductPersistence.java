/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.hr.service.persistence;

import com.liferay.hr.model.HRAssetProduct;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the h r asset product service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRAssetProductPersistenceImpl
 * @see HRAssetProductUtil
 * @generated
 */
public interface HRAssetProductPersistence extends BasePersistence<HRAssetProduct> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HRAssetProductUtil} to access the h r asset product persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the h r asset product in the entity cache if it is enabled.
	*
	* @param hrAssetProduct the h r asset product to cache
	*/
	public void cacheResult(com.liferay.hr.model.HRAssetProduct hrAssetProduct);

	/**
	* Caches the h r asset products in the entity cache if it is enabled.
	*
	* @param hrAssetProducts the h r asset products to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.hr.model.HRAssetProduct> hrAssetProducts);

	/**
	* Creates a new h r asset product with the primary key. Does not add the h r asset product to the database.
	*
	* @param hrAssetProductId the primary key for the new h r asset product
	* @return the new h r asset product
	*/
	public com.liferay.hr.model.HRAssetProduct create(long hrAssetProductId);

	/**
	* Removes the h r asset product with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrAssetProductId the primary key of the h r asset product to remove
	* @return the h r asset product that was removed
	* @throws com.liferay.hr.NoSuchAssetProductException if a h r asset product with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRAssetProduct remove(long hrAssetProductId)
		throws com.liferay.hr.NoSuchAssetProductException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.hr.model.HRAssetProduct updateImpl(
		com.liferay.hr.model.HRAssetProduct hrAssetProduct, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the h r asset product with the primary key or throws a {@link com.liferay.hr.NoSuchAssetProductException} if it could not be found.
	*
	* @param hrAssetProductId the primary key of the h r asset product to find
	* @return the h r asset product
	* @throws com.liferay.hr.NoSuchAssetProductException if a h r asset product with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRAssetProduct findByPrimaryKey(
		long hrAssetProductId)
		throws com.liferay.hr.NoSuchAssetProductException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the h r asset product with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrAssetProductId the primary key of the h r asset product to find
	* @return the h r asset product, or <code>null</code> if a h r asset product with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRAssetProduct fetchByPrimaryKey(
		long hrAssetProductId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the h r asset products.
	*
	* @return the h r asset products
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRAssetProduct> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the h r asset products.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r asset products to return
	* @param end the upper bound of the range of h r asset products to return (not inclusive)
	* @return the range of h r asset products
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRAssetProduct> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the h r asset products.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r asset products to return
	* @param end the upper bound of the range of h r asset products to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r asset products
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRAssetProduct> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the h r asset products from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the h r asset products.
	*
	* @return the number of h r asset products
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public HRAssetProduct remove(HRAssetProduct hrAssetProduct)
		throws SystemException;
}