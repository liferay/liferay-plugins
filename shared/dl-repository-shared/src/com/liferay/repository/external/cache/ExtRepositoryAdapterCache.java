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
package com.liferay.repository.external.cache;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.repository.external.ExtRepositoryModel;
import com.liferay.repository.external.model.ExtRepositoryModelAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Iván Zaera
 */
public class ExtRepositoryAdapterCache implements Cloneable {

	public static ExtRepositoryAdapterCache getInstance() {
		return _threadLocal.get();
	}

	@Override
	public ExtRepositoryAdapterCache clone() {
		if (_log.isInfoEnabled()) {
			_log.info("Cache created: " + Thread.currentThread().getName() );
		}

		try {
			return (ExtRepositoryAdapterCache)super.clone();
		}
		catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends ExtRepositoryModelAdapter<?>> T get(String modelKey) {
		T adapter = (T)_getAdaptersCache().get(modelKey);

		if (_log.isDebugEnabled()) {
			if (adapter != null) {
				_log.debug("Cache hit: " + modelKey);
			}
			else {
				_log.debug("Cache miss: " + modelKey);
			}
		}

		return adapter;
	}

	public void put(ExtRepositoryModelAdapter<?> adapter) {
		ExtRepositoryModel model = adapter.getExtRepositoryModel();

		String modelKey = model.getExtRepositoryModelKey();

		_getAdaptersCache().put(modelKey, adapter);

		if (_log.isInfoEnabled()) {
			_log.info("Cache put: " + modelKey);
		}
	}

	public void remove(String modelKey) {
		_getAdaptersCache().remove(modelKey);

		if (_log.isInfoEnabled()) {
			_log.info("Cache remove: " + modelKey);
		}
	}

	private Map<String, ExtRepositoryModelAdapter<?>> _getAdaptersCache() {
		if (_adaptersCache == null) {
			_adaptersCache =
				new HashMap<String, ExtRepositoryModelAdapter<?>>();
		}

		return _adaptersCache;
	}

	private static Log _log = LogFactoryUtil.getLog(
		ExtRepositoryAdapterCache.class);

	private static AutoResetThreadLocal<ExtRepositoryAdapterCache>
		_threadLocal =
			new AutoResetThreadLocal<ExtRepositoryAdapterCache>(
				ExtRepositoryAdapterCache.class + "._tls",
				new ExtRepositoryAdapterCache() );

	private Map<String, ExtRepositoryModelAdapter<?>> _adaptersCache;

}