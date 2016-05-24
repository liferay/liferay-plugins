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

import com.liferay.samplealloymvc.model.SAMTodoItem;

import java.util.List;

/**
 * The persistence utility for the s a m todo item service. This utility wraps {@link com.liferay.samplealloymvc.service.persistence.impl.SAMTodoItemPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SAMTodoItemPersistence
 * @see com.liferay.samplealloymvc.service.persistence.impl.SAMTodoItemPersistenceImpl
 * @generated
 */
@ProviderType
public class SAMTodoItemUtil {
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
	public static void clearCache(SAMTodoItem samTodoItem) {
		getPersistence().clearCache(samTodoItem);
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
	public static List<SAMTodoItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SAMTodoItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SAMTodoItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SAMTodoItem> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SAMTodoItem update(SAMTodoItem samTodoItem) {
		return getPersistence().update(samTodoItem);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SAMTodoItem update(SAMTodoItem samTodoItem,
		ServiceContext serviceContext) {
		return getPersistence().update(samTodoItem, serviceContext);
	}

	/**
	* Caches the s a m todo item in the entity cache if it is enabled.
	*
	* @param samTodoItem the s a m todo item
	*/
	public static void cacheResult(SAMTodoItem samTodoItem) {
		getPersistence().cacheResult(samTodoItem);
	}

	/**
	* Caches the s a m todo items in the entity cache if it is enabled.
	*
	* @param samTodoItems the s a m todo items
	*/
	public static void cacheResult(List<SAMTodoItem> samTodoItems) {
		getPersistence().cacheResult(samTodoItems);
	}

	/**
	* Creates a new s a m todo item with the primary key. Does not add the s a m todo item to the database.
	*
	* @param samTodoItemId the primary key for the new s a m todo item
	* @return the new s a m todo item
	*/
	public static SAMTodoItem create(long samTodoItemId) {
		return getPersistence().create(samTodoItemId);
	}

	/**
	* Removes the s a m todo item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param samTodoItemId the primary key of the s a m todo item
	* @return the s a m todo item that was removed
	* @throws NoSuchTodoItemException if a s a m todo item with the primary key could not be found
	*/
	public static SAMTodoItem remove(long samTodoItemId)
		throws com.liferay.samplealloymvc.exception.NoSuchTodoItemException {
		return getPersistence().remove(samTodoItemId);
	}

	public static SAMTodoItem updateImpl(SAMTodoItem samTodoItem) {
		return getPersistence().updateImpl(samTodoItem);
	}

	/**
	* Returns the s a m todo item with the primary key or throws a {@link NoSuchTodoItemException} if it could not be found.
	*
	* @param samTodoItemId the primary key of the s a m todo item
	* @return the s a m todo item
	* @throws NoSuchTodoItemException if a s a m todo item with the primary key could not be found
	*/
	public static SAMTodoItem findByPrimaryKey(long samTodoItemId)
		throws com.liferay.samplealloymvc.exception.NoSuchTodoItemException {
		return getPersistence().findByPrimaryKey(samTodoItemId);
	}

	/**
	* Returns the s a m todo item with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param samTodoItemId the primary key of the s a m todo item
	* @return the s a m todo item, or <code>null</code> if a s a m todo item with the primary key could not be found
	*/
	public static SAMTodoItem fetchByPrimaryKey(long samTodoItemId) {
		return getPersistence().fetchByPrimaryKey(samTodoItemId);
	}

	public static java.util.Map<java.io.Serializable, SAMTodoItem> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the s a m todo items.
	*
	* @return the s a m todo items
	*/
	public static List<SAMTodoItem> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the s a m todo items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SAMTodoItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of s a m todo items
	* @param end the upper bound of the range of s a m todo items (not inclusive)
	* @return the range of s a m todo items
	*/
	public static List<SAMTodoItem> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the s a m todo items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SAMTodoItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of s a m todo items
	* @param end the upper bound of the range of s a m todo items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of s a m todo items
	*/
	public static List<SAMTodoItem> findAll(int start, int end,
		OrderByComparator<SAMTodoItem> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the s a m todo items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SAMTodoItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of s a m todo items
	* @param end the upper bound of the range of s a m todo items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of s a m todo items
	*/
	public static List<SAMTodoItem> findAll(int start, int end,
		OrderByComparator<SAMTodoItem> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the s a m todo items from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of s a m todo items.
	*
	* @return the number of s a m todo items
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SAMTodoItemPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SAMTodoItemPersistence)PortletBeanLocatorUtil.locate(com.liferay.samplealloymvc.service.ClpSerializer.getServletContextName(),
					SAMTodoItemPersistence.class.getName());

			ReferenceRegistry.registerReference(SAMTodoItemUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static SAMTodoItemPersistence _persistence;
}