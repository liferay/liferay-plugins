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
 * <a href="WorkflowInstanceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowInstanceUtil {
	public static void cacheResult(
		com.liferay.portal.workflow.edoras.model.WorkflowInstance workflowInstance) {
		getPersistence().cacheResult(workflowInstance);
	}

	public static void cacheResult(
		java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> workflowInstances) {
		getPersistence().cacheResult(workflowInstances);
	}

	public static void clearCache() {
		getPersistence().clearCache();
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

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance remove(
		com.liferay.portal.workflow.edoras.model.WorkflowInstance workflowInstance)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(workflowInstance);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance update(
		com.liferay.portal.workflow.edoras.model.WorkflowInstance workflowInstance)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(workflowInstance);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance update(
		com.liferay.portal.workflow.edoras.model.WorkflowInstance workflowInstance,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().update(workflowInstance, merge);
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

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByC_FID(
		long companyId, java.lang.String friendlyId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence().findByC_FID(companyId, friendlyId);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance fetchByC_FID(
		long companyId, java.lang.String friendlyId)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByC_FID(companyId, friendlyId);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance fetchByC_FID(
		long companyId, java.lang.String friendlyId, boolean retrieveFromCache)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .fetchByC_FID(companyId, friendlyId, retrieveFromCache);
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

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_C(
		java.lang.String className, long classPK)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByC_C(className, classPK);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_C(
		java.lang.String className, long classPK, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByC_C(className, classPK, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> findByC_C(
		java.lang.String className, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByC_C(className, classPK, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByC_C_First(
		java.lang.String className, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence().findByC_C_First(className, classPK, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance findByC_C_Last(
		java.lang.String className, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence().findByC_C_Last(className, classPK, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowInstance[] findByC_C_PrevAndNext(
		long workflowInstanceId, java.lang.String className, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		return getPersistence()
				   .findByC_C_PrevAndNext(workflowInstanceId, className,
			classPK, obc);
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

	public static void removeByC_P(long companyId, long parentWorkflowInstanceId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByC_P(companyId, parentWorkflowInstanceId);
	}

	public static void removeByC_FID(long companyId, java.lang.String friendlyId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException {
		getPersistence().removeByC_FID(companyId, friendlyId);
	}

	public static void removeByC_F(long companyId, boolean finished)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByC_F(companyId, finished);
	}

	public static void removeByC_C(java.lang.String className, long classPK)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByC_C(className, classPK);
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

	public static int countByC_P(long companyId, long parentWorkflowInstanceId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByC_P(companyId, parentWorkflowInstanceId);
	}

	public static int countByC_FID(long companyId, java.lang.String friendlyId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByC_FID(companyId, friendlyId);
	}

	public static int countByC_F(long companyId, boolean finished)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByC_F(companyId, finished);
	}

	public static int countByC_C(java.lang.String className, long classPK)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByC_C(className, classPK);
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