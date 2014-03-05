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

package com.liferay.sync.engine.util;

import java.io.File;

/**
 * @author Brian Wing Shun Chan
 */
public class OSDetector {

	public static boolean isAIX() {
		if (_aix != null) {
			return _aix.booleanValue();
		}

		String osName = System.getProperty("os.name").toLowerCase();

		if (osName.equals("aix")) {
			_aix = Boolean.TRUE;
		}
		else {
			_aix = Boolean.FALSE;
		}

		return _aix.booleanValue();
	}

	public static boolean isApple() {
		if (_apple != null) {
			return _apple.booleanValue();
		}

		String osName = System.getProperty("os.name").toLowerCase();

		if (osName.contains("mac")) {
			_apple = Boolean.TRUE;
		}
		else {
			_apple = Boolean.FALSE;
		}

		return _apple.booleanValue();
	}

	public static boolean isUnix() {
		if (_unix != null) {
			return _unix.booleanValue();
		}

		if (File.pathSeparator.equals(":")) {
			_unix = Boolean.TRUE;
		}
		else {
			_unix = Boolean.FALSE;
		}

		return _unix.booleanValue();
	}

	public static boolean isWindows() {
		if (_windows != null) {
			return _windows.booleanValue();
		}

		if (File.pathSeparator.equals(";")) {
			_windows = Boolean.TRUE;
		}
		else {
			_windows = Boolean.FALSE;
		}

		return _windows.booleanValue();
	}

	private static Boolean _aix;
	private static Boolean _apple;
	private static Boolean _unix;
	private static Boolean _windows;

}