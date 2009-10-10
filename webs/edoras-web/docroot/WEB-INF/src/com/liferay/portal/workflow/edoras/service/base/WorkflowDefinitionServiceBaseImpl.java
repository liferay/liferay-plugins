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

package com.liferay.portal.workflow.edoras.service.base;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.service.base.PrincipalBean;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.workflow.edoras.service.WorkflowDefinitionLocalService;
import com.liferay.portal.workflow.edoras.service.WorkflowDefinitionService;
import com.liferay.portal.workflow.edoras.service.WorkflowInstanceLocalService;
import com.liferay.portal.workflow.edoras.service.WorkflowInstanceService;
import com.liferay.portal.workflow.edoras.service.WorkflowJobLocalService;
import com.liferay.portal.workflow.edoras.service.WorkflowJobService;
import com.liferay.portal.workflow.edoras.service.WorkflowLogLocalService;
import com.liferay.portal.workflow.edoras.service.WorkflowLogService;
import com.liferay.portal.workflow.edoras.service.WorkflowTaskLocalService;
import com.liferay.portal.workflow.edoras.service.WorkflowTaskService;
import com.liferay.portal.workflow.edoras.service.persistence.WorkflowDefinitionPersistence;
import com.liferay.portal.workflow.edoras.service.persistence.WorkflowInstancePersistence;
import com.liferay.portal.workflow.edoras.service.persistence.WorkflowJobPersistence;
import com.liferay.portal.workflow.edoras.service.persistence.WorkflowLogPersistence;
import com.liferay.portal.workflow.edoras.service.persistence.WorkflowTaskPersistence;

/**
 * <a href="WorkflowDefinitionServiceBaseImpl.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
 */
public abstract class WorkflowDefinitionServiceBaseImpl extends PrincipalBean
	implements WorkflowDefinitionService {
	public WorkflowDefinitionLocalService getWorkflowDefinitionLocalService() {
		return workflowDefinitionLocalService;
	}

	public void setWorkflowDefinitionLocalService(
		WorkflowDefinitionLocalService workflowDefinitionLocalService) {
		this.workflowDefinitionLocalService = workflowDefinitionLocalService;
	}

	public WorkflowDefinitionService getWorkflowDefinitionService() {
		return workflowDefinitionService;
	}

	public void setWorkflowDefinitionService(
		WorkflowDefinitionService workflowDefinitionService) {
		this.workflowDefinitionService = workflowDefinitionService;
	}

	public WorkflowDefinitionPersistence getWorkflowDefinitionPersistence() {
		return workflowDefinitionPersistence;
	}

	public void setWorkflowDefinitionPersistence(
		WorkflowDefinitionPersistence workflowDefinitionPersistence) {
		this.workflowDefinitionPersistence = workflowDefinitionPersistence;
	}

	public WorkflowInstanceLocalService getWorkflowInstanceLocalService() {
		return workflowInstanceLocalService;
	}

	public void setWorkflowInstanceLocalService(
		WorkflowInstanceLocalService workflowInstanceLocalService) {
		this.workflowInstanceLocalService = workflowInstanceLocalService;
	}

	public WorkflowInstanceService getWorkflowInstanceService() {
		return workflowInstanceService;
	}

	public void setWorkflowInstanceService(
		WorkflowInstanceService workflowInstanceService) {
		this.workflowInstanceService = workflowInstanceService;
	}

	public WorkflowInstancePersistence getWorkflowInstancePersistence() {
		return workflowInstancePersistence;
	}

	public void setWorkflowInstancePersistence(
		WorkflowInstancePersistence workflowInstancePersistence) {
		this.workflowInstancePersistence = workflowInstancePersistence;
	}

	public WorkflowJobLocalService getWorkflowJobLocalService() {
		return workflowJobLocalService;
	}

	public void setWorkflowJobLocalService(
		WorkflowJobLocalService workflowJobLocalService) {
		this.workflowJobLocalService = workflowJobLocalService;
	}

	public WorkflowJobService getWorkflowJobService() {
		return workflowJobService;
	}

	public void setWorkflowJobService(WorkflowJobService workflowJobService) {
		this.workflowJobService = workflowJobService;
	}

	public WorkflowJobPersistence getWorkflowJobPersistence() {
		return workflowJobPersistence;
	}

	public void setWorkflowJobPersistence(
		WorkflowJobPersistence workflowJobPersistence) {
		this.workflowJobPersistence = workflowJobPersistence;
	}

	public WorkflowLogLocalService getWorkflowLogLocalService() {
		return workflowLogLocalService;
	}

	public void setWorkflowLogLocalService(
		WorkflowLogLocalService workflowLogLocalService) {
		this.workflowLogLocalService = workflowLogLocalService;
	}

	public WorkflowLogService getWorkflowLogService() {
		return workflowLogService;
	}

	public void setWorkflowLogService(WorkflowLogService workflowLogService) {
		this.workflowLogService = workflowLogService;
	}

	public WorkflowLogPersistence getWorkflowLogPersistence() {
		return workflowLogPersistence;
	}

	public void setWorkflowLogPersistence(
		WorkflowLogPersistence workflowLogPersistence) {
		this.workflowLogPersistence = workflowLogPersistence;
	}

	public WorkflowTaskLocalService getWorkflowTaskLocalService() {
		return workflowTaskLocalService;
	}

	public void setWorkflowTaskLocalService(
		WorkflowTaskLocalService workflowTaskLocalService) {
		this.workflowTaskLocalService = workflowTaskLocalService;
	}

	public WorkflowTaskService getWorkflowTaskService() {
		return workflowTaskService;
	}

	public void setWorkflowTaskService(WorkflowTaskService workflowTaskService) {
		this.workflowTaskService = workflowTaskService;
	}

	public WorkflowTaskPersistence getWorkflowTaskPersistence() {
		return workflowTaskPersistence;
	}

	public void setWorkflowTaskPersistence(
		WorkflowTaskPersistence workflowTaskPersistence) {
		this.workflowTaskPersistence = workflowTaskPersistence;
	}

	protected void runSQL(String sql) throws SystemException {
		try {
			PortalUtil.runSQL(sql);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(name = "com.liferay.portal.workflow.edoras.service.WorkflowDefinitionLocalService.impl")
	protected WorkflowDefinitionLocalService workflowDefinitionLocalService;
	@BeanReference(name = "com.liferay.portal.workflow.edoras.service.WorkflowDefinitionService.impl")
	protected WorkflowDefinitionService workflowDefinitionService;
	@BeanReference(name = "com.liferay.portal.workflow.edoras.service.persistence.WorkflowDefinitionPersistence.impl")
	protected WorkflowDefinitionPersistence workflowDefinitionPersistence;
	@BeanReference(name = "com.liferay.portal.workflow.edoras.service.WorkflowInstanceLocalService.impl")
	protected WorkflowInstanceLocalService workflowInstanceLocalService;
	@BeanReference(name = "com.liferay.portal.workflow.edoras.service.WorkflowInstanceService.impl")
	protected WorkflowInstanceService workflowInstanceService;
	@BeanReference(name = "com.liferay.portal.workflow.edoras.service.persistence.WorkflowInstancePersistence.impl")
	protected WorkflowInstancePersistence workflowInstancePersistence;
	@BeanReference(name = "com.liferay.portal.workflow.edoras.service.WorkflowJobLocalService.impl")
	protected WorkflowJobLocalService workflowJobLocalService;
	@BeanReference(name = "com.liferay.portal.workflow.edoras.service.WorkflowJobService.impl")
	protected WorkflowJobService workflowJobService;
	@BeanReference(name = "com.liferay.portal.workflow.edoras.service.persistence.WorkflowJobPersistence.impl")
	protected WorkflowJobPersistence workflowJobPersistence;
	@BeanReference(name = "com.liferay.portal.workflow.edoras.service.WorkflowLogLocalService.impl")
	protected WorkflowLogLocalService workflowLogLocalService;
	@BeanReference(name = "com.liferay.portal.workflow.edoras.service.WorkflowLogService.impl")
	protected WorkflowLogService workflowLogService;
	@BeanReference(name = "com.liferay.portal.workflow.edoras.service.persistence.WorkflowLogPersistence.impl")
	protected WorkflowLogPersistence workflowLogPersistence;
	@BeanReference(name = "com.liferay.portal.workflow.edoras.service.WorkflowTaskLocalService.impl")
	protected WorkflowTaskLocalService workflowTaskLocalService;
	@BeanReference(name = "com.liferay.portal.workflow.edoras.service.WorkflowTaskService.impl")
	protected WorkflowTaskService workflowTaskService;
	@BeanReference(name = "com.liferay.portal.workflow.edoras.service.persistence.WorkflowTaskPersistence.impl")
	protected WorkflowTaskPersistence workflowTaskPersistence;
}