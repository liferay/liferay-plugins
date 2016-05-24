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

import com.liferay.samplealloymvc.model.SAMTodoList;

import java.util.List;

/**
 * The persistence utility for the s a m todo list service. This utility wraps {@link com.liferay.samplealloymvc.service.persistence.impl.SAMTodoListPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SAMTodoListPersistence
 * @see com.liferay.samplealloymvc.service.persistence.impl.SAMTodoListPersistenceImpl
 * @generated
 */
@ProviderType
public class SAMTodoListUtil {
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
	public static void clearCache(SAMTodoList samTodoList) {
		getPersistence().clearCache(samTodoList);
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
	public static List<SAMTodoList> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SAMTodoList> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SAMTodoList> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SAMTodoList> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SAMTodoList update(SAMTodoList samTodoList) {
		return getPersistence().update(samTodoList);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SAMTodoList update(SAMTodoList samTodoList,
		ServiceContext serviceContext) {
		return getPersistence().update(samTodoList, serviceContext);
	}

	/**
	* Caches the s a m todo list in the entity cache if it is enabled.
	*
	* @param samTodoList the s a m todo list
	*/
	public static void cacheResult(SAMTodoList samTodoList) {
		getPersistence().cacheResult(samTodoList);
	}

	/**
	* Caches the s a m todo lists in the entity cache if it is enabled.
	*
	* @param samTodoLists the s a m todo lists
	*/
	public static void cacheResult(List<SAMTodoList> samTodoLists) {
		getPersistence().cacheResult(samTodoLists);
	}

	/**
	* Creates a new s a m todo list with the primary key. Does not add the s a m todo list to the database.
	*
	* @param samTodoListId the primary key for the new s a m todo list
	* @return the new s a m todo list
	*/
	public static SAMTodoList create(long samTodoListId) {
		return getPersistence().create(samTodoListId);
	}

	/**
	* Removes the s a m todo list with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param samTodoListId the primary key of the s a m todo list
	* @return the s a m todo list that was removed
	* @throws NoSuchTodoListException if a s a m todo list with the primary key could not be found
	*/
	public static SAMTodoList remove(long samTodoListId)
		throws com.liferay.samplealloymvc.exception.NoSuchTodoListException {
		return getPersistence().remove(samTodoListId);
	}

	public static SAMTodoList updateImpl(SAMTodoList samTodoList) {
		return getPersistence().updateImpl(samTodoList);
	}

	/**
	* Returns the s a m todo list with the primary key or throws a {@link NoSuchTodoListException} if it could not be found.
	*
	* @param samTodoListId the primary key of the s a m todo list
	* @return the s a m todo list
	* @throws NoSuchTodoListException if a s a m todo list with the primary key could not be found
	*/
	public static SAMTodoList findByPrimaryKey(long samTodoListId)
		throws com.liferay.samplealloymvc.exception.NoSuchTodoListException {
		return getPersistence().findByPrimaryKey(samTodoListId);
	}

	/**
	* Returns the s a m todo list with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param samTodoListId the primary key of the s a m todo list
	* @return the s a m todo list, or <code>null</code> if a s a m todo list with the primary key could not be found
	*/
	public static SAMTodoList fetchByPrimaryKey(long samTodoListId) {
		return getPersistence().fetchByPrimaryKey(samTodoListId);
	}

	public static java.util.Map<java.io.Serializable, SAMTodoList> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the s a m todo lists.
	*
	* @return the s a m todo lists
	*/
	public static List<SAMTodoList> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the s a m todo lists.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SAMTodoListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of s a m todo lists
	* @param end the upper bound of the range of s a m todo lists (not inclusive)
	* @return the range of s a m todo lists
	*/
	public static List<SAMTodoList> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the s a m todo lists.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SAMTodoListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of s a m todo lists
	* @param end the upper bound of the range of s a m todo lists (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of s a m todo lists
	*/
	public static List<SAMTodoList> findAll(int start, int end,
		OrderByComparator<SAMTodoList> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the s a m todo lists.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SAMTodoListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of s a m todo lists
	* @param end the upper bound of the range of s a m todo lists (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of s a m todo lists
	*/
	public static List<SAMTodoList> findAll(int start, int end,
		OrderByComparator<SAMTodoList> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the s a m todo lists from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of s a m todo lists.
	*
	* @return the number of s a m todo lists
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SAMTodoListPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SAMTodoListPersistence)PortletBeanLocatorUtil.locate(com.liferay.samplealloymvc.service.ClpSerializer.getServletContextName(),
					SAMTodoListPersistence.class.getName());

			ReferenceRegistry.registerReference(SAMTodoListUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static SAMTodoListPersistence _persistence;
}