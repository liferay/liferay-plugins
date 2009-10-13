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

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

/**
 * <a href="TransactionStatusClp.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * A classloader proxy implementation for a transaction status object created by
 * the transaction manager within the portal and serialized back and forth
 * between the portal and portlet classloader.
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

	public Object createSavepoint()
		throws TransactionException {
		try {
			return _remoteMethods.get("createSavepoint").invoke(
				_remoteTransactionStatus, null);
		}
		catch (Exception e) {
			throw new IllegalArgumentException(
				"Could not invoke remote transaction status", e);
		}
	}

	public Object getRemoteTransactionStatus() {
		return _remoteTransactionStatus;
	}

	public boolean hasSavepoint() {
		try {
			return (Boolean) _remoteMethods.get("hasSavepoint").invoke(
				_remoteTransactionStatus, null);
		}
		catch (Exception e) {
			throw new IllegalArgumentException(
				"Could not invoke remote transaction status", e);
		}
	}

	protected void initRemoteMethods(Object remoteTransactionStatus) {
		_remoteMethods = new HashMap<String, Method>();
		Method[] methods = TransactionStatus.class.getMethods();
		for (Method method : methods) {
			_remoteMethods.put(method.getName(), method);
		}
	}

	public boolean isCompleted() {
		try {
			return (Boolean) _remoteMethods.get("isCompleted").invoke(
				_remoteTransactionStatus, null);
		}
		catch (Exception e) {
			throw new IllegalArgumentException(
				"Could not invoke remote transaction status", e);
		}
	}

	public boolean isNewTransaction() {
		try {
			return (Boolean) _remoteMethods.get("isNewTransaction").invoke(
				_remoteTransactionStatus, null);
		}
		catch (Exception e) {
			throw new IllegalArgumentException(
				"Could not invoke remote transaction status", e);
		}
	}

	public boolean isRollbackOnly() {
		try {
			return (Boolean) _remoteMethods.get("isRollbackOnly").invoke(
				_remoteTransactionStatus, null);
		}
		catch (Exception e) {
			throw new IllegalArgumentException(
				"Could not invoke remote transaction status", e);
		}
	}

	public void releaseSavepoint(Object savepoint)
		throws TransactionException {
		try {
			_remoteMethods.get("releaseSavepoint").invoke(
				_remoteTransactionStatus, null);
		}
		catch (Exception e) {
			throw new IllegalArgumentException(
				"Could not invoke remote transaction status", e);
		}
	}

	public void rollbackToSavepoint(Object savepoint)
		throws TransactionException {
		try {
			_remoteMethods.get("rollbackToSavepoint").invoke(
				_remoteTransactionStatus, null);
		}
		catch (Exception e) {
			throw new IllegalArgumentException(
				"Could not invoke remote transaction status", e);
		}
	}

	public void setRollbackOnly() {
		try {
			_remoteMethods.get("setRollbackOnly").invoke(
				_remoteTransactionStatus, null);
		}
		catch (Exception e) {
			throw new IllegalArgumentException(
				"Could not invoke remote transaction status", e);
		}
	}

	private static Map<String, Method> _remoteMethods;
	private Object _remoteTransactionStatus;
}