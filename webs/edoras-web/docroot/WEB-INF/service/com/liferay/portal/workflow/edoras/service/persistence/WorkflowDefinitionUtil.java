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
import com.liferay.portal.workflow.edoras.model.WorkflowDefinition;

import java.util.List;

/**
 * <a href="WorkflowDefinitionUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowDefinitionUtil {
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

	public static WorkflowDefinition remove(
		WorkflowDefinition workflowDefinition) throws SystemException {
		return getPersistence().remove(workflowDefinition);
	}

	public static WorkflowDefinition update(
		WorkflowDefinition workflowDefinition, boolean merge)
		throws SystemException {
		return getPersistence().update(workflowDefinition, merge);
	}

	public static void cacheResult(
		com.liferay.portal.workflow.edoras.model.WorkflowDefinition workflowDefinition) {
		getPersistence().cacheResult(workflowDefinition);
	}

	public static void cacheResult(
		java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowDefinition> workflowDefinitions) {
		getPersistence().cacheResult(workflowDefinitions);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowDefinition create(
		long workflowDefinitionId) {
		return getPersistence().create(workflowDefinitionId);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowDefinition remove(
		long workflowDefinitionId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowDefinitionException {
		return getPersistence().remove(workflowDefinitionId);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowDefinition updateImpl(
		com.liferay.portal.workflow.edoras.model.WorkflowDefinition workflowDefinition,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(workflowDefinition, merge);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowDefinition findByPrimaryKey(
		long workflowDefinitionId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowDefinitionException {
		return getPersistence().findByPrimaryKey(workflowDefinitionId);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowDefinition fetchByPrimaryKey(
		long workflowDefinitionId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(workflowDefinitionId);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowDefinition> findByCompanyId(
		long companyId) throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowDefinition> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowDefinition> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowDefinition findByCompanyId_First(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowDefinitionException {
		return getPersistence().findByCompanyId_First(companyId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowDefinition findByCompanyId_Last(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowDefinitionException {
		return getPersistence().findByCompanyId_Last(companyId, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowDefinition[] findByCompanyId_PrevAndNext(
		long workflowDefinitionId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowDefinitionException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(workflowDefinitionId,
			companyId, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowDefinition> findByName(
		java.lang.String name) throws com.liferay.portal.SystemException {
		return getPersistence().findByName(name);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowDefinition> findByName(
		java.lang.String name, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByName(name, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowDefinition> findByName(
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByName(name, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowDefinition findByName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowDefinitionException {
		return getPersistence().findByName_First(name, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowDefinition findByName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowDefinitionException {
		return getPersistence().findByName_Last(name, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowDefinition[] findByName_PrevAndNext(
		long workflowDefinitionId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowDefinitionException {
		return getPersistence()
				   .findByName_PrevAndNext(workflowDefinitionId, name, obc);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowDefinition> findByC_N(
		long companyId, java.lang.String name)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByC_N(companyId, name);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowDefinition> findByC_N(
		long companyId, java.lang.String name, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByC_N(companyId, name, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowDefinition> findByC_N(
		long companyId, java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByC_N(companyId, name, start, end, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowDefinition findByC_N_First(
		long companyId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowDefinitionException {
		return getPersistence().findByC_N_First(companyId, name, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowDefinition findByC_N_Last(
		long companyId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowDefinitionException {
		return getPersistence().findByC_N_Last(companyId, name, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowDefinition[] findByC_N_PrevAndNext(
		long workflowDefinitionId, long companyId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowDefinitionException {
		return getPersistence()
				   .findByC_N_PrevAndNext(workflowDefinitionId, companyId,
			name, obc);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowDefinition findByC_N_V(
		long companyId, java.lang.String name, int version)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowDefinitionException {
		return getPersistence().findByC_N_V(companyId, name, version);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowDefinition fetchByC_N_V(
		long companyId, java.lang.String name, int version)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByC_N_V(companyId, name, version);
	}

	public static com.liferay.portal.workflow.edoras.model.WorkflowDefinition fetchByC_N_V(
		long companyId, java.lang.String name, int version,
		boolean retrieveFromCache) throws com.liferay.portal.SystemException {
		return getPersistence()
				   .fetchByC_N_V(companyId, name, version, retrieveFromCache);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowDefinition> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowDefinition> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowDefinition> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	public static void removeByName(java.lang.String name)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByName(name);
	}

	public static void removeByC_N(long companyId, java.lang.String name)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByC_N(companyId, name);
	}

	public static void removeByC_N_V(long companyId, java.lang.String name,
		int version)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.workflow.edoras.NoSuchWorkflowDefinitionException {
		getPersistence().removeByC_N_V(companyId, name, version);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	public static int countByName(java.lang.String name)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByName(name);
	}

	public static int countByC_N(long companyId, java.lang.String name)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByC_N(companyId, name);
	}

	public static int countByC_N_V(long companyId, java.lang.String name,
		int version) throws com.liferay.portal.SystemException {
		return getPersistence().countByC_N_V(companyId, name, version);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static WorkflowDefinitionPersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(WorkflowDefinitionPersistence persistence) {
		_persistence = persistence;
	}

	private static WorkflowDefinitionPersistence _persistence;
}