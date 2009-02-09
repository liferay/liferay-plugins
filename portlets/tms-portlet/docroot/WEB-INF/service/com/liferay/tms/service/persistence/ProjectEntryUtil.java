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
 * <a href="ProjectEntryUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ProjectEntryUtil {
	public static com.liferay.tms.model.ProjectEntry create(long projectEntryId) {
		return getPersistence().create(projectEntryId);
	}

	public static com.liferay.tms.model.ProjectEntry remove(long projectEntryId)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchProjectEntryException {
		return getPersistence().remove(projectEntryId);
	}

	public static com.liferay.tms.model.ProjectEntry remove(
		com.liferay.tms.model.ProjectEntry projectEntry)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(projectEntry);
	}

	public static com.liferay.tms.model.ProjectEntry update(
		com.liferay.tms.model.ProjectEntry projectEntry)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(projectEntry);
	}

	public static com.liferay.tms.model.ProjectEntry update(
		com.liferay.tms.model.ProjectEntry projectEntry, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(projectEntry, merge);
	}

	public static com.liferay.tms.model.ProjectEntry updateImpl(
		com.liferay.tms.model.ProjectEntry projectEntry, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(projectEntry, merge);
	}

	public static com.liferay.tms.model.ProjectEntry findByPrimaryKey(
		long projectEntryId)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchProjectEntryException {
		return getPersistence().findByPrimaryKey(projectEntryId);
	}

	public static com.liferay.tms.model.ProjectEntry fetchByPrimaryKey(
		long projectEntryId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(projectEntryId);
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

	public static java.util.List<com.liferay.tms.model.ProjectEntry> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.tms.model.ProjectEntry> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.tms.model.ProjectEntry> findAll(
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

	public static ProjectEntryPersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(ProjectEntryPersistence persistence) {
		_persistence = persistence;
	}

	private static ProjectEntryPersistence _persistence;
}