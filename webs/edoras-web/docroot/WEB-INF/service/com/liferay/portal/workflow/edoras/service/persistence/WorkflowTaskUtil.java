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

/**
 * <a href="WorkflowTaskUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowTaskUtil {
	public static void cacheResult(
		com.liferay.portal.workflow.edoras.model.WorkflowTask workflowTask) {
		getPersistence().cacheResult(workflowTask);
	}

	public static void cacheResult(
		java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> workflowTasks) {
		getPersistence().cacheResult(workflowTasks);
	}

	public static void clearCache() {
		getPersistence().clearCache();
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

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask remove(
		com.liferay.portal.workflow.edoras.model.WorkflowTask workflowTask)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(workflowTask);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask update(
		com.liferay.portal.workflow.edoras.model.WorkflowTask workflowTask)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(workflowTask);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask update(
		com.liferay.portal.workflow.edoras.model.WorkflowTask workflowTask,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().update(workflowTask, merge);
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

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByUserId(
		long assigneeUserId) throws com.liferay.portal.SystemException {
		return getPersistence().findByUserId(assigneeUserId);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByUserId(
		long assigneeUserId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByUserId(assigneeUserId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByUserId(
		long assigneeUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByUserId(assigneeUserId, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask findByUserId_First(
		long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence().findByUserId_First(assigneeUserId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask findByUserId_Last(
		long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence().findByUserId_Last(assigneeUserId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask[] findByUserId_PrevAndNext(
		long workflowTaskId, long assigneeUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence()
				   .findByUserId_PrevAndNext(workflowTaskId, assigneeUserId, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByRoleId(
		long roleId) throws com.liferay.portal.SystemException {
		return getPersistence().findByRoleId(roleId);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByRoleId(
		long roleId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByRoleId(roleId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByRoleId(
		long roleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByRoleId(roleId, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask findByRoleId_First(
		long roleId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence().findByRoleId_First(roleId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask findByRoleId_Last(
		long roleId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence().findByRoleId_Last(roleId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask[] findByRoleId_PrevAndNext(
		long workflowTaskId, long roleId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence()
				   .findByRoleId_PrevAndNext(workflowTaskId, roleId, obc);
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

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByU_C(
		long assigneeUserId, boolean completed)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByU_C(assigneeUserId, completed);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByU_C(
		long assigneeUserId, boolean completed, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByU_C(assigneeUserId, completed, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByU_C(
		long assigneeUserId, boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByU_C(assigneeUserId, completed, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask findByU_C_First(
		long assigneeUserId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence().findByU_C_First(assigneeUserId, completed, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask findByU_C_Last(
		long assigneeUserId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence().findByU_C_Last(assigneeUserId, completed, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask[] findByU_C_PrevAndNext(
		long workflowTaskId, long assigneeUserId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence()
				   .findByU_C_PrevAndNext(workflowTaskId, assigneeUserId,
			completed, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByR_C(
		long roleId, boolean completed)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByR_C(roleId, completed);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByR_C(
		long roleId, boolean completed, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByR_C(roleId, completed, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowTask> findByR_C(
		long roleId, boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByR_C(roleId, completed, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask findByR_C_First(
		long roleId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence().findByR_C_First(roleId, completed, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask findByR_C_Last(
		long roleId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence().findByR_C_Last(roleId, completed, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowTask[] findByR_C_PrevAndNext(
		long workflowTaskId, long roleId, boolean completed,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowTaskException {
		return getPersistence()
				   .findByR_C_PrevAndNext(workflowTaskId, roleId, completed, obc);
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

	public static void removeByUserId(long assigneeUserId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByUserId(assigneeUserId);
	}

	public static void removeByRoleId(long roleId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByRoleId(roleId);
	}

	public static void removeByCompleted(boolean completed)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByCompleted(completed);
	}

	public static void removeByC_C(long companyId, boolean completed)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByC_C(companyId, completed);
	}

	public static void removeByW_C(long workflowInstanceId, boolean completed)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByW_C(workflowInstanceId, completed);
	}

	public static void removeByU_C(long assigneeUserId, boolean completed)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByU_C(assigneeUserId, completed);
	}

	public static void removeByR_C(long roleId, boolean completed)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByR_C(roleId, completed);
	}

	public static void removeByW_S(long workflowInstanceId, int state)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByW_S(workflowInstanceId, state);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByWorkflowInstanceId(long workflowInstanceId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByWorkflowInstanceId(workflowInstanceId);
	}

	public static int countByUserId(long assigneeUserId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByUserId(assigneeUserId);
	}

	public static int countByRoleId(long roleId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByRoleId(roleId);
	}

	public static int countByCompleted(boolean completed)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByCompleted(completed);
	}

	public static int countByC_C(long companyId, boolean completed)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByC_C(companyId, completed);
	}

	public static int countByW_C(long workflowInstanceId, boolean completed)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByW_C(workflowInstanceId, completed);
	}

	public static int countByU_C(long assigneeUserId, boolean completed)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByU_C(assigneeUserId, completed);
	}

	public static int countByR_C(long roleId, boolean completed)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByR_C(roleId, completed);
	}

	public static int countByW_S(long workflowInstanceId, int state)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByW_S(workflowInstanceId, state);
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