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
 * <a href="JIRAChangeGroupUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JIRAChangeGroupUtil {
	public static com.liferay.sd.model.JIRAChangeGroup create(
		long jiraChangeGroupId) {
		return getPersistence().create(jiraChangeGroupId);
	}

	public static com.liferay.sd.model.JIRAChangeGroup remove(
		long jiraChangeGroupId)
		throws com.liferay.portal.SystemException,
			com.liferay.sd.NoSuchJIRAChangeGroupException {
		return getPersistence().remove(jiraChangeGroupId);
	}

	public static com.liferay.sd.model.JIRAChangeGroup remove(
		com.liferay.sd.model.JIRAChangeGroup jiraChangeGroup)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(jiraChangeGroup);
	}

	public static com.liferay.sd.model.JIRAChangeGroup update(
		com.liferay.sd.model.JIRAChangeGroup jiraChangeGroup)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(jiraChangeGroup);
	}

	public static com.liferay.sd.model.JIRAChangeGroup update(
		com.liferay.sd.model.JIRAChangeGroup jiraChangeGroup, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(jiraChangeGroup, merge);
	}

	public static com.liferay.sd.model.JIRAChangeGroup updateImpl(
		com.liferay.sd.model.JIRAChangeGroup jiraChangeGroup, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(jiraChangeGroup, merge);
	}

	public static com.liferay.sd.model.JIRAChangeGroup findByPrimaryKey(
		long jiraChangeGroupId)
		throws com.liferay.portal.SystemException,
			com.liferay.sd.NoSuchJIRAChangeGroupException {
		return getPersistence().findByPrimaryKey(jiraChangeGroupId);
	}

	public static com.liferay.sd.model.JIRAChangeGroup fetchByPrimaryKey(
		long jiraChangeGroupId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(jiraChangeGroupId);
	}

	public static java.util.List<com.liferay.sd.model.JIRAChangeGroup> findByJiraUserId(
		java.lang.String jiraUserId) throws com.liferay.portal.SystemException {
		return getPersistence().findByJiraUserId(jiraUserId);
	}

	public static java.util.List<com.liferay.sd.model.JIRAChangeGroup> findByJiraUserId(
		java.lang.String jiraUserId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByJiraUserId(jiraUserId, start, end);
	}

	public static java.util.List<com.liferay.sd.model.JIRAChangeGroup> findByJiraUserId(
		java.lang.String jiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByJiraUserId(jiraUserId, start, end, obc);
	}

	public static com.liferay.sd.model.JIRAChangeGroup findByJiraUserId_First(
		java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.sd.NoSuchJIRAChangeGroupException {
		return getPersistence().findByJiraUserId_First(jiraUserId, obc);
	}

	public static com.liferay.sd.model.JIRAChangeGroup findByJiraUserId_Last(
		java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.sd.NoSuchJIRAChangeGroupException {
		return getPersistence().findByJiraUserId_Last(jiraUserId, obc);
	}

	public static com.liferay.sd.model.JIRAChangeGroup[] findByJiraUserId_PrevAndNext(
		long jiraChangeGroupId, java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.sd.NoSuchJIRAChangeGroupException {
		return getPersistence()
				   .findByJiraUserId_PrevAndNext(jiraChangeGroupId, jiraUserId,
			obc);
	}

	public static java.util.List<com.liferay.sd.model.JIRAChangeGroup> findByJiraIssueId(
		long jiraIssueId) throws com.liferay.portal.SystemException {
		return getPersistence().findByJiraIssueId(jiraIssueId);
	}

	public static java.util.List<com.liferay.sd.model.JIRAChangeGroup> findByJiraIssueId(
		long jiraIssueId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByJiraIssueId(jiraIssueId, start, end);
	}

	public static java.util.List<com.liferay.sd.model.JIRAChangeGroup> findByJiraIssueId(
		long jiraIssueId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByJiraIssueId(jiraIssueId, start, end, obc);
	}

	public static com.liferay.sd.model.JIRAChangeGroup findByJiraIssueId_First(
		long jiraIssueId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.sd.NoSuchJIRAChangeGroupException {
		return getPersistence().findByJiraIssueId_First(jiraIssueId, obc);
	}

	public static com.liferay.sd.model.JIRAChangeGroup findByJiraIssueId_Last(
		long jiraIssueId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.sd.NoSuchJIRAChangeGroupException {
		return getPersistence().findByJiraIssueId_Last(jiraIssueId, obc);
	}

	public static com.liferay.sd.model.JIRAChangeGroup[] findByJiraIssueId_PrevAndNext(
		long jiraChangeGroupId, long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.sd.NoSuchJIRAChangeGroupException {
		return getPersistence()
				   .findByJiraIssueId_PrevAndNext(jiraChangeGroupId,
			jiraIssueId, obc);
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

	public static java.util.List<com.liferay.sd.model.JIRAChangeGroup> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.sd.model.JIRAChangeGroup> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.sd.model.JIRAChangeGroup> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByJiraUserId(java.lang.String jiraUserId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByJiraUserId(jiraUserId);
	}

	public static void removeByJiraIssueId(long jiraIssueId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByJiraIssueId(jiraIssueId);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByJiraUserId(java.lang.String jiraUserId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByJiraUserId(jiraUserId);
	}

	public static int countByJiraIssueId(long jiraIssueId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByJiraIssueId(jiraIssueId);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static JIRAChangeGroupPersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(JIRAChangeGroupPersistence persistence) {
		_persistence = persistence;
	}

	private static JIRAChangeGroupPersistence _persistence;
}