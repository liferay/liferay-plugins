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

package com.liferay.portal.workflow.edoras.dao;

import com.liferay.portal.SystemException;
import com.liferay.portal.workflow.edoras.NoSuchWorkflowInstanceException;
import com.liferay.portal.workflow.edoras.dao.model.WorkflowEntityBridgeUtil;
import com.liferay.portal.workflow.edoras.dao.model.WorkflowInstanceBridge;
import com.liferay.portal.workflow.edoras.model.WorkflowInstance;
import com.liferay.portal.workflow.edoras.service.persistence.WorkflowInstanceUtil;

import java.util.List;

import org.edorasframework.process.api.dao.ObjectIdentity;
import org.edorasframework.process.api.dao.ProcessDao;
import org.edorasframework.process.api.entity.MutableProcessInstance;
import org.edorasframework.process.api.entity.ProcessInstance;
import org.edorasframework.process.api.ex.ProcessException;
import org.edorasframework.process.api.session.ProcessSession;

/**
 * <a href="WorkflowInstanceDao.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 */
public class WorkflowInstanceDao
	extends AbstractWorkflowDao<WorkflowInstance, WorkflowInstanceBridge>
	implements ProcessDao {

	public void clearCache() {
		WorkflowInstanceUtil.clearCache();
	}

	public <T> void delete(T workflowEntityBridge) {
		try {
			WorkflowInstanceBridge workflowInstanceBridge =
				(WorkflowInstanceBridge)workflowEntityBridge;

			WorkflowInstanceUtil.remove(workflowInstanceBridge.getPrimaryKey());
		}
		catch (Exception e) {
			throw new ProcessException(e.getMessage(), e);
		}
	}

	public <T> T find(Class<T> clazz, Object identity) {
		return (T)loadProcessInstance((Long)identity);
	}

	public <T> T find(T workflowEntityBridge, Object identity) {
		return (T)loadProcessInstance((Long)identity);
	}

	public <T> T loadAttribute(
		MutableProcessInstance mutableProcessInstance,
		ObjectIdentity objectIdentity, String attributeName) {

		// TODO

		return null;
	}

	public List<? extends MutableProcessInstance> loadOpenInstances() {
		try {
			List<WorkflowInstance> workflowInstances =
				WorkflowInstanceUtil.findByFinished(false);

			return (List<? extends MutableProcessInstance>) WorkflowEntityBridgeUtil.wrapWorkflowInstances(
				workflowInstances, null, true);
		}
		catch (Exception e) {
			throw new ProcessException(e.getMessage(), e);
		}
	}

	public <T extends ProcessInstance> T loadProcessInstance(
		final long primaryKey) {
		
		return (T) loadProcessInstance(primaryKey, true);
	}

	public <T extends ProcessInstance> T loadProcessInstance(
		long primaryKey, boolean includeChildren) {

		try {
			WorkflowInstance workflowInstance =
				WorkflowInstanceUtil.findByPrimaryKey(primaryKey);

			return (T) new WorkflowInstanceBridge(
				workflowInstance, null, includeChildren);
		}
		catch (NoSuchWorkflowInstanceException nswie) {
			return null;
		}
		catch (Exception e) {
			throw new ProcessException(e.getMessage(), e);
		}
	}

	public <T extends ProcessInstance> T loadProcessInstance(
		String relationType, Long relationId) {

		List<? extends MutableProcessInstance>processInstances =
			loadProcessInstances(relationType, relationId);

		if ((processInstances != null) && !processInstances.isEmpty()) {
			return (T)processInstances.get(0);
		}

		return null;
	}

	public List<? extends MutableProcessInstance> loadProcessInstances(
		final String relationType, final Long relationId) {

		try {
			List<WorkflowInstance> workflowInstances =
				WorkflowInstanceUtil.findByC_C(relationType, relationId);

			return (List<? extends MutableProcessInstance>) WorkflowEntityBridgeUtil.wrapWorkflowInstances(
				workflowInstances, null, true);
		}
		catch (Exception e) {
			throw new ProcessException(e.getMessage(), e);
		}
	}

	public <T> T merge(T workflowEntityBridge) {
		return workflowEntityBridge;
	}

	public ObjectIdentity persistAttribute(
		Object attribute, MutableProcessInstance mutableProcessInstance,
		String attributeName) {

		// TODO

		return null;
	}

	public <T> void refresh(T workflowEntityBridge) {
	}

	public void reload(Object workflowEntityBridge) {
	}

	public <T> void save(T workflowEntityBridge) {
		saveInternally((WorkflowInstanceBridge)workflowEntityBridge);
	}

	public List<? extends MutableProcessInstance> searchChildInstances(
		MutableProcessInstance mutableProcessInstance, String cause,
		boolean onlyOpen) {

		try {
			List<WorkflowInstance> workflowInstances = null;

			if (onlyOpen) {
				workflowInstances = WorkflowInstanceUtil.findByC_P_R_F(
					mutableProcessInstance.getTenantId(),
					mutableProcessInstance.getPrimaryKey(), cause, false);
			}
			else {
				workflowInstances = WorkflowInstanceUtil.findByC_P_R(
					mutableProcessInstance.getTenantId(),
					mutableProcessInstance.getPrimaryKey(), cause);
			}

			return (List<? extends MutableProcessInstance>)
				WorkflowEntityBridgeUtil.wrapWorkflowInstances(
					workflowInstances,
					(WorkflowInstanceBridge)mutableProcessInstance, false);
		}
		catch (Exception e) {
			throw new ProcessException(e.getMessage(), e);
		}
	}

	public void sessionClosing(ProcessSession processSession) {
	}

	public void sessionStarted(ProcessSession processSession) {
	}

	protected void saveThroughPersistenceUtil(
			WorkflowInstanceBridge workflowInstanceBridge)
		throws SystemException {

		WorkflowInstanceUtil.update(workflowInstanceBridge.unwrap());
	}

}