/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.hook.events;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Matthew Kong
 */
public class StartupAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		try {
			doRun();
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void doRun() throws Exception {
		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Class<?> customSQLClass = portalClassLoader.loadClass(
			"com.liferay.util.dao.orm.CustomSQL");

		Method readMethod = ReflectionUtil.getDeclaredMethod(
			customSQLClass, "read", ClassLoader.class, String.class);

		Class<?> customSQLUtilClass = portalClassLoader.loadClass(
			"com.liferay.util.dao.orm.CustomSQLUtil");

		Field instanceField = ReflectionUtil.getDeclaredField(
			customSQLUtilClass, "_instance");

		Object instance = instanceField.get(null);

		Field customSQLField = ReflectionUtil.getDeclaredField(
			customSQLUtilClass, "_customSQL");

		Object customSQL = customSQLField.get(instance);

		Class<?> clazz = getClass();

		ClassLoader contextClassLoader = clazz.getClassLoader();

		readMethod.invoke(
			customSQL, contextClassLoader, "/custom-sql/announcements.xml");
	}

}