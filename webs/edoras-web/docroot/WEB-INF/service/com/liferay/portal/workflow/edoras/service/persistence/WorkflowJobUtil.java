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
import com.liferay.portal.workflow.edoras.model.WorkflowJob;

import java.util.List;

/**
 * <a href="WorkflowJobUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowJobUtil {
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

	public static WorkflowJob remove(WorkflowJob workflowJob)
		throws SystemException {
		return getPersistence().remove(workflowJob);
	}

	public static WorkflowJob update(WorkflowJob workflowJob, boolean merge)
		throws SystemException {
		return getPersistence().update(workflowJob, merge);
	}

	public static void cacheResult(
		com.liferay.portal.workflow.edoras.model.WorkflowJob workflowJob) {
		getPersistence().cacheResult(workflowJob);
	}

	public static void cacheResult(
		java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> workflowJobs) {
		getPersistence().cacheResult(workflowJobs);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowJob create(
		long workflowJobId) {
		return getPersistence().create(workflowJobId);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowJob remove(
		long workflowJobId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowJobException {
		return getPersistence().remove(workflowJobId);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowJob updateImpl(
		com.liferay.portal.workflow.edoras.model.WorkflowJob workflowJob,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(workflowJob, merge);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowJob findByPrimaryKey(
		long workflowJobId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowJobException {
		return getPersistence().findByPrimaryKey(workflowJobId);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowJob fetchByPrimaryKey(
		long workflowJobId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(workflowJobId);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> findBySetupId(
		java.lang.String setupId) throws com.liferay.portal.SystemException {
		return getPersistence().findBySetupId(setupId);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> findBySetupId(
		java.lang.String setupId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findBySetupId(setupId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> findBySetupId(
		java.lang.String setupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findBySetupId(setupId, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowJob findBySetupId_First(
		java.lang.String setupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowJobException {
		return getPersistence().findBySetupId_First(setupId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowJob findBySetupId_Last(
		java.lang.String setupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowJobException {
		return getPersistence().findBySetupId_Last(setupId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowJob[] findBySetupId_PrevAndNext(
		long workflowJobId, java.lang.String setupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowJobException {
		return getPersistence()
				   .findBySetupId_PrevAndNext(workflowJobId, setupId, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> findByWorkflowDefinitionId(
		long workflowDefinitionId) throws com.liferay.portal.SystemException {
		return getPersistence().findByWorkflowDefinitionId(workflowDefinitionId);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> findByWorkflowDefinitionId(
		long workflowDefinitionId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByWorkflowDefinitionId(workflowDefinitionId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> findByWorkflowDefinitionId(
		long workflowDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByWorkflowDefinitionId(workflowDefinitionId, start,
			end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowJob findByWorkflowDefinitionId_First(
		long workflowDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowJobException {
		return getPersistence()
				   .findByWorkflowDefinitionId_First(workflowDefinitionId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowJob findByWorkflowDefinitionId_Last(
		long workflowDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowJobException {
		return getPersistence()
				   .findByWorkflowDefinitionId_Last(workflowDefinitionId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowJob[] findByWorkflowDefinitionId_PrevAndNext(
		long workflowJobId, long workflowDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowJobException {
		return getPersistence()
				   .findByWorkflowDefinitionId_PrevAndNext(workflowJobId,
			workflowDefinitionId, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> findByWorkflowInstanceId(
		long workflowInstanceId) throws com.liferay.portal.SystemException {
		return getPersistence().findByWorkflowInstanceId(workflowInstanceId);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> findByWorkflowInstanceId(
		long workflowInstanceId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByWorkflowInstanceId(workflowInstanceId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> findByWorkflowInstanceId(
		long workflowInstanceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByWorkflowInstanceId(workflowInstanceId, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowJob findByWorkflowInstanceId_First(
		long workflowInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowJobException {
		return getPersistence()
				   .findByWorkflowInstanceId_First(workflowInstanceId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowJob findByWorkflowInstanceId_Last(
		long workflowInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowJobException {
		return getPersistence()
				   .findByWorkflowInstanceId_Last(workflowInstanceId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowJob[] findByWorkflowInstanceId_PrevAndNext(
		long workflowJobId, long workflowInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowJobException {
		return getPersistence()
				   .findByWorkflowInstanceId_PrevAndNext(workflowJobId,
			workflowInstanceId, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowJob> findAll(
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

	public static void removeByWorkflowInstanceId(long workflowInstanceId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByWorkflowInstanceId(workflowInstanceId);
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

	public static int countByWorkflowInstanceId(long workflowInstanceId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByWorkflowInstanceId(workflowInstanceId);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static WorkflowJobPersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(WorkflowJobPersistence persistence) {
		_persistence = persistence;
	}

	private static WorkflowJobPersistence _persistence;
}