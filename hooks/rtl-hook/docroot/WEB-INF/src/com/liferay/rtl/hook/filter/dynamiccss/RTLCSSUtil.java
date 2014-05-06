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

package com.liferay.rtl.hook.filter.dynamiccss;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.StringUtil;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.ScriptableObject;

/**
 * @author Eduardo Garcia
 */
public class RTLCSSUtil {

	public static String getRtlCss(String css) throws Exception {
		Context context = Context.enter();

		String rtlCss = css;

		try {
			ScriptableObject scope = context.initStandardObjects();

			context.evaluateString(scope, _jsScript, "script", 1, null);

			Function function = (Function)scope.get("r2", scope);

			Object result = function.call(
				context, scope, scope, new Object[] {css});

			rtlCss = (String)Context.jsToJava(result, String.class);
		}
		finally {
			Context.exit();
		}

		return rtlCss;
	}

	public static void init() {
		ClassLoader classLoader = PortletClassLoaderUtil.getClassLoader();

		if (classLoader == null) {
			classLoader = RTLCSSUtil.class.getClassLoader();
		}

		init(classLoader);
	}

	public static void init(ClassLoader classLoader) {
		try {
			_jsScript = StringUtil.read(
				classLoader,
				"com/liferay/rtl/hook/filter/dynamiccss/dependencies/r2.js");
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public static boolean isExcludedPath(String filePath) {
		if (filePath.matches(".*\\/ckeditor\\/.*")) {
			return true;
		}

		return false;
	}

	private static Log _log = LogFactoryUtil.getLog(RTLCSSUtil.class);

	private static String _jsScript;

}