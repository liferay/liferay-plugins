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

package com.liferay.compat.portal.kernel.util;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class PortalClassInvoker {

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #invoke(MethodKey,
	 *             Object...)}
	 */
	@Deprecated
	public static Object invoke(
			boolean newInstance, MethodKey methodKey, Object... arguments)
		throws Exception {

		return invoke(methodKey, arguments);
	}

	public static Object invoke(MethodKey methodKey, Object... arguments)
		throws Exception {

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(
				PortalClassLoaderUtil.getClassLoader());

			MethodHandler methodHandler = new MethodHandler(
				methodKey, arguments);

			return methodHandler.invoke();
		}
		catch (InvocationTargetException ite) {
			Throwable cause = ite.getCause();

			if (cause instanceof Error) {
				throw new SystemException(ite);
			}
			else {
				throw (Exception)cause;
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}
	}

}