/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.contacts.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Ryan Park
 */
public class ContactsExtensionsUtil {

	public static Map<String, String> getExtensions() {
		return _instance._getExtensions();
	}

	public static void register(String servletContextName, String path) {
		_instance._register(servletContextName, path);
	}

	public static void unregister(String servletContextName) {
		_instance._unregister(servletContextName);
	}

	private ContactsExtensionsUtil() {
		_extensions = new ConcurrentHashMap<String, String>();
	}

	public Map<String, String> _getExtensions() {
		return _extensions;
	}

	private void _register(String servletContextName, String path) {
		_extensions.put(servletContextName, path);
	}

	private void _unregister(String servletContextName) {
		_extensions.remove(servletContextName);
	}

	private static ContactsExtensionsUtil _instance =
		new ContactsExtensionsUtil();

	private Map<String, String> _extensions;

}