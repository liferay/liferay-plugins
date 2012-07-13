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

package com.liferay.socialcoding.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.socialcoding.model.JIRAChangeItem;

/**
 * The persistence interface for the j i r a change item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAChangeItemPersistenceImpl
 * @see JIRAChangeItemUtil
 * @generated
 */
public interface JIRAChangeItemPersistence extends BasePersistence<JIRAChangeItem> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link JIRAChangeItemUtil} to access the j i r a change item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the j i r a change item in the entity cache if it is enabled.
	*
	* @param jiraChangeItem the j i r a change item
	*/
	public void cacheResult(
		com.liferay.socialcoding.model.JIRAChangeItem jiraChangeItem);

	/**
	* Caches the j i r a change items in the entity cache if it is enabled.
	*
	* @param jiraChangeItems the j i r a change items
	*/
	public void cacheResult(
		java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> jiraChangeItems);

	/**
	* Creates a new j i r a change item with the primary key. Does not add the j i r a change item to the database.
	*
	* @param jiraChangeItemId the primary key for the new j i r a change item
	* @return the new j i r a change item
	*/
	public com.liferay.socialcoding.model.JIRAChangeItem create(
		long jiraChangeItemId);

	/**
	* Removes the j i r a change item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraChangeItemId the primary key of the j i r a change item
	* @return the j i r a change item that was removed
	* @throws com.liferay.socialcoding.NoSuchJIRAChangeItemException if a j i r a change item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAChangeItem remove(
		long jiraChangeItemId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeItemException;

	public com.liferay.socialcoding.model.JIRAChangeItem updateImpl(
		com.liferay.socialcoding.model.JIRAChangeItem jiraChangeItem,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the j i r a change item with the primary key or throws a {@link com.liferay.socialcoding.NoSuchJIRAChangeItemException} if it could not be found.
	*
	* @param jiraChangeItemId the primary key of the j i r a change item
	* @return the j i r a change item
	* @throws com.liferay.socialcoding.NoSuchJIRAChangeItemException if a j i r a change item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAChangeItem findByPrimaryKey(
		long jiraChangeItemId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeItemException;

	/**
	* Returns the j i r a change item with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param jiraChangeItemId the primary key of the j i r a change item
	* @return the j i r a change item, or <code>null</code> if a j i r a change item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAChangeItem fetchByPrimaryKey(
		long jiraChangeItemId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the j i r a change items where jiraChangeGroupId = &#63;.
	*
	* @param jiraChangeGroupId the jira change group ID
	* @return the matching j i r a change items
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the j i r a change items where jiraChangeGroupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraChangeGroupId the jira change group ID
	* @param start the lower bound of the range of j i r a change items
	* @param end the upper bound of the range of j i r a change items (not inclusive)
	* @return the range of matching j i r a change items
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the j i r a change items where jiraChangeGroupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param jiraChangeGroupId the jira change group ID
	* @param start the lower bound of the range of j i r a change items
	* @param end the upper bound of the range of j i r a change items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching j i r a change items
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first j i r a change item in the ordered set where jiraChangeGroupId = &#63;.
	*
	* @param jiraChangeGroupId the jira change group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a change item
	* @throws com.liferay.socialcoding.NoSuchJIRAChangeItemException if a matching j i r a change item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAChangeItem findByJiraChangeGroupId_First(
		long jiraChangeGroupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeItemException;

	/**
	* Returns the first j i r a change item in the ordered set where jiraChangeGroupId = &#63;.
	*
	* @param jiraChangeGroupId the jira change group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a change item, or <code>null</code> if a matching j i r a change item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAChangeItem fetchByJiraChangeGroupId_First(
		long jiraChangeGroupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last j i r a change item in the ordered set where jiraChangeGroupId = &#63;.
	*
	* @param jiraChangeGroupId the jira change group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a change item
	* @throws com.liferay.socialcoding.NoSuchJIRAChangeItemException if a matching j i r a change item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAChangeItem findByJiraChangeGroupId_Last(
		long jiraChangeGroupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeItemException;

	/**
	* Returns the last j i r a change item in the ordered set where jiraChangeGroupId = &#63;.
	*
	* @param jiraChangeGroupId the jira change group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a change item, or <code>null</code> if a matching j i r a change item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAChangeItem fetchByJiraChangeGroupId_Last(
		long jiraChangeGroupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the j i r a change items before and after the current j i r a change item in the ordered set where jiraChangeGroupId = &#63;.
	*
	* @param jiraChangeItemId the primary key of the current j i r a change item
	* @param jiraChangeGroupId the jira change group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next j i r a change item
	* @throws com.liferay.socialcoding.NoSuchJIRAChangeItemException if a j i r a change item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.JIRAChangeItem[] findByJiraChangeGroupId_PrevAndNext(
		long jiraChangeItemId, long jiraChangeGroupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeItemException;

	/**
	* Returns all the j i r a change items.
	*
	* @return the j i r a change items
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the j i r a change items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of j i r a change items
	* @param end the upper bound of the range of j i r a change items (not inclusive)
	* @return the range of j i r a change items
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the j i r a change items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of j i r a change items
	* @param end the upper bound of the range of j i r a change items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of j i r a change items
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the j i r a change items where jiraChangeGroupId = &#63; from the database.
	*
	* @param jiraChangeGroupId the jira change group ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByJiraChangeGroupId(long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the j i r a change items from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of j i r a change items where jiraChangeGroupId = &#63;.
	*
	* @param jiraChangeGroupId the jira change group ID
	* @return the number of matching j i r a change items
	* @throws SystemException if a system exception occurred
	*/
	public int countByJiraChangeGroupId(long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of j i r a change items.
	*
	* @return the number of j i r a change items
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}