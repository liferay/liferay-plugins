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

package com.liferay.compat.hook.webdav;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.webdav.Resource;
import com.liferay.portal.kernel.webdav.WebDAVException;

import java.io.InputStream;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Brian Wing Shun Chan
 */
public class CompatResourceInvocationHandler implements InvocationHandler {

	public CompatResourceInvocationHandler(Resource resource) {
		_resource = resource;

		try {
			Class<?> clazz = resource.getClass();

			Field field = clazz.getDeclaredField("_fileEntry");

			field.setAccessible(true);

			_fileEntry = (FileEntry)field.get(resource);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] arguments)
		throws Throwable {

		try {
			if (_fileEntry == null) {
				return method.invoke(_resource, arguments);
			}

			String methodName = method.getName();

			if (methodName.equals("getContentAsStream")) {
				return getContentAsStream();
			}
			else if (methodName.equals("getContentType")) {
				return getContentType();
			}
			else if (methodName.equals("getSize")) {
				return getSize();
			}

			return method.invoke(_resource, arguments);
		}
		catch (InvocationTargetException ite) {
			throw ite.getTargetException();
		}
	}

	protected InputStream getContentAsStream() throws WebDAVException {
		try {
			FileVersion fileVersion = _fileEntry.getLatestFileVersion();

			return fileVersion.getContentStream(true);
		}
		catch (Exception e) {
			throw new WebDAVException(e);
		}
	}

	protected String getContentType() {
		try {
			FileVersion fileVersion = _fileEntry.getLatestFileVersion();

			return fileVersion.getMimeType();
		}
		catch (Exception e) {
			return _fileEntry.getMimeType();
		}
	}

	protected long getSize() {
		try {
			FileVersion fileVersion = _fileEntry.getLatestFileVersion();

			return fileVersion.getSize();
		}
		catch (Exception e) {
			return _fileEntry.getSize();
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CompatResourceInvocationHandler.class);

	private FileEntry _fileEntry;
	private Resource _resource;

}