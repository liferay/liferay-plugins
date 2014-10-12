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

import com.liferay.dlfilename.hook.model.impl.DLFileNameBackgroundTaskImpl;
import com.liferay.dlfilename.hook.model.impl.DLFileNameFileEntryImpl;
import com.liferay.dlfilename.hook.model.impl.DLFileNameFileVersionImpl;
import com.liferay.portal.kernel.lar.ExportImportThreadLocal;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.model.BackgroundTask;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

/**
* @author Preston Crary
*/
class DLFileNameInvocationHandler implements InvocationHandler {

	public DLFileNameInvocationHandler(Object object) {
		_object = object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
		throws Throwable {

		try {
			if (!ExportImportThreadLocal.isExportInProcess()) {
				return wrap(method.invoke(_object, args));
			}
			else {
				return method.invoke(_object, args);
			}
		}
		catch (InvocationTargetException ite) {
			throw ite.getTargetException();
		}
	}

	protected Object setAssetEntryTitle(AssetEntry assetEntry) {
		String className = assetEntry.getClassName();

		try {
			if (className.equals(DLFileEntry.class.getName())) {
				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
					assetEntry.getClassPK());

				assetEntry.setTitle(fileEntry.getTitle());
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to update asset entry title", e);
			}
		}

		return assetEntry;
	}

	protected Object wrap(Object object) {
		if (object instanceof List) {
			List<Object> wrappedList = new ArrayList<Object>();

			for (Object value : (List<Object>)object) {
				wrappedList.add(wrap(value));
			}

			return wrappedList;
		}

		if (object instanceof AssetEntry) {
			return setAssetEntryTitle((AssetEntry)object);
		}
		else if (object instanceof BackgroundTask) {
			return new DLFileNameBackgroundTaskImpl((BackgroundTask)object);
		}
		else if (object instanceof FileEntry) {
			return new DLFileNameFileEntryImpl((FileEntry)object);
		}
		else if (object instanceof FileVersion) {
			return new DLFileNameFileVersionImpl((FileVersion)object);
		}

		return object;
	}

	private static Log _log = LogFactoryUtil.getLog(
		DLFileNameInvocationHandler.class);

	private Object _object;

}