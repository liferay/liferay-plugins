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

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public class ListUtil extends com.liferay.portal.kernel.util.ListUtil {

	public static boolean isEmpty(List<?> list) {
		if ((list == null) || list.isEmpty()) {
			return true;
		}

		return false;
	}

	public static boolean isNotEmpty(List<?> list) {
		return !isEmpty(list);
	}

	public static <T> List<T> unique(List<T> list) {
		Set<T> set = new LinkedHashSet<T>();

		set.addAll(list);

		if (list.size() == set.size()) {
			return list;
		}

		return new ArrayList<T>(set);
	}

}