/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.service.ServiceContext;

import com.liferay.socialcoding.model.JIRAChangeItem;

import java.util.List;

/**
 * @author    Brian Wing Shun Chan
 * @see       JIRAChangeItemPersistence
 * @see       JIRAChangeItemPersistenceImpl
 * @generated
 */
public class JIRAChangeItemUtil {
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
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static JIRAChangeItem remove(JIRAChangeItem jiraChangeItem)
		throws SystemException {
		return getPersistence().remove(jiraChangeItem);
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

	public static void cacheResult(
		com.liferay.socialcoding.model.JIRAChangeItem jiraChangeItem) {
		getPersistence().cacheResult(jiraChangeItem);
	}

	public static void cacheResult(
		java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> jiraChangeItems) {
		getPersistence().cacheResult(jiraChangeItems);
	}

	public static com.liferay.socialcoding.model.JIRAChangeItem create(
		long jiraChangeItemId) {
		return getPersistence().create(jiraChangeItemId);
	}

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

	public static com.liferay.socialcoding.model.JIRAChangeItem findByPrimaryKey(
		long jiraChangeItemId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeItemException {
		return getPersistence().findByPrimaryKey(jiraChangeItemId);
	}

	public static com.liferay.socialcoding.model.JIRAChangeItem fetchByPrimaryKey(
		long jiraChangeItemId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(jiraChangeItemId);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByJiraChangeGroupId(jiraChangeGroupId);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByJiraChangeGroupId(jiraChangeGroupId, start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByJiraChangeGroupId(jiraChangeGroupId, start, end,
			orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAChangeItem findByJiraChangeGroupId_First(
		long jiraChangeGroupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeItemException {
		return getPersistence()
				   .findByJiraChangeGroupId_First(jiraChangeGroupId,
			orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAChangeItem findByJiraChangeGroupId_Last(
		long jiraChangeGroupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeItemException {
		return getPersistence()
				   .findByJiraChangeGroupId_Last(jiraChangeGroupId,
			orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAChangeItem[] findByJiraChangeGroupId_PrevAndNext(
		long jiraChangeItemId, long jiraChangeGroupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeItemException {
		return getPersistence()
				   .findByJiraChangeGroupId_PrevAndNext(jiraChangeItemId,
			jiraChangeGroupId, orderByComparator);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	public static void removeByJiraChangeGroupId(long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByJiraChangeGroupId(jiraChangeGroupId);
	}

	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	public static int countByJiraChangeGroupId(long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByJiraChangeGroupId(jiraChangeGroupId);
	}

	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static JIRAChangeItemPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (JIRAChangeItemPersistence)PortletBeanLocatorUtil.locate(com.liferay.socialcoding.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					JIRAChangeItemPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(JIRAChangeItemPersistence persistence) {
		_persistence = persistence;
	}

	private static JIRAChangeItemPersistence _persistence;
}