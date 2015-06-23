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

package com.liferay.compat.portal.kernel.util;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Joan Kim
 */
public class Validator extends com.liferay.portal.kernel.util.Validator {

	public static boolean isUri(String uri) {
		if (isNotNull(uri)) {
			try {
				new URI(uri);

				return true;
			}
			catch (URISyntaxException urise) {
			}
		}

		return false;
	}

}