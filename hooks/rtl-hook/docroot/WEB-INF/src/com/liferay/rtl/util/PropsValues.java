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

package com.liferay.rtl.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

/**
 * @author Eduardo Garcia
 * @see com.liferay.portal.util.PropsValues
 */
public class PropsValues {

	public static final String LIFERAY_LIB_PORTAL_DIR = PropsUtil.get(
		PropsKeys.LIFERAY_LIB_PORTAL_DIR);

	public static final String SCRIPTING_JRUBY_COMPILE_MODE = PropsUtil.get(
		PropsKeys.SCRIPTING_JRUBY_COMPILE_MODE);

	public static final int SCRIPTING_JRUBY_COMPILE_THRESHOLD =
		GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.SCRIPTING_JRUBY_COMPILE_THRESHOLD), 50);

	public static final String[] SCRIPTING_JRUBY_LOAD_PATHS =
		PropsUtil.getArray(PropsKeys.SCRIPTING_JRUBY_LOAD_PATHS);

	public static boolean THEME_CSS_FAST_LOAD = GetterUtil.getBoolean(
		PropsUtil.get(PropsKeys.THEME_CSS_FAST_LOAD));

}