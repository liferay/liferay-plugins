/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.so.service;

/**
 * <a href="ProjectsEntryLocalServiceWrapper.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class ProjectsEntryLocalServiceWrapper
	implements ProjectsEntryLocalService {
	public ProjectsEntryLocalServiceWrapper(
		ProjectsEntryLocalService projectsEntryLocalService) {
		_projectsEntryLocalService = projectsEntryLocalService;
	}

	public com.liferay.so.model.ProjectsEntry addProjectsEntry(
		com.liferay.so.model.ProjectsEntry projectsEntry)
		throws com.liferay.portal.SystemException {
		return _projectsEntryLocalService.addProjectsEntry(projectsEntry);
	}

	public com.liferay.so.model.ProjectsEntry createProjectsEntry(
		long projectsEntryId) {
		return _projectsEntryLocalService.createProjectsEntry(projectsEntryId);
	}

	public void deleteProjectsEntry(long projectsEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_projectsEntryLocalService.deleteProjectsEntry(projectsEntryId);
	}

	public void deleteProjectsEntry(
		com.liferay.so.model.ProjectsEntry projectsEntry)
		throws com.liferay.portal.SystemException {
		_projectsEntryLocalService.deleteProjectsEntry(projectsEntry);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return _projectsEntryLocalService.dynamicQuery(dynamicQuery);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return _projectsEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	public com.liferay.so.model.ProjectsEntry getProjectsEntry(
		long projectsEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _projectsEntryLocalService.getProjectsEntry(projectsEntryId);
	}

	public java.util.List<com.liferay.so.model.ProjectsEntry> getProjectsEntries(
		int start, int end) throws com.liferay.portal.SystemException {
		return _projectsEntryLocalService.getProjectsEntries(start, end);
	}

	public int getProjectsEntriesCount()
		throws com.liferay.portal.SystemException {
		return _projectsEntryLocalService.getProjectsEntriesCount();
	}

	public com.liferay.so.model.ProjectsEntry updateProjectsEntry(
		com.liferay.so.model.ProjectsEntry projectsEntry)
		throws com.liferay.portal.SystemException {
		return _projectsEntryLocalService.updateProjectsEntry(projectsEntry);
	}

	public com.liferay.so.model.ProjectsEntry updateProjectsEntry(
		com.liferay.so.model.ProjectsEntry projectsEntry, boolean merge)
		throws com.liferay.portal.SystemException {
		return _projectsEntryLocalService.updateProjectsEntry(projectsEntry,
			merge);
	}

	public com.liferay.so.model.ProjectsEntry addProjectsEntry(long userId,
		java.lang.String title, java.lang.String description,
		int startDateMonth, int startDateDay, int startDateYear,
		int endDateMonth, int endDateDay, int endDateYear, boolean current,
		java.lang.String data)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _projectsEntryLocalService.addProjectsEntry(userId, title,
			description, startDateMonth, startDateDay, startDateYear,
			endDateMonth, endDateDay, endDateYear, current, data);
	}

	public java.util.List<com.liferay.so.model.ProjectsEntry> getUserProjectsEntries(
		long userId) throws com.liferay.portal.SystemException {
		return _projectsEntryLocalService.getUserProjectsEntries(userId);
	}

	public int getUserProjectsEntriesCount(long userId)
		throws com.liferay.portal.SystemException {
		return _projectsEntryLocalService.getUserProjectsEntriesCount(userId);
	}

	public com.liferay.so.model.ProjectsEntry updateProjectsEntry(
		long projectsEntryId, java.lang.String title,
		java.lang.String description, int startDateMonth, int startDateDay,
		int startDateYear, int endDateMonth, int endDateDay, int endDateYear,
		boolean current, java.lang.String data)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _projectsEntryLocalService.updateProjectsEntry(projectsEntryId,
			title, description, startDateMonth, startDateDay, startDateYear,
			endDateMonth, endDateDay, endDateYear, current, data);
	}

	private ProjectsEntryLocalService _projectsEntryLocalService;
}