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

package com.liferay.opensocial.model.impl;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Dennis Ju
 */
public class GadgetConstants {

	public static final String ADHOC_PREFIX = "ADHOC_";

	public static final String PUBLISHED_PREFIX = "PUBLISHED_";

	public static boolean isAdhocGadget(String gadgetKey) {
		return StringUtil.contains(gadgetKey, ADHOC_PREFIX);
	}

	public static boolean isPublishedGadget(String gadgetKey) {
		return StringUtil.contains(gadgetKey, PUBLISHED_PREFIX);
	}

	public static long toAdhocGadgetId(String gadgetKey) {
		String moduleIdString = StringUtil.remove(gadgetKey, ADHOC_PREFIX);

		return GetterUtil.getLong(moduleIdString);
	}

	public static String toAdhocGadgetKey(long moduleId) {
		return ADHOC_PREFIX.concat(String.valueOf(moduleId));
	}

	public static long toPublishedGadgetId(String gadgetKey) {
		String gadgetIdString = StringUtil.remove(gadgetKey, PUBLISHED_PREFIX);

		return GetterUtil.getLong(gadgetIdString);
	}

	public static String toPublishedGadgetKey(long gadgetId) {
		return PUBLISHED_PREFIX.concat(String.valueOf(gadgetId));
	}

}