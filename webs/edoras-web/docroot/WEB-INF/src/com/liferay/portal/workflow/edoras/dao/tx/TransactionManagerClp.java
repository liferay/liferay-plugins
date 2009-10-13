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

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * <a href="TransactionManagerClp.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * TODO: add Class-Description here ...
 * </p>
 *
 * @author Micha Kiener
 *
 */
public class TransactionManagerClp implements PlatformTransactionManager {

	@PostConstruct
	public void init()
		throws SystemException {
		_transactionManager =
			PortalBeanLocatorUtil.locate("liferayTransactionManager");

		_transactionDefinitionClp =
			new SimplePojoClp<TransactionDefinition>(
				PortalClassLoaderUtil.getClassLoader(),
				TransactionDefinition.class,
				DefaultTransactionDefinition.class,
				DefaultTransactionDefinition.class.getName());

		initTransactionManagerMethods();
	}

	protected void initTransactionManagerMethods() {
		_transactionManagerMethods = new HashMap<String, Method>();
		Method[] methods = _transactionManager.getClass().getMethods();
		for (Method method : methods) {
			_transactionManagerMethods.put(method.getName(), method);
		}
	}

	public TransactionStatus getTransaction(TransactionDefinition definition)
		throws TransactionException {
		Object transactionStatus;
		try {
			transactionStatus =
				_transactionManagerMethods.get("getTransaction").invoke(
			_transactionManager, createRemoteTransactionDefinition(definition));
		}
		catch (Exception e) {
			throw new TransactionSystemException(
				"Could not invoke remote transaction manager", e);
		}
		return new TransactionStatusClp(transactionStatus);
	}

	public void commit(TransactionStatus status)
		throws TransactionException {
		try {
			_transactionManagerMethods.get("commit").invoke(
				_transactionManager, unwrapTransactionStatus(status));
		}
		catch (Exception e) {
			throw new TransactionSystemException(
				"Could not commit transaction via remote transaction manager",
				e);
		}
	}

	public void rollback(TransactionStatus status)
		throws TransactionException {
		try {
			_transactionManagerMethods.get("rollback").invoke(
				_transactionManager, unwrapTransactionStatus(status));
		}
		catch (Exception e) {
			throw new TransactionSystemException(
				"Could not rollback transaction via remote transaction manager",
				e);
		}
	}

	protected Object createRemoteTransactionDefinition(
		TransactionDefinition definition) {
		return _transactionDefinitionClp.createRemoteObject(definition);
	}

	protected Object unwrapTransactionStatus(
		TransactionStatus localTransactionStatus) {
		return ((TransactionStatusClp) localTransactionStatus).getRemoteTransactionStatus();
	}

	private Object _transactionManager;
	private Map<String, Method> _transactionManagerMethods;
	private SimplePojoClp<TransactionDefinition> _transactionDefinitionClp;
}