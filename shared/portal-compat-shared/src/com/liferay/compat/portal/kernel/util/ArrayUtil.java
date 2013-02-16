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

package com.liferay.compat.portal.kernel.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class ArrayUtil extends com.liferay.portal.kernel.util.ArrayUtil {

	public static long[] toLongArray(Collection<Long> collection) {
		long[] newArray = new long[collection.size()];

		if (collection instanceof List) {
			List<Long> list = (List<Long>)collection;

			for (int i = 0; i < list.size(); i++) {
				Long value = list.get(i);

				newArray[i] = value.longValue();
			}
		}
		else {
			int i = 0;

			Iterator<Long> iterator = collection.iterator();

			while (iterator.hasNext()) {
				Long value = iterator.next();

				newArray[i++] = value.longValue();
			}
		}

		return newArray;
	}

}