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

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.SimplePojoClp;

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
 * A transaction manager class loader proxy delegating the method invocations to
 * the transaction manager in the portal implementation. The transaction manager
 * within the portal must be specified as a Spring bean having id
 * <code>"liferayTransactionManager"</code>.
 * </p>
 *
 * @author Micha Kiener
 */
public class TransactionManagerClp implements PlatformTransactionManager {

	public void commit(TransactionStatus transactionStatus)
		throws TransactionException {

		try {
			Method method = _transactionManagerMethods.get("commit");

			method.invoke(
				_transactionManager,
				unwrapTransactionStatus(transactionStatus));
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new TransactionSystemException(e.getMessage());
		}
	}

	public TransactionStatus getTransaction(
			TransactionDefinition transactionDefinition)
		throws TransactionException {

		Object transactionStatus = null;

		try {
			Method method = _transactionManagerMethods.get("getTransaction");

			transactionStatus = method.invoke(
				_transactionManager,
				createRemoteTransactionDefinition(transactionDefinition));
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new TransactionSystemException(e.getMessage());
		}

		return new TransactionStatusClp(transactionStatus);
	}

	@PostConstruct
	public void init() throws ClassNotFoundException {
		_transactionManager = PortalBeanLocatorUtil.locate(
			"liferayTransactionManager");

		_transactionDefinitionClp = new SimplePojoClp<TransactionDefinition>(
			DefaultTransactionDefinition.class,
			PortalClassLoaderUtil.getClassLoader());

		initTransactionManagerMethods();
	}

	public void rollback(TransactionStatus transactionStatus)
		throws TransactionException {

		try {
			Method method = _transactionManagerMethods.get("rollback");

			method.invoke(
				_transactionManager,
				unwrapTransactionStatus(transactionStatus));
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new TransactionSystemException(e.getMessage());
		}
	}

	protected Object createRemoteTransactionDefinition(
			TransactionDefinition transactionDefinition)
		throws IllegalAccessException, InstantiationException {

		return _transactionDefinitionClp.createRemoteObject(
			transactionDefinition);
	}

	protected void initTransactionManagerMethods() {
		_transactionManagerMethods = new HashMap<String, Method>();

		Method[] methods = _transactionManager.getClass().getMethods();

		for (Method method : methods) {
			_transactionManagerMethods.put(method.getName(), method);
		}
	}

	protected Object unwrapTransactionStatus(
		TransactionStatus localTransactionStatus) {

		TransactionStatusClp transactionStatusClp =
			(TransactionStatusClp)localTransactionStatus;

		return transactionStatusClp.getRemoteTransactionStatus();
	}

	private static Log _log =
		LogFactoryUtil.getLog(TransactionManagerClp.class);

	private SimplePojoClp<TransactionDefinition> _transactionDefinitionClp;
	private Object _transactionManager;
	private Map<String, Method> _transactionManagerMethods;

}