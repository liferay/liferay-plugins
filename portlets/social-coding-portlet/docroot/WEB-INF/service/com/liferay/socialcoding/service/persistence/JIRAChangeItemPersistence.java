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

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.socialcoding.model.JIRAChangeItem;

/**
 * @author    Brian Wing Shun Chan
 * @see       JIRAChangeItemPersistenceImpl
 * @see       JIRAChangeItemUtil
 * @generated
 */
public interface JIRAChangeItemPersistence extends BasePersistence<JIRAChangeItem> {
	public void cacheResult(
		com.liferay.socialcoding.model.JIRAChangeItem jiraChangeItem);

	public void cacheResult(
		java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> jiraChangeItems);

	public com.liferay.socialcoding.model.JIRAChangeItem create(
		long jiraChangeItemId);

	public com.liferay.socialcoding.model.JIRAChangeItem remove(
		long jiraChangeItemId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeItemException;

	public com.liferay.socialcoding.model.JIRAChangeItem updateImpl(
		com.liferay.socialcoding.model.JIRAChangeItem jiraChangeItem,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialcoding.model.JIRAChangeItem findByPrimaryKey(
		long jiraChangeItemId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeItemException;

	public com.liferay.socialcoding.model.JIRAChangeItem fetchByPrimaryKey(
		long jiraChangeItemId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialcoding.model.JIRAChangeItem findByJiraChangeGroupId_First(
		long jiraChangeGroupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeItemException;

	public com.liferay.socialcoding.model.JIRAChangeItem findByJiraChangeGroupId_Last(
		long jiraChangeGroupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeItemException;

	public com.liferay.socialcoding.model.JIRAChangeItem[] findByJiraChangeGroupId_PrevAndNext(
		long jiraChangeItemId, long jiraChangeGroupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeItemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAChangeItem> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByJiraChangeGroupId(long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByJiraChangeGroupId(long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}