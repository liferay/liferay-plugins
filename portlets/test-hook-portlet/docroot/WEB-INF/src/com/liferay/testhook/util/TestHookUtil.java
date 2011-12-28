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

package com.liferay.testhook.util;

import com.liferay.portal.kernel.util.SystemProperties;
import com.liferay.portal.kernel.util.Time;

import java.io.File;

/**
 * @author Brian Wing Shun Chan
 */
public class TestHookUtil {

	public static File getStartupActionFile() {
		return _instance._getStartupActionFile();
	}

	private TestHookUtil() {
		String tmpDir = SystemProperties.get(SystemProperties.TMP_DIR);

		_startupActionFileName =
			tmpDir + "/liferay/testhook/" + Time.getTimestamp();
	}

	public File _getStartupActionFile() {
		return new File(_startupActionFileName);
	}

	private static TestHookUtil _instance = new TestHookUtil();

	private String _startupActionFileName;

}