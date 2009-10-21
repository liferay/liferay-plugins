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
import com.liferay.portal.workflow.edoras.model.WorkflowJob;

/**
 * <a href="WorkflowJobPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public interface WorkflowJobPersistence extends BasePersistence<WorkflowJob> {
	public void cacheResult(
		com.liferay.portal.workflow.edoras.model.WorkflowJob workflowJob);

	public void cacheResult(
		java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> workflowJobs);

	public com.liferay.portal.workflow.edoras.model.WorkflowJob create(
		long workflowJobId);

	public com.liferay.portal.workflow.edoras.model.WorkflowJob remove(
		long workflowJobId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowJobException;

	public com.liferay.portal.workflow.edoras.model.WorkflowJob updateImpl(
		com.liferay.portal.workflow.edoras.model.WorkflowJob workflowJob,
		boolean merge) throws com.liferay.portal.SystemException;

	public com.liferay.portal.workflow.edoras.model.WorkflowJob findByPrimaryKey(
		long workflowJobId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowJobException;

	public com.liferay.portal.workflow.edoras.model.WorkflowJob fetchByPrimaryKey(
		long workflowJobId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> findBySetupId(
		java.lang.String setupId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> findBySetupId(
		java.lang.String setupId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> findBySetupId(
		java.lang.String setupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.workflow.edoras.model.WorkflowJob findBySetupId_First(
		java.lang.String setupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowJobException;

	public com.liferay.portal.workflow.edoras.model.WorkflowJob findBySetupId_Last(
		java.lang.String setupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowJobException;

	public com.liferay.portal.workflow.edoras.model.WorkflowJob[] findBySetupId_PrevAndNext(
		long workflowJobId, java.lang.String setupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowJobException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> findByWorkflowDefinitionId(
		long workflowDefinitionId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> findByWorkflowDefinitionId(
		long workflowDefinitionId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> findByWorkflowDefinitionId(
		long workflowDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.workflow.edoras.model.WorkflowJob findByWorkflowDefinitionId_First(
		long workflowDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowJobException;

	public com.liferay.portal.workflow.edoras.model.WorkflowJob findByWorkflowDefinitionId_Last(
		long workflowDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowJobException;

	public com.liferay.portal.workflow.edoras.model.WorkflowJob[] findByWorkflowDefinitionId_PrevAndNext(
		long workflowJobId, long workflowDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowJobException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> findByWorkflowInstanceId(
		long workflowInstanceId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> findByWorkflowInstanceId(
		long workflowInstanceId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> findByWorkflowInstanceId(
		long workflowInstanceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.workflow.edoras.model.WorkflowJob findByWorkflowInstanceId_First(
		long workflowInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowJobException;

	public com.liferay.portal.workflow.edoras.model.WorkflowJob findByWorkflowInstanceId_Last(
		long workflowInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowJobException;

	public com.liferay.portal.workflow.edoras.model.WorkflowJob[] findByWorkflowInstanceId_PrevAndNext(
		long workflowJobId, long workflowInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowJobException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeBySetupId(java.lang.String setupId)
		throws com.liferay.portal.SystemException;

	public void removeByWorkflowDefinitionId(long workflowDefinitionId)
		throws com.liferay.portal.SystemException;

	public void removeByWorkflowInstanceId(long workflowInstanceId)
		throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countBySetupId(java.lang.String setupId)
		throws com.liferay.portal.SystemException;

	public int countByWorkflowDefinitionId(long workflowDefinitionId)
		throws com.liferay.portal.SystemException;

	public int countByWorkflowInstanceId(long workflowInstanceId)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;
}