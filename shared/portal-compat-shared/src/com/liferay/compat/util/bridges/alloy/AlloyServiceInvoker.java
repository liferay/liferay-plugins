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

package com.liferay.compat.util.bridges.alloy;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.model.BaseModel;

import java.lang.reflect.Method;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class AlloyServiceInvoker {

	public AlloyServiceInvoker(String className) {
		Class<?> clazz = getClass();

		ClassLoader classLoader = clazz.getClassLoader();

		int pos = className.indexOf(".model.");

		String simpleClassName = className.substring(pos + 7);

		String serviceClassName =
			className.substring(0, pos) + ".service." + simpleClassName +
				"LocalServiceUtil";

		try {
			Class<?> serviceClass = classLoader.loadClass(serviceClassName);

			dynamicQueryCountMethod = serviceClass.getMethod(
				"dynamicQueryCount", new Class[] {DynamicQuery.class});
			dynamicQueryMethod = serviceClass.getMethod(
				"dynamicQuery", new Class[] {DynamicQuery.class});
			fetchModelMethod = serviceClass.getMethod(
				"fetch" + simpleClassName, new Class[] {long.class});
			getModelsCountMethod = serviceClass.getMethod(
				"get" + TextFormatter.formatPlural(simpleClassName) + "Count",
				new Class[0]);
			getModelsMethod = serviceClass.getMethod(
				"get" + TextFormatter.formatPlural(simpleClassName),
				new Class[] {int.class, int.class});
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery) throws Exception {
		return (List)dynamicQueryMethod.invoke(false, dynamicQuery);
	}

	public long dynamicQueryCount(DynamicQuery dynamicQuery) throws Exception {
		return (Long)dynamicQueryCountMethod.invoke(false, dynamicQuery);
	}

	public BaseModel<?> fetchModel(long classPK) throws Exception {
		return (BaseModel<?>)fetchModelMethod.invoke(false, classPK);
	}

	@SuppressWarnings("rawtypes")
	public List getModels(int start, int end) throws Exception {
		return (List)getModelsMethod.invoke(false, start, end);
	}

	public int getModelsCount() throws Exception {
		return (Integer)getModelsCountMethod.invoke(false);
	}

	protected Method dynamicQueryCountMethod;
	protected Method dynamicQueryMethod;
	protected Method fetchModelMethod;
	protected Method getModelsCountMethod;
	protected Method getModelsMethod;

}