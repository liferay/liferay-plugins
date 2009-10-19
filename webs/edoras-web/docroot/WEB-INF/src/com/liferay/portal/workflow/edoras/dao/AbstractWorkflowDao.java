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
import com.liferay.portal.workflow.edoras.dao.model.WorkflowEntityBridge;

import org.edorasframework.process.api.ex.ProcessException;

import org.springframework.transaction.support.TransactionOperations;

/**
 * <a href="AbstractWorkflowDao.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 */
public abstract class AbstractWorkflowDao
	<E, T extends WorkflowEntityBridge<E>> {

	public TransactionOperations getTxTemplate() {
		return _txTemplate;
	}

	public TransactionOperations getTxTemplateReadOnly() {
		return _txTemplateReadOnly;
	}

	public void setTxTemplate(TransactionOperations txTemplate) {
		_txTemplate = txTemplate;
	}

	public void setTxTemplateReadOnly(
		TransactionOperations txTemplateReadOnly) {

		_txTemplateReadOnly = txTemplateReadOnly;
	}

	protected boolean checkAndInitializeNewInstance(T workflowEntityBridge) {
		if (workflowEntityBridge.getPrimaryKey() == 0) {
			try {
				workflowEntityBridge.setNew(true);
				workflowEntityBridge.setPrimaryKey(
					CounterLocalServiceUtil.increment());

				return true;
			}
			catch (Exception e) {
				throw new ProcessException(e.getMessage(), e);
			}
		}
		else {
			workflowEntityBridge.setNew(false);

			return false;
		}
	}

	protected void saveInternally(T workflowEntityBridge) {
		if (checkAndInitializeNewInstance(workflowEntityBridge)) {
			workflowEntityBridge.initializeForInsert();
		}
		else {
			workflowEntityBridge.initializeForUpdate();
		}

		try {
			saveThroughPersistenceUtil(workflowEntityBridge);
		}
		catch (Exception e) {
			throw new ProcessException(e.getMessage(), e);
		}
	}

	protected abstract void saveThroughPersistenceUtil(T workflowEntityBridge)
		throws SystemException;

	private TransactionOperations _txTemplate;
	private TransactionOperations _txTemplateReadOnly;

}