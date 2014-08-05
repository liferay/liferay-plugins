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

package com.liferay.compat.portal.kernel.bean;

import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

import java.lang.reflect.Method;

/**
 * @author Danny Situ
 */
public class BeanPropertiesUtil
	extends com.liferay.portal.kernel.bean.BeanPropertiesUtil {

	public static void setPropertySilent(
		Object bean, String param, Object value) {

		try {
			ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

			Class<?> clazz = classLoader.loadClass("jodd.bean.BeanUtil");

			Method method = clazz.getMethod(
				"setPropertyForcedSilent",
				new Class[] {Object.class, String.class, Object.class});

			method.invoke(null, bean, param, value);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}