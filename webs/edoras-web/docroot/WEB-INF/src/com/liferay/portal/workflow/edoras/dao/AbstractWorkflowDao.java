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
import com.liferay.portal.workflow.edoras.dao.model.WorkflowEntity;

import org.edorasframework.process.api.ex.ProcessException;

import org.springframework.transaction.support.TransactionOperations;

/**
 * <a href="AbstractWorkflowDao.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 */
public class AbstractWorkflowDao<T extends WorkflowEntity> {

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

	protected boolean checkAndInitializeNewInstance(T workflowEntity) {
		if (workflowEntity.getPrimaryKey() == 0) {
			try {
				workflowEntity.setNew(true);
				workflowEntity.setPrimaryKey(
					CounterLocalServiceUtil.increment());

				return true;
			}
			catch (SystemException se) {
				throw new ProcessException(
					"Could not obtain a new primary key from counter service",
					se);
			}
		}
		else {
			workflowEntity.setNew(false);

			return false;
		}
	}

	private TransactionOperations _txTemplate;
	private TransactionOperations _txTemplateReadOnly;

}