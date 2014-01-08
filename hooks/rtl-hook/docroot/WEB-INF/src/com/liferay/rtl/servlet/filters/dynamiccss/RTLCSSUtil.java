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

package com.liferay.rtl.servlet.filters.dynamiccss;

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

	public static String getRtlCss(String fileName, String css)
		throws Exception {

		Context context = Context.enter();

		String rtlCss = css;

		try {
			ScriptableObject scope = context.initStandardObjects();

			// Prepare the context to execute the script with Rhino 1.7

			context.evaluateString(
				scope, "var module = {exports: {}};", "module", 1, null);
			context.evaluateString(
				scope, "function require() {}", "require", 1, null);
			context.evaluateString(
				scope,
				"String.prototype.trim = function() {return " +
					"this.replace(/^\\s+|\\s+$/g, '');}",
				"trim", 1, null);
			context.evaluateString(scope, _jsScript, "script", 1, null);

			Function function = (Function)scope.get("r2", scope);

			Object result = function.call(
				context, scope, scope, new Object[] {css});

			rtlCss = (String)Context.jsToJava(result, String.class);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to parse " + fileName + " to RTL");
			}
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
				"com/liferay/rtl/servlet/filters/dynamiccss" +
					"/dependencies/r2.js");
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(RTLCSSUtil.class);

	private static String _jsScript;

}