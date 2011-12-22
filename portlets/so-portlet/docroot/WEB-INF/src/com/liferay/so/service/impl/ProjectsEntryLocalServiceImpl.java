/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.so.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import com.liferay.so.ProjectsEntryEndDateException;
import com.liferay.so.ProjectsEntryStartDateException;
import com.liferay.so.model.ProjectsEntry;
import com.liferay.so.service.base.ProjectsEntryLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * @author Ryan Park
 */
public class ProjectsEntryLocalServiceImpl
	extends ProjectsEntryLocalServiceBaseImpl {

	public ProjectsEntry addProjectsEntry(
			long userId, String title, String description, int startDateMonth,
			int startDateDay, int startDateYear, int endDateMonth,
			int endDateDay, int endDateYear, boolean current, String data)
		throws PortalException, SystemException {

		User user = userLocalService.getUserById(userId);

		Date startDate = PortalUtil.getDate(
			startDateMonth, startDateDay, startDateYear, user.getTimeZone(),
			new ProjectsEntryStartDateException());

		Date endDate = null;

		if (!current) {
			endDate = PortalUtil.getDate(
				endDateMonth, endDateDay, endDateYear, user.getTimeZone(),
				new ProjectsEntryEndDateException());
		}

		Date now = new Date();

		long projectsEntryId = counterLocalService.increment();

		ProjectsEntry projectsEntry = projectsEntryPersistence.create(
			projectsEntryId);

		projectsEntry.setCompanyId(user.getCompanyId());
		projectsEntry.setUserId(user.getUserId());
		projectsEntry.setUserName(user.getFullName());
		projectsEntry.setCreateDate(now);
		projectsEntry.setModifiedDate(now);
		projectsEntry.setTitle(title);
		projectsEntry.setDescription(description);
		projectsEntry.setStartDate(startDate);
		projectsEntry.setEndDate(endDate);
		projectsEntry.setData(data);

		projectsEntryPersistence.update(projectsEntry, false);

		return projectsEntry;
	}

	public List<ProjectsEntry> getUserProjectsEntries(long userId)
		throws SystemException {

		return projectsEntryPersistence.findByUserId(userId);
	}

	public int getUserProjectsEntriesCount(long userId) throws SystemException {
		return projectsEntryPersistence.countByUserId(userId);
	}

	public ProjectsEntry updateProjectsEntry(
			long projectsEntryId, String title, String description,
			int startDateMonth, int startDateDay, int startDateYear,
			int endDateMonth, int endDateDay, int endDateYear, boolean current,
			String data)
		throws PortalException, SystemException {

		ProjectsEntry projectsEntry = projectsEntryPersistence.findByPrimaryKey(
			projectsEntryId);

		User user = userLocalService.getUserById(projectsEntry.getUserId());

		Date startDate = PortalUtil.getDate(
			startDateMonth, startDateDay, startDateYear, user.getTimeZone(),
			new ProjectsEntryStartDateException());

		Date endDate = null;

		if (!current) {
			endDate = PortalUtil.getDate(
				endDateMonth, endDateDay, endDateYear, user.getTimeZone(),
				new ProjectsEntryEndDateException());
		}

		projectsEntry.setModifiedDate(new Date());
		projectsEntry.setTitle(title);
		projectsEntry.setDescription(description);
		projectsEntry.setStartDate(startDate);
		projectsEntry.setEndDate(endDate);
		projectsEntry.setData(data);

		projectsEntryPersistence.update(projectsEntry, false);

		return projectsEntry;
	}

}