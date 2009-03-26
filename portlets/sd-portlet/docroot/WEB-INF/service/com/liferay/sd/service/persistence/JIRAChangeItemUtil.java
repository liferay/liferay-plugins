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

package com.liferay.sd.service.persistence;

/**
 * <a href="JIRAChangeItemUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JIRAChangeItemUtil {
	public static com.liferay.sd.model.JIRAChangeItem create(
		long jiraChangeItemId) {
		return getPersistence().create(jiraChangeItemId);
	}

	public static com.liferay.sd.model.JIRAChangeItem remove(
		long jiraChangeItemId)
		throws com.liferay.portal.SystemException,
			com.liferay.sd.NoSuchJIRAChangeItemException {
		return getPersistence().remove(jiraChangeItemId);
	}

	public static com.liferay.sd.model.JIRAChangeItem remove(
		com.liferay.sd.model.JIRAChangeItem jiraChangeItem)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(jiraChangeItem);
	}

	public static com.liferay.sd.model.JIRAChangeItem update(
		com.liferay.sd.model.JIRAChangeItem jiraChangeItem)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(jiraChangeItem);
	}

	public static com.liferay.sd.model.JIRAChangeItem update(
		com.liferay.sd.model.JIRAChangeItem jiraChangeItem, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(jiraChangeItem, merge);
	}

	public static com.liferay.sd.model.JIRAChangeItem updateImpl(
		com.liferay.sd.model.JIRAChangeItem jiraChangeItem, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(jiraChangeItem, merge);
	}

	public static com.liferay.sd.model.JIRAChangeItem findByPrimaryKey(
		long jiraChangeItemId)
		throws com.liferay.portal.SystemException,
			com.liferay.sd.NoSuchJIRAChangeItemException {
		return getPersistence().findByPrimaryKey(jiraChangeItemId);
	}

	public static com.liferay.sd.model.JIRAChangeItem fetchByPrimaryKey(
		long jiraChangeItemId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(jiraChangeItemId);
	}

	public static java.util.List<com.liferay.sd.model.JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId) throws com.liferay.portal.SystemException {
		return getPersistence().findByJiraChangeGroupId(jiraChangeGroupId);
	}

	public static java.util.List<com.liferay.sd.model.JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByJiraChangeGroupId(jiraChangeGroupId, start, end);
	}

	public static java.util.List<com.liferay.sd.model.JIRAChangeItem> findByJiraChangeGroupId(
		long jiraChangeGroupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByJiraChangeGroupId(jiraChangeGroupId, start, end, obc);
	}

	public static com.liferay.sd.model.JIRAChangeItem findByJiraChangeGroupId_First(
		long jiraChangeGroupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.sd.NoSuchJIRAChangeItemException {
		return getPersistence()
				   .findByJiraChangeGroupId_First(jiraChangeGroupId, obc);
	}

	public static com.liferay.sd.model.JIRAChangeItem findByJiraChangeGroupId_Last(
		long jiraChangeGroupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.sd.NoSuchJIRAChangeItemException {
		return getPersistence()
				   .findByJiraChangeGroupId_Last(jiraChangeGroupId, obc);
	}

	public static com.liferay.sd.model.JIRAChangeItem[] findByJiraChangeGroupId_PrevAndNext(
		long jiraChangeItemId, long jiraChangeGroupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.sd.NoSuchJIRAChangeItemException {
		return getPersistence()
				   .findByJiraChangeGroupId_PrevAndNext(jiraChangeItemId,
			jiraChangeGroupId, obc);
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

	public static java.util.List<com.liferay.sd.model.JIRAChangeItem> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.sd.model.JIRAChangeItem> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.sd.model.JIRAChangeItem> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByJiraChangeGroupId(long jiraChangeGroupId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByJiraChangeGroupId(jiraChangeGroupId);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByJiraChangeGroupId(long jiraChangeGroupId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByJiraChangeGroupId(jiraChangeGroupId);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static JIRAChangeItemPersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(JIRAChangeItemPersistence persistence) {
		_persistence = persistence;
	}

	private static JIRAChangeItemPersistence _persistence;
}