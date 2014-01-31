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
		return _extRepositoryAdapterThreadLocal.get();
	}

	@Override
	public ExtRepositoryAdapterCache clone() {
		if (_log.isInfoEnabled()) {
			_log.info("Cache created: " + Thread.currentThread().getName());
		}

		try {
			return (ExtRepositoryAdapterCache)super.clone();
		}
		catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends ExtRepositoryModelAdapter<?>> T get(
		String extRepositoryModelKey) {

		Map<String, ExtRepositoryModelAdapter<?>> extRepositoryAdaptersCache =
			_getExtRepositoryAdaptersCache();

		T extRepositoryAdapter = (T)extRepositoryAdaptersCache.get(
			extRepositoryModelKey);

		if (extRepositoryAdapter != null) {
			if (_log.isDebugEnabled()) {
				_log.debug("Cache hit: " + extRepositoryModelKey);
			}
		}
		else {
			if (_log.isDebugEnabled()) {
				_log.debug("Cache miss: " + extRepositoryModelKey);
			}
		}

		return extRepositoryAdapter;
	}

	public void put(ExtRepositoryModelAdapter<?> extRepositoryModelAdapter) {
		ExtRepositoryModel extRepositoryModel =
			extRepositoryModelAdapter.getExtRepositoryModel();

		String extRepositoryModelKey =
			extRepositoryModel.getExtRepositoryModelKey();

		Map<String, ExtRepositoryModelAdapter<?>> extRepositoryAdaptersCache =
			_getExtRepositoryAdaptersCache();

		extRepositoryAdaptersCache.put(
			extRepositoryModelKey, extRepositoryModelAdapter);

		if (_log.isInfoEnabled()) {
			_log.info("Cache put: " + extRepositoryModelKey);
		}
	}

	public void remove(String extRepositoryModelKey) {
		Map<String, ExtRepositoryModelAdapter<?>> extRepositoryAdaptersCache =
			_getExtRepositoryAdaptersCache();

		extRepositoryAdaptersCache.remove(extRepositoryModelKey);

		if (_log.isInfoEnabled()) {
			_log.info("Cache remove: " + extRepositoryModelKey);
		}
	}

	private Map<String, ExtRepositoryModelAdapter<?>>
		_getExtRepositoryAdaptersCache() {

		if (_extRepositoryAdaptersCache == null) {
			_extRepositoryAdaptersCache =
				new HashMap<String, ExtRepositoryModelAdapter<?>>();
		}

		return _extRepositoryAdaptersCache;
	}

	private static Log _log = LogFactoryUtil.getLog(
		ExtRepositoryAdapterCache.class);

	private static ThreadLocal<ExtRepositoryAdapterCache>
		_extRepositoryAdapterThreadLocal =
			new AutoResetThreadLocal<ExtRepositoryAdapterCache>(
				ExtRepositoryAdapterCache.class.getName(),
				new ExtRepositoryAdapterCache());

	private Map<String, ExtRepositoryModelAdapter<?>>
		_extRepositoryAdaptersCache;

}