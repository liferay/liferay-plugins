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

import com.liferay.socialcoding.model.JIRAChangeGroup;

import java.util.List;

/**
 * @author    Brian Wing Shun Chan
 * @see       JIRAChangeGroupPersistence
 * @see       JIRAChangeGroupPersistenceImpl
 * @generated
 */
public class JIRAChangeGroupUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(JIRAChangeGroup jiraChangeGroup) {
		getPersistence().clearCache(jiraChangeGroup);
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
	public static List<JIRAChangeGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<JIRAChangeGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<JIRAChangeGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static JIRAChangeGroup remove(JIRAChangeGroup jiraChangeGroup)
		throws SystemException {
		return getPersistence().remove(jiraChangeGroup);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static JIRAChangeGroup update(JIRAChangeGroup jiraChangeGroup,
		boolean merge) throws SystemException {
		return getPersistence().update(jiraChangeGroup, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static JIRAChangeGroup update(JIRAChangeGroup jiraChangeGroup,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(jiraChangeGroup, merge, serviceContext);
	}

	public static void cacheResult(
		com.liferay.socialcoding.model.JIRAChangeGroup jiraChangeGroup) {
		getPersistence().cacheResult(jiraChangeGroup);
	}

	public static void cacheResult(
		java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> jiraChangeGroups) {
		getPersistence().cacheResult(jiraChangeGroups);
	}

	public static com.liferay.socialcoding.model.JIRAChangeGroup create(
		long jiraChangeGroupId) {
		return getPersistence().create(jiraChangeGroupId);
	}

	public static com.liferay.socialcoding.model.JIRAChangeGroup remove(
		long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException {
		return getPersistence().remove(jiraChangeGroupId);
	}

	public static com.liferay.socialcoding.model.JIRAChangeGroup updateImpl(
		com.liferay.socialcoding.model.JIRAChangeGroup jiraChangeGroup,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(jiraChangeGroup, merge);
	}

	public static com.liferay.socialcoding.model.JIRAChangeGroup findByPrimaryKey(
		long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException {
		return getPersistence().findByPrimaryKey(jiraChangeGroupId);
	}

	public static com.liferay.socialcoding.model.JIRAChangeGroup fetchByPrimaryKey(
		long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(jiraChangeGroupId);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findByJiraUserId(
		java.lang.String jiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByJiraUserId(jiraUserId);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findByJiraUserId(
		java.lang.String jiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByJiraUserId(jiraUserId, start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findByJiraUserId(
		java.lang.String jiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByJiraUserId(jiraUserId, start, end, orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAChangeGroup findByJiraUserId_First(
		java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException {
		return getPersistence()
				   .findByJiraUserId_First(jiraUserId, orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAChangeGroup findByJiraUserId_Last(
		java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException {
		return getPersistence()
				   .findByJiraUserId_Last(jiraUserId, orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAChangeGroup[] findByJiraUserId_PrevAndNext(
		long jiraChangeGroupId, java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException {
		return getPersistence()
				   .findByJiraUserId_PrevAndNext(jiraChangeGroupId, jiraUserId,
			orderByComparator);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findByJiraIssueId(
		long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByJiraIssueId(jiraIssueId);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findByJiraIssueId(
		long jiraIssueId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByJiraIssueId(jiraIssueId, start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findByJiraIssueId(
		long jiraIssueId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByJiraIssueId(jiraIssueId, start, end, orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAChangeGroup findByJiraIssueId_First(
		long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException {
		return getPersistence()
				   .findByJiraIssueId_First(jiraIssueId, orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAChangeGroup findByJiraIssueId_Last(
		long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException {
		return getPersistence()
				   .findByJiraIssueId_Last(jiraIssueId, orderByComparator);
	}

	public static com.liferay.socialcoding.model.JIRAChangeGroup[] findByJiraIssueId_PrevAndNext(
		long jiraChangeGroupId, long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException {
		return getPersistence()
				   .findByJiraIssueId_PrevAndNext(jiraChangeGroupId,
			jiraIssueId, orderByComparator);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	public static void removeByJiraUserId(java.lang.String jiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByJiraUserId(jiraUserId);
	}

	public static void removeByJiraIssueId(long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByJiraIssueId(jiraIssueId);
	}

	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	public static int countByJiraUserId(java.lang.String jiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByJiraUserId(jiraUserId);
	}

	public static int countByJiraIssueId(long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByJiraIssueId(jiraIssueId);
	}

	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static JIRAChangeGroupPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (JIRAChangeGroupPersistence)PortletBeanLocatorUtil.locate(com.liferay.socialcoding.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					JIRAChangeGroupPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(JIRAChangeGroupPersistence persistence) {
		_persistence = persistence;
	}

	private static JIRAChangeGroupPersistence _persistence;
}