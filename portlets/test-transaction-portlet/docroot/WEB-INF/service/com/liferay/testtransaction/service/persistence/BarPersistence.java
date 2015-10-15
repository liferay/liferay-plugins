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

package com.liferay.testtransaction.service.persistence;

import aQute.bnd.annotation.ProviderType;

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
 * @see com.liferay.testtransaction.service.persistence.impl.BarPersistenceImpl
 * @see BarUtil
 * @generated
 */
@ProviderType
public interface BarPersistence extends BasePersistence<Bar> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BarUtil} to access the bar persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the bars where text = &#63;.
	*
	* @param text the text
	* @return the matching bars
	*/
	public java.util.List<Bar> findByText(java.lang.String text);

	/**
	* Returns a range of all the bars where text = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param text the text
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @return the range of matching bars
	*/
	public java.util.List<Bar> findByText(java.lang.String text, int start,
		int end);

	/**
	* Returns an ordered range of all the bars where text = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param text the text
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching bars
	*/
	public java.util.List<Bar> findByText(java.lang.String text, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator);

	/**
	* Returns an ordered range of all the bars where text = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param text the text
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching bars
	*/
	public java.util.List<Bar> findByText(java.lang.String text, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first bar in the ordered set where text = &#63;.
	*
	* @param text the text
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching bar
	* @throws NoSuchBarException if a matching bar could not be found
	*/
	public Bar findByText_First(java.lang.String text,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator)
		throws com.liferay.testtransaction.NoSuchBarException;

	/**
	* Returns the first bar in the ordered set where text = &#63;.
	*
	* @param text the text
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching bar, or <code>null</code> if a matching bar could not be found
	*/
	public Bar fetchByText_First(java.lang.String text,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator);

	/**
	* Returns the last bar in the ordered set where text = &#63;.
	*
	* @param text the text
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching bar
	* @throws NoSuchBarException if a matching bar could not be found
	*/
	public Bar findByText_Last(java.lang.String text,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator)
		throws com.liferay.testtransaction.NoSuchBarException;

	/**
	* Returns the last bar in the ordered set where text = &#63;.
	*
	* @param text the text
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching bar, or <code>null</code> if a matching bar could not be found
	*/
	public Bar fetchByText_Last(java.lang.String text,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator);

	/**
	* Returns the bars before and after the current bar in the ordered set where text = &#63;.
	*
	* @param barId the primary key of the current bar
	* @param text the text
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next bar
	* @throws NoSuchBarException if a bar with the primary key could not be found
	*/
	public Bar[] findByText_PrevAndNext(long barId, java.lang.String text,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator)
		throws com.liferay.testtransaction.NoSuchBarException;

	/**
	* Removes all the bars where text = &#63; from the database.
	*
	* @param text the text
	*/
	public void removeByText(java.lang.String text);

	/**
	* Returns the number of bars where text = &#63;.
	*
	* @param text the text
	* @return the number of matching bars
	*/
	public int countByText(java.lang.String text);

	/**
	* Caches the bar in the entity cache if it is enabled.
	*
	* @param bar the bar
	*/
	public void cacheResult(Bar bar);

	/**
	* Caches the bars in the entity cache if it is enabled.
	*
	* @param bars the bars
	*/
	public void cacheResult(java.util.List<Bar> bars);

	/**
	* Creates a new bar with the primary key. Does not add the bar to the database.
	*
	* @param barId the primary key for the new bar
	* @return the new bar
	*/
	public Bar create(long barId);

	/**
	* Removes the bar with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param barId the primary key of the bar
	* @return the bar that was removed
	* @throws NoSuchBarException if a bar with the primary key could not be found
	*/
	public Bar remove(long barId)
		throws com.liferay.testtransaction.NoSuchBarException;

	public Bar updateImpl(Bar bar);

	/**
	* Returns the bar with the primary key or throws a {@link NoSuchBarException} if it could not be found.
	*
	* @param barId the primary key of the bar
	* @return the bar
	* @throws NoSuchBarException if a bar with the primary key could not be found
	*/
	public Bar findByPrimaryKey(long barId)
		throws com.liferay.testtransaction.NoSuchBarException;

	/**
	* Returns the bar with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param barId the primary key of the bar
	* @return the bar, or <code>null</code> if a bar with the primary key could not be found
	*/
	public Bar fetchByPrimaryKey(long barId);

	@Override
	public java.util.Map<java.io.Serializable, Bar> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the bars.
	*
	* @return the bars
	*/
	public java.util.List<Bar> findAll();

	/**
	* Returns a range of all the bars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @return the range of bars
	*/
	public java.util.List<Bar> findAll(int start, int end);

	/**
	* Returns an ordered range of all the bars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of bars
	*/
	public java.util.List<Bar> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator);

	/**
	* Returns an ordered range of all the bars.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BarModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of bars
	* @param end the upper bound of the range of bars (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of bars
	*/
	public java.util.List<Bar> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Bar> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the bars from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of bars.
	*
	* @return the number of bars
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}