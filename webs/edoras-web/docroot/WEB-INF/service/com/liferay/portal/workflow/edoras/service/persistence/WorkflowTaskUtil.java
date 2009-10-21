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
import com.liferay.portal.workflow.edoras.model.WorkflowTask;

import java.util.List;

/**
 * <a href="WorkflowTaskUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowTaskUtil {
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

	public static WorkflowTask remove(WorkflowTask workflowTask)
		throws SystemException {
		return getPersistence().remove(workflowTask);
	}

	public static WorkflowTask update(WorkflowTask workflowTask, boolean merge)
		throws SystemException {
		return getPersistence().update(workflowTask, merge);
	}

	public static void cacheResult(
		com.liferay.portal.workflow.edoras.model.WorkflowTask workflowTask) {
		getPersistence().cacheResult(workflowTask);
	}

	public static void cacheResult(
		java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> workflowTasks) {
		getPersistence().cacheResult(workflowTasks);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask create(
		long workflowTaskId) {
		return getPersistence().create(workflowTaskId);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask remove(
		long workflowTaskId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence().remove(workflowTaskId);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask updateImpl(
		com.liferay.portal.workflow.edoras.model.WorkflowTask workflowTask,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(workflowTask, merge);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask findByPrimaryKey(
		long workflowTaskId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence().findByPrimaryKey(workflowTaskId);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask fetchByPrimaryKey(
		long workflowTaskId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(workflowTaskId);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByWorkflowInstanceId(
		long workflowInstanceId) throws com.liferay.portal.SystemException {
		return getPersistence().findByWorkflowInstanceId(workflowInstanceId);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByWorkflowInstanceId(
		long workflowInstanceId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByWorkflowInstanceId(workflowInstanceId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByWorkflowInstanceId(
		long workflowInstanceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByWorkflowInstanceId(workflowInstanceId, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask findByWorkflowInstanceId_First(
		long workflowInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence()
				   .findByWorkflowInstanceId_First(workflowInstanceId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask findByWorkflowInstanceId_Last(
		long workflowInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence()
				   .findByWorkflowInstanceId_Last(workflowInstanceId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask[] findByWorkflowInstanceId_PrevAndNext(
		long workflowTaskId, long workflowInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence()
				   .findByWorkflowInstanceId_PrevAndNext(workflowTaskId,
			workflowInstanceId, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByCompleted(
		boolean completed) throws com.liferay.portal.SystemException {
		return getPersistence().findByCompleted(completed);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByCompleted(
		boolean completed, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCompleted(completed, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByCompleted(
		boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCompleted(completed, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask findByCompleted_First(
		boolean completed, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence().findByCompleted_First(completed, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask findByCompleted_Last(
		boolean completed, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence().findByCompleted_Last(completed, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask[] findByCompleted_PrevAndNext(
		long workflowTaskId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence()
				   .findByCompleted_PrevAndNext(workflowTaskId, completed, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByAssigneeUserId(
		long assigneeUserId) throws com.liferay.portal.SystemException {
		return getPersistence().findByAssigneeUserId(assigneeUserId);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByAssigneeUserId(
		long assigneeUserId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByAssigneeUserId(assigneeUserId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByAssigneeUserId(
		long assigneeUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByAssigneeUserId(assigneeUserId, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask findByAssigneeUserId_First(
		long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence().findByAssigneeUserId_First(assigneeUserId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask findByAssigneeUserId_Last(
		long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence().findByAssigneeUserId_Last(assigneeUserId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask[] findByAssigneeUserId_PrevAndNext(
		long workflowTaskId, long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence()
				   .findByAssigneeUserId_PrevAndNext(workflowTaskId,
			assigneeUserId, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByAssigneeRoleId(
		long assigneeRoleId) throws com.liferay.portal.SystemException {
		return getPersistence().findByAssigneeRoleId(assigneeRoleId);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByAssigneeRoleId(
		long assigneeRoleId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByAssigneeRoleId(assigneeRoleId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByAssigneeRoleId(
		long assigneeRoleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByAssigneeRoleId(assigneeRoleId, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask findByAssigneeRoleId_First(
		long assigneeRoleId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence().findByAssigneeRoleId_First(assigneeRoleId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask findByAssigneeRoleId_Last(
		long assigneeRoleId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence().findByAssigneeRoleId_Last(assigneeRoleId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask[] findByAssigneeRoleId_PrevAndNext(
		long workflowTaskId, long assigneeRoleId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence()
				   .findByAssigneeRoleId_PrevAndNext(workflowTaskId,
			assigneeRoleId, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByC_C(
		long companyId, boolean completed)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByC_C(companyId, completed);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByC_C(
		long companyId, boolean completed, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByC_C(companyId, completed, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByC_C(
		long companyId, boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByC_C(companyId, completed, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask findByC_C_First(
		long companyId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence().findByC_C_First(companyId, completed, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask findByC_C_Last(
		long companyId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence().findByC_C_Last(companyId, completed, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask[] findByC_C_PrevAndNext(
		long workflowTaskId, long companyId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence()
				   .findByC_C_PrevAndNext(workflowTaskId, companyId, completed,
			obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByW_C(
		long workflowInstanceId, boolean completed)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByW_C(workflowInstanceId, completed);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByW_C(
		long workflowInstanceId, boolean completed, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByW_C(workflowInstanceId, completed, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByW_C(
		long workflowInstanceId, boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByW_C(workflowInstanceId, completed, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask findByW_C_First(
		long workflowInstanceId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence()
				   .findByW_C_First(workflowInstanceId, completed, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask findByW_C_Last(
		long workflowInstanceId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence()
				   .findByW_C_Last(workflowInstanceId, completed, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask[] findByW_C_PrevAndNext(
		long workflowTaskId, long workflowInstanceId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence()
				   .findByW_C_PrevAndNext(workflowTaskId, workflowInstanceId,
			completed, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByW_S(
		long workflowInstanceId, int state)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByW_S(workflowInstanceId, state);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByW_S(
		long workflowInstanceId, int state, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByW_S(workflowInstanceId, state, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByW_S(
		long workflowInstanceId, int state, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByW_S(workflowInstanceId, state, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask findByW_S_First(
		long workflowInstanceId, int state,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence().findByW_S_First(workflowInstanceId, state, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask findByW_S_Last(
		long workflowInstanceId, int state,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence().findByW_S_Last(workflowInstanceId, state, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask[] findByW_S_PrevAndNext(
		long workflowTaskId, long workflowInstanceId, int state,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence()
				   .findByW_S_PrevAndNext(workflowTaskId, workflowInstanceId,
			state, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByAUID_C(
		long assigneeUserId, boolean completed)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByAUID_C(assigneeUserId, completed);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByAUID_C(
		long assigneeUserId, boolean completed, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByAUID_C(assigneeUserId, completed, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByAUID_C(
		long assigneeUserId, boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByAUID_C(assigneeUserId, completed, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask findByAUID_C_First(
		long assigneeUserId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence()
				   .findByAUID_C_First(assigneeUserId, completed, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask findByAUID_C_Last(
		long assigneeUserId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence().findByAUID_C_Last(assigneeUserId, completed, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask[] findByAUID_C_PrevAndNext(
		long workflowTaskId, long assigneeUserId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence()
				   .findByAUID_C_PrevAndNext(workflowTaskId, assigneeUserId,
			completed, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByARID_C(
		long assigneeRoleId, boolean completed)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByARID_C(assigneeRoleId, completed);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByARID_C(
		long assigneeRoleId, boolean completed, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByARID_C(assigneeRoleId, completed, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByARID_C(
		long assigneeRoleId, boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByARID_C(assigneeRoleId, completed, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask findByARID_C_First(
		long assigneeRoleId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence()
				   .findByARID_C_First(assigneeRoleId, completed, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask findByARID_C_Last(
		long assigneeRoleId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence().findByARID_C_Last(assigneeRoleId, completed, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask[] findByARID_C_PrevAndNext(
		long workflowTaskId, long assigneeRoleId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence()
				   .findByARID_C_PrevAndNext(workflowTaskId, assigneeRoleId,
			completed, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByWorkflowInstanceId(long workflowInstanceId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByWorkflowInstanceId(workflowInstanceId);
	}

	public static void removeByCompleted(boolean completed)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByCompleted(completed);
	}

	public static void removeByAssigneeUserId(long assigneeUserId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByAssigneeUserId(assigneeUserId);
	}

	public static void removeByAssigneeRoleId(long assigneeRoleId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByAssigneeRoleId(assigneeRoleId);
	}

	public static void removeByC_C(long companyId, boolean completed)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByC_C(companyId, completed);
	}

	public static void removeByW_C(long workflowInstanceId, boolean completed)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByW_C(workflowInstanceId, completed);
	}

	public static void removeByW_S(long workflowInstanceId, int state)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByW_S(workflowInstanceId, state);
	}

	public static void removeByAUID_C(long assigneeUserId, boolean completed)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByAUID_C(assigneeUserId, completed);
	}

	public static void removeByARID_C(long assigneeRoleId, boolean completed)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByARID_C(assigneeRoleId, completed);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByWorkflowInstanceId(long workflowInstanceId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByWorkflowInstanceId(workflowInstanceId);
	}

	public static int countByCompleted(boolean completed)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByCompleted(completed);
	}

	public static int countByAssigneeUserId(long assigneeUserId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByAssigneeUserId(assigneeUserId);
	}

	public static int countByAssigneeRoleId(long assigneeRoleId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByAssigneeRoleId(assigneeRoleId);
	}

	public static int countByC_C(long companyId, boolean completed)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByC_C(companyId, completed);
	}

	public static int countByW_C(long workflowInstanceId, boolean completed)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByW_C(workflowInstanceId, completed);
	}

	public static int countByW_S(long workflowInstanceId, int state)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByW_S(workflowInstanceId, state);
	}

	public static int countByAUID_C(long assigneeUserId, boolean completed)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByAUID_C(assigneeUserId, completed);
	}

	public static int countByARID_C(long assigneeRoleId, boolean completed)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByARID_C(assigneeRoleId, completed);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static WorkflowTaskPersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(WorkflowTaskPersistence persistence) {
		_persistence = persistence;
	}

	private static WorkflowTaskPersistence _persistence;
}