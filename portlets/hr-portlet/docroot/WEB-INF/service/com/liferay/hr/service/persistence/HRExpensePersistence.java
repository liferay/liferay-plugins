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

import com.liferay.hr.model.HRExpense;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the h r expense service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HRExpensePersistenceImpl
 * @see HRExpenseUtil
 * @generated
 */
public interface HRExpensePersistence extends BasePersistence<HRExpense> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HRExpenseUtil} to access the h r expense persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the h r expense in the entity cache if it is enabled.
	*
	* @param hrExpense the h r expense to cache
	*/
	public void cacheResult(com.liferay.hr.model.HRExpense hrExpense);

	/**
	* Caches the h r expenses in the entity cache if it is enabled.
	*
	* @param hrExpenses the h r expenses to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.hr.model.HRExpense> hrExpenses);

	/**
	* Creates a new h r expense with the primary key. Does not add the h r expense to the database.
	*
	* @param hrExpenseId the primary key for the new h r expense
	* @return the new h r expense
	*/
	public com.liferay.hr.model.HRExpense create(long hrExpenseId);

	/**
	* Removes the h r expense with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrExpenseId the primary key of the h r expense to remove
	* @return the h r expense that was removed
	* @throws com.liferay.hr.NoSuchExpenseException if a h r expense with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRExpense remove(long hrExpenseId)
		throws com.liferay.hr.NoSuchExpenseException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.hr.model.HRExpense updateImpl(
		com.liferay.hr.model.HRExpense hrExpense, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the h r expense with the primary key or throws a {@link com.liferay.hr.NoSuchExpenseException} if it could not be found.
	*
	* @param hrExpenseId the primary key of the h r expense to find
	* @return the h r expense
	* @throws com.liferay.hr.NoSuchExpenseException if a h r expense with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRExpense findByPrimaryKey(long hrExpenseId)
		throws com.liferay.hr.NoSuchExpenseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the h r expense with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrExpenseId the primary key of the h r expense to find
	* @return the h r expense, or <code>null</code> if a h r expense with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRExpense fetchByPrimaryKey(long hrExpenseId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the h r expenses.
	*
	* @return the h r expenses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRExpense> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the h r expenses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r expenses to return
	* @param end the upper bound of the range of h r expenses to return (not inclusive)
	* @return the range of h r expenses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRExpense> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the h r expenses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r expenses to return
	* @param end the upper bound of the range of h r expenses to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r expenses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRExpense> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the h r expenses from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the h r expenses.
	*
	* @return the number of h r expenses
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public HRExpense remove(HRExpense hrExpense) throws SystemException;
}