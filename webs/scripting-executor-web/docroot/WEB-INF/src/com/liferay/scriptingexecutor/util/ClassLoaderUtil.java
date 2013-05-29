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

package com.liferay.scriptingexecutor.util;

import com.liferay.portal.kernel.servlet.PluginContextListener;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.util.AggregateClassLoader;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

/**
 * @author Michael C. Han
 * @see    com.liferay.portal.util.ClassLoaderUtil
 */
public class ClassLoaderUtil {

	public static ClassLoader getAggregatePluginsClassLoader(
		String[] servletContextNames, boolean addContextClassLoader) {

		List<ClassLoader> classLoaders = new ArrayList<ClassLoader>(
			servletContextNames.length + 1);

		ClassLoader contextClassLoader = null;

		if (addContextClassLoader) {
			contextClassLoader = _getContextClassLoader();

			classLoaders.add(contextClassLoader);
		}

		for (String servletContextName : servletContextNames) {
			ClassLoader pluginClassLoader = _getPluginClassLoader(
				servletContextName);

			classLoaders.add(pluginClassLoader);
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		if (!portalClassLoader.equals(contextClassLoader)) {
			classLoaders.add(portalClassLoader);
		}

		ClassLoader[] classloaders = classLoaders.toArray(
			new ClassLoader[classLoaders.size()]);

		return AggregateClassLoader.getAggregateClassLoader(classloaders);
	}

	private static ClassLoader _getContextClassLoader() {
		Thread currentThread = Thread.currentThread();

		return currentThread.getContextClassLoader();
	}

	private static ClassLoader _getPluginClassLoader(
		String servletContextName) {

		ServletContext servletContext = ServletContextPool.get(
			servletContextName);

		ClassLoader pluginClassLoader =
			(ClassLoader)servletContext.getAttribute(
				PluginContextListener.PLUGIN_CLASS_LOADER);

		return pluginClassLoader;
	}

}