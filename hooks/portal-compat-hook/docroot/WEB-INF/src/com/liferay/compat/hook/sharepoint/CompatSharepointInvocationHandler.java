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

package com.liferay.compat.hook.sharepoint;

import com.liferay.compat.portal.kernel.webdav.WebDAVUtil;

import java.lang.Object;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Brian Wing Shun Chan
 */
public class CompatSharepointInvocationHandler implements InvocationHandler {

	public CompatSharepointInvocationHandler(Object sharepointMethod) {
		_sharepointMethod = sharepointMethod;
	}

	public Object getSharepointMethod() {
		return _sharepointMethod;
	}

	public Object invoke(Object proxy, Method method, Object[] arguments)
		throws Throwable {

		try {
			String methodName = method.getName();

			Object value = method.invoke(_sharepointMethod, arguments);

			if (methodName.equals("getRootPath")) {
				String rootPath = (String)value;

				rootPath = WebDAVUtil.stripManualCheckInRequiredPath(rootPath);

				value = rootPath;
			}

			return value;
		}
		catch (InvocationTargetException ite) {
			throw ite.getTargetException();
		}
	}

	private Object _sharepointMethod;

}