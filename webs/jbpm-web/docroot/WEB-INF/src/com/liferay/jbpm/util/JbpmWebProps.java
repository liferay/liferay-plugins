/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.jbpm.util;

import com.germinus.easyconf.ComponentProperties;

import com.liferay.util.ExtPropertiesLoader;

import java.util.Properties;

/**
 * <a href="JbpmWebProps.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JbpmWebProps {

	public static boolean containsKey(String key) {
		return _getInstance().containsKey(key);
	}

	public static String get(String key) {
		return _getInstance().get(key);
	}

	public static void set(String key, String value) {
		_getInstance().set(key, value);
	}

	public static String[] getArray(String key) {
		return _getInstance().getArray(key);
	}

	public static Properties getProperties() {
		return _getInstance().getProperties();
	}

	public static ComponentProperties getComponentProperties() {
		return _getInstance().getComponentProperties();
	}

	private static ExtPropertiesLoader _getInstance() {
		return ExtPropertiesLoader.getInstance("jbpm-web");
	}

}