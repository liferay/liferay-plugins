/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.wol.service.persistence;

/**
 * <a href="JIRAActionUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JIRAActionUtil {
	public static com.liferay.wol.model.JIRAAction create(long jiraActionId) {
		return getPersistence().create(jiraActionId);
	}

	public static com.liferay.wol.model.JIRAAction remove(long jiraActionId)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAActionException {
		return getPersistence().remove(jiraActionId);
	}

	public static com.liferay.wol.model.JIRAAction remove(
		com.liferay.wol.model.JIRAAction jiraAction)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(jiraAction);
	}

	public static com.liferay.wol.model.JIRAAction update(
		com.liferay.wol.model.JIRAAction jiraAction)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(jiraAction);
	}

	public static com.liferay.wol.model.JIRAAction update(
		com.liferay.wol.model.JIRAAction jiraAction, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(jiraAction, merge);
	}

	public static com.liferay.wol.model.JIRAAction updateImpl(
		com.liferay.wol.model.JIRAAction jiraAction, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(jiraAction, merge);
	}

	public static com.liferay.wol.model.JIRAAction findByPrimaryKey(
		long jiraActionId)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAActionException {
		return getPersistence().findByPrimaryKey(jiraActionId);
	}

	public static com.liferay.wol.model.JIRAAction fetchByPrimaryKey(
		long jiraActionId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(jiraActionId);
	}

	public static java.util.List<com.liferay.wol.model.JIRAAction> findByJiraUserId(
		java.lang.String jiraUserId) throws com.liferay.portal.SystemException {
		return getPersistence().findByJiraUserId(jiraUserId);
	}

	public static java.util.List<com.liferay.wol.model.JIRAAction> findByJiraUserId(
		java.lang.String jiraUserId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByJiraUserId(jiraUserId, start, end);
	}

	public static java.util.List<com.liferay.wol.model.JIRAAction> findByJiraUserId(
		java.lang.String jiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByJiraUserId(jiraUserId, start, end, obc);
	}

	public static com.liferay.wol.model.JIRAAction findByJiraUserId_First(
		java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAActionException {
		return getPersistence().findByJiraUserId_First(jiraUserId, obc);
	}

	public static com.liferay.wol.model.JIRAAction findByJiraUserId_Last(
		java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAActionException {
		return getPersistence().findByJiraUserId_Last(jiraUserId, obc);
	}

	public static com.liferay.wol.model.JIRAAction[] findByJiraUserId_PrevAndNext(
		long jiraActionId, java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAActionException {
		return getPersistence()
				   .findByJiraUserId_PrevAndNext(jiraActionId, jiraUserId, obc);
	}

	public static java.util.List<com.liferay.wol.model.JIRAAction> findByJiraIssueId(
		long jiraIssueId) throws com.liferay.portal.SystemException {
		return getPersistence().findByJiraIssueId(jiraIssueId);
	}

	public static java.util.List<com.liferay.wol.model.JIRAAction> findByJiraIssueId(
		long jiraIssueId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByJiraIssueId(jiraIssueId, start, end);
	}

	public static java.util.List<com.liferay.wol.model.JIRAAction> findByJiraIssueId(
		long jiraIssueId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByJiraIssueId(jiraIssueId, start, end, obc);
	}

	public static com.liferay.wol.model.JIRAAction findByJiraIssueId_First(
		long jiraIssueId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAActionException {
		return getPersistence().findByJiraIssueId_First(jiraIssueId, obc);
	}

	public static com.liferay.wol.model.JIRAAction findByJiraIssueId_Last(
		long jiraIssueId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAActionException {
		return getPersistence().findByJiraIssueId_Last(jiraIssueId, obc);
	}

	public static com.liferay.wol.model.JIRAAction[] findByJiraIssueId_PrevAndNext(
		long jiraActionId, long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAActionException {
		return getPersistence()
				   .findByJiraIssueId_PrevAndNext(jiraActionId, jiraIssueId, obc);
	}

	public static java.util.List<com.liferay.wol.model.JIRAAction> findByType(
		java.lang.String type) throws com.liferay.portal.SystemException {
		return getPersistence().findByType(type);
	}

	public static java.util.List<com.liferay.wol.model.JIRAAction> findByType(
		java.lang.String type, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByType(type, start, end);
	}

	public static java.util.List<com.liferay.wol.model.JIRAAction> findByType(
		java.lang.String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByType(type, start, end, obc);
	}

	public static com.liferay.wol.model.JIRAAction findByType_First(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAActionException {
		return getPersistence().findByType_First(type, obc);
	}

	public static com.liferay.wol.model.JIRAAction findByType_Last(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAActionException {
		return getPersistence().findByType_Last(type, obc);
	}

	public static com.liferay.wol.model.JIRAAction[] findByType_PrevAndNext(
		long jiraActionId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAActionException {
		return getPersistence().findByType_PrevAndNext(jiraActionId, type, obc);
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

	public static java.util.List<com.liferay.wol.model.JIRAAction> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.wol.model.JIRAAction> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.wol.model.JIRAAction> findAll(
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

	public static void removeByType(java.lang.String type)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByType(type);
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

	public static int countByType(java.lang.String type)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByType(type);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static void registerListener(
		com.liferay.portal.model.ModelListener listener) {
		getPersistence().registerListener(listener);
	}

	public static void unregisterListener(
		com.liferay.portal.model.ModelListener listener) {
		getPersistence().unregisterListener(listener);
	}

	public static JIRAActionPersistence getPersistence() {
		return _getUtil()._persistence;
	}

	public void setPersistence(JIRAActionPersistence persistence) {
		_persistence = persistence;
	}

	private static JIRAActionUtil _getUtil() {
		if (_util == null) {
			_util = (JIRAActionUtil)com.liferay.util.bean.PortletBeanLocatorUtil.locate(_UTIL);
		}

		return _util;
	}

	private static final String _UTIL = JIRAActionUtil.class.getName();
	private static JIRAActionUtil _util;
	private JIRAActionPersistence _persistence;
}