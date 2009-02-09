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

package com.liferay.tms.service.persistence;

/**
 * <a href="ProjectMilestoneUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ProjectMilestoneUtil {
	public static com.liferay.tms.model.ProjectMilestone create(
		long projectMilestoneId) {
		return getPersistence().create(projectMilestoneId);
	}

	public static com.liferay.tms.model.ProjectMilestone remove(
		long projectMilestoneId)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchProjectMilestoneException {
		return getPersistence().remove(projectMilestoneId);
	}

	public static com.liferay.tms.model.ProjectMilestone remove(
		com.liferay.tms.model.ProjectMilestone projectMilestone)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(projectMilestone);
	}

	public static com.liferay.tms.model.ProjectMilestone update(
		com.liferay.tms.model.ProjectMilestone projectMilestone)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(projectMilestone);
	}

	public static com.liferay.tms.model.ProjectMilestone update(
		com.liferay.tms.model.ProjectMilestone projectMilestone, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(projectMilestone, merge);
	}

	public static com.liferay.tms.model.ProjectMilestone updateImpl(
		com.liferay.tms.model.ProjectMilestone projectMilestone, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(projectMilestone, merge);
	}

	public static com.liferay.tms.model.ProjectMilestone findByPrimaryKey(
		long projectMilestoneId)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchProjectMilestoneException {
		return getPersistence().findByPrimaryKey(projectMilestoneId);
	}

	public static com.liferay.tms.model.ProjectMilestone fetchByPrimaryKey(
		long projectMilestoneId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(projectMilestoneId);
	}

	public static java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	public static java.util.List<com.liferay.tms.model.ProjectMilestone> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.tms.model.ProjectMilestone> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.tms.model.ProjectMilestone> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static ProjectMilestonePersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(ProjectMilestonePersistence persistence) {
		_persistence = persistence;
	}

	private static ProjectMilestonePersistence _persistence;
}