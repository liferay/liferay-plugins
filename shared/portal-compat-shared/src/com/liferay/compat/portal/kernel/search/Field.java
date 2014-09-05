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

package com.liferay.compat.portal.kernel.search;

import java.lang.reflect.Method;

/**
 * @author Minhchau Dang
 */
public class Field extends com.liferay.portal.kernel.search.Field {

	public static boolean isSortable(
		com.liferay.portal.kernel.search.Field field) {

		Method method = _getIsSortableMethod();

		if (method == null) {
			return false;
		}

		try {
			return (Boolean)method.invoke(field);
		}
		catch (Exception e) {
			return false;
		}
	}

	public Field(String name, String value) {
		super(name, value);
	}

	private static Method _getIsSortableMethod() {
		if (_hasSortableMethod != null) {
			return _sortableMethod;
		}

		Class<?> clazz = com.liferay.portal.kernel.search.Field.class;

		try {
			_sortableMethod = clazz.getMethod("isSortable");

			_hasSortableMethod = true;
		}
		catch (NoSuchMethodException e) {
			_hasSortableMethod = false;
		}

		return _sortableMethod;
	}

	private static Boolean _hasSortableMethod;
	private static Method _sortableMethod;

}