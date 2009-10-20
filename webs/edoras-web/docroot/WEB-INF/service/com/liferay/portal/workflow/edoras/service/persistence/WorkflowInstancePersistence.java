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

/**
 * <a href="WorkflowInstancePersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public interface WorkflowInstancePersistence extends BasePersistence {
	public void cacheResult(
		com.liferay.portal.workflow.edoras.model.WorkflowInstance workflowInstance);

	public void cacheResult(
		java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> workflowInstances);

	public void clearCache();

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance create(
		long workflowInstanceId);

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance remove(
		long workflowInstanceId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance remove(
		com.liferay.portal.workflow.edoras.model.WorkflowInstance workflowInstance)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance update(
		com.liferay.portal.workflow.edoras.model.WorkflowInstance workflowInstance)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance update(
		com.liferay.portal.workflow.edoras.model.WorkflowInstance workflowInstance,
		boolean merge) throws com.liferay.portal.SystemException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance updateImpl(
		com.liferay.portal.workflow.edoras.model.WorkflowInstance workflowInstance,
		boolean merge) throws com.liferay.portal.SystemException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance findByPrimaryKey(
		long workflowInstanceId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance fetchByPrimaryKey(
		long workflowInstanceId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findBySetupId(
		java.lang.String setupId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findBySetupId(
		java.lang.String setupId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findBySetupId(
		java.lang.String setupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance findBySetupId_First(
		java.lang.String setupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance findBySetupId_Last(
		java.lang.String setupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance[] findBySetupId_PrevAndNext(
		long workflowInstanceId, java.lang.String setupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByWorkflowDefinitionId(
		long workflowDefinitionId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByWorkflowDefinitionId(
		long workflowDefinitionId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByWorkflowDefinitionId(
		long workflowDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance findByWorkflowDefinitionId_First(
		long workflowDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance findByWorkflowDefinitionId_Last(
		long workflowDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance[] findByWorkflowDefinitionId_PrevAndNext(
		long workflowInstanceId, long workflowDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByParentWorkflowInstanceId(
		long parentWorkflowInstanceId)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByParentWorkflowInstanceId(
		long parentWorkflowInstanceId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByParentWorkflowInstanceId(
		long parentWorkflowInstanceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance findByParentWorkflowInstanceId_First(
		long parentWorkflowInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance findByParentWorkflowInstanceId_Last(
		long parentWorkflowInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance[] findByParentWorkflowInstanceId_PrevAndNext(
		long workflowInstanceId, long parentWorkflowInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByFinished(
		boolean finished) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByFinished(
		boolean finished, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByFinished(
		boolean finished, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance findByFinished_First(
		boolean finished, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance findByFinished_Last(
		boolean finished, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance[] findByFinished_PrevAndNext(
		long workflowInstanceId, boolean finished,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByworkflowDefinitionName(
		java.lang.String workflowDefinitionName)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByworkflowDefinitionName(
		java.lang.String workflowDefinitionName, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByworkflowDefinitionName(
		java.lang.String workflowDefinitionName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance findByworkflowDefinitionName_First(
		java.lang.String workflowDefinitionName,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance findByworkflowDefinitionName_Last(
		java.lang.String workflowDefinitionName,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance[] findByworkflowDefinitionName_PrevAndNext(
		long workflowInstanceId, java.lang.String workflowDefinitionName,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByN_V(
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByN_V(
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion,
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByN_V(
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance findByN_V_First(
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance findByN_V_Last(
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance[] findByN_V_PrevAndNext(
		long workflowInstanceId, java.lang.String workflowDefinitionName,
		int workflowDefinitionVersion,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByN_F(
		java.lang.String workflowDefinitionName, boolean finished)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByN_F(
		java.lang.String workflowDefinitionName, boolean finished, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByN_F(
		java.lang.String workflowDefinitionName, boolean finished, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance findByN_F_First(
		java.lang.String workflowDefinitionName, boolean finished,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance findByN_F_Last(
		java.lang.String workflowDefinitionName, boolean finished,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance[] findByN_F_PrevAndNext(
		long workflowInstanceId, java.lang.String workflowDefinitionName,
		boolean finished, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_P(
		long companyId, long parentWorkflowInstanceId)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_P(
		long companyId, long parentWorkflowInstanceId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_P(
		long companyId, long parentWorkflowInstanceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance findByC_P_First(
		long companyId, long parentWorkflowInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance findByC_P_Last(
		long companyId, long parentWorkflowInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance[] findByC_P_PrevAndNext(
		long workflowInstanceId, long companyId, long parentWorkflowInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_F(
		long companyId, boolean finished)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_F(
		long companyId, boolean finished, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_F(
		long companyId, boolean finished, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance findByC_F_First(
		long companyId, boolean finished,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance findByC_F_Last(
		long companyId, boolean finished,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance[] findByC_F_PrevAndNext(
		long workflowInstanceId, long companyId, boolean finished,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_C(
		java.lang.String relationClassName, long relationClassPK)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_C(
		java.lang.String relationClassName, long relationClassPK, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_C(
		java.lang.String relationClassName, long relationClassPK, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance findByC_C_First(
		java.lang.String relationClassName, long relationClassPK,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance findByC_C_Last(
		java.lang.String relationClassName, long relationClassPK,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance[] findByC_C_PrevAndNext(
		long workflowInstanceId, java.lang.String relationClassName,
		long relationClassPK,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByN_V_F(
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion,
		boolean finished) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByN_V_F(
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion,
		boolean finished, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByN_V_F(
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion,
		boolean finished, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance findByN_V_F_First(
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion,
		boolean finished, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance findByN_V_F_Last(
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion,
		boolean finished, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance[] findByN_V_F_PrevAndNext(
		long workflowInstanceId, java.lang.String workflowDefinitionName,
		int workflowDefinitionVersion, boolean finished,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_P_R(
		long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_P_R(
		long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_P_R(
		long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance findByC_P_R_First(
		long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance findByC_P_R_Last(
		long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance[] findByC_P_R_PrevAndNext(
		long workflowInstanceId, long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_P_R_F(
		long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName, boolean finished)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_P_R_F(
		long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName, boolean finished, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_P_R_F(
		long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName, boolean finished, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance findByC_P_R_F_First(
		long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName, boolean finished,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance findByC_P_R_F_Last(
		long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName, boolean finished,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance[] findByC_P_R_F_PrevAndNext(
		long workflowInstanceId, long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName, boolean finished,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeBySetupId(java.lang.String setupId)
		throws com.liferay.portal.SystemException;

	public void removeByWorkflowDefinitionId(long workflowDefinitionId)
		throws com.liferay.portal.SystemException;

	public void removeByParentWorkflowInstanceId(long parentWorkflowInstanceId)
		throws com.liferay.portal.SystemException;

	public void removeByFinished(boolean finished)
		throws com.liferay.portal.SystemException;

	public void removeByworkflowDefinitionName(
		java.lang.String workflowDefinitionName)
		throws com.liferay.portal.SystemException;

	public void removeByN_V(java.lang.String workflowDefinitionName,
		int workflowDefinitionVersion)
		throws com.liferay.portal.SystemException;

	public void removeByN_F(java.lang.String workflowDefinitionName,
		boolean finished) throws com.liferay.portal.SystemException;

	public void removeByC_P(long companyId, long parentWorkflowInstanceId)
		throws com.liferay.portal.SystemException;

	public void removeByC_F(long companyId, boolean finished)
		throws com.liferay.portal.SystemException;

	public void removeByC_C(java.lang.String relationClassName,
		long relationClassPK) throws com.liferay.portal.SystemException;

	public void removeByN_V_F(java.lang.String workflowDefinitionName,
		int workflowDefinitionVersion, boolean finished)
		throws com.liferay.portal.SystemException;

	public void removeByC_P_R(long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName)
		throws com.liferay.portal.SystemException;

	public void removeByC_P_R_F(long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName, boolean finished)
		throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countBySetupId(java.lang.String setupId)
		throws com.liferay.portal.SystemException;

	public int countByWorkflowDefinitionId(long workflowDefinitionId)
		throws com.liferay.portal.SystemException;

	public int countByParentWorkflowInstanceId(long parentWorkflowInstanceId)
		throws com.liferay.portal.SystemException;

	public int countByFinished(boolean finished)
		throws com.liferay.portal.SystemException;

	public int countByworkflowDefinitionName(
		java.lang.String workflowDefinitionName)
		throws com.liferay.portal.SystemException;

	public int countByN_V(java.lang.String workflowDefinitionName,
		int workflowDefinitionVersion)
		throws com.liferay.portal.SystemException;

	public int countByN_F(java.lang.String workflowDefinitionName,
		boolean finished) throws com.liferay.portal.SystemException;

	public int countByC_P(long companyId, long parentWorkflowInstanceId)
		throws com.liferay.portal.SystemException;

	public int countByC_F(long companyId, boolean finished)
		throws com.liferay.portal.SystemException;

	public int countByC_C(java.lang.String relationClassName,
		long relationClassPK) throws com.liferay.portal.SystemException;

	public int countByN_V_F(java.lang.String workflowDefinitionName,
		int workflowDefinitionVersion, boolean finished)
		throws com.liferay.portal.SystemException;

	public int countByC_P_R(long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName)
		throws com.liferay.portal.SystemException;

	public int countByC_P_R_F(long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName, boolean finished)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;
}