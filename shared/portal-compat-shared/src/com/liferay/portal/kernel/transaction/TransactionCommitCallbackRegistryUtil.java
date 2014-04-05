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

package com.liferay.portal.kernel.transaction;

import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

import java.lang.reflect.Method;

import java.util.concurrent.Callable;

/**
 * @author Michael C. Han
 */
public class TransactionCommitCallbackRegistryUtil {

	public static void registerCallback(Callable<?> callable) {
		try {
			ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

			Class<?> clazz = classLoader.loadClass(
				"com.liferay.portal.spring.transaction." +
					"TransactionCommitCallbackUtil");

			Method method = clazz.getMethod("registerCallback", Callable.class);

			method.setAccessible(true);

			method.invoke(null, callable);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void setTransactionCallbackRegistry(
		TransactionCommitCallbackRegistry transactionCommitCallbackRegistry) {

		// This should never be invoked except when invoking Service Builder
		// from the command line

	}

}