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

import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import java.util.Arrays;

/**
 * @author Shuyang Zhou
 */
public class MethodHandler implements Serializable {

	public MethodHandler(Method method, Object... arguments) {
		this(new MethodKey(method), arguments);
	}

	public MethodHandler(MethodKey methodKey, Object... arguments) {
		_methodKey = methodKey;
		_arguments = arguments;
	}

	public Object[] getArguments() {
		Object[] arguments = new Object[_arguments.length];

		System.arraycopy(_arguments, 0, arguments, 0, _arguments.length);

		return arguments;
	}

	public MethodKey getMethodKey() {
		return _methodKey;
	}

	public Object invoke() throws Exception {
		Method method = _methodKey.getMethod();

		Object targetObject = null;

		if (!Modifier.isStatic(method.getModifiers())) {
			Class<?> targetClass = _methodKey.getDeclaringClass();

			targetObject = targetClass.newInstance();
		}

		return method.invoke(targetObject, _arguments);
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #invoke}
	 */
	@Deprecated
	public Object invoke(boolean newInstance) throws Exception {
		return invoke();
	}

	public Object invoke(Object target) throws Exception {
		Method method = _methodKey.getMethod();

		return method.invoke(target, _arguments);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{arguments=");
		sb.append(Arrays.toString(_arguments));
		sb.append(", methodKey=");
		sb.append(_methodKey);
		sb.append("}");

		return sb.toString();
	}

	private Object[] _arguments;
	private MethodKey _methodKey;

}