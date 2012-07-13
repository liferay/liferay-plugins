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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.socialcoding.model.JIRAChangeItem;

import java.util.List;

/**
 * The persistence utility for the j i r a change item service. This utility wraps {@link JIRAChangeItemPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAChangeItemPersistence
 * @see JIRAChangeItemPersistenceImpl
 * @generated
 */
public class JIRAChangeItemUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(JIRAChangeItem jiraChangeItem) {
		getPersistence().clearCache(jiraChangeItem);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<JIRAChangeItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<JIRAChangeItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<JIRAChangeItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static JIRAChangeItem update(JIRAChangeItem jiraChangeItem,
		boolean merge) throws SystemException {
		return getPersistence().update(jiraChangeItem, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static JIRAChangeItem update(JIRAChangeItem jiraChangeItem,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(jiraChangeItem, merge, serviceContext);
	}

	/**
	* Caches the j i r a change item in the entity cache if it is enabled.
	*
	* @param jiraChangeItem the j i r a change item
	*/
	public static void cacheResult(
		com.liferay.socialcoding.model.JIRAChangeItem jiraChangeItem) {
		getPersistence().cacheResult(jiraChangeItem);
	}

	/**
	* Caches the j i r a change items in the entity cache if it is enabled.
	*
	* @param jiraChangeItems the j i r a change items
	*/
	public static void cacheResult(
		java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> jiraChangeItems) {
		getPersistence().cacheResult(jiraChangeItems);
	}

	/**
	* Creates a new j i r a change item with the primary key. Does not add the j i r a change item to the database.
	*
	* @param jiraChangeItemId the primary key for the new j i r a change item
	* @return the new j i r a change item
	*/
	public static com.liferay.socialcoding.model.JIRAChangeItem create(
		long jiraChangeItemId) {
		return getPersistence().create(jiraChangeItemId);
	}

	/**
	* Removes the j i r a change item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraChangeItemId the primary key of the j i r a change item
	* @return the j i r a change item that was removed
	* @throws com.liferay.socialcoding.NoSuchJIRAChangeItemException if a j i r a change item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAChangeItem remove(
		long jiraChangeItemId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeItemException {
		return getPersistence().remove(jiraChangeItemId);
	}

	public static com.liferay.socialcoding.model.JIRAChangeItem updateImpl(
		com.liferay.socialcoding.model.JIRAChangeItem jiraChangeItem,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(jiraChangeItem, merge);
	}

	/**
	* Returns the j i r a change item with the primary key or throws a {@link com.liferay.socialcoding.NoSuchJIRAChangeItemException} if it could not be found.
	*
	* @param jiraChangeItemId the primary key of the j i r a change item
	* @return the j i r a change item
	* @throws com.liferay.socialcoding.NoSuchJIRAChangeItemException if a j i r a change item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAChangeItem findByPrimaryKey(
		long jiraChangeItemId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeItemException {
		return getPersistence().findByPrimaryKey(jiraChangeItemId);
	}

	/**
	* Returns the j i r a change item with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param jiraChangeItemId the primary key of the j i r a change item
	* @return the j i r a change item, or <code>null</code> if a j i r a change item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAChangeItem fetchByPrimaryKey(
		long jiraChangeItemId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(jiraChangeItemId);
	}

	/**
	* Returns all the j i r a change items where jiraChangeGroupId = &#63;.
	*
	* @param jiraChangeGroupId the jira change group ID
	* @return the matching j i r a change items
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByJiraChangeGroupId(jiraChangeGroupId);
	}

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
	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByJiraChangeGroupId(jiraChangeGroupId, start, end);
	}

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
	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByJiraChangeGroupId(jiraChangeGroupId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first j i r a change item in the ordered set where jiraChangeGroupId = &#63;.
	*
	* @param jiraChangeGroupId the jira change group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a change item
	* @throws com.liferay.socialcoding.NoSuchJIRAChangeItemException if a matching j i r a change item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAChangeItem findByJiraChangeGroupId_First(
		long jiraChangeGroupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeItemException {
		return getPersistence()
				   .findByJiraChangeGroupId_First(jiraChangeGroupId,
			orderByComparator);
	}

	/**
	* Returns the first j i r a change item in the ordered set where jiraChangeGroupId = &#63;.
	*
	* @param jiraChangeGroupId the jira change group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a change item, or <code>null</code> if a matching j i r a change item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAChangeItem fetchByJiraChangeGroupId_First(
		long jiraChangeGroupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByJiraChangeGroupId_First(jiraChangeGroupId,
			orderByComparator);
	}

	/**
	* Returns the last j i r a change item in the ordered set where jiraChangeGroupId = &#63;.
	*
	* @param jiraChangeGroupId the jira change group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a change item
	* @throws com.liferay.socialcoding.NoSuchJIRAChangeItemException if a matching j i r a change item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAChangeItem findByJiraChangeGroupId_Last(
		long jiraChangeGroupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeItemException {
		return getPersistence()
				   .findByJiraChangeGroupId_Last(jiraChangeGroupId,
			orderByComparator);
	}

	/**
	* Returns the last j i r a change item in the ordered set where jiraChangeGroupId = &#63;.
	*
	* @param jiraChangeGroupId the jira change group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching j i r a change item, or <code>null</code> if a matching j i r a change item could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.JIRAChangeItem fetchByJiraChangeGroupId_Last(
		long jiraChangeGroupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByJiraChangeGroupId_Last(jiraChangeGroupId,
			orderByComparator);
	}

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
	public static com.liferay.socialcoding.model.JIRAChangeItem[] findByJiraChangeGroupId_PrevAndNext(
		long jiraChangeItemId, long jiraChangeGroupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeItemException {
		return getPersistence()
				   .findByJiraChangeGroupId_PrevAndNext(jiraChangeItemId,
			jiraChangeGroupId, orderByComparator);
	}

	/**
	* Returns all the j i r a change items.
	*
	* @return the j i r a change items
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the j i r a change items where jiraChangeGroupId = &#63; from the database.
	*
	* @param jiraChangeGroupId the jira change group ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByJiraChangeGroupId(long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByJiraChangeGroupId(jiraChangeGroupId);
	}

	/**
	* Removes all the j i r a change items from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of j i r a change items where jiraChangeGroupId = &#63;.
	*
	* @param jiraChangeGroupId the jira change group ID
	* @return the number of matching j i r a change items
	* @throws SystemException if a system exception occurred
	*/
	public static int countByJiraChangeGroupId(long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByJiraChangeGroupId(jiraChangeGroupId);
	}

	/**
	* Returns the number of j i r a change items.
	*
	* @return the number of j i r a change items
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static JIRAChangeItemPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (JIRAChangeItemPersistence)PortletBeanLocatorUtil.locate(com.liferay.socialcoding.service.ClpSerializer.getServletContextName(),
					JIRAChangeItemPersistence.class.getName());

			ReferenceRegistry.registerReference(JIRAChangeItemUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(JIRAChangeItemPersistence persistence) {
	}

	private static JIRAChangeItemPersistence _persistence;
}