/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portlet.service;

import com.liferay.portal.kernel.cache.CacheKVP;
import com.liferay.portal.kernel.cache.CacheRegistry;
import com.liferay.portal.kernel.cache.CacheRegistryItem;
import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringMaker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.BaseModel;
import com.liferay.portlet.service.PropsUtil;
import com.liferay.util.CollectionFactory;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * <a href="FinderCache.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FinderCache implements CacheRegistryItem {

	public static final boolean CACHE_ENABLED = GetterUtil.getBoolean(
		PropsUtil.get(PropsUtil.VALUE_OBJECT_FINDER_CACHE_ENABLED), true);

	public static final String CACHE_NAME = FinderCache.class.getName();

	public static void clearCache() {
		_instance._clearCache();
	}

	public static void clearCache(String className) {
		_instance._clearCache(className);
	}

	public static Object getResult(
		String className, String methodName, String[] params, Object[] args) {

		return _instance._getResult(className, methodName, params, args);
	}

	public static Object getResult(
		String className, String methodName, String[] params, Object[] args,
		SessionFactory sessionFactory) {

		return _instance._getResult(
			className, methodName, params, args, sessionFactory);
	}

	public static Object getResult(
		String sql, String[] classNames, String methodName, String[] params,
		Object[] args) {

		return _instance._getResult(sql, classNames, methodName, params, args);
	}

	public static Object getResult(
		String sql, String[] classNames, String methodName, String[] params,
		Object[] args, SessionFactory sessionFactory) {

		return _instance._getResult(
			sql, classNames, methodName, params, args, sessionFactory);
	}

	public static void putResult(
		String className, String methodName, String[] params, Object[] args,
		Object result) {

		_instance._putResult(className, methodName, params, args, result);
	}

	public static void putResult(
		String sql, String[] classNames, String methodName, String[] params,
		Object[] args, Object result) {

		_instance._putResult(sql, classNames, methodName, params, args, result);
	}

	public void invalidate() {
		_clearCache();
	}

	private FinderCache() {
		CacheRegistry.register(this);
	}

	private void _clearCache() {
		_cache.removeAll();
	}

	private void _clearCache(String className) {
		String groupKey = _encodeGroupKey(className);

		MultiVMPoolUtil.clearGroup(_groups, groupKey, _cache);
	}

	private Object _getResult(
		String className, String methodName, String[] params, Object[] args) {

		return _getResult(className, methodName, params, args, null);
	}

	private Object _getResult(
		String className, String methodName, String[] params, Object[] args,
		SessionFactory sessionFactory) {

		String key = _encodeKey(className, methodName, params, args);

		Object primaryKey = MultiVMPoolUtil.get(_cache, key);

		if (primaryKey != null) {
			Session session = null;

			try {
				session = HibernateUtil.openSession(sessionFactory);

				return _primaryKeyToResult(session, primaryKey);
			}
			finally {
				HibernateUtil.closeSession(session);
			}
		}
		else {
			return null;
		}
	}

	private Object _getResult(
		String sql, String[] classNames, String methodName, String[] params,
		Object[] args) {

		return _getResult(sql, classNames, methodName, params, args, null);
	}

	private Object _getResult(
		String sql, String[] classNames, String methodName, String[] params,
		Object[] args, SessionFactory sessionFactory) {

		String key = _encodeKey(sql, methodName, params, args);

		Object primaryKey = MultiVMPoolUtil.get(_cache, key);

		if (primaryKey != null) {
			Session session = null;

			try {
				session = HibernateUtil.openSession(sessionFactory);

				return _primaryKeyToResult(session, primaryKey);
			}
			finally {
				HibernateUtil.closeSession(session);
			}
		}
		else {
			return null;
		}
	}

	private void _putResult(
		String className, String methodName, String[] params, Object[] args,
		Object result) {

		if (CACHE_ENABLED && CacheRegistry.isActive() && (result != null)) {
			StringMaker sm = new StringMaker();

			sm.append(PropsUtil.VALUE_OBJECT_FINDER_CACHE_ENABLED);
			sm.append(StringPool.PERIOD);
			sm.append(className);

			boolean classNameCacheEnabled = GetterUtil.getBoolean(
				PropsUtil.get(sm.toString()), true);

			if (classNameCacheEnabled) {
				String key = _encodeKey(className, methodName, params, args);

				String groupKey = _encodeGroupKey(className);

				MultiVMPoolUtil.put(
					_cache, key, _groups, groupKey,
					_resultToPrimaryKey(result));
			}
		}
	}

	private void _putResult(
		String sql, String[] classNames, String methodName, String[] params,
		Object[] args, Object result) {

		if (CACHE_ENABLED && CacheRegistry.isActive() && (result != null)) {
			for (int i = 0; i < classNames.length; i++) {
				String className = classNames[i];

				StringMaker sm = new StringMaker();

				sm.append(PropsUtil.VALUE_OBJECT_FINDER_CACHE_ENABLED);
				sm.append(StringPool.PERIOD);
				sm.append(className);

				boolean classNameCacheEnabled = GetterUtil.getBoolean(
					PropsUtil.get(sm.toString()), true);

				if (!classNameCacheEnabled) {
					return;
				}
			}

			String key = _encodeKey(sql, methodName, params, args);

			for (int i = 0; i < classNames.length; i++) {
				String className = classNames[i];

				String groupKey = _encodeGroupKey(className);

				MultiVMPoolUtil.updateGroup(_groups, groupKey, key);
			}

			MultiVMPoolUtil.put(_cache, key, _resultToPrimaryKey(result));
		}
	}

	private String _encodeGroupKey(String className) {
		StringMaker sm = new StringMaker();

		sm.append(CACHE_NAME);
		sm.append(StringPool.POUND);
		sm.append(className);

		return sm.toString();
	}

	private String _encodeKey(
		String className, String methodName, String[] params, Object[] args) {

		StringMaker sm = new StringMaker();

		sm.append(CACHE_NAME);
		sm.append(StringPool.POUND);
		sm.append(className);
		sm.append(StringPool.POUND);
		sm.append(methodName);
		sm.append(_PARAMS_SEPARATOR);

		for (int i = 0; i < params.length; i++) {
			String param = params[i];

			sm.append(StringPool.POUND);
			sm.append(param);
		}

		sm.append(_ARGS_SEPARATOR);

		for (int i = 0; i < args.length; i++) {
			Object arg = args[i];

			sm.append(StringPool.POUND);
			sm.append(String.valueOf(arg));
		}

		return sm.toString();
	}

	private Object _primaryKeyToResult(
		Session session, Object primaryKey) {

		if (primaryKey instanceof CacheKVP) {
			CacheKVP cacheKVP = (CacheKVP)primaryKey;

			Class modelClass = cacheKVP.getModelClass();
			Serializable primaryKeyObj = cacheKVP.getPrimaryKeyObj();

			return session.load(modelClass, primaryKeyObj);
		}
		else if (primaryKey instanceof List) {
			List cachedList = (List)primaryKey;

			List list = new ArrayList(cachedList.size());

			for (int i = 0; i < cachedList.size(); i++) {
				Object result = _primaryKeyToResult(session, cachedList.get(i));

				list.add(result);
			}

			return list;
		}
		else {
			return primaryKey;
		}
	}

	private Object _resultToPrimaryKey(Object result) {
		if (result instanceof BaseModel) {
			BaseModel model = (BaseModel)result;

			Class modelClass = model.getClass();
			Serializable primaryKeyObj = model.getPrimaryKeyObj();

			return new CacheKVP(modelClass, primaryKeyObj);
		}
		else if (result instanceof List) {
			List list = (ArrayList)result;

			List cachedList = new ArrayList(list.size());

			for (int i = 0; i < list.size(); i++) {
				Object primaryKey = _resultToPrimaryKey(list.get(i));

				cachedList.add(primaryKey);
			}

			return cachedList;
		}
		else {
			return result;
		}
	}

	private static final String _ARGS_SEPARATOR = "_ARGS_SEPARATOR_";

	private static final String _PARAMS_SEPARATOR = "_PARAMS_SEPARATOR_";

	private static FinderCache _instance = new FinderCache();

	private PortalCache _cache = MultiVMPoolUtil.getCache(CACHE_NAME);

	private Map _groups = CollectionFactory.getSyncHashMap();

}