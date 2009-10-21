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
import com.liferay.portal.workflow.edoras.model.WorkflowInstance;

import java.util.List;

/**
 * <a href="WorkflowInstanceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowInstanceUtil {
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

	public static WorkflowInstance remove(WorkflowInstance workflowInstance)
		throws SystemException {
		return getPersistence().remove(workflowInstance);
	}

	public static WorkflowInstance update(WorkflowInstance workflowInstance,
		boolean merge) throws SystemException {
		return getPersistence().update(workflowInstance, merge);
	}

	public static void cacheResult(
		com.liferay.portal.workflow.edoras.model.WorkflowInstance workflowInstance) {
		getPersistence().cacheResult(workflowInstance);
	}

	public static void cacheResult(
		java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> workflowInstances) {
		getPersistence().cacheResult(workflowInstances);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance create(
		long workflowInstanceId) {
		return getPersistence().create(workflowInstanceId);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance remove(
		long workflowInstanceId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence().remove(workflowInstanceId);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance updateImpl(
		com.liferay.portal.workflow.edoras.model.WorkflowInstance workflowInstance,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(workflowInstance, merge);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByPrimaryKey(
		long workflowInstanceId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence().findByPrimaryKey(workflowInstanceId);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance fetchByPrimaryKey(
		long workflowInstanceId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(workflowInstanceId);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findBySetupId(
		java.lang.String setupId) throws com.liferay.portal.SystemException {
		return getPersistence().findBySetupId(setupId);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findBySetupId(
		java.lang.String setupId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findBySetupId(setupId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findBySetupId(
		java.lang.String setupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findBySetupId(setupId, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findBySetupId_First(
		java.lang.String setupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence().findBySetupId_First(setupId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findBySetupId_Last(
		java.lang.String setupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence().findBySetupId_Last(setupId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance[] findBySetupId_PrevAndNext(
		long workflowInstanceId, java.lang.String setupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findBySetupId_PrevAndNext(workflowInstanceId, setupId, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByWorkflowDefinitionId(
		long workflowDefinitionId) throws com.liferay.portal.SystemException {
		return getPersistence().findByWorkflowDefinitionId(workflowDefinitionId);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByWorkflowDefinitionId(
		long workflowDefinitionId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByWorkflowDefinitionId(workflowDefinitionId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByWorkflowDefinitionId(
		long workflowDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByWorkflowDefinitionId(workflowDefinitionId, start,
			end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByWorkflowDefinitionId_First(
		long workflowDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByWorkflowDefinitionId_First(workflowDefinitionId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByWorkflowDefinitionId_Last(
		long workflowDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByWorkflowDefinitionId_Last(workflowDefinitionId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance[] findByWorkflowDefinitionId_PrevAndNext(
		long workflowInstanceId, long workflowDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByWorkflowDefinitionId_PrevAndNext(workflowInstanceId,
			workflowDefinitionId, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByWorkflowDefinitionName(
		java.lang.String workflowDefinitionName)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByWorkflowDefinitionName(workflowDefinitionName);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByWorkflowDefinitionName(
		java.lang.String workflowDefinitionName, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByWorkflowDefinitionName(workflowDefinitionName, start,
			end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByWorkflowDefinitionName(
		java.lang.String workflowDefinitionName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByWorkflowDefinitionName(workflowDefinitionName, start,
			end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByWorkflowDefinitionName_First(
		java.lang.String workflowDefinitionName,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByWorkflowDefinitionName_First(workflowDefinitionName,
			obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByWorkflowDefinitionName_Last(
		java.lang.String workflowDefinitionName,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByWorkflowDefinitionName_Last(workflowDefinitionName,
			obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance[] findByWorkflowDefinitionName_PrevAndNext(
		long workflowInstanceId, java.lang.String workflowDefinitionName,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByWorkflowDefinitionName_PrevAndNext(workflowInstanceId,
			workflowDefinitionName, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByParentWorkflowInstanceId(
		long parentWorkflowInstanceId)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByParentWorkflowInstanceId(parentWorkflowInstanceId);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByParentWorkflowInstanceId(
		long parentWorkflowInstanceId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByParentWorkflowInstanceId(parentWorkflowInstanceId,
			start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByParentWorkflowInstanceId(
		long parentWorkflowInstanceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByParentWorkflowInstanceId(parentWorkflowInstanceId,
			start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByParentWorkflowInstanceId_First(
		long parentWorkflowInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByParentWorkflowInstanceId_First(parentWorkflowInstanceId,
			obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByParentWorkflowInstanceId_Last(
		long parentWorkflowInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByParentWorkflowInstanceId_Last(parentWorkflowInstanceId,
			obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance[] findByParentWorkflowInstanceId_PrevAndNext(
		long workflowInstanceId, long parentWorkflowInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByParentWorkflowInstanceId_PrevAndNext(workflowInstanceId,
			parentWorkflowInstanceId, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByFinished(
		boolean finished) throws com.liferay.portal.SystemException {
		return getPersistence().findByFinished(finished);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByFinished(
		boolean finished, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByFinished(finished, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByFinished(
		boolean finished, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByFinished(finished, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByFinished_First(
		boolean finished, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence().findByFinished_First(finished, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByFinished_Last(
		boolean finished, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence().findByFinished_Last(finished, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance[] findByFinished_PrevAndNext(
		long workflowInstanceId, boolean finished,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByFinished_PrevAndNext(workflowInstanceId, finished, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_P(
		long companyId, long parentWorkflowInstanceId)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByC_P(companyId, parentWorkflowInstanceId);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_P(
		long companyId, long parentWorkflowInstanceId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByC_P(companyId, parentWorkflowInstanceId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_P(
		long companyId, long parentWorkflowInstanceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByC_P(companyId, parentWorkflowInstanceId, start, end,
			obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByC_P_First(
		long companyId, long parentWorkflowInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByC_P_First(companyId, parentWorkflowInstanceId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByC_P_Last(
		long companyId, long parentWorkflowInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByC_P_Last(companyId, parentWorkflowInstanceId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance[] findByC_P_PrevAndNext(
		long workflowInstanceId, long companyId, long parentWorkflowInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByC_P_PrevAndNext(workflowInstanceId, companyId,
			parentWorkflowInstanceId, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_F(
		long companyId, boolean finished)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByC_F(companyId, finished);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_F(
		long companyId, boolean finished, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByC_F(companyId, finished, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_F(
		long companyId, boolean finished, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByC_F(companyId, finished, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByC_F_First(
		long companyId, boolean finished,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence().findByC_F_First(companyId, finished, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByC_F_Last(
		long companyId, boolean finished,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence().findByC_F_Last(companyId, finished, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance[] findByC_F_PrevAndNext(
		long workflowInstanceId, long companyId, boolean finished,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByC_F_PrevAndNext(workflowInstanceId, companyId,
			finished, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByN_V(
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByN_V(workflowDefinitionName, workflowDefinitionVersion);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByN_V(
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion,
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByN_V(workflowDefinitionName,
			workflowDefinitionVersion, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByN_V(
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByN_V(workflowDefinitionName,
			workflowDefinitionVersion, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByN_V_First(
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByN_V_First(workflowDefinitionName,
			workflowDefinitionVersion, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByN_V_Last(
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByN_V_Last(workflowDefinitionName,
			workflowDefinitionVersion, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance[] findByN_V_PrevAndNext(
		long workflowInstanceId, java.lang.String workflowDefinitionName,
		int workflowDefinitionVersion,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByN_V_PrevAndNext(workflowInstanceId,
			workflowDefinitionName, workflowDefinitionVersion, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByN_F(
		java.lang.String workflowDefinitionName, boolean finished)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByN_F(workflowDefinitionName, finished);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByN_F(
		java.lang.String workflowDefinitionName, boolean finished, int start,
		int end) throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByN_F(workflowDefinitionName, finished, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByN_F(
		java.lang.String workflowDefinitionName, boolean finished, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByN_F(workflowDefinitionName, finished, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByN_F_First(
		java.lang.String workflowDefinitionName, boolean finished,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByN_F_First(workflowDefinitionName, finished, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByN_F_Last(
		java.lang.String workflowDefinitionName, boolean finished,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByN_F_Last(workflowDefinitionName, finished, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance[] findByN_F_PrevAndNext(
		long workflowInstanceId, java.lang.String workflowDefinitionName,
		boolean finished, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByN_F_PrevAndNext(workflowInstanceId,
			workflowDefinitionName, finished, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_C(
		java.lang.String relationClassName, long relationClassPK)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByC_C(relationClassName, relationClassPK);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_C(
		java.lang.String relationClassName, long relationClassPK, int start,
		int end) throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByC_C(relationClassName, relationClassPK, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_C(
		java.lang.String relationClassName, long relationClassPK, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByC_C(relationClassName, relationClassPK, start, end,
			obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByC_C_First(
		java.lang.String relationClassName, long relationClassPK,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByC_C_First(relationClassName, relationClassPK, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByC_C_Last(
		java.lang.String relationClassName, long relationClassPK,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByC_C_Last(relationClassName, relationClassPK, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance[] findByC_C_PrevAndNext(
		long workflowInstanceId, java.lang.String relationClassName,
		long relationClassPK,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByC_C_PrevAndNext(workflowInstanceId,
			relationClassName, relationClassPK, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_P_R(
		long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByC_P_R(companyId, parentWorkflowInstanceId,
			relatedElementName);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_P_R(
		long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByC_P_R(companyId, parentWorkflowInstanceId,
			relatedElementName, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_P_R(
		long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByC_P_R(companyId, parentWorkflowInstanceId,
			relatedElementName, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByC_P_R_First(
		long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByC_P_R_First(companyId, parentWorkflowInstanceId,
			relatedElementName, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByC_P_R_Last(
		long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByC_P_R_Last(companyId, parentWorkflowInstanceId,
			relatedElementName, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance[] findByC_P_R_PrevAndNext(
		long workflowInstanceId, long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByC_P_R_PrevAndNext(workflowInstanceId, companyId,
			parentWorkflowInstanceId, relatedElementName, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByN_V_F(
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion,
		boolean finished) throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByN_V_F(workflowDefinitionName,
			workflowDefinitionVersion, finished);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByN_V_F(
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion,
		boolean finished, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByN_V_F(workflowDefinitionName,
			workflowDefinitionVersion, finished, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByN_V_F(
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion,
		boolean finished, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByN_V_F(workflowDefinitionName,
			workflowDefinitionVersion, finished, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByN_V_F_First(
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion,
		boolean finished, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByN_V_F_First(workflowDefinitionName,
			workflowDefinitionVersion, finished, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByN_V_F_Last(
		java.lang.String workflowDefinitionName, int workflowDefinitionVersion,
		boolean finished, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByN_V_F_Last(workflowDefinitionName,
			workflowDefinitionVersion, finished, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance[] findByN_V_F_PrevAndNext(
		long workflowInstanceId, java.lang.String workflowDefinitionName,
		int workflowDefinitionVersion, boolean finished,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByN_V_F_PrevAndNext(workflowInstanceId,
			workflowDefinitionName, workflowDefinitionVersion, finished, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_P_R_F(
		long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName, boolean finished)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByC_P_R_F(companyId, parentWorkflowInstanceId,
			relatedElementName, finished);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_P_R_F(
		long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName, boolean finished, int start,
		int end) throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByC_P_R_F(companyId, parentWorkflowInstanceId,
			relatedElementName, finished, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_P_R_F(
		long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName, boolean finished, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByC_P_R_F(companyId, parentWorkflowInstanceId,
			relatedElementName, finished, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByC_P_R_F_First(
		long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName, boolean finished,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByC_P_R_F_First(companyId, parentWorkflowInstanceId,
			relatedElementName, finished, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByC_P_R_F_Last(
		long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName, boolean finished,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByC_P_R_F_Last(companyId, parentWorkflowInstanceId,
			relatedElementName, finished, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance[] findByC_P_R_F_PrevAndNext(
		long workflowInstanceId, long companyId, long parentWorkflowInstanceId,
		java.lang.String relatedElementName, boolean finished,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByC_P_R_F_PrevAndNext(workflowInstanceId, companyId,
			parentWorkflowInstanceId, relatedElementName, finished, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeBySetupId(java.lang.String setupId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeBySetupId(setupId);
	}

	public static void removeByWorkflowDefinitionId(long workflowDefinitionId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByWorkflowDefinitionId(workflowDefinitionId);
	}

	public static void removeByWorkflowDefinitionName(
		java.lang.String workflowDefinitionName)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByWorkflowDefinitionName(workflowDefinitionName);
	}

	public static void removeByParentWorkflowInstanceId(
		long parentWorkflowInstanceId)
		throws com.liferay.portal.SystemException {
		getPersistence()
			.removeByParentWorkflowInstanceId(parentWorkflowInstanceId);
	}

	public static void removeByFinished(boolean finished)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByFinished(finished);
	}

	public static void removeByC_P(long companyId, long parentWorkflowInstanceId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByC_P(companyId, parentWorkflowInstanceId);
	}

	public static void removeByC_F(long companyId, boolean finished)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByC_F(companyId, finished);
	}

	public static void removeByN_V(java.lang.String workflowDefinitionName,
		int workflowDefinitionVersion)
		throws com.liferay.portal.SystemException {
		getPersistence()
			.removeByN_V(workflowDefinitionName, workflowDefinitionVersion);
	}

	public static void removeByN_F(java.lang.String workflowDefinitionName,
		boolean finished) throws com.liferay.portal.SystemException {
		getPersistence().removeByN_F(workflowDefinitionName, finished);
	}

	public static void removeByC_C(java.lang.String relationClassName,
		long relationClassPK) throws com.liferay.portal.SystemException {
		getPersistence().removeByC_C(relationClassName, relationClassPK);
	}

	public static void removeByC_P_R(long companyId,
		long parentWorkflowInstanceId, java.lang.String relatedElementName)
		throws com.liferay.portal.SystemException {
		getPersistence()
			.removeByC_P_R(companyId, parentWorkflowInstanceId,
			relatedElementName);
	}

	public static void removeByN_V_F(java.lang.String workflowDefinitionName,
		int workflowDefinitionVersion, boolean finished)
		throws com.liferay.portal.SystemException {
		getPersistence()
			.removeByN_V_F(workflowDefinitionName, workflowDefinitionVersion,
			finished);
	}

	public static void removeByC_P_R_F(long companyId,
		long parentWorkflowInstanceId, java.lang.String relatedElementName,
		boolean finished) throws com.liferay.portal.SystemException {
		getPersistence()
			.removeByC_P_R_F(companyId, parentWorkflowInstanceId,
			relatedElementName, finished);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countBySetupId(java.lang.String setupId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countBySetupId(setupId);
	}

	public static int countByWorkflowDefinitionId(long workflowDefinitionId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByWorkflowDefinitionId(workflowDefinitionId);
	}

	public static int countByWorkflowDefinitionName(
		java.lang.String workflowDefinitionName)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .countByWorkflowDefinitionName(workflowDefinitionName);
	}

	public static int countByParentWorkflowInstanceId(
		long parentWorkflowInstanceId)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .countByParentWorkflowInstanceId(parentWorkflowInstanceId);
	}

	public static int countByFinished(boolean finished)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByFinished(finished);
	}

	public static int countByC_P(long companyId, long parentWorkflowInstanceId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByC_P(companyId, parentWorkflowInstanceId);
	}

	public static int countByC_F(long companyId, boolean finished)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByC_F(companyId, finished);
	}

	public static int countByN_V(java.lang.String workflowDefinitionName,
		int workflowDefinitionVersion)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .countByN_V(workflowDefinitionName, workflowDefinitionVersion);
	}

	public static int countByN_F(java.lang.String workflowDefinitionName,
		boolean finished) throws com.liferay.portal.SystemException {
		return getPersistence().countByN_F(workflowDefinitionName, finished);
	}

	public static int countByC_C(java.lang.String relationClassName,
		long relationClassPK) throws com.liferay.portal.SystemException {
		return getPersistence().countByC_C(relationClassName, relationClassPK);
	}

	public static int countByC_P_R(long companyId,
		long parentWorkflowInstanceId, java.lang.String relatedElementName)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .countByC_P_R(companyId, parentWorkflowInstanceId,
			relatedElementName);
	}

	public static int countByN_V_F(java.lang.String workflowDefinitionName,
		int workflowDefinitionVersion, boolean finished)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .countByN_V_F(workflowDefinitionName,
			workflowDefinitionVersion, finished);
	}

	public static int countByC_P_R_F(long companyId,
		long parentWorkflowInstanceId, java.lang.String relatedElementName,
		boolean finished) throws com.liferay.portal.SystemException {
		return getPersistence()
				   .countByC_P_R_F(companyId, parentWorkflowInstanceId,
			relatedElementName, finished);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static WorkflowInstancePersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(WorkflowInstancePersistence persistence) {
		_persistence = persistence;
	}

	private static WorkflowInstancePersistence _persistence;
}