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

import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.workflow.edoras.model.WorkflowLog;

/**
 * <a href="WorkflowLogPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public interface WorkflowLogPersistence extends BasePersistence<WorkflowLog> {
	public void cacheResult(
		com.liferay.portal.workflow.edoras.model.WorkflowLog workflowLog);

	public void cacheResult(
		java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> workflowLogs);

	public com.liferay.portal.workflow.edoras.model.WorkflowLog create(
		long workflowLogId);

	public com.liferay.portal.workflow.edoras.model.WorkflowLog remove(
		long workflowLogId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException;

	public com.liferay.portal.workflow.edoras.model.WorkflowLog updateImpl(
		com.liferay.portal.workflow.edoras.model.WorkflowLog workflowLog,
		boolean merge) throws com.liferay.portal.SystemException;

	public com.liferay.portal.workflow.edoras.model.WorkflowLog findByPrimaryKey(
		long workflowLogId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException;

	public com.liferay.portal.workflow.edoras.model.WorkflowLog fetchByPrimaryKey(
		long workflowLogId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findByWorkflowInstanceId(
		long workflowInstanceId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findByWorkflowInstanceId(
		long workflowInstanceId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findByWorkflowInstanceId(
		long workflowInstanceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.workflow.edoras.model.WorkflowLog findByWorkflowInstanceId_First(
		long workflowInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException;

	public com.liferay.portal.workflow.edoras.model.WorkflowLog findByWorkflowInstanceId_Last(
		long workflowInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException;

	public com.liferay.portal.workflow.edoras.model.WorkflowLog[] findByWorkflowInstanceId_PrevAndNext(
		long workflowLogId, long workflowInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findByWorkflowTaskId(
		long workflowTaskId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findByWorkflowTaskId(
		long workflowTaskId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findByWorkflowTaskId(
		long workflowTaskId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.workflow.edoras.model.WorkflowLog findByWorkflowTaskId_First(
		long workflowTaskId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException;

	public com.liferay.portal.workflow.edoras.model.WorkflowLog findByWorkflowTaskId_Last(
		long workflowTaskId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException;

	public com.liferay.portal.workflow.edoras.model.WorkflowLog[] findByWorkflowTaskId_PrevAndNext(
		long workflowLogId, long workflowTaskId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findByW_T(
		long workflowInstanceId, int logEntityType)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findByW_T(
		long workflowInstanceId, int logEntityType, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findByW_T(
		long workflowInstanceId, int logEntityType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.workflow.edoras.model.WorkflowLog findByW_T_First(
		long workflowInstanceId, int logEntityType,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException;

	public com.liferay.portal.workflow.edoras.model.WorkflowLog findByW_T_Last(
		long workflowInstanceId, int logEntityType,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException;

	public com.liferay.portal.workflow.edoras.model.WorkflowLog[] findByW_T_PrevAndNext(
		long workflowLogId, long workflowInstanceId, int logEntityType,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findByW_T_T(
		long workflowInstanceId, int logEntityType, int type)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findByW_T_T(
		long workflowInstanceId, int logEntityType, int type, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findByW_T_T(
		long workflowInstanceId, int logEntityType, int type, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.workflow.edoras.model.WorkflowLog findByW_T_T_First(
		long workflowInstanceId, int logEntityType, int type,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException;

	public com.liferay.portal.workflow.edoras.model.WorkflowLog findByW_T_T_Last(
		long workflowInstanceId, int logEntityType, int type,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException;

	public com.liferay.portal.workflow.edoras.model.WorkflowLog[] findByW_T_T_PrevAndNext(
		long workflowLogId, long workflowInstanceId, int logEntityType,
		int type, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowLogException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowLog> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByWorkflowInstanceId(long workflowInstanceId)
		throws com.liferay.portal.SystemException;

	public void removeByWorkflowTaskId(long workflowTaskId)
		throws com.liferay.portal.SystemException;

	public void removeByW_T(long workflowInstanceId, int logEntityType)
		throws com.liferay.portal.SystemException;

	public void removeByW_T_T(long workflowInstanceId, int logEntityType,
		int type) throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByWorkflowInstanceId(long workflowInstanceId)
		throws com.liferay.portal.SystemException;

	public int countByWorkflowTaskId(long workflowTaskId)
		throws com.liferay.portal.SystemException;

	public int countByW_T(long workflowInstanceId, int logEntityType)
		throws com.liferay.portal.SystemException;

	public int countByW_T_T(long workflowInstanceId, int logEntityType, int type)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;
}