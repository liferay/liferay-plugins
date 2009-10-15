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

import com.liferay.counter.service.CounterLocalServiceUtil;
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
		long id = 0;

		try {
			id = ((WorkflowInstanceImpl) entity).getPrimaryKey();

			WorkflowInstanceUtil.remove(id);
		}
		catch (NoSuchWorkflowInstanceException nswie) {
			throw new ProcessException(
				"Could not delete workflow instance with id " + id, nswie);
		}
		catch (SystemException se) {
			throw new ProcessException(
				"Could not delete workflow instance with id " + id, se);
		}
	}

	public <T> T find(Class<T> clazz, Object identity) {
		return (T) loadProcessInstance((Long) identity);
	}

	public <T> T find(T entity, Object identity) {
		return (T) loadProcessInstance((Long) identity);
	}

	public <T> T loadAttribute(
		MutableProcessInstance instance, ObjectIdentity identity,
		String attributeName) {

		// TODO Auto-generated method stub
		return null;
	}

	public List<? extends MutableProcessInstance> loadOpenInstances() {

		return (List<? extends MutableProcessInstance>) getTxTemplateReadOnly().execute(
			new TransactionCallback() {

			public Object doInTransaction(TransactionStatus status) {
				try {
					List<WorkflowInstance> list =
						WorkflowInstanceUtil.findByFinished(false);

					return WorkflowEntityTransferUtil.transferLoadedObjects(
						list, null, true);
				}
				catch (SystemException se) {
					throw new ProcessException(
						"Could not load open workflow instances", se);
				}
			}
		});
	}

	public <T extends ProcessInstance> T loadProcessInstance(
		final long primaryKey) {

		return (T) getTxTemplateReadOnly().execute(new TransactionCallback() {

			public Object doInTransaction(TransactionStatus status) {
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
						"Could not load workflow instances with id [" +
							primaryKey + "]", se);
				}
			}
		});
	}

	public <T extends ProcessInstance> T loadProcessInstance(
		String relationType, Long relationId) {

		List<? extends MutableProcessInstance> list =
			loadProcessInstances(relationType, relationId);

		if (list != null && list.size() > 0) {
			return (T) list.get(0);
		}

		return null;
	}

	public List<? extends MutableProcessInstance> loadProcessInstances(
		final String relationType, final Long relationId) {

		return (List<? extends MutableProcessInstance>) getTxTemplateReadOnly().execute(
			new TransactionCallback() {

			public Object doInTransaction(TransactionStatus arg0) {
				try {
					List<WorkflowInstance> list =
						WorkflowInstanceUtil.findByC_C(relationType, relationId);

					return WorkflowEntityTransferUtil.transferLoadedObjects(
						list, null, true);
				}
				catch (SystemException se) {
					throw new ProcessException(
						"Could not load workflow instances based on relation [" +
							relationType + " / " + relationId + "]", se);
				}
			}
		});
	}

	public <T> T merge(T entity) {
		return entity;
	}

	public ObjectIdentity persistAttribute(
		Object attribute, MutableProcessInstance instance, String attributeName) {

		// TODO Auto-generated method stub
		return null;
	}

	public <T> void refresh(T entity) {
	}

	public void reload(Object entity) {
	}

	public <T> void save(T entity) {
		WorkflowInstanceImpl workflowInstance = (WorkflowInstanceImpl) entity;

		if (super.checkAndInitializeNewInstance(workflowInstance)) {
			workflowInstance.initializeForInsert();
		}
		else {
			workflowInstance.initializeForUpdate();
		}

		try {
			WorkflowInstance inst =
				new com.liferay.portal.workflow.edoras.model.impl.WorkflowInstanceImpl();
			inst.setNew(true);
			inst.setPrimaryKey(CounterLocalServiceUtil.increment());
			
			WorkflowInstance inst2 = workflowInstance.unwrap();
			// inst.setAttributes(inst2.getAttributes());
			// inst.setSetupId(inst2.getSetupId());
			// inst.setNestedWorkflowDefinitionIds("test");

			WorkflowInstanceUtil.update(inst2);// workflowInstance.unwrap());
		}
		catch (SystemException se) {
			throw new ProcessException(
				"Could not update workflow instance", se);
		}
	}

	public List<? extends MutableProcessInstance> searchChildInstances(
		MutableProcessInstance parentInstance, String cause, boolean onlyOpen) {
		List<WorkflowInstance> list = null;

		try {
			if (onlyOpen) {
				list =
					WorkflowInstanceUtil.findByC_P_R_F(
						parentInstance.getTenantId(),
						parentInstance.getPrimaryKey(), cause, false);
			}
			else {
				list =
					WorkflowInstanceUtil.findByC_P_R(
						parentInstance.getTenantId(),
						parentInstance.getPrimaryKey(), cause);
			}

			return (List<? extends MutableProcessInstance>) WorkflowEntityTransferUtil.transferLoadedObjects(
				list, (WorkflowInstanceImpl) parentInstance, false);
		}
		catch (SystemException se) {
			throw new ProcessException(
				"Could not load children for workflow instance with id [" +
					parentInstance.getPrimaryKey() + "]", se);
		}
	}

	public void sessionClosing(ProcessSession session) {
	}

	public void sessionStarted(ProcessSession session) {
	}

}