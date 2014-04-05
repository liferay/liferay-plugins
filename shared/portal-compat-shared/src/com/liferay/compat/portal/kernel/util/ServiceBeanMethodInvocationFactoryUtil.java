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

import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

import java.lang.reflect.Method;

/**
 * @author Brian Wing Shun Chan
 * @author Wesley Gong
 */
public class ServiceBeanMethodInvocationFactoryUtil {

	public static Object proceed(
			Object target, Class<?> targetClass, Method method,
			Object[] arguments, String[] methodInterceptorBeanIds)
		throws Exception {

		ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

		Class<?> clazz = classLoader.loadClass(
			"com.liferay.portal.kernel.util." +
				"ServiceBeanMethodInvocationFactoryUtil");

		Method proceedMethod = clazz.getMethod(
			"proceed", Object.class, Class.class, Method.class, Object[].class,
			String[].class);

		return proceedMethod.invoke(
			null, target, targetClass, method, arguments,
			methodInterceptorBeanIds);
	}

}