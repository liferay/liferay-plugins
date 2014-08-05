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

package com.liferay.compat.portal.kernel.dao.orm;

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author Ethan Bustad
 */
public class ProjectionFactoryUtil
	extends com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil {

	public static Projection sqlGroupProjection(
		String sql, String groupBy, String[] columnAliases, Type[] types) {

		try {
			ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

			Class<? extends Projection> projectionImplClass =
				(Class<? extends Projection>)classLoader.loadClass(
					"com.liferay.portal.dao.orm.hibernate.ProjectionImpl");

			Class<?> hibernateProjectionClass = classLoader.loadClass(
				"org.hibernate.criterion.Projection");

			Constructor<? extends Projection> projectionImplConstructor =
				projectionImplClass.getConstructor(hibernateProjectionClass);

			Class<?> hibernateProjectionsClass = classLoader.loadClass(
				"org.hibernate.criterion.Projections");

			Class<?> hibernateTypeClass = classLoader.loadClass(
				"org.hibernate.type.Type");

			Object hibernateTypeArray = Array.newInstance(
				hibernateTypeClass, types.length);

			Method sqlGroupProjectionMethod =
				hibernateProjectionsClass.getMethod(
					"sqlGroupProjection", String.class, String.class,
					String[].class, hibernateTypeArray.getClass());

			if (ArrayUtil.isEmpty(types)) {
				return projectionImplConstructor.newInstance(
					sqlGroupProjectionMethod.invoke(
						null, sql, groupBy, columnAliases, null));
			}

			Class<?> typeTranslatorClass = classLoader.loadClass(
				"com.liferay.portal.dao.orm.hibernate.TypeTranslator");

			Method translateMethod = typeTranslatorClass.getMethod(
				"translate", Type.class);

			for (int i = 0; i < types.length; i++) {
				Object value = translateMethod.invoke(null, types[i]);

				Array.set(hibernateTypeArray, i, value);
			}

			return projectionImplConstructor.newInstance(
				sqlGroupProjectionMethod.invoke(
					null, sql, groupBy, columnAliases, hibernateTypeArray));
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}