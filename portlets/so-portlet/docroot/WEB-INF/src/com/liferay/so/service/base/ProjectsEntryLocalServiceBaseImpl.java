/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.so.service.base;

import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import com.liferay.so.model.ProjectsEntry;
import com.liferay.so.service.MemberRequestLocalService;
import com.liferay.so.service.ProjectsEntryLocalService;
import com.liferay.so.service.persistence.MemberRequestPersistence;
import com.liferay.so.service.persistence.ProjectsEntryPersistence;

import java.util.List;

/**
 * <a href="ProjectsEntryLocalServiceBaseImpl.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
 */
public abstract class ProjectsEntryLocalServiceBaseImpl
	implements ProjectsEntryLocalService {
	public ProjectsEntry addProjectsEntry(ProjectsEntry projectsEntry)
		throws SystemException {
		projectsEntry.setNew(true);

		return projectsEntryPersistence.update(projectsEntry, false);
	}

	public ProjectsEntry createProjectsEntry(long projectsEntryId) {
		return projectsEntryPersistence.create(projectsEntryId);
	}

	public void deleteProjectsEntry(long projectsEntryId)
		throws PortalException, SystemException {
		projectsEntryPersistence.remove(projectsEntryId);
	}

	public void deleteProjectsEntry(ProjectsEntry projectsEntry)
		throws SystemException {
		projectsEntryPersistence.remove(projectsEntry);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return projectsEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return projectsEntryPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	public ProjectsEntry getProjectsEntry(long projectsEntryId)
		throws PortalException, SystemException {
		return projectsEntryPersistence.findByPrimaryKey(projectsEntryId);
	}

	public List<ProjectsEntry> getProjectsEntries(int start, int end)
		throws SystemException {
		return projectsEntryPersistence.findAll(start, end);
	}

	public int getProjectsEntriesCount() throws SystemException {
		return projectsEntryPersistence.countAll();
	}

	public ProjectsEntry updateProjectsEntry(ProjectsEntry projectsEntry)
		throws SystemException {
		projectsEntry.setNew(false);

		return projectsEntryPersistence.update(projectsEntry, true);
	}

	public ProjectsEntry updateProjectsEntry(ProjectsEntry projectsEntry,
		boolean merge) throws SystemException {
		projectsEntry.setNew(false);

		return projectsEntryPersistence.update(projectsEntry, merge);
	}

	public MemberRequestLocalService getMemberRequestLocalService() {
		return memberRequestLocalService;
	}

	public void setMemberRequestLocalService(
		MemberRequestLocalService memberRequestLocalService) {
		this.memberRequestLocalService = memberRequestLocalService;
	}

	public MemberRequestPersistence getMemberRequestPersistence() {
		return memberRequestPersistence;
	}

	public void setMemberRequestPersistence(
		MemberRequestPersistence memberRequestPersistence) {
		this.memberRequestPersistence = memberRequestPersistence;
	}

	public ProjectsEntryLocalService getProjectsEntryLocalService() {
		return projectsEntryLocalService;
	}

	public void setProjectsEntryLocalService(
		ProjectsEntryLocalService projectsEntryLocalService) {
		this.projectsEntryLocalService = projectsEntryLocalService;
	}

	public ProjectsEntryPersistence getProjectsEntryPersistence() {
		return projectsEntryPersistence;
	}

	public void setProjectsEntryPersistence(
		ProjectsEntryPersistence projectsEntryPersistence) {
		this.projectsEntryPersistence = projectsEntryPersistence;
	}

	protected void runSQL(String sql) throws SystemException {
		try {
			DB db = DBFactoryUtil.getDB();

			db.runSQL(sql);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(name = "com.liferay.so.service.MemberRequestLocalService")
	protected MemberRequestLocalService memberRequestLocalService;
	@BeanReference(name = "com.liferay.so.service.persistence.MemberRequestPersistence")
	protected MemberRequestPersistence memberRequestPersistence;
	@BeanReference(name = "com.liferay.so.service.ProjectsEntryLocalService")
	protected ProjectsEntryLocalService projectsEntryLocalService;
	@BeanReference(name = "com.liferay.so.service.persistence.ProjectsEntryPersistence")
	protected ProjectsEntryPersistence projectsEntryPersistence;
}