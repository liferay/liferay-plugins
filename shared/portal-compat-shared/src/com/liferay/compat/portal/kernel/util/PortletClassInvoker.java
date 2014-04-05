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

package com.liferay.compat.portal.kernel.util;

import com.liferay.portal.kernel.portlet.PortletBag;
import com.liferay.portal.kernel.portlet.PortletBagPool;
import com.liferay.portal.kernel.servlet.PluginContextListener;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

import javax.servlet.ServletContext;

/**
 * @author Bruno Farache
 * @author Shuyang Zhou
 */
public class PortletClassInvoker {

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #invoke(String, MethodKey,
	 *             Object...)}
	 */
	@Deprecated
	public static Object invoke(
			boolean newInstance, String portletId, MethodKey methodKey,
			Object... arguments)
		throws Exception {

		return invoke(portletId, methodKey, arguments);
	}

	public static Object invoke(
			String portletId, MethodKey methodKey, Object... arguments)
		throws Exception {

		portletId = _getRootPortletId(portletId);

		ClassLoader portletClassLoader = PortalClassLoaderUtil.getClassLoader();

		PortletBag portletBag = PortletBagPool.get(portletId);

		if (portletBag != null) {
			ServletContext servletContext = portletBag.getServletContext();

			portletClassLoader = (ClassLoader)servletContext.getAttribute(
				PluginContextListener.PLUGIN_CLASS_LOADER);
		}

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(portletClassLoader);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, arguments);

			return methodHandler.invoke();
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}
	}

	/**
	 * Copied from <code>com.liferay.portal.model.PortletConstants</code>.
	 */
	private static String _getRootPortletId(String portletId) {
		int pos = portletId.indexOf(_INSTANCE_SEPARATOR);

		if (pos == -1) {
			return portletId;
		}
		else {
			return portletId.substring(0, pos);
		}
	}

	private static final String _INSTANCE_SEPARATOR = "_INSTANCE_";

}