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

package com.liferay.portal.workflow.edoras.service.persistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.workflow.edoras.model.WorkflowLog;

import java.util.List;

/**
 * <a href="WorkflowLogUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowLogUtil {
	public static void clearCache() {
		getPersistence().clearCache();
	}

	public static List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	public static List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	public static WorkflowLog remove(WorkflowLog workflowLog)
		throws SystemException {
		return getPersistence().remove(workflowLog);
	}

	public static WorkflowLog update(WorkflowLog workflowLog, boolean merge)
		throws SystemException {
		return getPersistence().update(workflowLog, merge);
	}

	public static void cacheResult(
		com.liferay.portal.workflow.edoras.model.WorkflowLog workflowLog) {
		getPersistence().cacheResult(workflowLog);
	}

	public static void cacheResult(
		java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> workflowLogs) {
		getPersistence().cacheResult(workflowLogs);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowLog create(
		long workflowLogId) {
		return getPersistence().create(workflowLogId);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowLog remove(
		long workflowLogId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException {
		return getPersistence().remove(workflowLogId);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowLog updateImpl(
		com.liferay.portal.workflow.edoras.model.WorkflowLog workflowLog,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(workflowLog, merge);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowLog findByPrimaryKey(
		long workflowLogId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException {
		return getPersistence().findByPrimaryKey(workflowLogId);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowLog fetchByPrimaryKey(
		long workflowLogId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(workflowLogId);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findByWorkflowInstanceId(
		long workflowInstanceId) throws com.liferay.portal.SystemException {
		return getPersistence().findByWorkflowInstanceId(workflowInstanceId);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findByWorkflowInstanceId(
		long workflowInstanceId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByWorkflowInstanceId(workflowInstanceId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findByWorkflowInstanceId(
		long workflowInstanceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByWorkflowInstanceId(workflowInstanceId, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowLog findByWorkflowInstanceId_First(
		long workflowInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException {
		return getPersistence()
				   .findByWorkflowInstanceId_First(workflowInstanceId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowLog findByWorkflowInstanceId_Last(
		long workflowInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException {
		return getPersistence()
				   .findByWorkflowInstanceId_Last(workflowInstanceId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowLog[] findByWorkflowInstanceId_PrevAndNext(
		long workflowLogId, long workflowInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException {
		return getPersistence()
				   .findByWorkflowInstanceId_PrevAndNext(workflowLogId,
			workflowInstanceId, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findByWorkflowTaskId(
		long workflowTaskId) throws com.liferay.portal.SystemException {
		return getPersistence().findByWorkflowTaskId(workflowTaskId);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findByWorkflowTaskId(
		long workflowTaskId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByWorkflowTaskId(workflowTaskId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findByWorkflowTaskId(
		long workflowTaskId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByWorkflowTaskId(workflowTaskId, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowLog findByWorkflowTaskId_First(
		long workflowTaskId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException {
		return getPersistence().findByWorkflowTaskId_First(workflowTaskId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowLog findByWorkflowTaskId_Last(
		long workflowTaskId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException {
		return getPersistence().findByWorkflowTaskId_Last(workflowTaskId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowLog[] findByWorkflowTaskId_PrevAndNext(
		long workflowLogId, long workflowTaskId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException {
		return getPersistence()
				   .findByWorkflowTaskId_PrevAndNext(workflowLogId,
			workflowTaskId, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findByW_T(
		long workflowInstanceId, int logEntityType)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByW_T(workflowInstanceId, logEntityType);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findByW_T(
		long workflowInstanceId, int logEntityType, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByW_T(workflowInstanceId, logEntityType, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findByW_T(
		long workflowInstanceId, int logEntityType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByW_T(workflowInstanceId, logEntityType, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowLog findByW_T_First(
		long workflowInstanceId, int logEntityType,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException {
		return getPersistence()
				   .findByW_T_First(workflowInstanceId, logEntityType, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowLog findByW_T_Last(
		long workflowInstanceId, int logEntityType,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException {
		return getPersistence()
				   .findByW_T_Last(workflowInstanceId, logEntityType, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowLog[] findByW_T_PrevAndNext(
		long workflowLogId, long workflowInstanceId, int logEntityType,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException {
		return getPersistence()
				   .findByW_T_PrevAndNext(workflowLogId, workflowInstanceId,
			logEntityType, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findByW_T_T(
		long workflowInstanceId, int logEntityType, int type)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByW_T_T(workflowInstanceId, logEntityType, type);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findByW_T_T(
		long workflowInstanceId, int logEntityType, int type, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByW_T_T(workflowInstanceId, logEntityType, type, start,
			end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findByW_T_T(
		long workflowInstanceId, int logEntityType, int type, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByW_T_T(workflowInstanceId, logEntityType, type, start,
			end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowLog findByW_T_T_First(
		long workflowInstanceId, int logEntityType, int type,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException {
		return getPersistence()
				   .findByW_T_T_First(workflowInstanceId, logEntityType, type,
			obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowLog findByW_T_T_Last(
		long workflowInstanceId, int logEntityType, int type,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException {
		return getPersistence()
				   .findByW_T_T_Last(workflowInstanceId, logEntityType, type,
			obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowLog[] findByW_T_T_PrevAndNext(
		long workflowLogId, long workflowInstanceId, int logEntityType,
		int type, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException {
		return getPersistence()
				   .findByW_T_T_PrevAndNext(workflowLogId, workflowInstanceId,
			logEntityType, type, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByWorkflowInstanceId(long workflowInstanceId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByWorkflowInstanceId(workflowInstanceId);
	}

	public static void removeByWorkflowTaskId(long workflowTaskId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByWorkflowTaskId(workflowTaskId);
	}

	public static void removeByW_T(long workflowInstanceId, int logEntityType)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByW_T(workflowInstanceId, logEntityType);
	}

	public static void removeByW_T_T(long workflowInstanceId,
		int logEntityType, int type) throws com.liferay.portal.SystemException {
		getPersistence().removeByW_T_T(workflowInstanceId, logEntityType, type);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByWorkflowInstanceId(long workflowInstanceId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByWorkflowInstanceId(workflowInstanceId);
	}

	public static int countByWorkflowTaskId(long workflowTaskId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByWorkflowTaskId(workflowTaskId);
	}

	public static int countByW_T(long workflowInstanceId, int logEntityType)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByW_T(workflowInstanceId, logEntityType);
	}

	public static int countByW_T_T(long workflowInstanceId, int logEntityType,
		int type) throws com.liferay.portal.SystemException {
		return getPersistence()
				   .countByW_T_T(workflowInstanceId, logEntityType, type);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static WorkflowLogPersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(WorkflowLogPersistence persistence) {
		_persistence = persistence;
	}

	private static WorkflowLogPersistence _persistence;
}