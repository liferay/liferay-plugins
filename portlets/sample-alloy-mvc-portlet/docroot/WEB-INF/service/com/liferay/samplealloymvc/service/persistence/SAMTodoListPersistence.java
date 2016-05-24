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

import com.liferay.samplealloymvc.exception.NoSuchTodoListException;
import com.liferay.samplealloymvc.model.SAMTodoList;

/**
 * The persistence interface for the s a m todo list service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.samplealloymvc.service.persistence.impl.SAMTodoListPersistenceImpl
 * @see SAMTodoListUtil
 * @generated
 */
@ProviderType
public interface SAMTodoListPersistence extends BasePersistence<SAMTodoList> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SAMTodoListUtil} to access the s a m todo list persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the s a m todo list in the entity cache if it is enabled.
	*
	* @param samTodoList the s a m todo list
	*/
	public void cacheResult(SAMTodoList samTodoList);

	/**
	* Caches the s a m todo lists in the entity cache if it is enabled.
	*
	* @param samTodoLists the s a m todo lists
	*/
	public void cacheResult(java.util.List<SAMTodoList> samTodoLists);

	/**
	* Creates a new s a m todo list with the primary key. Does not add the s a m todo list to the database.
	*
	* @param samTodoListId the primary key for the new s a m todo list
	* @return the new s a m todo list
	*/
	public SAMTodoList create(long samTodoListId);

	/**
	* Removes the s a m todo list with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param samTodoListId the primary key of the s a m todo list
	* @return the s a m todo list that was removed
	* @throws NoSuchTodoListException if a s a m todo list with the primary key could not be found
	*/
	public SAMTodoList remove(long samTodoListId)
		throws NoSuchTodoListException;

	public SAMTodoList updateImpl(SAMTodoList samTodoList);

	/**
	* Returns the s a m todo list with the primary key or throws a {@link NoSuchTodoListException} if it could not be found.
	*
	* @param samTodoListId the primary key of the s a m todo list
	* @return the s a m todo list
	* @throws NoSuchTodoListException if a s a m todo list with the primary key could not be found
	*/
	public SAMTodoList findByPrimaryKey(long samTodoListId)
		throws NoSuchTodoListException;

	/**
	* Returns the s a m todo list with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param samTodoListId the primary key of the s a m todo list
	* @return the s a m todo list, or <code>null</code> if a s a m todo list with the primary key could not be found
	*/
	public SAMTodoList fetchByPrimaryKey(long samTodoListId);

	@Override
	public java.util.Map<java.io.Serializable, SAMTodoList> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the s a m todo lists.
	*
	* @return the s a m todo lists
	*/
	public java.util.List<SAMTodoList> findAll();

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
	public java.util.List<SAMTodoList> findAll(int start, int end);

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
	public java.util.List<SAMTodoList> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SAMTodoList> orderByComparator);

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
	public java.util.List<SAMTodoList> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SAMTodoList> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the s a m todo lists from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of s a m todo lists.
	*
	* @return the number of s a m todo lists
	*/
	public int countAll();
}