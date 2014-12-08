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

package com.liferay.marketplace.util;

import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Joan Kim
 */
public class ContextUtil {

	public static String getContextName(String contextPath) {
		String contextName = contextPath;

		if (contextName.length() == 0) {
			return StringPool.BLANK;
		}

		if (contextName.startsWith(StringPool.FORWARD_SLASH)) {
			contextName = contextName.substring(1);
		}

		if (contextName.endsWith(StringPool.FORWARD_SLASH)) {
			contextName = contextName.substring(0, contextName.length() - 1);
		}

		int index = getPluginTypeIndex(contextName);

		if (index >= 0) {
			contextName = contextName.substring(0, index);
		}

		return contextName;
	}

	protected static int getPluginTypeIndex(String contextName) {
		for (String pluginType : _PLUGIN_TYPES) {
			int index = contextName.lastIndexOf(pluginType);

			if (index >= 0) {
				return index + pluginType.length();
			}
		}

		return -1;
	}

	private static final String[] _PLUGIN_TYPES = {
		"-ext", "-hook", "-layouttpl", "-portlet", "-theme", "-web"
	};

}