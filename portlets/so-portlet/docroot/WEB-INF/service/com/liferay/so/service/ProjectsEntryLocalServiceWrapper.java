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

package com.liferay.so.service;


/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link ProjectsEntryLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ProjectsEntryLocalService
 * @generated
 */
public class ProjectsEntryLocalServiceWrapper
	implements ProjectsEntryLocalService {
	public ProjectsEntryLocalServiceWrapper(
		ProjectsEntryLocalService projectsEntryLocalService) {
		_projectsEntryLocalService = projectsEntryLocalService;
	}

	public com.liferay.so.model.ProjectsEntry addProjectsEntry(
		com.liferay.so.model.ProjectsEntry projectsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _projectsEntryLocalService.addProjectsEntry(projectsEntry);
	}

	public com.liferay.so.model.ProjectsEntry createProjectsEntry(
		long projectsEntryId) {
		return _projectsEntryLocalService.createProjectsEntry(projectsEntryId);
	}

	public void deleteProjectsEntry(long projectsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_projectsEntryLocalService.deleteProjectsEntry(projectsEntryId);
	}

	public void deleteProjectsEntry(
		com.liferay.so.model.ProjectsEntry projectsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		_projectsEntryLocalService.deleteProjectsEntry(projectsEntry);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _projectsEntryLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _projectsEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _projectsEntryLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _projectsEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.so.model.ProjectsEntry getProjectsEntry(
		long projectsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _projectsEntryLocalService.getProjectsEntry(projectsEntryId);
	}

	public java.util.List<com.liferay.so.model.ProjectsEntry> getProjectsEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _projectsEntryLocalService.getProjectsEntries(start, end);
	}

	public int getProjectsEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _projectsEntryLocalService.getProjectsEntriesCount();
	}

	public com.liferay.so.model.ProjectsEntry updateProjectsEntry(
		com.liferay.so.model.ProjectsEntry projectsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _projectsEntryLocalService.updateProjectsEntry(projectsEntry);
	}

	public com.liferay.so.model.ProjectsEntry updateProjectsEntry(
		com.liferay.so.model.ProjectsEntry projectsEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _projectsEntryLocalService.updateProjectsEntry(projectsEntry,
			merge);
	}

	public com.liferay.so.model.ProjectsEntry addProjectsEntry(long userId,
		java.lang.String title, java.lang.String description,
		int startDateMonth, int startDateDay, int startDateYear,
		int endDateMonth, int endDateDay, int endDateYear, boolean current,
		java.lang.String data)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _projectsEntryLocalService.addProjectsEntry(userId, title,
			description, startDateMonth, startDateDay, startDateYear,
			endDateMonth, endDateDay, endDateYear, current, data);
	}

	public java.util.List<com.liferay.so.model.ProjectsEntry> getUserProjectsEntries(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return _projectsEntryLocalService.getUserProjectsEntries(userId);
	}

	public int getUserProjectsEntriesCount(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _projectsEntryLocalService.getUserProjectsEntriesCount(userId);
	}

	public com.liferay.so.model.ProjectsEntry updateProjectsEntry(
		long projectsEntryId, java.lang.String title,
		java.lang.String description, int startDateMonth, int startDateDay,
		int startDateYear, int endDateMonth, int endDateDay, int endDateYear,
		boolean current, java.lang.String data)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _projectsEntryLocalService.updateProjectsEntry(projectsEntryId,
			title, description, startDateMonth, startDateDay, startDateYear,
			endDateMonth, endDateDay, endDateYear, current, data);
	}

	public ProjectsEntryLocalService getWrappedProjectsEntryLocalService() {
		return _projectsEntryLocalService;
	}

	private ProjectsEntryLocalService _projectsEntryLocalService;
}