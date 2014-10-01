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

package com.liferay.dlfilename.hook.service.impl;

import com.liferay.dlfilename.hook.model.impl.DLFileNameWrapperFileEntryImpl;
import com.liferay.dlfilename.hook.model.impl.DLFileNameWrapperFileVersionImpl;
import com.liferay.dlfilename.hook.util.DLFileNameThreadLocal;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

/**
* @author Preston Crary
*/
class DLFileNameWrapperInvocationHandler implements InvocationHandler {
	public DLFileNameWrapperInvocationHandler(Object object) {
		_object = object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
		throws Throwable {

		Object returnValue = method.invoke(_object, args);

		if (!DLFileNameThreadLocal.isEnabled()) {
			return returnValue;
		}

		if (returnValue instanceof List) {
			List wrappedList = new ArrayList();

			for (Object value : (List)returnValue) {
				wrappedList.add(wrap(value));
			}

			return wrappedList;
		}
		else {
			return wrap(returnValue);
		}
	}

	protected Object wrap(Object object) {
		if (object instanceof FileEntry) {
			return new DLFileNameWrapperFileEntryImpl((FileEntry)object);
		}

		if (object instanceof FileVersion) {
			return new DLFileNameWrapperFileVersionImpl((FileVersion)object);
		}

		return object;
	}

	private Object _object;

}