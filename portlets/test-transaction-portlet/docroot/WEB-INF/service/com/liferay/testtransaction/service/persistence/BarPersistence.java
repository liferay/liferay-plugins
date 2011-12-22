/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.testtransaction.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.testtransaction.model.Bar;

/**
 * The persistence interface for the bar service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BarPersistenceImpl
 * @see BarUtil
 * @generated
 */
public interface BarPersistence extends BasePersistence<Bar> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BarUtil} to access the bar persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the bar in the entity cache if it is enabled.
	*
	* @param bar the bar
	*/
	public void cacheResult(com.liferay.testtransaction.model.Bar bar);

	/**
	* Caches the bars in the entity cache if it is enabled.
	*
	* @param bars the bars
	*/
	public void cacheResult(
		java.util.List<com.liferay.testtransaction.model.Bar> bars);

	/**
	* Creates a new bar with the primary key. Does not add the bar to the database.
	*
	* @param barId the primary key for the new bar
	* @return the new bar
	*/
	public com.liferay.testtransaction.model.Bar create(long barId);

	/**
	* Removes the bar with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param barId the primary key of the bar
	* @return the bar that was removed
	* @throws com.liferay.testtransaction.NoSuchBarException if a bar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testtransaction.model.Bar remove(long barId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.testtransaction.NoSuchBarException;

	public com.liferay.testtransaction.model.Bar updateImpl(
		com.liferay.testtransaction.model.Bar bar, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the bar with the primary key or throws a {@link com.liferay.testtransaction.NoSuchBarException} if it could not be found.
	*
	* @param barId the primary key of the bar
	* @return the bar
	* @throws com.liferay.testtransaction.NoSuchBarException if a bar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testtransaction.model.Bar findByPrimaryKey(long barId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.testtransaction.NoSuchBarException;

	/**
	* Returns the bar with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param barId the primary key of the bar
	* @return the bar, or <code>null</code> if a bar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testtransaction.model.Bar fetchByPrimaryKey(long barId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the bars where text = &#63;.
	*
	* @param text the text
	* @return the matching bars
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.testtransaction.model.Bar> findByText(
		java.lang.String text)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the bars where text = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param text the text
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @return the range of matching bars
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.testtransaction.model.Bar> findByText(
		java.lang.String text, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the bars where text = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param text the text
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching bars
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.testtransaction.model.Bar> findByText(
		java.lang.String text, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first bar in the ordered set where text = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param text the text
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching bar
	* @throws com.liferay.testtransaction.NoSuchBarException if a matching bar could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testtransaction.model.Bar findByText_First(
		java.lang.String text,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.testtransaction.NoSuchBarException;

	/**
	* Returns the last bar in the ordered set where text = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param text the text
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching bar
	* @throws com.liferay.testtransaction.NoSuchBarException if a matching bar could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testtransaction.model.Bar findByText_Last(
		java.lang.String text,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.testtransaction.NoSuchBarException;

	/**
	* Returns the bars before and after the current bar in the ordered set where text = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param barId the primary key of the current bar
	* @param text the text
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next bar
	* @throws com.liferay.testtransaction.NoSuchBarException if a bar with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.testtransaction.model.Bar[] findByText_PrevAndNext(
		long barId, java.lang.String text,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.testtransaction.NoSuchBarException;

	/**
	* Returns all the bars.
	*
	* @return the bars
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.testtransaction.model.Bar> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the bars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @return the range of bars
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.testtransaction.model.Bar> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the bars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of bars
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.testtransaction.model.Bar> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the bars where text = &#63; from the database.
	*
	* @param text the text
	* @throws SystemException if a system exception occurred
	*/
	public void removeByText(java.lang.String text)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the bars from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of bars where text = &#63;.
	*
	* @param text the text
	* @return the number of matching bars
	* @throws SystemException if a system exception occurred
	*/
	public int countByText(java.lang.String text)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of bars.
	*
	* @return the number of bars
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}