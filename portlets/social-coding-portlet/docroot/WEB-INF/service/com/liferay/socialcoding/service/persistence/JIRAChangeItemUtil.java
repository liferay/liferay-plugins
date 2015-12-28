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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.socialcoding.model.JIRAChangeItem;

import java.util.List;

/**
 * The persistence utility for the j i r a change item service. This utility wraps {@link com.liferay.socialcoding.service.persistence.impl.JIRAChangeItemPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAChangeItemPersistence
 * @see com.liferay.socialcoding.service.persistence.impl.JIRAChangeItemPersistenceImpl
 * @generated
 */
@ProviderType
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
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<JIRAChangeItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<JIRAChangeItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<JIRAChangeItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<JIRAChangeItem> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static JIRAChangeItem update(JIRAChangeItem jiraChangeItem) {
		return getPersistence().update(jiraChangeItem);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static JIRAChangeItem update(JIRAChangeItem jiraChangeItem,
		ServiceContext serviceContext) {
		return getPersistence().update(jiraChangeItem, serviceContext);
	}

	/**
	* Returns all the j i r a change items where jiraChangeGroupId = &#63;.
	*
	* @param jiraChangeGroupId the jira change group ID
	* @return the matching j i r a change items
	*/
	public static List<JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId) {
		return getPersistence().findByJiraChangeGroupId(jiraChangeGroupId);
	}

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
	public static List<JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId, int start, int end) {
		return getPersistence()
				   .findByJiraChangeGroupId(jiraChangeGroupId, start, end);
	}

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
	public static List<JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId, int start, int end,
		OrderByComparator<JIRAChangeItem> orderByComparator) {
		return getPersistence()
				   .findByJiraChangeGroupId(jiraChangeGroupId, start, end,
			orderByComparator);
	}

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
	public static List<JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId, int start, int end,
		OrderByComparator<JIRAChangeItem> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByJiraChangeGroupId(jiraChangeGroupId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first j i r a change item in the ordered set where jiraChangeGroupId = &#63;.
	*
	* @param jiraChangeGroupId the jira change group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching j i r a change item
	* @throws NoSuchJIRAChangeItemException if a matching j i r a change item could not be found
	*/
	public static JIRAChangeItem findByJiraChangeGroupId_First(
		long jiraChangeGroupId,
		OrderByComparator<JIRAChangeItem> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAChangeItemException {
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
	*/
	public static JIRAChangeItem fetchByJiraChangeGroupId_First(
		long jiraChangeGroupId,
		OrderByComparator<JIRAChangeItem> orderByComparator) {
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
	* @throws NoSuchJIRAChangeItemException if a matching j i r a change item could not be found
	*/
	public static JIRAChangeItem findByJiraChangeGroupId_Last(
		long jiraChangeGroupId,
		OrderByComparator<JIRAChangeItem> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAChangeItemException {
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
	*/
	public static JIRAChangeItem fetchByJiraChangeGroupId_Last(
		long jiraChangeGroupId,
		OrderByComparator<JIRAChangeItem> orderByComparator) {
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
	* @throws NoSuchJIRAChangeItemException if a j i r a change item with the primary key could not be found
	*/
	public static JIRAChangeItem[] findByJiraChangeGroupId_PrevAndNext(
		long jiraChangeItemId, long jiraChangeGroupId,
		OrderByComparator<JIRAChangeItem> orderByComparator)
		throws com.liferay.socialcoding.NoSuchJIRAChangeItemException {
		return getPersistence()
				   .findByJiraChangeGroupId_PrevAndNext(jiraChangeItemId,
			jiraChangeGroupId, orderByComparator);
	}

	/**
	* Removes all the j i r a change items where jiraChangeGroupId = &#63; from the database.
	*
	* @param jiraChangeGroupId the jira change group ID
	*/
	public static void removeByJiraChangeGroupId(long jiraChangeGroupId) {
		getPersistence().removeByJiraChangeGroupId(jiraChangeGroupId);
	}

	/**
	* Returns the number of j i r a change items where jiraChangeGroupId = &#63;.
	*
	* @param jiraChangeGroupId the jira change group ID
	* @return the number of matching j i r a change items
	*/
	public static int countByJiraChangeGroupId(long jiraChangeGroupId) {
		return getPersistence().countByJiraChangeGroupId(jiraChangeGroupId);
	}

	/**
	* Caches the j i r a change item in the entity cache if it is enabled.
	*
	* @param jiraChangeItem the j i r a change item
	*/
	public static void cacheResult(JIRAChangeItem jiraChangeItem) {
		getPersistence().cacheResult(jiraChangeItem);
	}

	/**
	* Caches the j i r a change items in the entity cache if it is enabled.
	*
	* @param jiraChangeItems the j i r a change items
	*/
	public static void cacheResult(List<JIRAChangeItem> jiraChangeItems) {
		getPersistence().cacheResult(jiraChangeItems);
	}

	/**
	* Creates a new j i r a change item with the primary key. Does not add the j i r a change item to the database.
	*
	* @param jiraChangeItemId the primary key for the new j i r a change item
	* @return the new j i r a change item
	*/
	public static JIRAChangeItem create(long jiraChangeItemId) {
		return getPersistence().create(jiraChangeItemId);
	}

	/**
	* Removes the j i r a change item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraChangeItemId the primary key of the j i r a change item
	* @return the j i r a change item that was removed
	* @throws NoSuchJIRAChangeItemException if a j i r a change item with the primary key could not be found
	*/
	public static JIRAChangeItem remove(long jiraChangeItemId)
		throws com.liferay.socialcoding.NoSuchJIRAChangeItemException {
		return getPersistence().remove(jiraChangeItemId);
	}

	public static JIRAChangeItem updateImpl(JIRAChangeItem jiraChangeItem) {
		return getPersistence().updateImpl(jiraChangeItem);
	}

	/**
	* Returns the j i r a change item with the primary key or throws a {@link NoSuchJIRAChangeItemException} if it could not be found.
	*
	* @param jiraChangeItemId the primary key of the j i r a change item
	* @return the j i r a change item
	* @throws NoSuchJIRAChangeItemException if a j i r a change item with the primary key could not be found
	*/
	public static JIRAChangeItem findByPrimaryKey(long jiraChangeItemId)
		throws com.liferay.socialcoding.NoSuchJIRAChangeItemException {
		return getPersistence().findByPrimaryKey(jiraChangeItemId);
	}

	/**
	* Returns the j i r a change item with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param jiraChangeItemId the primary key of the j i r a change item
	* @return the j i r a change item, or <code>null</code> if a j i r a change item with the primary key could not be found
	*/
	public static JIRAChangeItem fetchByPrimaryKey(long jiraChangeItemId) {
		return getPersistence().fetchByPrimaryKey(jiraChangeItemId);
	}

	public static java.util.Map<java.io.Serializable, JIRAChangeItem> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the j i r a change items.
	*
	* @return the j i r a change items
	*/
	public static List<JIRAChangeItem> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<JIRAChangeItem> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<JIRAChangeItem> findAll(int start, int end,
		OrderByComparator<JIRAChangeItem> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<JIRAChangeItem> findAll(int start, int end,
		OrderByComparator<JIRAChangeItem> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the j i r a change items from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of j i r a change items.
	*
	* @return the number of j i r a change items
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
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

	private static JIRAChangeItemPersistence _persistence;
}