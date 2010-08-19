/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.wsrp.servlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.ReflectionUtil;

import java.lang.reflect.Field;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.cache.MethodCache;

/**
 * @author Shuyang Zhou
 */
public class AxisThreadLocalFilter extends BaseFilter {

	protected Log getLog() {
		return _log;
	}

	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {
		try {
			processFilter(
				AxisThreadLocalFilter.class, request, response, filterChain);
		}
		finally {
			try {
				ThreadLocal cache = (ThreadLocal)_cacheField.get(null);
				if (cache != null) {
					cache.remove();
				}
			}
			catch(Exception e) {
				_log.error(
					"Fail to remove " + MethodCache.class + "'s ThreadLocal",
					e);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		AxisThreadLocalFilter.class);

	private static Field _cacheField;

	static {
		try {
			_cacheField = ReflectionUtil.getDeclaredField(
				MethodCache.class, "cache");
		}
		catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

}