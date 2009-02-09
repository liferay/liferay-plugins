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
 * <a href="TaskViewUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class TaskViewUtil {
	public static com.liferay.tms.model.TaskView create(long taskViewId) {
		return getPersistence().create(taskViewId);
	}

	public static com.liferay.tms.model.TaskView remove(long taskViewId)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTaskViewException {
		return getPersistence().remove(taskViewId);
	}

	public static com.liferay.tms.model.TaskView remove(
		com.liferay.tms.model.TaskView taskView)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(taskView);
	}

	public static com.liferay.tms.model.TaskView update(
		com.liferay.tms.model.TaskView taskView)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(taskView);
	}

	public static com.liferay.tms.model.TaskView update(
		com.liferay.tms.model.TaskView taskView, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(taskView, merge);
	}

	public static com.liferay.tms.model.TaskView updateImpl(
		com.liferay.tms.model.TaskView taskView, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(taskView, merge);
	}

	public static com.liferay.tms.model.TaskView findByPrimaryKey(
		long taskViewId)
		throws com.liferay.portal.SystemException,
			com.liferay.tms.NoSuchTaskViewException {
		return getPersistence().findByPrimaryKey(taskViewId);
	}

	public static com.liferay.tms.model.TaskView fetchByPrimaryKey(
		long taskViewId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(taskViewId);
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

	public static java.util.List<com.liferay.tms.model.TaskView> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.tms.model.TaskView> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.tms.model.TaskView> findAll(
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

	public static TaskViewPersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(TaskViewPersistence persistence) {
		_persistence = persistence;
	}

	private static TaskViewPersistence _persistence;
}