/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.compat.portal.kernel.transaction;

import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

import java.lang.reflect.Method;

import java.util.concurrent.Callable;

/**
 * @author Shuyang Zhou
 */
public class TransactionInvokerUtil {

	public static <T> T invoke(
			TransactionAttribute transactionAttribute, Callable<T> callable)
		throws Throwable {

		ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

		Class<?> transactionalCallableUtilClass = classLoader.loadClass(
			"com.liferay.portal.spring.transaction.TransactionalCallableUtil");

		Method callMethod = transactionalCallableUtilClass.getMethod(
			"call",
			classLoader.loadClass(
				"org.springframework.transaction.interceptor." +
					"TransactionAttribute"),
			Callable.class);

		Class<?> transactionAttributeBuilderClass = classLoader.loadClass(
			"com.liferay.portal.spring.transaction." +
				"TransactionAttributeBuilder");

		Method buildMethod = transactionAttributeBuilderClass.getMethod(
			"build", boolean.class, Isolation.class, Propagation.class,
			boolean.class, int.class, Class[].class, String[].class,
			Class[].class, String[].class);

		Object springTransactionAttribute = buildMethod.invoke(
			null, true, transactionAttribute.getIsolation(),
			transactionAttribute.getPropagation(),
			transactionAttribute.isReadOnly(),
			transactionAttribute.getTimeout(),
			transactionAttribute.getRollbackForClasses(),
			transactionAttribute.getRollbackForClassNames(),
			transactionAttribute.getNoRollbackForClasses(),
			transactionAttribute.getNoRollbackForClassNames());

		return (T)callMethod.invoke(null, springTransactionAttribute, callable);
	}

}