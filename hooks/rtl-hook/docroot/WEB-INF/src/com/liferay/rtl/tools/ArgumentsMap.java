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

package com.liferay.rtl.tools;

import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;

/**
 * @author Raymond Augé
 * @author Eduardo Garcia
 * @see com.liferay.portal.tools.ArgumentsMap
 */
public class ArgumentsMap extends HashMap<String, String> {

	@Override
	public String get(Object key) {
		String value = super.get(key);

		if (Validator.isNull(value)) {
			value = System.getProperty((String)key);
		}

		return value;
	}

}