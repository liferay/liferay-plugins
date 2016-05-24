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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.liferay.samplealloymvc.exception.NoSuchTodoItemException;
import com.liferay.samplealloymvc.model.SAMTodoItem;

/**
 * The persistence interface for the s a m todo item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.samplealloymvc.service.persistence.impl.SAMTodoItemPersistenceImpl
 * @see SAMTodoItemUtil
 * @generated
 */
@ProviderType
public interface SAMTodoItemPersistence extends BasePersistence<SAMTodoItem> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SAMTodoItemUtil} to access the s a m todo item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the s a m todo item in the entity cache if it is enabled.
	*
	* @param samTodoItem the s a m todo item
	*/
	public void cacheResult(SAMTodoItem samTodoItem);

	/**
	* Caches the s a m todo items in the entity cache if it is enabled.
	*
	* @param samTodoItems the s a m todo items
	*/
	public void cacheResult(java.util.List<SAMTodoItem> samTodoItems);

	/**
	* Creates a new s a m todo item with the primary key. Does not add the s a m todo item to the database.
	*
	* @param samTodoItemId the primary key for the new s a m todo item
	* @return the new s a m todo item
	*/
	public SAMTodoItem create(long samTodoItemId);

	/**
	* Removes the s a m todo item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param samTodoItemId the primary key of the s a m todo item
	* @return the s a m todo item that was removed
	* @throws NoSuchTodoItemException if a s a m todo item with the primary key could not be found
	*/
	public SAMTodoItem remove(long samTodoItemId)
		throws NoSuchTodoItemException;

	public SAMTodoItem updateImpl(SAMTodoItem samTodoItem);

	/**
	* Returns the s a m todo item with the primary key or throws a {@link NoSuchTodoItemException} if it could not be found.
	*
	* @param samTodoItemId the primary key of the s a m todo item
	* @return the s a m todo item
	* @throws NoSuchTodoItemException if a s a m todo item with the primary key could not be found
	*/
	public SAMTodoItem findByPrimaryKey(long samTodoItemId)
		throws NoSuchTodoItemException;

	/**
	* Returns the s a m todo item with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param samTodoItemId the primary key of the s a m todo item
	* @return the s a m todo item, or <code>null</code> if a s a m todo item with the primary key could not be found
	*/
	public SAMTodoItem fetchByPrimaryKey(long samTodoItemId);

	@Override
	public java.util.Map<java.io.Serializable, SAMTodoItem> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the s a m todo items.
	*
	* @return the s a m todo items
	*/
	public java.util.List<SAMTodoItem> findAll();

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
	public java.util.List<SAMTodoItem> findAll(int start, int end);

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
	public java.util.List<SAMTodoItem> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SAMTodoItem> orderByComparator);

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
	public java.util.List<SAMTodoItem> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SAMTodoItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the s a m todo items from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of s a m todo items.
	*
	* @return the number of s a m todo items
	*/
	public int countAll();
}