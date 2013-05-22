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

package com.liferay.servletjspcompiler.compiler.internal;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.module.framework.ModuleFrameworkUtilAdapter;
import com.liferay.portal.util.ClassLoaderUtil;

import java.net.JarURLConnection;
import java.net.URL;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import org.osgi.framework.Bundle;
import org.osgi.framework.wiring.BundleWiring;

import org.phidias.compile.ResourceResolver;

/**
 * @author Raymond Augé
 */
public class JspResourceResolver implements ResourceResolver {

	public JspResourceResolver(JspResourceCache jspResourceCache) {
		_jspResourceCache = jspResourceCache;
	}

	public URL getResource(BundleWiring bundleWiring, String name) {
		Bundle bundle = bundleWiring.getBundle();

		URL url = bundle.getResource(name);

		if ((url == null) && (bundle.getBundleId() == 0)) {
			ClassLoader classLoader = ClassLoaderUtil.getPortalClassLoader();

			return classLoader.getResource(name);
		}

		return bundle.getResource(name);
	}

	public Collection<String> resolveResources(
		BundleWiring bundleWiring, String path, String filePattern,
		int options) {

		Collection<String> resources = bundleWiring.listResources(
			path, filePattern, options);

		Bundle bundle = bundleWiring.getBundle();

		if (((resources == null) || resources.isEmpty()) &&
			(bundle.getBundleId() == 0)) {

			return handleSystemBundle(bundleWiring, path, filePattern, options);
		}

		return resources;
	}

	protected Collection<String> handleSystemBundle(
		BundleWiring bundleWiring, final String path, final String fileRegex,
		int options) {

		String key = path + StringPool.SLASH + fileRegex;

		Collection<String> resources = _jspResourceCache.getResources(
			bundleWiring, key);

		if (resources != null) {
			return resources;
		}

		resources = new ArrayList<String>();

		Map<String, List<URL>> extraPackageMap =
			ModuleFrameworkUtilAdapter.getExtraPackageMap();

		String packageName = path.replace('/', '.');

		List<URL> urls = extraPackageMap.get(packageName);

		if ((urls == null) || !urls.isEmpty()) {
			_jspResourceCache.putResources(bundleWiring, key, resources);

			return resources;
		}

		String matcherRegex = fileRegex.replace(StringPool.STAR, "[^/]*");

		matcherRegex = matcherRegex.replace(".", "\\.");

		matcherRegex = path + "/" + matcherRegex;

		for (URL url : urls) {
			try {
				JarURLConnection jarUrlConnection =
					(JarURLConnection)url.openConnection();

				JarFile jarFile = jarUrlConnection.getJarFile();

				Enumeration<? extends ZipEntry> enumeration = jarFile.entries();

				while (enumeration.hasMoreElements()) {
					ZipEntry zipEntry = enumeration.nextElement();

					String name = zipEntry.getName();

					if (name.matches(matcherRegex)) {
						resources.add(name);
					}
				}
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		_jspResourceCache.putResources(bundleWiring, key, resources);

		return resources;
	}

	private static Log _log = LogFactoryUtil.getLog(JspResourceResolver.class);

	private JspResourceCache _jspResourceCache;

}