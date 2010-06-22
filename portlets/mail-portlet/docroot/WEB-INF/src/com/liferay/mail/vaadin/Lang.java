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

package com.liferay.mail.vaadin;

import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.ResourceBundle;

/**
 * <a href="Lang.java.html"><b><i>View Source</i></b></a>
 *
 * @author Henri Sara
 */
public class Lang {

	public static String get(String key) {
		String value = resourceBundle.getString(key);

		if (value != null) {
			return value;
		}
		else {
			return LanguageUtil.get(Controller.get().getUserLocale(), key);
		}
	}

	public static String get(String key, String... arguments) {
		return LanguageUtil.format(
			Controller.get().getUserLocale(), Lang.get(key), arguments);
	}

	private static ResourceBundle resourceBundle = ResourceBundle.getBundle(
		"content.Language", Controller.get().getUserLocale());

}