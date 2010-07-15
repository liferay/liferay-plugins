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

import com.liferay.socialcoding.model.JIRAChangeGroup;

/**
 * @author    Brian Wing Shun Chan
 * @see       JIRAChangeGroupPersistenceImpl
 * @see       JIRAChangeGroupUtil
 * @generated
 */
public interface JIRAChangeGroupPersistence extends BasePersistence<JIRAChangeGroup> {
	public void cacheResult(
		com.liferay.socialcoding.model.JIRAChangeGroup jiraChangeGroup);

	public void cacheResult(
		java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> jiraChangeGroups);

	public com.liferay.socialcoding.model.JIRAChangeGroup create(
		long jiraChangeGroupId);

	public com.liferay.socialcoding.model.JIRAChangeGroup remove(
		long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException;

	public com.liferay.socialcoding.model.JIRAChangeGroup updateImpl(
		com.liferay.socialcoding.model.JIRAChangeGroup jiraChangeGroup,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialcoding.model.JIRAChangeGroup findByPrimaryKey(
		long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException;

	public com.liferay.socialcoding.model.JIRAChangeGroup fetchByPrimaryKey(
		long jiraChangeGroupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findByJiraUserId(
		java.lang.String jiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findByJiraUserId(
		java.lang.String jiraUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findByJiraUserId(
		java.lang.String jiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialcoding.model.JIRAChangeGroup findByJiraUserId_First(
		java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException;

	public com.liferay.socialcoding.model.JIRAChangeGroup findByJiraUserId_Last(
		java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException;

	public com.liferay.socialcoding.model.JIRAChangeGroup[] findByJiraUserId_PrevAndNext(
		long jiraChangeGroupId, java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException;

	public java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findByJiraIssueId(
		long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findByJiraIssueId(
		long jiraIssueId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findByJiraIssueId(
		long jiraIssueId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialcoding.model.JIRAChangeGroup findByJiraIssueId_First(
		long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException;

	public com.liferay.socialcoding.model.JIRAChangeGroup findByJiraIssueId_Last(
		long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException;

	public com.liferay.socialcoding.model.JIRAChangeGroup[] findByJiraIssueId_PrevAndNext(
		long jiraChangeGroupId, long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchJIRAChangeGroupException;

	public java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByJiraUserId(java.lang.String jiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByJiraIssueId(long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByJiraUserId(java.lang.String jiraUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByJiraIssueId(long jiraIssueId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}