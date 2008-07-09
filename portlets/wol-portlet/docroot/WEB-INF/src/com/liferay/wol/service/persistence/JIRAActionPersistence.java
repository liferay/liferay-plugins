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
 * <a href="JIRAActionPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface JIRAActionPersistence {
	public com.liferay.wol.model.JIRAAction create(long jiraActionId);

	public com.liferay.wol.model.JIRAAction remove(long jiraActionId)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAActionException;

	public com.liferay.wol.model.JIRAAction remove(
		com.liferay.wol.model.JIRAAction jiraAction)
		throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.JIRAAction update(
		com.liferay.wol.model.JIRAAction jiraAction)
		throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.JIRAAction update(
		com.liferay.wol.model.JIRAAction jiraAction, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.JIRAAction updateImpl(
		com.liferay.wol.model.JIRAAction jiraAction, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.JIRAAction findByPrimaryKey(long jiraActionId)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAActionException;

	public com.liferay.wol.model.JIRAAction fetchByPrimaryKey(long jiraActionId)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.JIRAAction> findByJiraUserId(
		java.lang.String jiraUserId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.JIRAAction> findByJiraUserId(
		java.lang.String jiraUserId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.JIRAAction> findByJiraUserId(
		java.lang.String jiraUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.JIRAAction findByJiraUserId_First(
		java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAActionException;

	public com.liferay.wol.model.JIRAAction findByJiraUserId_Last(
		java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAActionException;

	public com.liferay.wol.model.JIRAAction[] findByJiraUserId_PrevAndNext(
		long jiraActionId, java.lang.String jiraUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAActionException;

	public java.util.List<com.liferay.wol.model.JIRAAction> findByJiraIssueId(
		long jiraIssueId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.JIRAAction> findByJiraIssueId(
		long jiraIssueId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.JIRAAction> findByJiraIssueId(
		long jiraIssueId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.JIRAAction findByJiraIssueId_First(
		long jiraIssueId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAActionException;

	public com.liferay.wol.model.JIRAAction findByJiraIssueId_Last(
		long jiraIssueId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAActionException;

	public com.liferay.wol.model.JIRAAction[] findByJiraIssueId_PrevAndNext(
		long jiraActionId, long jiraIssueId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAActionException;

	public java.util.List<com.liferay.wol.model.JIRAAction> findByType(
		java.lang.String type) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.JIRAAction> findByType(
		java.lang.String type, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.JIRAAction> findByType(
		java.lang.String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.JIRAAction findByType_First(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAActionException;

	public com.liferay.wol.model.JIRAAction findByType_Last(
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAActionException;

	public com.liferay.wol.model.JIRAAction[] findByType_PrevAndNext(
		long jiraActionId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchJIRAActionException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.JIRAAction> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.JIRAAction> findAll(int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.JIRAAction> findAll(int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByJiraUserId(java.lang.String jiraUserId)
		throws com.liferay.portal.SystemException;

	public void removeByJiraIssueId(long jiraIssueId)
		throws com.liferay.portal.SystemException;

	public void removeByType(java.lang.String type)
		throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByJiraUserId(java.lang.String jiraUserId)
		throws com.liferay.portal.SystemException;

	public int countByJiraIssueId(long jiraIssueId)
		throws com.liferay.portal.SystemException;

	public int countByType(java.lang.String type)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;

	public void registerListener(
		com.liferay.portal.model.ModelListener listener);

	public void unregisterListener(
		com.liferay.portal.model.ModelListener listener);
}