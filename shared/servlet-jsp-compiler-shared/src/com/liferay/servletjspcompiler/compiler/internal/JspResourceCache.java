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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;
import org.osgi.framework.wiring.BundleWiring;

/**
 * @author Raymond Augé
 */
public class JspResourceCache implements BundleListener {

	public void bundleChanged(BundleEvent event) {
		if ((event.getType() & BundleEvent.UNRESOLVED) !=
				BundleEvent.UNRESOLVED) {

			return;
		}

		_resourceMaps.remove(event.getBundle());
	}

	public Collection<String> getResources(
		BundleWiring bundleWiring, String path) {

		Map<String, Collection<String>> resourceMap = _resourceMaps.get(
			bundleWiring.getBundle());

		if (resourceMap == null) {
			return null;
		}

		return resourceMap.get(path);
	}

	public void putResources(
		BundleWiring bundleWiring, String path, Collection<String> resources) {

		Map<String, Collection<String>> resourceMap = _resourceMaps.get(
			bundleWiring.getBundle());

		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<String>>();
		}

		resourceMap.put(path, resources);
	}

	private Map<Bundle, Map<String, Collection<String>>> _resourceMaps =
		new ConcurrentHashMap<Bundle, Map<String, Collection<String>>>();

}