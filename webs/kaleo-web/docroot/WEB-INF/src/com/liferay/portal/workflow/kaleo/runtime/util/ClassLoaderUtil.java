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

package com.liferay.portal.workflow.kaleo.runtime.util;

import com.liferay.portal.kernel.servlet.PluginContextListener;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

/**
 * @author Michael C. Han
 */
public class ClassLoaderUtil {

	public static ClassLoader[] getClassLoaders(String[] servletContextNames) {
		List<ClassLoader> classLoaders = new ArrayList<>(
			servletContextNames.length + 2);

		classLoaders.add(_getContextClassLoader());
		classLoaders.add(PortalClassLoaderUtil.getClassLoader());

		for (String servletContextName : servletContextNames) {
			ServletContext servletContext = ServletContextPool.get(
				servletContextName);

			ClassLoader pluginClassLoader =
				(ClassLoader)servletContext.getAttribute(
					PluginContextListener.PLUGIN_CLASS_LOADER);

			classLoaders.add(pluginClassLoader);
		}

		return classLoaders.toArray(new ClassLoader[classLoaders.size()]);
	}

	private static ClassLoader _getContextClassLoader() {
		Thread currentThread = Thread.currentThread();

		return currentThread.getContextClassLoader();
	}

}