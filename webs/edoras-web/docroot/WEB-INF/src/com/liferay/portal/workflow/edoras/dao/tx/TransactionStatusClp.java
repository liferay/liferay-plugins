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

package com.liferay.portal.workflow.edoras.dao.tx;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.TransactionSystemException;

/**
 * <a href="TransactionStatusClp.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * A class loader proxy implementation for a transaction status object created
 * by the transaction manager within the portal and serialized back and forth
 * between the portal and plugin class loader.
 * </p>
 *
 * @author Micha Kiener
 */
public class TransactionStatusClp implements TransactionStatus {

	public TransactionStatusClp(Object remoteTransactionStatus) {
		_remoteTransactionStatus = remoteTransactionStatus;

		if (_remoteMethods == null) {
			initRemoteMethods(remoteTransactionStatus);
		}
	}

	public Object createSavepoint() throws TransactionException {
		try {
			Method method = _remoteMethods.get("createSavepoint");

			return method.invoke(_remoteTransactionStatus);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new TransactionSystemException(e.getMessage());
		}
	}

	public Object getRemoteTransactionStatus() {
		return _remoteTransactionStatus;
	}

	public boolean hasSavepoint() {
		try {
			Method method = _remoteMethods.get("hasSavepoint");

			return (Boolean)method.invoke(_remoteTransactionStatus);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RuntimeException(e.getMessage());
		}
	}

	public boolean isCompleted() {
		try {
			Method method = _remoteMethods.get("isCompleted");

			return (Boolean)method.invoke(_remoteTransactionStatus);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RuntimeException(e.getMessage());
		}
	}

	public boolean isNewTransaction() {
		try {
			Method method = _remoteMethods.get("isNewTransaction");

			return (Boolean)method.invoke(_remoteTransactionStatus);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RuntimeException(e.getMessage());
		}
	}

	public boolean isRollbackOnly() {
		try {
			Method method = _remoteMethods.get("isRollbackOnly");

			return (Boolean)method.invoke(_remoteTransactionStatus);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RuntimeException(e.getMessage());
		}
	}

	public void releaseSavepoint(Object savepoint) throws TransactionException {
		try {
			Method method = _remoteMethods.get("releaseSavepoint");

			method.invoke(_remoteTransactionStatus);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new TransactionSystemException(e.getMessage());
		}
	}

	public void rollbackToSavepoint(Object savepoint)
		throws TransactionException {

		try {
			Method method = _remoteMethods.get("rollbackToSavepoint");

			method.invoke(_remoteTransactionStatus);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new TransactionSystemException(e.getMessage());
		}
	}

	public void setRollbackOnly() {
		try {
			Method method = _remoteMethods.get("setRollbackOnly");

			method.invoke(_remoteTransactionStatus);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RuntimeException(e.getMessage());
		}
	}

	protected void initRemoteMethods(Object remoteTransactionStatus) {
		_remoteMethods = new HashMap<String, Method>();

		Method[] methods = TransactionStatus.class.getMethods();

		for (Method method : methods) {
			_remoteMethods.put(method.getName(), method);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TransactionStatusClp.class);

	private static Map<String, Method> _remoteMethods;

	private Object _remoteTransactionStatus;

}