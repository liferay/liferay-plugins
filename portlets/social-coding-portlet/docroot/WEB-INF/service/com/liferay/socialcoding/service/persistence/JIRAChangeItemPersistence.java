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

package com.liferay.socialcoding.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.liferay.socialcoding.exception.NoSuchJIRAChangeItemException;
import com.liferay.socialcoding.model.JIRAChangeItem;

/**
 * The persistence interface for the j i r a change item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.socialcoding.service.persistence.impl.JIRAChangeItemPersistenceImpl
 * @see JIRAChangeItemUtil
 * @generated
 */
@ProviderType
public interface JIRAChangeItemPersistence extends BasePersistence<JIRAChangeItem> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link JIRAChangeItemUtil} to access the j i r a change item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the j i r a change items where jiraChangeGroupId = &#63;.
	*
	* @param jiraChangeGroupId the jira change group ID
	* @return the matching j i r a change items
	*/
	public java.util.List<JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId);

	/**
	* Returns a range of all the j i r a change items where jiraChangeGroupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAChangeItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param jiraChangeGroupId the jira change group ID
	* @param start the lower bound of the range of j i r a change items
	* @param end the upper bound of the range of j i r a change items (not inclusive)
	* @return the range of matching j i r a change items
	*/
	public java.util.List<JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId, int start, int end);

	/**
	* Returns an ordered range of all the j i r a change items where jiraChangeGroupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAChangeItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param jiraChangeGroupId the jira change group ID
	* @param start the lower bound of the range of j i r a change items
	* @param end the upper bound of the range of j i r a change items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a change items
	*/
	public java.util.List<JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAChangeItem> orderByComparator);

	/**
	* Returns an ordered range of all the j i r a change items where jiraChangeGroupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAChangeItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param jiraChangeGroupId the jira change group ID
	* @param start the lower bound of the range of j i r a change items
	* @param end the upper bound of the range of j i r a change items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching j i r a change items
	*/
	public java.util.List<JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAChangeItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first j i r a change item in the ordered set where jiraChangeGroupId = &#63;.
	*
	* @param jiraChangeGroupId the jira change group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a change item
	* @throws NoSuchJIRAChangeItemException if a matching j i r a change item could not be found
	*/
	public JIRAChangeItem findByJiraChangeGroupId_First(
		long jiraChangeGroupId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAChangeItem> orderByComparator)
		throws NoSuchJIRAChangeItemException;

	/**
	* Returns the first j i r a change item in the ordered set where jiraChangeGroupId = &#63;.
	*
	* @param jiraChangeGroupId the jira change group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a change item, or <code>null</code> if a matching j i r a change item could not be found
	*/
	public JIRAChangeItem fetchByJiraChangeGroupId_First(
		long jiraChangeGroupId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAChangeItem> orderByComparator);

	/**
	* Returns the last j i r a change item in the ordered set where jiraChangeGroupId = &#63;.
	*
	* @param jiraChangeGroupId the jira change group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a change item
	* @throws NoSuchJIRAChangeItemException if a matching j i r a change item could not be found
	*/
	public JIRAChangeItem findByJiraChangeGroupId_Last(long jiraChangeGroupId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAChangeItem> orderByComparator)
		throws NoSuchJIRAChangeItemException;

	/**
	* Returns the last j i r a change item in the ordered set where jiraChangeGroupId = &#63;.
	*
	* @param jiraChangeGroupId the jira change group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a change item, or <code>null</code> if a matching j i r a change item could not be found
	*/
	public JIRAChangeItem fetchByJiraChangeGroupId_Last(
		long jiraChangeGroupId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAChangeItem> orderByComparator);

	/**
	* Returns the j i r a change items before and after the current j i r a change item in the ordered set where jiraChangeGroupId = &#63;.
	*
	* @param jiraChangeItemId the primary key of the current j i r a change item
	* @param jiraChangeGroupId the jira change group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a change item
	* @throws NoSuchJIRAChangeItemException if a j i r a change item with the primary key could not be found
	*/
	public JIRAChangeItem[] findByJiraChangeGroupId_PrevAndNext(
		long jiraChangeItemId, long jiraChangeGroupId,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAChangeItem> orderByComparator)
		throws NoSuchJIRAChangeItemException;

	/**
	* Removes all the j i r a change items where jiraChangeGroupId = &#63; from the database.
	*
	* @param jiraChangeGroupId the jira change group ID
	*/
	public void removeByJiraChangeGroupId(long jiraChangeGroupId);

	/**
	* Returns the number of j i r a change items where jiraChangeGroupId = &#63;.
	*
	* @param jiraChangeGroupId the jira change group ID
	* @return the number of matching j i r a change items
	*/
	public int countByJiraChangeGroupId(long jiraChangeGroupId);

	/**
	* Caches the j i r a change item in the entity cache if it is enabled.
	*
	* @param jiraChangeItem the j i r a change item
	*/
	public void cacheResult(JIRAChangeItem jiraChangeItem);

	/**
	* Caches the j i r a change items in the entity cache if it is enabled.
	*
	* @param jiraChangeItems the j i r a change items
	*/
	public void cacheResult(java.util.List<JIRAChangeItem> jiraChangeItems);

	/**
	* Creates a new j i r a change item with the primary key. Does not add the j i r a change item to the database.
	*
	* @param jiraChangeItemId the primary key for the new j i r a change item
	* @return the new j i r a change item
	*/
	public JIRAChangeItem create(long jiraChangeItemId);

	/**
	* Removes the j i r a change item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraChangeItemId the primary key of the j i r a change item
	* @return the j i r a change item that was removed
	* @throws NoSuchJIRAChangeItemException if a j i r a change item with the primary key could not be found
	*/
	public JIRAChangeItem remove(long jiraChangeItemId)
		throws NoSuchJIRAChangeItemException;

	public JIRAChangeItem updateImpl(JIRAChangeItem jiraChangeItem);

	/**
	* Returns the j i r a change item with the primary key or throws a {@link NoSuchJIRAChangeItemException} if it could not be found.
	*
	* @param jiraChangeItemId the primary key of the j i r a change item
	* @return the j i r a change item
	* @throws NoSuchJIRAChangeItemException if a j i r a change item with the primary key could not be found
	*/
	public JIRAChangeItem findByPrimaryKey(long jiraChangeItemId)
		throws NoSuchJIRAChangeItemException;

	/**
	* Returns the j i r a change item with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param jiraChangeItemId the primary key of the j i r a change item
	* @return the j i r a change item, or <code>null</code> if a j i r a change item with the primary key could not be found
	*/
	public JIRAChangeItem fetchByPrimaryKey(long jiraChangeItemId);

	@Override
	public java.util.Map<java.io.Serializable, JIRAChangeItem> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the j i r a change items.
	*
	* @return the j i r a change items
	*/
	public java.util.List<JIRAChangeItem> findAll();

	/**
	* Returns a range of all the j i r a change items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAChangeItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of j i r a change items
	* @param end the upper bound of the range of j i r a change items (not inclusive)
	* @return the range of j i r a change items
	*/
	public java.util.List<JIRAChangeItem> findAll(int start, int end);

	/**
	* Returns an ordered range of all the j i r a change items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAChangeItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of j i r a change items
	* @param end the upper bound of the range of j i r a change items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of j i r a change items
	*/
	public java.util.List<JIRAChangeItem> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAChangeItem> orderByComparator);

	/**
	* Returns an ordered range of all the j i r a change items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAChangeItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of j i r a change items
	* @param end the upper bound of the range of j i r a change items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of j i r a change items
	*/
	public java.util.List<JIRAChangeItem> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAChangeItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the j i r a change items from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of j i r a change items.
	*
	* @return the number of j i r a change items
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}