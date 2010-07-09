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

package com.liferay.so.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.so.model.ProjectsEntry;

/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ProjectsEntryPersistenceImpl
 * @see       ProjectsEntryUtil
 * @generated
 */
public interface ProjectsEntryPersistence extends BasePersistence<ProjectsEntry> {
	public void cacheResult(com.liferay.so.model.ProjectsEntry projectsEntry);

	public void cacheResult(
		java.util.List<com.liferay.so.model.ProjectsEntry> projectsEntries);

	public com.liferay.so.model.ProjectsEntry create(long projectsEntryId);

	public com.liferay.so.model.ProjectsEntry remove(long projectsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchProjectsEntryException;

	public com.liferay.so.model.ProjectsEntry updateImpl(
		com.liferay.so.model.ProjectsEntry projectsEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.so.model.ProjectsEntry findByPrimaryKey(
		long projectsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchProjectsEntryException;

	public com.liferay.so.model.ProjectsEntry fetchByPrimaryKey(
		long projectsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.so.model.ProjectsEntry> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.so.model.ProjectsEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.so.model.ProjectsEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.so.model.ProjectsEntry findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchProjectsEntryException;

	public com.liferay.so.model.ProjectsEntry findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchProjectsEntryException;

	public com.liferay.so.model.ProjectsEntry[] findByUserId_PrevAndNext(
		long projectsEntryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchProjectsEntryException;

	public java.util.List<com.liferay.so.model.ProjectsEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.so.model.ProjectsEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.so.model.ProjectsEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}