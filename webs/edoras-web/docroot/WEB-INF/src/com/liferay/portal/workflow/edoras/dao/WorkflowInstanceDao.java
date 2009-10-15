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
import com.liferay.portal.workflow.edoras.model.WorkflowInstance;
import com.liferay.portal.workflow.edoras.model.impl.wrapped.WorkflowEntityTransferUtil;
import com.liferay.portal.workflow.edoras.model.impl.wrapped.WorkflowInstanceImpl;
import com.liferay.portal.workflow.edoras.service.persistence.WorkflowInstanceUtil;

import java.util.List;

import org.edorasframework.process.api.dao.ObjectIdentity;
import org.edorasframework.process.api.dao.ProcessDao;
import org.edorasframework.process.api.entity.MutableProcessInstance;
import org.edorasframework.process.api.entity.ProcessInstance;
import org.edorasframework.process.api.ex.ProcessException;
import org.edorasframework.process.api.session.ProcessSession;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

/**
 * <a href="WorkflowInstanceDao.java.html"><b><i>View Source</i></b></a>
 *
 *
 * @author Micha Kiener
 */
public class WorkflowInstanceDao
	extends AbstractWorkflowDao<WorkflowInstanceImpl> implements ProcessDao {

	public void clearCache() {
		WorkflowInstanceUtil.clearCache();
	}

	public <T> void delete(T entity) {
		long primaryKey = 0;

		try {
			WorkflowInstanceImpl workflowInstanceImpl =
				(WorkflowInstanceImpl)entity;

			primaryKey = workflowInstanceImpl.getPrimaryKey();

			WorkflowInstanceUtil.remove(primaryKey);
		}
		catch (NoSuchWorkflowInstanceException nswie) {
			throw new ProcessException(
				"Could not delete workflow instance with id " + primaryKey,
				nswie);
		}
		catch (SystemException se) {
			throw new ProcessException(
				"Could not delete workflow instance with id " + primaryKey, se);
		}
	}

	public <T> T find(Class<T> clazz, Object identity) {
		return (T)loadProcessInstance((Long)identity);
	}

	public <T> T find(T entity, Object identity) {
		return (T)loadProcessInstance((Long)identity);
	}

	public <T> T loadAttribute(
		MutableProcessInstance instance, ObjectIdentity identity,
		String attributeName) {

		// TODO

		return null;
	}

	public List<? extends MutableProcessInstance> loadOpenInstances() {
		TransactionCallback transactionCallback = new TransactionCallback() {

			public Object doInTransaction(TransactionStatus transactionStatus) {
				try {
					List<WorkflowInstance> workflowInstances =
						WorkflowInstanceUtil.findByFinished(false);

					return WorkflowEntityTransferUtil.transferLoadedObjects(
						workflowInstances, null, true);
				}
				catch (SystemException se) {
					throw new ProcessException(
						"Could not load open workflow instances", se);
				}
			}

		};

		return (List<? extends MutableProcessInstance>)
			getTxTemplateReadOnly().execute(transactionCallback);
	}

	public <T extends ProcessInstance> T loadProcessInstance(
		final long primaryKey) {

		TransactionCallback transactionCallback = new TransactionCallback() {

			public Object doInTransaction(TransactionStatus transactionStatus) {
				try {
					WorkflowInstance instance =
						WorkflowInstanceUtil.findByPrimaryKey(primaryKey);

					return new WorkflowInstanceImpl(null, instance, true);
				}
				catch (NoSuchWorkflowInstanceException nswie) {
					return null;
				}
				catch (SystemException se) {
					throw new ProcessException(
						"Could not load workflow instances with id " +
							primaryKey,
						se);
				}
			}

		};

		return (T)getTxTemplateReadOnly().execute(transactionCallback);
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

		TransactionCallback transactionCallback = new TransactionCallback() {

			public Object doInTransaction(TransactionStatus transactionStatus) {
				try {
					List<WorkflowInstance> workflowInstances =
						WorkflowInstanceUtil.findByC_C(
							relationType, relationId);

					return WorkflowEntityTransferUtil.transferLoadedObjects(
						workflowInstances, null, true);
				}
				catch (SystemException se) {
					throw new ProcessException(
						"Could not load workflow instances with type " +
							relationType + " and id " + relationId,
						se);
				}
			}
		};

		return (List<? extends MutableProcessInstance>)
			getTxTemplateReadOnly().execute(transactionCallback);
	}

	public <T> T merge(T entity) {
		return entity;
	}

	public ObjectIdentity persistAttribute(
		Object attribute, MutableProcessInstance instance,
		String attributeName) {

		// TODO

		return null;
	}

	public <T> void refresh(T entity) {
	}

	public void reload(Object entity) {
	}

	public <T> void save(T entity) {
		WorkflowInstanceImpl workflowInstanceImpl= (WorkflowInstanceImpl)entity;

		if (super.checkAndInitializeNewInstance(workflowInstanceImpl)) {
			workflowInstanceImpl.initializeForInsert();
		}
		else {
			workflowInstanceImpl.initializeForUpdate();
		}

		try {
			WorkflowInstanceUtil.update(workflowInstanceImpl.unwrap());
		}
		catch (SystemException se) {
			throw new ProcessException(
				"Could not update workflow instance", se);
		}
	}

	public List<? extends MutableProcessInstance> searchChildInstances(
		MutableProcessInstance parentInstance, String cause, boolean onlyOpen) {

		List<WorkflowInstance> workflowInstances = null;

		try {
			if (onlyOpen) {
				workflowInstances = WorkflowInstanceUtil.findByC_P_R_F(
					parentInstance.getTenantId(),
					parentInstance.getPrimaryKey(), cause, false);
			}
			else {
				workflowInstances = WorkflowInstanceUtil.findByC_P_R(
					parentInstance.getTenantId(),
					parentInstance.getPrimaryKey(), cause);
			}

			return (List<? extends MutableProcessInstance>)
				WorkflowEntityTransferUtil.transferLoadedObjects(
					workflowInstances, (WorkflowInstanceImpl)parentInstance,
					false);
		}
		catch (SystemException se) {
			throw new ProcessException(
				"Could not load children for workflow instance with id " +
					parentInstance.getPrimaryKey(), se);
		}
	}

	public void sessionClosing(ProcessSession session) {
	}

	public void sessionStarted(ProcessSession session) {
	}

}